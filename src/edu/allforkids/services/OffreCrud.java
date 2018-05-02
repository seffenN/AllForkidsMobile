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
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import edu.allforkids.entities.Avis;
import edu.allforkids.entities.Offre;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class OffreCrud {
     public void ajoutOffre(Offre o) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PI4/web/app_dev.php/pi/AjouttOffreMob/" + o.getParent_id()+"/" + o.getBabysitter_id() 
                +"/"+o.getNbrEnfant()
                +"/"+o.getService()
                +"/"+o.getAge()
                +"/"+o.getDateOffre()
                +"/"+o.getTemps()
                +"/"+o.getAccept()
                +"/"+o.getVille();

                
        con.setUrl(Url);



        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());

            Dialog.show("Succés", "Offre Envoyé", "ok", null);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public ArrayList<Offre> getListOffre(int idp) {
        
        ArrayList<Offre> listOffre = new ArrayList<Offre>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI4/web/app_dev.php/pi/AffiicherOffreMob/"+idp);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                Container c1= new Container();
                try {
                    Map<String, Object> offres = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    
                    List<Map<String, Object>> list = (List<Map<String, Object>>) offres.get("root");
                    
                        if (list !=null){
                    for (Map<String, Object> obj : list) {
                        Offre a = new Offre();
                        float id = Float.parseFloat(obj.get("id").toString()); 
                        a.setId((int) id);
                        float idp=Float.parseFloat(obj.get("parent_id").toString());
                        a.setParent_id((int) idp);
                        float b=Float.parseFloat(obj.get("babysitter_id").toString());
                        a.setBabysitter_id((int) b);
                        float nbr=Float.parseFloat(obj.get("NbrEnfant").toString());
                        a.setNbrEnfant((int) nbr);
                        a.setService(obj.get("Service").toString());
                        float ag=Float.parseFloat(obj.get("Age").toString());
                        a.setAge((int) ag);
                        a.setDateOffre(obj.get("DateOffre").toString());
                        a.setTemps(obj.get("temps").toString());
                        a.setAccept(0);
                        a.setVille(obj.get("ville").toString());
                        
                        
                        
                        listOffre.add(a);

                    }
                    }
                    
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listOffre;
    }
    
     public void UpdateOffre(Offre o) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PI4/web/app_dev.php/pi/ModifOffreMob/"+ o.getId()+"/" + o.getParent_id()+"/" + o.getBabysitter_id() 
                +"/"+o.getNbrEnfant()
                +"/"+o.getService()
                +"/"+o.getAge()
                +"/"+o.getDateOffre()
                +"/"+o.getTemps()
                +"/"+o.getAccept()
                +"/"+o.getVille();
                
        con.setUrl(Url);


        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());

            
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }    
}
