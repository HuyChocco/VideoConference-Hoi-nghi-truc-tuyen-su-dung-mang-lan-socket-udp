/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AudioThread;

import java.net.InetAddress;

/**
 *
 * @author ACER
 */
public class ServerDTO {
    public String name;
    public InetAddress address;
    public int port;
    public final int ID;
    public int attemp = 0;

    public ServerDTO(String name, InetAddress address, int port, final int ID) {
        this.name = name;
        this.address = address;
        this.port = port;
        this.ID = ID;
    }

    public ServerDTO(InetAddress address, int port, int ID) {
        this.address = address;
        this.port = port;
        this.ID = ID;
    }
    
    public int getID() {
        return this.ID;
    }
}
