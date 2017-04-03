package ktang20.hw2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {

    public Button button1;
    public Button button2;

    //Initialize Top Button
    public void top_init() {
        button1 = (Button)findViewById(R.id.button1); //Assign button1
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent top = new Intent(MainActivity.this, TopButton.class); //Explicit Intent
                startActivityForResult(top, 1);
            }


        });
    }

    //Get result code
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK) {
                TextView topResult = (TextView)findViewById(R.id.textView2);
                topResult.setText("Top Button Activity ResultCode\nSUCCESS");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                TextView topResult = (TextView)findViewById(R.id.textView2);
                topResult.setText("Top Button Activity ResultCode\nFAIL");
            }
        }
    }

    //Initialize Bottom Button
    public void bot_init() {
        button2 = (Button)findViewById(R.id.button2); //Assign button2
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browser = new Intent(); //Implicit Intent
                browser.setAction(Intent.ACTION_VIEW); //Set Intent Action
                browser.setData(Uri.parse("https://developer.android.com/index.html")); //Set Intent Data
                //Check if there's something to run the activity
                if (browser.resolveActivity(getPackageManager()) != null) {
                    startActivity(browser);
                }
            }
        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        top_init();
        bot_init();
    }


}
