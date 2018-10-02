package Server.Utli;

import java.rmi.RemoteException;
import java.util.*;
import java.io.*;
import Server.Common.*;

/**
 * Created by lidawei on 07/04/2017.
 */
public class Executer {

    public ResourceManager resourceManager = null;

    public String execute(Command cmd, Vector<String> arguments) throws RemoteException
    {

        switch (cmd)
        {
            case Help:
            {
                if (arguments.size() == 1) {
                    System.out.println(Command.description());
                } else if (arguments.size() == 2) {
                    Command l_cmd = Command.fromString((String)arguments.elementAt(1));
                    System.out.println(l_cmd.toString());
                } else {
                    System.err.println((char)27 + "[31;1mCommand exception: " + (char)27 + "[0mImproper use of help command. Location \"help\" or \"help,<CommandName>\"");
                }
                break;
            }
            case AddFlight: {
                checkArgumentsCount(5, arguments.size());

                System.out.println("Adding a new flight [xid=" + arguments.elementAt(1) + "]");
                System.out.println("-Flight Number: " + arguments.elementAt(2));
                System.out.println("-Flight Seats: " + arguments.elementAt(3));
                System.out.println("-Flight Price: " + arguments.elementAt(4));

                int id = toInt(arguments.elementAt(1));
                int flightNum = toInt(arguments.elementAt(2));
                int flightSeats = toInt(arguments.elementAt(3));
                int flightPrice = toInt(arguments.elementAt(4));

                return String.valueOf(resourceManager.addFlight(id, flightNum, flightSeats, flightPrice));
            }
            case AddCars: {
                checkArgumentsCount(5, arguments.size());

                System.out.println("Adding new cars [xid=" + arguments.elementAt(1) + "]");
                System.out.println("-Car Location: " + arguments.elementAt(2));
                System.out.println("-Number of Cars: " + arguments.elementAt(3));
                System.out.println("-Car Price: " + arguments.elementAt(4));

                int id = toInt(arguments.elementAt(1));
                String location = arguments.elementAt(2);
                int numCars = toInt(arguments.elementAt(3));
                int price = toInt(arguments.elementAt(4));

                return String.valueOf(resourceManager.addCars(id, location, numCars, price));
            }
            case AddRooms: {
                checkArgumentsCount(5, arguments.size());

                System.out.println("Adding new rooms [xid=" + arguments.elementAt(1) + "]");
                System.out.println("-Room Location: " + arguments.elementAt(2));
                System.out.println("-Number of Rooms: " + arguments.elementAt(3));
                System.out.println("-Room Price: " + arguments.elementAt(4));

                int id = toInt(arguments.elementAt(1));
                String location = arguments.elementAt(2);
                int numRooms = toInt(arguments.elementAt(3));
                int price = toInt(arguments.elementAt(4));

                return String.valueOf((resourceManager.addRooms(id, location, numRooms, price)));
            }
//            case AddCustomer: {
//                checkArgumentsCount(2, arguments.size());
//
//                System.out.println("Adding a new customer [xid=" + arguments.elementAt(1) + "]");
//
//                int id = toInt(arguments.elementAt(1));
//                int customer = resourceManager.newCustomer(id);
//
//                System.out.println("Add customer ID: " + customer);
//                break;
//            }
            case AddCustomerID: {
                checkArgumentsCount(3, arguments.size());

                System.out.println("Adding a new customer [xid=" + arguments.elementAt(1) + "]");
                System.out.println("-Customer ID: " + arguments.elementAt(2));

                int id = toInt(arguments.elementAt(1));
                int customerID = toInt(arguments.elementAt(2));

                return String.valueOf(resourceManager.newCustomer(id, customerID));
            }
            case DeleteFlight: {
                checkArgumentsCount(3, arguments.size());

                System.out.println("Deleting a flight [xid=" + arguments.elementAt(1) + "]");
                System.out.println("-Flight Number: " + arguments.elementAt(2));

                int id = toInt(arguments.elementAt(1));
                int flightNum = toInt(arguments.elementAt(2));

                return String.valueOf(resourceManager.deleteFlight(id, flightNum));
            }
            case DeleteCars: {
                checkArgumentsCount(3, arguments.size());

                System.out.println("Deleting all cars at a particular location [xid=" + arguments.elementAt(1) + "]");
                System.out.println("-Car Location: " + arguments.elementAt(2));

                int id = toInt(arguments.elementAt(1));
                String location = arguments.elementAt(2);

                return String.valueOf(resourceManager.deleteCars(id, location));
            }
            case DeleteRooms: {
                checkArgumentsCount(3, arguments.size());

                System.out.println("Deleting all rooms at a particular location [xid=" + arguments.elementAt(1) + "]");
                System.out.println("-Car Location: " + arguments.elementAt(2));

                int id = toInt(arguments.elementAt(1));
                String location = arguments.elementAt(2);

                return String.valueOf(resourceManager.deleteRooms(id, location));
            }
            case DeleteCustomer: {
                checkArgumentsCount(3, arguments.size());

                System.out.println("Deleting a customer from the database [xid=" + arguments.elementAt(1) + "]");
                System.out.println("-Customer ID: " + arguments.elementAt(2));

                int id = toInt(arguments.elementAt(1));
                int customerID = toInt(arguments.elementAt(2));

                return String.valueOf(resourceManager.deleteCustomer(id, customerID));
            }
            case QueryFlight: {
                checkArgumentsCount(3, arguments.size());

                System.out.println("Querying a flight [xid=" + arguments.elementAt(1) + "]");
                System.out.println("-Flight Number: " + arguments.elementAt(2));

                int id = toInt(arguments.elementAt(1));
                int flightNum = toInt(arguments.elementAt(2));

                return String.valueOf(resourceManager.queryFlight(id, flightNum));
            }
            case QueryCars: {
                checkArgumentsCount(3, arguments.size());

                System.out.println("Querying cars location [xid=" + arguments.elementAt(1) + "]");
                System.out.println("-Car Location: " + arguments.elementAt(2));

                int id = toInt(arguments.elementAt(1));
                String location = arguments.elementAt(2);

                return String.valueOf(resourceManager.queryCars(id, location));
            }
            case QueryRooms: {
                checkArgumentsCount(3, arguments.size());

                System.out.println("Querying rooms location [xid=" + arguments.elementAt(1) + "]");
                System.out.println("-Room Location: " + arguments.elementAt(2));

                int id = toInt(arguments.elementAt(1));
                String location = arguments.elementAt(2);

                return String.valueOf(resourceManager.queryRooms(id, location));
            }
            case QueryCustomer: {
                checkArgumentsCount(3, arguments.size());

                System.out.println("Querying customer information [xid=" + arguments.elementAt(1) + "]");
                System.out.println("-Customer ID: " + arguments.elementAt(2));

                int id = toInt(arguments.elementAt(1));
                int customerID = toInt(arguments.elementAt(2));

                return String.valueOf(resourceManager.queryCustomerInfo(id, customerID));
            }
            case QueryFlightPrice: {
                checkArgumentsCount(3, arguments.size());

                System.out.println("Querying a flight price [xid=" + arguments.elementAt(1) + "]");
                System.out.println("-Flight Number: " + arguments.elementAt(2));

                int id = toInt(arguments.elementAt(1));
                int flightNum = toInt(arguments.elementAt(2));

                return String.valueOf(resourceManager.queryFlightPrice(id, flightNum));
            }
            case QueryCarsPrice: {
                checkArgumentsCount(3, arguments.size());

                System.out.println("Querying cars price [xid=" + arguments.elementAt(1) + "]");
                System.out.println("-Car Location: " + arguments.elementAt(2));

                int id = toInt(arguments.elementAt(1));
                String location = arguments.elementAt(2);

                return String.valueOf(resourceManager.queryCarsPrice(id, location));
            }
            case QueryRoomsPrice: {
                checkArgumentsCount(3, arguments.size());

                System.out.println("Querying rooms price [xid=" + arguments.elementAt(1) + "]");
                System.out.println("-Room Location: " + arguments.elementAt(2));

                int id = toInt(arguments.elementAt(1));
                String location = arguments.elementAt(2);

                return String.valueOf(resourceManager.queryRoomsPrice(id, location));
            }
            case ReserveFlight: {
                checkArgumentsCount(4, arguments.size());

                System.out.println("Reserving seat in a flight [xid=" + arguments.elementAt(1) + "]");
                System.out.println("-Customer ID: " + arguments.elementAt(2));
                System.out.println("-Flight Number: " + arguments.elementAt(3));

                int id = toInt(arguments.elementAt(1));
                int customerID = toInt(arguments.elementAt(2));
                int flightNum = toInt(arguments.elementAt(3));

                return String.valueOf(resourceManager.reserveFlight(id, customerID, flightNum));
            }
            case ReserveCar: {
                checkArgumentsCount(4, arguments.size());

                System.out.println("Reserving a car at a location [xid=" + arguments.elementAt(1) + "]");
                System.out.println("-Customer ID: " + arguments.elementAt(2));
                System.out.println("-Car Location: " + arguments.elementAt(3));

                int id = toInt(arguments.elementAt(1));
                int customerID = toInt(arguments.elementAt(2));
                String location = arguments.elementAt(3);

                return String.valueOf(resourceManager.reserveCar(id, customerID, location));
            }
            case ReserveRoom: {
                checkArgumentsCount(4, arguments.size());

                System.out.println("Reserving a room at a location [xid=" + arguments.elementAt(1) + "]");
                System.out.println("-Customer ID: " + arguments.elementAt(2));
                System.out.println("-Room Location: " + arguments.elementAt(3));

                int id = toInt(arguments.elementAt(1));
                int customerID = toInt(arguments.elementAt(2));
                String location = arguments.elementAt(3);

                return String.valueOf((resourceManager.reserveRoom(id, customerID, location)));
            }
            case Bundle: {
                if (arguments.size() < 7) {
                    System.err.println((char)27 + "[31;1mCommand exception: " + (char)27 + "[0mBundle command expects at least 7 arguments. Location \"help\" or \"help,<CommandName>\"");
                    break;
                }

                System.out.println("Reserving an bundle [xid=" + arguments.elementAt(1) + "]");
                System.out.println("-Customer ID: " + arguments.elementAt(2));
                for (int i = 0; i < arguments.size() - 6; ++i)
                {
                    System.out.println("-Flight Number: " + arguments.elementAt(3+i));
                }
                System.out.println("-Location for Car/Room: " + arguments.elementAt(arguments.size()-3));
                System.out.println("-Book Car: " + arguments.elementAt(arguments.size()-2));
                System.out.println("-Book Room: " + arguments.elementAt(arguments.size()-1));

                int id = toInt(arguments.elementAt(1));
                int customerID = toInt(arguments.elementAt(2));
                Vector<String> flightNumbers = new Vector<String>();
                for (int i = 0; i < arguments.size() - 6; ++i)
                {
                    flightNumbers.addElement(arguments.elementAt(3+i));
                }
                String location = arguments.elementAt(arguments.size()-3);
                boolean car = toBoolean(arguments.elementAt(arguments.size()-2));
                boolean room = toBoolean(arguments.elementAt(arguments.size()-1));

                return String.valueOf(resourceManager.bundle(id, customerID, flightNumbers, location, car, room));
            }
        }
        return "unknow cmd";
    }

    public static void checkArgumentsCount(Integer expected, Integer actual) throws IllegalArgumentException
    {
        if (expected != actual)
        {
            throw new IllegalArgumentException("Invalid number of arguments. Expected " + (expected - 1) + ", received " + (actual - 1) + ". Location \"help,<CommandName>\" to check usage of this command");
        }
    }

    public static int toInt(String string) throws NumberFormatException
    {
        return (new Integer(string)).intValue();
    }

    public static boolean toBoolean(String string)// throws Exception
    {
        return (new Boolean(string)).booleanValue();
    }
}

