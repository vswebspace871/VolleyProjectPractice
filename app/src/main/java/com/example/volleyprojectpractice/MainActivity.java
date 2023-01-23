package com.example.volleyprojectpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.volleyprojectpractice.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<Model> list = new ArrayList<>();

    public static final String URL = "https://jsonplaceholder.typicode.com/users";
    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // get all json response in Array
                    JSONArray jsonArray = new JSONArray(response);

                    // run loop on Array
                    for (int i = 0; i < jsonArray.length(); i++) {
                        // get all objects one by one
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        String email = jsonObject.getString("email");
                        //go into address object
                        JSONObject addrObject = jsonObject.getJSONObject("address");
                        String city = addrObject.getString("city");
                        String phone = jsonObject.getString("phone");
                        //add all object in Arraylist
                        list.add(new Model(name, email, city, phone));
                        //pass list to the adapter
                        binding.rv.setAdapter(new Adapter(list));
                        binding.rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));


//                        // in each object go into address object
//                        JSONObject addrObject = jsonObject.getJSONObject("address");
//                        // in every address object select City only and printy city name on TextView
//                        binding.textView.append(addrObject.get("city")+" \n");
//                        // Log.d(TAG, "onResponse: "+addrObject.get("city"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);
    }
}