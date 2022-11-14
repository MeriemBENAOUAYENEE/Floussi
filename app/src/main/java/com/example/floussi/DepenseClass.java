package com.example.floussi;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.Date;


@Entity(tableName = "DepensesTable")
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
    @TypeConverters(DateConverter.class)
    private Date dateDepense;
    @ColumnInfo
    private String type;


    public static class DateConverter {

        @TypeConverter
        public Date toDate(Long dateLong){
            return dateLong == null ? null: new Date(dateLong);
        }

        @TypeConverter
        public Long fromDate(Date date){
            return date == null ? null : date.getTime();
        }
    }

    public DepenseClass() {
    }

    public DepenseClass(String depense, float prix, int quantite, Date dateDepense, String type) {
        this.depense = depense;
        this.prix = prix;
        this.quantite = quantite;
        this.dateDepense = dateDepense;
        this.type = type;
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

    public Date getDateDepense() {
        return dateDepense;
    }

    public void setDateDepense(Date dateDepense) {
        this.dateDepense = dateDepense;
    }

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

    @Override
    public String toString() {
        return "DepenseClass{" +
                "id=" + id +
                ", depense='" + depense + '\'' +
                ", prix=" + prix +
                ", quantite=" + quantite +
                ", dateDepense='" + dateDepense + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
