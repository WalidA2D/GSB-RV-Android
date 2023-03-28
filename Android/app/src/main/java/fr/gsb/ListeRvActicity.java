package fr.gsb;

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

import java.nio.BufferUnderflowException;

import fr.gsb.rv.technique.Session;

public class ListeRvActicity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Button retour ;
    TextView session, rapport;
    ListView liste ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_rapport);
        retour = findViewById(R.id.retourForm) ;
        session = findViewById(R.id.name);
        rapport = (TextView)findViewById(R.id.rapport);
        liste = (ListView)findViewById(R.id.listeRapport);

        Bundle annee = this.getIntent().getExtras() ;
        String listeAnnee = annee.getString("year") ;

        Bundle mois = this.getIntent().getExtras() ;
        String listeMois = mois.getString("month") ;

        Bundle numero = this.getIntent().getExtras() ;
        String listeNumero= String.valueOf(numero.getInt("numero"));

        Bundle date = this.getIntent().getExtras() ;
        String listeDate= date.getString("date_visite") ;

        Bundle bilan = this.getIntent().getExtras() ;
        String listeBilan= bilan.getString("bilan") ;

        String[] rapports = {listeNumero,listeDate,listeBilan};

        ArrayAdapter<String> adaptateur = new ArrayAdapter<String>(
                this ,
                android.R.layout.simple_list_item_1,
                rapports
        ) ;
        liste.setAdapter(adaptateur);
        liste.setOnItemClickListener(this);

        session.setText(Session.getSession().getLeVisiteur().getNom() + " " + Session.getSession().getLeVisiteur().getPrenom());
        Log.i("GSB_LISTE_ACTIVITY", "Création de l'activité liste des rapport");
    }

    public void retour(View vue){
        Intent retour = new Intent(this,RechercheRvActivity.class);
        startActivity(retour);
        Log.i("GSB_LISTE_ACTIVITY", "Retour au formulaire Ok");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View vue, int position, long l) {
        Bundle numero = this.getIntent().getExtras() ;
        String listeNumero= String.valueOf(numero.getInt("numero"));

        Bundle date = this.getIntent().getExtras() ;
        String listeDate= date.getString("date_visite") ;

        Bundle bilan = this.getIntent().getExtras() ;
        String listeBilan= bilan.getString("bilan") ;

        String[] rapports = {listeNumero,listeDate,listeBilan};

        String rapportSelectionne = rapports[position] ;
        rapport.setText(rapportSelectionne);

    }
}
