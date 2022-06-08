package Classes;

import java.util.ArrayList;

public class Termin {

    private Integer terminid;
    private String angabe;
    private String date;
    private String startzeit;
    private String endezeit;
    private String tiername;
    private String tiernachname;
    private String hbname;
    private String tierarztname;

    private static ArrayList<Termin> terminList = new ArrayList<>();

    public Termin(String angabe, String date, String startzeit, String endezeit, String tiername, String tiernachname, String hbname, String tierarztname) {
        this.terminid = terminList.size();
        this.angabe = angabe;
        this.date = date;
        this.startzeit = startzeit;
        this.endezeit = endezeit;
        this.tiername = tiername;
        this.tiernachname = tiernachname;
        this.hbname = hbname;
        this.tierarztname = tierarztname;
    }

    // set
    public void setTerminid(Integer terminid) {
        this.terminid = terminid;
    }

    public void setAngabe(String angabe) {
        this.angabe = angabe;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStartzeit(String startzeit) {
        this.startzeit = startzeit;
    }

    public void setEndezeit(String endezeit) {
        this.endezeit = endezeit;
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
    public static Integer getTerminid() {
        return terminList.size();
    }

    public String getAngabe() {
        return angabe;
    }

    public String getDate() {
        return date;
    }

    public String getStartzeit() {
        return startzeit;
    }

    public String getEndezeit() {
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
}
