package no.kristiania.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class HttpClient {

    private String host;
    private int statusCode = 200;
    private String requestTarget;
    private String statusLine;

    public HttpClient(String host, String requestTarget) {
        this.host = host;
        this.requestTarget = requestTarget;
    }

    public static void main(String[] args) throws IOException {
        new HttpClient("urlecho.appspot.com", "/echo?status=200&Content-Type=text%2Fhtml&body=Hello%20world!").executeRequest();
    }

    public void executeRequest() throws IOException {
        try (Socket socket = new Socket(host, 80)) {
            socket.getOutputStream().write(("GET " + requestTarget + " HTTP/1.1\r\n").getBytes());
            socket.getOutputStream().write(("Host: " + host + "\r\n").getBytes());
            socket.getOutputStream().write("Connection: close\r\n".getBytes());
            socket.getOutputStream().write("\n\r".getBytes());
            socket.getOutputStream().flush();

            InputStream input = socket.getInputStream();
            int c;// c is really a byte (0-255), but has to be int to allow for -1

            StringBuilder statusLine = new StringBuilder();
            while ((c = input.read()) != -1 && c != '\r') {
                statusLine.append((char)c);
            }
            this.statusLine = statusLine.toString();

            while ((c = input.read()) != -1) { //-1 means "end of stream"
                System.out.print((char)c); // Force c (which is an int) to be interpreted as character
                // c = input.read();
            }
        }
    }

    public int getStatusCode() {
        return Integer.parseInt(statusLine.split(" ")[1]);
    }

}
