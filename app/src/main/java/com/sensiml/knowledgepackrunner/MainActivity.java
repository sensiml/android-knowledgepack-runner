package com.sensiml.knowledgepackrunner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button run_test_button;
    private TextView tv;

    static {
        //System.loadLibrary("knowledgepack-wrapper");
        System.loadLibrary("sensiml-knowledgepack");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        tv = findViewById(R.id.sample_text);
        initSensiMLKnowledgePackModels();
        run_test_button = (Button)findViewById(R.id.run_test_data);
        run_test_button.setOnClickListener(this);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    private static native void initSensiMLKnowledgePackModels();

    /**
     *
     * @param model model number to run, typically 0
     * @param data array of data to run through knowledgepack.
     * @return integer classification of data
     */
    public native int runSensiMLKnowledgePackModel(int model, short[] data, int data_length);
    // Used for debug. Not needed for base application
    // public native short[] getSensiMLFeatureVector(int model);

    @Override
    public void onClick(View v) {
        StringBuilder sb = new StringBuilder();
        int classification = runSensiMLKnowledgePackModel(0, null, 0);

        sb.append("Model: ");
        sb.append(0);
        sb.append(", Class: ");
        sb.append(classification);

        tv.setText(sb.toString());

    }
}
