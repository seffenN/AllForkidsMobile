/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.allforkids.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import edu.allforkids.entities.Enfant;
import edu.allforkids.entities.Question;
import edu.allforkids.entities.Quiz;
import edu.allforkids.entities.Reponse;
import edu.allforkids.entities.Score;
import edu.allforkids.services.ServiceQuiz;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author khaoula
 */
public class AfficheQuiz {
     Form f;
     Label labeltheme;
     private Resources theme;
     public  Container c2;
  private  Container con;
  public static int enfant;
    public AfficheQuiz(int idenfant)  {
        enfant=idenfant;
           theme = UIManager.initFirstTheme("/theme");
        f= new Form("Play Quiz",new BoxLayout(BoxLayout.Y_AXIS));
                 
       
        labeltheme=new Label("Théme Quiz");
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
                  
                  
               }
           });
      
       
        c1.add(labeltheme);
         c1.add(themequiz);
           Container espace=new Container(new BoxLayout(BoxLayout.Y_AXIS));
           Label labesp=new Label();
           espace.add(labesp);
             
        f.add(c1);
            f.add(espace);  
           
         parcours();
        f.add(con);
      f.getToolbar().addCommandToLeftBar("back",theme.getImage("back-command.png"),b->{
       try {
                    HomeForm hf=new HomeForm();
                    
                } catch (IOException ex) {
                    
                }
             
                    
      });   
    }
    
    public void reset(String theme)
    {
        f.removeAll();
         labeltheme=new Label("Théme Quiz");
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
                   parcours2(themequiz.getSelectedItem().toString());
                   reset(themequiz.getSelectedItem().toString());
               }
           });
      
       
        c1.add(labeltheme);
         c1.add(themequiz);
           Container espace=new Container(new BoxLayout(BoxLayout.Y_AXIS));
           Label labesp=new Label();
           espace.add(labesp);
             
        f.add(c1);
            f.add(espace); 
            parcours2(theme);
            
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
     public void parcours()
     { System.out.println("parcours1");
            con=new Container(new BoxLayout(BoxLayout.Y_AXIS));
         ServiceQuiz serviceTask=new ServiceQuiz();
         ArrayList<Quiz> listquiz=serviceTask.getAllQuiz(enfant);
           for(Quiz i:listquiz)
        {
      
             c2=new Container(new BoxLayout(BoxLayout.Y_AXIS));
             
             
             System.out.println(i.getImage().substring(i.getImage().indexOf("Quiz"), i.getImage().length()));
             System.out.println("image"+i.getImage());
             ImageViewer img2= new ImageViewer();
             Image placeholder2 = Image.createImage(200,200,0xbfc92d);
             //image temporiaire
             EncodedImage encImage2=EncodedImage.createFromImage(placeholder2, false);
             Image icone= URLImage.createToStorage(encImage2, i.getImage().substring(i.getImage().indexOf("Quiz"), i.getImage().length()),"http://localhost/PI4/web/upload/"+i.getImage().substring(i.getImage().indexOf("Quiz"), i.getImage().length()),URLImage.RESIZE_SCALE);
             //Image  icone=Image.createImage('/'+i.getImage().substring(i.getImage().indexOf("Quiz"), i.getImage().length()));
           //  icone.scale(100, 100);
            // Container c3=new Container(new BoxLayout(BoxLayout.Y_AXIS));
             
             Label l=new Label(icone);
             
             SpanLabel nomq=new SpanLabel("nom Quiz :"+i.getNom_quiz());
             
             nomq.setY(10);
             SpanLabel desc=new SpanLabel("Description "+i.getDescription());
             SpanLabel themeq=new SpanLabel("Théme : "+i.getTheme());
             SpanLabel categ=new SpanLabel("Catégorie d'age "+i.getCategorie_age());
             
             Button vouButton=new Button("Jouer");
             vouButton.setName(String.valueOf(i.getId()));
             vouButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent evt) {
                     System.out.println(vouButton.getName());
                     HashMap<Question,ArrayList<Reponse>> hash=  serviceTask.PlayQuiz(vouButton.getName());
                     PlayQuiz play=new PlayQuiz(enfant,Integer.parseInt(vouButton.getName()), hash);
                     play.getF().show();
                 }
             });
             c2.add(nomq);
             c2.add(desc);
             c2.add(themeq);
             c2.add(categ);
             c2.add(l);
             c2.add(vouButton);

         
             f.add(c2);
        
             }
           
        
     }
     
  
    
      public void parcours2(String theme)
     { 
         System.err.println("parcours2");
           
         ServiceQuiz serviceTask=new ServiceQuiz();
         ArrayList<Quiz> listquiz=serviceTask.getListByTheme(enfant,theme);
    
         System.err.println("list"+listquiz.size());
           for(Quiz i:listquiz)
        {
              c2=new Container(new BoxLayout(BoxLayout.Y_AXIS));
       
             ImageViewer img2= new ImageViewer();
             Image placeholder2 = Image.createImage(200,200,0xbfc92d);
             //image temporiaire
             EncodedImage encImage2=EncodedImage.createFromImage(placeholder2, false);
             Image icone= URLImage.createToStorage(encImage2, i.getImage().substring(i.getImage().indexOf("Quiz"), i.getImage().length()),
                     "http://localhost/PI4/web/upload/"+i.getImage().substring(i.getImage().indexOf("Quiz"), i.getImage().length()),URLImage.RESIZE_SCALE);
             //Image  icone=Image.createImage('/'+i.getImage().substring(i.getImage().indexOf("Quiz"), i.getImage().length()));
           //  icone.scale(100, 100);
            // Container c3=new Container(new BoxLayout(BoxLayout.Y_AXIS));
                   
                 Label l=new Label(icone);
                 SpanLabel nomq=new SpanLabel("nom Quiz :"+i.getNom_quiz());
               
                  nomq.setY(10);
                   SpanLabel desc=new SpanLabel("Description "+i.getDescription());
                      SpanLabel themeq=new SpanLabel("Théme : "+i.getTheme());
                       SpanLabel categ=new SpanLabel("Catégorie d'age "+i.getCategorie_age());
                       
                       Button vouButton=new Button("Jouer");
                       vouButton.setName(String.valueOf(i.getId()));
                       vouButton.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent evt) {
                         System.out.println(vouButton.getName());
                             serviceTask.PlayQuiz(vouButton.getName());
                     }
                 });
                       c2.add(nomq);
                       c2.add(desc);
                       c2.add(themeq);
                       c2.add(categ);
                       c2.add(l);
                       c2.add(vouButton);
           
         
             f.add(c2);
            
        }
     }
     
}
