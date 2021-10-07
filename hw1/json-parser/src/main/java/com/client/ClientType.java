package com.client;

import java.io.FileReader;
import java.io.IOException;

public enum ClientType {
    INDIVIDUAL {
        public Client createClient(String filename) throws IOException {
            IndividualCreator individualCreator = new IndividualCreator();
            return individualCreator.createClient(new FileReader(filename));
        }
    },
    LEGAL_ENTITY {
        public Client createClient(String filename) throws IOException {
            LegalEntityCreator legalEntityCreator = new LegalEntityCreator();
            return legalEntityCreator.createClient(new FileReader(filename));
        }
    },
    HOLDING {
        public Client createClient(String filename) throws IOException {
            HoldingCreator holdingCreator = new HoldingCreator();
            return holdingCreator.createClient(new FileReader(filename));
        }
    },
    ;

    public abstract Client createClient(String filename) throws IOException;
}
