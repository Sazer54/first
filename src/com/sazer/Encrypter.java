package com.sazer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Encrypter {
    private String choice;
    private String rawMessage;
    private int key;
    private String resolvedMessage="";
    private String inFilePath;
    private String outFilePath;
    private CipherAlgorithm algUsed;

    public void work(String[] args){
        try{
            this.getData(args);
            this.resolveMessage();
            this.outputData();
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Wrong arguments passed");
        }
    }

    private void setAlgUsed(CipherAlgorithm algUsed) {
        this.algUsed = algUsed;
    }

    private void getData(String[] args){
        key = 0;
        rawMessage = "";
        choice = "enc";
        inFilePath = null;
        outFilePath = null;
        this.setAlgUsed(new ShiftCipherAlgorithm());
        for(int i=0;i<args.length;i++){
            switch (args[i]){
                case "-mode":
                    choice = args[i+1];
                    i++;
                    break;
                case "-key":
                    key = Integer.parseInt(args[i+1]);
                    i++;
                    break;
                case "-data":
                    if(inFilePath == null){
                        rawMessage = args[i+1];
                    }
                    i++;
                    break;
                case "-in":
                    inFilePath = args[i+1];
                    try(Scanner fileReader = new Scanner(new File(inFilePath))){
                        rawMessage = fileReader.nextLine();
                    }catch (IOException e){
                        System.out.println("An Error occurred");
                    }
                    i++;
                    break;
                case "-out":
                    outFilePath = args[i+1];
                    i++;
                    break;
                case "-alg":
                    if(args[i+1].equals("unicode")){
                        this.setAlgUsed(new UnicodeCipherAlgorithm()) ;
                    }
                    i++;
                    break;
            }
        }
    }

    private void resolveMessage(){
        if(choice.equals("enc")) this.resolvedMessage = this.algUsed.encrypt(this.rawMessage, this.key);
        else this.resolvedMessage = this.algUsed.decrypt(this.rawMessage, this.key);
    }

    private void outputData(){
        if(outFilePath != null){
            try(PrintWriter printWriter = new PrintWriter(new File(outFilePath))){
                printWriter.println(resolvedMessage);
            }catch(IOException e){
                System.out.println("An Error occurred");
            }
        }
        else{
            System.out.println(resolvedMessage);
        }
    }
}
