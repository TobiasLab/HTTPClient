package no.kristiania.http;

import no.kristiania.httpclient.HttpClient;
import no.kristiania.httpclient.HttpClientResponse;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HttpServerTest {

    @Test
    void shouldReturnRequestedErrorCode() throws IOException {
        startServer();
        HttpClient client = new HttpClient("localhost", 8080, "/echo?staus=401");
        HttpClientResponse response = client.executeRequest();
        assertEquals(401, response.getStatusCode());

        new Thread(() -> {
            try {
                HttpServer.main(null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        HttpClient client = new HttpClient("localhost", 8080, "/echo?status=401");
        HttpClientResponse response = client.executeRequest();
        assertEquals(401, response.getStatusCode());
    }
}