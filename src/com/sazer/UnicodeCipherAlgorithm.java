package com.sazer;

public class UnicodeCipherAlgorithm implements CipherAlgorithm {
    @Override
    public String encrypt(String message, int key) {
        String encryptedMessage = "";
        char currentChar;
        for(int i=0;i<message.length();i++){
            currentChar = message.charAt(i);
            encryptedMessage+= (char) (currentChar + key);
        }
        return encryptedMessage;
    }

    @Override
    public String decrypt(String message, int key) {
        char currentChar;
        String resolvedMessage = "";
        for(int i=0;i<message.length();i++){
            currentChar = message.charAt(i);
            resolvedMessage += (char) (currentChar - key);
        }
        return resolvedMessage;
    }
}
