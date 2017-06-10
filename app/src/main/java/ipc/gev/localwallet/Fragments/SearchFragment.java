package ipc.gev.localwallet.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import ipc.gev.localwallet.R;


public class SearchFragment extends Fragment {
    private Spinner spinner;
    private ArrayAdapter arrayAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.search_fragment,container,false);
        spinner=(Spinner)view.findViewById(R.id.property_spinner);
        init();
        return view;
    }

    private void init(){
        arrayAdapter=ArrayAdapter.createFromResource(getContext(),R.array.propertes,R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }
}
