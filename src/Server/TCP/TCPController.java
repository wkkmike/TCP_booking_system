package Server.TCP;

import java.net.*;
import java.util.*;
import java.io.*;
import Server.Utli.*;

public class TCPController {
    private static int port = 56665;
    private static ServerSocket serverSocket = null;
    private static Socket socket = null;
    private PrintWriter output;
    private BufferedReader input;
    private String command;

    public void listenCommand(){
        while (true) {
            try {
                command = input.readLine();
                System.out.println(command);
            } catch (IOException e) {
                System.out.println("Read failed");
                System.exit(-1);
            }
            CmdParser parser = new CmdParser(command);
            String cmd = parser.getCmd();
            ArrayList<String> arg = parser.getArg();
        }
    }

    public TCPController(){
        new TCPController(8888);
    }

    public TCPController(int port){
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Could not listen on port " + port);
            System.exit(-1);
        }

        try{
            socket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 4321");
            System.exit(-1);
        }

        try{
            input = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(),
                    true);
        } catch (IOException e) {
            System.out.println("Read failed");
            System.exit(-1);
        }
    }
}
