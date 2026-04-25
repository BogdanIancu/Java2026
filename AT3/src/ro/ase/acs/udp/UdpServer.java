package ro.ase.acs.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpServer extends Thread {
    private static DatagramSocket socket;
    private static InetAddress clientAddress;
    private static int clientPort;

    public static void main(String[] args) {
        try {
            socket = new DatagramSocket(8888);
            System.out.println("Server started on port 8888...");
            int i = 0;
            while(true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println(message);
                if(i == 0) {
                    clientAddress = packet.getAddress();
                    clientPort = packet.getPort();
                    UdpServer server = new UdpServer();
                    server.start();
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();

        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Message: ");
            String replyMessage = scanner.nextLine();
            byte[] replyBuffer = replyMessage.getBytes();
            DatagramPacket packetToBeSent =
                    new DatagramPacket(replyBuffer, replyBuffer.length,
                            clientAddress, clientPort);
            try {
                socket.send(packetToBeSent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
