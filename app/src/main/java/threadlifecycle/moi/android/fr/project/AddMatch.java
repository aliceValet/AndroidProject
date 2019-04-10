package threadlifecycle.moi.android.fr.project;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddMatch extends AppCompatActivity {

    SQLiteDatabase db;

    EditText name1, name2, latitude, longitude, duree, type, classement;
    TextView player1Let, player2Let, player1Fault, player2Fault;
    Button player1AddLet, player2AddLet, player1AddFault, player2AddFault, btnSave, btnData, btnDelete;

    int p1Let = 0, p2Let = 0, p1Fault = 0, p2Fault = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_match);

        db = new SQLiteDatabase(this);

        name1 = findViewById(R.id.name1);
        name2 = findViewById(R.id.name2);
        latitude = findViewById(R.id.lat);
        longitude = findViewById(R.id.longi);
        duree = findViewById(R.id.duree);
        type = findViewById(R.id.type);
        classement = findViewById(R.id.classement);

        player1Let = findViewById(R.id.let_player1_number);
        player2Let = findViewById(R.id.let_player2_number);
        player1Fault = findViewById(R.id.fault_player1_number);
        player2Fault = findViewById(R.id.fault_player2_number);

        player1AddLet = findViewById(R.id.add_let_player1);
        player2AddLet = findViewById(R.id.add_let_player2);
        player1AddFault = findViewById(R.id.add_fault_player1);
        player2AddFault = findViewById(R.id.add_fault_player2);
        btnSave = findViewById(R.id.btn_save);
        btnData = findViewById(R.id.btn_data);
        btnDelete = findViewById(R.id.btn_delete);

        addMatch();
        ViewData();
        Init();

        player1AddLet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1Let++;
                String inter = String.valueOf(p1Let);
                player1Let.setText(inter);
            }
        });

        player2AddLet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p2Let++;
                player2Let.setText(String.valueOf(p2Let));
            }
        });

        player1AddFault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1Fault++;
                player1Fault.setText(String.valueOf(p1Fault));
            }
        });

        player2Fault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p2Fault++;
                player2Fault.setText(String.valueOf(p2Fault));
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.DeleteAllMatches();
            }
        });
    }

    public void Init(){
        player1Let.setText(String.valueOf(p1Let));
        player2Let.setText(String.valueOf(p2Let));
        player1Fault.setText(String.valueOf(p1Fault));
        player2Fault.setText(String.valueOf(p2Fault));
    }

    public void addMatch(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = db.InsertNewMatch(name1.getText().toString(),
                        name2.getText().toString(), Integer.parseInt((String) player1Let.getText()),
                        Integer.parseInt((String) player2Let.getText()),
                        Integer.parseInt((String) player1Fault.getText()),
                        Integer.parseInt((String) player2Fault.getText()),
                        Integer.parseInt(latitude.getText().toString()),
                        Integer.parseInt(longitude.getText().toString()), type.getText().toString(),
                        duree.getText().toString(), classement.getText().toString());
                if(isInserted){
                    Toast.makeText(AddMatch.this, "Data is inserted sucessfully", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(AddMatch.this, "Data not inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void ViewData(){
        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = db.GetAllMatches();
                if(data.getCount()==0){
                    showData("Data", "No data found");
                }
                else{
                    StringBuffer buffer = new StringBuffer();
                    while(data.moveToNext()){
                        buffer.append("ID : " + data.getString(0)+ "\n ");
                        buffer.append("PLAYER1 : " + data.getString(1)+ "\n ");
                        buffer.append("PLAYER2 : " + data.getString(2)+ "\n ");
                        buffer.append("LET1 : " + data.getString(3)+ "\n ");
                        buffer.append("LET2 : " + data.getString(4)+ "\n ");
                        buffer.append("FAULT1 : " + data.getString(5)+ "\n ");
                        buffer.append("FAULT2 : " + data.getString(6)+ "\n ");
                        buffer.append("LATITUDE : " + data.getString(7)+ "\n ");
                        buffer.append("LONGITUDE : " + data.getString(8)+ "\n ");
                        buffer.append("TYPE : " + data.getString(9)+ "\n ");
                        buffer.append("DUREE : " + data.getString(10)+ "\n ");
                        buffer.append("CLASSEMENT : " + data.getString(11)+ "\n ");
                    }
                    showData("Data", buffer.toString());
                }
            }
        });
    }

    public void showData(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
