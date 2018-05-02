/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.allforkids.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

import edu.allforkids.entities.User;
import static edu.allforkids.gui.AfficheBabysitters.curent;
import edu.allforkids.services.ListeBabysitterCrud;
import java.io.IOException;
import java.util.ArrayList;
import jdk.nashorn.internal.parser.TokenType;



/**
 *
 * @author ASUS
 */
public class AfficheBabysitters {
   
    Form f;
    Form f2;
    SpanLabel lb;
    static User Baybisit= null;
    User baby;
    ListeBabysitterCrud userfind=new ListeBabysitterCrud();
    static int curent =Login.idUser;
   
        private Resources theme;
  
    public AfficheBabysitters() throws IOException {
        theme = UIManager.initFirstTheme("/theme");
        User u =userfind.getUser(curent);
        
        
        f = new Form("Liste des Babysitter");
        lb = new SpanLabel("");
        f.add(lb);
        
        ListeBabysitterCrud bb=new ListeBabysitterCrud();
        ArrayList<User> lis=bb.getList2();
        for (User l : lis){
            Container c1= new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label nom= new Label(l.getUsername());
            Label age=new Label(""+l.getAge());
            Label Etat=new Label(l.getEtat());
            Label Ville = new Label(l.getVille());
            Label adresse = new Label(l.getAdresse());
            Label nbr = new Label(""+l.getNbrAnneeExp());
            Label num = new Label(""+l.getNumTe());
            Label sexe = new Label(l.getSexe());
            Label email = new Label(l.getEmail());
            ImageViewer img=new ImageViewer();
            Image placeholder = Image.createImage(200,200,0xbfc92d);
            EncodedImage encImage=EncodedImage.createFromImage(placeholder, false);
            Image imgs= URLImage.createToStorage(encImage, l.getNom_image(),"http://localhost/PI4/web/images/"+l.getNom_image(),URLImage.RESIZE_SCALE);
            img.setImage(imgs);
            System.out.println(imgs);
            c1.add(img);
            c1.add(nom);
            c1.add(age);
            c1.add(Etat);
            c1.add(Ville);
            c1.add(adresse);
            c1.add(nbr);
            c1.add(num);
            c1.add(sexe);
            c1.add(email);
            Button mail = new Button("Envoyer mail");
            Button avis = new Button(" Les Avis");
            Button offre = new Button("Envoyer un Offre");
            c1.add(mail);
            c1.add(avis);
            c1.add(offre);
            
            avis.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    try {
                        curent=u.getId();
                        Baybisit=l;
                        setBaybisit(l);
                        AfficheAvis a= new AfficheAvis();
                        a.getF().show();
                    } catch (IOException ex) {
                        System.out.println("Error");;
                    }
                }
            });
            
            mail.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    try {
                        Form f2= new Form("Envoyer un mail",new BoxLayout(BoxLayout.Y_AXIS));
                        Label s=new Label("Sujet:");
                        TextField sub = new TextField();
                        TextArea mess = new TextArea(5,6);
                        mess.getAllStyles().setFgColor(0xff0000);
                        Button envmail=new Button("Envoyer");
                        f2.add(s);
                        f2.add(sub);
                        f2.add(mess);
                        f2.add(envmail);
                        envmail.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                Message m= new Message(mess.getText());
                                Display.getInstance().sendMessage(new String[] {l.getEmail()},sub.getText(),m);
                            }
                        });
                        
                        
                        f2.show();
                        AfficheBabysitters h = new AfficheBabysitters();
                        
                        f2.getToolbar().addCommandToLeftBar("Back",theme.getImage("back-command.png"),e->{h.getF().show();});
                    } catch (IOException ex) {
                        System.out.println("Error!! ");;
                    }
                }
            });
            
            offre.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    curent=u.getId();
                    Baybisit=l;
                    setBaybisit(l);
                    AfficheOffre o;
                    try {
                        o = new AfficheOffre();
                    
                    o.getF().show();
                    } catch (IOException ex) {
                        System.out.println("Error");
                    }
                }
            });
            
            
            
            
            f.add(c1);
          
            
        }
        f.getToolbar().addCommandToOverflowMenu("Mes Offres",theme.getImage("back-command.png"), d -> {
            try {
                TousLesOffres tt=new TousLesOffres();
                curent=u.getId();
                
                tt.getF().show();
            } catch (IOException ex) {
                System.out.println("Error !!");
            }
                                        
            });
        
         
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public User getBaybisit() {
        return Baybisit;
    }

    public void setBaybisit(User Baybisit) {
        this.Baybisit = Baybisit;
    }
    
    


   
    
}
