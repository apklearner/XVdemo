package com.example.xkdemo.pin;

public class PinnedEntry {

    public boolean isPinned = false;
    public String time;
    public String content;


    public PinnedEntry(String time, String content) {
        this.time = time;
        this.content = content;
        isPinned = false;
    }

    public PinnedEntry(String content) {
        this.content = content;
        isPinned = true;
    }
}
