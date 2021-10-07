package com.parser;

import com.client.Client;
import com.client.ClientType;

import java.io.FileReader;
import java.io.IOException;

public class SecondApproach {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Wrong amount of args (amount != 1)");
            return;
        }
        String filename = args[0];

        try {
            Client client = ClientType.valueOf(Parser.determineClientType(new FileReader(filename))).createClient(filename);
            if (!(client instanceof Client)) {
                System.out.println("Null");
            } else {
                System.out.println(client.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
