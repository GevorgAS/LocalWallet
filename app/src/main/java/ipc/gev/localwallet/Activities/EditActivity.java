package ipc.gev.localwallet.Activities;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import ipc.gev.localwallet.R;
import ipc.gev.localwallet.db.DB;

public class EditActivity extends AppCompatActivity {
    EditText markups_et;
    EditText location_et;
    EditText price_et;
    EditText date_et;
    int year,month,day;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        markups_et = (EditText) findViewById(R.id.edit_markups);
        location_et = (EditText) findViewById(R.id.edit_location);
        price_et = (EditText) findViewById(R.id.edit_price);
        date_et = (EditText) findViewById(R.id.edit_date);
        db = DB.getInstance(this);
    }
    public void saveEdits(View view) {
        if (validate()){

        }

    }
    public void editDate(View view) {
        Calendar calendar = Calendar.getInstance();
        int c_year = calendar.get(Calendar.YEAR);
        int c_month = calendar.get(Calendar.MONTH);
        int c_day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, datePickerListener,c_year, c_month,c_day).show();
    }


    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int c_year,
                              int c_month, int c_day) {

            year = c_year;
            month = c_month;
            day = c_day;

            String mm = (month+1)<10?"0" + (month+1):""+(month+1);
            date_et.setText(new StringBuilder().append(day)
                    .append("/").append(mm).append("/").append(year)
                    .append(" "));

        }
    };
    private boolean validate(){
        boolean isCorrect = true;
        if (markups_et.getText().toString().equals("")){
            markups_et.setError(getString(R.string.required));
            isCorrect = false;
        }
        if (location_et.getText().toString().equals("")){
            location_et.setError(getString(R.string.required));
            isCorrect = false;
        }
        if (price_et.getText().toString().equals("")){
            price_et.setError(getString(R.string.required));
            isCorrect = false;
        }
        if (date_et.getText().toString().equals("")){
            date_et.setError(getString(R.string.required));
            isCorrect = false;
        }
        return isCorrect;
    }
}
