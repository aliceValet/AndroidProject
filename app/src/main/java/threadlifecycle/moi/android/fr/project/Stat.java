package threadlifecycle.moi.android.fr.project;

import android.arch.persistence.room.Database;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import threadlifecycle.moi.android.fr.project.Database.MatchRepository;
import threadlifecycle.moi.android.fr.project.Local.MatchDataSource;
import threadlifecycle.moi.android.fr.project.Local.MatchDatabase;
import threadlifecycle.moi.android.fr.project.Model.Match;

public class Stat extends AppCompatActivity {

    SQLiteDatabase db;

    private TextView dureeMoyenne, fauteMoyenne, letMoyenne;

    float dMoy = 0, fMoy = 0, lMoy = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        dureeMoyenne = findViewById(R.id.dureemoy);
        fauteMoyenne = findViewById(R.id.fautemoy);
        letMoyenne = findViewById(R.id.letmoy);

        db = new SQLiteDatabase(this);

        getMoyenne();

        //Actionbar
        /*setSupportActionBar((Toolbar) findViewById(R.id.home_toolbar));
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        compositeDisposable = new CompositeDisposable();

        lstMatches = findViewById(R.id.lstMatches);
        fab = findViewById(R.id.fab);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, matchList);
        registerForContextMenu(lstMatches);
        lstMatches.setAdapter(adapter);

        MatchDatabase matchDatabase = MatchDatabase.getInstance(this);
        matchRepository = MatchRepository.getInstance(MatchDataSource.getInstance(matchDatabase.matchDAO()));

        loadData();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disposable disposable = (Disposable) io.reactivex.Observable.create(new ObservableOnSubscribe<Object>()
                {
                    @Override
                    public void subscribe(ObservableEmitter<Object> e) throws Exception
                    {
                        Match match = new Match("Bob", "Steve", 1, 2,
                        "DH", "17:20:53", "Blip", 3,
                        4, 5, 6);
                        matchRepository.insertMatch(match);
                        e.onComplete();
                    }
                })
                        .observeOn(AndroidSchedulers.mainThread())//mainthread is when user interactions take place
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer() {
                            @Override
                            public void accept(Object o) throws Exception {
                                Toast.makeText(Stat.this, "Match added !", Toast.LENGTH_SHORT).show();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(Stat.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }, new Action() {
                            @Override
                            public void run() throws Exception {
                                loadData();//Refresh data
                            }
                        });
            }
        });
        */
    }

    public void getMoyenne(){
        int i=0;
        Cursor data = db.GetAllMatches();
        if(data.getCount()==0){
            Toast.makeText(this, "Pas de données", Toast.LENGTH_SHORT).show();
        }
        else{
            StringBuffer buffer = new StringBuffer();
            while(data.moveToNext()){
                i++;
                dMoy += Integer.valueOf(data.getString(10));
                fMoy += Integer.valueOf(data.getString(5)) + Integer.valueOf(data.getString(6));
                lMoy += Integer.valueOf(data.getString(3)) + Integer.valueOf(data.getString(4));
            }
        }
        dureeMoyenne.setText(String.valueOf(dMoy/i));
        fauteMoyenne.setText(String.valueOf(fMoy/i));
        letMoyenne.setText(String.valueOf(lMoy/i));
    }
}


    /*
    private void loadData() {
        Disposable disposable = matchRepository.getAllMatches()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Match>>() {
                    @Override
                    public void accept(List<Match> matches) throws Exception {
                        onGetAllMatchSuccess(matches);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(Stat.this, ""+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void onGetAllMatchSuccess(List<Match> matches) {
        matchList.clear();
        matchList.addAll(matches);
        adapter.notifyDataSetChanged();
    }
    */

