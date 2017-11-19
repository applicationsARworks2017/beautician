package androidapp.com.sapplication.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import androidapp.com.sapplication.Pojo.User;
import androidapp.com.sapplication.R;
import androidapp.com.sapplication.SplashScreen;
import androidapp.com.sapplication.Utils.CheckInternet;
import androidapp.com.sapplication.Utils.Constants;

public class PostActivity extends AppCompatActivity {
    TextView tv_services;
    Spinner adult;
    String user_id;
    EditText et_contentheading;
    Button submit_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        user_id = PostActivity.this.getSharedPreferences(Constants.SHAREDPREFERENCE_KEY, 0).getString(Constants.USER_ID, null);

        tv_services=(TextView)findViewById(R.id.tv_services);
        tv_services.setText(RequestSubcategories.subcateryName);
        adult=(Spinner)findViewById(R.id.adult_spin);
        et_contentheading=(EditText)findViewById(R.id.et_contentheading);
        submit_post=(Button)findViewById(R.id.submit_post);
        submit_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postDetails=et_contentheading.getText().toString().trim();
                String numof=adult.getSelectedItem().toString();
                if(CheckInternet.getNetworkConnectivityStatus(PostActivity.this)){
                    Postservice postservice=new Postservice();
                    postservice.execute(user_id,Categories.category_id,RequestSubcategories.SubcateryId,numof,postDetails);
                }
                else{
                    Constants.noInternetDialouge(PostActivity.this,"No Internet");

                }



            }
        });
    }



    /*
    *
    * "user_id:9
category_id:1
sub_category_id:1
no_of_user:5
remarks:jhgjhghjgjg"
    * */
    private class Postservice extends AsyncTask<String, Void, Void> {

        private static final String TAG = "SynchMobnum";
        String server_message;
        String id,name,email,mobile,photo,created_dt,modified_dt,usertype;

        int server_status;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // onPreExecuteTask();
        }

        @Override
        protected Void doInBackground(String... params) {

            try {
                String _user_id = params[0];
                String _cat_id = params[1];
                String _scat_id = params[2];
                String _no_of_user = params[3];
                String _remarks = params[4];
                InputStream in = null;
                int resCode = -1;

                String link =Constants.ONLINEURL+ Constants.SERVICE_REQUEST ;
                URL url = new URL(link);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setAllowUserInteraction(false);
                conn.setInstanceFollowRedirects(true);
                conn.setRequestMethod("POST");

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("user_id", _user_id)
                        .appendQueryParameter("category_id", _cat_id)
                        .appendQueryParameter("sub_category_id", _scat_id)
                        .appendQueryParameter("no_of_user", _no_of_user)
                        .appendQueryParameter("remarks", _remarks);

                //.appendQueryParameter("deviceid", deviceid);
                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();

                conn.connect();
                resCode = conn.getResponseCode();
                if (resCode == HttpURLConnection.HTTP_OK) {
                    in = conn.getInputStream();
                }
                if (in == null) {
                    return null;
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                String response = "", data = "";

                while ((data = reader.readLine()) != null) {
                    response += data + "\n";
                }

                Log.i(TAG, "Response : " + response);

                /**
                 * {
                 {
                 "res": {
                 "message": "The service request has been saved.",
                 "status": 1
                 }
                 }
                 * */

                if (response != null && response.length() > 0) {
                    JSONObject res = new JSONObject(response.trim());
                    JSONObject j_obj=res.getJSONObject("res");
                    server_status = j_obj.optInt("status");
                    if (server_status == 1) {
                        server_message = "Posted";
                    }
                    else{
                        server_message = "Posting failed";

                    }

                }
                return null;
            } catch (Exception exception) {
                server_message = "Network Error";
                Log.e(TAG, "SynchMobnum : doInBackground", exception);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void user) {
            super.onPostExecute(user);
            if (server_status == 1) {
                Intent intent = new Intent(PostActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
            Toast.makeText(PostActivity.this,server_message,Toast.LENGTH_SHORT).show();
        }
    }
}
