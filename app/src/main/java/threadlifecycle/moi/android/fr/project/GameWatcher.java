package threadlifecycle.moi.android.fr.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameWatcher extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RollCamera fragCam = new RollCamera();
    Searching fragSearch = new Searching();
    Localisation fragLoc = new Localisation();
    Welcome accueil = new Welcome();

    private static final String FILE_NAME_ONE = "test.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_watcher);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.ContentToReplace, accueil).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game_watcher, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id==R.id.Location){
            getSupportFragmentManager().beginTransaction().replace(R.id.ContentToReplace, fragLoc).commit();


        }
        else if(id==R.id.Camera){

            //new RollCamera().OpenCamera();
            getSupportFragmentManager().beginTransaction().replace(R.id.ContentToReplace, fragCam).commit();

        }
        else if(id==R.id.Stats){
            Intent intent = new Intent( this, Stat.class);
            if (intent != null) {
                startActivity(intent);
            }
        }
        else if(id==R.id.Search){
            getSupportFragmentManager().beginTransaction().replace(R.id.ContentToReplace, fragSearch).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /*public void save(View view) {
        String text = fragCam.getTestText();
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME_ONE, Context.MODE_PRIVATE);
            //getBytes permet d'ecrire les donnees du text dans le file
            fos.write(text.getBytes());

            fragCam.setTestToClear();
            //file pas accessible directement mais seulement au travers de l'app pour des raisons de securite.
            Toast.makeText(this,"Saved to " + getFilesDir() + "/" + FILE_NAME_ONE, Toast.LENGTH_LONG ).show();
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

    public void load(View view) {
        FileInputStream fis = null;

        try {
            fis = openFileInput(FILE_NAME_ONE);
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
            fragCam.setTestText(sb.toString());
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
    }*/

}
