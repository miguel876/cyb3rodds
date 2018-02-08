package enterprise.minura.cyb3rodds;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.os.SystemClock;
import android.widget.TextView;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        ImageView logo= (ImageView) findViewById(R.id.logo);
        TextView click=(TextView) findViewById(R.id.click);

        ObjectAnimator objanim= ObjectAnimator.ofFloat(logo,"alpha",0,1);
        objanim.setDuration(1000);
        objanim.start();

        click.setText("Click to enter!");

    }

    public void Change(View v) {
        Intent it = new Intent(this, LoginActivity.class);
    startActivity(it);
    }


}
