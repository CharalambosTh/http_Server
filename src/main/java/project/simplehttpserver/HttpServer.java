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

        try {
            final ServerSocket server = new ServerSocket(conf.getPort());
            System.out.println("Listening for connection on port " + conf.getPort() + "....");
            Socket client = server.accept();
            // Input
            InputStream inputStream = client.getInputStream();
            // Output
            OutputStream outputStream = client.getOutputStream();

            String html = "<html><head><title>Http Server</title></head><body><h1>Hello World! (Server by Java http server)</h1></body></html>";

            final String CRLF = "\n\r"; // 13, 10

            // Status Line : HTTP VERSION RESPONSE_CODE RESPONSE_MESSAGE;
            String response = "HTTP/1.1 200 OK" + CRLF +
                    "Content-Length: " + html.getBytes().length + CRLF + // HEADER
                    CRLF +
                    html +
                    CRLF + CRLF;

            outputStream.write(response.getBytes());

            inputStream.close();
            outputStream.close();
            client.close();
            server.close();

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