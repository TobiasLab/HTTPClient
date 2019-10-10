package no.kristiania.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);

        Socket socket = serverSocket.accept();
        String line;
        while (!(line = readLine(socket)).isEmpty()) {
            System.out.println("Line" + line);
        }
        System.out.println("Done");
    }

    private static String readLine(Socket socket) throws IOException {
        int c;
        StringBuilder line = new StringBuilder();
        while ((c = socket.getInputStream().read()) != -1 && c != '\n' && c != '\r') {
            line.append((char)c);
        }
        return line.toString();
    }
}