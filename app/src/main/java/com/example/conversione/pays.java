package com.example.conversione;

public class pays {
    private String nomPays;
    private String monnaiePays;
    private String symboleMonnaie;
    private double EURConversion;

    public pays(String nom, String monnaie, String symbole, double Conv) {
        nomPays = nom;
        monnaiePays = monnaie;
        symboleMonnaie = symbole;
        EURConversion = Conv;
    }

    public String getNom() {
        return nomPays;
    }
    public String getMonnaie() {
        return monnaiePays;
    }
    public String getSymbole() {
        return symboleMonnaie;
    }
    public double getEURConversion() {
        return EURConversion;
    }
    @Override
    public String toString()  {
        return this.getNom() + " : " + this.getMonnaie() + " " + this.getSymbole();
    }
}
