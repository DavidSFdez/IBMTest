package main.java.export.iface;

import main.java.export.exception.IBMTestExportException;
import main.java.persistence.model.Provider;

import java.util.List;

public interface ExporterService {

    /**
     * Exports the list of providers
     *
     * @param providers
     * @throws IBMTestExportException
     */
    void exportResults(List<Provider> providers) throws IBMTestExportException;
}
