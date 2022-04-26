package Classes;

class Personal extends Person {

    private String personalnummer;
    private double gehalt;

    public Personal(String name, String nachname, String personalnummer) {
        super(name, nachname);
        this.personalnummer = personalnummer;
    }

    // set
    public void setPersonalnummer(String Personalnummer) {
        this.personalnummer = personalnummer;
    }

    public void setGehalt(double gehalt) {
        this.gehalt = gehalt;
    }

    // get
    public double getGehalt() {
        return gehalt;
    }

    public String getPersonalnummer() {
        return personalnummer;
    }
}