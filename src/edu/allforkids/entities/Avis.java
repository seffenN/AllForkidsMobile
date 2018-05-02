/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.allforkids.entities;

/**
 *
 * @author ASUS
 */
public class Avis {
    private int id;
    private int id_parent_id;
    private int id_babysitter_id;
    private String monAvis;
    
    public Avis(){}

    public Avis(int id, int id_parent_id, int id_babysitter_id, String monAvis) {
        this.id = id;
        this.id_parent_id = id_parent_id;
        this.id_babysitter_id = id_babysitter_id;
        this.monAvis = monAvis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_parent_id() {
        return id_parent_id;
    }

    public void setId_parent_id(int id_parent_id) {
        this.id_parent_id = id_parent_id;
    }

    public int getId_babysitter_id() {
        return id_babysitter_id;
    }

    public void setId_babysitter_id(int id_babysitter_id) {
        this.id_babysitter_id = id_babysitter_id;
    }

    public String getMonAvis() {
        return monAvis;
    }

    public void setMonAvis(String monAvis) {
        this.monAvis = monAvis;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Avis other = (Avis) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.id_parent_id != other.id_parent_id) {
            return false;
        }
        if (this.id_babysitter_id != other.id_babysitter_id) {
            return false;
        }
        return true;
    }

  
    
    
}
