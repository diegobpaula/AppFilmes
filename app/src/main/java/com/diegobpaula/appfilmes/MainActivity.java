package com.diegobpaula.appfilmes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.diegobpaula.appfilmes.Adapter.AdapterFilme;
import com.diegobpaula.appfilmes.Model.FilmeApi;
import com.diegobpaula.appfilmes.Model.Filmes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView_filmes;
    private AdapterFilme adapterFilme;
    private List<Filmes> filmesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarComponentes();
        getSupportActionBar().hide();

        filmesList = new ArrayList<>();


        //Configurar retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://firebasestorage.googleapis.com/v0/b/app-delivery-97d5b.appspot.com/o/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        // Iniciar a retrofit
        FilmeApi filmeApi = retrofit.create(FilmeApi.class);
        Call<List<Filmes>> call = filmeApi.getFilmes();
        call.enqueue(new Callback<List<Filmes>>() {
            @Override
            public void onResponse(Call<List<Filmes>> call, Response<List<Filmes>> response) {
                if (response.code() != 200){
                    return;
                }
                List<Filmes> filmes = response.body();

                for (Filmes filme : filmes){
                    filmesList.add(filme);
                }

                adapterFilme = new AdapterFilme(getApplicationContext(), filmesList);
                recyclerView_filmes.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                recyclerView_filmes.setHasFixedSize(true);
                recyclerView_filmes.setAdapter(adapterFilme);
            }

            @Override
            public void onFailure(Call<List<Filmes>> call, Throwable t) {

            }
        });


        

        /*
        Filmes filmes = new Filmes(R.drawable.ic_launcher_background, "SOSSA FILME");
        filmesList.add(filmes);

        Filmes filmes1 = new Filmes(R.drawable.ic_launcher_background, "Meu Ovo");
        filmesList.add(filmes1);

        Filmes filmes2 = new Filmes(R.drawable.ic_launcher_background, "Meu nemne");*/
    }

    public void iniciarComponentes() {
        recyclerView_filmes = findViewById(R.id.recyclerView_filmes);
    }
}