package com.parser;

import com.client.Client;
import com.client.HoldingCreator;
import com.client.IndividualCreator;
import com.client.LegalEntityCreator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Identifier {
    static public Client identify(String filename){
        try {
            Client client;
            FileReader reader = new FileReader(filename);
            switch (Parser.determineClientType(reader)) {
                case "INDIVIDUAL":
                    IndividualCreator individualCreator = new IndividualCreator();
                    client = individualCreator.createClient(new FileReader(filename));
                    break;
                case "HOLDING":
                    HoldingCreator holdingCreator = new HoldingCreator();
                    client = holdingCreator.createClient(new FileReader(filename));
                    break;
                case "LEGAL_ENTITY":
                    LegalEntityCreator legalEntityCreator = new LegalEntityCreator();
                    client = legalEntityCreator.createClient(new FileReader(filename));
                    break;
                default:
                    client = null;
            }
            return client;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
