package ro.ase.acs.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            Scanner scanner = new Scanner(System.in);
            new Thread(() -> {
                while(true) {
                    byte[] buffer = new byte[1024];
                    DatagramPacket packetToBeReceived =
                            new DatagramPacket(buffer, buffer.length);
                    try {
                        socket.receive(packetToBeReceived);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String receivedMessage = new String(packetToBeReceived.getData(), 0,
                            packetToBeReceived.getLength());
                    System.out.println(receivedMessage);
                }
            }).start();

            while(true) {
                System.out.println("Message: ");
                String message = scanner.nextLine();
                byte[] buf = message.getBytes();
                DatagramPacket packetToBeSent =
                        new DatagramPacket(buf, buf.length,
                                InetAddress.getLocalHost(), 8888);
                socket.send(packetToBeSent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
