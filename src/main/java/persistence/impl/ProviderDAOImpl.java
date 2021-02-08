package main.java.persistence.impl;

import main.java.persistence.dao.ProviderDAO;
import main.java.persistence.exception.IBMTestPersistenceException;
import main.java.persistence.model.Provider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProviderDAOImpl implements ProviderDAO {

    // Database connection data
    private static final String DATABASE_URL = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7391703?useSSL=false";
    private static final String DATABASE_USER = "sql7391703";
    private static final String DATABASE_PASSWORD = "vCAUXtiYlN";

    // Query
    private static final String QUERY_GET_BY_CLIENT_ID = "SELECT * FROM PROVEEDOR WHERE ID_CLIENTE = ?";

    // Table column names
    private static final String PROVIDER_ID = "ID_PROVEEDOR";
    private static final String PROVIDER_NAME = "NOMBRE";
    private static final String PROVIDER_DISCHARGE_DATE = "FECHA_DE_ALTA";
    private static final String PROVIDER_CLIENT_ID = "ID_CLIENTE";

    @Override
    public List<Provider> getByClientId(Integer clientId) throws IBMTestPersistenceException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // Creates the connection
        conn = getDBConnection();

        try {
            // Sets the query and its parameters
            StringBuffer stb = new StringBuffer();
            stb.append(QUERY_GET_BY_CLIENT_ID);
            ps = conn.prepareStatement(stb.toString());
            ps.setInt(1, clientId);

            // Execute the query
            rs = ps.executeQuery();

            return extractResults(rs);
        } catch (SQLException e) {
            throw new IBMTestPersistenceException("An error occurred executing the query: "+e.getMessage(),e);
        } catch (Exception e){
            throw new IBMTestPersistenceException("Unexpected error executing the query: "+e.getMessage(),e);
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }catch (SQLException e) {
                throw new IBMTestPersistenceException("An unexpected error occurred clossing the connection: "+e.getMessage(), e);
            }
        }

    }

    /**
     * Extract the providers result
     *
     * @param rs
     * @return list of providers
     * @throws SQLException
     */
    private List<Provider> extractResults(ResultSet rs) throws SQLException {
        List<Provider> results = new ArrayList<>();
        if(rs !=null){
            while (rs.next()) {
                Provider entry = new Provider();
                entry.setId(rs.getInt(PROVIDER_ID));
                entry.setName(rs.getString(PROVIDER_NAME));
                entry.setDischargeDate(rs.getDate(PROVIDER_DISCHARGE_DATE));
                entry.setClientId(rs.getInt(PROVIDER_CLIENT_ID));
                results.add(entry);
            }
        }
        return results;
    }


    /**
     * Prepare the connection to the database
     *
     * @return database connection
     * @throws IBMTestPersistenceException
     */
    private Connection getDBConnection() throws IBMTestPersistenceException {
        Connection conn = null;

        Properties props = new Properties();
        props.setProperty("user", DATABASE_USER);
        props.setProperty("password", DATABASE_PASSWORD);


        try {
            conn = DriverManager.getConnection(DATABASE_URL, props);
            conn.setAutoCommit(false);

        } catch (SQLException e) {
            throw new IBMTestPersistenceException("Unexpected exception connecting to database: "+e.getMessage(), e);
        }  catch (Exception e){
            throw new IBMTestPersistenceException("Unexpected exception creating the connection to database: "+e.getMessage(), e);
        }

        return conn;
    }
}
