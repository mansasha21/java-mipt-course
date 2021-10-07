package com.parser;

import com.client.*;

public class FirstApproach {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Wrong amount of args (amount != 1)");
            return;
        }
        String filename = args[0];
        Client client = Identifier.identify(filename);
        if (!(client instanceof Client)) {
            System.out.println("Null");
        } else {
            System.out.println(client.getInfo());
        }
    }
}
