package beautician.com.sapplication.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import beautician.com.sapplication.R;

public class ShopDetails extends AppCompatActivity {
    String shop_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            shop_id = extras.getString("SHOP_ID");
            // and get whatever type user account id is
        }
    }
}
