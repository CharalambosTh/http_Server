package project.simplehttpserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpConnectionWorkerThread extends Thread {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);
    private Socket client;

    public HttpConnectionWorkerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            // Input
            inputStream = client.getInputStream();
            // Output
            outputStream = client.getOutputStream();

            String html = "<html><head><title>Http Server</title></head><body><h1>Hello World! (Server by Java http server)</h1></body></html>";

            final String CRLF = "\n\r"; // 13, 10

            // Status Line : HTTP VERSION RESPONSE_CODE RESPONSE_MESSAGE;
            String response = "HTTP/1.1 200 OK" + CRLF +
                    "Content-Length: " + html.getBytes().length + CRLF + // HEADER
                    CRLF +
                    html +
                    CRLF + CRLF;

            outputStream.write(response.getBytes());

            LOGGER.info("Connection Proccessing Finished.");
        } catch (IOException e) {
            LOGGER.error("Problem with communication", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
            }
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
            }
        }
    }

}
