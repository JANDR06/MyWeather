package com.example.myweatherbase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherbase.API.Connector;
import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.Root;
import com.example.myweatherbase.base.BaseActivity;
import com.example.myweatherbase.base.CallInterface;
import com.example.myweatherbase.base.ImageDownloader;
import com.example.myweatherbase.base.Parameters;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends BaseActivity implements CallInterface {

    private TextView textView;
    private RecyclerView recyclerView;
    private Root root;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showProgress();
        executeCall(this);
    }

    @Override
    public void doInBackground() {
        root = Connector.getConector().get(Root.class,"&lat=39.454528&lon=-0.350364");
    }

    @Override
    public void doInUI() {
        hideProgress();
        recyclerView = findViewById(R.id.recyclerView);

        Adaptador miAdaptador = new Adaptador(this, root);

        recyclerView.setAdapter(miAdaptador);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        textView = findViewById(R.id.textView);
        textView.setText(root.getCity());

    }


    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivityDetails.class);
        int info = recyclerView.getChildAdapterPosition(view);
        intent.putExtra("numero",info);
        intent.putExtra("rooti",root);
        //intent.putExtra("ciudad",ciudad);
        startActivity(intent);
        finish();
    }
}