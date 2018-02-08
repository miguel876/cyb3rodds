package enterprise.minura.cyb3rodds.games;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.eunainter.r2std2oid.RequestR2D2;
import net.eunainter.r2std2oid.ResponseR2D2;
import net.eunainter.r2std2oid.RestObserver;
import net.eunainter.r2std2oid.Skyrunner;

import java.util.Random;

import enterprise.minura.cyb3rodds.MainActivity;
import enterprise.minura.cyb3rodds.R;
import enterprise.minura.cyb3rodds.config.Config;
import enterprise.minura.cyb3rodds.model.User;

public class DicesGameActivity extends AppCompatActivity implements RestObserver{
    ImageView d1,d2;
    TextView cyb3r;
    EditText bet;
    ImageView btnmaior, btnmenor;
    Random rand1, rand2;
    int imag1, imag2, money;
    boolean maior;
    boolean menor;
    int result1=0,result2=0, resulttotal;
    ImageView result;
    Spinner sp;
    ArrayAdapter<CharSequence> adapters;
    Skyrunner mSky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dices_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSky= new Skyrunner(20);
        mSky.addObserver(this);


        //Popup Window
        AlertDialog.Builder alert= new AlertDialog.Builder(this);
        alert.setMessage("Welcome to the Dices! Where you can bet you cyb3r coins in order to win more! Have fun!").setPositiveButton("Continue to Dices", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setTitle("Dices").create();

        alert.show();

        d1= (ImageView) findViewById(R.id.dice1);
        d2= (ImageView) findViewById(R.id.dice2);
        cyb3r= (TextView) findViewById(R.id.cyber);
        btnmaior= (ImageView) findViewById(R.id.betmaior);
        btnmenor= (ImageView) findViewById(R.id.betmenor);
        result= (ImageView) findViewById(R.id.result);
        sp=(Spinner) findViewById(R.id.spinner);
        final User gb= (User) getApplicationContext();

        money= gb.getCyber();
        cyb3r.setText(String.valueOf(money));

        //Adapter Sex--------------
        adapters= ArrayAdapter.createFromResource(this, R.array.dices, R.layout.textspinner2);
        adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapters);

        rand1= new Random();
        rand2= new Random();

        btnmenor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                result.setImageResource(0);

                String selected= sp.getSelectedItem().toString();
                int value=Integer.parseInt(selected);

                menor=true;
                if(value > money){
                    Toast.makeText(DicesGameActivity.this, "You don't have enough money, please recharge your account!", Toast.LENGTH_SHORT).show();
                } else {
                    money = money-value;
                    cyb3r.setText(String.valueOf(money));
                    btnmenor.setBackgroundDrawable(getResources().getDrawable(R.drawable.btson));

                    d1.setImageResource(R.drawable.animationdice1);
                    final AnimationDrawable img1 = (AnimationDrawable) d1.getDrawable();
                    img1.start();

                    d2.setImageResource(R.drawable.animationdice2);
                    final AnimationDrawable img2 = (AnimationDrawable) d2.getDrawable();
                    img2.start();




                    android.os.Handler handler = new android.os.Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            img1.stop();
                            img2.stop();

                            setImage();
                            getResult();
                            btnmenor.setBackgroundDrawable(getResources().getDrawable(R.drawable.btsoff));

                        }
                    }, 1000);


                }
            }
        });

        btnmaior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                result.setImageResource(0);
                String selected= sp.getSelectedItem().toString();
                int value=Integer.parseInt(selected);

                maior=true;
                if(value > money){
                    Toast.makeText(DicesGameActivity.this, "You don't have enough money, please recharge your account!", Toast.LENGTH_SHORT).show();
                } else {
                    money = money-value;
                    cyb3r.setText(String.valueOf(money));
                    btnmaior.setBackgroundDrawable(getResources().getDrawable(R.drawable.btbon));

                    d1.setImageResource(R.drawable.animationdice1);
                    final AnimationDrawable img1 = (AnimationDrawable) d1.getDrawable();
                    img1.start();

                    d2.setImageResource(R.drawable.animationdice2);
                    final AnimationDrawable img2 = (AnimationDrawable) d2.getDrawable();
                    img2.start();




                    android.os.Handler handler = new android.os.Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            img1.stop();
                            img2.stop();

                            setImage();
                            getResult();
                            btnmaior.setBackgroundDrawable(getResources().getDrawable(R.drawable.btboff));


                        }
                    }, 1000);

                }
            }
        });
    }



    public void setImage() {
        imag1 = rand1.nextInt(6) + 1;
        imag2 = rand2.nextInt(6) + 1;

        switch (imag1) {
            case 1:
                d1.setImageResource(R.drawable.diceone);
                result1=1;
                break;
            case 2:
                d1.setImageResource(R.drawable.dicetwo);
                result1=2;
                break;
            case 3:
                d1.setImageResource(R.drawable.dicethree);
                result1=3;
                break;
            case 4:
                d1.setImageResource(R.drawable.dicefour);
                result1=4;
                break;
            case 5:
                d1.setImageResource(R.drawable.dicefive);
                result1=5;
                break;
            case 6:
                d1.setImageResource(R.drawable.dicesix);
                result1=6;
                break;

        }
        switch (imag2) {
            case 1:
                d2.setImageResource(R.drawable.diceone);
                result2=1;
                break;
            case 2:
                d2.setImageResource(R.drawable.dicetwo);
                result2=2;
                break;
            case 3:
                d2.setImageResource(R.drawable.dicethree);
                result2=3;
                break;
            case 4:
                d2.setImageResource(R.drawable.dicefour);
                result2=4;
                break;
            case 5:
                d2.setImageResource(R.drawable.dicefive);
                result2=5;
                break;
            case 6:
                d2.setImageResource(R.drawable.dicesix);
                result2=6;
                break;

        }
    }

    public  void getResult(){
        final User gb= (User) getApplicationContext();
        String item= sp.getSelectedItem().toString();
        int value= Integer.parseInt(item);

        resulttotal=result1+result2;
        if(maior){
            if(resulttotal>1 && resulttotal<=6){


            }
            else{

                result.setImageResource(R.drawable.win);
                money=money+(value*2);
            }

        }else{
            if(resulttotal>1 && resulttotal<=6){
                result.setImageResource(R.drawable.win);
                money=money+(value*2);
            }
            else{

            }
        }
        menor=false;
        maior=false;
        cyb3r.setText(String.valueOf(money));
        gb.setCyber(money);

        RequestR2D2 req= new RequestR2D2(Config.WSUPDATEWALLET,null,RequestR2D2.GET);
        req.addParValue("id_utilizador",gb.getId());
        req.addParValue("cyb3rmoney",gb.getCyber());
        req.addParValue("realmoney",gb.getMoney());

        mSky.sendRequest(req, Skyrunner.RequestTag.KPOSONE);


    }
    public void Back(View v){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }

    @Override
    public void receivedResponse(ResponseR2D2 response) {

    }

    @Override
    public void startConnecting() {

    }

    @Override
    public void endConnecting() {

    }

    @Override
    public void requestTimeout() {

    }
}


