package ktang20.project3a3;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class BasketballActivity extends AppCompatActivity implements BasketballListFragment.BasketballListListener{

    //Gets called by the BasketballListFragment
    @Override
    public void updateWebView(int position) {
        // BasketballWebFragment webFragment = (BasketballWebFragment) getFragmentManager().findFragmentById(R.id.webFragment);
        // webFragment.setWebView(position);


        BasketballWebFragment webFragment = new BasketballWebFragment();
        webFragment.setWebView(position);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, webFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    //Get backstack fragments
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basketball);
    }

    //Options Menu Buttons
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.basketball:
                Intent basketball = new Intent(this, BasketballActivity.class);
                //needs the no history tag to smooth the use of the options menu
                basketball.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(basketball);
                return true;
            case R.id.baseball:
                Intent baseball = new Intent(this, BaseballActivity.class);
                //needs the no history tag to smooth the use of the options menu
                baseball.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(baseball);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Initiates Option Menu Buttons
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
    }
}
