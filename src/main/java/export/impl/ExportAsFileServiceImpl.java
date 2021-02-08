package main.java.export.impl;

import main.java.export.exception.IBMTestExportException;
import main.java.export.iface.ExporterService;
import main.java.persistence.model.Provider;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * This implementation creates an export file with the result informed
 */
public class ExportAsFileServiceImpl implements ExporterService {

    private static final String path = "Export\\";
    private static final String fileName = "DATE_proveedores.txt";

    @Override
    public void exportResults(List<Provider> providers) throws IBMTestExportException {

        if (providers == null || providers.isEmpty()) {
            return;
        }

        FileWriter fw = null;

        // Gets the actual moment to set in the file name
        String now = String.valueOf(System.currentTimeMillis());

        // Creates the file into the indicated folder
        File parentFolder = new File(path);
        File file = new File(parentFolder, fileName.replace("DATE", now));

        try {
            file.createNewFile();

            fw = new FileWriter(file);
            StringBuilder sb = new StringBuilder();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            // writes the content of the file
            providers.stream().forEach(x -> {
                sb.append(x.getId() + ", ");
                sb.append(x.getName() + ", ");
                sb.append(df.format(x.getDischargeDate()) + ", ");
                sb.append(x.getClientId() + "\n");
            });

            // creates the content of the file
            fw.write(sb.toString());
            fw.flush();

        } catch (IOException e) {
            throw new IBMTestExportException("An error occurred creating the file: " + e, e);
        }

    }
}
