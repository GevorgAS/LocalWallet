package ipc.gev.localwallet.Fragments;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;

import ipc.gev.localwallet.Activities.EditActivity;
import ipc.gev.localwallet.Adapters.TradeAdapter;
import ipc.gev.localwallet.R;
import ipc.gev.localwallet.db.DB;
import ipc.gev.localwallet.db.entity.Trade;


public class SearchFragment extends Fragment implements View.OnClickListener {
    int year;
    int month;
    int day;
    Button searchDate, searchLocation, searchMarkups, searchbt;
    EditText searchByDate, searchByLocation, searchByMarkups;
    TextView result;
    boolean button_click = false;
    boolean location_clicked = false;
    boolean markups_clicked = false;
    BottomSheetBehavior bottomSheetBehavior;
    Switch switch_trade;
    DB db;
    ArrayList<Trade> trades;
    TradeAdapter tradeAdapter;
    ListView listView;
    final int EDIT_INTENT = 100;
    boolean date_set = true;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        searchDate = (Button) view.findViewById(R.id.search_date);
        View bottomSheet =view.findViewById( R.id.bottom_sheet );
        searchLocation = (Button) view.findViewById(R.id.search_location);
        searchMarkups = (Button) view.findViewById(R.id.search_markups);
        searchByDate = (EditText) view.findViewById(R.id.search_by_date_et);
        searchByLocation = (EditText) view.findViewById(R.id.search_by_location_et);
        searchByMarkups = (EditText) view.findViewById(R.id.search_by_markups_et);
        searchbt = (Button) view.findViewById(R.id.search_bt);
        listView = (ListView)bottomSheet.findViewById(R.id.list_view);
        switch_trade = (Switch) view.findViewById(R.id.switch_trade);
        result = (TextView)view.findViewById(R.id.result);
        searchByDate.setOnClickListener(this);
        searchLocation.setOnClickListener(this);
        searchMarkups.setOnClickListener(this);
        searchbt.setOnClickListener(this);
        db = DB.getInstance(getContext());
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomInit();
        dialogTrade();
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

                } else if (button_click) {
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
                } else if (button_click) {
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
                    if (!switch_trade.isChecked()) {
                        if (search_by_markups.equals("") && search_by_location.equals("") && !search_by_date.equals("")) {
                            int sumIncome = db.sumByDate(search_by_date, Trade.INCOME);
                            trades = (ArrayList<Trade>) db.searchByDate(search_by_date, Trade.INCOME);
                            tradeAdapter = new TradeAdapter(getContext(), trades);
                            listView.setAdapter(tradeAdapter);
                            tradeAdapter.notifyDataSetChanged();
                            result.setText(getString(R.string.income_sum)+" "+sumIncome+" "+getString(R.string.money));
                        }else if (!search_by_markups.equals("") && !search_by_location.equals("") && !search_by_date.equals("")){
                            search_by_location = searchByLocation.getText().toString();
                            search_by_markups = searchByMarkups.getText().toString();
                            int sumIncome =db.sumByDateLocationMarkups(search_by_date,search_by_location,search_by_markups,Trade.INCOME);
                            trades = (ArrayList<Trade>) db.searchByDateLocationMarkups(search_by_date,search_by_location,search_by_markups,Trade.INCOME);
                            tradeAdapter = new TradeAdapter(getContext(), trades);
                            listView.setAdapter(tradeAdapter);
                            tradeAdapter.notifyDataSetChanged();
                            result.setText(getString(R.string.income_sum)+" "+sumIncome+" "+getString(R.string.money));
                        }else if (search_by_location.equals("") && !search_by_markups.equals("") && !search_by_date.equals("")){
                            search_by_markups = searchByMarkups.getText().toString();
                            int sumIncome = db.sumByDateMarkups(search_by_date,search_by_markups,Trade.INCOME);
                            trades = (ArrayList<Trade>) db.searchByDateMarkups(search_by_date,search_by_markups,Trade.INCOME);
                            tradeAdapter = new TradeAdapter(getContext(), trades);
                            listView.setAdapter(tradeAdapter);
                            tradeAdapter.notifyDataSetChanged();
                            result.setText(getString(R.string.income_sum)+" "+sumIncome+" "+getString(R.string.money));
                        }else if (search_by_markups.equals("") && !search_by_location.equals("") && !search_by_date.equals("")){
                            search_by_location = searchByLocation.getText().toString();
                            int sumIncome = db.sumByDateLocation(search_by_date,search_by_location,Trade.INCOME);
                            trades =(ArrayList<Trade>) db.searchByDateLocation(search_by_date,search_by_location,Trade.INCOME);
                            tradeAdapter = new TradeAdapter(getContext(), trades);
                            listView.setAdapter(tradeAdapter);
                            tradeAdapter.notifyDataSetChanged();
                            result.setText(getString(R.string.income_sum)+" "+sumIncome+" "+getString(R.string.money));
                        }else if (search_by_date.equals("") && search_by_location.equals("") && search_by_markups.equals("") ){
                            int sumIncome = db.sumAll(Trade.INCOME);
                            trades =(ArrayList<Trade>) db.searchAll(Trade.INCOME);
                            tradeAdapter = new TradeAdapter(getContext(), trades);
                            listView.setAdapter(tradeAdapter);
                            tradeAdapter.notifyDataSetChanged();
                            result.setText(getString(R.string.income_sum)+" "+sumIncome+" "+getString(R.string.money));
                        }else if (search_by_date.equals("") && !search_by_location.equals("") && search_by_markups.equals("") ){
                            int sumIncome = db.sumByLocation(search_by_location,Trade.INCOME);
                            trades =(ArrayList<Trade>) db.searchByLocation(search_by_location,Trade.INCOME);
                            tradeAdapter = new TradeAdapter(getContext(), trades);
                            listView.setAdapter(tradeAdapter);
                            tradeAdapter.notifyDataSetChanged();
                            result.setText(getString(R.string.income_sum)+" "+sumIncome+" "+getString(R.string.money));
                        }else if (search_by_date.equals("") && search_by_location.equals("") && !search_by_markups.equals("") ){
                            int sumIncome = db.sumByMarkups(search_by_markups,Trade.INCOME);
                            trades =  (ArrayList<Trade>) db.searchByMarkups(search_by_markups,Trade.INCOME);
                            tradeAdapter = new TradeAdapter(getContext(), trades);
                            listView.setAdapter(tradeAdapter);
                            tradeAdapter.notifyDataSetChanged();
                            result.setText(getString(R.string.income_sum)+" "+sumIncome+" "+getString(R.string.money));
                        }else if (search_by_date.equals("") && !search_by_location.equals("") && !search_by_markups.equals("") ){
                            int sumIncome = db.sumByMarkupsLocation(search_by_location,search_by_markups,Trade.INCOME);
                            trades =  (ArrayList<Trade>) db.searchByMarkupsLocation(search_by_location,search_by_markups,Trade.INCOME);
                            tradeAdapter = new TradeAdapter(getContext(), trades);
                            listView.setAdapter(tradeAdapter);
                            tradeAdapter.notifyDataSetChanged();
                            result.setText(getString(R.string.income_sum)+" "+sumIncome+" "+getString(R.string.money));
                        }
                    } else {
                        if (search_by_markups.equals("") && search_by_location.equals("") && !search_by_date.equals("")) {
                            int sumExpense = db.sumByDate(search_by_date, Trade.EXPENSE);
                            trades = (ArrayList<Trade>) db.searchByDate(search_by_date, Trade.EXPENSE);
                            tradeAdapter = new TradeAdapter(getContext(), trades);
                            listView.setAdapter(tradeAdapter);
                            tradeAdapter.notifyDataSetChanged();
                            result.setText(getString(R.string.expense_sum)+" "+sumExpense+" "+getString(R.string.money));
                        }else if (!search_by_markups.equals("") && !search_by_location.equals("") && !search_by_date.equals("")){
                            search_by_location = searchByLocation.getText().toString();
                            search_by_markups = searchByMarkups.getText().toString();
                            int sumExpense =db.sumByDateLocationMarkups(search_by_date,search_by_location,search_by_markups,Trade.EXPENSE);
                            trades = (ArrayList<Trade>) db.searchByDateLocationMarkups(search_by_date,search_by_location,search_by_markups,Trade.EXPENSE);
                            tradeAdapter = new TradeAdapter(getContext(), trades);
                            listView.setAdapter(tradeAdapter);
                            tradeAdapter.notifyDataSetChanged();
                            result.setText(getString(R.string.expense_sum)+" "+sumExpense+" "+getString(R.string.money));
                        }else if (search_by_location.equals("") && !search_by_markups.equals("") && !search_by_date.equals("")){
                            search_by_markups = searchByMarkups.getText().toString();
                            int sumExpense = db.sumByDateMarkups(search_by_date,search_by_markups,Trade.EXPENSE);
                            trades = (ArrayList<Trade>) db.searchByDateMarkups(search_by_date,search_by_markups,Trade.EXPENSE);
                            tradeAdapter = new TradeAdapter(getContext(), trades);
                            listView.setAdapter(tradeAdapter);
                            tradeAdapter.notifyDataSetChanged();
                            result.setText(getString(R.string.expense_sum)+" "+sumExpense+" "+getString(R.string.money));
                        }else if (search_by_markups.equals("") && !search_by_location.equals("") && !search_by_date.equals("")){
                            search_by_location = searchByLocation.getText().toString();
                            int sumExpense = db.sumByDateLocation(search_by_date,search_by_location,Trade.EXPENSE);
                            trades =(ArrayList<Trade>) db.searchByDateLocation(search_by_date,search_by_location,Trade.EXPENSE);
                            tradeAdapter = new TradeAdapter(getContext(), trades);
                            listView.setAdapter(tradeAdapter);
                            tradeAdapter.notifyDataSetChanged();
                            result.setText(getString(R.string.expense_sum)+" "+sumExpense+" "+getString(R.string.money));
                        }else if (search_by_date.equals("") && search_by_location.equals("") && search_by_markups.equals("") ){
                            int sumExpense = db.sumAll(Trade.EXPENSE);
                            trades =(ArrayList<Trade>) db.searchAll(Trade.EXPENSE);
                            tradeAdapter = new TradeAdapter(getContext(), trades);
                            listView.setAdapter(tradeAdapter);
                            tradeAdapter.notifyDataSetChanged();
                            result.setText(getString(R.string.expense_sum)+" "+sumExpense+" "+getString(R.string.money));
                        }else if (search_by_date.equals("") && !search_by_location.equals("") && search_by_markups.equals("") ){
                            int sumExpense = db.sumByLocation(search_by_location,Trade.EXPENSE);
                            trades =(ArrayList<Trade>) db.searchByLocation(search_by_location,Trade.EXPENSE);
                            tradeAdapter = new TradeAdapter(getContext(), trades);
                            listView.setAdapter(tradeAdapter);
                            tradeAdapter.notifyDataSetChanged();
                            result.setText(getString(R.string.expense_sum)+" "+sumExpense+" "+getString(R.string.money));
                        }else if (search_by_date.equals("") && search_by_location.equals("") && !search_by_markups.equals("") ){
                            int sumExpense = db.sumByMarkups(search_by_markups,Trade.EXPENSE);
                            trades =  (ArrayList<Trade>) db.searchByMarkups(search_by_markups,Trade.EXPENSE);
                            tradeAdapter = new TradeAdapter(getContext(), trades);
                            listView.setAdapter(tradeAdapter);
                            tradeAdapter.notifyDataSetChanged();
                            result.setText(getString(R.string.expense_sum)+" "+sumExpense+" "+getString(R.string.money));
                        }else if (search_by_date.equals("") && !search_by_location.equals("") && !search_by_markups.equals("") ){
                            int sumExpense = db.sumByMarkupsLocation(search_by_location,search_by_markups,Trade.EXPENSE);
                            trades =  (ArrayList<Trade>) db.searchByMarkupsLocation(search_by_location,search_by_markups,Trade.EXPENSE);
                            tradeAdapter = new TradeAdapter(getContext(), trades);
                            listView.setAdapter(tradeAdapter);
                            tradeAdapter.notifyDataSetChanged();
                            result.setText(getString(R.string.expense_sum)+" "+sumExpense+" "+getString(R.string.money));
                        }
                    }
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                break;
            case R.id.search_by_date_et:
                if (date_set) {
                    Calendar calendar = Calendar.getInstance();
                    int c_year = calendar.get(Calendar.YEAR);
                    int c_month = calendar.get(Calendar.MONTH);
                    int c_day = calendar.get(Calendar.DAY_OF_MONTH);
                    new DatePickerDialog(getContext(), datePickerListener, c_year, c_month, c_day).show();
                    date_set = false;
                }else{
                    searchByDate.setText("");
                    date_set  = true;
                }
                break;

        }

    }
    private void bottomInit() {
        bottomSheetBehavior.setPeekHeight(0);
        bottomSheetBehavior.setHideable(true);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.setPeekHeight(0);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }

        });

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

    private void dialogTrade(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {
                String currentLocation = getLocation(position);
                String currentMarkups = getMarkups(position);
                int currentPrice = getPrice(position);
                String currentDate = getDate(position);
                String m = getResources().getString(R.string.money);
                String message = new StringBuilder().append(currentPrice).append(" ").append(m).append("\n").append(currentMarkups).append("\n").append(currentDate).toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(currentLocation)
                        .setMessage(message)
                        .setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteTrade(position);
                            }
                        })
                        .setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                editTrade(position);
                            }
                        })
                        .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setIcon(R.drawable.location_icon)
                        .show();
            }
        });
    }
    private void deleteTrade(int position){
        Trade trade = trades.get(position);
        long id_get = trade.getId();
        db.deleteTradeByID(id_get);
        trades.remove(position);
        tradeAdapter.notifyDataSetChanged();
    }
    private void editTrade(int position){
        Trade trade = trades.get(position);
        long id = trade.getId();
        String markups = trade.getMarkups();
        String location = trade.getLocation();
        String date = trade.getDate();
        int price = trade.getPrice();

        Intent intent = new Intent(getActivity(), EditActivity.class);
        intent.putExtra("_id",id+"");
        intent.putExtra("_markups",markups);
        intent.putExtra("_location",location);
        intent.putExtra("_price",price+"");
        intent.putExtra("_date",date);
        startActivityForResult(intent,EDIT_INTENT);

    }
    private int getPrice(int position){
        Trade trade = trades.get(position);
        return trade.getPrice();
    }
    private String getMarkups(int position){
        Trade trade = trades.get(position);

        return trade.getMarkups();
    }
    private String getLocation(int position){
        Trade trade = trades.get(position);
        return trade.getLocation();
    }
    private String getDate(int position){
        Trade trade = trades.get(position);
        return trade.getDate();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EDIT_INTENT){
            tradeAdapter.notifyDataSetChanged();
        }
    }
}
