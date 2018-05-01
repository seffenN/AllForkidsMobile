/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.allforkids.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import edu.allforkids.entities.Enfant;
import edu.allforkids.services.ServiceQuiz;
import java.io.IOException;

/**
 *
 * @author Narjes
 */
public class HomeForm {

    Toolbar toolbar;
    Image logo;
    Form f1;
    public static int idenfant;
    int enfant;

    public HomeForm() throws IOException {
        f1 = new Form();
        f1.setLayout(new FlowLayout(CENTER, CENTER));
        toolbar = f1.getToolbar();
        f1 = new Form();
        f1.setLayout(new FlowLayout(CENTER, CENTER));
        toolbar = f1.getToolbar();
        logo = Image.createImage("/logo.png");
        Container cn = BorderLayout.west(new Label(logo));
        //cn.add(BorderLayout.SOUTH, nom);
        toolbar.addComponentToSideMenu(cn);
        f1.add("welcome narjes");
        AfficherProduits2 ap = new AfficherProduits2();
        AjoutProduit AjoutP = new AjoutProduit();
        toolbar.addMaterialCommandToSideMenu("Boutique", FontImage.MATERIAL_STORE, e
                -> {

            ap.getF2().show();

        });
        toolbar.addMaterialCommandToSideMenu("Ajout Produit", FontImage.MATERIAL_ADD_SHOPPING_CART, e
                -> {

            AjoutP.getF().show();

        });

        toolbar.addMaterialCommandToSideMenu("Quiz", FontImage.MATERIAL_LIBRARY_ADD, e -> {

            ServiceQuiz serviceQuiz = new ServiceQuiz();
            ComboBox listeenfant = new ComboBox();
            for (Enfant enfa : serviceQuiz.getListEnfant(9)) {
                listeenfant.addItem(enfa.getNom() + "  " + enfa.getPrenom());
            }

            listeenfant.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    idenfant = 0;
                    for (Enfant enfa : serviceQuiz.getListEnfant(9)) {
                        if ((enfa.getNom() + "  " + enfa.getPrenom()).equals(listeenfant.getSelectedItem())) {
                            idenfant = enfa.getId();
                        }
                    }

                    AfficheQuiz q = new AfficheQuiz(idenfant);
                    q.getF().show();
                }
            });
            Dialog.show("Veuillez choisir votre Nom", listeenfant, null);

        });
        toolbar.addMaterialCommandToSideMenu("Videos", FontImage.MATERIAL_HEADSET, e -> {
            AfficheVideos q = new AfficheVideos();
            q.getF().show();
        });
        toolbar.addMaterialCommandToSideMenu("Lessons", FontImage.MATERIAL_ATTACHMENT, e -> {
            AfficheLesson a = new AfficheLesson();
            a.getF().show();

        });
        toolbar.addMaterialCommandToSideMenu("Consulter Progression", FontImage.MATERIAL_NOTIFICATIONS, e -> {

            Container c1 = new Container(BoxLayout.y());
            ComboBox anne = new ComboBox();
            for (int i = 1980; i < 2040; i++) {
                anne.addItem(i);
            }
            ComboBox listeenfant = new ComboBox();
            ServiceQuiz serviceQuiz = new ServiceQuiz();
            for (Enfant enfa : serviceQuiz.getListEnfant(9)) {
                listeenfant.addItem(enfa.getNom() + "  " + enfa.getPrenom());
            }

            listeenfant.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    for (Enfant enfa : serviceQuiz.getListEnfant(9)) {
                        if ((enfa.getNom() + "  " + enfa.getPrenom()).equals(listeenfant.getSelectedItem())) {
                            enfant = enfa.getId();
                            System.out.println("enfant" + enfant);
                        }
                    }
                }
            });
            Button butt = new Button("valider");
            butt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ConsulterProgression consulterProgression = new ConsulterProgression(Integer.parseInt(anne.getSelectedItem().toString()), enfant);
                    consulterProgression.getF().show();
                }
            });
            c1.add(anne);
            c1.add(listeenfant);
            c1.add(butt);
            Dialog.show("Veuillez choisir Le nom de votre enfant et l'année", c1, null);

        });

        f1.show();

    }

    public Form getF1() {
        return f1;
    }

}
