package com.sazer;

public class ShiftCipherAlgorithm implements CipherAlgorithm {
    String alphabetLowercase = "abcdefghijklmnopqrstuvwxyz";
    String alphabetUppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    @Override
    public String encrypt(String message, int key) {
        char currentChar;
        String encryptedMessage = "";
        for(int i=0;i<message.length();i++){
            currentChar = message.charAt(i);
            if(alphabetLowercase.indexOf(currentChar) != -1){
                encryptedMessage += alphabetLowercase.charAt((alphabetLowercase.indexOf(currentChar)+key)%alphabetLowercase.length());
            }else if(alphabetUppercase.indexOf(currentChar) != -1){
                encryptedMessage += alphabetUppercase.charAt((alphabetUppercase.indexOf(currentChar)+key)%alphabetUppercase.length());
            }
            else{
                encryptedMessage += currentChar;
            }
        }
        return encryptedMessage;
    }

    @Override
    public String decrypt(String message, int key) {
        char currentChar;
        int decodedCharIndex;
        char decodedChar;
        String resolvedMessage = "";
        for(int i=0;i<message.length();i++){
            currentChar = message.charAt(i);
            if(alphabetLowercase.indexOf(currentChar) != -1){
                decodedCharIndex = alphabetLowercase.indexOf(currentChar) - key;
                if(decodedCharIndex < 0) decodedCharIndex += alphabetLowercase.length();
                decodedChar = alphabetLowercase.charAt(decodedCharIndex);
            }else if(alphabetUppercase.indexOf(currentChar) != -1){
                decodedCharIndex = alphabetUppercase.indexOf(currentChar) - key;
                if(decodedCharIndex < 0) decodedCharIndex += alphabetUppercase.length();
                decodedChar = alphabetUppercase.charAt(decodedCharIndex);
            }
            else{
                decodedChar = currentChar;
            }
            resolvedMessage += decodedChar;
        }
        return resolvedMessage;
    }
}
