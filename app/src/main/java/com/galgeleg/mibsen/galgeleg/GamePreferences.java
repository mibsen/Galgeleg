package com.galgeleg.mibsen.galgeleg;

public enum GamePreferences
{
    USERNAME("username");

    private String key;

    GamePreferences(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
