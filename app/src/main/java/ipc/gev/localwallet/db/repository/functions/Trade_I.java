package ipc.gev.localwallet.db.repository.functions;


import java.util.List;

import ipc.gev.localwallet.db.entity.Trade;

public interface Trade_I {
    void createTrade(Trade trade);
    List<Trade> searchByDate(String date, int status);
    List<Trade> searchByMarkups(String markups,int status);
    List<Trade> searchByLocation(String location,int status);
    void deleteTradeByID(long id);
    void updateTradeByID(long id,String text,String location,int price,String date);
    int sumTrades(String date, int status);
}
