package fr.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


import fr.gsb.rv.entites.Visiteur;
import fr.gsb.rv.technique.Ip;
import fr.gsb.rv.technique.Session;

public class MainActivity extends AppCompatActivity {

    EditText login, mdp ;
    TextView message ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.editTextTextPersonName) ;
        mdp = findViewById(R.id.etMdp) ;
        message = findViewById(R.id.tvErreur) ;
        Log.i("GSB_MAIN_ACTIVITY", "Création de l'activité principale");
    }

    public void seConnecter(View vue){

        Response.Listener<JSONObject> ecouteurReponse = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{

                    Visiteur visiteur = new Visiteur();
                    visiteur.setMatricule(response.getString("vis_matricule"));
                    visiteur.setNom(response.getString("vis_nom"));
                    visiteur.setPrenom(response.getString("vis_prenom"));
                    Session.ouvrir(visiteur);

                    Log.v("GSB_MAIN_ACTIVITY", "200 Ok");

                    Intent intentionEnvoyer = new Intent(getApplicationContext(), MenuRvActivity.class);
                    startActivity(intentionEnvoyer);


                }catch(JSONException e){
                    Log.e("GSB_MAIN_ACTIVITY", "JSON : " + e.getMessage());
                    System.out.println("catch");
                    Toast.makeText(getApplicationContext(), "Erreur lors de la saisie des données.", Toast.LENGTH_LONG).show();

                }

            }
        };

        Response.ErrorListener ecouteurError = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("GSB_MAIN_ACTIVITY", "Erreur HTTP :" + " " + error.getMessage());
                System.out.println("erreur");
                Toast.makeText(getApplicationContext(), "Erreur lors de la saisie des données.", Toast.LENGTH_LONG).show();
            }
        };

        JsonObjectRequest requete = new JsonObjectRequest(
                Request.Method.GET,
                "http://"+ Ip.ip +"/visiteurs/"+login.getText().toString()+"/"+mdp.getText().toString(),
                null,
                ecouteurReponse,
                ecouteurError
        );

        RequestQueue fileRequetes = Volley.newRequestQueue(this);
        fileRequetes.add(requete);


        /*
        if (login.getText().toString().equals("a131")  && mdp.getText().toString().equals("azerty")) {
            Intent acceuil = new Intent(this,ActivitesSecondaire.class);
            Session.ouvrir(new Visiteur(login.getText().toString(),mdp.getText().toString(),"Villechalane", "Louis"));
            startActivity(acceuil);

            Log.i("GSB_MAIN_ACTIVITY", "Connexion Ok (" + Session.getSession().getLeVisiteur().getNom() + " " + Session.getSession().getLeVisiteur().getPrenom() + ")");
        }
        else if(login.getText().toString().equals("")  && mdp.getText().toString().equals("")){
            message.setText("Remplire le formulaire");
            Log.i("GSB_MAIN_ACTIVITY", "Connexion Nok");
        }
        else if(login.getText().toString().equals("") ){
            message.setText("Remplire le matricule");
            Log.i("GSB_MAIN_ACTIVITY", "Connexion Nok");
        }
        else if(mdp.getText().toString().equals("")){
            message.setText("Remplire le mot de passe");
            Log.i("GSB_MAIN_ACTIVITY", "Connexion Nok");
        }
        else if(!login.getText().toString().equals("a131")){
            message.setText("Erreur matricule");
            Log.i("GSB_MAIN_ACTIVITY", "Connexion Nok");
        }
        else if(!mdp.getText().toString().equals("azerty")){
            message.setText("Erreur mot de passe");
            Log.i("GSB_MAIN_ACTIVITY", "Connexion Nok");
        }

         */
    }

    public void annuler(View vue){
        login.setText("");
        mdp.setText("");
        message.setText("");
        Log.i("GSB_MAIN_ACTIVITY", "Initialisation des champs");
    }
}