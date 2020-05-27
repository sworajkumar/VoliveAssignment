package com.cpetsolut.com.voliveassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText userid;
    private TextView apikey;
    private Button search;
    private ListView listdata;
    public SaveDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apikey=(TextView)findViewById(R.id.apikey);
        userid=(EditText)findViewById(R.id.userid);
        search=(Button) findViewById(R.id.search);
        listdata=(ListView) findViewById(R.id.listview);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (apikey.getText().toString().isEmpty()){
                    apikey.setError("API Key");
                }else {
                    if (userid.getText().toString().isEmpty()){
                        userid.setError("USER ID");
                    }else {
                        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo netInfo = cm.getActiveNetworkInfo();
                        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                            SearchAsyncTask runner = new SearchAsyncTask();
                            runner.execute();
                        }else {
                            Toast.makeText(MainActivity.this, "No Internet Connection..", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    private class SearchAsyncTask extends AsyncTask<String, String, String> {

        private String APIKEY,USERID;
        ProgressDialog progressDialog;
        private ArrayList<DetailsModel> articleList;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this,"Please wait","loading...");
            APIKEY=apikey.getText().toString();
            USERID=userid.getText().toString();
        }
        @Override
        protected String doInBackground(String... params) {
            JSONObject obj= null;
            String content=null;
            try{
                obj= new JSONObject();
                //obj.accumulate("api_key",APIKEY);
                obj.accumulate("api_key","2308691");
                obj.accumulate("user_id",USERID);
                //obj.accumulate("user_id","15");
                content= AsyncTaskHelper.makeServiceCall("http://volive.in/dresscode_new/api/services/Myorders","POST",obj);
            }catch (Exception e){
                e.printStackTrace();
            }
            return content;
        }
        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
            articleList = new ArrayList<DetailsModel>();
            try {
                JSONObject jsonObject=new JSONObject(result);
                String status= jsonObject.getString("status");
                String message= jsonObject.getString("message");
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                JSONArray jsonArray=jsonObject.getJSONArray("data");
                if (jsonArray.length() > 0){
                    for (int i= 0; i<jsonArray.length() ; i++){
                        DetailsModel savedartilcemodel=new DetailsModel();
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        savedartilcemodel.setOrder_id(jsonObject1.getString("order_id"));
                        savedartilcemodel.setOrder_status(jsonObject1.getString("order_status"));
                        savedartilcemodel.setAdded_on(jsonObject1.getString("added_on"));
                        savedartilcemodel.setPaid_amount(jsonObject1.getString("paid_amount"));
                        savedartilcemodel.setRating(jsonObject1.getString("rating"));
                        savedartilcemodel.setProduct_id(jsonObject1.getString("product_id"));
                        savedartilcemodel.setOrder_title(jsonObject1.getString("order_title"));
                        savedartilcemodel.setImage(jsonObject1.getString("image"));
                        articleList.add(savedartilcemodel);
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Data is not available", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            adapter=new SaveDetailsAdapter(MainActivity.this,articleList);
            listdata.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            progressDialog.dismiss();

            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }//onPostExecute

}
