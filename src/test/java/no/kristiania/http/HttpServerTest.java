package no.kristiania.http;

import no.kristiania.httpclient.HttpClient;
import no.kristiania.httpclient.HttpClientResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HttpServerTest {

    @Test
    void shouldReturnRequestedErrorCode() throws IOException {
        HttpClient client = new HttpClient("urlecho.appspot.com", "/echo?status=401");
        HttpClientResponse response = client.executeRequest();
        assertEquals(401, response.getStatusCode());
    }
}