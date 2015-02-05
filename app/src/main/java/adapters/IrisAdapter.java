package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import haiderllc.com.irisclassification.R;
import models.Iris;

/**
 * Created by Haider on 9/24/2014.
 */
public class IrisAdapter extends ArrayAdapter<Iris> {

    private Context context;
    private ArrayList<Iris> irisItems;

    public IrisAdapter(Context context, ArrayList<Iris> irisItems) {
        super(context, R.layout.iris_item, irisItems);
        this.context = context;
        this.irisItems = irisItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1. Create the inflator
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from the Inflator
        View rowView = null;
        rowView = inflater.inflate(R.layout.iris_item, parent, false);

        // 3. Get Icon, title, and counter for number of pictures views from the rowView
        TextView sepalLength = (TextView) rowView.findViewById(R.id.sepalLength);
        TextView sepalWidth = (TextView) rowView.findViewById(R.id.sepalWidth);
        TextView petalLength = (TextView) rowView.findViewById(R.id.petalLength);
        TextView petalWidth = (TextView) rowView.findViewById(R.id.petalWidth);

        // 4. Set the text for the TextView
        sepalLength.setText(irisItems.get(position).getSepalLengthCm());
        sepalWidth.setText(irisItems.get(position).getSepalWidthCm());
        petalLength.setText(irisItems.get(position).getPetalLengthCm());
        petalWidth.setText(irisItems.get(position).getPetalWidthCm());

        // 5. Return the view
        return rowView;
    }

}
