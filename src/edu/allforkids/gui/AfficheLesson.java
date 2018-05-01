/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.allforkids.gui;


import com.codename1.components.SpanLabel;
import com.codename1.components.WebBrowser;
import com.codename1.ui.Button;

import com.codename1.ui.ComboBox;

import com.codename1.ui.Container;
import com.codename1.ui.Display;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import edu.allforkids.entities.Enfant;
import edu.allforkids.entities.Lesson;
import edu.allforkids.entities.Question;
import edu.allforkids.entities.Quiz;
import edu.allforkids.entities.Reponse;
import edu.allforkids.entities.Score;
import edu.allforkids.services.ServiceLesson;
import java.io.IOException;




/**
 *
 * @author khaoula
 */
public class AfficheLesson {
    Form f;
private Resources theme;
    public AfficheLesson() {
        
         
           
            theme = UIManager.initFirstTheme("/theme");
            f= new Form("Liste des leçons",new BoxLayout(BoxLayout.Y_AXIS));
          f.getToolbar().addCommandToLeftBar("back",theme.getImage("back-command.png"),b->{
                try {
                    HomeForm hf=new HomeForm();
                    
                } catch (IOException ex) {
                    
                }
             
                    
             
             
          });
      Label  labeltheme=new Label("Théme Quiz");
    ComboBox themequiz=new ComboBox();
       themequiz.addItem("");
    themequiz.addItem("Mathématique");
     themequiz.addItem("Science");
     themequiz.addItem("Langue");
     themequiz.addItem("Culture Générale");
     
      Container c1=new Container(new BoxLayout(BoxLayout.X_AXIS));
    
     themequiz.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent evt) {
                   System.out.println(themequiz.getSelectedItem().toString());
                   reset(themequiz.getSelectedItem().toString());
                   // parcours(themequiz.getSelectedItem().toString());
                  
               }
           });
      
       
        c1.add(labeltheme);
         c1.add(themequiz);
           Container espace=new Container(new BoxLayout(BoxLayout.Y_AXIS));
           Label labesp=new Label();
           espace.add(labesp);
             
        f.add(c1);
            f.add(espace);    
            parcours("");
//WebBrowser browser=new WebBrowser("file:///C:/Users/khaoula/Downloads/rapport.pdf");


    
    }
public void parcours(String theme)
{ServiceLesson serviceLesson=new ServiceLesson();

           for(Lesson video:serviceLesson.getLesson(6, theme)){
               Container  c2=new Container(new BoxLayout(BoxLayout.Y_AXIS));

            SpanLabel titre=new SpanLabel("Nom Lesson :"+video.getNomLesson());
            Label themev=new Label("Théme :"+video.getTheme());
            Label catev=new Label("Catégorie Age :"+video.getCategorieAge());
               Button ouvrir=new Button("ouvrir");
               ouvrir.addActionListener(new ActionListener() {
                   @Override
                   
                   public void actionPerformed(ActionEvent evt) {
                    
                   Display.getInstance().execute("file:///C:/wamp64/www/PI4/web/upload/pdfs/"+video.getBrochure());
                   }
               });
            c2.add(titre);
              c2.add(themev);
                c2.add(catev);
                c2.add(ouvrir);

f.add(c2);
        } 
}
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
        public void reset(String theme)
    {
        f.removeAll();
      Label   labeltheme=new Label("Théme Quiz");
    ComboBox themequiz=new ComboBox();
       themequiz.addItem("");
    themequiz.addItem("Mathématique");
     themequiz.addItem("Science");
     themequiz.addItem("Langue");
     themequiz.addItem("Culture Générale");
     themequiz.setSelectedItem(theme);
      Container c1=new Container(new BoxLayout(BoxLayout.X_AXIS));
    
     themequiz.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent evt) {
                   System.out.println(themequiz.getSelectedItem().toString());
                   reset(themequiz.getSelectedItem().toString());
                   parcours(themequiz.getSelectedItem().toString());
                   
               }
           });
      
       
        c1.add(labeltheme);
         c1.add(themequiz);
           Container espace=new Container(new BoxLayout(BoxLayout.Y_AXIS));
           Label labesp=new Label();
           espace.add(labesp);
             
        f.add(c1);
            f.add(espace); 
            parcours(theme);
            
    }
    
}
