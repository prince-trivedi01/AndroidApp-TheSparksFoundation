package com.thesparksfoundation.sparksfoundation;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.thesparksfoundation.sparksfoundation.fragment.EducationalDetailsFragment;
import com.thesparksfoundation.sparksfoundation.fragment.PersonalDetailsFragment;
import com.thesparksfoundation.sparksfoundation.fragment.ProfessionalDetailsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    List<Fragment> mFragmentList = new ArrayList<>();
    List<String> mTitleFragmentList = new ArrayList<>();
    private SessionManager mSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ButterKnife.bind(this);
        mSessionManager = new SessionManager(getApplicationContext());

        prepareDataResource();

        CustomAdapter adapter = new CustomAdapter(getSupportFragmentManager(), mFragmentList, mTitleFragmentList);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void prepareDataResource() {
        addData(new PersonalDetailsFragment(), "Personal Details");
        addData(new EducationalDetailsFragment(), "Educational Details");
        addData(new ProfessionalDetailsFragment(), "Professional Details");
    }

    private void addData(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mTitleFragmentList.add(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_setting:
                return true;
            case R.id.menu_logout:
                mSessionManager.logoutUser();
                return true;
            case R.id.menu_notification:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
