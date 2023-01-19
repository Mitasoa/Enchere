package com.enchere.enchere.model;
import java.time.LocalDateTime;
public class TauxComission {
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    private float taux;
    public float getTaux() {
        return taux;
    }
    public void setTaux(float taux) {
        this.taux = taux;
    }
    private LocalDateTime date;
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
