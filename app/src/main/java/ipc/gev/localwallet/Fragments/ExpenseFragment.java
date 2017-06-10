package ipc.gev.localwallet.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import ipc.gev.localwallet.R;


public class ExpenseFragment extends Fragment implements View.OnClickListener{
    private RelativeLayout layout;
    private Button save;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.expense_fragment,container,false);
        save = (Button) view.findViewById(R.id.save_expanse);
        save.setOnClickListener(this);
        layout = (RelativeLayout) view.findViewById(R.id.main_expense_layout);
        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.save_expanse:
                Snackbar snackbar = Snackbar
                        .make(layout, "Welcome to AndroidHive", Snackbar.LENGTH_LONG);

                snackbar.show();
                break;

            case R.id.expense_date:

                break;

        }
    }
}


