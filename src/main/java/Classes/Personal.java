package Classes;

class Personal extends Person {

    private int personalnummer;
    private double gehalt;

    public Personal(String name, String nachname) {
        super(name, nachname);
    }

    // set
    public void setPersonalnummer(int personalnummer) {
        this.personalnummer = personalnummer;
    }

    public void setGehalt(double gehalt) {
        this.gehalt = gehalt;
    }

    // get
    public double getGehalt() {
        return gehalt;
    }

    public int getPersonalnummer() {
        return personalnummer;
    }
}