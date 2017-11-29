package beautician.com.sapplication.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import beautician.com.sapplication.Pojo.RatingsPoints;
import beautician.com.sapplication.R;

public class GiveCommentActivity extends AppCompatActivity {
    RatingBar customer_ratings;
    String ratings_value;
    RelativeLayout rel_ratingpoints;
    ArrayList<RatingsPoints> ratingspoints;
    RatingspointsAdapter ratingspointsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_comment);
        customer_ratings=(RatingBar)findViewById(R.id.customer_ratings);
        rel_ratingpoints=(RelativeLayout)findViewById(R.id.rel_ratingpoints);
        customer_ratings.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //ratings.setText(String.valueOf(rating));
                ratings_value=String.valueOf(rating);
                if(rating<4.0){
                        rel_ratingpoints.setVisibility(View.VISIBLE);
                    }
                    else{
                        rel_ratingpoints.setVisibility(View.GONE);
                }
            }


        });

        ratingspoints = new ArrayList();
        ratingspoints.add(new RatingsPoints("Guard Quality", false));
        ratingspoints.add(new RatingsPoints("Operations", false));
        ratingspoints.add(new RatingsPoints("Admin", false));
        ratingspoints.add(new RatingsPoints("Others", false));
        radapter = new RatingspointsAdapter(ratingspoints, getApplicationContext());
        lv_ratings_value.setAdapter(radapter);

    }
}
