package ipc.gev.localwallet.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ipc.gev.localwallet.R;
import ipc.gev.localwallet.db.entity.Trade;

public class TradeAdapter extends BaseAdapter{
    private ArrayList<Trade> trades;
    private LayoutInflater inflater;

    public TradeAdapter(Context c, ArrayList<Trade> t){
        trades = t;
        inflater = (LayoutInflater.from(c));
    }
    @Override
    public int getCount() {
        return trades.size();
    }

    @Override
    public Object getItem(int position) {
        return trades.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class Holder{
        TextView price;
        TextView loc;
        TextView text;
        TextView date;
        ImageView image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.adapter_list_item,null);
        Holder holder = new Holder();
        holder.price = (TextView) view.findViewById(R.id.adapter_price);
        holder.loc = (TextView) view.findViewById(R.id.adapter_location);
        holder.text = (TextView) view.findViewById(R.id.adapter_markups);
        holder.date = (TextView) view.findViewById(R.id.adapter_date);
        holder.image = (ImageView) view.findViewById(R.id.image_icon);

        holder.price.setText(trades.get(position).getPrice()+"");
        holder.loc.setText(trades.get(position).getLocation());
        holder.text.setText(trades.get(position).getMarkups());
        holder.date.setText(trades.get(position).getDate());
        if (trades.get(position).getStatus()==Trade.INCOME){
            holder.image.setImageResource(R.drawable.income_icon);
        }else {
            holder.image.setImageResource(R.drawable.expense_icon);
        }

        return view;
    }
}