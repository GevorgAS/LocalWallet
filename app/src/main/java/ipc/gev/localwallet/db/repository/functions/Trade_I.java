package ipc.gev.localwallet.db.repository.functions;


import java.util.List;

import ipc.gev.localwallet.db.entity.Trade;

public interface Trade_I {
    void createTrade(Trade trade);
    void deleteTradeByID(long id);
    void updateTradeByID(long id,String text,String location,long price,String date);
    List<Trade> searchByDate(String date, int status);
    List<Trade> searchByDateMarkups(String date,String markups,int status);
    List<Trade> searchByDateLocation(String date,String location,int status);
    List<Trade> searchByDateLocationMarkups(String date,String location,String markups,int status);
    List<Trade> searchByMarkups(String markups,int status);
    List<Trade> searchByLocation(String location,int status);
    List<Trade> searchByMarkupsLocation(String location,String markups,int status);
    List<Trade> searchAll(int status);
    List<Trade> getAll();
    List<Trade> getAllByDate(String date);
    List<Trade> getAllByDateMarkups(String date,String markups);
    List<Trade> getAllByDateLocation(String date,String location);
    List<Trade> getAllByDateLocationMarkups(String date,String location,String markups);
    List<Trade> getAllByMarkups(String markups);
    List<Trade> getAllByLocation(String location);
    List<Trade> getAllByLocationMarkups(String location,String markups);




    long sumByDate(String date, int status);
    long sumAll(int status);
    long sumByMarkups(String markups,int status);
    long sumByLocation(String location,int status);
    long sumByMarkupsLocation(String location,String markups,int status);
    long sumByDateMarkups(String date,String markups,int status);
    long sumByDateLocation(String date,String location,int status);
    long sumByDateLocationMarkups(String date,String location,String markups,int status);

}
