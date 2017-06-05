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
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import videoconference.Server;

/**
 *
 * @author ACER
 */
public class ServerThread extends Thread {
    private ServerSocket serversocket;
    private Server form;
    private Socket socket;
    private ArrayList clientOutputStreams;
    public ServerThread(Server form)
    {
       
        try
        {
        this.serversocket=new ServerSocket(222);
        this.form=form;
        this.clientOutputStreams=new ArrayList();
        serversocket.setSoTimeout(180000);
        }
        catch(Exception ex)
        {
              Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     @Override
    public void run() {
        System.out.print("vô rồi");
       
        while(true){
            try {
                socket=serversocket.accept();
                OutputStream writer=socket.getOutputStream();
                clientOutputStreams.add(writer);
                BufferedImage buff = ImageIO.read(ImageIO.createImageInputStream(socket.getInputStream()));
                //sendMultiStream(form,img);
                
                //ImageIO.write(img, "png", new File("server.png"));
               //  Image img2 = ImageIO.read(new File("server.png"));
                 ImageIO.write(buff, "png", socket.getOutputStream());
                Graphics gra = form.getJPanel().getGraphics();
                gra.drawImage(buff,0,0, form.getWidth(), form.getHeight(),0, 0, buff.getWidth(), buff.getHeight(),  Color.BLACK,null);
            
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
   }
    //Đang gặp vấn đề
    void sendMultiStream(Server form,BufferedImage img)
    {
         Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()) 
        {
            try 
            {
                OutputStream writer = (OutputStream) it.next();
		
                 //BufferedImage img = ImageIO.read(ImageIO.createImageInputStream(writer.getInputStream()));
                  ImageIO.write(img, "png", writer);
		//txtThongBaoServer.append("Đang gửi: " + message + "\n");
               // writer.flush();
                //txtThongBaoServer.setCaretPosition(txtThongBaoServer.getDocument().getLength());
              //  ImageIO.write(img, "png", writer.getOutputStream());
                Graphics gra = form.getJPanel().getGraphics();
                gra.drawImage(img,0,0, form.getWidth(), form.getHeight(), Color.BLACK,null);
            } 
            catch (Exception ex) 
            {
		//form.getJText().append("Lỗi: Send MultiStream. \n");
                System.out.println(ex.toString());
            }
        } 
    }
        
}
