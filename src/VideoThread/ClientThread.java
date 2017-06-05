/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VideoThread;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import videoconference.Client;
import videoconference.VideoForm;

/**
 *
 * @author ACER
 */
public class ClientThread extends Thread {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    public static VideoCapture webSource;//=null;//=new VideoCapture(0) ;
    private Mat frame = new Mat();
    private MatOfByte mem = new MatOfByte();
    private boolean isComplete;
    private VideoForm form;
    public static boolean runnable;
   // private Socket socket;
    public ClientThread(VideoForm form)//,Socket socket)
    {
        this.form=form;
       webSource=new VideoCapture(0) ;
       
       // this.socket=socket;
    }
     public synchronized void saveImage() {
         while(runnable)
            {
                
        if (webSource.grab()) {
            try {
                 int port = 222;
                 InetAddress add=InetAddress.getByName("localhost");
                webSource.read(frame);
                Imgcodecs.imwrite("src/Image/client.png", frame);
                //Imgcodecs.imencode(".png", frame, mem);
               Image img = ImageIO.read(new File("src/Image/client.png"));
                BufferedImage buff = (BufferedImage) img;
               // ByteArrayOutputStream baos=new ByteArrayOutputStream();
                //ImageIO.write(buff,"png",baos);
              // byte[] bytearr=baos.toByteArray();
             // System.out.println(bytearr.length);
             //  DatagramSocket datasock=new DatagramSocket(222);
              // DatagramPacket datagram=new DatagramPacket(bytearr,bytearr.length,add,port);
              // datasock.send(datagram);
                
                Graphics gra = form.getJPanel().getGraphics();
               
               
                Socket socket = new Socket("localhost", port);
                
                ImageIO.write(buff, "png", socket.getOutputStream());
                BufferedImage img2 = ImageIO.read(ImageIO.createImageInputStream(socket.getInputStream()));
                 gra.drawImage(img2, 0, 0, form.getWidth(), form.getHeight(),0, 0, buff.getWidth(), buff.getHeight(), Color.BLACK, null);
                
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            }
    }

    @Override
    public void run() {
        
        while (true) {
            try{
            this.saveImage();
            
            }
            catch (Exception ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
      
            
         
    }
    public void sendPacket(byte[] b,DatagramSocket ds)
    {
        int offset=0;
        
        DatagramPacket dp=new DatagramPacket(b,offset,512);
        int bytesSent=0;
        
        while(bytesSent<b.length)
        {
            try {
                ds.send(dp);
                bytesSent+=dp.getLength();
                
                dp.setData(b, bytesSent, 512);
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
