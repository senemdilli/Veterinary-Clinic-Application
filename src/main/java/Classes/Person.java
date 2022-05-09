package Classes;

public class Person {

    private Integer id;
    private String name;
    private String nachname;
    private String adresse;
    private Integer telefonnummer;
    private String email;

    public Person(Integer id, String name, String nachname) {
        this.name = name;
        this.nachname = nachname;
    }

    public Person(Integer id, String name, String nachname, Integer telefonnummer, String email, String adresse) {
        this.id = id;
        this.name = name;
        this.nachname = nachname;
        this.adresse = adresse;
        this.telefonnummer = telefonnummer;
        this.email = email;
    }

    // set
    public void setId(Integer id) {
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

    public void setTelefonnummer(Integer telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    // get
    public Integer getId() {
        return id;
    }

    public Integer getTelefonnummer() {
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