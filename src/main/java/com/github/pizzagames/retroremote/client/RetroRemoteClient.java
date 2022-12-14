package com.github.pizzagames.retroremote.client;


import java.awt.*;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class RetroRemoteClient implements Runnable {

    private Robot robot;


    public static void main(String[] args) {
        new RetroRemoteClient().run();
    }


    private int readMessage(StringBuilder chunk, char[] buffer, int offset, int charsRead) {
        chunk.append(buffer, 0, charsRead);

        final int start = chunk.indexOf("{");
        final int end = chunk.indexOf("}", offset);

        if (start > -1 && offset == -1) {
            offset = start;
        }

        if (end > -1 && offset > -1 && end > start) {
            final String extracted = chunk.substring(offset + 1, end).trim();
            parseMessage(extracted);
            chunk.delete(0, end);
            offset = -1;
        }
        return offset;
    }

    private void parseMessage(String msg) {
        if (msg.indexOf(':') == -1) {
            return;
        }

        final String[] entries = msg.split(",");
        final RetroEvent event = new RetroEvent();

        for (String entry : entries) {
            parseEntry(entry.trim(), event);
        }

        handleEvent(event);
    }

    private void handleEvent(RetroEvent event) {
        final RetroAction action = event.getAction();

        switch (action) {
            case MouseMove: handleMouseMove(event.getX(), event.getY()); break;
            case MouseDown: handleMouseDown(event.getButton()); break;
            case MouseUp: handleMouseUp(event.getButton()); break;
            case KeyDown: handleKeyDown(event.getKeyCode()); break;
            case KeyUp: handleKeyUp(event.getKeyCode()); break;
            case MouseWheel: handleMouseWheel(event.getDelta()); break;
        }
    }

    private void handleMouseWheel(int delta) {
        robot.mouseWheel(delta);
    }

    private void handleKeyUp(int keyCode) {
        robot.keyRelease(keyCode);
    }

    private void handleKeyDown(int keyCode) {
        robot.keyPress(keyCode);
    }

    private void handleMouseUp(int button) {
        robot.mouseRelease(convertButton(button));
    }

    private void handleMouseDown(int button) {
        robot.mousePress(convertButton(button));
    }

    private static int convertButton(int button) {
        switch (button) {
            case 1: return InputEvent.BUTTON1_MASK;
            case 2: return InputEvent.BUTTON2_MASK;
            case 3: return InputEvent.BUTTON3_MASK;
            default: throw new RuntimeException("Invalid button");
        }
    }

    private void handleMouseMove(final int x, final int y) {
        final Point location = MouseInfo.getPointerInfo().getLocation();
        robot.mouseMove(location.x + x, location.y + y);
    }

    private void parseEntry(final String entry, final RetroEvent event) {
        if (entry.indexOf(':') == -1) {
            return;
        }

        final String[] keyValue = entry.split(":");

        final String key = keyValue[0].trim();
        final String value = keyValue[1].trim();

        switch (key.charAt(0)) {
            case 'a': event.setAction(RetroAction.fromCode(Integer.parseInt(value))); break;
            case 'x': event.setX(Integer.parseInt(value)); break;
            case 'y': event.setY(Integer.parseInt(value)); break;
            case 'd': event.setDelta(Integer.parseInt(value)); break;
            case 'k': event.setKeyCode(Integer.parseInt(value)); break;
            case 'b': event.setButton(Integer.parseInt(value)); break;
        }

    }

    private static boolean cancelled(StringBuilder chunk) {
        return chunk.indexOf("^]") > 0;
    }

    public void run() {

        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

        try {
            //final ServerSocket server = new ServerSocket(9999);
            //final Socket socket = server.accept();

            final Socket socket = new Socket("10.0.0.20", 9999);

            final InputStreamReader input = new InputStreamReader(socket.getInputStream(), "UTF-8");
            final StringBuilder chunk = new StringBuilder();

            final char[] buffer = new char[3];
            int offset = -1;
            int charsRead;

            while ((charsRead = input.read(buffer)) >= 0) {
                offset = readMessage(chunk, buffer, offset, charsRead);
                if (cancelled(chunk)) {
                    break;
                }
            }

            input.close();
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
