package enterprise.minura.cyb3rodds.games;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.eunainter.r2std2oid.RequestR2D2;
import net.eunainter.r2std2oid.ResponseR2D2;
import net.eunainter.r2std2oid.RestObserver;
import net.eunainter.r2std2oid.Skyrunner;

import java.util.Random;

import enterprise.minura.cyb3rodds.config.Config;
import enterprise.minura.cyb3rodds.model.User;
import enterprise.minura.cyb3rodds.MainActivity;
import enterprise.minura.cyb3rodds.R;

public class SlotMachine extends AppCompatActivity implements RestObserver{
    ImageView img1,img2,img3,result;
    int imag1,imag2,imag3;
    Random rand1, rand2, rand3;
    Button spin;
    TextView mon;
    boolean icon;
    int money;
    int hit=0, hit2=0, hit3=0;
    Skyrunner mSky;
    LinearLayout slotbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSky= new Skyrunner(20);
        mSky.addObserver(this);


        //Popup Window
        AlertDialog.Builder alert= new AlertDialog.Builder(this);
        alert.setMessage("Welcome to the Slots! Where you can bet you cyb3r coins in order to win more! Have fun!").setPositiveButton("Continue to Slot Machine", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setTitle("Slot Machine").create();

            alert.show();

            img1= (ImageView) findViewById(R.id.img1);
            img2 = (ImageView) findViewById(R.id.img2);
            img3 = (ImageView) findViewById(R.id.img3);
            result= (ImageView) findViewById(R.id.result);
            spin = (Button) findViewById(R.id.spin);
            slotbtn= (LinearLayout) findViewById(R.id.slotbtn);
            mon= (TextView) findViewById(R.id.money);
            final User gb= (User) getApplicationContext();
            //Wallet Money------
            money= gb.getCyber();
            mon.setText(String.valueOf(money));
            icon=gb.isRooled();

            rand1 = new Random();
            rand2= new Random();
            rand3= new Random();


            spin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    result.setImageResource(0);

                    if (money<200) {
                        Toast.makeText(SlotMachine.this, "You don't have enough money, please recharge your account!", Toast.LENGTH_SHORT).show();
                    } else {
                        money = money - 200;
                        mon.setText(String.valueOf(money));
                        slotbtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.cyb3rslotcontrolon));

                        img1.setImageResource(R.drawable.animation1);
                        final AnimationDrawable imganim1 = (AnimationDrawable) img1.getDrawable();
                        imganim1.start();

                        img2.setImageResource(R.drawable.animation2);
                        final AnimationDrawable imganim2 = (AnimationDrawable) img2.getDrawable();
                        imganim2.start();

                        img3.setImageResource(R.drawable.animation3);
                        final AnimationDrawable imganim3 = (AnimationDrawable) img3.getDrawable();
                        imganim3.start();


                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                imganim1.stop();
                                imganim2.stop();
                                imganim3.stop();

                                setImage();
                                getResult();
                                slotbtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.cyb3rslotcontroloff));

                            }
                        }, 1000);


                    }
                }
            });

        }
    public void setImage(){
        final User gb= (User) getApplicationContext();
        imag1= rand1.nextInt(9)+1;
        imag2= rand2.nextInt(9)+1;
        imag3= rand3.nextInt(9)+1;
        gb.setRooled(false);

        switch (imag1){
            case 1:
                img1.setImageResource(R.drawable.img1);
                gb.setRooled(true);
                hit=1;
                break;
            case 2:
                img1.setImageResource(R.drawable.img2);
                hit=0;
                break;
            case 3:
                img1.setImageResource(R.drawable.img3);
                hit=0;
                break;
            case 4:
                img1.setImageResource(R.drawable.img4);
                hit=0;
                break;
            case 5:
                img1.setImageResource(R.drawable.img5);
                hit=0;
                break;
            case 6:
                img1.setImageResource(R.drawable.img6);
                gb.setRooled(true);
                hit=1;
                break;
            case 7:
                img1.setImageResource(R.drawable.img7);
                hit=0;
                break;
            case 8:
                img1.setImageResource(R.drawable.img8);
                gb.setRooled(true);
                hit=1;
                break;
            case 9:
                img1.setImageResource(R.drawable.img9);
                hit=0;
                break;
        }
        switch (imag2){
            case 1:
                img2.setImageResource(R.drawable.img1);
                gb.setRooled(true);
                hit2=1;
                break;
            case 2:
                img2.setImageResource(R.drawable.img2);
                hit2=0;
                break;
            case 3:
                img2.setImageResource(R.drawable.img3);
                hit2=0;
                break;
            case 4:
                img2.setImageResource(R.drawable.img4);
                hit2=0;
                break;
            case 5:
                img2.setImageResource(R.drawable.img5);
                hit2=0;
                break;
            case 6:
                img2.setImageResource(R.drawable.img6);
                gb.setRooled(true);
                hit2=1;
                break;
            case 7:
                img2.setImageResource(R.drawable.img7);
                hit2=0;
                break;
            case 8:
                img2.setImageResource(R.drawable.img8);
                gb.setRooled(true);
                hit2=1;
                break;
            case 9:
                img2.setImageResource(R.drawable.img9);
                hit2=0;
                break;
        }
        switch (imag3){
            case 1:
                img3.setImageResource(R.drawable.img1);
                gb.setRooled(true);
                hit3=1;
                break;
            case 2:
                img3.setImageResource(R.drawable.img2);
                hit3=0;
                break;
            case 3:
                img3.setImageResource(R.drawable.img3);
                hit3=0;
                break;
            case 4:
                img3.setImageResource(R.drawable.img4);
                hit3=0;
                break;
            case 5:
                img3.setImageResource(R.drawable.img5);
                hit3=0;
                break;
            case 6:
                img3.setImageResource(R.drawable.img6);
                gb.setRooled(true);
                hit3=1;
                break;
            case 7:
                img3.setImageResource(R.drawable.img7);
                hit3=0;
                break;
            case 8:
                img3.setImageResource(R.drawable.img8);
                gb.setRooled(true);
                hit3=1;
                break;
            case 9:
                img3.setImageResource(R.drawable.img9);
                hit3=0;
                break;
        }

    }

    public  void getResult(){
        final User gb= (User) getApplicationContext();

        icon=gb.isRooled();
        Log.i("hit",String.valueOf(hit));
        Log.i("hit",String.valueOf(hit2));
        Log.i("hit",String.valueOf(hit3));

        if(hit==1 && hit2==1 || hit==1 && hit3==1 || hit2==1 && hit3==1){
            if(imag1== imag2 && imag2==imag3 && imag1== imag3){
                result.setImageResource(R.drawable.jackpotmega);
                money=money+40000;
            }
            else if(imag1== imag2 || imag2==imag3 || imag1== imag3){
                result.setImageResource(R.drawable.doublee);
                money=money+600;
            }
        }
        else{
            if(imag1== imag2 && imag2==imag3 && imag1== imag3) {
                result.setImageResource(R.drawable.jackpot);
                money=money+20000;
            } else if(imag1== imag2 || imag2==imag3 || imag1== imag3){
                result.setImageResource(R.drawable.doublenormal);
                money=money+400;
            }

        }
        mon.setText(String.valueOf(money));
        gb.setCyber(money);

        RequestR2D2 req= new RequestR2D2(Config.WSUPDATEWALLET,null,RequestR2D2.GET);
        req.addParValue("id_utilizador",gb.getId());
        req.addParValue("cyb3rmoney",gb.getCyber());
        req.addParValue("realmoney",gb.getMoney());

        mSky.sendRequest(req, Skyrunner.RequestTag.KPOSONE);
        hit=0;

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


