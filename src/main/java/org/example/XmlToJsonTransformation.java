package org.example;

import org.eclipse.epsilon.emc.plainxml.PlainXmlModel;
import org.eclipse.epsilon.emc.json.JsonModel;
import org.eclipse.epsilon.etl.EtlModule;

import java.io.File;

/**
 * Standalone Java application demonstrating XML to JSON transformation using Eclipse Epsilon.
 */
public class XmlToJsonTransformation {

    public static void main(String[] args) {
        try {
            System.out.println("Starting XML to JSON transformation...");

            // Get resource paths
            String xmlPath = getResourcePath("library.xml");
            String etlPath = getResourcePath("transformation.etl");
            String outputPath = "output.json";

            // Create and configure the XML source model
            PlainXmlModel sourceModel = new PlainXmlModel();
            sourceModel.setName("Source");
            sourceModel.setFile(new File(xmlPath));
            sourceModel.setReadOnLoad(true);
            sourceModel.setStoredOnDisposal(false);
            sourceModel.load();

            System.out.println("Loaded XML model: " + xmlPath);

            // Create and configure the JSON target model
            JsonModel targetModel = new JsonModel();
            targetModel.setName("Target");
            targetModel.setFile(new File(outputPath));
            targetModel.setReadOnLoad(false);
            targetModel.setStoredOnDisposal(true);
            targetModel.load();

            System.out.println("Initialized JSON target model");

            // Create and configure ETL module
            EtlModule etlModule = new EtlModule();
            etlModule.parse(new File(etlPath));

            if (!etlModule.getParseProblems().isEmpty()) {
                System.err.println("Parse errors in ETL script:");
                etlModule.getParseProblems().forEach(System.err::println);
                return;
            }

            // Add models to the ETL module context
            etlModule.getContext().getModelRepository().addModel(sourceModel);
            etlModule.getContext().getModelRepository().addModel(targetModel);

            // Execute the transformation
            System.out.println("Executing transformation...");
            etlModule.execute();

            // Dispose models (this will save the JSON file)
            targetModel.dispose();
            sourceModel.dispose();

            System.out.println("Transformation completed successfully!");
            System.out.println("Output saved to: " + outputPath);

        } catch (Exception e) {
            System.err.println("Error during transformation: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Helper method to get the absolute path of a resource file.
     */
    private static String getResourcePath(String resourceName) {
        ClassLoader classLoader = XmlToJsonTransformation.class.getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());
        return file.getAbsolutePath();
    }
}
