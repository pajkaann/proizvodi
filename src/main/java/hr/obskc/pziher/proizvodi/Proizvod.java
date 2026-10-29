package hr.obskc.pziher.proizvodi;

public class Proizvod {

    // Podatci na razini klase
    private static int proizvodCount = 0;

    private final String sifra;
    private String naziv;
    private double cijena;
    private int kolicina;

    public Proizvod(String naziv, double cijena, int kolicina) {
        setNaziv(naziv);
        setCijena(cijena);
        checkKolicina(kolicina); // Neovisna provjera bez settera
        this.kolicina = kolicina;

        proizvodCount++; // Registriramo novi proizvod
        this.sifra = "P" + String.format("%03d", proizvodCount); // npr. P + 001 = P001
    }

    public Proizvod(String naziv, double cijena) {
        this(naziv, cijena, 0);
    }

    public String getSifra() {
        return sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public double getCijena() {
        return cijena;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setNaziv(String naziv) {
        if (naziv == null || naziv.isBlank()) {
            throw new IllegalArgumentException("Naziv ne smije biti prazan.");
        }
        this.naziv = naziv.strip();
    }

    public void setCijena(double cijena) {
        if (cijena < 0) {
            throw new IllegalArgumentException("Cijena ne može biti negativna.");
        }
        this.cijena = cijena;
    }

    private void checkKolicina(int kolicina) {
        if (kolicina < 0) {
            throw new IllegalArgumentException("Količina ne može biti negativna.");
        }
    }

    public void dodaj(int kolicinaZaDodavanje) {
        checkKolicina(kolicinaZaDodavanje);
        this.kolicina += kolicinaZaDodavanje;
    }

    public void prodaj(int kolicinaZaProdaju) {
        checkKolicina(kolicinaZaProdaju);
        if (kolicinaZaProdaju > this.kolicina) {
            throw new IllegalArgumentException(
                    "Nema dovoljno proizvoda na skladištu, traženo: " + kolicinaZaProdaju + ", dostupno: " + this.kolicina + "."
            );
        }
        this.kolicina -= kolicinaZaProdaju;
    }

    public double ukupnaVrijednost() {
        return cijena * kolicina;
    }

    public double ukupnaVrijednost(double popust) {
        if (popust < 0 || popust > 100) {
            throw new IllegalArgumentException("Popust mora biti između 0 i 100 posto.");
        }
        return ukupnaVrijednost() * (1 - popust / 100);
    }

    public String opis() {
        return sifra + " | " + naziv + " | " + String.format("%.2f", cijena) + " EUR | "
                + kolicina + " kom.";
    }

    public boolean jeNaziv(String nazivZaProvjeru) {
        return naziv.equalsIgnoreCase(nazivZaProvjeru.strip());
    }

    public static int getProizvodCount() {
        return proizvodCount;
    }

    public static boolean isValidSifra(String sifra) {
        if (sifra == null || sifra.length() != 4 || !sifra.startsWith("P")) {
            return false;
        }

        for (int i = 1; i < sifra.length(); i++) {
            if (!Character.isDigit(sifra.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}