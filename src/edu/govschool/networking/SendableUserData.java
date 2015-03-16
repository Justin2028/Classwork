/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.govschool.networking;

/**
 *
 * @author Glaedwyn
 */
public class SendableUserData {
    String user;
    String pass;
    public SendableUserData()
    {
        user = null;
        pass = null;
    }
    
    public String toString()
    {
        return "Username : " + user + "\npassword : " + pass;
    }
    
}
