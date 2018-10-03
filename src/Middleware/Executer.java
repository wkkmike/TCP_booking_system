package Middleware;

import Server.Common.ResourceManager;
import Server.Common.Trace;

import java.rmi.RemoteException;
import java.util.Vector;

/**
 * Created by lidawei on 07/04/2017.
 */
public class Executer {
    ResourceManager m_resourceManager_f = null;
    ResourceManager m_resourceManager_c = null;
    ResourceManager m_resourceManager_r = null;
    ResourceManager m_resourceManager_cus = null;

    // Create a new flight, or add seats to existing flight
    // NOTE: if flightPrice <= 0 and the flight already exists, it maintains its current price
    public String addFlight(int xid, int flightNum, int flightSeats, int flightPrice) throws RemoteException
    {
        Trace.info("RM::addFlight(" + xid + ", " + flightNum + ", " + flightSeats + ", $" + flightPrice + ") called");
//		connect();
        return m_resourceManager_f.addFlight(xid, flightNum, flightSeats, flightPrice);
    }

    // Create a new car location or add cars to an existing location
    // NOTE: if price <= 0 and the location already exists, it maintains its current price
    public String addCars(int xid, String location, int count, int price) throws RemoteException
    {
        Trace.info("RM::addCars(" + xid + ", " + location + ", " + count + ", $" + price + ") called");
        return  m_resourceManager_c.addCars(xid, location, count, price);
    }

    // Create a new room location or add rooms to an existing location
    // NOTE: if price <= 0 and the room location already exists, it maintains its current price
    public String addRooms(int xid, String location, int count, int price) throws RemoteException
    {
        Trace.info("RM::addRooms(" + xid + ", " + location + ", " + count + ", $" + price + ") called");
        return m_resourceManager_r.addRooms(xid, location, count, price);
    }

    // Deletes flight
    public String deleteFlight(int xid, int flightNum) throws RemoteException
    {
        return m_resourceManager_f.deleteFlight(xid, flightNum);
    }

    // Delete cars at a location
    public String deleteCars(int xid, String location) throws RemoteException
    {
        return m_resourceManager_c.deleteCars(xid, location);
    }

    // Delete rooms at a location
    public String deleteRooms(int xid, String location) throws RemoteException
    {
        return m_resourceManager_r.deleteRooms(xid, location);
    }

    // Returns the number of empty seats in this flight
    public String queryFlight(int xid, int flightNum) throws RemoteException
    {
        return m_resourceManager_f.queryFlight(xid, flightNum);
    }

    // Returns the number of cars available at a location
    public String queryCars(int xid, String location) throws RemoteException
    {
//		connect();
        return m_resourceManager_c.queryCars(xid, location);
    }

    // Returns the amount of rooms available at a location
    public String queryRooms(int xid, String location) throws RemoteException
    {
        return m_resourceManager_r.queryRooms(xid, location);
    }

    // Returns price of a seat in this flight
    public String queryFlightPrice(int xid, int flightNum) throws RemoteException
    {
        return m_resourceManager_f.queryFlightPrice(xid, flightNum);
    }

    // Returns price of cars at this location
    public String queryCarsPrice(int xid, String location) throws RemoteException
    {
        return m_resourceManager_c.queryCarsPrice(xid, location);
    }

    // Returns room price at this location
    public String queryRoomsPrice(int xid, String location) throws RemoteException
    {
        return m_resourceManager_r.queryRoomsPrice(xid, location);
    }

    public String queryCustomerInfo(int xid, int customerID) throws RemoteException
    {
        return m_resourceManager_cus.queryCustomerInfo(xid, customerID);
    }

    public String newCustomer(int xid) throws RemoteException
    {
        return m_resourceManager_cus.newCustomer(xid);
    }

    public String newCustomer(int xid, int customerID) throws RemoteException
    {
        return m_resourceManager_cus.newCustomer(xid,customerID);
    }

    public String deleteCustomer(int xid, int customerID) throws RemoteException
    {
        Trace.info("RM::deleteCustomer(" + xid + ", " + customerID + ") called");
        return m_resourceManager_cus.deleteCustomer(xid,customerID);
    }

    // Adds flight reservation to this customer
    public String reserveFlight(int xid, int customerID, int flightNum) throws RemoteException
    {
        return m_resourceManager_f.reserveFlight(xid, customerID, flightNum);
    }

    // Adds car reservation to this customer
    public String reserveCar(int xid, int customerID, String location) throws RemoteException
    {
        return m_resourceManager_c.reserveCar(xid, customerID, location);
    }

    // Adds room reservation to this customer
    public String reserveRoom(int xid, int customerID, String location) throws RemoteException
    {
        return m_resourceManager_r.reserveRoom(xid, customerID, location);
    }

    // Reserve bundle
    public String bundle(int xid, int customerId, Vector<String> flightNumbers, String location, boolean car, boolean room) throws RemoteException
    {
        for(String flightNumber: flightNumbers){
            reserveFlight(xid, customerId, Integer.parseInt(flightNumber));
        }
        if(car){
            reserveCar(xid, customerId, location);
        }
        if(room){
            reserveRoom(xid, customerId, location);
        }
        return "false";
    }
//
//    public String getName() throws RemoteException
//    {
//        return m_name;
//    }
}

