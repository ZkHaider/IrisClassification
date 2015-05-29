package haiderllc.com.irisclassification.ui;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import haiderllc.com.irisclassification.R;
import haiderllc.com.irisclassification.ui.widget.FButton;

/**
 * Created by Haider on 9/24/2014.
 */
public class TrainOrPredictActivity extends Activity {

    private FButton trainButton;
    private FButton predictButton;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_or_predict);

        trainButton = (FButton) findViewById(R.id.trainButton);
        predictButton = (FButton) findViewById(R.id.predictButton);

        // Go to train fragment
        trainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = getFragmentManager().beginTransaction();
                TrainFragment trainFragment = new TrainFragment();
                fragmentTransaction.replace(R.id.container, trainFragment);
                fragmentTransaction.commit();
            }
        });

        // Go to predict fragment
        predictButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = getFragmentManager().beginTransaction();
                PredictFragment predictFragment = new PredictFragment();
                fragmentTransaction.replace(R.id.container, predictFragment);
                fragmentTransaction.commit();
            }
        });

    }

}
