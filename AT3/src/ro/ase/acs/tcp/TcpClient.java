package ro.ase.acs.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("localhost", 8888)) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("name: ");
            String name = scanner.nextLine();
            new Thread(() -> {
                try {
                    InputStream is = null;
                    while (true) {
                        is = clientSocket.getInputStream();
                        DataInputStream dis = new DataInputStream(is);
                        String message = dis.readUTF();
                        System.out.println(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
            while (true) {
                OutputStream out = clientSocket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(out);
                System.out.println("message: ");
                dataOutputStream.writeUTF(name + ": " + scanner.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
