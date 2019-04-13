package client_server;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientHTTP {
    public static void main(String[] args){
        try {

            Socket s = new Socket(InetAddress.getByName("stackoverflow.com"), 80);
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            pw.println("GET / HTTP/1.1");
            pw.println("Host: stackoverflow.com");
            pw.println("");
            pw.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String t;
            while((t = br.readLine()) != null)
                System.out.println(t);
            br.close();
        }
        catch (IOException ioe) {

        }
    }
}
