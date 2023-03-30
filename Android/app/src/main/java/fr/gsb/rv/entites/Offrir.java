package fr.gsb.rv.entites;

public class Offrir {
    private String vis_matricule ;
    private String rap_num ;
    private String med_depotlegal ;
    private String off_quantite ;

    public Offrir(String vis_matricule, String rap_num, String med_depotlegal, String off_quantite) {
        this.vis_matricule = vis_matricule;
        this.rap_num = rap_num;
        this.med_depotlegal = med_depotlegal;
        this.off_quantite = off_quantite;
    }

    public Offrir() {

    }

    public String getVis_matricule() {
        return vis_matricule;
    }

    public void setVis_matricule(String vis_matricule) {
        this.vis_matricule = vis_matricule;
    }

    public String getRap_num() {
        return rap_num;
    }

    public void setRap_num(String rap_num) {
        this.rap_num = rap_num;
    }

    public String getMed_depotlegal() {
        return med_depotlegal;
    }

    public void setMed_depotlegal(String med_depotlegal) {
        this.med_depotlegal = med_depotlegal;
    }

    public String getOff_quantite() {
        return off_quantite;
    }

    public void setOff_quantite(String off_quantite) {
        this.off_quantite = off_quantite;
    }

    @Override
    public String toString() {
        return "Offrir{" +
                "vis_matricule='" + vis_matricule + '\'' +
                ", rap_num='" + rap_num + '\'' +
                ", med_depotlegal='" + med_depotlegal + '\'' +
                ", off_quantite='" + off_quantite + '\'' +
                '}';
    }
}
