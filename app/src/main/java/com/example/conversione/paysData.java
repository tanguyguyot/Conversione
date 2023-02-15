package com.example.conversione;

public class paysData {

    public static pays[] getPays() {
        pays UE = new pays("UE", "EUR", "€", 1.0000);
        pays US = new pays("Etats-Unis", "USD", "$", 1.0400);
        pays UK = new pays("Royaume-Uni", "GBP", "£", 1.1400);
        pays CN = new pays("Chine", "CNY", "€", 0.1400);
        pays JP = new pays("Japon", "JPY", "€", 0.0068);
        pays MX = new pays("Mexique", "MXN", "$", 0.0510);
        pays IT = new pays("Italie", "ITL", "£", 0.0005);
        pays OP = new pays("One Piece", "BERRY", "B", 0.0232);
        return new pays[] {UE, US, UK, CN, JP, MX, IT, OP};
    }
}
