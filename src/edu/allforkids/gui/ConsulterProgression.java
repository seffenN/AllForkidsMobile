/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.allforkids.gui;

import ca.weblite.codename1.components.charts.Chart;
import ca.weblite.codename1.components.charts.ChartBuilder;
import ca.weblite.codename1.components.charts.ChartView;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import static edu.allforkids.gui.HomeForm.idenfant;
import edu.allforkids.entities.Enfant;
import edu.allforkids.entities.Question;
import edu.allforkids.entities.Quiz;
import edu.allforkids.entities.Reponse;
import edu.allforkids.entities.Score;
import edu.allforkids.services.ServiceQuiz;
import java.io.IOException;
/**
 *
 * @author khaoula
 */
public class ConsulterProgression {
        Form f;
private Resources theme;
private int idenfant;
   public ConsulterProgression(int annee,int idenfant) {
         theme = UIManager.initFirstTheme("/theme");
         ServiceQuiz service=new ServiceQuiz();
          Container c1=new Container(BoxLayout.y());
f=new Form("Play Quiz",new BorderLayout());
      f.getToolbar().addCommandToLeftBar("back",theme.getImage("back-command.png"),b->{
              try {
                    HomeForm hf=new HomeForm();
                    
                } catch (IOException ex) {
                    
                }
             
             
             
          });

  String [] theme=new String[]{"Mathématique", "Science", "Langue", "Culture Générale"};
  String [] mois=new String[]{"Jan-Mars","Avr-Juin","Juill-Sept","Oct-Dec"};
 
 
 double[][]   allValues=service.getStat(theme, annee, idenfant);
  
ChartBuilder b = new ChartBuilder();
Chart chart = b.newBarChart(
       allValues,
        theme,
      mois
);
ChartView v = new ChartView(chart);
v.initLater();
//c1.add(anne);
//c1.add(listeenfant);
f.addComponent(BorderLayout.CENTER, v);


    }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

 

    
}
