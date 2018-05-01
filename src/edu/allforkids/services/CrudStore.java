/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.allforkids.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import edu.allforkids.entities.Commande;
import edu.allforkids.entities.Produits;
import edu.allforkids.entities.ligne_commandes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Narjes
 */
public class CrudStore {
   //njareeb feha
    public void AjoutProd(Produits p){
         ConnectionRequest con = new ConnectionRequest();
         String Url="http://localhost/PI4/web/app_dev.php/pi/prod/AjoutMobile/" +p.getPrix()+ "/" +p.getNom()+ "/" +p.getQuantite()+ "/" +p.getImage()+ "/" +p.getCategorie()+ "/" +p.isDisponible()+ "/" +p.getEtat();
                 
         System.out.println("produit ajout");
          con.setUrl(Url);
          
        con.addResponseListener((evt) -> {
             String str = new String(con.getResponseData());
            System.out.println(str);
        });
          NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public ArrayList<Produits> getAllProducts(){
         ArrayList<Produits> listProduits = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI4/web/app_dev.php/pi/prod/ListeMobile");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                  JSONParser jsonp = new JSONParser();
                  try{
                  Map<String, Object> prods = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(prods);
                   // System.out.println(prods);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) prods.get("root");
                    for (Map<String, Object> obj : list) {
                        Produits p = new Produits();
                       float id = Float.parseFloat(obj.get("id").toString());
                        float prix = Float.parseFloat(obj.get("prix").toString());
                        float quantite = Float.parseFloat(obj.get("quantite").toString());
                        
                       p.setId((int) id);
                       // p.setImage(obj.get("image").toString());
                        p.setNom(obj.get("nom").toString());
                        System.out.println(obj.get("nom").toString());
                        p.setEtat(obj.get("etat").toString());
                        p.setImage(obj.get("image").toString());
                        p.setPrix(prix);
                        p.setQuantite((int)quantite);
                        listProduits.add(p);

                    }
                } catch (IOException ex) {
                    
                }
                

           }
         });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProduits;
         
        
    }
    public void AjoutCommande(Commande c){
         ConnectionRequest connectionRequest=new ConnectionRequest(){
             @Override
            protected void postResponse() {
            Dialog d = new Dialog("Ajout Commande");
            
            TextArea popupBody = new TextArea("Commande Ajoute avec Succes");
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(true);
            d.setLayout(new BorderLayout());
            d.add(BorderLayout.CENTER, popupBody);
            d.show("Ajout Commande","Commande Ajoute avec Succes","OK",null);
}
         };
           connectionRequest.setUrl("http://localhost/CrudCommande/AjoutCommande.php?idClient="+c.getIdClient()+ "&dateCommande");
           NetworkManager.getInstance().addToQueue(connectionRequest);
        
    }
    public void AjoutLigneCommande(ligne_commandes lc){
         ConnectionRequest connectionRequest=new ConnectionRequest(){
             @Override
            protected void postResponse() {
              
}
         };
           connectionRequest.setUrl("http://localhost/CrudCommande/AjoutLigneCommande.php?PrixTotal="+lc.getPrix_commande()+ "&nbrArticle="+lc.getQuantite()+"&idProduit="+lc.getId_produit()+"&idCommande="+lc.getId_commande());
           NetworkManager.getInstance().addToQueue(connectionRequest);
           System.out.println("tajoutaa");
        
    }
  
  
    
      public ArrayList<Commande> getAllCommande(){
         ArrayList<Commande> Listc = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI4/web/app_dev.php/pi/prod/ListeCommande");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                  JSONParser jsonp = new JSONParser();
                  try{
                  Map<String, Object> com = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(com);
                   // System.out.println(prods);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) com.get("root");
                    for (Map<String, Object> obj : list) {
                      Commande c=new Commande();
                       float id = Float.parseFloat(obj.get("idCommande").toString());
                        
                        
                       c.setIdCommande((int) id);
                       // p.setImage(obj.get("image").toString());
                       
                       
                        Listc.add(c);

                    }
                } catch (IOException ex) {
                    
                }
                

           }
         });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return Listc;
         
        
    }
      public ArrayList<ligne_commandes> getAllLigneCommande(int idCommande){
         ArrayList<ligne_commandes> Listc = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI4/web/app_dev.php/pi/prod/LigneCommandeId/"+idCommande);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                  JSONParser jsonp = new JSONParser();
                  try{
                  Map<String, Object> com = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(com);
                   // System.out.println(prods);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) com.get("root");
                    for (Map<String, Object> obj : list) {
                        
                        System.err.println(obj.toString());
                     ligne_commandes lc=new ligne_commandes();
                       float id = Float.parseFloat(obj.get("idCommande").toString());
                       float idProduit = Float.parseFloat(obj.get("idProduit").toString());
                        lc.setId_produit((int)idProduit);
                        float Quantite=Float.parseFloat(obj.get("nbrArticle").toString());
                        lc.setQuantite((int)Quantite);
                        float Prix=Float.parseFloat(obj.get("prixTotal").toString());
                      
                       
                        lc.setPrix_commande((float)Prix);
                       lc.setId_commande((int) id);
                       // p.setImage(obj.get("image").toString());
                       
                       
                        Listc.add(lc);

                    }
                } catch (IOException ex) {
                    
                }
                

           }
         });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return Listc;
         
        
    }
      public ArrayList<Produits> findProd(int id){
         ArrayList<Produits> Listc = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI4/web/app_dev.php/pi/prod/FindProduitById/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                  JSONParser jsonp = new JSONParser();
                  try{
                  Map<String, Object> com = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(com);
                   // System.out.println(prods);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) com.get("root");
                    for (Map<String, Object> obj : list) {
                        
                        System.err.println(obj.toString());
                       Produits p=new Produits();
                       // p.setImage(obj.get("image").toString());
                       
                         p.setNom(obj.get("nom").toString());
                        Listc.add(p);

                    }
                } catch (IOException ex) {
                    
                }
                

           }
         });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return Listc;
         
        
    }
}
