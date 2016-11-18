/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emedicine.server;

import emedicine.GUI.AlertBox;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

public class DatabaseConnection {

    private static Connection con ;   
    public static final String DATABASE_NAME = "E-Medicine";
    
    public static Connection getConnection(){
        try {
            if(con!=null && !con.isClosed()){
                return con;
            }
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DATABASE_NAME+"?useUnicode=true&characterEncoding=UTF-8","system","system");
        } catch (SQLException | ClassNotFoundException e) {
            AlertBox.display("Error", "Connection Lost");
        }
        return con;
    }
    
    public static boolean isNetworkConnected() {
        return (DatabaseConnection.getConnection() != null);
    }
    
    public static ResultSet query(String query,Object ...data) throws SQLException{
        if(!isNetworkConnected()) return null;
        PreparedStatement statement=con.prepareStatement(query);
        setData(statement,data);
        return statement.executeQuery();
    }
    
    public static int insertKey(String query,Object... data) throws SQLException{
        if(!isNetworkConnected()) return -1;
        PreparedStatement statement=con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        setData(statement,data);
        
        int key=-1;
        if(statement.executeUpdate()>0){
            ResultSet rs = statement.getGeneratedKeys();
            key=(rs.next())?rs.getInt(1):-1;
            rs.close();
        }
        return key;
    }
    
    public static boolean insert(String query,Object... data) throws SQLException{
        if(!isNetworkConnected()) return false;
        PreparedStatement statement=con.prepareStatement(query);
        setData(statement,data);
        boolean result=statement.executeUpdate()>0;
        return result;
    }
    
    public static boolean update(String query,Object...data)throws SQLException{
        if(!isNetworkConnected()) return false;
        PreparedStatement statement=con.prepareStatement(query);
        setData(statement,data);
        boolean result=statement.executeUpdate()>0;
        return result;
    }
    
    public static boolean delete(String query,Object...data)throws SQLException{
        if(!isNetworkConnected()) return false;
        PreparedStatement statement=con.prepareStatement(query);
        setData(statement,data);
        boolean result=statement.executeUpdate()>0;
        return result;
    }
    private static void setData(PreparedStatement statement,Object...data)throws SQLException{
        if(data!=null){
            for(int i=0;i<data.length;i++){
                if(data[i] instanceof String)
                    statement.setString(i+1,(String)data[i]);
                else if(data[i] instanceof Integer)
                    statement.setInt(i+1,(int)data[i]);
                else if(data[i] instanceof Float)
                    statement.setFloat(i+1,(float)data[i]);
                else if(data[i] instanceof Double)
                    statement.setDouble(i+1,(double)data[i]);
                else if(data[i] instanceof Date)
                    statement.setDate(i+1,(Date)data[i]);
                else if(data[i] instanceof Long)
                    statement.setLong(i+1,(long)data[i]);
                else if(data[i] instanceof Short)
                    statement.setShort(i+1,(short)data[i]);
                else if(data[i] instanceof Time)
                    statement.setTime(i+1,(Time)data[i]);
                else
                    statement.setObject(i+1,data[i]);
            }
        }
    }
    public static void startTriger()throws SQLException{
        getConnection().setAutoCommit(false);
    }
    public static void finishTriger()throws SQLException{
        getConnection().commit();
    }
    public static void finishTransaction(){
        try {
            getConnection().rollback();
            getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            AlertBox.display("Error", "connection lost");
        }
    }
}