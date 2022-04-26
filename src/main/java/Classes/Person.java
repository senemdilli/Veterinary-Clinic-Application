package Classes;

public class Person {

    private int id;
    private String name;
    private String nachname;
    private String adresse;
    private int telefonnummer;
    private String email;

    public Person(String name, String nachname) {
        this.name = name;
        this.nachname = nachname;
    }

    // set
    public void setId(int id) {
        this.id = id;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTelefonnummer(int telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    // get
    public int getId() {
        return id;
    }

    public int getTelefonnummer() {
        return telefonnummer;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public String getNachname() {
        return nachname;
    }

    public String getName() {
        return name;
    }
}