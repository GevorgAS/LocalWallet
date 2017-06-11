package ipc.gev.localwallet.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import ipc.gev.localwallet.R;


public class SearchFragment extends Fragment implements View.OnClickListener {
    private Button searchDate, searchLocation, searchMarkups, searchbt;
    private EditText searchByDate, searchByLocation, searchByMarkups;
    private boolean buttonclick = false;
    private BottomSheetBehavior bottomSheetBehavior;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        searchDate = (Button) view.findViewById(R.id.search_date);
        View bottomSheet =view.findViewById( R.id.bottom_sheet );
        searchLocation = (Button) view.findViewById(R.id.search_location);
        searchMarkups = (Button) view.findViewById(R.id.search_markups);
        searchByDate = (EditText) view.findViewById(R.id.search_by_date_ed);
        searchByLocation = (EditText) view.findViewById(R.id.search_by_location_ed);
        searchByMarkups = (EditText) view.findViewById(R.id.search_by_markups_ed);
        searchbt = (Button) view.findViewById(R.id.search_bt);

        searchLocation.setOnClickListener(this);
        searchMarkups.setOnClickListener(this);
        searchbt.setOnClickListener(this);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottominit();


        return view;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();


        switch (id) {
            case R.id.search_date:

                break;
            case R.id.search_location:
                if (!buttonclick) {
                    searchLocation.setBackgroundResource(R.drawable.button_shape);
                    searchLocation.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    searchByLocation.setVisibility(View.VISIBLE);
                    buttonclick = true;

                } else if (buttonclick) {
                    searchLocation.setBackgroundResource(R.drawable.button_shape_two);
                    searchLocation.setTextColor(getResources().getColor(R.color.colorPrimary));
                    searchByLocation.setVisibility(View.GONE);
                    buttonclick = false;
                }
                break;
            case R.id.search_markups:
                if (!buttonclick) {
                    searchMarkups.setBackgroundResource(R.drawable.button_shape);
                    searchMarkups.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    searchByMarkups.setVisibility(View.VISIBLE);
                    buttonclick = true;
                } else if (buttonclick) {
                    searchMarkups.setBackgroundResource(R.drawable.button_shape_two);
                    searchMarkups.setTextColor(getResources().getColor(R.color.colorPrimary));
                    searchByMarkups.setVisibility(View.GONE);
                    buttonclick = false;
                }
                break;
            case R.id.search_bt:
                Toast.makeText(getContext(), "asdadad", Toast.LENGTH_LONG).show();
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;

        }

    }
    private void bottominit() {
        bottomSheetBehavior.setPeekHeight(0);


        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.setPeekHeight(0);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }


}
