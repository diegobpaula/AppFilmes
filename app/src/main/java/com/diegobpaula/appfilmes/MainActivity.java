package com.diegobpaula.appfilmes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.diegobpaula.appfilmes.Model.AdapterFilme;
import com.diegobpaula.appfilmes.Model.Filmes;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView_filmes;
    private AdapterFilme adapterFilme;
    private List<Filmes> filmesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarComponentes();

        filmesList = new ArrayList<>();
        adapterFilme = new AdapterFilme(getApplicationContext(), filmesList);
        recyclerView_filmes.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView_filmes.setHasFixedSize(true);
        recyclerView_filmes.setAdapter(adapterFilme);

        Filmes filmes = new Filmes(R.drawable.ic_launcher_background, "SOSSA FILME");
        filmesList.add(filmes);
    }

    public void iniciarComponentes() {
        recyclerView_filmes = findViewById(R.id.recyclerView_filmes);
    }
}