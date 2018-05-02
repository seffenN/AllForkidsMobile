/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.allforkids.gui;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import edu.allforkids.entities.Offre;
import edu.allforkids.entities.User;
import edu.allforkids.services.ListeBabysitterCrud;
import edu.allforkids.services.OffreCrud;
import java.io.IOException;


/**
 *
 * @author ASUS
 */
public class AfficheOffre {

    Form f;
    AfficheBabysitters Listeb=new AfficheBabysitters();
    User baby=AfficheBabysitters.Baybisit;
    private Resources theme;
    
    public AfficheOffre() throws IOException{
       User b= baby;
       int u=Listeb.curent;

     theme = UIManager.initFirstTheme("/theme");
       
        OffreCrud offre = new OffreCrud();
     ListeBabysitterCrud bb=new ListeBabysitterCrud();
     AfficheBabysitters affb=new AfficheBabysitters();
     f = new Form(b.getUsername(),new BoxLayout(BoxLayout.Y_AXIS));
        Container c= new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label phrase=new Label("Remplir le formulaire pour envoyer un offre");
        Label nbrEnflab=new Label("Nombre d'enfant");
        TextField nbrEnf=new TextField();
        Label Servlab=new Label("Service demandé");
        TextField Serv= new TextField();
        Label Agelab= new Label("Age de cadé");
        TextField Age=new TextField();
        Label datelabel=new Label("Date de l'offre");
        Picker dateo=new Picker();
        Label tempsLab = new Label("le temps demandé");
         ComboBox temps=new ComboBox(new String[]{"matin","aprés midi","soire","tout au long du jour"});
        Label VilleLab = new Label("Votre ville");
        ComboBox ville=new ComboBox(new String[]{"Ariana","Beja","Ben Arous",
            "Bizerte","Gabes","Gafsa","Jendouba","Kairouan","Kasserine",
                "Kebili","Le Kef","Mahdia","La Manouba","Medenine",
                "Monastir","Nabeul","Sfax","Sidi Bouzid","Siliana","Sousse",
                "Tataouine","Tozeur","Tunis","Zaghouan"});
        Button envoyer = new Button("Envoyer");
        c.add(phrase);
        c.add(nbrEnflab);
        c.add(nbrEnf);
        c.add(Servlab);
        c.add(Serv);
        c.add(Agelab);
        c.add(Age);
        c.add(datelabel);
        c.add(dateo);
        c.add(tempsLab);
        c.add(temps);
        c.add(VilleLab);
        c.add(ville);
        c.add(envoyer);
        
        envoyer.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
               int nbr =Integer.parseInt(nbrEnf.getText());
               int ag=Integer.parseInt(Age.getText());
                  if (nbrEnf.getText()==""){
                        Dialog.show("Erreur", "Vous devez Saisir le nombre des enfants", "ok", null);
                    }
                  if (Serv.getText()==""){
                        Dialog.show("Erreur", "Vous devez Saisir Le Service demandé", "ok", null);
                    }
                  if (Age.getText()==""){
                        Dialog.show("Erreur", "Vous devez Saisir L'age de l'enfant cadé", "ok", null);
                    }
                  
                  


               String stringdate = new SimpleDateFormat("yyyy-MM-dd").format(dateo.getDate());
               String tempsstring = (String) temps.getSelectedItem();
               String villestring = (String) ville.getSelectedItem();
               Offre o = new Offre(0,u,b.getId(),nbr,Serv.getText(),ag,stringdate,tempsstring,0,villestring);
               System.out.println(o);
            offre.ajoutOffre(o);

           }
       });
        
        f.add(c);
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
