package Classes;

import tierklinik.TableControllerAppointment;

import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Termin {

    private static Integer terminid;
    private String angabe;
    private Date date;
    private Time startzeit;
    private Time endezeit;
    private String tiername;
    private String tiernachname;
    private String hbname;
    private String tierarztname;

    SimpleDateFormat timeformat = new SimpleDateFormat("hh:mm");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public Termin(String angabe, String tiername, String tiernachname, String hbname, String tierarztname) throws SQLException {
        terminid = TableControllerAppointment.getTerminId();
        this.angabe = angabe;
        this.tiername = tiername;
        this.tiernachname = tiernachname;
        this.hbname = hbname;
        this.tierarztname = tierarztname;
    }

    public Termin(int terminid, String tiername, String hbname, String tiernachname, String tierarzt, String angabe, String date, String startzeit, String endezeit) {
        this.terminid = terminid;
        this.angabe = angabe;
        this.tiername = tiername;
        this.tiernachname = tiernachname;
        this.hbname = hbname;
        this.tierarztname = tierarzt;
        try {
            this.date = dateFormat.parse(date);
            this.startzeit = Time.valueOf(startzeit);
            this.endezeit = Time.valueOf(endezeit);
        } catch (ParseException ex) {
            Logger.getLogger(Termin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // set
    public void setTerminId(int terminid) {
        this.terminid = terminid;
    }
    public void setAngabe(String angabe) {
        this.angabe = angabe;
    }

    public void setDate(String date) {
        try {
            this.date = dateFormat.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(Termin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setStartzeit(String gegebenstartzeit) {
        Time start;
        try {
            start = new Time(timeformat.parse(gegebenstartzeit).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        startzeit = start;
    }

    public void setEndezeit(String gegebenendezeit) {
        Time ende;
        try {
            ende = new Time(timeformat.parse(gegebenendezeit).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        endezeit = ende;
    }

    public void setTiername(String tiername) {
        this.tiername = tiername;
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

    // get
    public static Integer getTerminid(Termin termin) {
        return terminid;
    }

    public String getAngabe() {
        return angabe;
    }

    public Date getDate() {
        return date;
    }

    public Time getStartzeit() {
        return startzeit;
    }

    public Time getEndezeit() {
        return endezeit;
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

    public static boolean controlDate(int terminid) throws SQLException {
        if(terminid == -1) return false;
        Termin termin = TableControllerAppointment.getTermin(terminid);
        Date termindate = termin.getDate();
        Date today = new Date();
        if(termindate.after(today)) {
            return true;
        }
        return false;
    }
}
