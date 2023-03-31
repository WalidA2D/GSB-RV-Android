package fr.gsb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import fr.gsb.rv.technique.Session;

public class MenuRvActivity extends AppCompatActivity {

    Button deco , consulte;
    TextView session ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceuil);
        deco = findViewById(R.id.deco) ;
        session = findViewById(R.id.session);
        consulte = findViewById(R.id.consulter);

        session.setText(Session.getSession().getLeVisiteur().getNom() + " " + Session.getSession().getLeVisiteur().getPrenom());
        Log.i("GSB_ACTIVITES_SECONDARY", "Création de l'activité secondaire");
    }

    public void deconnection(View vue){
        Intent deco = new Intent(this,MainActivity.class);
        Session.getSession().fermer();
        startActivity(deco);
        Log.i("GSB_ACTIVITES_SECONDARY", "Déconnection Ok");
    }

    public void consulte(View vue){
        Intent consulte = new Intent(this,RechercheRvActivity.class) ;
        startActivity(consulte);
    }

    public void saisie(View vue){
        Intent consulte = new Intent(this,SaisieRvActivity.class) ;
        startActivity(consulte);
    }
}
