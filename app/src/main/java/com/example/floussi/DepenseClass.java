package com.example.floussi;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;


@Entity(tableName = "depenseTable")
public class DepenseClass {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String depense;
    @ColumnInfo
    private float prix;
    @ColumnInfo
    private int quantite;
    @ColumnInfo
    private String dateDepense;

    public DepenseClass() {
    }

    public DepenseClass(String depense, float prix, int quantite, String dateDepense) {
        this.depense = depense;
        this.prix = prix;
        this.quantite = quantite;
        this.dateDepense = dateDepense;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepense() {
        return depense;
    }

    public void setDepense(String depense) {
        this.depense = depense;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getDateDepense() {
        return dateDepense;
    }

    public void setDateDepense(String dateDepense) {
        this.dateDepense = dateDepense;
    }

    @Override
    public String toString() {
        return "DepenseClass{" +
                "id=" + id +
                ", depense='" + depense + '\'' +
                ", prix=" + prix +
                ", quantite=" + quantite +
                ", date=" + dateDepense +
                '}';
    }
}
