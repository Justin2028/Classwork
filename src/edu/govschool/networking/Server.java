package edu.govschool.networking;

import java.awt.BorderLayout;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;



/**
 *
 * @author bryce
 */
public class Server extends JFrame
{
    private JTextField enterField;
    private final JTextArea displayArea;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;
    private int counter = 1;
    
    public Server()
    {
        super("Server");
        
        enterField = new JTextField();
        enterField.setEditable(false);
        enterField.addActionListener((e) -> {
            sendData(e.getActionCommand());
            enterField.setText("");
        });
        add(enterField, BorderLayout.NORTH);
        
        displayArea = new JTextArea();
        add(new JScrollPane(displayArea));
        
        setSize(300, 150);
        setVisible(true);
    }
    
    public void runServer()
    {
        try {
            server = new ServerSocket(5000, 100);
            
            while (true) {
                try {
                    waitForConnection();
                    getStreams();
                    processConnection();
                } catch (EOFException e) {
                    displayMessage("\nServer terminated connection.");
                } finally {
                    closeConnection();
                    counter++;
                }
            }
        } catch (IOException e) {}
    }
    
    private void waitForConnection() throws IOException
    {
        displayMessage("Waiting for connection\n");
        connection = server.accept();
        displayMessage("Connection " + counter + " received from: " +
                connection.getInetAddress().getHostName());
    }
    
    private void getStreams() throws IOException
    {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        
        input = new ObjectInputStream(connection.getInputStream());
        
        displayMessage("\nGot I/O streams\n");
    }
    
    private void processConnection() throws IOException
    {
        String msg = "Connection successful";
        sendData(msg);
        
        setTextFieldEditable(true);
        
        do {
            try {
                msg = (String) input.readObject();
                displayMessage("\n" + msg);
            } catch (ClassNotFoundException e) {
                displayMessage("\nUnknown object type received");
            }
        } while (!msg.equals("CLIENT>>> TERMINATE"));
    }
    
    private void closeConnection()
    {
        displayMessage("\nTerminating connection\n");
        setTextFieldEditable(false);
        
        try {
            output.close();
            input.close();
            connection.close();
        } catch (IOException e) {}
    }
    
    private void sendData(String msg)
    {
        try {
            output.writeObject("SERVER>>> " + msg);
            output.flush();
            displayMessage("\nSERVER>>> " + msg);
        } catch (IOException e) {
            displayArea.append("\nError writing object");
        }
    }
    
    private void displayMessage(final String msg)
    {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                displayArea.append(msg);
//            }
//        });
        SwingUtilities.invokeLater(() -> displayArea.append(msg));
    }
    
    private void setTextFieldEditable(final boolean editable)
    {
        SwingUtilities.invokeLater(() -> enterField.setEditable(editable));
    }
}