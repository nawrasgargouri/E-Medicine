/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emedicine.server;

import emedicine.Pharmacy;
import emedicine.User;
import java.sql.SQLException;

public class provider {
    
    public static boolean login(String username,String password){
        //login code
        return true;
    }
    
    public static boolean pharmacyRegistration(Pharmacy pharmacy){
        String sql="INSERT INTO pharmacy VALUES(null,?,?,?)";
        try {
            DatabaseConnection.startTriger();
            int id =DatabaseConnection.insertKey(sql,pharmacy.name,pharmacy.phone,pharmacy.des);
            if(id != -1){
                sql = "INSERT INTO user VALUES(null,?,?,SHA1(?),?,?)";
                int i= DatabaseConnection.insertKey(sql,pharmacy.admin.name,pharmacy.admin.userName,pharmacy.admin.password,id,pharmacy.admin.email);
                DatabaseConnection.finishTriger();
                return i != -1;
            }
        } catch (SQLException e) {
            
        }
        DatabaseConnection.finishTransaction();
        return false;
    }
    public static boolean userRegistration(User user){
        String sql = "INSERT INTO user VALUES(null,?,?,SHA1(?),null,?)";
        try {
            return DatabaseConnection.insertKey(sql,user.name,user.userName,user.password,user.email) != -1;
            
        } catch (SQLException e) {
            
        }
        return false;
    }
    
}
