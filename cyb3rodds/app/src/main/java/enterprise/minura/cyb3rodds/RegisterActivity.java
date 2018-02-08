package enterprise.minura.cyb3rodds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.eunainter.r2std2oid.RequestR2D2;
import net.eunainter.r2std2oid.ResponseR2D2;
import net.eunainter.r2std2oid.RestObserver;
import net.eunainter.r2std2oid.Skyrunner;

import org.json.JSONException;

import java.util.Calendar;
import java.util.Date;

import enterprise.minura.cyb3rodds.config.Config;

public class RegisterActivity extends AppCompatActivity implements RestObserver {
    RadioGroup rg;
    RadioButton rb;
    Spinner sp,sp2,sp3;
    ArrayAdapter<CharSequence> adapterq;
    ArrayAdapter<CharSequence> adapterc;
    ArrayAdapter<CharSequence> adapters;
    Skyrunner mSky;
    String err="";

    TextView username, email, reemail, password, repassword,  answer, firstname, lastname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        sp = (Spinner) findViewById(R.id.spinquestion);
        sp2=(Spinner) findViewById(R.id.spincountry);
        sp3= (Spinner) findViewById(R.id.spinsex);

        //Adapter Question-------------
        adapterq= ArrayAdapter.createFromResource(this, R.array.question_array, R.layout.textspinner);
        adapterq.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapterq);

        //Adapter Country--------------
        adapterc= ArrayAdapter.createFromResource(this, R.array.countries_array, R.layout.textspinner);
        adapterc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapterc);

        //Adapter Sex--------------
        adapters= ArrayAdapter.createFromResource(this, R.array.sex, R.layout.textspinner);
        adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3.setAdapter(adapters);


        username= (TextView) findViewById(R.id.username);
        email= (TextView) findViewById(R.id.email);
        reemail= (TextView) findViewById(R.id.reemail);
        password= (TextView) findViewById(R.id.password);
        repassword= (TextView) findViewById(R.id.repassword);
        answer= (TextView) findViewById(R.id.Answer);
        firstname= (TextView) findViewById(R.id.first);
        lastname= (TextView) findViewById(R.id.last);

        mSky= new Skyrunner(20);
        mSky.addObserver(this);



    }

    public void Register(View v) {

        String user= username.getText().toString();
        String mail= email.getText().toString();
        String rmail= reemail.getText().toString();
        String pass= password.getText().toString();
        String rpass= repassword.getText().toString();;
        String ans= answer.getText().toString();
        String first= firstname.getText().toString();
        String last= lastname.getText().toString();
        Date time= Calendar.getInstance().getTime();
        String country= sp2.getSelectedItem().toString();
        String question= sp.getSelectedItem().toString();
        String sex= sp3.getSelectedItem().toString();


        if (user.equals("") || user.equals(" ")){
            Toast.makeText(this,"Insert a Valid Username!",Toast.LENGTH_SHORT).show();
        }
        else if(mail.equals("") || mail.equals(" ")){
            Toast.makeText(this,"Insert an E-mail!",Toast.LENGTH_SHORT).show();
        }
        else if(rmail.equals("") || rmail.equals(" ")){
            Toast.makeText(this,"Insert a Repeat E-mail!",Toast.LENGTH_SHORT).show();

        }
        else if(!mail.contains("@")){
            Toast.makeText(this,"Insert a Valid E-mail!",Toast.LENGTH_SHORT).show();

        }
        else if(!mail.contains(".")){
            Toast.makeText(this,"Insert a Valid E-mail!",Toast.LENGTH_SHORT).show();

        }
        else if(pass.equals("") || pass.equals(" ")){
            Toast.makeText(this,"Insert a Valid Password!",Toast.LENGTH_SHORT).show();

        }
        else if(rpass.equals("") || rpass.equals(" ")){
            Toast.makeText(this,"Insert a Valid Repeat Password!",Toast.LENGTH_SHORT).show();

        }
        else if(first.equals("")|| first.equals(" ")){
            Toast.makeText(this,"Insert a Firstname!!",Toast.LENGTH_SHORT).show();

        }
        else if(last.equals("")|| last.equals(" ")){
            Toast.makeText(this,"Insert a Lastname!!",Toast.LENGTH_SHORT).show();

        }
        else if(ans.equals("") || ans.equals(" ")) {
            Toast.makeText(this,"Insert a Valid Answer!",Toast.LENGTH_SHORT).show();

        }
        else if(!pass.equals(rpass)){
            Toast.makeText(this,"The Passwords don't Match!",Toast.LENGTH_SHORT).show();

        }
        else if(!mail.equals(rmail)){
            Toast.makeText(this,"The E-mails don't Match!",Toast.LENGTH_SHORT).show();

        }

        else{

            RequestR2D2 req= new RequestR2D2(Config.WSREGISTER,null,RequestR2D2.GET);
            req.addParValue("username",user);
            req.addParValue("password",pass);
            req.addParValue("firstname", first);
            req.addParValue("lastname", last);
            req.addParValue("email", mail);
            req.addParValue("date_created",time.toString());
            req.addParValue("sex",sex);
            req.addParValue("country",country);
            req.addParValue("answer",ans);
            req.addParValue("question",question);

            mSky.sendRequest(req, Skyrunner.RequestTag.KPOSONE);




        }



        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });



    }
    @Override
    public void receivedResponse(ResponseR2D2 response) {
        try {
            err = response.getJSONObj().getString("errcode");
        } catch (JSONException e) {

        }

        Toast.makeText(this, err , Toast.LENGTH_SHORT).show();
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
