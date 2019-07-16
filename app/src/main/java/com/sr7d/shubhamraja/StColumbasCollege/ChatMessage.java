package com.sr7d.shubhamraja.StColumbasCollege;

/**
 * Created by Shubham Raja on 1/8/2017.
 */

public class ChatMessage {
    String name;
    String message;
    public ChatMessage(){

    }

    public ChatMessage(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }
}
