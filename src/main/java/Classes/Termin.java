package Classes;

import tierklinik.FullDB;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Termin {

    private static Integer terminid;
    private String angabe;
    private Date date;
    private String startzeit;
    private String endezeit;
    private String tiername;
    private String tiernachname;
    private String hbname;
    private String tierarztname;
    private String zustand = "nicht";
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public Termin(String tiername, String tiernachname, String tierarztname) throws SQLException {
        terminid = FullDB.getTerminId();
        this.tiername = tiername;
        this.tiernachname = tiernachname;
        this.tierarztname = tierarztname;
    }
    public Termin(String angabe, String tiername, String tiernachname, String hbname, String tierarztname) throws SQLException {
        terminid = FullDB.getTerminId();
        this.angabe = angabe;
        this.tiername = tiername;
        this.tiernachname = tiernachname;
        this.hbname = hbname;
        this.tierarztname = tierarztname;
    }

    public Termin(int terminid, String tiername, String hbname, String tiernachname, String tierarzt, String angabe, String date, String startzeit, String endezeit) throws SQLException {
        this.terminid = FullDB.getTerminId();
        this.angabe = angabe;
        this.tiername = tiername;
        this.tiernachname = tiernachname;
        this.hbname = hbname;
        this.tierarztname = tierarzt;
        try {
            this.date = dateFormat.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(Termin.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.startzeit = startzeit;
        this.endezeit = startzeit;
    }

    public Termin(String angabe, String tiername, String tiernachname, String hbname, String tierarztname, int terminid, String zustand, String date, String startzeit, String endezeit){
        this.angabe = angabe;
        this.tiername = tiername;
        this.tiernachname = tiernachname;
        this.hbname = hbname;
        this.tierarztname = tierarztname;
        this.terminid = terminid;
        this.zustand = zustand;
        try {
            this.date = dateFormat.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(Termin.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.startzeit = startzeit;
        this.endezeit = startzeit;
    }

    // Setter
    public void setTerminId(int terminid) {
        Termin.terminid = terminid;
    }
    public void setAngabe(String angabe) {
        this.angabe = angabe;
    }

    public void setDate(String date) throws ParseException {
        this.date = dateFormat.parse(date);
    }

    public void setStartzeit(String gegebenstartzeit) {
        startzeit = gegebenstartzeit;
    }

    public void setEndezeit(String gegebenendezeit) {
        endezeit = gegebenendezeit;
    }

    public void setTiername(String tiername) {
        this.tiername = tiername;
    }

    public void setZustand(String zustand) {
        this.zustand = zustand;
    }

    public void setTiernachname(String tiernachname) {
        this.tiernachname = tiernachname;
    }

    public void setHbname(String hbname) {
        this.hbname = hbname;
    }

    public void setTierarztname(String tierarztname) {
        this.tierarztname = tierarztname;
    }

    // Getter
    public static Integer getTerminid() {
        return terminid;
    }

    public String getAngabe() {
        return angabe;
    }

    public String getDate() {
        return dateFormat.format(date);
    }

    public String getStartzeit() {
        return startzeit;
    }

    public String getEndezeit() {
        return endezeit;
    }

    public String getZustand() {
        return zustand;
    }

    public String getTiername() {
        return tiername;
    }

    public String getHbname() {
        return hbname;
    }

    public String getTiernachname() {
        return tiernachname;
    }

    public String getTierarztname() {
        return tierarztname;
    }

    public static boolean controlDate(int terminid) throws SQLException, ParseException {
        if(terminid == -1) return false;
        Termin termin = FullDB.getTermin(terminid);
        String termindate = termin.getDate();
        Date termindateDate = dateFormat.parse(termindate);
        Date today = new Date();
        return termindateDate.after(today);
    }
}
