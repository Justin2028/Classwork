package edu.govschool.networking.multi;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Class to represent a chat client. The client has a GUI window to chat with.
 * @author Mr. Davis
 */
public class Client extends JFrame implements Runnable
{
    // The port to connect to
    private static final int PORT = 5000;
    // The socket representing the connection to the server
    private Socket socket;
    // I/O representations
    private BufferedReader input;
    private PrintWriter output;
    // Thread to handle the GUI
    private final Thread thread;
    // GUI elements
    private JTextField entryField;
    private JTextArea displayArea;
    // Unique ID
    private String ID;
    // Message count
    private int count = 0;
    // Hostname/IP of the server
    private String serverHost;
    
    /**
     * Constructor for our client. This will take care of setting up the GUI and
     * passing it to the thread property we have to deal with it.
     * @param host the chat server host
     */
    public Client(String host) 
    {
        // Create a simple JFrame with the title "Client"
        super("Client");
        
        // Setup the entry field
        entryField = new JTextField();
        entryField.addActionListener((e) -> {
            if (entryField.getText().equals("")) return;
            sendData(entryField.getText());
            entryField.setText("");
        });
        add(entryField, BorderLayout.NORTH);
        
        // Setup the display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea));
        
        // Save our server host location
        serverHost = host;
        
        ID = getUserName();
        
        // Attempt to connect to the server and setup the I/O streams
        try {
            socket = new Socket(serverHost, PORT);
            getStreams();
        } catch (IOException e) {}
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Finalize the GUI and pass it to a background thread
        setSize(500, 500);
        setVisible(true);
        thread = new Thread(this);
        thread.start();
    }
    
    /**
     * Setup our I/O streams via the socket connection.
     * @throws IOException the streams could not be created
     */
    private void getStreams() throws IOException
    {
        input = new BufferedReader(
                                new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
    }
    
    private String getUserName()
    {
        return JOptionPane.showInputDialog(this, 
                                            "Enter a user name", 
                                            "Getting username", 
                                            JOptionPane.QUESTION_MESSAGE);
    }
    
    /**
     * Handle messages via the input stream as we receive them. The first
     * message is assumed to the be the assigned user ID.
     */
    @Override
    public void run()
    {
        while (true) {
            try {
                // Read a line from the input stream and increment our line
                // counter.
                String line = input.readLine();
                count++;
                
                // Setup our ID on the first message, otherwise display the 
                // message
                if (count == 1) {
                    sendData(this.ID);
                    displayMessage("Your ID is " + this.ID);
                } else {
                    displayMessage(line);
                }
            } catch (IOException e) {}
        }
    }
    
    /**
     * Send a message over the output stream.
     * @param msg the message to send
     */
    private void sendData(String msg)
    {
        if (msg != null || !msg.equals(""))
            output.println(this.ID + ": " + msg);
    }
    
    /**
     * Add a message to our display area.
     * @param msg the message to display
     */
    private void displayMessage(String msg)
    {
        SwingUtilities.invokeLater(() -> displayArea.append(msg + "\n"));
    }
    
    /**
     * Start a new client.
     * @param args command line arguments
     */
    public static void main(String[] args)
    {
        Client app = new Client("localhost");
    }
}