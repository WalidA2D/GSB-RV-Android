package fr.gsb.rv.entites;


public class RapportVisite {

    private int numero;

    private String dateVisite;

    private String dateRedaction;

    private String bilan;

    private String motif;

    private int coefConfiance;

    private boolean lu;

    private Visiteur leVisiteur;

    private Praticien lePraticien;



    public RapportVisite(int numero, String dateVisite, String dateRedaction, String bilan, String motif, int coefConfiance, boolean lu, Visiteur leVisiteur, Praticien lePraticien) {
        this.numero = numero;
        this.dateVisite = dateVisite;
        this.dateRedaction = dateRedaction;
        this.bilan = bilan;
        this.motif = motif;
        this.coefConfiance = coefConfiance;
        this.lu = lu;
        this.leVisiteur = leVisiteur;
        this.lePraticien = lePraticien;
    }

    public RapportVisite() {
    }


    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDateVisite() {
        return dateVisite;
    }

    public void setDateVisite(String dateVisite) {
        this.dateVisite = dateVisite;
    }

    public String getDateRedaction() {
        return dateRedaction;
    }

    public void setDateRedaction(String dateRedaction) {
        this.dateRedaction = dateRedaction;
    }

    public String getBilan() {
        return bilan;
    }

    public void setBilan(String bilan) {
        this.bilan = bilan;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public int getCoefConfiance() {
        return coefConfiance;
    }

    public void setCoefConfiance(int coefConfiance) {
        this.coefConfiance = coefConfiance;
    }

    public boolean isLu() {
        return lu;
    }

    public void setLu(boolean lu) {
        this.lu = lu;
    }

    public Visiteur getLeVisiteur() {
        return leVisiteur;
    }

    public void setLeVisiteur(Visiteur leVisiteur) {
        this.leVisiteur = leVisiteur;
    }

    public Praticien getLePraticien() {
        return lePraticien;
    }

    public void setLePraticien() {
        this.lePraticien = lePraticien;
    }


    @Override
    public String toString() {
        return "numero : " + this.getNumero() + "\n dateVisite : " + dateVisite + "\n dateRedaction : " + dateRedaction + "\n bilan : " + bilan + "\n motif : " + motif + "\n coefConfiance : " + coefConfiance + "\n lu : " + lu + "\n LePraticien : ";
    }
}
