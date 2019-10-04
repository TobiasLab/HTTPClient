package no.kristiania.httpclient;

import java.io.IOException;
import java.net.Socket;

public class HttpClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("urlecho.appspot.com", 80);

        socket.getOutputStream().write("GET /echo?status=200&Content-Type=text%2Fhtml&body=Hello%20world! HTTP/1.1\r\n".getBytes());
        socket.getOutputStream().write("Host: urlecho.appspot.com\r\n".getBytes());
        socket.getOutputStream().write("Connection: close\r\n".getBytes());
        socket.getOutputStream().write("\n\r".getBytes());
        socket.getOutputStream().flush();

        int c;
        while ((c = socket.getInputStream().read()) != -1)  {
            System.out.print((char)c);
        }
    }
}
