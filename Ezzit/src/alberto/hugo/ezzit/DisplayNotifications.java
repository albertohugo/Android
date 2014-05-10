package alberto.hugo.ezzit;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;

import com.sunil.timepickerdemo.R;
 
public class DisplayNotifications extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        int notifID = getIntent().getExtras().getInt("NotifID");

        Intent i = new Intent(DisplayNotifications.this, AlarmDetails.class);
        i.putExtra("NotifID", notifID);  
    
        PendingIntent detailsIntent = 
            PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
 
        NotificationManager nm = (NotificationManager)
            getSystemService(NOTIFICATION_SERVICE);
        Notification notif = new Notification(
            R.drawable.ic_launcher, 
            "Time's up!",
            System.currentTimeMillis());
 
        CharSequence from = "Ezzit Notification";
        CharSequence message = "";        
        notif.setLatestEventInfo(this, from, message, detailsIntent);
        
        // Get instance of Vibrator from current Context
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 300 milliseconds
        v.vibrate(300);
        
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {}

        //---100ms delay, vibrate for 250ms, pause for 100 ms and
        // then vibrate for 500ms---
        notif.vibrate = new long[] { 100, 250, 100, 500};        
        nm.notify(notifID, notif);
        //---destroy the activity---
        finish();
    }
}
