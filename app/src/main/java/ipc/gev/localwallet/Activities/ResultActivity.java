package ipc.gev.localwallet.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ipc.gev.localwallet.Adapters.TradeAdapter;
import ipc.gev.localwallet.R;
import ipc.gev.localwallet.db.DB;
import ipc.gev.localwallet.db.entity.Trade;

public class ResultActivity extends AppCompatActivity {
    private ListView listView;
    private TextView income_tv;
    private TextView expense_tv;
    private TextView sum_tv;
    private DB db;
    private ArrayList<Trade> trades;
    private TradeAdapter tradeAdapter;
    private String date;
    private String location;
    private String markups;
    private boolean income_chb;
    private boolean expense_chb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        listView = (ListView) findViewById(R.id.list_view);
        income_tv = (TextView) findViewById(R.id.income_text_view);
        expense_tv = (TextView) findViewById(R.id.expense_text_view);
        sum_tv = (TextView) findViewById(R.id.sum_text_view);
        db = DB.getInstance(this);
        Intent intent = getIntent();
        date = intent.getStringExtra("search_by_date");
        location = intent.getStringExtra("search_by_location");
        markups = intent.getStringExtra("search_by_markups");
        income_chb = intent.getExtras().getBoolean("income_chb");
        expense_chb = intent.getExtras().getBoolean("expense_chb");

        trades = new ArrayList<>();
        tradeAdapter = new TradeAdapter(this,trades);
        listView.setAdapter(tradeAdapter);
        listInit(date,location,markups,income_chb,expense_chb);
        dialogTrade();

    }
    private void listInit(String search_by_date, String search_by_location, String search_by_markups, boolean income_chb,boolean expense_chb) {
        if (income_chb && !expense_chb) {
            if (search_by_markups.equals("") && search_by_location.equals("") && !search_by_date.equals("")) {
                long sumExpense = db.sumByDate(search_by_date, Trade.EXPENSE);
                long sumIncome = db.sumByDate(search_by_date, Trade.INCOME);
                trades = (ArrayList<Trade>) db.searchByDate(search_by_date, Trade.INCOME);
                tradeAdapter = new TradeAdapter(this, trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                long result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (!search_by_markups.equals("") && !search_by_location.equals("") && !search_by_date.equals("")) {
                long sumExpense = db.sumByDateLocationMarkups(search_by_date, search_by_location, search_by_markups, Trade.EXPENSE);
                long sumIncome = db.sumByDateLocationMarkups(search_by_date, search_by_location, search_by_markups, Trade.INCOME);
                trades = (ArrayList<Trade>) db.searchByDateLocationMarkups(search_by_date, search_by_location, search_by_markups, Trade.INCOME);
                tradeAdapter = new TradeAdapter(this, trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                long result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_location.equals("") && !search_by_markups.equals("") && !search_by_date.equals("")) {
                long sumExpense = db.sumByDateMarkups(search_by_date, search_by_markups, Trade.EXPENSE);
                long sumIncome = db.sumByDateMarkups(search_by_date, search_by_markups, Trade.INCOME);
                trades = (ArrayList<Trade>) db.searchByDateMarkups(search_by_date, search_by_markups, Trade.INCOME);
                tradeAdapter = new TradeAdapter(this, trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                long result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_markups.equals("") && !search_by_location.equals("") && !search_by_date.equals("")) {
                long sumExpense = db.sumByDateLocation(search_by_date, search_by_location, Trade.EXPENSE);
                long sumIncome = db.sumByDateLocation(search_by_date, search_by_location, Trade.INCOME);
                trades = (ArrayList<Trade>) db.searchByDateLocation(search_by_date, search_by_location, Trade.INCOME);
                tradeAdapter = new TradeAdapter(this, trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                long result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_date.equals("") && search_by_location.equals("") && search_by_markups.equals("")) {
                long sumExpense = db.sumAll(Trade.EXPENSE);
                long sumIncome = db.sumAll(Trade.INCOME);
                trades = (ArrayList<Trade>) db.searchAll(Trade.INCOME);
                tradeAdapter = new TradeAdapter(this, trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                long result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_date.equals("") && !search_by_location.equals("") && search_by_markups.equals("")) {
                long sumExpense = db.sumByLocation(search_by_location, Trade.EXPENSE);
                long sumIncome = db.sumByLocation(search_by_location, Trade.INCOME);
                trades = (ArrayList<Trade>) db.searchByLocation(search_by_location, Trade.INCOME);
                tradeAdapter = new TradeAdapter(this, trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                long result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_date.equals("") && search_by_location.equals("") && !search_by_markups.equals("")) {
                long sumExpense = db.sumByMarkups(search_by_markups, Trade.EXPENSE);
                long sumIncome = db.sumByMarkups(search_by_markups, Trade.INCOME);
                trades = (ArrayList<Trade>) db.searchByMarkups(search_by_markups, Trade.INCOME);
                tradeAdapter = new TradeAdapter(this, trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                long result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_date.equals("") && !search_by_location.equals("") && !search_by_markups.equals("")) {
                long sumExpense = db.sumByMarkupsLocation(search_by_location, search_by_markups, Trade.EXPENSE);
                long sumIncome = db.sumByMarkupsLocation(search_by_location, search_by_markups, Trade.INCOME);
                trades = (ArrayList<Trade>) db.searchByMarkupsLocation(search_by_location, search_by_markups, Trade.INCOME);
                tradeAdapter = new TradeAdapter(this, trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                long result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            }
        } else if (expense_chb && !income_chb){
            if (search_by_markups.equals("") && search_by_location.equals("") && !search_by_date.equals("")) {
                long sumIncome = db.sumByDate(search_by_date, Trade.INCOME);
                long sumExpense = db.sumByDate(search_by_date, Trade.EXPENSE);
                trades = (ArrayList<Trade>) db.searchByDate(search_by_date, Trade.EXPENSE);
                tradeAdapter = new TradeAdapter(this, trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                long result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (!search_by_markups.equals("") && !search_by_location.equals("") && !search_by_date.equals("")) {
                long sumIncome = db.sumByDateLocationMarkups(search_by_date, search_by_location, search_by_markups, Trade.INCOME);
                long sumExpense = db.sumByDateLocationMarkups(search_by_date, search_by_location, search_by_markups, Trade.EXPENSE);
                trades = (ArrayList<Trade>) db.searchByDateLocationMarkups(search_by_date, search_by_location, search_by_markups, Trade.EXPENSE);
                tradeAdapter = new TradeAdapter(this, trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                long result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_location.equals("") && !search_by_markups.equals("") && !search_by_date.equals("")) {
                long sumIncome = db.sumByDateMarkups(search_by_date, search_by_markups, Trade.INCOME);
                long sumExpense = db.sumByDateMarkups(search_by_date, search_by_markups, Trade.EXPENSE);
                trades = (ArrayList<Trade>) db.searchByDateMarkups(search_by_date, search_by_markups, Trade.EXPENSE);
                tradeAdapter = new TradeAdapter(this, trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                long result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_markups.equals("") && !search_by_location.equals("") && !search_by_date.equals("")) {
                long sumExpense = db.sumByDateLocation(search_by_date, search_by_location, Trade.EXPENSE);
                long sumIncome = db.sumByDateLocation(search_by_date, search_by_location, Trade.INCOME);
                trades = (ArrayList<Trade>) db.searchByDateLocation(search_by_date, search_by_location, Trade.EXPENSE);
                tradeAdapter = new TradeAdapter(this, trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                long result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_date.equals("") && search_by_location.equals("") && search_by_markups.equals("")) {
                long sumIncome = db.sumAll(Trade.INCOME);
                long sumExpense = db.sumAll(Trade.EXPENSE);
                trades = (ArrayList<Trade>) db.searchAll(Trade.EXPENSE);
                tradeAdapter = new TradeAdapter(this, trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                long result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_date.equals("") && !search_by_location.equals("") && search_by_markups.equals("")) {
                long sumIncome = db.sumByLocation(search_by_location, Trade.INCOME);
                long sumExpense = db.sumByLocation(search_by_location, Trade.EXPENSE);
                trades = (ArrayList<Trade>) db.searchByLocation(search_by_location, Trade.EXPENSE);
                tradeAdapter = new TradeAdapter(this, trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                long result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_date.equals("") && search_by_location.equals("") && !search_by_markups.equals("")) {
                long sumIncome = db.sumByMarkups(search_by_markups, Trade.INCOME);
                long sumExpense = db.sumByMarkups(search_by_markups, Trade.EXPENSE);
                trades = (ArrayList<Trade>) db.searchByMarkups(search_by_markups, Trade.EXPENSE);
                tradeAdapter = new TradeAdapter(this, trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                long result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_date.equals("") && !search_by_location.equals("") && !search_by_markups.equals("")) {
                long sumIncome = db.sumByMarkupsLocation(search_by_location, search_by_markups, Trade.INCOME);
                long sumExpense = db.sumByMarkupsLocation(search_by_location, search_by_markups, Trade.EXPENSE);
                trades = (ArrayList<Trade>) db.searchByMarkupsLocation(search_by_location, search_by_markups, Trade.EXPENSE);
                tradeAdapter = new TradeAdapter(this, trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                long result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            }
        }else{
            if (search_by_markups.equals("") && search_by_location.equals("") && !search_by_date.equals("")) {
                long sumIncome = db.sumByDate(search_by_date, Trade.INCOME);
                long sumExpense = db.sumByDate(search_by_date, Trade.EXPENSE);
                trades = (ArrayList<Trade>) db.getAllByDate(search_by_date);
                tradeAdapter = new TradeAdapter(this, trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                long result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (!search_by_markups.equals("") && !search_by_location.equals("") && !search_by_date.equals("")) {
                long sumIncome = db.sumByDateLocationMarkups(search_by_date, search_by_location, search_by_markups, Trade.INCOME);
                long sumExpense = db.sumByDateLocationMarkups(search_by_date, search_by_location, search_by_markups, Trade.EXPENSE);
                trades = (ArrayList<Trade>) db.getAllByDateLocationMarkups(search_by_date, search_by_location, search_by_markups);
                tradeAdapter = new TradeAdapter(this, trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                long result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_location.equals("") && !search_by_markups.equals("") && !search_by_date.equals("")) {
                long sumIncome = db.sumByDateMarkups(search_by_date, search_by_markups, Trade.INCOME);
                long sumExpense = db.sumByDateMarkups(search_by_date, search_by_markups, Trade.EXPENSE);
                trades = (ArrayList<Trade>) db.getAllByDateMarkups(search_by_date, search_by_markups);
                tradeAdapter = new TradeAdapter(this, trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                long result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_markups.equals("") && !search_by_location.equals("") && !search_by_date.equals("")) {
                long sumExpense = db.sumByDateLocation(search_by_date, search_by_location, Trade.EXPENSE);
                long sumIncome = db.sumByDateLocation(search_by_date, search_by_location, Trade.INCOME);
                trades = (ArrayList<Trade>) db.getAllByDateLocation(search_by_date, search_by_location);
                tradeAdapter = new TradeAdapter(this, trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                long result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_date.equals("") && search_by_location.equals("") && search_by_markups.equals("")) {
                long sumIncome = db.sumAll(Trade.INCOME);
                long sumExpense = db.sumAll(Trade.EXPENSE);
                trades = (ArrayList<Trade>) db.getAll();
                tradeAdapter = new TradeAdapter(this, trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                long result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_date.equals("") && !search_by_location.equals("") && search_by_markups.equals("")) {
                long sumIncome = db.sumByLocation(search_by_location, Trade.INCOME);
                long sumExpense = db.sumByLocation(search_by_location, Trade.EXPENSE);
                trades = (ArrayList<Trade>) db.getAllByLocation(search_by_location);
                tradeAdapter = new TradeAdapter(this, trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                long result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_date.equals("") && search_by_location.equals("") && !search_by_markups.equals("")) {
                long sumIncome = db.sumByMarkups(search_by_markups, Trade.INCOME);
                long sumExpense = db.sumByMarkups(search_by_markups, Trade.EXPENSE);
                trades = (ArrayList<Trade>) db.getAllByMarkups(search_by_markups);
                tradeAdapter = new TradeAdapter(this, trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                long result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            } else if (search_by_date.equals("") && !search_by_location.equals("") && !search_by_markups.equals("")) {
                long sumIncome = db.sumByMarkupsLocation(search_by_location, search_by_markups, Trade.INCOME);
                long sumExpense = db.sumByMarkupsLocation(search_by_location, search_by_markups, Trade.EXPENSE);
                trades = (ArrayList<Trade>) db.getAllByLocationMarkups(search_by_location, search_by_markups);
                tradeAdapter = new TradeAdapter(this, trades);
                listView.setAdapter(tradeAdapter);
                tradeAdapter.notifyDataSetChanged();
                long result = sumIncome - sumExpense;
                income_tv.setText(getString(R.string.income_sum) + " " + sumIncome + " " + getString(R.string.money));
                expense_tv.setText(getString(R.string.expense_sum) + " " + sumExpense + " " + getString(R.string.money));
                sum_tv.setText(result + " " + getString(R.string.money));
            }
        }
    }
    private void dialogTrade(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                String currentLocation = getLocation(position);
                String currentMarkups = getMarkups(position);
                long currentPrice = getPrice(position);
                String currentDate = getDate(position);
                String m = getResources().getString(R.string.money);
                String message = String.valueOf(currentPrice) + " " + m + "\n" + currentMarkups + "\n" + currentDate;
                AlertDialog.Builder builder = new AlertDialog.Builder(ResultActivity.this);
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
        long price = trade.getPrice();

        Intent intent = new Intent(ResultActivity.this, EditActivity.class);
        intent.putExtra("_id",id+"");
        intent.putExtra("_markups",markups);
        intent.putExtra("_location",location);
        intent.putExtra("_price",price+"");
        intent.putExtra("_date",date);
        startActivityForResult(intent, 0b1100100);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0b1100100 && resultCode == 0b10 ){
            listInit(date,location,markups,income_chb,expense_chb);
        }
    }

    private long getPrice(int position){
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.languages:
                Intent lang_intent = new Intent(ResultActivity.this,LanguagesActivity.class);
                startActivity(lang_intent);
                break;
            case R.id.about:
                Intent about_intent = new Intent(ResultActivity.this,AboutActivity.class);
                startActivity(about_intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
