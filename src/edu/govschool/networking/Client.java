/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.govschool.networking;

import edu.govschool.riskboard.modals.MessageBox;
import java.awt.BorderLayout;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.awt.Cursor;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;

/**
 *
 * @author bryce
 */
public class Client extends JFrame {

    private JTextField enterField;
    JTextField passField;
    JButton send;
    private JTextArea displayArea;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String msg = "";
    private String chatServer;
    private Socket client;
    SendableUserData data;

    public Client(String host) {
        super("Client");
        send = new JButton("Login");
        data = new SendableUserData();
        enterField = new JTextField();
        passField = new JTextField();
        enterField.setEditable(false);
        passField.setEditable(false);
        enterField.addActionListener((e) -> {
            data.user = enterField.getText();
            passField.setCursor(Cursor.getDefaultCursor());
        });
        passField.addActionListener((e) -> {
            data.pass = passField.getText();
        });
        send.addActionListener((e) -> {
            sendData(data);
        });
        add(enterField, BorderLayout.NORTH);
        add(passField, BorderLayout.CENTER);
        add(send, BorderLayout.SOUTH);
        setSize(300, 150);
        setVisible(true);

        chatServer = host;
    }

    public void runClient() {
        try {
            connectToServer();
            getStreams();
            processConnection();
        } catch (EOFException eof) {
            displayMessage("\nClient terminated connection");
        } catch (IOException io) {
        } finally {
            closeConnection();
        }
    }

    private void connectToServer() throws IOException {
        displayMessage("Attempting connection\n");

        client = new Socket(InetAddress.getByName(chatServer), 5000);

        displayMessage("Connected to: "
                + client.getInetAddress().getHostName());
    }

    private void getStreams() throws IOException {
        output = new ObjectOutputStream(client.getOutputStream());
        output.flush();

        input = new ObjectInputStream(client.getInputStream());

        displayMessage("\nGot I/O streams\n");
    }

    private void processConnection() throws IOException {
        setTextFieldEditable(true);

        do {
            try {
                msg = (String) input.readObject();
                displayMessage("\n" + msg);
            } catch (ClassNotFoundException e) {
                displayMessage("\nUnknown object type received");
            }
        } while (!msg.equals("SERVER>>> TERMINATE"));
    }

    private void closeConnection() {
        displayMessage("\nClosing connection");
        setTextFieldEditable(false);

        try {
            output.close();
            input.close();
            client.close();
        } catch (IOException e) {
        }
    }

    private void sendData(Object mess) {
        try {
            output.writeObject("CLIENT>>> " + mess);
            output.flush();
            displayMessage("\nCLIENT>>> " + mess);
        } catch (IOException e) {
            displayArea.append("\nError writing object");
        }
    }

    private void displayMessage(final String mess) {
//        SwingUtilities.invokeLater(() -> displayArea.append(mess));
    }

    private void setTextFieldEditable(final boolean edit) {
        SwingUtilities.invokeLater(() -> enterField.setEditable(edit));
        SwingUtilities.invokeLater(() -> passField.setEditable(edit));
    }
}
