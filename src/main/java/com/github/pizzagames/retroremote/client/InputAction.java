package com.github.pizzagames.retroremote.client;

import jdk.internal.util.xml.impl.Input;

import java.io.InputStream;

public class InputAction {

	public static final String KEY_DOWN = "KD";
    public static final String KEY_UP = "KU";
    public static final String MOUSE_DOWN = "MD";
    public static final String MOUSE_UP = "MU";
    public static final String MOUSE_MOVE = "MM";
    public static final String MOUSE_WHEEL = "MW";

    public static final String START_TOKEN = "{";
    public static final String END_TOKEN = "}";

    private static final int INVALID = -1;

    private final String action;
    private final int width;
    private final int height;
    private final int x;
    private final int y;
    private final int delta;
    private final int keyCode;
    private final String button;
    private final String keyValue;

	public InputAction(String action, int width, int height, int x, int y, int delta, int keyCode, final String button, String keyValue) {
		this.action = action;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.delta = delta;
		this.keyCode = keyCode;
        this.button = button;
		this.keyValue = keyValue;
	}

    public static InputAction parseAction(String message) {
        //{MM;x:845;y:486;w:1920;h:1080;}
        final String[] msgParts = message.split(";");

        final String action = msgParts[0];

        int width = -1;
        int height = -1;
        int x = -1;
        int y = -1;
        int delta = -1;
        int keyCode = -1;
        String button = null;
        String keyValue = null;

        for (int i = 1; i < msgParts.length; i++) {
            final String part = msgParts[i];
            final char c = part.charAt(0);
            switch (c) {
                case 'x': x = readNumber(part); break;
                case 'y': y = readNumber(part); break;
                case 'd': delta = readNumber(part); break;
                case 'w': width = readNumber(part); break;
                case 'h': height = readNumber(part); break;
                case 'c': keyCode = readNumber(part); break;
                case 'b': button = readString(part); break;
                case 'v': keyValue = readString(part); break;
            }
        }


        return new InputAction(action, width, height, x, y, delta, keyCode, button, keyValue);
    }

    private static String readString(String part) {
        return part.substring(2);
    }

    private static int readNumber(String part) {
        return Integer.parseInt(readString(part));
    }

    public String toString() {
        return "InputAction{" +
                "action='" + action + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", x=" + x +
                ", y=" + y +
                ", delta=" + delta +
                ", keyCode=" + keyCode +
                ", keyValue='" + keyValue + '\'' +
                '}';
    }

    public String getAction() {
        return action;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDelta() {
        return delta;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public String getKeyValue() {
        return keyValue;
    }

}
