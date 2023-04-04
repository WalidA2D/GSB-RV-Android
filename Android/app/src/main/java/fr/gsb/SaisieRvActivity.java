package fr.gsb;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import fr.gsb.rv.entites.Praticien;
import fr.gsb.rv.modeles.ModeleGsb;
import fr.gsb.rv.technique.Ip;
import fr.gsb.rv.technique.Session;

public class SaisieRvActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    TextView session , tvDate;
    Button retour , selectionne , clear;
    EditText bilan ;
    private static final String [] lesCoef = {"1","2","3","4","5"} ;
    Spinner praticien , motif , coef ;
    Button valider ;

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
        praticien = findViewById(R.id.praticien) ;
        motif = findViewById(R.id.motif) ;
        coef = findViewById(R.id.coef) ;
        valider = findViewById(R.id.valider) ;

        remplirSpinnerPraticien();
        remplirSpinnerMotif();

        coef = (Spinner) findViewById(R.id.coef) ;
        ArrayAdapter<String> aaMois = new ArrayAdapter<String>(
                this ,
                android.R.layout.simple_spinner_item ,
                lesCoef
        ) ;
        aaMois.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        coef.setAdapter(aaMois);

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

    public void remplirSpinnerPraticien() {
        String url = "http://" + Ip.ip + "/praticiens";
        RequestQueue fileRequetes = Volley.newRequestQueue(this);

        JsonArrayRequest requete = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            List<String> numsPraticiens = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject praticienJson = response.getJSONObject(i);
                                String num = praticienJson.getString("pra_num");
                                numsPraticiens.add(num);
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                    SaisieRvActivity.this, android.R.layout.simple_spinner_item, numsPraticiens);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            praticien.setAdapter(adapter);
                        } catch (JSONException e) {
                            Log.e("GSB_SAISIE_RV_ACTIVITY", "Erreur lors du parsing JSON : " + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("GSB_SAISIE_RV_ACTIVITY", "Erreur lors de la récupération des praticiens : " + error.getMessage());
                    }
                }
        );
        fileRequetes.add(requete);
    }

    public void remplirSpinnerMotif() {
        String url = "http://" + Ip.ip + "/motifs";
        RequestQueue fileRequetes = Volley.newRequestQueue(this);

        JsonArrayRequest requete = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            List<String> nomsMotifs = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject motifJson = response.getJSONObject(i);
                                String num = motifJson.getString("mot_num");
                                nomsMotifs.add(num);
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                    SaisieRvActivity.this, android.R.layout.simple_spinner_item, nomsMotifs);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            motif.setAdapter(adapter);
                        } catch (JSONException e) {
                            Log.e("GSB_SAISIE_RV_ACTIVITY", "Erreur lors du parsing JSON : " + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("GSB_SAISIE_RV_ACTIVITY", "Erreur lors de la récupération des motifs : " + error.getMessage());
                    }
                }
        );
        fileRequetes.add(requete);
    }

    public void ajouterRapportVisite(View vue) {
        String url = "http://" + Ip.ip + "/rapports";
        RequestQueue fileRequetes = Volley.newRequestQueue(this);

        // Récupération de la date au format String
        String dateStr = tvDate.getText().toString();

        // Conversion de la chaîne de caractères en objet LocalDate
        LocalDate date = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }

        // Formatage de la date au format "yyyy/MM/dd"
        String formattedDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        }



        JSONObject rapport = new JSONObject();
        try {
            rapport.put("matricule", Session.getSession().getLeVisiteur().getMatricule());
            rapport.put("praticien", praticien.getSelectedItem().toString());
            rapport.put("visite", formattedDate);
            rapport.put("bilan", bilan.getText().toString());
            rapport.put("coef_confiance", coef.getSelectedItem().toString());
            rapport.put("mot_num", motif.getSelectedItem().toString());
            Log.e("GSB_SAISIE_RV_ACTIVITY",Session.getSession().getLeVisiteur().getMatricule() + " " + praticien.getSelectedItem().toString() + " " + tvDate.getText().toString() + " " + bilan.getText().toString() + " " + coef.getSelectedItem().toString() + " " + motif.getSelectedItem().toString()) ;
        } catch (JSONException e) {
            Log.e("GSB_SAISIE_RV_ACTIVITY", "Erreur lors de la création de l'objet JSON : " + e.getMessage());
        }

        JsonObjectRequest requete = new JsonObjectRequest(Request.Method.POST, url, rapport,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("GSB_SAISIE_RV_ACTIVITY", "Rapport ajouté avec succès");
                        Toast.makeText(getApplicationContext(), "Rapport ajouté avec succès", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MenuRvActivity.class);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("GSB_SAISIE_RV_ACTIVITY", "Erreur lors de l'ajout du rapport : " + error.getMessage());
                        Toast.makeText(getApplicationContext(), "Erreur lors de l'ajout du rapport", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        fileRequetes.add(requete);
    }








}
