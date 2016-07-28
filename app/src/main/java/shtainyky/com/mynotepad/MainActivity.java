package shtainyky.com.mynotepad;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import shtainyky.com.mynotepad.Adapter.TabPagerFragmentAdapter;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private static ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initTabs();
        initIconsForTabs();
    }

    private void initIconsForTabs() {
        TabLayout.Tab tab_0 = tabLayout.getTabAt(0);
        TabLayout.Tab tab_1 = tabLayout.getTabAt(1);

        if (tab_0 != null)
            tab_0.setIcon(R.mipmap.ic_calendar_plus);
        if (tab_1 != null)
            tab_1.setIcon(R.mipmap.ic_calendar_check);
    }

    private void initTabs() {
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        TabPagerFragmentAdapter adapter = new TabPagerFragmentAdapter(this,getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        toolbar.setTitle(R.string.app_name);
        toolbar.inflateMenu(R.menu.menu);
        toolbar.setNavigationIcon(R.mipmap.ic_book_open_page_variant);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                finish();
                return false;
            }
        });
    }
    public static void showCreatingTab(int currentTab)
    {
        viewPager.setCurrentItem(currentTab);
    }
}
