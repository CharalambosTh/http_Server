package project.simplehttpserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerListenerThread extends Thread {

    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);

    private int port;
    private String webroot;
    private ServerSocket serverSocket;

    public ServerListenerThread(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.serverSocket = new ServerSocket(this.port);

    }

    @Override
    public void run() {
        try {

            while (serverSocket.isBound() && !serverSocket.isClosed()) {
                Socket client = serverSocket.accept();

                LOGGER.info("Connection accepted: " + client.getInetAddress());

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
            }
            // serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}