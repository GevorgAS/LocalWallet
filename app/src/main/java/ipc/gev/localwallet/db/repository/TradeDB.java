package ipc.gev.localwallet.db.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ipc.gev.localwallet.db.DB_Helper;
import ipc.gev.localwallet.db.entity.Trade;
import ipc.gev.localwallet.db.repository.functions.Trade_I;
import ipc.gev.localwallet.db.table.TradeTable;



public class TradeDB implements Trade_I{
    private SQLiteDatabase database;
    private DB_Helper dbHelper;

    public TradeDB(Context context){
        dbHelper = new DB_Helper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }


    @Override
    public void createTrade(Trade trade) {
        ContentValues values = new ContentValues();
        values.put(TradeTable.COLUMN_MARKUPS,trade.getMarkups());
        values.put(TradeTable.COLUMN_LOCATION,trade.getLocation());
        values.put(TradeTable.COLUMN_PRICE,trade.getPrice());
        values.put(TradeTable.COLUMN_DATE,trade.getDate());
        values.put(TradeTable.COLUMN_STATUS,trade.getStatus());
        database.insert(TradeTable.TABLE_NAME,null,values);
    }

    @Override
    public List<Trade> searchByDate(String date, int status) {
        List<Trade> trades = new ArrayList<>();
        String query = "select * from "+ TradeTable.TABLE_NAME+" where "+ TradeTable.COLUMN_DATE+" = ?  and "+TradeTable.COLUMN_STATUS+" = ?";
        Cursor cursor = database.rawQuery(query,new String[]{date,status+""});
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Trade trade = cursorToTrade(cursor);
            trades.add(trade);
            cursor.moveToNext();
        }
        cursor.close();
        return trades;
    }






    @Override
    public void deleteTradeByID(long id) {
        database.delete(TradeTable.TABLE_NAME, TradeTable.COLUMN_ID+"="+id,null);
    }

    @Override
    public void updateTradeByID(long id, String text, String location, int price, String date) {
        ContentValues values = new ContentValues();
        values.put(TradeTable.COLUMN_MARKUPS,text);
        values.put(TradeTable.COLUMN_LOCATION,location);
        values.put(TradeTable.COLUMN_PRICE,price);
        values.put(TradeTable.COLUMN_DATE,date);
        database.update(TradeTable.TABLE_NAME,values, TradeTable.COLUMN_ID+"="+id,null);
    }

    @Override
    public int sumTrades(String date, int status) {
        int sum = 0;
        String query = "select * from "+ TradeTable.TABLE_NAME+" where "+ TradeTable.COLUMN_DATE+" = ?  and "+TradeTable.COLUMN_STATUS+" = ?";
        Cursor cursor = database.rawQuery(query,new String[]{date,status+""});
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Trade trade = cursorToTrade(cursor);
            sum+=trade.getPrice();
            cursor.moveToNext();
        }
        cursor.close();
        return sum;
    }

    @Override
    public int sumByDateMarkups(String date, String markups, int status) {
        int sum = 0;
        String query = "select * from "+ TradeTable.TABLE_NAME+" where "+ TradeTable.COLUMN_DATE+" = ?  and "+TradeTable.COLUMN_STATUS+" = ? " +
                "and "+TradeTable.COLUMN_MARKUPS+" like ?";
        Cursor cursor = database.rawQuery(query,new String[]{date,status+"","%"+markups+"%"});
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Trade trade = cursorToTrade(cursor);
            sum+=trade.getPrice();
            cursor.moveToNext();
        }
        cursor.close();
        return sum;
    }

    @Override
    public int sumByDateLocation(String date, String location, int status) {
        int sum =0;
        String query = "select * from "+ TradeTable.TABLE_NAME+" where "+ TradeTable.COLUMN_DATE+" = ?  and "+TradeTable.COLUMN_STATUS+" = ? " +
                "and "+TradeTable.COLUMN_LOCATION+" like ?";
        Cursor cursor = database.rawQuery(query,new String[]{date,status+"","%"+location+"%"});
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Trade trade = cursorToTrade(cursor);
            sum+=trade.getPrice();
            cursor.moveToNext();
        }
        cursor.close();
        return sum;
    }

    @Override
    public int sumByDateLocationMarkups(String date, String location, String markups, int status) {
        int sum = 0;
        String query = "select * from "+ TradeTable.TABLE_NAME+" where "+ TradeTable.COLUMN_DATE+" = ?  and "+TradeTable.COLUMN_STATUS+" = ? " +
                "and "+TradeTable.COLUMN_LOCATION+" like ? and "+TradeTable.COLUMN_MARKUPS+" like ?";
        Cursor cursor = database.rawQuery(query,new String[]{date,status+"","%"+location+"%","%"+markups+"%"});
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Trade trade = cursorToTrade(cursor);
            sum+=trade.getPrice();
            cursor.moveToNext();
        }
        cursor.close();
        return sum;
    }

    @Override
    public List<Trade> searchByDateMarkups(String date, String markups, int status) {
        List<Trade> trades = new ArrayList<>();
        String query = "select * from "+ TradeTable.TABLE_NAME+" where "+ TradeTable.COLUMN_DATE+" = ?  and "+TradeTable.COLUMN_STATUS+" = ? " +
                "and "+TradeTable.COLUMN_MARKUPS+" like ?";
        Cursor cursor = database.rawQuery(query,new String[]{date,status+"","%"+markups+"%"});
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Trade trade = cursorToTrade(cursor);
            trades.add(trade);
            cursor.moveToNext();
        }
        cursor.close();
        return trades;
    }

    @Override
    public List<Trade> searchByDateLocation(String date, String location, int status) {
        List<Trade> trades = new ArrayList<>();
        String query = "select * from "+ TradeTable.TABLE_NAME+" where "+ TradeTable.COLUMN_DATE+" = ?  and "+TradeTable.COLUMN_STATUS+" = ? " +
                "and "+TradeTable.COLUMN_LOCATION+" like ?";
        Cursor cursor = database.rawQuery(query,new String[]{date,status+"","%"+location+"%"});
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Trade trade = cursorToTrade(cursor);
            trades.add(trade);
            cursor.moveToNext();
        }
        cursor.close();
        return trades;
    }

    @Override
    public List<Trade> searchByDateLocationMarkups(String date, String location, String markups, int status) {
        List<Trade> trades = new ArrayList<>();
        String query = "select * from "+ TradeTable.TABLE_NAME+" where "+ TradeTable.COLUMN_DATE+" = ?  and "+TradeTable.COLUMN_STATUS+" = ? " +
                "and "+TradeTable.COLUMN_LOCATION+" like ? and "+TradeTable.COLUMN_MARKUPS+" like ?";
        Cursor cursor = database.rawQuery(query,new String[]{date,status+"","%"+location+"%","%"+markups+"%"});
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Trade trade = cursorToTrade(cursor);
            trades.add(trade);
            cursor.moveToNext();
        }
        cursor.close();
        return trades;
    }


    private Trade cursorToTrade(Cursor cursor) {
        Trade trade = new Trade();
        trade.setId(cursor.getLong(0));
        trade.setMarkups(cursor.getString(1));
        trade.setLocation(cursor.getString(2));
        trade.setPrice(cursor.getInt(3));
        trade.setDate(cursor.getString(4));
        trade.setStatus(cursor.getInt(5));
        return trade;
    }
}
