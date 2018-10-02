package Server.TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import Server.Common.ResourceManager;

public class Listener implements Runnable{
    Socket socket = null;
    ResourceManager resourceManager = null;

    public Listener(Socket socket, ResourceManager resourceManager){
        this.socket = socket;
        this.resourceManager = resourceManager;
    }

    public void run(){
        BufferedReader input = null;
        PrintWriter output = null;
        try{
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(),
                    true);
        } catch (IOException e) {
            System.out.println("Read failed");
            System.exit(-1);
        }
        try {
            String command = input.readLine();
            System.out.println(command);
        } catch (IOException e) {
            System.out.println("Read failed");
            System.exit(-1);
        }
    }
}
