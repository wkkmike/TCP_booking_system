package Server.TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

import Server.Common.*;
import Server.Utli.Command;
import Server.Utli.Executer;


public class Listener implements Runnable{
    Socket socket = null;
    ResourceManager resourceManager = null;
    BufferedReader input = null;
    PrintWriter output = null;

    public Listener(Socket socket, ResourceManager resourceManager){
        this.socket = socket;
        this.resourceManager = resourceManager;
        try{
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(),
                    true);
        } catch (IOException e) {
            System.out.println("Failed for get input and output stream");
            System.exit(-1);
        }
    }

    public void run(){
        String command = "";
        try{
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(),
                    true);
        } catch (IOException e) {
            System.out.println("Failed for get input and output stream");
            System.exit(-1);
        }
        try {
            command = input.readLine();
            System.out.println(command);
        } catch (IOException e) {
            System.out.println("Read failed");
            System.exit(-1);
        }

        try {
            Vector<String> arguments = parser.parse(command);
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

    // Block lambda to parse command
    Parser parser = (command) -> {
        Vector<String> arguments = new Vector<String>();
        StringTokenizer tokenizer = new StringTokenizer(command,",");
        String argument = "";
        while (tokenizer.hasMoreTokens())
        {
            argument = tokenizer.nextToken();
            argument = argument.trim();
            arguments.add(argument);
        }
        return arguments;
    };
}
