package ipc.gev.localwallet.db.repository.functions;


import java.util.List;

import ipc.gev.localwallet.db.entity.Trade;

public interface Trade_I {
    void createTrade(Trade trade);
    List<Trade> searchByDate(String date, int status);
    void deleteTradeByID(long id);
    void updateTradeByID(long id,String text,String location,int price,String date);
    List<Trade> searchByDateMarkups(String date,String markups,int status);
    List<Trade> searchByDateLocation(String date,String location,int status);
    List<Trade> searchByDateLocationMarkups(String date,String location,String markups,int status);
    int sumTrades(String date, int status);
    int sumByDateMarkups(String date,String markups,int status);
    int sumByDateLocation(String date,String location,int status);
    int sumByDateLocationMarkups(String date,String location,String markups,int status);
}
