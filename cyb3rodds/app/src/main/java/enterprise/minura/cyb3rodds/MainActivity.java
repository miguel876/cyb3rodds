package enterprise.minura.cyb3rodds;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import net.eunainter.r2std2oid.RequestR2D2;
import net.eunainter.r2std2oid.ResponseR2D2;
import net.eunainter.r2std2oid.RestObserver;
import net.eunainter.r2std2oid.Skyrunner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import enterprise.minura.cyb3rodds.config.Config;
import enterprise.minura.cyb3rodds.games.DicesGameActivity;
import enterprise.minura.cyb3rodds.games.SlotMachine;
import enterprise.minura.cyb3rodds.model.User;
import enterprise.minura.cyb3rodds.profile.ProfileFrag;
import enterprise.minura.cyb3rodds.settings.SettingsFrag;
import enterprise.minura.cyb3rodds.wallet.WalletFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ProfileFrag.OnFragmentInteractionListener, SettingsFrag.OnFragmentInteractionListener, RestObserver {

    FragmentManager fm;
    FragmentTransaction ft;
    Skyrunner mSky;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        mSky= new Skyrunner(20);
        mSky.addObserver(this);

        final User gb= (User) getApplicationContext();

        RequestR2D2 req= new RequestR2D2(Config.WSREADWALLET,null,RequestR2D2.GET);
        req.addParValue("id_utilizador",gb.getId());


        mSky.sendRequest(req, Skyrunner.RequestTag.KPOSONE);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Popup Window
        boolean dia= gb.isDialog();

        if(dia==false) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Welcome to Cyb3rOdds, a free and fun way to bet on sports and casino games. Feel free to play our games. For more information please visit our website! Have fun!").setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).setTitle("Cyb3rOdds").create();

            alert.show();
        }
        else{
            return;
        }
        gb.setDialog(true);
    }

    @Override
    public void receivedResponse(ResponseR2D2 response) {
        final User gb= (User) getApplicationContext();


        if(response.getId() == Skyrunner.RequestTag.KPOSONE) {
            JSONObject jobj = null;

            try {

                JSONArray jar = response.getJSONArray();

                for (int i = 0; i < jar.length(); i++) {
                    jobj = jar.getJSONObject(i);

                    if (!jobj.equals(null)) {

                        gb.setCyber(jobj.getInt("cyb3rmoney"));
                        gb.setMoney(jobj.getInt("realmoney"));




                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        fm = getSupportFragmentManager();
        ft=fm.beginTransaction();

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.settings) {
            ft.replace(R.id.framechange, new SettingsFrag());
            ft.commit();

        }else if(id==R.id.profile){
            ft.replace(R.id.framechange, new ProfileFrag());
            ft.commit();
        }
        else if(id==R.id.logout){
            Intent it= new Intent(this, LoginActivity.class);
            startActivity(it);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        fm = getSupportFragmentManager();
        ft=fm.beginTransaction();

        int id = item.getItemId();

         if (id == R.id.nav_slot) {
            Intent it= new Intent(this, SlotMachine.class);
            startActivity(it);

        }else if (id == R.id.nav_guess) {
            Intent it= new Intent(this, DicesGameActivity.class);
            startActivity(it);

        }else if (id == R.id.nav_wallet) {
            ft.replace(R.id.framechange, new WalletFragment());
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
