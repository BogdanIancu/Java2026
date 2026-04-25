package ro.ase.acs.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class TcpServer {
    private static List<Socket> clients = new Vector<>();

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(8888)) {
            System.out.println("Server started on port 8888...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                clients.add(clientSocket);
                new Thread(()->{
                    while (true) {
                        InputStream is = null;
                        try {
                            is = clientSocket.getInputStream();
                            DataInputStream dis = new DataInputStream(is);
                            String receivedMessage = dis.readUTF();
                            for (Socket client : clients) {
                                if (!clientSocket.equals(client)) {
                                    DataOutputStream dataOutputStream =
                                            new DataOutputStream(client.getOutputStream());
                                    dataOutputStream.writeUTF(receivedMessage);
                                }
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
