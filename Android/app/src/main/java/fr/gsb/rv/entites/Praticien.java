package fr.gsb.rv.entites;

public class Praticien {
    private int numero;
    private String nom;
    private String ville;
    private double coefNotoriete;
    private String dateDerniereVisite;
    private int dernierCoefConfiance;

    private String adresse;

    private String codePostal;

    private String prenom;


    public String getAdresse() {
        return adresse;
    }



    public Praticien(int numero, String nom, String ville, double coefNotoriete, String dateDerniereVisite, int dernierCoefConfiance) {
        this.numero = numero;
        this.nom = nom;
        this.ville = ville;
        this.coefNotoriete = coefNotoriete;
        this.dateDerniereVisite = dateDerniereVisite;
        this.dernierCoefConfiance = dernierCoefConfiance;
    }
    public Praticien() {

    }



    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public double getCoefNotoriete() {
        return coefNotoriete;
    }

    public void setCoefNotoriete(double coefNotoriete) {
        this.coefNotoriete = coefNotoriete;
    }

    public String getDateDerniereVisite() {
        return dateDerniereVisite;
    }

    public void setDateDerniereVisite(String dateDerniereVisite) {
        this.dateDerniereVisite = dateDerniereVisite;
    }

    public int getDernierCoefConfiance() {
        return dernierCoefConfiance;
    }

    public void setDernierCoefConfiance(int dernierCoefConfiance) {
        this.dernierCoefConfiance = dernierCoefConfiance;
    }

    public void setAdresse(String adresse) {this.adresse = adresse;}

    public String getCodePostal() {return codePostal;}

    public void setCodePostal(String codePostal) {this.codePostal = codePostal;}

    public String getPrenom() {return prenom;}

    public void setPrenom(String prenom) {this.prenom = prenom;}

    @Override
    public String toString() {
        return "Praticien{" +
                "numero='" + numero + '\'' +
                ", nom='" + nom + '\'' +
                ", ville='" + ville + '\'' +
                ", coefNotoriete=" + coefNotoriete +
                ", dateDerniereVisite=" + dateDerniereVisite +
                ", dernierCoefConfiance=" + dernierCoefConfiance +
                '}';
    }
}
