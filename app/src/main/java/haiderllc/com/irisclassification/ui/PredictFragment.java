package haiderllc.com.irisclassification.ui;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

import haiderllc.com.irisclassification.LoadingClass;
import haiderllc.com.irisclassification.R;
import haiderllc.com.irisclassification.ui.adapters.IrisAdapter;
import haiderllc.com.irisclassification.models.Iris;
import haiderllc.com.irisclassification.network.MachineLearningInstance;
import haiderllc.com.irisclassification.sampledata.SampleData;

/**
 * Created by Haider on 9/24/2014.
 */
public class PredictFragment extends Fragment {

    private ListView mListView;
    private ArrayList<Iris> irisArrayList;
    private ArrayList<Iris> trainingSet = new ArrayList<Iris>();
    private ArrayList<Iris> testSet = new ArrayList<Iris>();
    private MachineLearningInstance machineLearningInstance;
    private IrisAdapter irisAdapter;

    private FileOutputStream rawFile;
    private File rawFileOutput;
    private Uri rawUri;

    /*
     * These are values for the NeuralNetwork Constructor
     */
    private final String comma = ",";
    private final String irisSetosa = "Iris-setosa";
    private final String irisVersicolor = "Iris-versicolor";
    private final String irisVirginica = "Iris-virginica";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_predict, container, false);

//        rawUri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.iris);
//        rawFileOutput = new File(rawUri.toString());

        //rawFileOutput = new File("C:\\iris\\iris.data");
        //initData(rawFileOutput);

        mListView = (ListView) view.findViewById(R.id.listView);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        irisAdapter = new IrisAdapter(getActivity(), SampleData.generateTestList());
        mListView.setAdapter(irisAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), LoadingClass.class);
                i.putExtra("id", id);
                startActivity(i);
            }
        });
    }

    /***********************************************************************************************
    DISREGARD ALL CODE BELOW SINCE IT IS ONLY USED IF WE ARE TRYING TO OPEN A FILE.
     **********************************************************************************************/


    public FileOutputStream createFile() {
        try {
            rawFileOutput = new File("dataFile");
            InputStream inputStream = getActivity().getResources().openRawResource(R.raw.iris);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int size = 0;
            // Read the entire resource into a local byte buffer
            byte[] buffer = new byte[1024];
            while ((size = inputStream.read(buffer, 0, 1024)) >= 0) {
                outputStream.write(buffer, 0, size);
            }
            inputStream.close();
            buffer = outputStream.toByteArray();

            rawFile = new FileOutputStream("iris.data");
            rawFile.write(buffer);
            rawFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rawFile;
    }

    public void initData(File f) {

        try {

            int noOfRowsInData = 0;

            LineNumberReader lnr = new LineNumberReader(new FileReader(f));
            try {
                lnr.skip(Long.MAX_VALUE);
                noOfRowsInData = lnr.getLineNumber();
                //System.out.println(noOfRowsInData);
                lnr.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            irisArrayList = new ArrayList<Iris>();

            // While there is another line in inFile.
            Scanner inFile = new Scanner(f);

            for (int i = 0; i < noOfRowsInData - 1; i++) {

                if (inFile.hasNextLine()) {
                    // Store line into String
                    String line = inFile.nextLine();

                    // Partition values into separate elements in array
                    String[] numbers = line.split(comma);
                    Log.i(getClass().getSimpleName(), numbers[i]);

                    // Grab values from that line and store it into a Iris ArrayList Item
                    irisArrayList.add(i, new Iris(i, numbers[0], numbers[1], numbers[2], numbers[3], numbers[4]));

                }
            }


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (irisArrayList == null) {
            Toast.makeText(getActivity(), "IRIS DATA IS NULL!", Toast.LENGTH_LONG);
        }

        for (int i = 0; i < irisArrayList.size(); i++) {
            Toast.makeText(getActivity(), irisArrayList.get(i).getId(), Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(), irisArrayList.get(i).getIrisClassName(), Toast.LENGTH_SHORT).show();

        } // Used to check values

        //sortData();
    }

    private File getFileFromRawResource(Uri rUri) {
        String uri = rUri.toString();
        String fn;

        if (uri.contains("/raw/")) {
            // Try to get the resource name
            String[] parts = uri.split("/");
            fn = parts[parts.length - 1];
        } else {
            return null;
        }
        // Set the externalstoragedirectory path
        String destination = Environment.getExternalStorageDirectory() + "/iris.data";

        // Setup try-catch to grab the raw file into the destination path.
        try {
            // Use reflection to get the resource ID of the raw resource
            // because we need to get the InputStream to that specific Raw file
            R.raw r = new R.raw();
            Field frame = R.raw.class.getDeclaredField(fn);
            frame.setAccessible(true);
            int id = (Integer) frame.get(r);

            // Now we grab the InputStream
            InputStream inputStream = getResources().openRawResource(id);
            FileOutputStream fileOutputStream = new FileOutputStream(destination);

            // IOUtils is a class from the Apache Commons IO
            // It writes an InputStream to an OutputStream
            IOUtils.copy(inputStream, fileOutputStream);
            fileOutputStream.close();
            return new File(destination);
        } catch (NoSuchFieldException e) {
            Log.e("IrisClassification", "NoSuchFieldException in getFileFromRawResource()");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Log.e("IrisClassification", "IllegalAccessException in getFileFromRawResource()");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            Log.e("IrisClassification", "FileNotFoundException in getFileFromRawResource()");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("IrisClassification", "IOException in getFileFromRawResource()");
            e.printStackTrace();
        }
        return null;
    }

}
