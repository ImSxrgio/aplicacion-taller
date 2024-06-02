package com.sroblesdorado.apptaller;

import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CalendarActivity extends AppCompatActivity {
    private SQLiteHelper dbHelper;
    //Para almacenar los dias
    private Set<Long> diasOcupadosSet = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calendar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // inicializar la base de datos
        dbHelper = new SQLiteHelper(this);

        // inicializar y cargar el calendar view
        // Movido aquí para que sea accesible en toda la

        CalendarView calendarView = findViewById(R.id.calendarView);
        // mostrar fecha actual en el calendar view
        Calendar calendar = Calendar.getInstance();
        long today = calendar.getTimeInMillis();
        calendarView.setDate(today, false, true);


        // agregar un dia ocupado
        agregarDiaOcupado();


    }


    private void agregarDiaOcupado() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {

            // Obtener los días ocupados de la base de datos y agregarlos al conjunto
            List<Long> diasOcupados = dbHelper.getAllDiasOcupados();
            diasOcupadosSet.addAll(diasOcupados);
            for (Long dia : diasOcupados) {
                Log.d("CalendarActivity", "Dia ocupado cargado: " + dia);
            }

        });

    }


    private void insertarDiaOcupado(long timestamp) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            dbHelper.insertDiaOcupado(timestamp);
            Log.d("CalendarActivity", "Día ocupado insertado: " + timestamp);
        });


    }
}


