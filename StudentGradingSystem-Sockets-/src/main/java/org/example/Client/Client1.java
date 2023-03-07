package org.example.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {
    private static Socket socket;
    private static DataInputStream inputFromServer;
    private static DataOutputStream outputToServer;
    private static Scanner scanner;

    public static void main(String[] args) {
        try {
            socket = new Socket("localhost", 8000);
            inputFromServer = new DataInputStream(socket.getInputStream());
            outputToServer = new DataOutputStream(socket.getOutputStream());
            scanner = new Scanner(System.in);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        try {
            // Authentication process
            while (true) {
                showServerMessages();
                String sendToServer = scanner.nextLine();
                outputToServer.writeUTF(sendToServer);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static void showServerMessages() {
        try {
            String line = inputFromServer.readUTF();  // read the first message from the server
            if (line.equals("download")) {
                simulateDownloading();
                return;
            }
            System.out.println(line);

            while (true) {
                if (inputFromServer.available() == 0) {
                    break;
                }
                line = inputFromServer.readUTF();
                if (line.equals("download")) {
                    simulateDownloading();
                    return;
                }
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static void simulateDownloading() {
        // Print the progress bar
        for (int i = 0; i <= 10; i++) {
            try {
                Thread.sleep(50);
                System.out.print("\r"); // Move the cursor back to the beginning of the line
                for (int j = 0; j < i; j++) {
                    System.out.print("\u2588"); // Unicode full block character
                }
                for (int j = i; j < 10; j++) {
                    System.out.print("\u2591"); // Unicode light shade character
                }
                System.out.print(" " + (i * 10) + "%"); // Print the percentage
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("\r"); // Remove the progress bar
        for (int i = 0; i < 10; i++) {
            System.out.print(" ");
        }
        showServerMessages();
    }

}
