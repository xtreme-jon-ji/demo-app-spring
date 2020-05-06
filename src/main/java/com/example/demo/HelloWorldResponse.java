package com.example.demo;

public class HelloWorldResponse {
    private String value;

    public HelloWorldResponse() {}

    public HelloWorldResponse(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
