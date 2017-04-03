package ktang20.project3a3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class A3 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == "p3_basketball") {
            Toast.makeText(context, "A3: Basketball Selected", Toast.LENGTH_SHORT).show();
            Intent basketball = new Intent(context, BasketballActivity.class);
            //Needs the activity new task flag since starting from broadcast reciever,
            //and then needs the no history tag to smooth the use of the options menu
            basketball.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
            context.startActivity(basketball);
        }

        if (intent.getAction() == "p3_baseball") {
            Toast.makeText(context, "A3: Baseball Selected", Toast.LENGTH_SHORT).show();
            Intent baseball = new Intent(context, BaseballActivity.class);
            baseball.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
            context.startActivity(baseball);
        }
    }
}
