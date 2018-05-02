/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.allforkids.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import edu.allforkids.entities.Avis;
import edu.allforkids.entities.User;
import static edu.allforkids.gui.AfficheBabysitters.Baybisit;
import edu.allforkids.services.AvisCrud;
import edu.allforkids.services.ListeBabysitterCrud;
import java.io.IOException;
import java.util.ArrayList;





/**
 *
 * @author ASUS
 */
public class AfficheAvis {
    Form f;
    User baby=AfficheBabysitters.Baybisit;
    private Resources theme;
    int curent;
    
    public AfficheAvis() throws IOException{
        theme = UIManager.initFirstTheme("/theme");
       User b= baby;
     AfficheBabysitters afb=new AfficheBabysitters();
     curent=afb.curent;
       
     AvisCrud avis = new AvisCrud();
     ListeBabysitterCrud bb=new ListeBabysitterCrud();
        
    f = new Form(b.getUsername(),new BoxLayout(BoxLayout.Y_AXIS));
    Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    Container c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));               
    Container c4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));               
    ImageViewer img2;
                   Label nom2= new Label(b.getUsername());
                   Label age2=new Label(""+b.getAge());
                   Label Etat2=new Label(b.getEtat());
                   Label Ville2 = new Label(b.getVille());
                   Label adresse2 = new Label(b.getAdresse());
                   Label nbr2 = new Label(""+b.getNbrAnneeExp());
                   Label num2 = new Label(""+b.getNumTe());
                   Label sexe2 = new Label(b.getSexe());
                   Label email2 = new Label(b.getEmail());
                   img2= new ImageViewer();
                   Image placeholder2 = Image.createImage(200,200,0xbfc92d);
                   EncodedImage encImage2=EncodedImage.createFromImage(placeholder2, false);
                   Image imgss= URLImage.createToStorage(encImage2, b.getNom_image(),"http://localhost/PI4/web/images/"+b.getNom_image(),URLImage.RESIZE_SCALE);
                   img2.setImage(imgss);
                   
                   
                    
        ArrayList<Avis> ListAv=avis.getListAv(b.getId());
        
        System.err.println(ListAv.size());
        if (ListAv!=null )
        {
        for (Avis lesavis : ListAv){
        Container c5= new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label name =new Label(bb.getUser(lesavis.getId_parent_id()).getUsername());
        name.getAllStyles().setFgColor(0xff0000);
        Label MyAvis=new Label(lesavis.getMonAvis());
        Button avism= new Button("Modifier");
        c5.add(name);
        c5.add(MyAvis);
        c5.add(avism);
        
        c5.getAllStyles().setBorder(Border.createBevelLowered(2, 2, 2, 2));
        c4.add(c5);
        avism.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Dialog d=new Dialog(" Ton Avis");
                Label labelmodif=new Label("Modifier Votre avis");
                TextArea t=new TextArea(6,15);
                t.getAllStyles().setBgColor(0x000000);
                t.setText(lesavis.getMonAvis());
                Button save = new Button("Modifier");
                d.add(labelmodif);
                d.add(t);
                d.add(save);
                
                save.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (t.getText()==""){
                        Dialog.show("Erreur", "Vous devez Ajouter un avis avant de cliquer sur Ajouter", "ok", null);
                    }
                        else {
                        try {
                            Avis av = new Avis(lesavis.getId(),lesavis.getId_parent_id(),lesavis.getId_babysitter_id(),t.getText());
                            System.out.println(av);
                            avis.UpdateAvis(av);
                            baby=b;
                            AfficheAvis ab =new AfficheAvis();
                            ab.getF().show();
                        } catch (IOException ex) {
                            System.out.println("EROOOOOR MODIF AVIS");
                        }
                        }
                    }
                });
                d.show();
            }
        });
        }
        
                }
                   
                    TextArea monavis=new TextArea(5, 10);
                   
                    monavis.getStyle().setFgColor(0x0000ff);
                    monavis.getStyle().setBgColor(0xf5deb3);
                    Button ajouterav =new Button("Ajouter");
                   c3.add(img2);
                   c3.add(nom2);
                   c3.add(age2);
                   c3.add(Etat2);
                   c3.add(Ville2);
                   c3.add(adresse2);
                   c3.add(nbr2);
                   c3.add(num2);
                   c3.add(sexe2);
                   c3.add(email2);
                   
                   c2.add(monavis);
                   c2.add(ajouterav);
                   f.add(c3);
                   f.add(c4);
                   f.add(c2);
                   
            f.show();
            ajouterav.addActionListener(new ActionListener() {
                       

         @Override
         public void actionPerformed(ActionEvent evt) {
             if (monavis.getText()==""){
                        Dialog.show("Erreur", "Vous devez Ajouter un avis avant de cliquer sur Ajouter", "ok", null);
                    }
             else{
             
             try {
                 Avis a = new Avis(0,curent,b.getId(),monavis.getText());
                 avis.ajoutAv(a);
                 baby=b;
                 AfficheAvis ab =new AfficheAvis();
                 ab.getF().show();
             } catch (IOException ex) {
                 System.out.println("Error");;
             }
            }
         }
                    });
     
         AfficheBabysitters h = new AfficheBabysitters();
        
        f.getToolbar().addCommandToLeftBar("Back",theme.getImage("back-command.png"),e->{h.getF().show();});
        
       
       
  }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }



}
