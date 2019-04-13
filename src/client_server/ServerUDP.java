package client_server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

class GenericServerUDP implements Runnable{

    DatagramPacket datagramPacket;
    DatagramSocket serverSocket;

    public GenericServerUDP(DatagramPacket datagramPacket,DatagramSocket serverSocket) {
        this.datagramPacket = datagramPacket;
        this.serverSocket=serverSocket;
    }

    @Override
    public void run() {
        byte[] sendData = new byte[1024];
        String sentence = new String( datagramPacket.getData());
        System.out.println("RECEIVED: " + sentence);
        InetAddress IPAddress = datagramPacket.getAddress();
        int port = datagramPacket.getPort();
        String capitalizedSentence = sentence.toUpperCase();
        sendData = capitalizedSentence.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
        try {
            serverSocket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

public class ServerUDP {
    public static void main(String[] args) {
        DatagramSocket serverSocket = null;


        try {
            serverSocket = new DatagramSocket(9876);
            while(true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                GenericServerUDP genericServerUDP=new GenericServerUDP(receivePacket,serverSocket);
                Thread thread=new Thread(genericServerUDP);
                thread.start();


            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
