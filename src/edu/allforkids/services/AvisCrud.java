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
import com.codename1.ui.events.ActionListener;
import edu.allforkids.entities.Avis;
import edu.allforkids.entities.Offre;
import edu.allforkids.entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class AvisCrud {
    
    
    public void ajoutAv(Avis a) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PI4/web/app_dev.php/pi/AjoutAvisMob/" + a.getId_parent_id()+"/" + a.getId_babysitter_id() + "/"+a.getMonAvis();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public ArrayList<Avis> getListAv(int idb) {
        
        ArrayList<Avis> listAv = new ArrayList<Avis>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI4/web/app_dev.php/pi/AfficheAvisMob/"+idb);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                Container c1= new Container();
                try {
                    Map<String, Object> aviss = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    
                    List<Map<String, Object>> list = (List<Map<String, Object>>) aviss.get("root");
                        if (list !=null){
                    for (Map<String, Object> obj : list) {
                        Avis a = new Avis();
                        float id = Float.parseFloat(obj.get("id").toString()); 
                        a.setId((int) id);
                       Map<String, Object> parent = (Map<String, Object>) obj.get("idParent");
                        float idp=Float.parseFloat(parent.get("id").toString());
                        a.setId_parent_id((int) idp);
                        Map<String, Object> babysitter = (Map<String, Object>) obj.get("idBabysitter");
                        float b=Float.parseFloat(babysitter.get("id").toString());
                        a.setId_babysitter_id((int) b);
                        a.setMonAvis(obj.get("monAvis").toString());
                        
                        
                        
                        listAv.add(a);

                    }
                    }
                    
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAv;
    }
    
    public void UpdateAvis(Avis a) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PI4/web/app_dev.php/pi/ModifAvisMob/"+ a.getId()+"/" + a.getId_parent_id()+"/" + a.getId_babysitter_id()
                +"/"+a.getMonAvis();
                
        con.setUrl(Url);


        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());

            
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }    
}
