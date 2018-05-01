/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.allforkids.gui;

import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.io.IOException;

/**
 *
 * @author Narjes
 */
public class HomeForm {

    Toolbar toolbar;
    Image logo;
    Form f1;

    public HomeForm() throws IOException {
        f1 = new Form();
        f1.setLayout(new FlowLayout(CENTER, CENTER));
        toolbar = f1.getToolbar();
        f1 = new Form();
        f1.setLayout(new FlowLayout(CENTER, CENTER));
        toolbar = f1.getToolbar();
        logo = Image.createImage("/logo.png");
        Container cn = BorderLayout.west(new Label(logo));
        //cn.add(BorderLayout.SOUTH, nom);
        toolbar.addComponentToSideMenu(cn);
        f1.add("welcome narjes");
        AfficherProduits2 ap = new AfficherProduits2();
        AjoutProduit AjoutP = new AjoutProduit();
        toolbar.addMaterialCommandToSideMenu("Boutique", FontImage.MATERIAL_STORE, e
                -> {

            ap.getF2().show();

        });
        toolbar.addMaterialCommandToSideMenu("Ajout Produit", FontImage.MATERIAL_ADD_SHOPPING_CART, e
                -> {

            AjoutP.getF().show();

        });
        
      
        
        
        f1.show();

    }

    public Form getF1() {
        return f1;
    }

}
