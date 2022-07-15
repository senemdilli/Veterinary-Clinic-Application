package Classes;

import tierklinik.FullDB;

import java.sql.SQLException;

public class Rezepte {
    private Integer rezeptId;
    private Integer tierId;
    private String tiername;
    private String nachname;
    private String medizin;

    public Rezepte(Integer rezeptId, Integer tierId, String tierName, String nachaname, String medizin) throws SQLException {
        rezeptId = FullDB.getRezeptId();
        this.tierId = tierId;
        this.tiername = tierName;
        this.nachname = nachaname;
        this.medizin = medizin;
    }

    public Rezepte(Integer tierId, String medizin) throws SQLException {
        rezeptId = FullDB.getRezeptId();
        this.tierId = tierId;
        this.medizin = medizin;
    }

    //Setter
    public void setRezeptid(Integer rezeptId) {
        this.rezeptId = rezeptId;
    }
    public void setTierId(Integer tierId) {
        this.tierId = tierId;
    }
    public void setTiername(String tierName) {
        this.tiername = tierName;
    }
    public void setNachaname(String nachaname) {
        this.nachname = nachaname;
    }
    public void setMedizin(String medizin) {
        this.medizin = medizin;
    }

    //Getter
    public Integer getRezeptid() {
        return rezeptId;
    }
    public Integer getTierid() {
        return tierId;
    }
    public String getTiername() {
        return tiername;
    }
    public String getNachname() {
        return nachname;
    }
    public String getMedizin() {
        return medizin;
    }

}
