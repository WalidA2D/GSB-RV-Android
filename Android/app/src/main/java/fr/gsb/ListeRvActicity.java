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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.List;

import fr.gsb.rv.entites.Offrir;
import fr.gsb.rv.entites.RapportVisite;
import fr.gsb.rv.technique.Ip;
import fr.gsb.rv.technique.Session;

public class ListeRvActicity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Button retour, echante ;
    TextView session, rapport;
    ListView liste ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_rapport);
        retour = findViewById(R.id.retourForm) ;
        echante = findViewById(R.id.echanteBtn) ;
        session = findViewById(R.id.name);
        rapport = (TextView)findViewById(R.id.rapport);
        liste = (ListView)findViewById(R.id.listeRapport);

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

    public void echantOffert(View vue) {
        try {
            Response.Listener<JSONArray> ecouteurReponse = new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(0);
                        List<Offrir> offr = new ArrayList<>();
                        for(int i =0 ;i<response.length(); i++ ) {
                            JSONObject element = response.getJSONObject(i);
                            String off_quantite = element.getString("off_quantite");
                            String med_nomcommercial = element.getString("med_nomcommercial");

                            Offrir offrir = new Offrir();
                            offrir.setOff_quantite(off_quantite);
                            offrir.setMed_depotlegal(med_nomcommercial);

                            System.out.println(offrir);
                            offr.add(offrir);

                        }

                        Log.v("GSB_LISTE_ACTIVITY", "200 Ok");


                        Bundle bundle = new Bundle();
                        ArrayList<String> offQuantiteList = new ArrayList<>();
                        ArrayList<String> medNomcommercialList = new ArrayList<>();

                        for (Offrir offrir : offr) {
                            offQuantiteList.add(offrir.getOff_quantite());
                            medNomcommercialList.add(offrir.getMed_depotlegal());
                        }

                        bundle.putStringArrayList("offQuantiteList", offQuantiteList);
                        bundle.putStringArrayList("medNomcommercialList", medNomcommercialList);

                        Intent intentionEnvoyer = new Intent(getApplicationContext(), VisuEchantActivity.class) ;
                        intentionEnvoyer.putExtras(bundle);
                        startActivity(intentionEnvoyer);

                    } catch(JSONException e) {
                        Log.e("GSB_LISTE_ACTIVITY", "JSON : " + e.getMessage());
                        Toast.makeText(getApplicationContext(), "Assurer vous de bien séléctionner le numéro de rapport", Toast.LENGTH_LONG).show();
                    }
                }
            };

            Response.ErrorListener ecouteurError = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("GSB_LISTE_ACTIVITY", "Erreur HTTP :" + " " + error.getMessage());
                    Toast.makeText(getApplicationContext(), "Assurer vous de bien séléctionner le numéro de rapport", Toast.LENGTH_LONG).show();
                }
            };

            JsonArrayRequest requete = new JsonArrayRequest(
                    Request.Method.GET,
                    "http://" + Ip.ip + "/rapports/echantillons/" + Session.getSession().getLeVisiteur().getMatricule() + "/" + rapport.getText().toString() ,
                    null,
                    ecouteurReponse,
                    ecouteurError
            );

            RequestQueue fileRequetes = Volley.newRequestQueue(this);
            fileRequetes.add(requete);
        } catch (Exception e) {
            Log.e("GSB_LISTE_ACTIVITY", "Exception : " + e.getMessage());
            Toast.makeText(getApplicationContext(), "Assurer vous de bien séléctionner le numéro de rapport", Toast.LENGTH_LONG).show();
        }
    }   /*
        Bundle numero = this.getIntent().getExtras() ;
        String listeNumero= String.valueOf(numero.getInt("numero"));

        Intent echante = new Intent(this,VisuEchantActivity.class);
        startActivity(echante);

         */

    @Override
    public void onItemClick(AdapterView<?> adapterView, View vue, int position, long l) {
        Bundle date = this.getIntent().getExtras() ;
        String listeDate= date.getString("date_visite") ;

        Bundle bilan = this.getIntent().getExtras() ;
        String listeBilan= bilan.getString("bilan") ;

        Bundle numero = this.getIntent().getExtras() ;
        String listeNumero= String.valueOf(numero.getInt("numero"));

        String[] rapports = {listeNumero,listeDate,listeBilan};

        String rapportSelectionne = rapports[position] ;
        rapport.setText(rapportSelectionne);

    }
}
