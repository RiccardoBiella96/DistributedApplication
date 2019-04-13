package client_server;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args){
        try {
            Socket client = new Socket("localhost", 5000); //Il programma client si connette al server
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter (new OutputStreamWriter(client.getOutputStream()));
            BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in));
            //input da console
            while ( true ) {
                String line = lineReader.readLine();
                if ( line.equals("!q") ) break;
                else {
                    out.println(line); //scrivo sulla connessione
                    out.flush(); //rliascia le risorse
                    System.out.println(in.readLine());
                }
            }
            in.close();
            out.close();
            client.close(); }
        catch (IOException ioe) {

        }
    }
}
