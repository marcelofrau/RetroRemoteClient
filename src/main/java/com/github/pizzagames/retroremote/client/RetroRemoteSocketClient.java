package com.github.pizzagames.retroremote.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class RetroRemoteSocketClient implements Runnable {

    private Socket socket = null;

    public void run() {
        try {
            socket = new Socket("127.0.0.1", 9999);


            final InputStreamReader reader = new InputStreamReader(socket.getInputStream());

            //reader.read();

            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
