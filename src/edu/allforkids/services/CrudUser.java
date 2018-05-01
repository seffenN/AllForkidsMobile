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
import edu.allforkids.entities.Commande;
import edu.allforkids.entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Narjes
 */
public class CrudUser {
    public ArrayList<User> Login(String username,String password){
        ArrayList<User> ListUser = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI4/web/app_dev.php/pi/UserLogin/"+username+"/"+password);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                  JSONParser jsonp = new JSONParser();
                  try{
                  Map<String, Object> user = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(user);
                   // System.out.println(prods);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) user.get("root");
                    for (Map<String, Object> obj : list) {
                      User u=new User();
                       float id = Float.parseFloat(obj.get("id").toString());
                        u.setId((int)id);
                        u.setPassword(obj.get("password").toString());
                        u.setUsername(obj.get("username").toString());
                        
                       
                       
                       
                        ListUser.add(u);

                    }
                } catch (IOException ex) {
                    
                }
                

           }
         });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return ListUser;
         
        
    }
        
    }
    
