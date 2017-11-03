package androidapp.com.sapplication.Tabs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import androidapp.com.sapplication.Activity.Categories;
import androidapp.com.sapplication.Pojo.CategoryList;
import androidapp.com.sapplication.R;
import androidapp.com.sapplication.Utils.CheckInternet;
import androidapp.com.sapplication.Utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SPSignup.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SPSignup#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SPSignup extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Context _context;
    private OnFragmentInteractionListener mListener;
    Double latitude,longitude;
    EditText et_latlong;
    public static EditText et_category;
    public static String selected_category_id;
    ProgressBar loader_signup;
    private int server_status;
    private String server_message;

    RelativeLayout category_rel;

    public SPSignup() {
        // Required empty public constructor
    }

    public SPSignup(Double lat, Double lng) {
        this.latitude=lat;
        this.longitude=lng;

    }

    public static SPSignup newInstance(String param1, String param2) {
        SPSignup fragment = new SPSignup();
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
        View v=inflater.inflate(R.layout.fragment_spsignup, container, false);
        loader_signup=(ProgressBar)v.findViewById(R.id.loader_signup);
        et_latlong=(EditText)v.findViewById(R.id.et_latlong);
        et_category=(EditText)v.findViewById(R.id.et_category);
        category_rel=(RelativeLayout)v.findViewById(R.id.category_rel);
        et_latlong.setText(latitude+","+longitude);
        /*if(CheckInternet.getNetworkConnectivityStatus(getActivity())){
            new getcategoryList().execute();
        }
        else{
            Constants.noInternetDialouge(_context,"Kindly Check Your Internet Connection");
        }*/
        et_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Categories.class);
                startActivity(intent);
            }
        });

        return v;
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
       /* if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
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


}
