package fr.gsb;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.GregorianCalendar;

import fr.gsb.rv.technique.Session;

public class SaisieRvActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    TextView session ,tvDate;
    Button retour , selectionne, clear;
    EditText bilan ;

    private GregorianCalendar laDateCommande ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saisie_date);
        session = findViewById(R.id.name);
        retour = findViewById(R.id.retourAcceuil) ;
        tvDate = findViewById(R.id.tvDateSelectionnee) ;
        selectionne = findViewById(R.id.b_selectionner_date) ;
        clear = findViewById(R.id.clear) ;
        bilan = findViewById(R.id.bilan) ;

        session.setText(Session.getSession().getLeVisiteur().getNom() + " " + Session.getSession().getLeVisiteur().getPrenom());
        Log.i("GSB_ACTIVITES_DATE", "Création de l'activité saisie date");
    }

    public void retour(View vue){
        Intent retour = new Intent(this,MenuRvActivity.class);
        startActivity(retour);
        Log.i("GSB_ACTIVITES_DATE", "Retour a l'acceuil Ok");
    }

    public void selectionnerDateCommande(View vue){

        GregorianCalendar aujourdhui = new GregorianCalendar();
        int jour = aujourdhui.get(Calendar.DAY_OF_MONTH);
        int mois = aujourdhui.get(Calendar.MONTH);
        int annee = aujourdhui.get(Calendar.YEAR);

        new DatePickerDialog(this,this,annee,mois,jour).show();
    }

    public void onDateSet(DatePicker view, int annee, int mois, int jour){
        String dateCommande = String.format("%02d/%02d/%04d", jour,mois+1,annee);

        tvDate.setText(dateCommande);

        laDateCommande = new GregorianCalendar(annee,mois,jour);
    }

    public void clear(View vue){
        bilan.setText("");

        Log.i("GSB_ACTIVITES_DATE", "Initialisation du bilan");
    }
}
