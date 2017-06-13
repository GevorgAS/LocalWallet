package ipc.gev.localwallet.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RadioButton;

import java.util.Locale;

import ipc.gev.localwallet.R;

public class LanguagesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);
    }
    private void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf,dm);
        Intent refresh = new Intent(this,MainActivity.class);
        startActivity(refresh);
    }

    public void save(View view) {
        RadioButton rb = (RadioButton) findViewById(R.id.en);
        if (rb.isChecked()){
            setLocale("en");
        }else{
            setLocale("hy");
        }
    }
}
