package project.simplehttpserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import project.simplehttpserver.config.Configuration;
import project.simplehttpserver.config.ConfigurationManager;
import project.simplehttpserver.core.ServerListenerThread;

import java.io.*;

public class HttpServer {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

    public static void main(String[] args) {

        LOGGER.info("Server Starting...");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        LOGGER.info("Using Port: " + conf.getPort());

        LOGGER.info("Using WebRoot: " + conf.getWebroot());

        try {
            ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
            serverListenerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}