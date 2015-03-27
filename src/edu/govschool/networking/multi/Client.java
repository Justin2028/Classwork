package edu.govschool.networking.multi;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;



/**
 *
 * @author bryce
 */
public class Client extends JFrame implements Runnable
{
    // The port to connect to
    private static final int PORT = 5000;
    // The socket representing the connection to the server
    private Socket socket;
    // I/O representations
    private ObjectInputStream input;
    private ObjectOutputStream output;
    // Thread to handle the GUI
    private Thread thread;
    // GUI elements
    private JTextField entryField;
    private JTextArea displayArea;
    // Username
    private String username;
    // Message count
    private int count = 0;
    // Hostname/IP of the server
    private String serverHost;
    
    public Client()
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
        add(entryField, BorderLayout.SOUTH);
        
        // Setup the display area
        displayArea = new JTextArea(100, 40);
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.NORTH);
        
        // Save our server host location
        this.serverHost = getServerHost();
        
        // Attempt to connect to the server
        try {
            socket = new Socket(serverHost, PORT);
        } catch (IOException e) {
            printErr("Error connection to server.");
        }
        
        // Attempt the setup the I/O streams
        try {
            getStreams();
        } catch (IOException e) {
            printErr("Error getting streams.");
        }
        
        // Attempt to send the username over the output stream
        try {
            // Get a username
            this.username = getUsername();
            ChatResponse name = new ChatResponse(ChatResponse.TYPE_USERNAME,
                                                 this.username);
            output.writeObject(name);
            output.flush();
        } catch (IOException e) {
            printErr("Error sending username to server.");
        }
        // Finalize the GUI and pass it to a background thread
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {
                sendData(new ChatResponse(ChatResponse.TYPE_DISCONNECT, ""));
            }
        });
    }
    
    /**
     * Displays a dialog box prompting for the server's hostname/IP
     * @return 
     */
    private String getServerHost()
    {
        return JOptionPane.showInputDialog(this, 
                                           "Enter the server hostname/IP", 
                                           "Server host", 
                                           JOptionPane.QUESTION_MESSAGE);
    }
    
    /**
     * Displays a dialog box prompting for a username.
     * @return the chosen username
     */
    private String getUsername()
    {
        return JOptionPane.showInputDialog(this, 
                                            "Enter a user name", 
                                            "Getting username", 
                                            JOptionPane.QUESTION_MESSAGE);
    }
    
    /**
     * Send a message over the output stream.
     * @param msg the message to send
     */
    private void sendData(String msg)
    {
        try {
            ChatResponse message = new ChatResponse(ChatResponse.TYPE_MESSAGE,
                                                    this.username + ": " + msg);
            output.writeObject(message);
            output.flush();
        } catch (IOException e) {
            printErr("Error sending message to server.");
        }
    }
    
    private void sendData(ChatResponse data)
    {
        try {
            output.writeObject(data);
            output.flush();
        } catch (IOException e) {
            printErr("Error sending message to server.");
        }
    }
    
    /**
     * Print an error message.
     * @param err the error message
     */
    private void printErr(String err)
    {
        System.err.println(err);
    }
    
    /**
     * Setup our I/O streams via the socket connection. ENSURE YOU GET THE
     * OUTPUT STREAM FIRST.
     * @throws IOException the streams could not be created
     */
    private void getStreams() throws IOException
    {
        output = new ObjectOutputStream(socket.getOutputStream());
        output.flush();
        input = new ObjectInputStream(socket.getInputStream());
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
     * Start the client thread.
     */
    public void runClient()
    {
        thread = new Thread(this);
        thread.start();
    }
    
    /**
     * Handle messages via the input stream as we receive them.
     */
    @Override
    public void run()
    {
        while (true) {
            try {
                // Read a message from the input stream.
                ChatResponse msg = (ChatResponse) input.readObject();

                displayMessage(msg.getMessage());
            } catch (IOException | ClassNotFoundException e) {
                printErr("Error reading message from server.");
            }
        }
    }
    
    public static void main(String[] args)
    {
        new Client().runClient();
    }
}