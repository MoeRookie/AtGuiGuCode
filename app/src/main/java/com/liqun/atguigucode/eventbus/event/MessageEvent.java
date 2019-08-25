package com.liqun.atguigucode.eventbus.event;

public class MessageEvent {
    public String name;
    public String password;

    public MessageEvent(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
