package alberto.hugo.ezzit;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;

import com.sunil.timepickerdemo.R;
 
public class AlarmDetails extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarmdetails);
        NotificationManager nm = (NotificationManager) 
            getSystemService(NOTIFICATION_SERVICE);
 
        //---cancel the notification---
        nm.cancel(getIntent().getExtras().getInt("NotifID"));        
    }
}