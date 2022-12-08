package com.github.pizzagames.retroremote.client;

public class InputAction {
    public static final String KEY_DOWN = "KD";
    public static final String KEY_UP = "KU";
    public static final String MOUSE_DOWN = "MD";
    public static final String MOUSE_UP = "MU";
    public static final String MOUSE_MOVE = "MM";
    public static final String MOUSE_WHEEL = "MW";



    private final String action;
    private final int width;
    private final int height;
    private final int x;
    private final int y;
    private final int delta;
    private final int keyCode;
    private final String keyValue;

	public InputAction(String action, int width, int height, int x, int y, int delta, int keyCode, String keyValue) {
		this.action = action;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.delta = delta;
		this.keyCode = keyCode;
		this.keyValue = keyValue;
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
