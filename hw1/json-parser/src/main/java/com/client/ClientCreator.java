package com.client;

import java.io.FileReader;
import java.io.IOException;

public abstract class ClientCreator {

    public void getInfo(FileReader reader) throws IOException {
        Client client = createClient(reader);
        System.out.println(client.getType());
    }

    public abstract Client createClient(FileReader reader) throws IOException;
}
