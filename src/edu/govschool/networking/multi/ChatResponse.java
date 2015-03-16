/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.govschool.networking.multi;
import java.io.Serializable;

/**
 * Class representing a message over our chat network.
 * @author Mr. Davis
 */
public class ChatResponse implements Serializable
{
    public static final int TYPE_BAD_USERNAME  = 0x01;
    public static final int TYPE_GOOD_USERNAME = 0x02;
    public static final int TYPE_MESSAGE       = 0x03;
    public static final int TYPE_USERNAME      = 0x04;
    
    private final int type;
    private final String message;
    
    public ChatResponse(int type, String message)
    {
        this.type = type;
        this.message = message;
    }
    
    public int getResponseType()
    {
        return this.type;
    }
    
    public String getMessage()
    {
        return this.message;
    }
    
    public static ChatResponse badUsernameResponse()
    {
        return new ChatResponse(TYPE_BAD_USERNAME, "");
    }
    
    public static ChatResponse goodUsernameResponse()
    {
        return new ChatResponse(TYPE_GOOD_USERNAME, "");
    }
}