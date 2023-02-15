package com.example.conversione;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    pays listePaysSaisie;
    pays listePaysResult2;
    pays listePaysResult3;
    int positionSaisie;
    int positionResult1;

    // pour initialiser et pas que ça crash
    pays listePaysResult1 = new pays("UE", "EUR", "€", 1.0000);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pays[] listePays = paysData.getPays();
        TextView TextResult1 = (TextView) findViewById(R.id.pays_result1);
        TextView TextResult2 = (TextView) findViewById(R.id.pays_result2);
        TextView TextResult3 = (TextView) findViewById(R.id.pays_result3);
        EditText Saisie = (EditText) findViewById(R.id.saisie);
        ImageButton Conversion = (ImageButton) findViewById(R.id.convertir);
        LinearLayout ajout_devise = (LinearLayout) findViewById(R.id.ajout_devise);
        ImageButton Transposition = (ImageButton) findViewById(R.id.transposer);
        LinearLayout result2 = (LinearLayout) findViewById(R.id.result2);
        ImageButton Plus = (ImageButton) findViewById(R.id.bouton_plus);
        LinearLayout result3 = (LinearLayout) findViewById(R.id.result3);
        LinearLayout ajout_devise2 = (LinearLayout) findViewById(R.id.ajout_devise2);
        ImageButton Plus2 = (ImageButton) findViewById(R.id.bouton_plus2);


        result2.setVisibility(View.INVISIBLE);
        ajout_devise2.setVisibility(View.INVISIBLE);
        result3.setVisibility(View.INVISIBLE);


        // BOUTON MENU

        ImageButton menuIcon = (ImageButton) findViewById(R.id.menuIcon);
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ouvrirMenu();
            }

    });

        // ACTION DE CONVERSION
        Conversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double tauxConvSaisie = listePaysSaisie.getEURConversion(); // c
                double tauxConvResult1 = listePaysResult1.getEURConversion(); // k
                double tauxConvResult2 = listePaysResult2.getEURConversion(); // k2
                double tauxConvResult3 = listePaysResult3.getEURConversion(); // k3
                // formule conversion : c/k
                String montantStr = Saisie.getText().toString();

                if (montantStr.length() == 0) {
                    Toast.makeText(getApplicationContext(),"Veuillez entrer un montant",
                            Toast.LENGTH_SHORT).show();
                } else {
                    double montant = Double.parseDouble(montantStr);
                    double resultConv1 = arrondisseur(montant * (tauxConvSaisie/tauxConvResult1));
                    String resultConv1Str = String.valueOf(resultConv1);
                    double resultConv2 = arrondisseur(montant * (tauxConvSaisie/tauxConvResult2));
                    String resultConv2Str = String.valueOf(resultConv2);
                    double resultConv3 = arrondisseur(montant * (tauxConvSaisie/tauxConvResult3));
                    String resultConv3Str = String.valueOf(resultConv3);

                    TextResult1.setText(resultConv1Str);
                    TextResult2.setText(resultConv2Str);
                    TextResult3.setText(resultConv3Str);
                }
            }

        }
        );

        // BOUTON PLUS AJOUTE UN DEUXIEME RESULTAT

         Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result2.setVisibility(View.VISIBLE);
                ajout_devise.setVisibility(View.INVISIBLE);
                ajout_devise2.setVisibility(View.VISIBLE);

            }
        });

         // DEUXIEME BOUTON PLUS : TROISIEME RESULTAT

        Plus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result3.setVisibility(View.VISIBLE);
                ajout_devise2.setVisibility(View.INVISIBLE);

            }
        });

        // SPINNER 1 SAISIE

        Spinner saisieSpin = (Spinner) findViewById(R.id.saisie_pays);
        ArrayAdapter<pays> SaisieAdapter = new ArrayAdapter<pays>(this,
                android.R.layout.simple_spinner_item, listePays);
        SaisieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        saisieSpin.setAdapter(SaisieAdapter);

        saisieSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandlerSaisie(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // SPINNER RESULT 1

        Spinner result1Spin = (Spinner) findViewById(R.id.spin1);
        ArrayAdapter<pays> result1Adapter = new ArrayAdapter<pays>(this, android.R.layout.simple_spinner_item, listePays);
        result1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        result1Spin.setAdapter(result1Adapter);
        result1Spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandlerResult1(parent, view, position, id);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        // SPINNER RESULT 2

        Spinner result2Spin = (Spinner) findViewById(R.id.spin2);
        ArrayAdapter<pays> result2Adapter = new ArrayAdapter<pays>(this, android.R.layout.simple_spinner_item, listePays);
        result2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        result2Spin.setAdapter(result2Adapter);
        result2Spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandlerResult2(parent, view, position, id);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        // SPINNER RESULT 3

        Spinner result3Spin = (Spinner) findViewById(R.id.spin3);
        ArrayAdapter<pays> result3Adapter = new ArrayAdapter<pays>(this, android.R.layout.simple_spinner_item, listePays);
        result3Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        result3Spin.setAdapter(result3Adapter);
        result3Spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandlerResult3(parent, view, position, id);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });



        // BOUTON DE TRANSPOSITION

        Transposition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int SaisiePos = positionSaisie;
                int Result1Pos = positionResult1;
                saisieSpin.setSelection(SaisiePos);
                result1Spin.setSelection(Result1Pos);

                Toast.makeText(getApplicationContext(), "Transposition effectuée",
                        Toast.LENGTH_SHORT).show();
            }
        });



    }


    // FONCTIONS POUR LES SPINNERS


    private void onItemSelectedHandlerResult1(AdapterView<?> adapterView, View view, int position, long id) {
        TextView Comparaison1 = (TextView) findViewById(R.id.comparaison);

        Adapter adapter = adapterView.getAdapter();
        listePaysResult1 = (pays) adapter.getItem(position);
        positionSaisie = position;

        //arrondissement à 2 décimales
        double tauxConvSaisie = (double) listePaysSaisie.getEURConversion();
        double tauxConvResult1 = (double) listePaysResult1.getEURConversion();
        double facteur = Math.round((tauxConvSaisie/tauxConvResult1) * 100);
        facteur = facteur/100;

        Comparaison1.setText("1 " + listePaysSaisie.getMonnaie() + " = " + Double.toString(facteur) + " " + listePaysResult1.getMonnaie());


    }

    private void onItemSelectedHandlerResult2(AdapterView<?> adapterView, View view, int position, long id) {

        Adapter adapter = adapterView.getAdapter();
        listePaysResult2 = (pays) adapter.getItem(position);

    }

    private void onItemSelectedHandlerResult3(AdapterView<?> adapterView, View view, int position, long id) {

        Adapter adapter = adapterView.getAdapter();
        listePaysResult3 = (pays) adapter.getItem(position);

    }


    private void onItemSelectedHandlerSaisie(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        listePaysSaisie = (pays) adapter.getItem(position);
        positionResult1 = position;

        //arrondissement à 2 décimales
        TextView Comparaison2 = (TextView) findViewById(R.id.comparaison);
        double tauxConvSaisie = (double) listePaysSaisie.getEURConversion();
        double tauxConvResult1 = listePaysResult1.getEURConversion();
        double facteur = arrondisseur(tauxConvSaisie/tauxConvResult1);

        Comparaison2.setText("1 " + listePaysSaisie.getMonnaie() + " = " + Double.toString(facteur) +
                " " + listePaysResult1.getMonnaie());
    }

    //fonction pour arrondir à 2 décimales
    private double arrondisseur(double val) {
        val = Math.round(val*100);
        return val/100;
    }

// fonction pour ouvrir activité menu

    public void ouvrirMenu() {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
    }








