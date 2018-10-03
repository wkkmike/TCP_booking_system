package Middleware;

import Server.Common.Parser;
import Server.Utli.Command;
import Server.Utli.Executer;
import
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

public class Listener {
    Socket socket = null;
    BufferedReader input = null;
    PrintWriter output = null;

    public Listener(Socket socket){
        this.socket = socket;
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
        try{
            socket.close();
        }
        catch (IOException e){
            System.out.println("Socket close failed.");
            System.exit(-1);
        }
    }

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
