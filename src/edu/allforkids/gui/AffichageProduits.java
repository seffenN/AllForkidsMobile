/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.allforkids.gui;

import com.codename1.components.ImageViewer;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import edu.allforkids.entities.Commande;
import edu.allforkids.entities.Produits;
import edu.allforkids.entities.ligne_commandes;
import edu.allforkids.services.CrudStore;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Narjes
 */
public class AffichageProduits {

    private EncodedImage ei;

    Database db;
    boolean created = false;

    Form f2;
    Form f;
    CrudStore service = new CrudStore();

    public AffichageProduits() throws IOException {
        f = new Form("Panier Liste", new BoxLayout(BoxLayout.Y_AXIS));

        f2 = new Form();
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Button affiche = new Button("Afficher Panier");
        c1.add(affiche);
        
        ArrayList<Produits> array = service.getAllProducts();
        created = Database.exists("allforkids");
        System.out.println("ok1");
        try {
            db = Database.openOrCreate("allforkids");
            System.out.println("ok2");
            if (created == false) {
                db.execute("CREATE TABLE PANIER (idProduit INTEGER,nomProd VARCHAR,nbrArticle INTEGER,Prix REAL )");
                System.out.println("ok");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        for (int i = 0; i < array.size(); i++) {

            //String maphoto = "";
            Produits prod = array.get(i);
            Container eq1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container eq2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ImageViewer image = new ImageViewer();
            Image placeholder = Image.createImage(100, 100, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            Image img = URLImage.createToStorage(encImage, prod.getImage(), "http://localhost/PI4/web/bundles/images/" + prod.getImage(), URLImage.RESIZE_SCALE);
            image.setImage(img);
            img.getWidth();
            eq2.add(image);
            Container eq3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label lb = new Label(prod.getNom());
           // System.out.println(lb.getText());
            //Button b=new Button("Ajout au Panier");
            // b.setSize(new Dimension(5,5));
            Label lb3 = new Label("" + prod.getId());
            Label qt=new Label(""+ prod.getQuantite());
            //System.out.println(qt.getText());
            Label lb2 = new Label("Prix :" + prod.getPrix());
            Button ap = new Button("Ajout au Panier");
            eq3.add(lb);

            // eq1.add(b);
            eq3.add(lb2);
            eq3.add(ap);
            // eq3.add(ap);
            eq1.add(eq2);
            eq1.add(eq3);
            c1.add(eq1);
            // c1.add(eq2);

            ap.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    try {
                       // ap.setUIID("true");
                        String id = lb3.getText();
                        String nomProd = lb.getText();
                        String prix=lb2.getText();
                        //int x =Integer.parseInt(prix);
                        
                        //System.out.println(prix);
                        String quant=qt.getText();
                        System.out.println(quant);
                        //System.out.println(nomProd);
                             
                        db.execute("INSERT into 'PANIER' (idProduit,nomProd,nbrArticle,Prix) VALUES(" + id + ",'" + nomProd + "'," +quant + "," +10.00+ ")");
                        System.out.println("ajoutttt");
                     
                             

                    } catch (IOException ex) {
                    }

                }
            });

        }

        f2.add(c1);
        affiche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                try {
                   
                    Cursor cr = db.executeQuery("SELECT * FROM PANIER");
                   
                    while (cr.next()) {
                        Label mylabel = new Label();
                        Row r = cr.getRow();
                        // System.out.println(r.getString(2));
                        mylabel.setText(r.getString(0) + "" + r.getString(1)+""+r.getString(2));
                        f.add(mylabel);
                        

                        //Database.delete("payseve");
                    }

                } catch (IOException ex) {

                }

                f.show();

            }

        });
        Button supp = new Button("valider");
        f.add(supp);
        supp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                   
                  System.out.println("ok");
                    //db.close();
                    //Database.delete("paysevekids");
                    Commande c=new Commande();
                    
                    
                    //c.setDateCommande();
                    c.setIdClient(4);
                    service.AjoutCommande(c);
                    System.out.println("ajout avec succes");
                    Cursor cr = db.executeQuery("SELECT * FROM PANIER");
                     while (cr.next()) {
                          Row r = cr.getRow();
                          ligne_commandes l=new ligne_commandes();
                          l.setId_produit(Integer.parseInt(r.getString(0)));
                          System.out.println(r.getString(0));
                          //service.LastCommande(0);
                          l.setId_commande(11);
                          l.setPrix_commande(Float.parseFloat(r.getString(3)));
                          l.setQuantite(Integer.parseInt(r.getString(2)));
                          service.AjoutLigneCommande(l);
                          System.out.println("ajout ligne commande");
                          
                         
                     }
                   
                   
                    
                    db.execute("DELETE FROM PANIER ");
                   
                        System.out.println("fasakhh");
                } catch (IOException ex) {
                }
            }
        });

    }

    public Form getF2() {
        return f2;
    }

    public void setF2(Form f2) {
        this.f2 = f2;
    }

    public Form getF() {
        return f;
    }

}
