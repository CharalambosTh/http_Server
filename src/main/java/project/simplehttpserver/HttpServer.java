package project.simplehttpserver;

import java.net.ServerSocket;
import java.net.Socket;

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

        // while (true) {

        // Basic HTTP response
        // String body = "<h1>Hello from my Java HTTP server!</h1>";
        // writer.write("HTTP/1.1 200 OK\r\n");
        // writer.write("Content-Type: text/html\r\n");
        // writer.write("Content-Length: " + body.length() + "\r\n");
        // writer.write("Connection: close\r\n");
        // writer.write("\r\n"); // End of headers
        // writer.write(body);
        // writer.flush();

        // Close client connection

        // }
    }

}