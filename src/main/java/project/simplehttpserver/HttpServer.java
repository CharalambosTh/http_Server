package project.simplehttpserver;

import java.net.ServerSocket;
import java.net.Socket;

import project.simplehttpserver.config.Configuration;
import project.simplehttpserver.config.ConfigurationManager;

import java.io.*;

public class HttpServer {

    public static void main(String[] args0) throws Exception {

        System.out.println("Server Starting...");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Using Port: " + conf.getPort());

        System.out.println("Using WebRoot: " + conf.getWebroot());

        // final ServerSocket server = new ServerSocket(8080);
        // System.out.println("Listening for connection on port 8080....");
        // while (true) {
        // Socket client = server.accept();

        // // Input
        // BufferedReader reader = new BufferedReader(new
        // InputStreamReader(client.getInputStream()));

        // // Output
        // BufferedWriter writer = new BufferedWriter(new
        // OutputStreamWriter(client.getOutputStream()));

        // String line = reader.readLine();
        // while (!line.isEmpty() && line != null) {
        // System.out.println(line);
        // line = reader.readLine();
        // }

        // // Basic HTTP response
        // String body = "<h1>Hello from my Java HTTP server!</h1>";
        // writer.write("HTTP/1.1 200 OK\r\n");
        // writer.write("Content-Type: text/html\r\n");
        // writer.write("Content-Length: " + body.length() + "\r\n");
        // writer.write("Connection: close\r\n");
        // writer.write("\r\n"); // End of headers
        // writer.write(body);
        // writer.flush();

        // // Close client connection
        // client.close();
        // }
    }

}