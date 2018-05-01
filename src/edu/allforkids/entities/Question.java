/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.allforkids.entities;

/**
 *
 * @author khaoula
 */
public class Question {
    private int id;
    private int id_quiz_id;
    private String libelle;
    private int flag;

    public Question(String libelle) {
        this.id_quiz_id = id_quiz_id;
        this.libelle = libelle;
        this.flag = flag;
    }

    public Question(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }
    

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", id_quiz_id=" + id_quiz_id + ", libelle=" + libelle + ", flag=" + flag + '}';
    }

    public Question() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_quiz_id() {
        return id_quiz_id;
    }

    public void setId_quiz_id(int id_quiz_id) {
        this.id_quiz_id = id_quiz_id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
    
    
    
}
