package com;
import static org.junit.jupiter.api.Assertions.*;

import com.client.Client;
import com.client.ClientType;
import com.parser.Identifier;
import org.junit.jupiter.api.Test;

public class FirstApproachTest {

    @Test
    public void testIndividual(){
        Client client = Identifier.identify("src/main/resources/jsons/ind.json");
        assertEquals(ClientType.INDIVIDUAL, client.getType());
    }

    @Test
    public void testLegalEntity(){
        Client client = Identifier.identify("src/main/resources/jsons/leg.json");
        assertEquals(ClientType.LEGAL_ENTITY, client.getType());

    }

    @Test
    public void testHolding(){
        Client client = Identifier.identify("src/main/resources/jsons/hol.json");
        assertEquals(ClientType.HOLDING, client.getType());
    }
}