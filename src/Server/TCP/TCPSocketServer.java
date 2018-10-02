package Server.TCP;

import java.net.*;
import java.io.*;
import Server.Common.*;

public class TCPSocketServer {
    private static String s_serverName = "Server";
    private static String s_TCPPrefix = "group15";
    private static TCPController controller;
    private static int port = 8888;

    public static void main(String[] args) throws IOException {

        if(args.length > 0)
        {
            s_serverName = args[0];
        }

        controller = new TCPController(port);

    }
}
