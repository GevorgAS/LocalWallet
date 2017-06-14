package ipc.gev.localwallet.db.repository.functions;


import java.util.List;

import ipc.gev.localwallet.db.entity.Trade;

public interface Trade_I {
    void createTrade(Trade trade);
    void deleteTradeByID(long id);
    void updateTradeByID(long id,String text,String location,int price,String date);
    List<Trade> searchByDate(String date, int status);
    List<Trade> searchByDateMarkups(String date,String markups,int status);
    List<Trade> searchByDateLocation(String date,String location,int status);
    List<Trade> searchByDateLocationMarkups(String date,String location,String markups,int status);
    List<Trade> searchByMarkups(String markups,int status);
    List<Trade> searchByLocation(String location,int status);
    List<Trade> searchByMarkupsLocation(String location,String markups,int status);
    List<Trade> searchAll(int status);
    int sumByDate(String date, int status);
    int sumAll(int status);
    int sumByMarkups(String markups,int status);
    int sumByLocation(String location,int status);
    int sumByMarkupsLocation(String location,String markups,int status);
    int sumByDateMarkups(String date,String markups,int status);
    int sumByDateLocation(String date,String location,int status);
    int sumByDateLocationMarkups(String date,String location,String markups,int status);
}
