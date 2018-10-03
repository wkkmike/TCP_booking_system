package Middleware;
import java.net.*;
import java.io.*;


public class TCPMiddleware {
    private static String s_serverName = "MiddlewareServer";
    private static String s_rmiPrefix = "group15";
    private static int port = 56666;

    private static String s_serverHost_Flight = "localhost";
    private static int s_serverPort_Flight = 56665;
    private static String s_serverName_Flight = "Flights";

    private static String s_serverHost_Car = "localhost";
    private static int s_serverPort_Car = 56665;
    private static String s_serverName_Car = "Cars";

    private static String s_serverHost_Room = "localhost";
    private static int s_serverPort_Room = 56665;
    private static String s_serverName_Room = "Rooms";

    private static String s_serverHost_Customer = "localhost";
    private static int s_serverPort_Customer = 56665;
    private static String s_serverName_Customer = "Customers";

    private static Server.TCP.TCPController controller;

    public static void main(String[] args) throws IOException {

        if(args.length > 0)
        {
            port = Integer.parseInt(args[0]);
        }

        if(args.length > 1)
        {
            s_serverHost_Flight = args[1];
        }

        if(args.length > 2)
        {
            s_serverPort_Flight = Integer.parseInt(args[2]);
        }

        if(args.length > 3)
        {
            s_serverHost_Car = args[3];
        }

        if(args.length > 4)
        {
            s_serverPort_Car = Integer.parseInt(args[4]);
        }

        if(args.length > 5)
        {
            s_serverHost_Room = args[5];
        }

        if(args.length > 6)
        {
            s_serverPort_Room = Integer.parseInt(args[6]);
        }

        if(args.length > 7)
        {
            s_serverHost_Customer = args[7];
        }

        if(args.length > 8)
        {
            s_serverPort_Customer = Integer.parseInt(args[8]);
        }

        controller = new Server.TCP.TCPController(port);

        while (true){
            Socket socket = controller.acceptNewSocket();
            if(socket == null){
                System.out.println("Middleware can't get new socket.");
                System.exit(-1);
            }
            Runnable r = new Listener(socket);
            Thread t = new Thread(r);
            t.start();
        }
    }

    public static Socket connect(String ip, int port){
        Socket socket = null;
        try{
            socket = new Socket(ip, port);
            PrintWriter output = new PrintWriter(socket.getOutputStream(),
                    true);
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.out.println("Unknown host");
            System.exit(1);
        } catch  (IOException e) {
            System.out.println("No I/O");
            System.exit(1);
        }
        return socket;
    }

    public static String execute(String command, Socket socket){
        PrintWriter output = null;
        BufferedReader input = null;
        try{
            output = new PrintWriter(socket.getOutputStream(),
                    true);
            input = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
        }
        catch (IOException e){
            System.out.println("IO exception during execute time.");
            System.exit(-1);
        }
        output.println(command);
        output.flush();
        String returnValue = "";
        try{
            returnValue = input.readLine();
        }
        catch(IOException e){
            System.out.println("input fail");
        }
        try{
            socket.close();
        }
        catch (IOException e){
            System.out.println("Socket close failed.");
            System.exit(-1);
        }
        return returnValue;
    }

    public static String flightExecute(String command){
        Socket socket = connect(s_serverHost_Flight,s_serverPort_Flight);
        return execute(command, socket);
    }

    public static String carExecute(String command){
        Socket socket = connect(s_serverHost_Car, s_serverPort_Car);
        return execute(command, socket);
    }

    public static String roomExecute(String command){
        Socket socket = connect(s_serverHost_Room, s_serverPort_Room);
        return execute(command, socket);
    }

    public static String customerExecute(String command){
        Socket socket = connect(s_serverHost_Customer, s_serverPort_Customer);
        return execute(command, socket);
    }

}
