package mx.tec.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class MainActivity extends AppCompatActivity {

    Button requestButton;
    String chosenCity;
    String chosenUnits;
    EditText myCity;
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestButton = findViewById(R.id.button);
        myCity = findViewById(R.id.editTextCityName);
        radioGroup =  findViewById(R.id.radioGroup);

        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                chosenCity = myCity.getText().toString();

                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton =  findViewById(selectedId);
                chosenUnits = radioButton.getText().toString();

                intent.putExtra("city", chosenCity);
                intent.putExtra("units", chosenUnits);
                startActivity(intent);
            }
        });


    }


}