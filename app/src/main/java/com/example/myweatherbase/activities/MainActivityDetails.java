package com.example.myweatherbase.activities;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.Root;
import com.example.myweatherbase.base.ImageDownloader;
import com.example.myweatherbase.base.Parameters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivityDetails extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_element_details);



        Root r = (Root) getIntent().getExtras().get("extra");
        int n = getIntent().getExtras().getInt("numero");
        Date date = new Date(r.list.get(n).dt * 1000L);


        TextView fechaDetails = findViewById(R.id.fechaDetails);
        TextView horaDetails = findViewById(R.id.horaDetails);
        TextView diaDetails = findViewById(R.id.diaDetails);
        TextView estadoDetails = findViewById(R.id.estadoCieloDetails);
        TextView temperaturaDetails = findViewById(R.id.temperaturaDetails);
        TextView temperaturaMinDetails = findViewById(R.id.temperaturaMinDetails);
        TextView temperaturaMaxDetails = findViewById(R.id.temperaturaMaxDetails);
        TextView sensacionTermicaDetails = findViewById(R.id.sensacionTermicaDetails);
        TextView humedadDetails = findViewById(R.id.humedadDetails);
        TextView presionDetails = findViewById(R.id.presionDetails);
        ImageView imagenDetails = findViewById(R.id.imagenDetails);

        fechaDetails.setText(new SimpleDateFormat("dd/MM/yyyy").format(date));
        horaDetails.setText(new SimpleDateFormat("HH:mm").format(date));
        diaDetails.setText(new SimpleDateFormat("EEEE",new Locale("es","ES")).format(date));
        estadoDetails.setText(r.list.get(n).weather.get(0).description);
        temperaturaDetails.setText(r.list.get(n).main.temp + "º");
        temperaturaMaxDetails.setText(r.list.get(n).main.temp_max + "º");
        temperaturaMinDetails.setText(r.list.get(n).main.temp_min + "º");
        sensacionTermicaDetails.setText(r.list.get(n).main.feels_like + "º");
        humedadDetails.setText(r.list.get(n).main.humidity + "%");
        presionDetails.setText(r.list.get(n).main.pressure + " hPa");
        ImageDownloader.downloadImage(Parameters.ICON_URL_PRE + r.list.get(n).weather.get(0).icon + Parameters.ICON_URL_POST, imagenDetails);
    }



    // HACER UN GET SERIALIZABLE Y UN BUNDLE ESTA EN EL RECYCLE VIEW
}
