package Classes;

public class Tierarzt extends Personal {

    //private int personalnummer;
    public String fachgebiet;
    public String operationen;
    //private double gehalt;

    public Tierarzt(String name, String nachname) {
        super(name, nachname);
    }

    //set
    /*public void setPersonalnummer(int personalnummer) {
        this.personalnummer = personalnummer;
    }*/

    public void setOperationen(String operationen) {
        this.operationen = operationen;
    }

    /*public void setGehalt(double gehalt) {
        this.gehalt = gehalt;
    } */

    public void setFachgebiet(String fachgebiet) {
        this.fachgebiet = fachgebiet;
    }

    //get
    /*public int getPersonalnummer() {
        return personalnummer;
    }*/

    public String getOperationen() {
        return operationen;
    }

    public String getFachgebiet() {
        return fachgebiet;
    }

    /*public double getGehalt() {
        return gehalt;
    }*/

}