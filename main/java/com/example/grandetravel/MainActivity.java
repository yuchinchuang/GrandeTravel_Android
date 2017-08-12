package com.example.grandetravel;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    PackageAdapter packageAdapter;
    EditText etLocation;
    String location;
    ArrayList<Package> packageList;
    ListView lvPackageList;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

//        this.packageList = packageAdapter.getList();
//        outState.putParcelableArrayList(STATE_LIST, getAdapter().getList());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        packageList = new ArrayList<>();
        packageAdapter = new PackageAdapter(this, packageList);
        etLocation = (EditText)findViewById(R.id.etDestination);


        lvPackageList = (ListView)findViewById(R.id.lvMain);
        lvPackageList.setAdapter(packageAdapter);

//        if (savedInstanceState != null) {
//            ArrayList<Package> list = savedInstanceState.getParcelableArrayList(STATE_LIST);
//            PackageAdapter adapter = new PackageAdapter(list, getActivity(), this);
//        } else {
//            //Else we are creating our Activity from scratch, pull list from where ever you initially get it from
//            ArrayList<Package> list = getInitData();
//            PackageAdapter adapter = new PackageAdapter(list, getActivity(), this);
//        }

    }

    public void SearchLocation(View view) {
        location = etLocation.getText().toString();
        new AsyncPackage().execute();
    }

    class AsyncPackage extends AsyncTask<Void, Void, JSONObject>{

        @Override
        protected JSONObject doInBackground(Void... voids) {
            JSONObject jsonPackage = null;
            jsonPackage = getPackgeJSON();
            return jsonPackage;
        }

        //
        private JSONObject getPackgeJSON(){
            try{
                // Get data from the web
//                URL url = new URL("www.grandetravel.com?location=" + location);
//
//                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.connect();
//
//                BufferedReader reader =
//                        new BufferedReader(
//                                new InputStreamReader(urlConnection.getInputStream())
//                        );


                // Read data from file
                InputStream stream = getResources().openRawResource(R.raw.json_data);
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream,"UTF-8"));


                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine())!= null){
                    builder.append(line);
                }

                String jsonString = builder.toString();
                JSONObject jsonObject = new JSONObject(jsonString);
//                renderJson(jsonObject);

                return jsonObject;
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {

            try {
                if(jsonObject != null){
                    String title = jsonObject.getString("title");

                    JSONArray jsonPackages = jsonObject.getJSONArray("packages");
                    ArrayList<Package> packages = new ArrayList<>();

                    for(int i = 0; i < jsonPackages.length(); i++){
                        JSONObject pack = jsonPackages.getJSONObject(i);

                        String name = pack.getString("name");
                        String location = pack.getString("location");
                        String description = pack.getString("description");
                        double price = pack.getDouble("price");

                        packages.add(new Package(name, location, description, price));
                    }

                    packageAdapter.clear();
                    for( Package p : packages){
                        packageAdapter.add(p);
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            super.onPostExecute(jsonObject);
        }
    }
}


