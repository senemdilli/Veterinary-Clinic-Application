package Classes;

import tierklinik.FullDB;

import java.sql.SQLException;

public class Zahlung {
    private static Integer zahlungid;
    private String zahlungsart;
    private Double zahlungsbetrag;
    private String zustand;
    private static Integer tierid;
    private String name;
    private String nachname;
    private String hbname;
    public Zahlung(String zahlungsart, double zahlungsbetrag, Integer tierid) throws SQLException {
        zahlungid = FullDB.getZahlungid();
        Zahlung.tierid = tierid;
        this.zahlungsart = zahlungsart;
        this.zahlungsbetrag = zahlungsbetrag;
        zustand  = "nicht";
    }
    public Zahlung(String zahlungsart, double zahlungsbetrag, Integer zahlungid, String zustand, Integer tierid, String name, String nachname, String hbname) throws SQLException {
        zahlungid = FullDB.getZahlungid();
        this.zahlungsart = zahlungsart;
        this.zahlungsbetrag = zahlungsbetrag;
        this.zustand = zustand;
        Zahlung.tierid = tierid;
        this.name = name;
        this.nachname = nachname;
        this.hbname = hbname;
    }

    // Setter
    public static void setZahlungid(Integer zahlungid) {
        Zahlung.zahlungid = zahlungid;
    }

    public void setZahlungsart(String zahlungsart) {
        this.zahlungsart = zahlungsart;
    }

    public void setZahlungsbetrag(Double zahlungsbetrag) {
        this.zahlungsbetrag = zahlungsbetrag;
    }

    public void setZustand(String zustand) {
        this.zustand = zustand;
    }
    public void setTierid(Integer tierid) {
        Zahlung.tierid = tierid;
    }

    // Getter
    public static Integer getZahlungid() {
        return zahlungid;
    }

    public String getZahlungsart() {
        return zahlungsart;
    }

    public Double getZahlungsbetrag() {
        return zahlungsbetrag;
    }

    public String getZustand() {
        return zustand;
    }
    public static Integer getTierid() {
        return tierid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getHbname() {
        return hbname;
    }

    public void setHbname(String hbname) {
        this.hbname = hbname;
    }
}
