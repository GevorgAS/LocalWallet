package ipc.gev.localwallet.db.table;


public class TradeTable {
    public static final String TABLE_NAME = "trade";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MARKUPS = "name";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_STATUS = "status";


    public static final String CREATE_TABLE = "create table "+TABLE_NAME+" ( " +
            COLUMN_ID+" integer primary key autoincrement ," +
            COLUMN_MARKUPS +" text , "+ COLUMN_LOCATION+" text, " +
            COLUMN_PRICE+ " integer , " + COLUMN_DATE + " text , " + COLUMN_STATUS+" int ) ; ";
    public static final String[] allColumns(){
        return new String[]{COLUMN_ID, COLUMN_MARKUPS,COLUMN_LOCATION,COLUMN_PRICE,COLUMN_DATE,COLUMN_STATUS};
    }
}
