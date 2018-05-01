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
import com.codename1.ui.events.ActionListener;
import edu.allforkids.entities.MediaVideo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author khaoula
 */
public class ServiceVideo {
     public ArrayList<MediaVideo> getVideo(int id,String theme) {
        
        ArrayList<MediaVideo> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI4/web/app_dev.php/pi/DisplayVideo?theme="+theme+"&idenfant="+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                //canvas
                JSONParser jsonp = new JSONParser();
                
            
                    Map<String, Object> tasks;
                try {
                    tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
               //  System.out.println(tasks);
                 List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        MediaVideo video = new MediaVideo();
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        video.setId((int) id);
                        video.setTitre(obj.get("titre").toString());
                        video.setTheme(obj.get("theme").toString());
                        video.setCategorieAge(obj.get("categorieage").toString());
                        video.setDescription(obj.get("description").toString());
                          
                        listTasks.add(video);

                    }
                } catch (IOException ex) {
                     System.out.println(ex.getMessage());  
                }
                 
                
                }
                  
                    //System.out.println(tasks);
                   

           
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        //yab3ath requette w yestana reponse
        return listTasks;
    }
    
}
