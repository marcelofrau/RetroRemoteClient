package com.github.pizzagames.retroremote.client;

public enum RetroAction {
    KeyDown(1),
    KeyUp(2),
    MouseMove(3),
    MouseDown(4),
    MouseUp(5),
    MouseWheel(6);


    private final int code;

    RetroAction(int code) {
        this.code = code;
    }

    public static RetroAction fromCode(int code) {
        switch (code) {
            case 1: return KeyDown;
            case 2: return KeyUp;
            case 3: return MouseMove;
            case 4: return MouseDown;
            case 5: return MouseUp;
            case 6: return MouseWheel;
        }
        throw new UnsupportedOperationException("Code not valid");
    }
}
