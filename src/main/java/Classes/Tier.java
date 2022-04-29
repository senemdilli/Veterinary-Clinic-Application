package Classes;

public class Tier extends Person {

    private String tier_id;
    private double gewicht;
    private double lange;
    private String operationen;
    private String geschichte;
    private String hb_name;
    private String hb_nachname;
    private String hb_id;
    private double kontostand;

    public Tier(String name, String nachname, String tier_id, String hb_id) {
        super(name, nachname);
        this.tier_id = tier_id;
        this.hb_id = hb_id;
    }

    //set
    public void setGeschichte(String geschichte) {
        this.geschichte = geschichte;
    }

    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }

    public void setHb_name(String hb_name) {
        this.hb_name = hb_name;
    }

    public void setHb_nachname(String hb_nachname) {
        this.hb_nachname = hb_nachname;
    }

    public void setHb_id(String hb_id) {
        this.hb_id = hb_id;
    }

    public void setKontostand(double kontostand) {
        this.kontostand = kontostand;
    }

    public void setLange(double lange) {
        this.lange = lange;
    }

    public void setOperationen(String operationen) {
        this.operationen = operationen;
    }

    public void setTier_id(String tier_id) {
        this.tier_id = tier_id;
    }

    //get
    public double getGewicht() {
        return gewicht;
    }

    public double getKontostand() {
        return kontostand;
    }

    public double getLange() {
        return lange;
    }

    public String getGeschichte() {
        return geschichte;
    }

    public String getHb_id() {
        return hb_id;
    }

    public String getHb_name() {
        return hb_name;
    }

    public String getHb_nachname() {
        return hb_nachname;
    }

    public String getOperationen() {
        return operationen;
    }

    public String getTier_id() {
        return tier_id;
    }
}