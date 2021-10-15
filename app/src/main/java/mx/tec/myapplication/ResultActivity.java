package mx.tec.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;


public class ResultActivity extends AppCompatActivity {
    TextView textviewCity;
    TextView textviewTemp;
    TextView textViewDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textviewCity  = findViewById(R.id.resultTextView);
        textviewTemp  = findViewById(R.id.tempTextView);
        textViewDesc = findViewById(R.id.textView_desc);
        ImageView myImageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        String chosenCity = intent.getStringExtra("city");
        String chosenUnits = intent.getStringExtra("units");

        // 1) Instantiate the Request Queue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.openweathermap.org/data/2.5/weather?" +
                "q=" + chosenCity + "&" +
                "units=" + chosenUnits + "&" +
                "mode=json&" +
                "appid=";


        // 2) Request a response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    // If response = 200
                    @Override
                    public void onResponse(JSONObject response) {
                        String city = "";
                        String temp = "";
                        String icon = "";
                        String description = "";
                        try {
                            city = response.getString("name");
                            temp = response.getJSONObject("main").getString("feels_like");
                            icon = response.getJSONArray("weather").getJSONObject(0).getString("icon");
                            description = response.getJSONArray("weather").getJSONObject(0).getString("description");

                            textviewCity.setText(city);
                            textviewTemp.setText(temp);
                            textViewDesc.setText(description);
                            Glide.with(getApplicationContext()).load("http://openweathermap.org/img/w/" + icon + ".png").into(myImageView);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            textviewCity.setText("Unable to process the request, due to a JSON exception");
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textviewCity.setText("Unable to process the request, due to a REST API error");

                    }
                });

        //3 Don't forget this one
        queue.add(jsonObjectRequest);

    }
}