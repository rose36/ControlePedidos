package br.ufc.rcm.systech;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Garen on 12/12/2015.
 */
public class Envia extends Thread {
    Socket socket;
    DataOutputStream dataOutputStream;
    String json;

    public Envia(String json){
        this.json = json;
    }

}
