package threadlifecycle.moi.android.fr.project;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class Notes extends Fragment {

    Button btnsave, btnload;
    EditText test;

    private static final String FILE_NAME_ONE = "test.txt";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        btnload = view.findViewById(R.id.btnload);
        btnsave = view.findViewById(R.id.btnsave);
        test = view.findViewById(R.id.edittest);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = test.getText().toString();
                FileOutputStream fos = null;

                try {
                    fos = getContext().openFileOutput(FILE_NAME_ONE, Context.MODE_PRIVATE);
                    //getBytes permet d'ecrire les donnees du text dans le file
                    fos.write(text.getBytes());

                    test.getText().clear();
                    //file pas accessible directement mais seulement au travers de l'app pour des raisons de securite.
                   // Toast.makeText(this,"Saved to " + getFilesDir() + "/" + FILE_NAME_ONE, Toast.LENGTH_LONG ).show();
                    //n'arrive pas a ouvrir le file
                } catch (FileNotFoundException e) {
                    e.printStackTrace();

                    //n'arrive pas a ecrire dedans
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //whatever happens
                finally {
                    //si le fichier existe, ferme le bordel
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            //sinon renvoie une erreur
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        btnload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileInputStream fis = null;

                try {
                    fis = getContext().openFileInput(FILE_NAME_ONE);
                    //recupere les donnees du file. It reads bytes and decodes them into characters using a specified charset
                    InputStreamReader isr = new InputStreamReader(fis);
                    //classe permettant de lire et traiter toutes les donnees du file
                    BufferedReader br = new BufferedReader(isr);
                    //liste de strings (en fait classe à part entiere qui enfile les strings comme des perles)
                    StringBuilder sb = new StringBuilder();
                    String text;
                    //tant qu'il y a des lignes a lire
                    while ((text = br.readLine()) != null) {
                        //text = ligne recuperee dans br
                        sb.append(text).append("\n");
                    }
                    //revoir la note ecrite precedemment en la loadant
                    test.setText(sb.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if(fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    /*
    public String getTestText() {
        return test.getText().toString();
    }

    public void setTestToClear() {
        test.getText().clear();
    }

    public void setTestText(String str) {
        test.setText(str);
    }
    */

}
