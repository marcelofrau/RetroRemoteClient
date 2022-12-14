package com.github.pizzagames.retroremote.client;


public class RetroEvent {
    private RetroAction action;
    private int x;
    private int y;
    private int delta;
    private int keyCode;
    private int button;

    public RetroAction getAction() {
        return action;
    }

    public void setAction(RetroAction action) {
        this.action = action;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getButton() {
        return button;
    }

    public void setButton(int button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "RetroEvent{" +
                "action=" + action +
                ", x=" + x +
                ", y=" + y +
                ", delta=" + delta +
                ", keyCode=" + keyCode +
                ", button=" + button +
                '}';
    }

}