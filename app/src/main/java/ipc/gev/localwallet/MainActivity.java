package ipc.gev.localwallet;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

import java.util.ArrayList;
import java.util.List;

import ipc.gev.localwallet.Fragments.ExpenseFragment;
import ipc.gev.localwallet.Fragments.IncomeFragment;
import ipc.gev.localwallet.Fragments.SearchFragment;


public class MainActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.income_icon);
        tabLayout.getTabAt(1).setIcon(R.drawable.expense_icon);
        tabLayout.getTabAt(2).setIcon(R.drawable.search_icon);

    }
    private void setupViewPager(ViewPager viewPager) {
        WalletPagerAdapter adapter = new WalletPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new IncomeFragment(),getString(R.string.income));
        adapter.addFragment(new ExpenseFragment(),getString(R.string.expense));
        adapter.addFragment(new SearchFragment(),getString(R.string.search));
        viewPager.setAdapter(adapter);
    }

    class WalletPagerAdapter extends FragmentStatePagerAdapter {
        private final List<String> mFragmentTitleList = new ArrayList<>();
        private final List<Fragment> mFragmentList = new ArrayList<>();

        public WalletPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);


        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
             return mFragmentTitleList.get(position);
        }
    }

}
