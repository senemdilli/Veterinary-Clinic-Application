package Classes;

import tierklinik.TableControllerKontenstelle;

import java.sql.SQLException;

public class Zahlung {
    private static Integer zahlungid;
    private String zahlungsart;
    private Double zahlungsbetrag;
    private String hbname;
    private String tiername;
    private String nachname;
    private String zustand;

    public Zahlung(String zahlungsart, double zahlungsbetrag, String nachname, String hbname, String tiername) throws SQLException {
        zahlungid = TableControllerKontenstelle.getZahlungid();
        this.zahlungsart = zahlungsart;
        this.zahlungsbetrag = zahlungsbetrag;
        this.hbname = hbname;
        this.nachname = nachname;
        this.tiername = tiername;
        zustand  = "nicht";
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

    public void setHbname(String hbname) {
        this.hbname = hbname;
    }

    public void setTiername(String tiername) {
        this.tiername = tiername;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setZustand(String zustand) {
        this.zustand = zustand;
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

    public String getHbname() {
        return hbname;
    }

    public String getTiername() {
        return tiername;
    }

    public String getNachname() {
        return nachname;
    }

    public String getZustand() {
        return zustand;
    }
}
