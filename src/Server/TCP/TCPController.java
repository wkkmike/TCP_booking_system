package Server.TCP;

import java.net.*;
import java.util.*;
import java.io.*;
import Server.Utli.*;

public class TCPController{
    private static int port = 56665;
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private PrintWriter output;
    private BufferedReader input;
    private String command;

    public TCPController(){
        new TCPController(port);
    }

    public TCPController(int port){
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Could not listen on port " + port);
            System.exit(-1);
        }
    }

    public Socket acceptNewSocket(){
        Socket socket = null;
        try{
            socket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 4321");
            System.exit(-1);
        }
        return socket;
    }
}
