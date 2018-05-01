/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.allforkids.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.db.Database;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.MyApplication;
import edu.allforkids.entities.Commande;
import edu.allforkids.entities.Produits;
import edu.allforkids.entities.ligne_commandes;
import edu.allforkids.services.CrudStore;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 *
 * @author Narjes
 */
public class AfficherProduits2 {

    Form f2;
     int idcom=0;
    
     Label nomProds;
    Form f;
    CrudStore service = new CrudStore();
    private EncodedImage ei;
    public static List<Produits> p = new ArrayList<>();
    int occ = 0;
 TextField nvr;
    public AfficherProduits2() {
        Form coman=new Form("Vos Commandes", new BoxLayout(BoxLayout.Y_AXIS));
        f2 = new Form();
        f = new Form("Panier Liste", new BoxLayout(BoxLayout.Y_AXIS));
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container panier = new Container(new BoxLayout(BoxLayout.Y_AXIS));
         Container Commandes = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container VoirCommande = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Button affiche = new Button("Afficher Panier");
       // affiche.setVisible(false);
        c1.add(affiche);

        ArrayList<Produits> array = service.getAllProducts();

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
            Label qt = new Label("" + prod.getQuantite());
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
            ap.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    // ap.setUIID("true");
                       
                    if (p.isEmpty()) {
                        p.add(prod);
                          Dialog d = new Dialog("Produit Ajoute Avec succés");
            
            TextArea popupBody = new TextArea("Produit Ajoute Avec succés");
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(true);
            d.setLayout(new BorderLayout());
            d.add(BorderLayout.CENTER, popupBody);
            d.show("Panier","Produit Ajoute Avec succés","OK",null);
                        
                        System.out.println("ajouta" + prod.getId());
                    } else {
                        for (int i = 0; i < p.size(); i++) {
                            if (p.get(i).getId() == prod.getId()) {
                                occ++;
                            }
                        }
                        if (occ == 0) {
                            p.add(prod);
                            System.out.println("tawaajouta " + prod.getId());
 Dialog d = new Dialog("Produit Ajoute Avec succés");
            
            TextArea popupBody = new TextArea("Produit Ajoute Avec succés");
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(true);
            d.setLayout(new BorderLayout());
            d.add(BorderLayout.CENTER, popupBody);
            d.show("Panier","Produit Ajoute Avec succés","OK",null);
                        }
                    

                    }
                    
                }

            });
        }

        f2.add(c1);
        affiche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                for (int i = 0; i < p.size(); i++) {
                    Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container c4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    ImageViewer image = new ImageViewer();
                    Image placeholder = Image.createImage(100, 100, 0xbfc9d2);
                    EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                    Image img = URLImage.createToStorage(encImage, p.get(i).getImage(), "http://localhost/PI4/web/bundles/images/" + p.get(i).getImage(), URLImage.RESIZE_SCALE);
                    image.setImage(img);
                    img.getWidth();
                    c4.add(image);
                    Container c5 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Label lb = new Label(p.get(i).getNom());
                    Label lb3 = new Label("" + p.get(i).getId());
                    Label qt = new Label("" + p.get(i).getQuantite());
                    Label lb2 = new Label("Prix :" + p.get(i).getPrix());
                    nvr=new TextField();
                   // nvr.setUIID("1");
                    c5.add(lb);
                     c5.add(nvr);
                    c5.add(lb2);
                    c3.add(c4);
                    c3.add(c5);
                    panier.add(c3);
                }
                Button Commander = new Button("Commander");
                Button VoirCommande =new Button("Voir Commande");
                VoirCommande.setVisible(false);
                panier.add(Commander);
                panier.add(VoirCommande);
                
                
                Commander.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        System.out.println(nvr.getText());
                        Commande c = new Commande();
                        c.setIdClient(Login.idUser);
                        service.AjoutCommande(c);
                        System.out.println("ajout avec succes");
                         ArrayList<Commande> listCommande=service.getAllCommande();
                        
                         for(int k=0;k<listCommande.size();k++){
                              idcom=listCommande.get(k).getIdCommande();
                            
                         }
                          System.out.println(idcom);
                          
                         
                        for (int i = 0; i < p.size(); i++) {
                          
                            ligne_commandes l=new ligne_commandes();
                          l.setId_produit(p.get(i).getId());
                        
                           l.setId_commande(idcom);
                           float prixTot=p.get(i).getPrix()*Integer.parseInt(nvr.getText());
                          l.setPrix_commande(prixTot);
                         
                          l.setQuantite(Integer.parseInt(nvr.getText()));
                         service.AjoutLigneCommande(l);
                          System.out.println("ajout ligne commande");
                        }
                        
                        VoirCommande.setVisible(true);
                        Commander.setVisible(false);
                        VoirCommande.addActionListener(new ActionListener() {
                            
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                System.out.println(idcom);
                                ArrayList<ligne_commandes> listLC=service.getAllLigneCommande(idcom);
                                
                                for(ligne_commandes item:listLC){
                                     Container lis = new Container(new BoxLayout(BoxLayout.X_AXIS));
                                     Container lis1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                                     Container lis2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                                     Container lis3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                                    Label idCom=new Label("Numéro de Commande"+item.getId_commande());
                                    Label article=new Label("Nombre Article"+item.getQuantite());
                                    Label Prix=new Label("Prix"+item.getPrix_commande());
                                    ArrayList<Produits> listP=service.findProd(item.getId_produit());
                                    for(Produits itemP:listP){
                                         nomProds=new Label(itemP.getNom());
                                    }
                                   lis.add(idCom);
                                   lis1.add(article);
                                   lis2.add(Prix);
                                   lis3.add(nomProds);
                                  Commandes.addAll(lis,lis2,lis3,lis1);
                                  
                                   
                                }
                                coman.add(Commandes);
                                coman.show();
                                
                                
                                
                            }
                        });
                        
                        
                    }
                });

                f.show();

            }
        });
        f.add(panier);

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
