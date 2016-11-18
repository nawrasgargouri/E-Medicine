/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emedicine;

public class User {
    public int id;
    public String name;
    public String userName;
    public String password;
    public String email;
    public Pharmacy pharmacy;

    public User(String name, String userName, String email, Pharmacy pharmacy) {
        this.id = -1;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.pharmacy = pharmacy;
    }

    public User( String name, String userName, String password, String email, Pharmacy pharmacy) {
        this.id = -1;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.pharmacy = pharmacy;
    }
    

    public User(int id, String name, String userName, String email, Pharmacy pharmacy) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.pharmacy = pharmacy;
    }
}
