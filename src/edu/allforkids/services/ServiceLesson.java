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
import edu.allforkids.entities.Lesson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author khaoula
 */
public class ServiceLesson {
      public ArrayList<Lesson> getLesson(int id,String theme) {
        
        ArrayList<Lesson> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI4/web/app_dev.php/pi/DisplayLesson?theme="+theme+"&idenfant="+id);
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
                        Lesson lesson = new Lesson();
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        lesson.setId((int) id);
                        lesson.setNomLesson(obj.get("nomLesson").toString());
                        lesson.setTheme(obj.get("theme").toString());
                        lesson.setCategorieAge(obj.get("categorieAge").toString());
                        lesson.setBrochure(obj.get("brochure").toString());
                          
                        listTasks.add(lesson);

                    }
                } catch (IOException ex) {
                     System.out.println(ex.getMessage());  
                }
                 
                
                }
   
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        //yab3ath requette w yestana reponse
        return listTasks;
    }
    
}
