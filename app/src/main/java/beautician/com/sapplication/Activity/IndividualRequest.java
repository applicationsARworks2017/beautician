package beautician.com.sapplication.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import beautician.com.sapplication.R;

public class IndividualRequest extends AppCompatActivity {
    TextView shopName;
    EditText et_details;
    Spinner sp_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_request);
        shopName=(TextView)findViewById(R.id.postHeading);
        et_details=(EditText)findViewById(R.id.et_contentheading);
        sp_num=(Spinner)findViewById(R.id.adult_spin);
    }
}
