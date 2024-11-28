package com.example.myapplication2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView box = findViewById(R.id.text);
        String text2 = getIntent().getStringExtra("equal");
        box.setText(text2);

        Spinner spinner = findViewById(R.id.spinner);

        // Получаем массив из strings.xml
        String[] items = getResources().getStringArray(R.array.dropdown_items);

        // Создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Устанавливаем адаптер для Spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity2.this, "Вы выбрали: " + selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Действие, если ничего не выбрано
            }
        });

        Button heart = findViewById(R.id.heart);
        AtomicBoolean isIconChanged = new AtomicBoolean(true);
        heart.setOnClickListener(v -> {
            if (isIconChanged.get()){
                heart.setCompoundDrawablesWithIntrinsicBounds(R.drawable.img_2,0,0,0);
                isIconChanged.set(false);
            } else {
                heart.setCompoundDrawablesWithIntrinsicBounds(R.drawable.img_3,0,0,0);
                isIconChanged.set(true);
            }
        });

        Button next = findViewById(R.id.next);
        next.setOnClickListener(v -> {
            finish();
        });
    }
}