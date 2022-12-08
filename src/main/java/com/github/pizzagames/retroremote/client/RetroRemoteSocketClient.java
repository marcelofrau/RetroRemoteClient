package com.github.pizzagames.retroremote.client;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class RetroRemoteSocketClient implements Runnable {

    private Socket socket = null;

    public void run() {


            //socket = new Socket("127.0.0.1", 9999);


            //final InputStreamReader reader = new InputStreamReader(socket.getInputStream());

            //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


            //final String name = reader.readLine();

            // Printing the read line
            //System.out.println(name);


            //reader.read();

            //reader.close();

    }

    public static void main(String[] args) throws IOException {
        //final ServerSocket server = new ServerSocket(9999);

        //System.out.println("accept");
        //final Socket socket = server.accept();

        final Socket socket = new Socket("localhost", 9999);

        final InputStreamReader input = new InputStreamReader(socket.getInputStream());

        final StringBuffer chunk = new StringBuffer();

        final char[] buffer = new char[8];
        int offset = -1;

        int charsRead;
        System.out.print("reading data....");
        while ((charsRead = input.read(buffer)) >= 0) {
            System.out.print(".");
            chunk.append(buffer, 0, charsRead);

            final int start = chunk.indexOf("{");
            final int end = chunk.indexOf("}", offset);

            if (start > -1 && offset == -1) {
                offset = start;
            }

            if (end > -1 && offset > -1 && end > start) {
                final String msg = chunk.substring(offset);
                System.out.println();

                System.out.println(InputAction.parseAction(msg));

                chunk.setLength(0);
                offset = -1;
            }
        }

        input.close();
        socket.close();
    }
}
