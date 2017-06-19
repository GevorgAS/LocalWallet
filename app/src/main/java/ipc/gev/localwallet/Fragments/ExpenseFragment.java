package ipc.gev.localwallet.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Calendar;

import ipc.gev.localwallet.R;
import ipc.gev.localwallet.db.DB;
import ipc.gev.localwallet.db.entity.Trade;


public class ExpenseFragment extends Fragment implements View.OnClickListener{
    int year;
    int month;
    int day;
    RelativeLayout layout;
    Button save;
    DB db;
    EditText markups_et;
    EditText location_et;
    EditText price_et;
    EditText date_et;
    Calendar calendar = Calendar.getInstance();
    int c_year = calendar.get(Calendar.YEAR);
    int c_month = calendar.get(Calendar.MONTH);
    int c_day = calendar.get(Calendar.DAY_OF_MONTH);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.expense_fragment,container,false);
        save = (Button) view.findViewById(R.id.save_expanse);
        save.setOnClickListener(this);
        layout = (RelativeLayout) view.findViewById(R.id.main_expense_layout);
        db = DB.getInstance(getContext());

        markups_et = (EditText) view.findViewById(R.id.expense_markups);
        location_et = (EditText) view.findViewById(R.id.expense_location);
        price_et = (EditText) view.findViewById(R.id.expense_price);
        date_et = (EditText) view.findViewById(R.id.expense_date);
        String mm = (c_month+1)<10?"0" + (c_month+1):""+(c_month+1);
        date_et.setText(new StringBuilder().append(c_day)
                .append("/").append(mm).append("/").append(c_year)
                .append(" "));
        date_et.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.save_expanse:
                if (validate()) {
                    String markups = markups_et.getText().toString();
                    String location = location_et.getText().toString();
                    long price = Long.parseLong(price_et.getText().toString());
                    String date = date_et.getText().toString();
                    Trade trade = new Trade(markups, location, price, date, Trade.EXPENSE);
                    db.createTrade(trade);
                    Snackbar snackbar = Snackbar
                            .make(layout, R.string.add_expense, Snackbar.LENGTH_LONG);
                    snackbar.show();
                    markups_et.setText("");
                    location_et.setText("");
                    price_et.setText("");

                }
                break;

            case R.id.expense_date:

                new DatePickerDialog(getContext(),R.style.DialogTheme,datePickerListener, c_year, c_month, c_day).show();
                break;

        }
    }
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
        }else{
            long price = Long.parseLong(price_et.getText().toString());
            if (price > 999999999){
                isCorrect=false;
                Snackbar snackbar = Snackbar
                        .make(layout, R.string.errorPrice, Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        if (date_et.getText().toString().equals("")){
            date_et.setError(getString(R.string.required));
            isCorrect = false;
        }
        return isCorrect;
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
}


