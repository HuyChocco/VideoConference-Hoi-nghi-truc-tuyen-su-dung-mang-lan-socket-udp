/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.PhongBan;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import videoconference.Client;

/**
 *
 * @author ACER
 */
public class CreateXMLFile {
    static String filepath="src/Database/data.xml";
    public static void main(String[] args) throws TransformerConfigurationException {
        try{
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        DocumentBuilder db=dbf.newDocumentBuilder();
        Document doc=db.newDocument();
        
        Node root=doc.createElement("congty");
        doc.appendChild(root);
        PhongBan ph1=new PhongBan("002","Tài chính","123");
        PhongBan ph2=new PhongBan("003","Kế Toán","12345");
        
      //  addPhongBan(doc,root,ph1);
       // addPhongBan(doc,root,ph2);
        
        
        TransformerFactory tff=TransformerFactory.newInstance();
        Transformer tf=tff.newTransformer();
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        
        DOMSource source=new DOMSource(doc);
        StreamResult dest=new StreamResult("src/Database/data.xml");
        tf.transform(source, dest);
        
       
       // System.out.println(arr.size());
        }
        catch(ParserConfigurationException ex)
        { System.out.print(ex.toString());
              java.util.logging.Logger.getLogger(CreateXMLFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(CreateXMLFile.class.getName()).log(Level.SEVERE, null, ex);
        
        } 
        
    }
    public  void  addPhongBan(PhongBan ph)
    {
        try {
            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
            DocumentBuilder db=dbf.newDocumentBuilder();
             Document doc=db.parse(filepath);
            Element root=doc.getDocumentElement();
            
            Element phongban=doc.createElement("phongban");
           
            Element ten=doc.createElement("ten");
            Element mk=doc.createElement("matkhau");
            
            Text tten=doc.createTextNode(ph.getTen());
            Text tmk=doc.createTextNode(ph.getMatkhau());
            
            ten.appendChild(tten);
            mk.appendChild(tmk);
            phongban.appendChild(ten);
            phongban.appendChild(mk);
            phongban.setAttribute("id", ph.getId());
            
            root.appendChild(phongban);
          
              TransformerFactory tff=TransformerFactory.newInstance();
        Transformer tf=tff.newTransformer();
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        
        DOMSource source=new DOMSource(doc);
        StreamResult dest=new StreamResult("src/Database/data.xml");
        tf.transform(source, dest);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CreateXMLFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(CreateXMLFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(CreateXMLFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateXMLFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public  void load(ArrayList<PhongBan> ph) throws ParserConfigurationException, SAXException, IOException
    {
         DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        DocumentBuilder db=dbf.newDocumentBuilder();
        Document doc=db.parse(filepath);
        NodeList listPhongBan=doc.getElementsByTagName("phongban");
       
        NodeList listTenPhong=doc.getElementsByTagName("ten");
        
        NodeList listMatKhau=doc.getElementsByTagName("matkhau");
        for(int i=0;i<listPhongBan.getLength();i++)
        {
            PhongBan phong=new PhongBan();
            phong.setId(listPhongBan.item(i).getAttributes().getNamedItem("id").getNodeValue());
            phong.setTen(listTenPhong.item(i).getTextContent());
            phong.setMatkhau(listMatKhau.item(i).getTextContent());
            ph.add(phong);
            //System.out.println(ph.get(i).getId());
        }
        
    }
    public void ghiFile()
    {
        try {
            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
            DocumentBuilder db=dbf.newDocumentBuilder();
            Document doc=db.newDocument();
            TransformerFactory tff=TransformerFactory.newInstance();
        Transformer tf=tff.newTransformer();
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        
        DOMSource source=new DOMSource(doc);
        StreamResult dest=new StreamResult("src/Database/data.xml");
        tf.transform(source, dest);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CreateXMLFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(CreateXMLFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
