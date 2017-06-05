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
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;

/**
 *
 * @author ACER
 */
public class ServerAudio implements Runnable {
    public DatagramSocket din;
    public int port;
    byte[] buffer = new byte[512];
    public Thread send, receive;
    private List<ServerDTO> clients = new ArrayList<ServerDTO>();
    public boolean running = false;
    private ServerDTO cl;
////////////////////////////////////
     public static AudioFormat getaudioformat() {
        float sampleRate = 8000.0F;
        int sampleSizeInbits = 16;
        int channel = 2;
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate, sampleSizeInbits, channel, signed, bigEndian);
    }
///////////////////////////////////
    @Override
    public void run() {
        running = true;
        recieve();

    }

    private void recieve() {
        receive = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (running) {
                    System.out.println(clients.size() + "sizeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
                    byte[] data = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(data, data.length);
                    try {
                        din.receive(packet);
                        System.out.println("#" + packet.getData() + "   " + i++);
                    } catch (IOException ex) {

                    }
                    process(packet);
                }
            }
        };
        receive.start();
    }
    /////////////////////////////////////////////
    private void process(DatagramPacket packet) {
        String string = new String(packet.getData());
        
        int id;
        if (string.startsWith("/c/")) {
            try {  
                id = UniqueIdentifier.getIdentifier();
                InetAddress ip = InetAddress.getByName("localhost");
                cl = new ServerDTO(packet.getAddress(), packet.getPort(), id);
                clients.add(cl);
            } catch (UnknownHostException ex) {
                Logger.getLogger(ServerAudio.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    if(!string.startsWith("/c/"))
            sendToAll(packet.getData());
        
    }
////////////////////////////////////////////////
    private void sendToAll(byte[] data) {
        for (int i = 0; i < clients.size(); i++) {
            ServerDTO client = clients.get(i);
            send(data, client.address, client.port);
        }
    }
////////////////////////////////////////////////
    private void send(final byte[] data, InetAddress address, int port) {
        send = new Thread("Send") {
            @Override
            public void run() {
                DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
                try {
                    din.send(packet);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        };
        send.start();
    }
//////////////////////////////////////////////////////
}
