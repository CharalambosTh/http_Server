package project.http;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeAll;

@TestInstance(Lifecycle.PER_CLASS)
public class HttpParserTest {

    private HttpParser httpParser;

    @BeforeAll
    public void beforeClass() {
        httpParser = new HttpParser();
    }

    @Test
    void parseHttpRequest() {
        httpParser.parseHttpRequest(generateValidTestCase());

    }

    private InputStream generateValidTestCase() {
        String rawData = "GET / HTTP/1.1\r\n" + //
                "Host: localhost:8080\r\n" + //
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:139.0) Gecko/20100101 Firefox/139.0\r\n" + //
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" + //
                "Accept-Language: en-US,en;q=0.5\r\n" + //
                "Accept-Encoding: gzip, deflate, br, zstd\r\n" + //
                "Connection: keep-alive\r\n" + //
                "Upgrade-Insecure-Requests: 1\r\n" + //
                "Sec-Fetch-Dest: document    \r\n" + //
                "Sec-Fetch-Mode: navigate    \r\n" + //
                "Sec-Fetch-Site: none        \r\n" + //
                "Sec-Fetch-User: ?1\r\n" + //
                "Priority: u=0, i\r\n" + //
                "\r\n";
        InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));
        return inputStream;
    }

}
