package haiderllc.com.irisclassification.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import haiderllc.com.irisclassification.R;

/**
 * Created by ZkHaider on 5/29/15.
 */
public class StartingActivity extends Activity {

    private ImageView appLogo;
    // Set the display time, in milliseconds (or extract it out as a configurable parameter)
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);

        appLogo = (ImageView) findViewById(R.id.appLogo);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent openMainActivity =  new Intent(StartingActivity.this, MainActivity.class);
                startActivity(openMainActivity);
                finish();

            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}