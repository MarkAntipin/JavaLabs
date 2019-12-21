import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;


public class ProcessRequest implements Runnable{
    ServerSocket server;

    public ProcessRequest(ServerSocket server) {
        this.server = server;
    }
    public void run(){
        String data = null;
        Socket client = server.accept();
        String clientAddress = client.getInetAddress().getHostAddress();
        System.out.println("\r\nNew connection from " + clientAddress);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream()));

        String result;
        Calculator calculator = new Calculator();

        while ( (data = in.readLine()) != null ) {
            System.out.println("\r\nMessage from " + clientAddress + ": " + data);
            try {
                result = calculator.process(data) + '\n';
            } catch(Exception e) {
                result = "invalid command\n";
            }


            OutputStream os = client.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(result);
            bw.flush();
        }
    }
}
