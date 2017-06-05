/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoconference;

import AudioThread.ClientAudio;
import AudioThread.ClientDTO;
import AudioThread.ClientReceive;
import VideoThread.ClientThread;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JPanel;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author ACER
 */
public class Client extends javax.swing.JFrame {
//////////////////
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;
   VideoCapture webSource ;
    static String ten;
    static String id;
    private VideoForm form;
////////////////////////////////////
    public int port = 1111;
    public String ip = "localhost";
    public DatagramSocket dataSocket;
    public boolean start = false;
    private ClientDTO client ;

    TargetDataLine audio_in;
    SourceDataLine audio_out;
///////////////////////////////////
    /**
     * Creates new form Client
     */
    public Client() {
        initComponents();
        btnNgat.setEnabled(false);
        txtTenPhongBan.setText(ten);
        txtMaPhongBan.setText(id);
        btnNgungVideo.setEnabled(false);
        btnBatDau.setEnabled(false);
        btnNhan1.setEnabled(false);
        btnNhan2.setEnabled(false);
        //////////////////////////////
        client = new ClientDTO(ip, port);
            
            boolean connect = client.openConnection(ip);
            if(!connect){
                System.out.println("failllllll");
                return;
            }
            this.dataSocket = client.getSocket();
            
        /////////////////////////////
    }
 /////////////////////////////
    public static AudioFormat getaudioformat() {
        float sampleRate = 8000.0F;
        int sampleSizeInbits = 16;
        int channel = 2;
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate, sampleSizeInbits, channel, signed, bigEndian);
    }
 /////////////////////////////---Send audio----
  public void send(byte[] data){
        try {
            DatagramPacket packet = new DatagramPacket(data, data.length, client.getIp(), client.getPort());
            dataSocket.send(packet);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
  
//////////////////////////////
  public void init_audio() {
        try {
            AudioFormat format = getaudioformat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            DataLine.Info info_out = new DataLine.Info(SourceDataLine.class, format);
            if (!AudioSystem.isLineSupported(info_out)) {
                System.out.println("not suport");
                System.exit(0);
            }
            audio_in = (TargetDataLine) AudioSystem.getLine(info);
            audio_in.open(format);
            audio_in.start();

            audio_out = (SourceDataLine) AudioSystem.getLine(info_out);
            audio_out.open(format);
            audio_out.start();

            ClientAudio clAudio = new ClientAudio(this);
            InetAddress ip = InetAddress.getByName(this.ip);
            clAudio.audio_in = audio_in;
            clAudio.dout = dataSocket;
            clAudio.server_ip = ip;
            clAudio.server_port = port;
            clAudio.start();
            
            ClientReceive clReceive = new ClientReceive(this);
            clReceive.audio_out= audio_out;
            clReceive.dataSocket =dataSocket;
            clReceive.start();
            //btnStart.setEnabled(false);
            //btnStop.setEnabled(true);
        } catch (Exception e) {

        }
    }

    public class IncomingReader implements Runnable
    {
        @Override
        public void run() 
        {
            String[] data;
            String stream, done = "Done", connected = "Connected", disconnect = "Disconnect", chat = "Chat";

            try 
            {
                while ((stream = reader.readLine()) != null) 
                {
                     data = stream.split(":");

                     if (data[2].equals(chat)) 
                     {
                        txtNoiDung.append(data[0] + ": " + data[1] + "\n");
                        txtNoiDung.setCaretPosition(txtNoiDung.getDocument().getLength());
                     } 
                    if (data[1].equals(connected))
                     {
                         
                       list.append(data[0]+"\n");
                       
                       
                     } 
                  //   else if (data[2].equals(disconnect)) 
                   //  {
                     //    userRemove(data[0]);
                   //  } 
                  //   else if (data[2].equals(done)) 
                   //  {
                        //users.setText("");
                      //  writeUsers();
                      //  users.clear();
                   //  }
                }
           }catch(Exception ex) { }
        }
    }

/////////////////////////////
     public void ListenThread() 
    {
         Thread IncomingReader = new Thread(new IncomingReader());
         IncomingReader.start();
    }
 ///////////////////////////   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaPhongBan = new javax.swing.JLabel();
        txtTenPhongBan = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtIP = new javax.swing.JTextField();
        txtPort = new javax.swing.JTextField();
        btnKetNoi = new javax.swing.JButton();
        btnNgat = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtTrangThai = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        list = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        txtMessage = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNoiDung = new javax.swing.JTextArea();
        btnSend = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnBatDau = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        btnNgungVideo = new javax.swing.JButton();
        btnNhan1 = new javax.swing.JButton();
        btnNhan2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Mã phòng ban :");

        jLabel2.setText("Thông tin ");

        jLabel3.setText("Tên phòng ban :");

        txtMaPhongBan.setText("0001");

        txtTenPhongBan.setText("Nhân sự");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaPhongBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTenPhongBan, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))))
                .addGap(0, 71, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaPhongBan))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenPhongBan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText("IP");

        jLabel7.setText("Port");

        txtIP.setText("localhost");

        txtPort.setText("2222");

        btnKetNoi.setText("Kết nối");
        btnKetNoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKetNoiMouseClicked(evt);
            }
        });

        btnNgat.setText("Ngắt kết nối");
        btnNgat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNgatMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(txtIP, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNgat)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnKetNoi)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKetNoi))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNgat))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 280, 110));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setText("Trạng thái");

        txtTrangThai.setText("Chưa kết nối");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(txtTrangThai)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 190, 70));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setText("Danh sách các phòng ban đang kết nối :");

        list.setEditable(false);
        list.setColumns(20);
        list.setRows(5);
        jScrollPane1.setViewportView(list);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel9))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 37, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 270, 250));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtNoiDung.setEditable(false);
        txtNoiDung.setColumns(20);
        txtNoiDung.setRows(5);
        jScrollPane2.setViewportView(txtNoiDung);

        btnSend.setText("Gửi");
        btnSend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSendMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(txtMessage))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(btnSend))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 11, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(txtMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSend)
                .addGap(5, 5, 5))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, 320, 270));

        jPanel6.setBackground(new java.awt.Color(102, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setToolTipText("");

        jLabel4.setText("CAM 1");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(jLabel4)
                .addContainerGap(172, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(jLabel4)
                .addContainerGap(170, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 360, 330));

        btnBatDau.setText("Bắt đầu");
        btnBatDau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBatDauMouseClicked(evt);
            }
        });
        getContentPane().add(btnBatDau, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 440, -1, -1));

        jPanel7.setBackground(new java.awt.Color(102, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setText("CAM 2");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(jLabel5)
                .addContainerGap(176, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(167, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(147, 147, 147))
        );

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 470, 340, 330));

        jPanel8.setBackground(new java.awt.Color(102, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setText("CAM 3");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(152, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(125, 125, 125))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(165, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(149, 149, 149))
        );

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 470, 310, 330));

        btnNgungVideo.setText("Ngừng truyền");
        btnNgungVideo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNgungVideoMouseClicked(evt);
            }
        });
        getContentPane().add(btnNgungVideo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 440, -1, -1));

        btnNhan1.setText("Nhận");
        getContentPane().add(btnNhan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 440, -1, -1));

        btnNhan2.setText("Nhận");
        getContentPane().add(btnNhan2, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 440, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/videoconference/background.jpg"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -110, 1140, 940));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKetNoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKetNoiMouseClicked
        String ip=txtIP.getText();
       txtNoiDung.setText("");
        if(txtPort.getText().length()>0&&txtIP.getText().length()>0){
             int port=Integer.parseInt(txtPort.getText());
        try 
            {
                sock = new Socket(ip, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(txtTenPhongBan.getText() + ":Connected"+": ");
                writer.flush(); 
                txtTrangThai.setText("Kết nối thành công");
                btnKetNoi.setEnabled(false);
                btnNgat.setEnabled(true);
                txtIP.setEditable(false);
                txtPort.setEditable(false);
                btnBatDau.setEnabled(true);
                btnNhan1.setEnabled(true);
                btnNhan2.setEnabled(true);
               // isConnected = true;
               ListenThread() ;
            } 
            catch (Exception ex) 
            {
                txtNoiDung.append("Không thể kết nối! Vui lòng thử lại! \n");
                txtIP.setEditable(true);
                txtPort.setEditable(true);
            }}else
        {
            txtNoiDung.append("Vui lòng nhập đầy đủ ! \n");
        }
    }//GEN-LAST:event_btnKetNoiMouseClicked

    private void btnSendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSendMouseClicked
       String nothing = "";
        if ((txtMessage.getText()).equals(nothing)) {
            txtMessage.setText("");
            txtMessage.requestFocus();
        } else {
            try {
               writer.println(txtTenPhongBan.getText() + ":" + txtMessage.getText() + ":Chat");
               writer.flush();
               // txtNoiDung.append(txtTenPhongBan.getText() + ":" + txtMessage.getText()+"\n");// flushes the buffer
            } catch (Exception ex) {
                txtNoiDung.append("Tin nhắn chưa được gửi đi. \n");
            }
            txtMessage.setText("");
            txtMessage.requestFocus();
        }

        txtMessage.setText("");
        txtMessage.requestFocus();
    }//GEN-LAST:event_btnSendMouseClicked
 //--------------------------//
    
    public void sendDisconnect() 
    {
        String bye = (txtTenPhongBan.getText() + ": :Disconnect");
        try
        {
            writer.println(bye); 
            writer.flush(); 
        } catch (Exception e) 
        {
            txtNoiDung.append("Không gửi được thông điệp ngắt kết nối.\n");
        }
    }

    //--------------------------//
    
    public void Disconnect() 
    {
        try 
        {
            txtNoiDung.append("Đã ngắt kết nối.\n");
            sock.close();
        } catch(Exception ex) {
            txtNoiDung.append("Ngắt kết nối thất bại! \n");
        }
        
        txtIP.setEditable(true);
        txtPort.setEditable(true);

    }
    private void btnNgatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNgatMouseClicked
        sendDisconnect() ;
        Disconnect();
        btnKetNoi.setEnabled(true);
        btnNgat.setEnabled(false);
        list.setText("");
        txtTrangThai.setText("Đã ngắt kết nối");
        btnBatDau.setEnabled(false);
        btnNgungVideo.setEnabled(false);
        btnNhan1.setEnabled(false);
        btnNhan2.setEnabled(false);
        ClientThread.runnable=false;
        ClientThread.webSource.release();
         form.setVisible(false);
        ////////////////---audio---
       start = false;
    }//GEN-LAST:event_btnNgatMouseClicked

    private void btnBatDauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatDauMouseClicked
       //////////////////////---Video---
       this.form=new VideoForm();
               form.setVisible(true);
      
      btnNgungVideo.setEnabled(true);
      btnBatDau.setEnabled(false);
      //////////////////////---audio---
      String connection = "/c/";
        send(connection.getBytes());
        start = true;
        init_audio();
    }//GEN-LAST:event_btnBatDauMouseClicked

    private void btnNgungVideoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNgungVideoMouseClicked
      /////////////////////---Video---
      ClientThread.runnable=false;
      ClientThread.webSource.release();
      form.setVisible(false);
       btnBatDau.setEnabled(true);
       btnNgungVideo.setEnabled(false);
      ////////////////---audio---
       start = false;
       
    }//GEN-LAST:event_btnNgungVideoMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
         ten=args[0];
         id=args[1];
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatDau;
    private javax.swing.JButton btnKetNoi;
    private javax.swing.JButton btnNgat;
    private javax.swing.JButton btnNgungVideo;
    private javax.swing.JButton btnNhan1;
    private javax.swing.JButton btnNhan2;
    private javax.swing.JButton btnSend;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea list;
    private javax.swing.JTextField txtIP;
    private javax.swing.JLabel txtMaPhongBan;
    private javax.swing.JTextField txtMessage;
    private javax.swing.JTextArea txtNoiDung;
    private javax.swing.JTextField txtPort;
    private static javax.swing.JLabel txtTenPhongBan;
    private javax.swing.JLabel txtTrangThai;
    // End of variables declaration//GEN-END:variables
}
