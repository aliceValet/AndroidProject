package threadlifecycle.moi.android.fr.project;

import android.arch.persistence.room.Database;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

    private ListView lstMatches;
    private FloatingActionButton fab;

    //Adapter
    List<Match> matchList = new ArrayList<>();
    ArrayAdapter adapter;

    //Database
    private CompositeDisposable compositeDisposable;
    private MatchRepository matchRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        //Actionbar
        setSupportActionBar((Toolbar) findViewById(R.id.home_toolbar));
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
                        .observeOn(AndroidSchedulers.mainThread())
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
    }

    private void loadData() {
    }
}
