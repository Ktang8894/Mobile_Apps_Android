package ktang20.hw2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TopButton extends AppCompatActivity {

    public EditText input; //Use to hold text from phoneInput
    public Button button; //Initialize button for dialBut
    public String inputStr; //Use to hold converted str from input
    public String pattern = "(\\(\\d{3}\\)\\s?)?\\d{3}\\-\\d{4}"; //YYY-ZZZZ,(XXX)YYY-ZZZZ OR (XXX) YYY-ZZZZ)

    public void buttonInit() {
        button = (Button)findViewById(R.id.dialBut); //Assign button to dialBut
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = (EditText)findViewById(R.id.phoneInput); //Get input from phoneInput
                inputStr = input.getText().toString(); //Convert EditText to String
                Intent dialer = new Intent(); //Implicit Intent
                dialer.setAction(Intent.ACTION_DIAL); //Set Intent Action
                //Check if the input includes a match to one of the three formats
                //if (inputStr.matches(pattern1) || inputStr.matches(pattern2)) {
                Pattern p = Pattern.compile(pattern); //Create pattern
                Matcher m = p.matcher(inputStr); //Create Matcher

                if (m.find()) { //If a valid input is found, update string and execute activity
                    inputStr = m.group(0).toString(); //Get first matched string
                    dialer.setData(Uri.parse("tel:" + inputStr)); //Set Intent Data
                    //Check to see if there's any way to run the activity
                    if (dialer.resolveActivity(getPackageManager()) != null) {
                        startActivity(dialer); //Start the activity
                        Intent result = new Intent();
                        setResult(Activity.RESULT_OK,result);
                    }
                }
                else {
                    Intent result = new Intent();
                    setResult(Activity.RESULT_CANCELED,result);
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_button);
        buttonInit();
    }
}
