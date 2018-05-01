/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.allforkids.gui;

import com.codename1.components.MediaPlayer;
import com.codename1.components.WebBrowser;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import edu.allforkids.entities.Question;
import edu.allforkids.entities.Reponse;
import edu.allforkids.entities.Score;
import edu.allforkids.services.ServiceQuiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author khaoula
 */
public class PlayQuiz {
    Form f;
private Resources theme;
   int somme=0;
   public int tot;
   int numques=0;
   public int nombrerep=0;
  
    public PlayQuiz(int idenfant,int idquiz,HashMap<Question,ArrayList<Reponse>> hash) {
     
    
        System.out.println("enfant"+idenfant);
                   
            theme = UIManager.initFirstTheme("/theme");
            f= new Form("Jouer Quiz",new BoxLayout(BoxLayout.Y_AXIS));
             f.getToolbar().addCommandToLeftBar("back",theme.getImage("back-command.png"),b->{
     AfficheQuiz q=new AfficheQuiz(idenfant);
         q.getF().show();
      });
           for(Map.Entry<Question,ArrayList<Reponse>> e :hash.entrySet())
         { numques++;
             Label labquestion=new Label(numques+")"+e.getKey().getLibelle());
            f.add(labquestion);
        
         for(Reponse r:e.getValue())
         { 
             tot=tot+r.getPoint();
             Container  c2=new Container(new BoxLayout(BoxLayout.X_AXIS));
         c2.setName("c"+r.getId());
             Label labreponse=new Label("      "+r.getLibelle()+"      ");
            CheckBox check=new CheckBox();
            check.setName("check"+r.getId());
            CheckBox verif=new CheckBox();
            Label point=new Label(String.valueOf(r.getPoint()));
            point.setVisible(false);
             verif.setName("verif"+r.getId());
             verif.setVisible(false);
           if(r.getVerif()==1)
           {
               verif.setSelected(true);
           }
           else
           {
               verif.setSelected(false); 
               somme+=r.getPoint();
           }
            check.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                 
              if(verif.isSelected()==false)
              {
                  if(check.isSelected()==true)
                  {nombrerep++;
                      somme=somme-r.getPoint();
                      System.out.println(
                              somme-r.getPoint());
                  }
                  else
                  {nombrerep--;
                      somme=somme+r.getPoint(); 
                  }
              }
              if(verif.isSelected()==true)
              {
                   if(check.isSelected()==true)
                   {
                       somme=somme+r.getPoint();
                   }
                   else
                   {
                       somme=somme-r.getPoint();
                   }
              }
             }
         });
             
            
           c2.add(labreponse);
           c2.add(check);
           c2.add(verif);
           c2.add(point);
           f.add(c2);
          
         }
         } Button valider=new Button("Valider");
         valider.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    System.out.println("somme"+somme);
                    ServiceQuiz serviceQuiz=new ServiceQuiz();
                    Score score=new Score(somme, idquiz, idenfant);
                    serviceQuiz.ajoutScore(score);
                    AfficheQuiz afficheQuiz=new AfficheQuiz(idenfant);
                    if(nombrerep>0)
                    {
                    if((somme/tot)*100>50)
                    {

                    Dialog.show("score", "Bravo !! vous avez réçu"+somme+"/"+tot, "Ok", "Retour");
                   afficheQuiz.getF().show();
                    }
                    else
                    {
                 
                 
                           Dialog.show("score", "Oups vous avez réçu"+somme+"/"+tot, "Ok", "Retour");
                     afficheQuiz.getF().show();
                     }
                    }
                    else
                    {
                       Dialog.show("Attention", "Veuillez sélectionner une réponse", "Ok", "Retour");  
                    }
                  
                }
            });
  f.add(valider);
     
        
 
       
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
}
