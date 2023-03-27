package fr.gsb.rv.technique;

import fr.gsb.rv.entites.Visiteur;

public class Session {
    private static Session session = null;
    private Visiteur leVisiteur;

    private Session(Visiteur leVisiteur) {
        this.leVisiteur = leVisiteur;
    }

    public static void setSession(Session session) {
        Session.session = session;
    }

    public void setLeVisiteur(Visiteur leVisiteur) {
        this.leVisiteur = leVisiteur;
    }

    public static Session getSession() {
        return session;
    }

    public Visiteur getLeVisiteur() {
        return leVisiteur;
    }

    public static Session ouvrir(Visiteur leVisiteur) {
        if (session == null) {
            session = new Session(leVisiteur);
        }
        return session;
    }

    public void fermer() {
        session = null;
    }

    @Override
    public String toString() {
        return "Session{" +
                "session=" + session +
                "leVisiteur=" + leVisiteur +
                '}';
    }
}
