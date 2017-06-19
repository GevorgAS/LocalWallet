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
    public void updateTradeByID(long id, String text, String location, long price, String date) {
        tradeDB.open();
        tradeDB.updateTradeByID(id,text,location,price,date);
        tradeDB.close();
    }

    @Override
    public long sumByDate(String date, int status) {
        tradeDB.open();
        long sum = tradeDB.sumByDate(date,status);
        tradeDB.close();
        return sum;
    }

    @Override
    public long sumAll(int status) {
        tradeDB.open();
        long sum = tradeDB.sumAll(status);
        tradeDB.close();
        return sum;
    }

    @Override
    public long sumByMarkups(String markups, int status) {
        tradeDB.open();
        long sum = tradeDB.sumByMarkups(markups, status);
        tradeDB.close();
        return sum;
    }

    @Override
    public long sumByLocation(String location, int status) {
        tradeDB.open();
        long sum = tradeDB.sumByLocation(location, status);
        tradeDB.close();
        return sum;
    }

    @Override
    public long sumByMarkupsLocation(String location, String markups, int status) {
        tradeDB.open();
        long sum = tradeDB.sumByMarkupsLocation(location, markups, status);
        tradeDB.close();
        return sum;
    }

    @Override
    public long sumByDateMarkups(String date, String markups, int status) {
        tradeDB.open();
        long sum = tradeDB.sumByDateMarkups(date, markups, status);
        tradeDB.close();
        return sum;
    }

    @Override
    public long sumByDateLocation(String date, String location, int status) {
        tradeDB.open();
        long sum = tradeDB.sumByDateLocation(date, location, status);
        tradeDB.close();
        return sum;
    }

    @Override
    public long sumByDateLocationMarkups(String date, String location, String markups, int status) {
        tradeDB.open();
        long sum = tradeDB.sumByDateLocationMarkups(date, location, markups, status);
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
    public List<Trade> searchByMarkups(String markups, int status) {
        tradeDB.open();
        List<Trade> trades = tradeDB.searchByMarkups(markups, status);
        tradeDB.close();
        return trades;
    }

    @Override
    public List<Trade> searchByLocation(String location, int status) {
        tradeDB.open();
        List<Trade> trades = tradeDB.searchByLocation(location, status);
        tradeDB.close();
        return trades;
    }

    @Override
    public List<Trade> searchByMarkupsLocation(String location, String markups, int status) {
        tradeDB.open();
        List<Trade> trades = tradeDB.searchByMarkupsLocation(location, markups, status);
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

    @Override
    public List<Trade> getAll() {
        tradeDB.open();
        List<Trade> trades = tradeDB.getAll();
        tradeDB.close();
        return trades;
    }

    @Override
    public List<Trade> getAllByDate(String date) {
        tradeDB.open();
        List<Trade> trades = tradeDB.getAllByDate(date);
        tradeDB.close();
        return trades;
    }

    @Override
    public List<Trade> getAllByDateMarkups(String date, String markups) {
        tradeDB.open();
        List<Trade> trades = tradeDB.getAllByDateMarkups(date, markups);
        tradeDB.close();
        return trades;
    }

    @Override
    public List<Trade> getAllByDateLocation(String date, String location) {
        tradeDB.open();
        List<Trade> trades = tradeDB.getAllByDateLocation(date, location);
        tradeDB.close();
        return trades;
    }

    @Override
    public List<Trade> getAllByDateLocationMarkups(String date, String location, String markups) {
        tradeDB.open();
        List<Trade> trades = tradeDB.getAllByDateLocationMarkups(date, location, markups);
        tradeDB.close();
        return trades;
    }

    @Override
    public List<Trade> getAllByMarkups(String markups) {
        tradeDB.open();
        List<Trade> trades = tradeDB.getAllByMarkups(markups);
        tradeDB.close();
        return trades;
    }

    @Override
    public List<Trade> getAllByLocation(String location) {
        tradeDB.open();
        List<Trade> trades = tradeDB.getAllByLocation(location);
        tradeDB.close();
        return trades;
    }

    @Override
    public List<Trade> getAllByLocationMarkups(String location, String markups) {
        tradeDB.open();
        List<Trade> trades = tradeDB.getAllByLocationMarkups(location, markups);
        tradeDB.close();
        return trades;
    }
}
