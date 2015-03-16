package edu.govschool.networking.multi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Class to represent a chat server. The server runs as a command line
 * application, with no GUI.
 *
 * @author Mr. Davis
 */
public class Server implements Runnable {

    // The port to connect to
    private static final int PORT = 5000;
    // Thread to accept client connections in the background
    private final Thread serverThread;
    // Set of output streams to clients
    private volatile Set<PrintWriter> outputs;
    // Server socket for clients to connect to
    private ServerSocket server;
    // Socket to represent a client connection
    private Socket socket;
    // Sequential client IDs
    private int ID = 0;

    private ArrayList<String> Clients;

    /**
     * Setup the server and begin accepting client connections.
     */
    public Server() {
        try {
            System.out.println("Binding to port " + PORT + ", please wait...");
            server = new ServerSocket(PORT);
            System.out.println("Server started: " + server);
            System.out.println("Waiting for a client...");
        } catch (IOException e) {
        }

        outputs = new HashSet<>();
        Clients = new ArrayList();
        serverThread = new Thread(this);
        serverThread.start();
    }

    /**
     * Accept client connections as they appear. Once a client connects, its
     * connection (via a socket) is passed to a ClientThread for processing
     * while connected.
     */
    @Override
    public void run() {
        while (true) {
            try {
                // Accept a new client and increment the IDs
                socket = server.accept();
                ID++;
                // Notify that a client connected
                System.out.println("Client: " + ID + " accepted " + socket);
                // Start a new ClientThread to handle the conversation
                new Thread(new ClientThread(socket, ID)).start();
            } catch (IOException e) {
            }
        }
    }

    /**
     * Broadcasts a message to all clients.
     *
     * @param msg the message to broadcast
     * @throws IOException the message failed to broadcast
     */
    private void sendToClients(String msg) throws IOException {
        if (msg == null || msg.equals("")) {
            return;
        }
        synchronized (outputs) {
//            for (PrintWriter writer : outputs) {
//                writer.println(msg);
//            }
            PrintWriter[] outputArr = outputs.toArray(new PrintWriter[0]);
            for (int i = outputArr.length - 1; i >= 0; i--) {
                outputArr[i].println(msg);
            }
        }
    }

    /**
     * Starts a new Server.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Server server = new Server();
    }

    /**
     * ClientThread will handle the connected clients' I/O.
     */
    private class ClientThread implements Runnable {

        // The connection to the server
        private Socket socket;
        // I/O streams
        private BufferedReader input;
        private PrintWriter output;
        // The ID of this client
        private int ID;
        // The current message
        private String line;

        /**
         * Creates a new ClientThread to be used by the server.
         *
         * @param socket the connection to the server
         * @param ID the ID of the client
         */
        public ClientThread(Socket socket, int ID) {
            this.socket = socket;
            this.ID = ID;

            // Setup the I/O streams
            try {
                getStreams();
            } catch (IOException e) {
            }
            try {
                String id = input.readLine();
                boolean dupe = false;
                for (int i = 0; i < Clients.size(); i++) {
                    if (Clients.get(i).equals(id)) {
                        dupe = true;
                        output.println("Error, Duplicate ID");
                    }
                }
                if(!dupe)
                {
                    outputs.add(output);
                    output.println("ID : " + ID);
                }
            } catch (IOException e) {
            }

        }

        /**
         * Setup our I/O streams via the socket connection.
         *
         * @throws IOException the streams could not be created
         */
        private void getStreams() throws IOException {
            input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        }

        /**
         * Whenever a message is received from the input stream, broadcast it to
         * all available clients.
         */
        @Override
        public void run() {
            while (true) {
                try {
                    line = input.readLine();

                    sendToClients(line);
                } catch (IOException e) {
                }
            }
        }
    }
}
