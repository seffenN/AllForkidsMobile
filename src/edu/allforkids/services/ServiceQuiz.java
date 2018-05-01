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
import edu.allforkids.entities.Enfant;
import edu.allforkids.entities.Question;
import edu.allforkids.entities.Quiz;
import edu.allforkids.entities.Reponse;
import edu.allforkids.entities.Score;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




/**
 *
 * @author khaoula
 */
public class ServiceQuiz {
   // ArrayList<String> result=new ArrayList<>();
     String str;
     public ArrayList<Quiz> getAllQuiz(int id) {
        
        ArrayList<Quiz> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI4/web/app_dev.php/pi/DisplayQWS/"+id);
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
                        Quiz quiz = new Quiz();
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        quiz.setId((int) id);
                        quiz.setNom_quiz(obj.get("nomQuiz").toString());
                        quiz.setTheme(obj.get("theme").toString());
                        quiz.setCategorie_age(obj.get("categorieAge").toString());
                        quiz.setDescription(obj.get("description").toString());
                          quiz.setImage(obj.get("image").toString());
                        listTasks.add(quiz);

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
   public ArrayList<Quiz> getListByTheme(int id,String theme) {
        ArrayList<Quiz> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI4/web/app_dev.php/pi/DisplayQWS/"+id+"?theme="+theme);
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
                        
                        Quiz quiz = new Quiz();
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        quiz.setId((int) id);
                        quiz.setNom_quiz(obj.get("nomQuiz").toString());
                        quiz.setTheme(obj.get("theme").toString());
                        quiz.setCategorie_age(obj.get("categorieAge").toString());
                        quiz.setDescription(obj.get("description").toString());
                          quiz.setImage(obj.get("image").toString());
                        listTasks.add(quiz);

                    }
                    System.out.println(list);
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
      public HashMap<Question,ArrayList<Reponse>> PlayQuiz(String id) {
            HashMap<Question,ArrayList<Reponse>> listTasks=new HashMap<>();
        ConnectionRequest con = new ConnectionRequest();
       // con.setUrl("http://41.226.11.243:10004/tasks/");
        con.setUrl("http://localhost/PI4/web/app_dev.php/pi/PlayQuizWS/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                //canvas
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   // System.out.println(tasks);
                    //System.out.println(tasks);
                   List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("question");
                   List<Map<String, Object>> list2 = (List<Map<String, Object>>) tasks.get("reponse");
                //    System.out.println("list"+list);
                 //   System.out.println("list2"+list2);
                   for (Map<String, Object> obj : list) {
                       
                          Question task = new Question();
                        float id = Float.parseFloat(obj.get("id").toString());
                        task.setLibelle(obj.get("Libelle").toString());
                        task.setId((int) id);
                       task.setId_quiz_id((int) Float.parseFloat(obj.get("id_quiz_id").toString()));
                      ArrayList<Reponse> listereponse=new ArrayList<>();
                       for (Map<String, Object> obj2 : list2) {
                          
                           if((int)Float.parseFloat(obj2.get("id_quest_id").toString())==(int) id)
                           {
                               Reponse reponse=new Reponse();
                           float idrep = Float.parseFloat(obj2.get("id").toString());            
                               reponse.setId((int)idrep);
                               reponse.setLibelle(obj2.get("libelle").toString());
                                  reponse.setPoint((int)Float.parseFloat(obj2.get("point").toString()));
                                   reponse.setVerif((int)Float.parseFloat(obj2.get("verif").toString()));
                      listereponse.add(reponse);
                           }
                       } 
                     listTasks.put(task, listereponse);
                      }
                    
                } catch (IOException ex) {
                }
               System.out.println("list task"+listTasks);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        //yab3ath requette w yestana reponse
        return listTasks;
    }
          public void ajoutScore(Score score) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PI4/web/app_dev.php/pi/scoring?idquiz="+score.getId_quiz_id()+"&idenfant="+score.getId_enfant()+"&score="+score.getScore();
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        //j'excute lorsque je recois une reponse
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
          
          public ArrayList<Enfant> getListEnfant(int id)
          {
             ArrayList<Enfant> listEnfant = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI4/web/app_dev.php/pi/enfant/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                //canvas
                JSONParser jsonp = new JSONParser();
                  String str = new String(con.getResponseData());
                  System.out.println(str);
            
                    Map<String, Object> tasks;
                try {
                    tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
               //  System.out.println(tasks);
                 List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Enfant enfant = new Enfant();
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        enfant.setId((int) id);
                        enfant.setNom(obj.get("nom").toString());
                        enfant.setPrenom(obj.get("prenom").toString());
                     
                        listEnfant.add(enfant);

                    }
                } catch (IOException ex) {
                     System.out.println(ex.getMessage());  
                }
                 
                
                }
                  
                    //System.out.println(tasks);
                   

           
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        //yab3ath requette w yestana reponse
        return listEnfant;  
          }
          
          public double[][] getStat(String [] theme,int annee,int idenfant)
          { String [] month=new String[]{"1-3","4-6","7-9","10-12"};
              double[][] allValues = new double[4][4] ;
              String resultat,st1,st2,d,resch;
             for(int i=0;i<allValues.length;i++)
        {
             for(int j=0;j<allValues.length;j++) 
          {
             
            //  System.out.println(month[j]);
              System.out.println("mois"+month[j]);
               allValues[i][j]=0;    
         
              resultat=getstatvalue(month[j], annee, idenfant, theme[i]);
              System.out.println("res"+resultat);
             if(resultat!=null)
             {
                 resch=resultat.trim();
           
                 System.out.println("resch"+resch);
       st1=resultat.trim().substring(resultat.trim().substring(0,resultat.trim().indexOf(",")).indexOf(":")+2,resultat.trim().substring(0,resultat.trim().indexOf(",")).length()-3);
               
          st2=resultat.trim().substring(resch.indexOf(","),resultat.trim().length());
         
        d=st2.substring(st2.indexOf(":")+2, st2.length()-4);
          System.out.println("st1"+st1+"st2"+d);
          if(!st1.equals("ul"))
          {
            //    System.out.println("ggg"+st1+" "+d);
     allValues[i][j]=(Double.parseDouble(st1)/Double.parseDouble(d))*10;
              }
              else
          {
                 allValues[i][j]=0;
          }
      }
             
         } 
        }
return allValues;
             
}
          
      public String getstatvalue(String mois,int annee,int idenfant,String theme)
          {   
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PI4/web/app_dev.php/pi/stat?theme="+theme+"&mois="+mois+"&annee="+annee+"&idenfant="+idenfant;
        con.setUrl(Url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
              @Override
              public void actionPerformed(NetworkEvent evt) {
                  JSONParser jsonp = new JSONParser();
                   str = new String(con.getResponseData());
                  //System.out.println("dd"+str);
                
                    }
                  
              }
          );
            
      
          
             
        NetworkManager.getInstance().addToQueueAndWait(con);
              return str;

          }
}