/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AudioThread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.SourceDataLine;
import videoconference.Client;

/**
 *
 * @author ACER
 */
public class ClientReceive extends Thread{
    public DatagramSocket dataSocket;
    public SourceDataLine audio_out;
    public byte[] buffer = new byte[512];
    public Client frame;

    public ClientReceive(Client frame) {
        this.frame = frame;
    }

   

    @Override
    public void run() {
        int i = 0;
        DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
        while (frame.start) {
            try {             
                    dataSocket.receive(incoming);
                    buffer = incoming.getData();
                    audio_out.write(buffer, 0, buffer.length);
                    System.out.println("#" + buffer + "   " + i++);
                
            } catch (IOException ex) {
                Logger.getLogger(ClientReceive.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
