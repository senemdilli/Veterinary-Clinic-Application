package Classes;

import tierklinik.FullDB;

import java.sql.SQLException;

public class Zahlung {
    private static Integer zahlungid;
    private String zahlungsart;
    private Double zahlungsbetrag;
    private String zustand;
    private static Integer tierid;
    public Zahlung(String zahlungsart, double zahlungsbetrag, Integer tierid) throws SQLException {
        zahlungid = FullDB.getZahlungid();
        this.tierid = tierid;
        this.zahlungsart = zahlungsart;
        this.zahlungsbetrag = zahlungsbetrag;
        zustand  = "nicht";
    }
    public Zahlung(String zahlungsart, double zahlungsbetrag, Integer zahlungid, String zustand, Integer tierid, String tiername, String tiernachname, String hbname) throws SQLException {
        zahlungid = FullDB.getZahlungid();
        this.zahlungsart = zahlungsart;
        this.zahlungsbetrag = zahlungsbetrag;
        this.zustand = zustand;
        this.tierid = tierid;
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
        this.tierid = tierid;
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
}
