package com.sazer;

public class Main {

    public static void main(String[] args) {
        Encrypter encrypter = new Encrypter();
        String[] aargs = {"-mode", "enc", "-key", "5", "-data", "Welcome to hyperskill!", "-alg", "unicode"};
        encrypter.work(aargs);
    }
}
