package project.simplehttpserver.config;

import project.simplehttpserver.util.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

public class ConfigurationManager {

    private static ConfigurationManager myConfigurationManager;
    private static Configuration myCurrentConfiguration;

    private ConfigurationManager() {

    }

    public static ConfigurationManager getInstance() {
        if (myConfigurationManager == null)
            myConfigurationManager = new ConfigurationManager();
        return myConfigurationManager;
    }

    /*
     * Load config file for the provided path
     */

    public void loadConfigurationFile(String filePath) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();
        int i;
        try {
            while ((i = fileReader.read()) != -1) {
                sb.append((char) i);

            }
        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }
        JsonNode conf = null;
        try {
            conf = Json.parse(sb.toString());
        } catch (IOException e) {
            throw new HttpConfigurationException("Error parsing the configuration File", e);
        }
        try {
            myCurrentConfiguration = Json.decode(conf, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing the Configuration file, internal", e);
        }

    }

    /*
     * Returns the Current loaded Configuration
     */
    public Configuration getCurrentConfiguration() {
        if (myCurrentConfiguration == null) {
            throw new HttpConfigurationException("No Current Configuration Set.");

        }
        return myCurrentConfiguration;

    }

}
