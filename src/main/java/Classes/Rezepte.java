package Classes;

import tierklinik.FullDB;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Rezepte {
    private Integer rezeptId;
    private Integer tierId;
    private String tierName;
    private String nachaname;
    private String medizin;
    private Date date;
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public Rezepte(Integer rezeptId, Integer tierId, String tierName, String nachaname, String medizin, Date date) throws SQLException {
        rezeptId = FullDB.getRezeptId();
        this.tierId = tierId;
        this.tierName = tierName;
        this.nachaname = nachaname;
        this.medizin = medizin;
        this.date = date;
    }

    public Rezepte(Integer tierId, String medizin) throws SQLException {
        rezeptId = FullDB.getRezeptId();
        this.tierId = tierId;
        this.medizin = medizin;
    }

    //Setter
    public void setRezeptId(Integer rezeptId) {
        this.rezeptId = rezeptId;
    }
    public void setTierId(Integer tierId) {
        this.tierId = tierId;
    }
    public void setTierName(String tierName) {
        this.tierName = tierName;
    }
    public void setNachaname(String nachaname) {
        this.nachaname = nachaname;
    }
    public void setMedizin(String medizin) {
        this.medizin = medizin;
    }
    public void setDate(String date) {
        try {
            this.date = dateFormat.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(Termin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Getter
    public Integer getRezeptId() {
        return rezeptId;
    }
    public Integer getTierId() {
        return tierId;
    }
    public String getTierName() {
        return tierName;
    }
    public String getNachaname() {
        return nachaname;
    }
    public String getMedizin() {
        return medizin;
    }
    public Date getDate() {
        return date;
    }

}
