/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.allforkids.gui;

import com.codename1.db.Database;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import static com.codename1.ui.TextArea.PASSWORD;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import edu.allforkids.entities.User;
import edu.allforkids.services.CrudUser;
import java.util.ArrayList;

/**
 *
 * @author Narjes
 */
public class Login {
     private EncodedImage ei;
    private Label l;
    Database db;
    boolean created = false;
    public float prix = 0;
    public int idProduit;
    public String imageProd;
    public int quantite;
    Toolbar toolbar;

    public Login() {
        
        TextField login = new TextField();
        TextField mdp = new TextField();

        l = new Label();

        mdp.setConstraint(PASSWORD);
        Button b = new Button("Se Connecter");
        Form hi = new Form();
        hi.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        hi.add(login);
        hi.add(mdp);
        hi.add(b);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String n = login.getText();
                    String p = mdp.getText();
                     CrudUser userService=new CrudUser();
                    ArrayList<User> list=userService.Login(n,p);
                    String nom="";
                     String mdp="";
                    for(int i=0;i<list.size();i++){
                         nom=list.get(i).getUsername();
                         mdp=list.get(i).getPassword();
                    }
                    System.out.println(nom);
                    
                    //f2.getToolbar().add(new Button("afficher panier"));
                     if (n.equals(nom) && p.equals(mdp)) {
                          Form f1 = new Form();
                    f1.setLayout(new FlowLayout(CENTER, CENTER));
                      toolbar = f1.getToolbar();
                    //Image logo = theme.getImage("logo.png");
                   // Container cn = BorderLayout.west(new Label(logo));
                   // cn.add(BorderLayout.SOUTH, "narjes");
                   // toolbar.addComponentToSideMenu(cn);
                    f1.add("welcome narjes");
                    f1.show();
                     }else{
                         Dialog.show("Probléme", "Mot de passe ou login faux", "ok", "cancel");
                     }
                
            }
        });
    }
    
    
}
