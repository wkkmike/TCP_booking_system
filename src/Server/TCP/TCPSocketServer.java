package Server.TCP;

import java.net.*;
import java.io.*;
import Server.Common.*;

public class TCPSocketServer {
    private static String s_serverName = "Server";
    private static String s_TCPPrefix = "group15";
    private static TCPController controller;
    private static int port = 56665;
    private static ResourceManager resourceManager;

    public static void main(String[] args) throws IOException {

        if(args.length > 0)
        {
            s_serverName = args[0];
        }

        resourceManager = new ResourceManager(s_serverName);
        controller = new TCPController(port);

        while (true){
            Socket socket = controller.acceptNewSocket();
            if(socket == null){
                System.out.println("Server can't get new socket.");
                System.exit(-1);
            }
            Runnable r = new Listener(socket, resourceManager);
            Thread t = new Thread(r);
            t.start();
        }
    }
}
