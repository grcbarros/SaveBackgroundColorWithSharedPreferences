package com.grcb.savebackgroundcolorwithsharedpreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

    ConstraintLayout constraintLayout;
    RadioGroup radioGroupColors;
    RadioButton radioButtonSelected;
    Button buttonSalvar;
    private static final String COLOR_PREFERENCE = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroupColors = findViewById(R.id.radioGroupColorsId);
        buttonSalvar = findViewById(R.id.buttonSalvarId);
        constraintLayout = findViewById(R.id.constraintLayoutId);

        restoreColorPreference();

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioGroupColors.getCheckedRadioButtonId() == -1){
                    Toast.makeText(MainActivity.this,"Selecione uma cor",Toast.LENGTH_SHORT).show();
                }else{
                    radioButtonSelected = findViewById(radioGroupColors.getCheckedRadioButtonId());
                    String color = radioButtonSelected.getText().toString();
                    saveColorPreference(color);
                    setBackground(color);
                }
            }
        });
    }

    private void saveColorPreference(String color){
        SharedPreferences sharedPreferences = getSharedPreferences(COLOR_PREFERENCE, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("color", color);
        editor.commit();
    }

    private void restoreColorPreference(){
        SharedPreferences sharedPreferences = getSharedPreferences(COLOR_PREFERENCE, 0);

        if (sharedPreferences.contains("color")){
            setBackground(sharedPreferences.getString("color", "#FFFFFFFF"));
        }
    }

    private void setBackground(String color) {
        switch (color){
            case "Vermelho":
                constraintLayout.setBackgroundColor(Color.parseColor("#FFFF0000"));
                break;
            case "Verde":
                constraintLayout.setBackgroundColor(Color.parseColor("#FF00FF00"));
                break;
            case "Azul":
                constraintLayout.setBackgroundColor(Color.parseColor("#FF0000FF"));
                break;
            case "Branco":
                constraintLayout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                break;
            default:
                Toast.makeText(MainActivity.this, "Erro: Valor RGB inválido",Toast.LENGTH_SHORT).show();
                break;
        }

    }

}
