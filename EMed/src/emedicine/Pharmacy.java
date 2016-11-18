/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emedicine;

public class Pharmacy {
    public int id;
    public String name;
    public String des;
    public String phone;
    public User admin;

    public Pharmacy(String name, String des, String phone, User admin) {
        this.id = -1;
        this.name = name;
        this.des = des;
        this.phone = phone;
        this.admin = admin;
    }

    public Pharmacy(int id, String name, String des, String phone, User admin) {
        this.id = id;
        this.name = name;
        this.des = des;
        this.phone = phone;
        this.admin = admin;
    }
}
