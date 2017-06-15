package ipc.gev.localwallet.Activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import ipc.gev.localwallet.Fragments.ExpenseFragment;
import ipc.gev.localwallet.Fragments.IncomeFragment;
import ipc.gev.localwallet.Fragments.SearchFragment;
import ipc.gev.localwallet.R;


public class MainActivity extends AppCompatActivity{


    ViewPager viewPager;
    TabLayout tabLayout;


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


    private class WalletPagerAdapter extends FragmentStatePagerAdapter {
        private final List<String> mFragmentTitleList = new ArrayList<>();
        private final List<Fragment> mFragmentList = new ArrayList<>();

        private WalletPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        private void addFragment(Fragment fragment, String title) {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.languages:
                Intent lang_intent = new Intent(MainActivity.this,LanguagesActivity.class);
                startActivity(lang_intent);
                break;
            case R.id.about:
                Intent about_intent = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(about_intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
