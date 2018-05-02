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
import com.codename1.ui.Display;
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
public class ModifOffre {
    
    
    Form f;
    User baby;
    Offre o;
    int curent;
    private Resources theme;
    
    
   public ModifOffre() throws IOException{
       AfficheBabysitters afb=new AfficheBabysitters();
       baby=TousLesOffres.Baybisit;
       o=TousLesOffres.poffre;
       curent=afb.curent;
       
       User b=baby ;
       
     theme = UIManager.initFirstTheme("/theme");
       
        OffreCrud offre = new OffreCrud();
     ListeBabysitterCrud bb=new ListeBabysitterCrud();
     
     f = new Form(b.getUsername(),new BoxLayout(BoxLayout.Y_AXIS));
        Container c= new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label phrase=new Label("Modifier le formulaire pour envoyer un offre");
        Label nbrEnflab=new Label("Nombre d'enfant");
        TextField nbrEnf=new TextField(""+o.getNbrEnfant());
        Label Servlab=new Label("Service demandé");
        TextField Serv= new TextField(o.getService());
        Label Agelab= new Label("Age de cadé");
        TextField Age=new TextField(""+o.getAge());
        Label datelabel=new Label("Date de l'offre");
        Picker dateo=new Picker();
        dateo.setType(Display.PICKER_TYPE_DATE);
        Label tempsLab = new Label("le temps demandé");
         ComboBox temps=new ComboBox(new String[]{"matin","aprés midi","soire","tout au long du jour"});
        Label VilleLab = new Label("Votre ville");
        ComboBox ville=new ComboBox(new String[]{"Ariana","Beja","Ben Arous",
            "Bizerte","Gabes","Gafsa","Jendouba","Kairouan","Kasserine",
                "Kebili","Le Kef","Mahdia","La Manouba","Medenine",
                "Monastir","Nabeul","Sfax","Sidi Bouzid","Siliana","Sousse",
                "Tataouine","Tozeur","Tunis","Zaghouan"});
        temps.setSelectedItem(o.getTemps());
        ville.setSelectedItem(o.getVille());
        Button envoyer = new Button("Modifier");
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
               try {
                   
                   
                   if (nbrEnf.getText()==""){
                        Dialog.show("Erreur", "Vous devez Saisir le nombre des enfants", "ok", null);
                    }
                   else{
                       int nbr =Integer.parseInt(nbrEnf.getText());
                   if (nbr<1 && nbr>50){
                        Dialog.show("Erreur", "Vous devez Saisir le nombre des enfants", "ok", null);
                    }}
                   
                  if (Serv.getText()==""){
                        Dialog.show("Erreur", "Vous devez Saisir Le Service demandé", "ok", null);
                    }
                  if (Age.getText()==""){
                        Dialog.show("Erreur", "Vous devez Saisir L'age de l'enfant cadé", "ok", null);
                    }
                  else {
                      int ag =Integer.parseInt(Agelab.getText());
                      if (ag<1 && ag>13){
                        Dialog.show("Erreur", "Vous devez Saisir le nombre des enfants", "ok", null);
                    }
                  }
                  int ag =Integer.parseInt(Agelab.getText());
                  int nbr =Integer.parseInt(Agelab.getText());
                   String stringdate = new SimpleDateFormat("yyyy-MM-dd").format(dateo.getDate());
                   String tempsstring = (String) temps.getSelectedItem();
                   String villestring = (String) ville.getSelectedItem();
                   Offre off = new Offre(o.getId(),o.getParent_id(),o.getBabysitter_id(),nbr,Serv.getText(),ag,stringdate,tempsstring,0,villestring);
                   System.out.println(off);
                   offre.UpdateOffre(off);
                   Dialog.show("Succés", "Offre Modifié", "ok", null);
                   TousLesOffres tt=new TousLesOffres();
                   curent=afb.curent;
                   
                   tt.getF().show();
               } catch (IOException ex) {
                   System.out.println("ERRooooooooooooo!!");
               }

               

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
    

