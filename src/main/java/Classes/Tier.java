package Classes;

public class Tier extends Person {

    private Integer tierid;
    private Integer hbid;
    private String hbname;
    private Double kontostand;

    public Tier(Integer id, String name, String nachname) {
        super(id,name,nachname);
    }
    public Tier(Integer id, String name, String nachname, Integer telefonnummer, String email, String adresse, String hbname, Integer hbid, Double kontostand) {
        super(id, name, nachname, telefonnummer, email, adresse);
        this.hbname = hbname;
        this.hbid = hbid;
        this.kontostand = kontostand;
    }

    // Setter
    public void setHbname(String hbname) {
        this.hbname = hbname;
    }

    public void setKontostand(Double kontostand) {
        this.kontostand = kontostand;
    }

    public void setTierid(Integer tierid) {
        this.tierid = tierid;
    }

    public void setHBid(Integer hbid) {
        this.hbid = hbid;
    }

    // Getter

    public Double getKontostand() {
        return kontostand;
    }

    public String getHbname() {
        return hbname;
    }

    public Integer getTierid() {
        return tierid;
    }

    public Integer getHbid() {
        return hbid;
    }
}