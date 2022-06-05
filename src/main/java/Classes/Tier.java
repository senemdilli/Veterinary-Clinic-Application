package Classes;

public class Tier extends Person {

    private Integer tierid;
    private Double gewicht;
    private Double lange;
    private String operationen;
    private String geschichte;
    private Integer hbid;
    private String hbname;
    private Double kontostand;

    public Tier(Integer tierid, String name, String nachname, Integer telefonnummer, String email, String adresse, String hbname, Integer hbid, Double kontostand) {
        super(tierid, name, nachname, telefonnummer, email, adresse);
        this.hbname = hbname;
        this.hbid = hbid;
        this.kontostand = kontostand;
    }

    //set
    public void setGeschichte(String geschichte) {
        this.geschichte = geschichte;
    }

    public void setGewicht(Double gewicht) {
        this.gewicht = gewicht;
    }

    public void setHbName(String hbname) {
        this.hbname = hbname;
    }

    public void setKontostand(Double kontostand) {
        this.kontostand = kontostand;
    }

    public void setLange(Double lange) {
        this.lange = lange;
    }

    public void setOperationen(String operationen) {
        this.operationen = operationen;
    }

    public void setTierId(Integer tierid) {
        this.tierid = tierid;
    }

    public void setHBId(Integer hbid) {
        this.hbid = hbid;
    }

    //get
    public Double getGewicht() {
        return gewicht;
    }

    public Double getKontostand() {
        return kontostand;
    }

    public Double getLange() {
        return lange;
    }

    public String getGeschichte() {
        return geschichte;
    }

    public String getHbName() {
        return hbname;
    }

    public String getOperationen() {
        return operationen;
    }

    public Integer getTierId() {
        return tierid;
    }

    public Integer getHbid() {
        return hbid;
    }
}