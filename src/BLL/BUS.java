/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.CreateXMLFile;
import DTO.PhongBan;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author ACER
 */
public class BUS {
  CreateXMLFile cxf=new CreateXMLFile();
    public void load(ArrayList<PhongBan> arr)
    {
      try {
          cxf.load(arr);
      } catch (ParserConfigurationException ex) {
           
          Logger.getLogger(BUS.class.getName()).log(Level.SEVERE, null, ex);
      } catch (SAXException ex) {
          Logger.getLogger(BUS.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
          Logger.getLogger(BUS.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    public void addPhongBan(PhongBan ph)
    {
        cxf.addPhongBan(ph);
    }
    
}
