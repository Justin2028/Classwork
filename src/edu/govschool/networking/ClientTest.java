/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.govschool.networking;

import javax.swing.JFrame;

/**
 *
 * @author bryce
 */
public class ClientTest 
{
    public static void main(String[] args)
    {
        Client application;
        
        if (args.length == 0) {
            application = new Client("127.0.0.1");
        } else {
            application = new Client(args[0]);
        }
        
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.runClient();
    }
}