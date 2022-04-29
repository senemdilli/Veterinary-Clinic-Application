package Classes;

public class Personal extends Person {

    private int personalnummer;
    private double gehalt;
    private String arbeit;

    public Personal(String name, String nachname) {
        super(name, nachname);
    }

    public Personal(int id, String name, String nachname, int telefonnummer, String email, String adresse, String arbeit, int personalnummer, double gehalt) {
        super(id, name,nachname,telefonnummer,email,adresse);
        this.arbeit = arbeit;
        this.gehalt = gehalt;
        this.personalnummer = personalnummer;
    }

    // set
    public void setPersonalnummer(int personalnummer) {
        this.personalnummer = personalnummer;
    }

    public void setGehalt(double gehalt) {
        this.gehalt = gehalt;
    }

    public void setArbeit(String arbeit) {
        this.arbeit = arbeit;
    }

    // get
    public double getGehalt() {
        return gehalt;
    }

    public int getPersonalnummer() {
        return personalnummer;
    }

    public String getArbeit() {
        return arbeit;
    }
}