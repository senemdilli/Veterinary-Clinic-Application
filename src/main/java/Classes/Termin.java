package Classes;

import tierklinik.FullDB;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Termin {

    private static Integer terminid;
    private String angabe;
    private LocalDate date;
    private String startzeit;
    private String endezeit;
    private String tiername;
    private String tiernachname;
    private String hbname;
    private String tierarztname;
    private String zustand = "nicht";
    static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
    static DateTimeFormatter dateFormat2 = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

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
        this.date = LocalDate.parse(date, dateFormat);
        this.startzeit = startzeit;
        this.endezeit = startzeit;
    }

    public Termin(String angabe, String tiername, String tiernachname, String hbname, String tierarztname, int terminid, String zustand, String date, String startzeit, String endezeit) throws SQLException {
        this.angabe = angabe;
        this.tiername = tiername;
        this.tiernachname = tiernachname;
        this.hbname = hbname;
        this.tierarztname = tierarztname;
        this.terminid = terminid;
        this.zustand = zustand;
        this.date = LocalDate.parse(date, dateFormat);
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

    public void setDate(String date){
        this.date = LocalDate.parse(date, dateFormat);
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

    public LocalDate getDate() {
        return date;
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

    public static boolean controlDate(int terminid) throws SQLException {
        if(terminid == -1) return false;
        Termin termin = FullDB.getTermin(terminid);
        LocalDate termindate = termin.getDate();
        LocalDate today = LocalDate.now();
        return termindate.isAfter(today);
    }
}
