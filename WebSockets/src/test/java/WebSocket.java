import java.io.*;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.Scanner;

public class WebSocket {
    private ServerSocket server;
    private Calculator calculator;

    public WebSocket(String ipAddress) throws Exception {
        if (ipAddress != null && !ipAddress.isEmpty())
            this.server = new ServerSocket(0, 1, InetAddress.getByName(ipAddress));
        else
            this.server = new ServerSocket(0, 1, InetAddress.getLocalHost());
        calculator = new Calculator();
    }

    private void listen() throws Exception {
        try {
            Thread thread = new Thread((Runnable) new ProcessRequest());
            thread.start();
        } catch (Exception e) {
            System.out.println("thead can't start");
        }
    }

    public InetAddress getSocketAddress() {
        return this.server.getInetAddress();
    }

    public int getPort() {
        return this.server.getLocalPort();
    }
    public static void main(String[] args) throws Exception {
        WebSocket app = new WebSocket(null);
        System.out.println("\r\nRunning Server: " +
                "Host=" + app.getSocketAddress().getHostAddress() +
                " Port=" + app.getPort());

        app.listen();
    }
}
