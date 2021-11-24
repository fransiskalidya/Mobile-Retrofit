package org.aplas.latihanretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.aplas.latihanretrofit.models.Repo;
import org.aplas.latihanretrofit.service.GithubService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubService service = retrofit.create(GithubService.class);

        Call<List<Repo>> repos = service.listRepos("fransiskalidya");
        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> repoList) {
                //mengambil data HTMLURL dan menampilkan di logcat
                Log.d("Sukses", repoList.body().get(0).getHtmlUrl());
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }
}