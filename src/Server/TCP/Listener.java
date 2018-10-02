package Server.TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

import Server.Utli.Command;
import Server.Utli.Executer;
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
        try {
            String command = input.readLine();
            Vector<String> arguments = Executer.parse(command);
            Command cmd = Command.fromString((String)arguments.elementAt(0));
            String result = Executer.execute(cmd, arguments, resourceManager);
            output.println(result);
            output.flush();
        }
        catch (IOException e){
            System.out.println("IO wrong for buffered reader.");
            System.exit(-1);
        }
    }
}
