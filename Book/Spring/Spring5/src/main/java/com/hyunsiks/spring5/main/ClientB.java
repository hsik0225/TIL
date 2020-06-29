package com.hyunsiks.spring5.main;

public class ClientB {

    private String host;

    public void setHost(String host) {
        this.host = host;
    }

    public void send() {
        System.out.println("ClientB.send() to " + host);
    }

    public void connect() {
        System.out.println("ClientB.connect() 실행");
    }

    public void close() {
        System.out.println("ClientB.close() 실행");
    }
}
