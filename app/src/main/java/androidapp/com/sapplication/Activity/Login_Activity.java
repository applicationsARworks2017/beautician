package androidapp.com.sapplication.Activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import androidapp.com.sapplication.R;
import androidapp.com.sapplication.Tabs.CostumerSignup;
import androidapp.com.sapplication.Utils.Constants;

import static androidapp.com.sapplication.Utils.Constants.hasPermissions;

public class Login_Activity extends AppCompatActivity {
    TextView tv_signup,tv_forgotpassword;
    RadioGroup radio_user_type;
    RadioGroup user_type;
    LinearLayout lin_signin;
    EditText et_phone,et_password;
    ProgressBar login_loader;
    RadioButton radioButton;

    String[] PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_phone=(EditText)findViewById(R.id.et_phn);
        et_password=(EditText)findViewById(R.id.et_password);
        tv_signup=(TextView)findViewById(R.id.tv_signup);
        tv_forgotpassword=(TextView)findViewById(R.id.tv_forgotpassword);
        radio_user_type=(RadioGroup)findViewById(R.id.radio_user_type);
        lin_signin=(LinearLayout)findViewById(R.id.lin_signin);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // here it is checking whether the permission is granted previously or not
            if (!hasPermissions(this, PERMISSIONS)) {
                //Permission is granted
                ActivityCompat.requestPermissions(this, PERMISSIONS, 1);

            }
        }
/*
*
* btnDisplay.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View v) {

		        // get selected radio button from radioGroup
			int selectedId = radioSexGroup.getCheckedRadioButtonId();

			// find the radiobutton by returned id
		        radioSexButton = (RadioButton) findViewById(selectedId);

			Toast.makeText(MyAndroidAppActivity.this,
				radioSexButton.getText(), Toast.LENGTH_SHORT).show();

		}

	});
*
* */
        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login_Activity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
        lin_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Checklogin();
                // get selected radio button from radioGroup
                int selectedId = radio_user_type.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);

                /*Toast.makeText(Login_Activity.this,
                        radioButton.getText(), Toast.LENGTH_SHORT).show();*/
                if(radioButton.getText().toString().trim().contains("Consumer")){
                    Checklogin("user");
                }
                else{
                    Checklogin("sp");

                }
            }
        });
        tv_forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login_Activity.this,SPHome.class);
                startActivity(intent);
            }
        });
    }

    private void Checklogin(String type) {
        String login_phn_num=et_phone.getText().toString().trim();
        String login_phn_pass=et_password.getText().toString().trim();
        if(type.contentEquals("user")) {
            new LoginAsyntask().execute(login_phn_num, login_phn_pass);
        }
        else{

        }
    }

    private class LoginAsyntask extends AsyncTask<String, Void, Void> {

        private static final String TAG = "SynchMobnum";
        String server_message,id;
        int server_status;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // onPreExecuteTask();
        }

        @Override
        protected Void doInBackground(String... params) {

            try {
                String phone_number = params[0];
                String password = params[1];
                InputStream in = null;
                int resCode = -1;

                String link = Constants.LOGIN_USER ;
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
                        .appendQueryParameter("address", password)
                        .appendQueryParameter("checkin_time", phone_number);

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
                 "checkin_id": null,
                 "status": 1,
                 "message": "Successfully Checked In."
                 }
                 * */

                if (response != null && response.length() > 0) {
                    JSONObject res = new JSONObject(response.trim());
                    server_status = res.optInt("status");
                    if (server_status == 1) {
                        id = res.optString("checkin_id");
                        server_message = res.optString("message");

                    } else {
                        server_message = "Invalid Credentials";
                    }
                }
                return null;

            } catch (SocketTimeoutException exception) {
                server_message = "Network Error";
                Log.e(TAG, "SynchMobnum : doInBackground", exception);
            } catch (ConnectException exception) {
                server_message = "Network Error";
                Log.e(TAG, "SynchMobnum : doInBackground", exception);
            } catch (MalformedURLException exception) {
                server_message = "Network Error";
                Log.e(TAG, "SynchMobnum : doInBackground", exception);
            } catch (IOException exception) {
                server_message = "Network Error";
                Log.e(TAG, "SynchMobnum : doInBackground", exception);
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

            }
        }
    }
}
