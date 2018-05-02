/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.allforkids.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import edu.allforkids.entities.Offre;
import edu.allforkids.entities.User;
import edu.allforkids.services.ListeBabysitterCrud;
import edu.allforkids.services.OffreCrud;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author ASUS
 */
public class TousLesOffres {
    Form f;
    int curent;
    
    static Offre poffre;
    private Resources theme;
    static User Baybisit= null;
    
    public TousLesOffres() throws IOException{
        AfficheBabysitters afb=new AfficheBabysitters();
        curent=afb.curent;
        System.out.println(curent);
        poffre=null;
       theme = UIManager.initFirstTheme("/theme");
        OffreCrud oc=new OffreCrud();
        f = new Form("Mes Offres",new BoxLayout(BoxLayout.Y_AXIS));
    Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    
    ArrayList<Offre> ListOf=oc.getListOffre(curent);
        System.out.println(ListOf);
    if(ListOf!=null){
        for (Offre lesoffres : ListOf){
        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        c2.getAllStyles().setBorder(Border.createBevelLowered(2, 2, 2, 2));
        
        ListeBabysitterCrud bb = new ListeBabysitterCrud();
        SpanLabel babys=new SpanLabel("Babysitter: "+bb.getUser(lesoffres.getBabysitter_id()).getUsername());
        SpanLabel nbr=new SpanLabel("Nombre d'enfant: "+lesoffres.getNbrEnfant());
        SpanLabel Ser=new SpanLabel("Service demandé: "+lesoffres.getService());
        SpanLabel agee= new SpanLabel("Age de cadé: "+lesoffres.getAge());
        SpanLabel dat= new SpanLabel("Date de l'offre: "+lesoffres.getDateOffre());
        SpanLabel temp=new SpanLabel("Temps :"+lesoffres.getTemps());
        SpanLabel vil= new SpanLabel("Ville :"+lesoffres.getVille());
        
        String acc ="";
        if(lesoffres.getAccept()==0){  acc = "Etat: En Attente";}
        else 
        if(lesoffres.getAccept()==1){acc ="Etat: Accepté";}
        SpanLabel a= new SpanLabel(acc);
        Button modif =new Button("Modifier");
            
        c2.add(babys);
        c2.add(nbr);
        c2.add(Ser);
        c2.add(agee);
        c2.add(dat);
        c2.add(temp);
        c2.add(vil);
        c2.add(a);
        c2.add(modif);
        c1.add(c2);
        modif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                   
                    Baybisit=bb.getUser(lesoffres.getBabysitter_id());
                    System.out.println(Baybisit);
                    curent=afb.curent;
                    poffre=lesoffres;
                    ModifOffre m=new ModifOffre();
                    m.getF().show();
                } catch (IOException ex) {
                    System.out.println("Error !!!!! ");
                }
            }
        });
        
        
        }
                AfficheBabysitters h = new AfficheBabysitters();
        
        f.getToolbar().addCommandToLeftBar("Back",theme.getImage("back-command.png"),e->{h.getF().show();});

    }
    
    f.add(c1);
    
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
    
}
