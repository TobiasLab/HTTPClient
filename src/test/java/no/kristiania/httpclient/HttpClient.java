package no.kristiania.httpclient;

import java.io.IOException;
import java.net.Socket;

public class HttpClient {

    private String host;
    private int statusCode = 200;
    private String requestTarget;

    public HttpClient(String host, String requestTarget) {
        this.host = host;
        this.requestTarget = requestTarget;
    }

    public static void main(String[] args) throws IOException {
        new HttpClient("urlecho.appspot.com", "/echo?status=200&Content-Type=text%2Fhtml&body=Hello%20world!").executeRequest();
    }

    public HttpClientResponse executeRequest() throws IOException {
        try (Socket socket = new Socket(host, 80)) {
            socket.getOutputStream().write(("GET " + requestTarget + " HTTP/1.1\r\n").getBytes());
            socket.getOutputStream().write(("Host: " + host + "\r\n").getBytes());
            socket.getOutputStream().write("Connection: close\r\n".getBytes());
            socket.getOutputStream().write("\n\r".getBytes());
            socket.getOutputStream().flush();

            HttpClientResponse httpClientResponse = new HttpClientResponse(socket);
            httpClientResponse.invoke();
            return httpClientResponse;
        }
    }

}
