/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.allforkids.gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import edu.allforkids.entities.Produits;
import edu.allforkids.services.CrudStore;
import java.util.Date;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author Narjes
 */
public class AjoutProduit {
    Form f;
    TextField tnomProd;
    TextField tprix;
    TextField tquantite;
    CheckBox c;
    TextField tdipso;
    Button image;
    TextField tcategorie;
    Button ajout;
    ComboBox cb1;
     String fileNameInServer;
   
    

    public AjoutProduit()  {
      f=new Form(new BoxLayout(BoxLayout.Y_AXIS));
      cb1=new	ComboBox();
      cb1.addItem("jouets");
      cb1.addItem("vetements");
     
     // f.setLayout(new FlowLayout(CENTER, CENTER));
      tnomProd=new TextField("Nom Produit");
      c=new CheckBox("Disponible");
      tprix=new TextField("Prix");
      tquantite=new TextField("Quantite");
     
     // tdipso=new TextField("Disponibilite");
      image=new Button("Image");
      image.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent evt) {
              try {
                  fileNameInServer="";
                  MultipartRequest cr = new MultipartRequest();
          String filepath= Capture.capturePhoto(-1, -1);
          cr.setUrl("http://localhost/ImagesProduit/uploadimage.php");
           cr.setPost(true);
                String mime = "image/jpeg";
cr.addData("file", filepath, mime);
 String out = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                cr.setFilename("file", out + ".jpg");//any unique name you want
                
                fileNameInServer += out + ".jpg";
                System.err.println("path2 =" + fileNameInServer);
                InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                cr.setDisposeOnCompletion(dlg);
NetworkManager.getInstance().addToQueueAndWait(cr);


                  System.out.println(fileNameInServer);
              } catch (Exception ex) {
                  
              }
              
          }
      });
    //  tcategorie=new TextField("Categorie");
      ajout=new Button("Ajouter");
      f.addAll(tnomProd,tprix,image,tquantite,cb1,ajout);
      ajout.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent evt) {
              CrudStore service=new CrudStore();
              Produits p=new Produits();
                    p.setCategorie(""+cb1.getSelectedItem());
                    p.setNom(tnomProd.getText());
                    System.out.println("ok");
                   // p.isDisponible();
                    System.out.println("dispo");
                    p.setImage(fileNameInServer);
                    p.setPrix(Float.parseFloat(tprix.getText()));
                    p.setQuantite(Integer.parseInt(tquantite.getText()));
                    p.setEtat("accepted");
                    
                    service.AjoutProd(p);
                    
                      
          }
      });
      f.show();
              
              
        
    }

    public Form getF() {
        return f;
    }
    
    
}
