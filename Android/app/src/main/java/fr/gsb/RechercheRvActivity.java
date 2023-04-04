package fr.gsb;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.gsb.rv.entites.Offrir;
import fr.gsb.rv.entites.Praticien;
import fr.gsb.rv.entites.RapportVisite;
import fr.gsb.rv.entites.Visiteur;
import fr.gsb.rv.technique.Ip;
import fr.gsb.rv.technique.Session;


public class RechercheRvActivity extends AppCompatActivity {

    Button retour ;
    TextView session ;

    private static final String [] lesMois = {"01","02","03","04","05","06","07","08","09","10","11","12"} ;
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

        session.setText(Session.getSession().getLeVisiteur().getNom() + " " + Session.getSession().getLeVisiteur().getPrenom());
        Log.i("GSB_CONSULTE_ACTIVITY", "Création de l'activité de consultation");
    }

    public void retour(View vue){
        Intent retour = new Intent(this,MenuRvActivity.class);
        startActivity(retour);
        Log.i("GSB_CONSULTE_ACTIVITY", "Retour a l'acceuil Ok");
    }

    public void liste(View vue) {
        try {
            String selectedAnnee = annees.getSelectedItem().toString();
            String selectedMois = mois.getSelectedItem().toString();

            Response.Listener<JSONArray> ecouteurReponse = new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(0);
                        List<RapportVisite> rapp = new ArrayList<>();
                        for(int i =0 ;i<response.length(); i++ ) {
                            JSONObject element = response.getJSONObject(i);
                            String num = element.getString("rap_num");
                            String date = element.getString("rap_date_visite");
                            String bilan = element.getString("rap_bilan");

                            RapportVisite rapportVisite = new RapportVisite();
                            rapportVisite.setNumero(Integer.parseInt(num));
                            rapportVisite.setDateVisite(date);
                            rapportVisite.setBilan(bilan);

                            System.out.println(rapportVisite);
                            rapp.add(rapportVisite);

                        }
                        //rapportVisite.setMotif(jsonObject.getString("rap_autre_motif"));
                        //rapportVisite.setCoefConfiance(jsonObject.getInt("rap_coef_confiance"));

                        Bundle bundle = new Bundle();
                        ArrayList<String> num = new ArrayList<>();
                        ArrayList<String> date = new ArrayList<>();
                        ArrayList<String> bilan = new ArrayList<>();

                        for (RapportVisite rapportVisite : rapp) {
                            num.add(String.valueOf(rapportVisite.getNumero()));
                            date.add(rapportVisite.getDateVisite());
                            bilan.add(rapportVisite.getBilan());
                        }

                        Log.v("GSB_CONSULTE_ACTIVITY", "200 Ok");

                        Bundle bundlee = new Bundle() ;
                        bundlee.putString("year", selectedAnnee);
                        bundlee.putString("month", selectedMois);

                        bundle.putStringArrayList("numero",num );
                        bundle.putStringArrayList("date_visite", date);
                        bundle.putStringArrayList("bilan", bilan);
                        //bundle.putString("motif", rapportVisite.getMotif());
                        //bundle.putInt("coef", rapportVisite.getCoefConfiance());

                        Intent intentionEnvoyer = new Intent(getApplicationContext(), ListeRvActicity.class) ;
                        intentionEnvoyer.putExtras(bundle);
                        startActivity(intentionEnvoyer);

                    } catch(JSONException e) {
                        Log.e("GSB_CONSULTE_ACTIVITY", "JSON : " + e.getMessage());
                        Toast.makeText(getApplicationContext(), "Erreur lors de la récupération des données.", Toast.LENGTH_LONG).show();
                    }
                }
            };

            Response.ErrorListener ecouteurError = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("GSB_CONSULTE_ACTIVITY", "Erreur HTTP :" + " " + error.getMessage());
                    Toast.makeText(getApplicationContext(), "Erreur lors de la récupération des données.", Toast.LENGTH_LONG).show();
                }
            };

            JsonArrayRequest requete = new JsonArrayRequest(
                    Request.Method.GET,
                    "http://" + Ip.ip + "/rapports/" + Session.getSession().getLeVisiteur().getMatricule() + "/" + selectedMois + "/" + selectedAnnee,
                    null,
                    ecouteurReponse,
                    ecouteurError
            );

            RequestQueue fileRequetes = Volley.newRequestQueue(this);
            fileRequetes.add(requete);
        } catch (Exception e) {
            Log.e("GSB_CONSULTE_ACTIVITY", "Exception : " + e.getMessage());
            Toast.makeText(getApplicationContext(), "Erreur lors de la récupération des données.", Toast.LENGTH_LONG).show();
        }
    }




}
