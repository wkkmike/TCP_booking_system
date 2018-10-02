package Client;

import java.io.*;
import java.net.*;
import java.net.UnknownHostException;
import java.util.ArrayList;
import Server.Utli.*;

public class TCPSocketClient
{
    private static String s_serverHost = "localhost";
    private static int s_serverPort = 8888;
    private static String s_serverName = "MiddlewareServer";
    private static String s_rmiPrefix = "group15";
    private static  Socket socket = null;
    private static PrintWriter output;
    private static BufferedReader input;
    private static Client client;

    public static void main(String args[]) {
        // get customerized hostip and host port
        if(args.length > 0){
            s_serverHost = args[0];
        }

        if(args.length > 1){
            s_serverName = args[1];
        }

        if(args.length > 2){
            s_serverPort = Integer.parseInt(args[2]);
        }

        if (args.length > 3) {
            System.err.println((char) 27 + "[31;1mClient exception: " + (char) 27 + "[0mUsage: java client.RMIClient [server_hostname [server_rmiobject]]");
            System.exit(1);
        }

        if(!connect(s_serverHost, s_serverPort)){
            System.out.println("Connect Fail to Host: '" + s_serverHost + "' port: '" + s_serverPort + ".");
            System.exit(1);
        }

        client = new Client();
        try {
            client.start();
        }
        catch (Exception e){
            System.err.println((char)27 + "[31;1mClient exception: " + (char)27 + "[0mUncaught exception");
            e.printStackTrace();
            System.exit(1);
        }
    }


    public static boolean connect(String ip, int port){
        try{
            socket = new Socket(ip, port);
            output = new PrintWriter(socket.getOutputStream(),
                    true);
            input = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.out.println("Unknown host");
            System.exit(1);
            return false;
        } catch  (IOException e) {
            System.out.println("No I/O");
            System.exit(1);
            return false;
        }
        return true;
    }

    public static String execute(String command){
        output.print(command);
        output.flush();
        String returnValue = "";
        try{
            returnValue = input.readLine();
        }
        catch(IOException e){
            System.out.println("input fail");
        }
        return returnValue;
    }

    public static int intExecute(String command){
        return Integer.parseInt(execute(command));
    }

    public static boolean booleanExecute(String command){
        String returnValue = execute(command);
        if(returnValue == "True"){
            return true;
        }
        else return false;
    }

    public static String stringExecute(String command){
        return execute(command);
    }
    public static void connectServer(){
        connect(s_serverHost, s_serverPort);
    }

}
