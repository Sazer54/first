package com.sazer;

public interface CipherAlgorithm {
    String encrypt(String message, int key);
    String decrypt(String message, int key);
}
