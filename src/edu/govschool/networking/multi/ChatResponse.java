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
    public static final int TYPE_BAD_USERNAME  = 1;
    public static final int TYPE_GOOD_USERNAME = 2;
    public static final int TYPE_MESSAGE       = 3;
    public static final int TYPE_USERNAME      = 4;
    public static final int TYPE_DISCONNECT    = 5;
    
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