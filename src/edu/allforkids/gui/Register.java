/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.allforkids.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import static com.codename1.ui.TextArea.PASSWORD;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import edu.allforkids.entities.User;
import static edu.allforkids.gui.Login.idUser;
import edu.allforkids.services.CrudUser;
import java.io.IOException;
import java.util.ArrayList;



/**
 *
 * @author Narjes
 */
public class Register {
    private Resources theme;
ComboBox cb;
 Form f;
public static String emails ="";
    public Register() {
       
    try {
         theme = UIManager.initFirstTheme("/theme");
         f=new Form();
        Image logo=Image.createImage("/logo.png");
        ImageViewer im=new ImageViewer();
        Image kids=Image.createImage("/kids-014.jpg");
        im.setImage(kids);
        im.setPreferredSize(new Dimension(100, 100));
        kids.scaled(20,20);
        Container cn = BorderLayout.west(new Label(kids));
        f.add(cn);
        f.add(logo);
        TextField username=new TextField("","username");
        TextField email=new TextField("","email");
        TextField mdp=new TextField("","mdp");
        mdp.setConstraint(PASSWORD);
        cb=new ComboBox();
        cb.addItem("Parent");
        cb.addItem("BabySitter");
        cb.addItem("Pediatre");
        // TextField role=new TextField("","role");
        // TextField image=new TextField("", "image");
        Button inscrire=new Button("s'inscrire");
        f.addAll(username,email,mdp,cb,inscrire);
        inscrire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    if(username.getText().equals("")||email.getText().equals("")||cb.getSelectedItem().toString().equals("")||mdp.getText().equals("")){
                        Dialog d = new Dialog("Champs Vide");
                        
                        TextArea popupBody = new TextArea("Champs Vide");
                        popupBody.setUIID("PopupBody");
                        popupBody.setEditable(true);
                        d.setLayout(new BorderLayout());
                        d.add(BorderLayout.CENTER, popupBody);
                        d.show("Champs vide","Veuillez remplir tous les champs","OK",null);
                    }else{
                        User u=new User();
                        u.setUsername(username.getText());
                        u.setUsername_canonical(username.getText());
                        u.setEmail(email.getText());
                        u.setEmail_canonical(email.getText());
                        emails=email.getText();
                        u.setRoles(""+cb.getSelectedItem());
                        u.setPassword(mdp.getText());
                        //u.setNom_image(image.getText());
                        CrudUser serviceUser=new CrudUser();
                        serviceUser.Register(u);
                        ArrayList<User> list= serviceUser.Login(username.getText(), mdp.getText());
                        
                        String nom="";
                        String mdp="";
                        int id=0;
                        for(int i=0;i<list.size();i++){
                            nom=list.get(i).getUsername();
                            mdp=list.get(i).getPassword();
                            id=list.get(i).getId();
                        }
                        Login.idUser=id;
                        HomeForm h=new HomeForm();
                        
                        System.out.println("ajouta user");
                    }
                } catch (IOException ex) {
                    
                }
                
                
                
            }
        });
         f.getToolbar().addCommandToLeftBar("back", theme.getImage("back-command.png"), b -> {
            
                         MyApplication m=new MyApplication();
                          
                        });
        f.show();
        
    } catch (IOException ex) {
        
    }
        
    }
    
    
    
}
