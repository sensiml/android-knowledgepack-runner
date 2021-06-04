package com.sensiml.knowledgepackrunner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class KnowledgePackActivity extends AppCompatActivity implements View.OnClickListener{
    private Button run_test_button;
    private TextView tv;

    //FILL_KB_MODEL_INDEXES_JAVA

    static {
        System.loadLibrary("sensiml");
        System.loadLibrary("knowledgepack-wrapper");
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
     * Clear the specified model and reset its segmenters, features, etc.
     * @param model_index Model number to reset.
     */
    private static native void resetModel(int model_index);

    /**
     * Run SensiML model with data, and get model results
     * @param data sensor data sample
     * @param nsensors number of sensors being used
     * @param model_index Model index to run
     * @return -1 if not enough data to classify, > 0 for a classification.
     */
    public native int runModel(short[] data, int nsensors, int model_index);

    /**
     * Determine if knowledge pack is using test data
     * @return 1 if usign test data, 0 otherwise
     */
    public native boolean usingTestData();

    /**
     * Run SensiML model with cascade resets, and get model results
     *
     * @param data sensor data sample
     * @param nsensors number of sensors being used
     * @param model_index Model index to run
     * @return -1 if not enough data to classify, > 0 for a classification.
     */
    public native int runModelWithCascadeReset(short[] data, int nsensors, int model_index);

    private native int runSensiMLKnowledgePackModelTestData();

    @Override
    public void onClick(View v) {

        int ret;

        if (usingTestData())
        {
            ret = runSensiMLKnowledgePackModelTestData();
            if(ret >= 0){
                sml_output_results(0,ret);
            }
        }

        //FILL_RUN_MODEL_MOTION_JAVA
        //FILL_RUN_MODEL_AUDIO_JAVA
        //FILL_RUN_MODEL_CUSTOM_JAVA

    }

    public void sml_output_results(int model, int classification)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Model: ");
        sb.append(model);
        sb.append(" Classification: ");
        sb.append(classification);
        tv.setText(sb.toString());
    }
}
