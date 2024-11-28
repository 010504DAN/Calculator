package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Long a = 0L; // Промежуточный результат
    private Long b = 0L; // Второй операнд
    private String currentOperation = ""; // Текущая операция (+, -, *, /)
    private Boolean isOperationClock = false;
    private Boolean openActivity = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        textView = findViewById(R.id.text_view);

    }



    public void onNumberClick(View view) {
        String textButton = ((MaterialButton) view).getText().toString();

        if (textButton.equals("AC")) {
            textView.setText("0");
            a = 0L;
            b = 0L;
            currentOperation = "";
            isOperationClock = false;
        } else if (textView.getText().toString().equals("0") || isOperationClock) {
            textView.setText(textButton);
        } else {
            textView.append(textButton);
        }

        openActivity = false;
        isOperationClock = false;
    }

    public void onOperationClock(View view) {
        String input = textView.getText().toString();
        if (!input.isEmpty()) {
            b = Long.parseLong(input);
        }

        if (!currentOperation.isEmpty()) {
            switch (currentOperation) {
                case "+":
                    a += b;
                    break;
                case "-":
                    a -= b;
                    break;
                case "*":
                    a *= b;
                    break;
                case "/":
                    if (b == 0) {
                        textView.setText("Error");
                        return;
                    }
                    a /= b;
                    break;
            }
            textView.setText(String.valueOf(a));
        } else {
            a = b;
        }

        if (view.getId() == R.id.btn_plus) {
            openActivity = false;
            currentOperation = "+";
        } else if (view.getId() == R.id.btn_minus) {
            openActivity = false;
            currentOperation = "-";
        } else if (view.getId() == R.id.btn_multi) {
            openActivity = false;
            currentOperation = "*";
        } else if (view.getId() == R.id.btn_divi) {
            openActivity = false;
            currentOperation = "/";
        } else if (view.getId() == R.id.btn_equal) {
            openActivity = true;
            currentOperation = "";
        }

        Button visibility = findViewById(R.id.click_me);


        if (openActivity) {
            visibility.setVisibility(View.VISIBLE);
        } else {
            visibility.setVisibility(View.GONE);
        }

        visibility.setOnClickListener(v -> {
            String input2 = textView.getText().toString();
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("equal", input2);
            startActivity(intent);
        });

        isOperationClock = true;
    }
}