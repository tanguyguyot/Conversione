package com.example.conversione;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button section_conversion = findViewById(R.id.section_conversion);
        Button section_infos = findViewById(R.id.section_infos);

        section_conversion.setAlpha(0f);
        section_infos.setAlpha(0f);

        section_conversion.animate().alpha(1f).setDuration(1500);
        section_infos.animate().alpha(1f).setDuration(1500);

        section_infos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allerInfos();
            }
        });
        section_conversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allerConversione();
            }
        });
    }
    public void allerInfos() {
        Intent intent = new Intent(this, infos.class);
        startActivity(intent);
    }
    public void allerConversione() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}