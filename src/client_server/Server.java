package client_server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
//server distribuito
class GenericServer implements Runnable{

    private final Socket client;
    private final int ID;

    public GenericServer(Socket client, int ID) {
        this.client = client;
        this.ID = ID;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
            while ( true ) {
                String s = in.readLine();
                if ( s == null )break;
                else {
                    System.out.println(client + ". Messaggio: " + s);
                    StringBuffer reverse = new StringBuffer(s).reverse();
                    out.println(reverse); out.flush(); //Rilsacia risolrse attiva le operazioni di scrittura
                }
            }
            in.close();
            out.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

public class Server {
    public static void main(String[] args){

        ServerSocket server = null; // Il server si mette in ascolto
        try {
            server = new ServerSocket(5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while ( true )//sulla porta di servizio
        try {
            System.out.println("In attesa di connessione...");
            Socket client = server.accept();
            int id=0;
            Runnable runnable=new GenericServer(client,id++);
            Thread thread=new Thread (runnable);
            thread.start();
        }
        catch (IOException ioe) {

        }

    }
}
