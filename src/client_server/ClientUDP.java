package client_server;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class ClientUDP {
    public static void main(String[] args) {
        var inFromUser = new BufferedReader(new InputStreamReader(System.in));
        InetAddress IPAddress;
        DatagramSocket clientSocket = null;
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        int numRequest=0;
        try {

            long end=0;
            long start= System.currentTimeMillis();
            while(end-start<15000) {
                numRequest++;
                clientSocket = new DatagramSocket();
                IPAddress = InetAddress.getByName("localhost");
                //String sentence = inFromUser.readLine();
                String sentence="ciao";
                sendData = sentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
                clientSocket.send(sendPacket);
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String modifiedSentence = new String(receivePacket.getData());
                System.out.println("FROM SERVER:" + modifiedSentence);
                end = System.currentTimeMillis();
            }
            clientSocket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("numero richieste effettuate: "+ numRequest);

    }
}
