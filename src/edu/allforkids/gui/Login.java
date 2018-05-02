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
import com.codename1.ui.FontImage;
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
import com.codename1.ui.util.Resources;
import edu.allforkids.entities.User;
import edu.allforkids.services.CrudUser;
import java.io.IOException;
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
    public static int idUser;
   public static String nom ="";
  

    public Login(Resources rs) {

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
                CrudUser userService = new CrudUser();
                ArrayList<User> list = userService.Login(n, p);
                      String mdp ="";
                for (int i = 0; i < list.size(); i++) {
                    nom = list.get(i).getUsername();
                    mdp = list.get(i).getPassword();
                    idUser = list.get(i).getId();
                    
                }
                System.out.println(nom);
                System.out.println(idUser);

                //f2.getToolbar().add(new Button("afficher panier"));
                if (n.equals(nom) && p.equals(mdp)) {
                    try {
                        HomeForm hf = new HomeForm();
                    } catch (IOException ex) {

                    }

                } else {
                    Dialog.show("Probléme", "Mot de passe ou login faux", "ok", "cancel");
                }

            }
        });
        hi.show();
    }

}
