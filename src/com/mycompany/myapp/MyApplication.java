package com.mycompany.myapp;

import com.codename1.components.ImageViewer;
import com.codename1.db.Database;

import static com.codename1.ui.CN.*;

import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;

import com.codename1.ui.Toolbar;

import com.codename1.ui.layouts.BoxLayout;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import static com.codename1.ui.TextArea.PASSWORD;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import edu.allforkids.entities.Produits;
import edu.allforkids.entities.User;
import edu.allforkids.gui.AffichageProduits;
import edu.allforkids.gui.AfficherProduits2;
import edu.allforkids.gui.AjoutProduit;

import edu.allforkids.services.CrudStore;
import edu.allforkids.services.CrudUser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.List;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename
 * One</a> for the purpose of building native mobile applications using Java.
 */
public class MyApplication {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        // use two network threads instead of one
        updateNetworkThreadCount(2);

        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if (err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        });
    }
    private EncodedImage ei;
    private Label l;
    Database db;
    boolean created = false;
    public float prix = 0;
    public int idProduit;
    public String imageProd;
    public int quantite;
    Toolbar toolbar;
    Form f1;
    

    public void start() {
        if (current != null) {
            current.show();
            return;
        }

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

                // try {
                String n = login.getText();
                String p = mdp.getText();
                CrudUser userService = new CrudUser();
                ArrayList<User> list = userService.Login(n, p);
                String nom = "";
                String mdp = "";
                for (int i = 0; i < list.size(); i++) {
                    nom = list.get(i).getUsername();
                    mdp = list.get(i).getPassword();
                }
                System.out.println(nom);

                //f2.getToolbar().add(new Button("afficher panier"));
                if (n.equals(nom) && p.equals(mdp)) {
                    f1 = new Form();
                    f1.setLayout(new FlowLayout(CENTER, CENTER));
                    toolbar = f1.getToolbar();
                    Image logo = theme.getImage("logo.png");
                    Container cn = BorderLayout.west(new Label(logo));
                    cn.add(BorderLayout.SOUTH, "narjes");
                    toolbar.addComponentToSideMenu(cn);
                    f1.add("welcome narjes");
                    f1.show();
                    AfficherProduits2 ap = new AfficherProduits2();
    AjoutProduit AjoutP = new AjoutProduit();
                    toolbar.addMaterialCommandToSideMenu("Boutique", FontImage.MATERIAL_STORE, e
                            -> {

                        ap.getF2().show();

                    });
                    toolbar.addMaterialCommandToSideMenu("Boutique", FontImage.MATERIAL_CHECK_BOX, e
                            -> {

                        AjoutP.getF().show();

                    });
                     ap.getF2().getToolbar().addCommandToLeftBar("Back", theme.getImage("back-command.png"), e -> {
            f1.show();
        });
        AjoutP.getF().getToolbar().addCommandToLeftBar("Back", theme.getImage("back-command.png"), e -> {
            f1.show();
        });
        ap.getF().getToolbar().addCommandToLeftBar("Back", theme.getImage("back-command.png"), e -> {
            ap.getF2().show();
        });
                } else {
                    Dialog.show("Probléme", "Mot de passe ou login faux", "ok", "cancel");
                }
            }
        });

       

      

        // } catch (IOException ex) {
        //  }
        hi.show();

    }

    public void stop() {
        current = getCurrentForm();
        if (current instanceof Dialog) {
            ((Dialog) current).dispose();
            current = getCurrentForm();
        }
    }

    public void destroy() {
    }

}
