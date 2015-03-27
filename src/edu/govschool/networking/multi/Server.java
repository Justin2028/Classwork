package edu.govschool.networking.multi;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private Thread serverThread;
    // Set of output streams to clients
    private final Set<ObjectOutputStream> outputs;
    private final Set<String> users;
    // ServerSocket for clients to connect to
    private ServerSocket server;
    // Temporary variable for client connection to pass to thread
    private Socket conn;
    // Variable for locking while broadcasting
    private boolean broadcasting = false;

    /**
     * Setup the server and begin accepting client connections.
     */
    public Server() {
        try {
            System.out.println("Binding to port " + PORT + ", please wait...");
            server = new ServerSocket(PORT);
            System.out.println("Server started: " + server);
        } catch (IOException e) {
            printErr("Error binding to port " + PORT + ".");
        }

        outputs = new HashSet<>();
        users = new HashSet<>();
    }

    public void runServer() {
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
                System.out.println("Waiting for a client...");
                // Accept a new client
                conn = server.accept();
                // Create a new ClientThread to handle the connection
                ClientThread user = new ClientThread(conn);
                // Notify that a client connected
                if (!users.contains(user.getUsername())) {
                    users.add(user.getUsername());
                    System.out.println("Client: " + user.getUsername()
                            + " accepted " + conn);
                    // Begin processing the client
                    user.start();
                } else {
                    System.out.println("Attempted duplicate username.");
                    outputs.remove(user.output);
                    user = null;
                }
            } catch (IOException e) {
                printErr("Error connecting client.");
            }
        }
    }

    /**
     * Print an error message.
     *
     * @param err the error message
     */
    private void printErr(String err) {
        System.err.println(err);
    }

    /**
     * Send a message to the connected clients. We lock the outputs set, and
     * release it afterwards. Clients will need to wait before disconnecting.
     *
     * @param msg the message to broadcast
     * @throws IOException the message could not be broadcast
     */
    private synchronized void sendToClients(ChatResponse msg) throws IOException {
        broadcasting = true;

        while (broadcasting) {
            for (ObjectOutputStream out : outputs) {
                out.writeObject(msg);
            }

            broadcasting = false;
        }

        notifyAll();
    }

    private class ClientThread extends Thread {

        // The connection to the server
        private final Socket socket;
        // I/O streams
        private ObjectInputStream input;
        private ObjectOutputStream output;
        // The username of this client
        private String username;
        // The current message
        private ChatResponse line;
        private boolean connected;

        public ClientThread(Socket socket) {
            // Save our connection
            this.socket = socket;

            // Setup the I/O streams
            try {
                this.getStreams();
                // Add the output stream to our set of outputs
                outputs.add(this.output);
                connected = true;
            } catch (IOException e) {
                printErr("Error getting streams on client thread.");
            }

            // Set the client username
            try {
                this.line = (ChatResponse) input.readObject();
                this.username = this.line.getMessage();
            } catch (IOException | ClassNotFoundException e) {
                printErr("Error setting username on client thread.");
            }
        }

        /**
         * Get the client username.
         *
         * @return the client username
         */
        public String getUsername() {
            return this.username;
        }

        /**
         * Setup our I/O streams via the socket connection.
         *
         * @throws IOException the streams could not be created
         */
        private void getStreams() throws IOException {
            this.input = new ObjectInputStream(socket.getInputStream());
            this.output = new ObjectOutputStream(socket.getOutputStream());
            this.output.flush();
        }

        /**
         * Whenever a message is received from the input stream, broadcast it to
         * all available clients.
         */
        @Override
        public void run() {
            while (connected) {
                try {
                    line = (ChatResponse) input.readObject();
                    if (line.getResponseType() == ChatResponse.TYPE_DISCONNECT) {
                        outputs.remove(output);
                        input.close();
                    } else {
                        sendToClients(line);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    printErr("Error sending message from client "
                            + this.username);
                    connected = false;
                }
            }
            outputs.remove(output);
            try {
                input.close();
            } catch (Exception e) {
            }
            users.remove(username);
        }
    }

    public static void main(String[] args) {
        new Server().runServer();
    }
}
