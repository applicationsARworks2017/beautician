package androidapp.com.sapplication.Activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import androidapp.com.sapplication.Fragment.HomeFragment;
import androidapp.com.sapplication.Fragment.Profile;
import androidapp.com.sapplication.Fragment.ServiceList;
import androidapp.com.sapplication.R;

public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNav;
    private int mSelectedItem;
    private static final String SELECTED_ITEM = "arg_selected_item";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mBottomNav = (BottomNavigationView) findViewById(R.id.navigation);
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });
        MenuItem selectedItem;
        if (savedInstanceState != null) {
            mSelectedItem = savedInstanceState.getInt(SELECTED_ITEM, 0);
            selectedItem = mBottomNav.getMenu().findItem(mSelectedItem);
        } else {
            selectedItem = mBottomNav.getMenu().getItem(0);
        }
        selectFragment(selectedItem);

    }
    private void selectFragment(MenuItem item) {
        Fragment frag = null;

        switch (item.getItemId()) {
            case R.id.home:
                // Visitors Exit
                frag=new HomeFragment();
                item.setCheckable(true);

//                toolbar.setTitle("Exit");
                break;
            case R.id.mylist:
                frag=new ServiceList();
                item.setCheckable(true);

                break;

            case R.id.profile:
                frag=new Profile();
                item.setCheckable(true);
                break;
            default:
                frag=new HomeFragment();
                item.setCheckable(true);

        }
        mSelectedItem = item.getItemId();

        // uncheck the other items.
        /*for (int i = 0; i< mBottomNav.getMenu().size(); i++) {
            MenuItem menuItem = mBottomNav.getMenu().getItem(i);
            menuItem.setChecked(menuItem.getItemId() == item.getItemId());
        }*/

        updateToolbarText(item.getTitle());

        if (frag != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, frag, frag.getTag());
            ft.commit();
        }
    }
    private void updateToolbarText(CharSequence text) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(text);
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_ITEM, mSelectedItem);
        super.onSaveInstanceState(outState);
    }
}
