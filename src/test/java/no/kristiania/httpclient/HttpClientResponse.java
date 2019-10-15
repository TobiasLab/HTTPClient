package no.kristiania.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;


public class HttpClientResponse {

    private Socket socket;
    private String statusLine;

    public HttpClientResponse(Socket socket) {
        this.socket = socket;
    }

    public void invoke() throws IOException {
        InputStream input = socket.getInputStream();
        int c;

        this.statusLine = readLine(socket);
        System.out.print(this.statusLine);
        String line;
        while (!(line = readLine(socket)).isEmpty()) {
            System.out.println(getClass().getSimpleName() + ": " + line);
        }
    }

    private static String readLine(Socket socket) throws IOException {
        int c;
        StringBuilder line = new StringBuilder();
        while ((c = socket.getInputStream().read()) != -1) {
            if (c == '\r') {
                c = socket.getInputStream().read();
                if (c != '\n') {
                    System.err.println("Unexpected character! " + ((char)c));
                }
            }
        }
        return line.toString();
    }


    public int getStatusCode() {
        return Integer.parseInt(statusLine.split(" ")[1]);
    }
}

