package hr.obskc.pziher.proizvodi;

public class Main {
    public static void main(String[] args) {
        Proizvod first = new Proizvod("  Čaj od kamilice  ", 3.50, 12);
        Proizvod second = new Proizvod("Kava mljevena", 6.20, 25);
        Proizvod third = new Proizvod("Mlijeko svježe", 1.45);

        Proizvod[] katalog = { first, second, third };

        int ukupnaKolicina = 0;
        for (Proizvod p : katalog) {
            ukupnaKolicina += p.getKolicina();
        }
        System.out.println("Ukupno količina na skladištu: " + ukupnaKolicina);
        System.out.println("Ukupno stvorenih proizvoda: " + Proizvod.getProizvodCount());
        
        Proizvod same = first;
        same.dodaj(5);
        System.out.println("Preko varijable first: " + first.getKolicina() + " komada.");
        
        int kolicinaVar = 50;
        dupliraj(kolicinaVar);
        System.out.println("Varijabla kolicinaVar nakon 'dupliranja': " + kolicinaVar);
        
        povecajCijenu(third);
        System.out.println("Cijena trećeg proizvoda nakon podizanja: " + third.getCijena());
        
        zamijeni(third);
        System.out.println("Naziv nakon zamjene (unutar metodu): " + third.getNaziv());
        
        String typed = new String("kava mljevena");
        System.out.println("== referenca :" + (typed == "kava mljevena"));
        System.out.println("equals sadržaj : " + typed.equals("kava mljevena"));
        System.out.println("jeNaziv metoda : " + second.jeNaziv(typed));

        String name = "kava mljevena";
        String newName = name.toUpperCase();
        System.out.println("Provjera imena: " + newName);
        
        StringBuilder popis = new StringBuilder("Popis proizvoda: ");
        for (Proizvod p : katalog) {
            popis.append(p.getNaziv()).append("; ");
        }
        System.out.println(popis);

        System.out.println("\n--- TESTIRANJE IZNIMAKA ---");
        try {
            first.prodaj(1000);
        } catch (IllegalArgumentException e) {
            System.out.println("Odbijeno: " + e.getMessage());
        }

        try {
            second.setCijena(-5.00);
        } catch (IllegalArgumentException e) {
            System.out.println("Odbijeno: " + e.getMessage());
        }
    }
    
    static void dupliraj(int x) {
        x = x * 2;
    }
    
    static void povecajCijenu(Proizvod p) {
        p.setCijena(p.getCijena() + 1.00);
    }
    
    static void zamijeni(Proizvod p) {
        p = new Proizvod("Kruh bijeli", 1.20, 10);
    }
}