package main.java.main;

import main.java.export.exception.IBMTestExportException;
import main.java.export.iface.ExporterService;
import main.java.export.impl.ExportAsFileServiceImpl;
import org.apache.commons.lang3.StringUtils;
import main.java.persistence.dao.ProviderDAO;
import main.java.persistence.exception.IBMTestPersistenceException;
import main.java.persistence.impl.ProviderDAOImpl;
import main.java.persistence.model.Provider;

import java.util.List;

public class IBMTestMain {

    public static void main(String[] args) {
        if (args != null && args.length > 0 && StringUtils.trimToNull(args[0]) != null) {
            if (checkValidArgument(args[0])) {
                ProviderDAO dao = new ProviderDAOImpl();
                ExporterService exporter = new ExportAsFileServiceImpl();
                // Finds the providers in database by the informed argument
                List<Provider> providers = null;
                try {
                    providers = dao.getByClientId(Integer.valueOf(args[0]));
                } catch (IBMTestPersistenceException e) {
                    System.out.println("Se ha producido un error: " + e.getMessage());
                    return;
                }
                // If no one provider was returned, show a message to the user
                if (providers == null || providers.isEmpty()) {
                    System.out.println("El ciente no tiene proveedores asignados");
                    return;
                } else {
                    try {
                        // Exports the result
                        exporter.exportResults(providers);
                    } catch (IBMTestExportException e) {
                        System.out.println("Se ha producido un error: " + e.getMessage());
                        return;
                    }
                }
            } else {
                System.out.println("El argumento introducido ha de ser un número");
            }
        } else {
            System.out.println("Debe informarse un código de cliente válido por argumento");
        }
    }

    /**
     * Checks that the argument entered is correct
     *
     * @param arg
     * @return true if the argument is numeric, falase if not
     */
    private static boolean checkValidArgument(String arg) {
        char[] argument = arg.toCharArray();
        for (char character : argument) {
            if (!Character.isDigit(character)) {
                return false;
            }
        }
        return true;
    }
}