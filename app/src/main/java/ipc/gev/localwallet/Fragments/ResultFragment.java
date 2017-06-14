package ipc.gev.localwallet.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ipc.gev.localwallet.Activities.EditActivity;
import ipc.gev.localwallet.Adapters.TradeAdapter;
import ipc.gev.localwallet.R;
import ipc.gev.localwallet.db.DB;
import ipc.gev.localwallet.db.entity.Trade;


public class ResultFragment extends Fragment {
    ListView listView;
    TextView income_tv;
    TextView expense_tv;
    TextView sum_tv;
    DB db;
    ArrayList<Trade> trades;
    TradeAdapter tradeAdapter;
    final int EDIT_INTENT = 100;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.result_fragment,container,false);
        listView = (ListView) view.findViewById(R.id.list_view);
        income_tv = (TextView) view.findViewById(R.id.income_text_view);
        expense_tv = (TextView) view.findViewById(R.id.expense_text_view);
        sum_tv = (TextView) view.findViewById(R.id.sum_text_view);
        db = DB.getInstance(getContext());

        return view;
    }
    private void listInit(String search_by_date,String search_by_location,String search_by_markups,boolean isChecked) {
        if (!isChecked) {
            if (search_by_markups.equals("") && search_by_location.equals("") && !search_by_date.equals("")) {
                int sumExpense = db.sumByDate(search_by_date, Trade.EXPENSE);
                int sumIncome = db.sumByDate(search_by_date, Trade.INCOME);
                trades = (ArrayList<Trade>) db.searchByDate(search_by_date, Trade.INCOME);
                tradeAdapter = new TradeAdapter(getContext(), trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                int result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (!search_by_markups.equals("") && !search_by_location.equals("") && !search_by_date.equals("")) {
                int sumExpense = db.sumByDateLocationMarkups(search_by_date, search_by_location, search_by_markups, Trade.EXPENSE);
                int sumIncome = db.sumByDateLocationMarkups(search_by_date, search_by_location, search_by_markups, Trade.INCOME);
                trades = (ArrayList<Trade>) db.searchByDateLocationMarkups(search_by_date, search_by_location, search_by_markups, Trade.INCOME);
                tradeAdapter = new TradeAdapter(getContext(), trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                int result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_location.equals("") && !search_by_markups.equals("") && !search_by_date.equals("")) {
                int sumExpense = db.sumByDateMarkups(search_by_date, search_by_markups, Trade.EXPENSE);
                int sumIncome = db.sumByDateMarkups(search_by_date, search_by_markups, Trade.INCOME);
                trades = (ArrayList<Trade>) db.searchByDateMarkups(search_by_date, search_by_markups, Trade.INCOME);
                tradeAdapter = new TradeAdapter(getContext(), trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                int result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_markups.equals("") && !search_by_location.equals("") && !search_by_date.equals("")) {
                int sumExpense = db.sumByDateLocation(search_by_date, search_by_location, Trade.EXPENSE);
                int sumIncome = db.sumByDateLocation(search_by_date, search_by_location, Trade.INCOME);
                trades = (ArrayList<Trade>) db.searchByDateLocation(search_by_date, search_by_location, Trade.INCOME);
                tradeAdapter = new TradeAdapter(getContext(), trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                int result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_date.equals("") && search_by_location.equals("") && search_by_markups.equals("")) {
                int sumExpense = db.sumAll(Trade.EXPENSE);
                int sumIncome = db.sumAll(Trade.INCOME);
                trades = (ArrayList<Trade>) db.searchAll(Trade.INCOME);
                tradeAdapter = new TradeAdapter(getContext(), trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                int result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_date.equals("") && !search_by_location.equals("") && search_by_markups.equals("")) {
                int sumExpense = db.sumByLocation(search_by_location, Trade.EXPENSE);
                int sumIncome = db.sumByLocation(search_by_location, Trade.INCOME);
                trades = (ArrayList<Trade>) db.searchByLocation(search_by_location, Trade.INCOME);
                tradeAdapter = new TradeAdapter(getContext(), trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                int result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_date.equals("") && search_by_location.equals("") && !search_by_markups.equals("")) {
                int sumExpense = db.sumByMarkups(search_by_markups, Trade.EXPENSE);
                int sumIncome = db.sumByMarkups(search_by_markups, Trade.INCOME);
                trades = (ArrayList<Trade>) db.searchByMarkups(search_by_markups, Trade.INCOME);
                tradeAdapter = new TradeAdapter(getContext(), trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                int result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_date.equals("") && !search_by_location.equals("") && !search_by_markups.equals("")) {
                int sumExpense = db.sumByMarkupsLocation(search_by_location, search_by_markups, Trade.EXPENSE);
                int sumIncome = db.sumByMarkupsLocation(search_by_location, search_by_markups, Trade.INCOME);
                trades = (ArrayList<Trade>) db.searchByMarkupsLocation(search_by_location, search_by_markups, Trade.INCOME);
                tradeAdapter = new TradeAdapter(getContext(), trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                int result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            }
        } else {
            if (search_by_markups.equals("") && search_by_location.equals("") && !search_by_date.equals("")) {
                int sumIncome = db.sumByDate(search_by_date, Trade.INCOME);
                int sumExpense = db.sumByDate(search_by_date, Trade.EXPENSE);
                trades = (ArrayList<Trade>) db.searchByDate(search_by_date, Trade.EXPENSE);
                tradeAdapter = new TradeAdapter(getContext(), trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                int result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (!search_by_markups.equals("") && !search_by_location.equals("") && !search_by_date.equals("")) {
                int sumIncome = db.sumByDateLocationMarkups(search_by_date, search_by_location, search_by_markups, Trade.INCOME);
                int sumExpense = db.sumByDateLocationMarkups(search_by_date, search_by_location, search_by_markups, Trade.EXPENSE);
                trades = (ArrayList<Trade>) db.searchByDateLocationMarkups(search_by_date, search_by_location, search_by_markups, Trade.EXPENSE);
                tradeAdapter = new TradeAdapter(getContext(), trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                int result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_location.equals("") && !search_by_markups.equals("") && !search_by_date.equals("")) {
                int sumIncome = db.sumByDateMarkups(search_by_date, search_by_markups, Trade.INCOME);
                int sumExpense = db.sumByDateMarkups(search_by_date, search_by_markups, Trade.EXPENSE);
                trades = (ArrayList<Trade>) db.searchByDateMarkups(search_by_date, search_by_markups, Trade.EXPENSE);
                tradeAdapter = new TradeAdapter(getContext(), trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                int result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_markups.equals("") && !search_by_location.equals("") && !search_by_date.equals("")) {
                int sumExpense = db.sumByDateLocation(search_by_date, search_by_location, Trade.EXPENSE);
                int sumIncome = db.sumByDateLocation(search_by_date, search_by_location, Trade.INCOME);
                trades = (ArrayList<Trade>) db.searchByDateLocation(search_by_date, search_by_location, Trade.EXPENSE);
                tradeAdapter = new TradeAdapter(getContext(), trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                int result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_date.equals("") && search_by_location.equals("") && search_by_markups.equals("")) {
                int sumIncome = db.sumAll(Trade.INCOME);
                int sumExpense = db.sumAll(Trade.EXPENSE);
                trades = (ArrayList<Trade>) db.searchAll(Trade.EXPENSE);
                tradeAdapter = new TradeAdapter(getContext(), trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                int result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_date.equals("") && !search_by_location.equals("") && search_by_markups.equals("")) {
                int sumIncome = db.sumByLocation(search_by_location, Trade.INCOME);
                int sumExpense = db.sumByLocation(search_by_location, Trade.EXPENSE);
                trades = (ArrayList<Trade>) db.searchByLocation(search_by_location, Trade.EXPENSE);
                tradeAdapter = new TradeAdapter(getContext(), trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                int result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_date.equals("") && search_by_location.equals("") && !search_by_markups.equals("")) {
                int sumIncome = db.sumByMarkups(search_by_markups, Trade.INCOME);
                int sumExpense = db.sumByMarkups(search_by_markups, Trade.EXPENSE);
                trades = (ArrayList<Trade>) db.searchByMarkups(search_by_markups, Trade.EXPENSE);
                tradeAdapter = new TradeAdapter(getContext(), trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                int result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_date.equals("") && !search_by_location.equals("") && !search_by_markups.equals("")) {
                int sumIncome = db.sumByMarkupsLocation(search_by_location, search_by_markups, Trade.INCOME);
                int sumExpense = db.sumByMarkupsLocation(search_by_location, search_by_markups, Trade.EXPENSE);
                trades = (ArrayList<Trade>) db.searchByMarkupsLocation(search_by_location, search_by_markups, Trade.EXPENSE);
                tradeAdapter = new TradeAdapter(getContext(), trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                int result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            }
        }
    }
    private void dialogTrade(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {
                String currentLocation = getLocation(position);
                String currentMarkups = getMarkups(position);
                int currentPrice = getPrice(position);
                String currentDate = getDate(position);
                String m = getResources().getString(R.string.money);
                String message = String.valueOf(currentPrice) + " " + m + "\n" + currentMarkups + "\n" + currentDate;
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

}
