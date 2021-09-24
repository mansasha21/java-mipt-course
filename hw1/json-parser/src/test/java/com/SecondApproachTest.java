package com;
import static org.junit.jupiter.api.Assertions.*;

import com.client.Client;
import com.client.ClientType;
import com.parser.Identifier;
import com.parser.Parser;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;

public class SecondApproachTest {

    @Test
    public void testIndividual(){
        String filename = "src/main/resources/jsons/ind.json";
        try {
            Client client = ClientType.valueOf(Parser.determineClientType(new FileReader(filename))).createClient(filename);
            assertEquals(ClientType.INDIVIDUAL, client.getType());
        } catch (IOException e) {
            assertTrue(false);
        }

    }

    @Test
    public void testLegalEntity(){
        String filename = "src/main/resources/jsons/leg.json";
        try {
            Client client = ClientType.valueOf(Parser.determineClientType(new FileReader(filename))).createClient(filename);
            assertEquals(ClientType.LEGAL_ENTITY, client.getType());
        } catch (IOException e) {
            assertTrue(false);
        }
    }

    @Test
    public void testHolding(){
        String filename = "src/main/resources/jsons/hol.json";
        try {
            Client client = ClientType.valueOf(Parser.determineClientType(new FileReader(filename))).createClient(filename);
            assertEquals(ClientType.HOLDING, client.getType());
        } catch (IOException e) {
            assertTrue(false);
        }
    }
}