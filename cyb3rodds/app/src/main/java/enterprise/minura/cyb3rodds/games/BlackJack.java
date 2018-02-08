package enterprise.minura.cyb3rodds.games;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import enterprise.minura.cyb3rodds.MainActivity;
import enterprise.minura.cyb3rodds.R;

public class BlackJack extends AppCompatActivity {
    ImageView p1, p2, p3, d1, d2, d3;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Popup Window
        AlertDialog.Builder alert= new AlertDialog.Builder(this);
        alert.setMessage("Welcome to the Black Jack! Where you can bet you cyb3r coins in order to win more! Have fun!").setPositiveButton("Continue to Black Jack", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setTitle("Black Jack").create();

        alert.show();

        btn= (Button) findViewById(R.id.btn);
        p1= (ImageView) findViewById(R.id.player1);
        p2= (ImageView) findViewById(R.id.player2);
        p3= (ImageView) findViewById(R.id.player3);
        d1= (ImageView) findViewById(R.id.dealer1);
        d2= (ImageView) findViewById(R.id.dealer2);
        d3= (ImageView) findViewById(R.id.dealer3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p1.setImageResource(R.drawable.deckback);
                p2.setImageResource(R.drawable.deckback);
                p3.setImageResource(R.drawable.deckback);

                d1.setImageResource(R.drawable.deckback);
                d2.setImageResource(R.drawable.deckback);
                d3.setImageResource(R.drawable.deckback);



            }
        });
    }

    public void Back(View v){
        Intent it= new Intent(this, MainActivity.class);
        startActivity(it);
    }

}
