package Classes;

class Tierarzt extends Person {

    private String personalnummer;
    public String fachgebiet;
    public String operationen;
    private double gehalt;

    public Tierarzt(String name, String nachname, String personalnummer) {
        super(name, nachname);
        this.personalnummer = personalnummer;
    }

    //set
    public void setPersonalnummer(String personalnummer) {
        this.personalnummer = personalnummer;
    }

    public void setOperationen(String operationen) {
        this.operationen = operationen;
    }

    public void setGehalt(double gehalt) {
        this.gehalt = gehalt;
    }

    public void setFachgebiet(String fachgebiet) {
        this.fachgebiet = fachgebiet;
    }

    //get
    public String getPersonalnummer() {
        return personalnummer;
    }

    public String getOperationen() {
        return operationen;
    }

    public String getFachgebiet() {
        return fachgebiet;
    }

    public double getGehalt() {
        return gehalt;
    }

}