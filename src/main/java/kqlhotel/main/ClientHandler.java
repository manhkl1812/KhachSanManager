package kqlhotel.main;



import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            while (true) {
                Request request = (Request) in.readObject();
                String action = request.getAction();
                System.out.println("Action: " + action);

                Response response;
                switch (action) {
                    case "PING":
                        response = new Response(true, null, "PONG");
                        break;
                    default:
                        response = new Response(false, null, "Unknown request");
                }

                out.writeObject(response);
                out.flush();
            }
        } catch (Exception e) {
            System.out.println("Client disconnected: " + e.getMessage());
        }
    }
}