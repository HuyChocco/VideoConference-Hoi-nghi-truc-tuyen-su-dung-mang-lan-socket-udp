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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.TargetDataLine;
import videoconference.Client;

/**
 *
 * @author ACER
 */
public class ClientAudio extends Thread {

    public TargetDataLine audio_in = null;
    public DatagramSocket dout;
    byte byte_buff[] = new byte[512];
    public InetAddress server_ip;
    public int server_port;
    public Client frame;
    public ClientDTO client;
   
    
    public ClientAudio(Client frame){
        this.frame = frame;
    }

   
    @Override
    public void run() {
        int i = 0;
        while (frame.start) {
            try {//gui      
                audio_in.read(byte_buff, 0, byte_buff.length);
                DatagramPacket data = new DatagramPacket(byte_buff, byte_buff.length, server_ip, server_port);
                //System.out.println("send #" + data + "  " + i++);
                dout.send(data);
            } catch (IOException ex) {
                Logger.getLogger(ClientAudio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        audio_in.close();
        audio_in.drain();
    }
}