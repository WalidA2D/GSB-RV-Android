package fr.gsb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import fr.gsb.rv.technique.Session;

public class VisuEchantActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Button retour ;
    TextView session, medoc ;
    ListView liste ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.echantillon);
        retour = findViewById(R.id.retourListe) ;
        liste = findViewById(R.id.listeMedoc) ;
        medoc = findViewById(R.id.medoc);
        session = findViewById(R.id.name);


        Bundle quantite = this.getIntent().getExtras() ;
        String listeQuantite= quantite.getString("quantite") ;

        Bundle depot = this.getIntent().getExtras() ;
        String listedepot = depot.getString("nom") ;

        String[] medoc = {listeQuantite,listedepot};

        ArrayAdapter<String> adaptateur = new ArrayAdapter<String>(
                this ,
                android.R.layout.simple_list_item_1,
                medoc
        ) ;
        liste.setAdapter(adaptateur);
        liste.setOnItemClickListener(this);


        session.setText(Session.getSession().getLeVisiteur().getNom() + " " + Session.getSession().getLeVisiteur().getPrenom());
        Log.i("GSB_OFFRE_ACTIVITY", "Création de l'activité d'offr");
    }


    public void retour(View vue){
        Intent retour = new Intent(this,RechercheRvActivity.class);
        startActivity(retour);
        Log.i("GSB_LISTE_ACTIVITY", "Retour à la recherche Ok");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View vue, int position, long l) {

        Bundle quantite = this.getIntent().getExtras() ;
        String listeQuantite= quantite.getString("quantite") ;

        Bundle depot = this.getIntent().getExtras() ;
        String listedepot = depot.getString("nom") ;

        String[] rapports = { listeQuantite, listedepot};

        String rapportSelectionne = rapports[position] ;
        medoc.setText(rapportSelectionne);


    }
}
