package ipc.gev.localwallet.db;

import android.content.Context;

import java.util.List;

import ipc.gev.localwallet.db.entity.Trade;
import ipc.gev.localwallet.db.repository.TradeDB;
import ipc.gev.localwallet.db.repository.functions.Trade_I;



public class DB implements Trade_I {
    private TradeDB tradeDB;
    private static DB db;

    private DB(Context context){
        tradeDB = new TradeDB(context);
    }

    public static DB getInstance(Context context){
        if (db==null){
            db = new DB(context);
        }
        return db;
    }
    @Override
    public void createTrade(Trade trade) {
        tradeDB.open();
        tradeDB.createTrade(trade);
        tradeDB.close();
    }

    @Override
    public List<Trade> searchByDate(String date, int status) {
        tradeDB.open();
        List<Trade> trades = tradeDB.searchByDate(date, status);
        tradeDB.close();
        return trades;
    }






    @Override
    public void deleteTradeByID(long id) {
        tradeDB.open();
        tradeDB.deleteTradeByID(id);
        tradeDB.close();
    }

    @Override
    public void updateTradeByID(long id, String text, String location, int price, String date) {
        tradeDB.open();
        tradeDB.updateTradeByID(id,text,location,price,date);
        tradeDB.close();
    }

    @Override
    public int sumTrades(String date, int status) {
        tradeDB.open();
        int sum = tradeDB.sumTrades(date,status);
        tradeDB.close();
        return sum;
    }

    @Override
    public int sumByDateMarkups(String date, String markups, int status) {
        tradeDB.open();
        int sum = tradeDB.sumByDateMarkups(date, markups, status);
        tradeDB.close();
        return sum;
    }

    @Override
    public int sumByDateLocation(String date, String location, int status) {
        tradeDB.open();
        int sum = tradeDB.sumByDateLocation(date, location, status);
        tradeDB.close();
        return sum;
    }

    @Override
    public int sumByDateLocationMarkups(String date, String location, String markups, int status) {
        tradeDB.open();
        int sum = tradeDB.sumByDateLocationMarkups(date, location, markups, status);
        tradeDB.close();
        return sum;
    }

    @Override
    public List<Trade> searchByDateMarkups(String date, String markups, int status) {
        tradeDB.open();
        List<Trade> trades = tradeDB.searchByDateMarkups(date,markups,status);
        tradeDB.close();
        return trades;
    }

    @Override
    public List<Trade> searchByDateLocation(String date, String location, int status) {
        tradeDB.open();
        List<Trade> trades = tradeDB.searchByDateLocation(date, location, status);
        tradeDB.close();
        return trades;
    }

    @Override
    public List<Trade> searchByDateLocationMarkups(String date, String location, String markups, int status) {
        tradeDB.open();
        List<Trade> trades = tradeDB.searchByDateLocationMarkups(date, location, markups, status);
        tradeDB.close();
        return trades;
    }

    @Override
    public List<Trade> searchAll(int status) {
        tradeDB.open();
        List<Trade> trades = tradeDB.searchAll(status);
        tradeDB.close();
        return trades;
    }
}
