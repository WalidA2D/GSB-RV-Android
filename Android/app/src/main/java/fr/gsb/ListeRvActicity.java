package fr.gsb;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.nio.BufferUnderflowException;

import fr.gsb.rv.technique.Session;

public class ListeRvActicity extends AppCompatActivity {

    Button retour ;
    TextView session ,liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_rapport);
        retour = findViewById(R.id.retourForm) ;
        session = findViewById(R.id.name);
        liste = findViewById(R.id.liste);

        Bundle resultat = this.getIntent().getExtras() ;
        String listeRapport = resultat.getString("result") ;
        liste.setText(listeRapport);

        session.setText(Session.getSession().getLeVisiteur().getNom() + " " + Session.getSession().getLeVisiteur().getPrenom());
        Log.i("GSB_LISTE_ACTIVITY", "Création de l'activité liste des rapport");
    }

    public void retour(View vue){
        Intent retour = new Intent(this,RechercheRvActivity.class);
        startActivity(retour);
        Log.i("GSB_LISTE_ACTIVITY", "Retour au formulaire Ok");
    }
}
