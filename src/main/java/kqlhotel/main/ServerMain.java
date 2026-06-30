package kqlhotel.main;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    private static final int PORT = 9999;

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            System.out.println("=================================");
            System.out.println(" SERVER IS RUNNING ");
            System.out.println(" PORT: " + PORT);
            System.out.println("=================================");

            while (true) {

                Socket socket = serverSocket.accept();

                System.out.println(
                        "Client connected: "
                                + socket.getInetAddress().getHostAddress()
                );

                // xử lý nhiều client cùng lúc
                ClientHandler clientHandler = new ClientHandler(socket);

                clientHandler.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}