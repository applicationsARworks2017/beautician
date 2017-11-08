package androidapp.com.sapplication.Tabs;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mikhaellopez.circularimageview.CircularImageView;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidapp.com.sapplication.Activity.Login_Activity;
import androidapp.com.sapplication.R;
import androidapp.com.sapplication.SplashScreen;
import androidapp.com.sapplication.Utils.CheckInternet;
import androidapp.com.sapplication.Utils.Constants;
import androidapp.com.sapplication.Utils.MultipartUtility;

public class CostumerSignup extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText et_name,et_phone,et_password,et_cpassword, et_mail;
    private CircularImageView iv_avtar;
    private Button bt_submit;
    Context _context;
    private int server_status;
    private String server_response;
    private RelativeLayout rel_user_signup;
    private File imageFile;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    private OnFragmentInteractionListener mListener;

    public CostumerSignup() {
        // Required empty public constructor
    }

    public static CostumerSignup newInstance(String param1, String param2) {
        CostumerSignup fragment = new CostumerSignup();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_costumer_signup, container, false);
        iv_avtar=(CircularImageView) v.findViewById(R.id.iv_user_pic);
        et_name=(EditText)v.findViewById(R.id.et_user_name);
        et_phone=(EditText)v.findViewById(R.id.et_user_phone);
        et_mail=(EditText)v.findViewById(R.id.et_user_mail);
        et_password=(EditText)v.findViewById(R.id.et_user_pass);
        et_cpassword=(EditText)v.findViewById(R.id.et_user_c_pass);
        rel_user_signup=(RelativeLayout)v.findViewById(R.id.rel_user_signup);
        bt_submit=(Button)v.findViewById(R.id.bt_user_submit);
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckInternet.getNetworkConnectivityStatus(getActivity())){
                    field_validation();
                }
                else{
                    Constants.noInternetDialouge(_context,"Kindly Check Your Internet Connection");
                }
            }
        });

        return v;
    }

    private void field_validation() {
        String name=et_name.getText().toString().trim();
        String phone=et_phone.getText().toString().trim();
        String email=et_mail.getText().toString().trim();
        String pass=et_password.getText().toString().trim();
        String cpass=et_cpassword.getText().toString().trim();

        if(name.contains("") && name.length()<=0){
        showSnackBar("Enter Name ");
        }
        else if(phone.contains("") && phone.length()<=0){
            showSnackBar("Enter Phone");
        }
        else if(email.contains("") && email.length()<=0){
            showSnackBar("Enter email");
        }
        else if(!email.matches(emailPattern)){
            showSnackBar("Enter Valid Email");
        }

        else if(pass.contains("") && pass.length()<=0){
            showSnackBar("Enter Password");
        }
        else if(cpass.contains("") && cpass.length()<=0){
            showSnackBar("Enter Confirm Password");
        }
        else if(!pass.contentEquals(cpass)){
            showSnackBar("Confirm password should equal to password");
        }
        else{
            new SignUpAsyntask().execute(name,phone,email,pass);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    void showSnackBar(String message){
        Snackbar snackbar = Snackbar
                .make(rel_user_signup, message, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(Color.parseColor("#7a2da6"));

        snackbar.show();
    }
    /**
     * User Registration
     */
    private class SignUpAsyntask extends AsyncTask<String, Void, Void> {

        String TAG = "Sign Up";
        private boolean is_success = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(String... params) {
            String charset = "UTF-8";
            String requestURL = "";
            //requestURL = Config.API_BASE_URL + Config.POST_CITIZEN_NEWS;
            requestURL = Constants.ONLINEURL + Constants.USER_REGISTRATION;

            try {
                String _phone = params[1];
                String _name = params[0];
                String _email = params[2];
                String _password = params[3];
                MultipartUtility multipart = new MultipartUtility(requestURL, charset);
                multipart.addFormField("mobile", _phone);
                multipart.addFormField("name", _name);
                multipart.addFormField("email", _email);
                multipart.addFormField("password", _password);
                multipart.addFormField("usertype", "user");

                // after completion of image work please enable this
                if (imageFile != null) {
                    multipart.addFilePart("photo", imageFile);
                }



                List<String> response = multipart.finish();
                System.out.println("SERVER REPLIED:");
                String res = "";
                for (String line : response) {
                    res = res + line + "\n";
                }
                Log.i(TAG, res);


                /*
                    "status": 1,
    "message": "Successfully inserted"(
                * */

                if (res != null && res.length() > 0) {
                    JSONObject res_server = new JSONObject(res.trim());
                    JSONObject newObj=new JSONObject(String.valueOf(res_server.getJSONObject("res")));
                    server_status = newObj.optInt("status");
                    if (server_status == 1) {
                        server_response = "Registration Successful";

                    } else {
                        server_response = "Sorry !! Entry failed";
                    }
                }
            } catch (JSONException e) {
                server_response = "Network Error";
                e.printStackTrace();
            } catch (IOException e) {
                server_response = "Network Error";
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(final Void result) {
            super.onPostExecute(result);
            if (server_status == 1) {
                Intent intent = new Intent(getActivity(), Login_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
            showSnackBar(server_response);
            //Toast.makeText(UploadAssets.this,server_message,Toast.LENGTH_LONG).show();
        }
    }


}
