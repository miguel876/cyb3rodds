package enterprise.minura.cyb3rodds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import net.eunainter.r2std2oid.RequestR2D2;
import net.eunainter.r2std2oid.ResponseR2D2;
import net.eunainter.r2std2oid.RestObserver;
import net.eunainter.r2std2oid.Skyrunner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import enterprise.minura.cyb3rodds.config.Config;
import enterprise.minura.cyb3rodds.model.User;


public class LoginActivity extends AppCompatActivity implements RestObserver {
    EditText user, pass;
    Skyrunner mSky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user= (EditText) findViewById(R.id.username);
        pass= (EditText) findViewById(R.id.password);

        mSky= new Skyrunner(20);
        mSky.addObserver(this);

    }

    public void Login(View v){

       String username= user.getText().toString();
        String password= pass.getText().toString();

        if(username.equals("") || username.equals(" ")){
            Toast.makeText(this,"Insert a Valid Username!", Toast.LENGTH_SHORT).show();
        }
        else if(password.equals("") || password.equals(" ")){
            Toast.makeText(this,"Insert a Valid Password!", Toast.LENGTH_SHORT).show();

        }


        RequestR2D2 req= new RequestR2D2(Config.WSREAD,null,RequestR2D2.GET);
        req.addParValue("username",username);
        req.addParValue("password",password);

        mSky.sendRequest(req, Skyrunner.RequestTag.KPOSONE);





    }
    @Override
    public void receivedResponse(ResponseR2D2 response) {
        final User gb= (User) getApplicationContext();


        if(response.getId() == Skyrunner.RequestTag.KPOSONE)
        {
            JSONObject jobj = null;

                try {

                    JSONArray jar= response.getJSONArray();
                    if(jar==null){
                        Toast.makeText(this, "Wrong Data!", Toast.LENGTH_SHORT).show();
                    }
                 for(int i=0; i< jar.length(); i++){
                   jobj=jar.getJSONObject(i);

                     if(!jobj.equals(null)){
                        gb.setId(jobj.getInt("id"));
                        gb.setDeleted(jobj.getInt("isDeleted"));
                        gb.setBanned(jobj.getInt("banned"));
                        gb.setUsername(jobj.getString("username"));
                        gb.setFirstname(jobj.getString("firstname"));
                        gb.setLastname(jobj.getString("lastname"));
                        gb.setEmail(jobj.getString("email"));
                        gb.setSex(jobj.getString("sex"));
                        gb.setCountry(jobj.getString("country"));
                        gb.setQuestion(jobj.getString("question"));
                        gb.setAnswer(jobj.getString("answer"));
                        gb.setChargedtrade(false);
                        gb.setCharged(false);
                        gb.setDialog(false);


                         if (gb.getDeleted() == 1) {
                            Toast.makeText(this, "Your account seems to be deleted! For more information please visit our website. Thank you!", Toast.LENGTH_SHORT).show();

                        } else if (gb.getBanned() == 1) {
                            Toast.makeText(this, "Your account seems to be banned! For more information please visit our website. Thank you!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Intent it = new Intent(this, MainActivity.class);
                            startActivity(it);
                            Toast.makeText(this, "Welcome " + gb.getFirstname() + "!", Toast.LENGTH_SHORT).show();

                            }

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

    public void Register(View v) {
        Intent it= new Intent(this,RegisterActivity.class);
        startActivity(it);

    }


}
