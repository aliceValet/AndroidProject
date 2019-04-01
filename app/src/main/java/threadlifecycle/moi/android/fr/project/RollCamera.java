package threadlifecycle.moi.android.fr.project;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.content.Context;


public class RollCamera extends Fragment {


    Button btnsave, btnload, btnimageone, btnimagetwo;
    EditText test;
    ImageView img;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_roll_camera, container, false);

        btnload = view.findViewById(R.id.btnload);
        btnsave = view.findViewById(R.id.btnsave);
        btnimageone = view.findViewById(R.id.btnimageone);
        btnimagetwo = view.findViewById(R.id.btnimagetwo);
        test = view.findViewById(R.id.edittest);
        img = view.findViewById(R.id.img);

        img.setImageResource(R.drawable.badminton1);

        // Inflate the layout for this fragment
        return view;
    }

    public String getTestText() {
        return test.getText().toString();
    }

    public void setTestToClear() {
        test.getText().clear();
    }

    public void setTestText(String str) {
        test.setText(str);
    }

}
