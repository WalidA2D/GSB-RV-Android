package fr.gsb.rv.entites;
import org.junit.Test;
import static org.junit.Assert.*;

public class VisiteurTest {

    @Test
    public void testConstructor() {
        Visiteur visiteur = new Visiteur("1234", "mdp", "Doe", "John");
        assertEquals("1234", visiteur.getMatricule());
        assertEquals("mdp", visiteur.getMdp());
        assertEquals("Doe", visiteur.getNom());
        assertEquals("John", visiteur.getPrenom());
    }

    @Test
    public void testSetters() {
        Visiteur visiteur = new Visiteur("1234", "mdp", "Doe", "John");

        visiteur.setMatricule("5678");
        visiteur.setMdp("nouveau_mdp");
        visiteur.setNom("Smith");
        visiteur.setPrenom("Jane");

        assertEquals("5678", visiteur.getMatricule());
        assertEquals("nouveau_mdp", visiteur.getMdp());
        assertEquals("Smith", visiteur.getNom());
        assertEquals("Jane", visiteur.getPrenom());
    }

    @Test
    public void testToString() {
        Visiteur visiteur = new Visiteur("1234", "mdp", "Doe", "John");
        String expected = "Visiteur{" +
                "matricule='1234'"+
                ", mdp='mdp'"+
                ", nom='Doe'"+
                ", prenom='John'" +
                '}';
        assertEquals(expected, visiteur.toString());
    }
}

