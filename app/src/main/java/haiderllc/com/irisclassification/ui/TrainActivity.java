package haiderllc.com.irisclassification.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Highlight;

import java.util.ArrayList;

import haiderllc.com.irisclassification.R;
import haiderllc.com.irisclassification.models.Iris;
import haiderllc.com.irisclassification.sampledata.SampleData;

/**
 * Created by Haider on 9/24/2014.
 */
public class TrainActivity extends AppCompatActivity {

    private static final int MAX_VISIBLE_COUNT = 200;

    private RelativeLayout mBubbleChartLayout;
    private BubbleChart mBubbleChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);

        ArrayList<Iris> irises = SampleData.generateTrainingList();

        initViews();
        initListeners();
    }

    private void initViews() {
        mBubbleChartLayout = (RelativeLayout) findViewById(R.id.bubbleChartLayout);

        // Programmatically create a bubble chart
        mBubbleChart = new BubbleChart(this);
        mBubbleChartLayout.addView(mBubbleChart);
        mBubbleChart.invalidate();
    }

    private void initListeners() {
        mBubbleChart.setDescription("");
        mBubbleChart.setTouchEnabled(true);
        mBubbleChart.setHighlightEnabled(true);

        // Enable Scrolling and Dragging
        mBubbleChart.setDragEnabled(true);
        mBubbleChart.setScaleEnabled(true);

        mBubbleChart.setMaxVisibleValueCount(MAX_VISIBLE_COUNT);
        mBubbleChart.setPinchZoom(true);

        mBubbleChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                // Init listener when value selected

            }

            @Override
            public void onNothingSelected() {
                // Do nothing

            }
        });
    }

}
