# Platforma za nekretnine - Key To The Castle
Ova aplikacija predstavlja softversku platformu za oglašavanje, pretragu i zakazivanje obilazaka nekretnina. Sistem podržava različite uloge korisnika, upravljanje nekretninama, komunikaciju između korisnika i agenata, kao i administraciju celokupne platforme.


## Funkcionalnosti

### Uloge u sistemu
- **Korisnik** – Registruje se samostalno. Može da pretražuje i pregleda nekretnine, zakaže obilazak, ocenjuje nekretninu (LIKE/DISLIKE), i ocenjuje agenta nakon obilaska.
- **Agent** – Dodaje ga vlasnik agencije. Može da unosi i upravlja nekretninama, prihvata/odbijа zahteve za obilazak, vidi svoj kalendar.
- **Vlasnik agencije** – Može sve kao i agent, uz dodatne mogućnosti dodavanja/uklanjanja agenata i pregled statistike popularnosti.
- **Administrator** – Dodaje vlasnike agencija, deaktivira naloge, sakriva nekretnine i prima mesečne izveštaje o zaradi platforme.

### Pretraga nekretnina
Korisnik može da pretražuje nekretnine po sledećim parametrima:
- Lokacija
- Površina (opseg)
- Cena (opseg)
- Tip nekretnine (npr. Kuća, Stan, Kancelarija…)
- Status (Prodaja / Izdavanje)

### Zakazivanje obilaska
- Korisnik bira datum i vreme obilaska.
- Agent dobija notifikaciju i može da prihvati ili odbije zahtev.
- Nakon obilaska, korisnik može da oceni agenta.

### Ocene i popularnost
- LIKE/DISLIKE je vidljiv samo korisniku.
- Agenti se ocenjuju numerički (uz komentar).
- Popularnost se računa na osnovu:
  - Broja pregleda nekretnine
  - Broja LIKE-ova
  - Broja zakazanih obilazaka

### Zarada platforme
- Platforma automatski uzima proviziju:
  - **0.1%** za prodaju
  - **1%** za izdavanje
- Administrator dobija mesečne izveštaje o ukupnoj zaradi.

## Licenca
Ovaj projekat je razvijen kao deo školskog zadatka iz predmeta **Specifikacija softverskih sistema** (2023/2024). Nema komercijalnu upotrebu.
