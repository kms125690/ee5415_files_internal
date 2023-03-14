package com.example.filesinternal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText mEditText;
    static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.editText1);
    }

    public void onClickSave(View v) {
        String str = mEditText.getText().toString();
        try {
            FileOutputStream fOut = openFileOutput("textfile.txt", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            // --- Write the string to the file ---
            osw.write(str);
            osw.flush();
            osw.close();
            // --- Display file saved message ---
            Toast.makeText(getBaseContext(), "File saved successfully!", Toast.LENGTH_LONG).show();
            //--- Clears the EditText ---
            mEditText.setText("");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    public void onClickLoad(View v) {
        try {
            FileInputStream fIn = openFileInput("textfile.txt");
            InputStreamReader isr = new InputStreamReader(fIn);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;
            while ((charRead = isr.read(inputBuffer)) > 0) {
                //--- Convert the chars to a String ---
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                s += readString;
                inputBuffer = new char[READ_BLOCK_SIZE];
            }
            //--- Set the EditText to the text that has been read ---
            mEditText.setText(s);
            Toast.makeText(getBaseContext(), "File loaded successfully!", Toast.LENGTH_LONG).show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}