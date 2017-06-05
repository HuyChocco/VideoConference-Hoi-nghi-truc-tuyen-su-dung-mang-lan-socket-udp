/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AudioThread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author ACER
 */
public class ClientDTO {
    private String name;
    private String address;
    private int port;
    private DatagramSocket socket;
    private InetAddress ip;
    private Thread send;
   

    public ClientDTO(String name, String address, int port) {
        this.name = name;
        this.address = address;
        this.port = port;
    }

    public ClientDTO(String address, int port) {
        this.address = address;
        this.port = port;
    }
    public InetAddress getIp() {
        return ip;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public boolean openConnection(String address) {
        try {
            socket = new DatagramSocket();
            ip = InetAddress.getByName(address);
        } catch (UnknownHostException ex) {
            
            return false;
        } catch (SocketException ex) {
           
            return false;
        }
        return true;
    }

    public String receive() {
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        try {
            socket.receive(packet);
        } catch (IOException ex) {
           
        }
        String message = new String(packet.getData());

        return message;
    }

    public void send(final byte[] data) {
        send = new Thread("Send") {
            @Override
            public void run() {
                DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
                try {
                    socket.send(packet);
                } catch (IOException ex) {
                    
                }
            }
        };
        send.start();
    }

   
}
