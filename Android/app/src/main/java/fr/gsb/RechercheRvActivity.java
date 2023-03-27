package fr.gsb;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import fr.gsb.rv.technique.Session;


public class RechercheRvActivity extends AppCompatActivity {

    Button retour ;
    TextView session ;

    private static final String [] lesMois = {"1","2","3","4","5","6","7","8","9","10","11","12"} ;
    Spinner mois ;

    private static final String [] lesAnnees = {"2017","2018","2019","2020","2021","2022","2023"} ;
    Spinner annees ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recherche);
        retour = findViewById(R.id.retourAcceuil) ;
        session = findViewById(R.id.name);

        mois = (Spinner) findViewById(R.id.mois) ;
        ArrayAdapter<String> aaMois = new ArrayAdapter<String>(
                this ,
                android.R.layout.simple_spinner_item ,
                lesMois
        ) ;
        aaMois.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        mois.setAdapter(aaMois);

        annees = (Spinner) findViewById(R.id.annee) ;
        ArrayAdapter<String> aaAnnees = new ArrayAdapter<String>(
                this ,
                android.R.layout.simple_spinner_item ,
                lesAnnees
        ) ;
        aaAnnees.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        annees.setAdapter(aaAnnees);

        Bundle resultat = new Bundle() ;
        resultat.putString("result", mois.toString() + " " + annees.toString());

        Intent intentionEnvoyer = new Intent(this,ListeRvActicity.class) ;
        intentionEnvoyer.putExtras(resultat);
        startActivity(intentionEnvoyer);

        session.setText(Session.getSession().getLeVisiteur().getNom() + " " + Session.getSession().getLeVisiteur().getPrenom());
        Log.i("GSB_CONSULTE_ACTIVITY", "Création de l'activité de consultation");
    }

    public void retour(View vue){
        Intent retour = new Intent(this,MenuRvActivity.class);
        startActivity(retour);
        Log.i("GSB_CONSULTE_ACTIVITY", "Retour a l'acceuil Ok");
    }

    public void liste(View vue){
        Intent liste = new Intent(this,ListeRvActicity.class);
        startActivity(liste);
        Log.i("GSB_CONSULTE_ACTIVITY", "Afficher la liste Ok");
    }


}
