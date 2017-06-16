package ipc.gev.localwallet.Fragments;

import android.app.DatePickerDialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;

import java.util.Calendar;

import ipc.gev.localwallet.Activities.ResultActivity;
import ipc.gev.localwallet.R;


public class SearchFragment extends Fragment implements View.OnClickListener {

    int year;
    int month;
    int day;
    Button searchDate, searchLocation, searchMarkups, search_bt;
    EditText searchByDate, searchByLocation, searchByMarkups;

    boolean button_click = false;
    boolean location_clicked = false;
    boolean markups_clicked = false;

    Switch switch_trade;


    boolean date_set = true;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        searchDate = (Button) view.findViewById(R.id.search_date);

        searchLocation = (Button) view.findViewById(R.id.search_location);
        searchMarkups = (Button) view.findViewById(R.id.search_markups);
        searchByDate = (EditText) view.findViewById(R.id.search_by_date_et);
        searchByLocation = (EditText) view.findViewById(R.id.search_by_location_et);
        searchByMarkups = (EditText) view.findViewById(R.id.search_by_markups_et);
        search_bt = (Button) view.findViewById(R.id.search_bt);

        switch_trade = (Switch) view.findViewById(R.id.switch_trade);
        searchByDate.setOnClickListener(this);
        searchLocation.setOnClickListener(this);
        searchMarkups.setOnClickListener(this);
        search_bt.setOnClickListener(this);


        return view;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();


        switch (id) {
            case R.id.search_date:

                break;
            case R.id.search_location:
                if (!button_click) {
                    location_clicked =true;
                    searchLocation.setBackgroundResource(R.drawable.button_shape);
                    searchLocation.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    searchByLocation.setVisibility(View.VISIBLE);
                    button_click = true;

                } else  {
                    location_clicked = false;
                    searchLocation.setBackgroundResource(R.drawable.button_shape_two);
                    searchLocation.setTextColor(getResources().getColor(R.color.colorPrimary));
                    searchByLocation.setVisibility(View.GONE);
                    button_click = false;
                }
                break;
            case R.id.search_markups:
                if (!button_click) {
                    markups_clicked = true;
                    searchMarkups.setBackgroundResource(R.drawable.button_shape);
                    searchMarkups.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    searchByMarkups.setVisibility(View.VISIBLE);
                    button_click = true;
                } else {
                    markups_clicked  = false;
                    searchMarkups.setBackgroundResource(R.drawable.button_shape_two);
                    searchMarkups.setTextColor(getResources().getColor(R.color.colorPrimary));
                    searchByMarkups.setVisibility(View.GONE);
                    button_click = false;
                }
                break;
            case R.id.search_bt:
                    String search_by_date = searchByDate.getText().toString();
                    String search_by_markups = searchByMarkups.getText().toString();
                    String search_by_location = searchByLocation.getText().toString();
                    boolean isChecked = switch_trade.isChecked();

                Intent intent = new Intent(getContext(), ResultActivity.class);
                intent.putExtra("search_by_date",search_by_date);
                intent.putExtra("search_by_markups",search_by_markups);
                intent.putExtra("search_by_location",search_by_location);
                intent.putExtra("isChecked",isChecked);
                startActivity(intent);

                break;
            case R.id.search_by_date_et:
                if (date_set) {
                    Calendar calendar = Calendar.getInstance();
                    int c_year = calendar.get(Calendar.YEAR);
                    int c_month = calendar.get(Calendar.MONTH);
                    int c_day = calendar.get(Calendar.DAY_OF_MONTH);
                    new DatePickerDialog(getContext(),R.style.DialogTheme,datePickerListener, c_year, c_month, c_day).show();


                    date_set = false;
                }else{
                    searchByDate.setText("");
                    date_set  = true;
                }
                break;

        }

    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {


        public void onDateSet(DatePicker view, int c_year,
                              int c_month, int c_day) {


            year = c_year;
            month = c_month;
            day = c_day;

            String mm = (month+1)<10?"0" + (month+1):""+(month+1);
            searchByDate.setText(new StringBuilder().append(day)
                    .append("/").append(mm).append("/").append(year)
                    .append(" "));

        }
    };

}
