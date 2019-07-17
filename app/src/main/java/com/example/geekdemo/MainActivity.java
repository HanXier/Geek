package com.example.geekdemo;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.geekdemo.base.BaseActivity;
import com.example.geekdemo.fragment.AboutFragment;
import com.example.geekdemo.fragment.CollectionFragment;
import com.example.geekdemo.fragment.GankMainFragment;
import com.example.geekdemo.fragment.GoldMainFragment;
import com.example.geekdemo.fragment.VtexMainFragment;
import com.example.geekdemo.fragment.WeChatFragment;
import com.example.geekdemo.fragment.ZhiHuMainFragment;
import com.example.geekdemo.utils.Constants;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    private static final String TAG = "MainActivity";
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.view_search)
    MaterialSearchView viewSearch;
    @BindView(R.id.toolbar_container)
    FrameLayout toolbarContainer;
    @BindView(R.id.fl_main_content)
    FrameLayout flMainContent;
    @BindView(R.id.navigation)
    NavigationView navigation;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    int showType = Constants.TYPE_ZHIHU;
    Fragment currFragment;
    private FragmentTransaction fragmentTransaction;
    private ZhiHuMainFragment zhiHuMainFragment;
    private WeChatFragment weChatFragment;
    private MenuItem lastItem;
    private AboutFragment mAboutFragment;
    private GankMainFragment gankMainFragment;
    private MenuItem mSearchMenuItem;
    private GoldMainFragment goldMainFragment;
    private VtexMainFragment vtexMainFragment;
    private CollectionFragment collectionFragment;

    @Override
    protected void initMvp() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_item, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.setVisible(false);
        viewSearch.setMenuItem(item);
        mSearchMenuItem = item;

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void initView() {
        super.initView();

        toolBar.setTitle(R.string.app_name);
        setSupportActionBar(toolBar);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolBar, R.string.drawer_open, R.string.drawer_close);
        actionBarDrawerToggle.syncState();
        drawer.addDrawerListener(actionBarDrawerToggle);
//______________________toolbar  drawer 关联____________________________________________

        viewSearch.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Log.d(TAG, "onQueryTextSubmit: " + query);
                // 根据不同action来区分

                Intent intent = new Intent();

                intent.setAction("com.gank.search");
                intent.putExtra("data", query);
                LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(intent);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        //_____________________搜索__________________________________________


        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (fragmentTransaction != null) {
            zhiHuMainFragment = new ZhiHuMainFragment();
            weChatFragment = new WeChatFragment();
            gankMainFragment = new GankMainFragment();
            goldMainFragment = new GoldMainFragment();

            vtexMainFragment = new VtexMainFragment();
            collectionFragment = new CollectionFragment();

            mAboutFragment = new AboutFragment();

            currFragment = zhiHuMainFragment;


            fragmentTransaction.add(R.id.fl_main_content, zhiHuMainFragment);

            fragmentTransaction.show(zhiHuMainFragment);
            fragmentTransaction.commit();
        }

        lastItem = navigation.getMenu().findItem(R.id.drawer_zhihu);

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    case R.id.drawer_zhihu:
                        showType = Constants.TYPE_ZHIHU;
                        // 获取要切换的fragment
                        Fragment targetFragment = getTargetFragment(showType);
                        switchFragmet(targetFragment);
                        mSearchMenuItem.setVisible(false);
                        break;

                    case R.id.drawer_wechat:
                        showType = Constants.TYPE_WECHAT;
                        targetFragment = getTargetFragment(showType);
                        switchFragmet(targetFragment);
                        mSearchMenuItem.setVisible(false);
                        break;
                    case R.id.drawer_gank:
                        showType = Constants.TYPE_GANK;
                        targetFragment = getTargetFragment(showType);
                        switchFragmet(targetFragment);
                        mSearchMenuItem.setVisible(true);
                        break;
                    case R.id.drawer_gold:
                        showType = Constants.TYPE_GOLD;
                        targetFragment = getTargetFragment(showType);
                        switchFragmet(targetFragment);
                        mSearchMenuItem.setVisible(true);
                        break;
                    case R.id.drawer_vtex:
                        showType = Constants.TYPE_VTEX;
                        targetFragment = getTargetFragment(showType);
                        switchFragmet(targetFragment);
                        mSearchMenuItem.setVisible(true);
                        break;


                    case R.id.drawer_like:
                        showType = Constants.TYPE_LIKE;
                        targetFragment = getTargetFragment(showType);
                        switchFragmet(targetFragment);
                        mSearchMenuItem.setVisible(true);
                        break;

                    case R.id.drawer_about:
                        showType = Constants.TYPE_ABOUT;
                        targetFragment = getTargetFragment(showType);
                        switchFragmet(targetFragment);
                        break;
                }
                if (lastItem != null) {
                    lastItem.setChecked(false);
                }

                menuItem.setChecked(true);
                lastItem = menuItem;

                drawer.closeDrawer(Gravity.LEFT);
                return false;


            }


        });
    }

    private void switchFragmet(Fragment targetFragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 目标对象没有加入过
        if (!targetFragment.isAdded()) {
            if (currFragment != null) {
                transaction.hide(currFragment);
                transaction.add(R.id.fl_main_content, targetFragment, targetFragment.getClass().getName());
            }
        } else {
            // 如果目标fragment 已经在事务里，显示即可
            transaction.hide(currFragment).show(targetFragment);
        }
        currFragment = targetFragment;
        transaction.commit();

    }

    /**
     * 返回 即将要切换的fragment
     *
     * @param item
     * @return
     */
    private Fragment getTargetFragment(int item) {
        switch (item) {
            case Constants.TYPE_ZHIHU:
                return zhiHuMainFragment;
            case Constants.TYPE_GANK:
                return gankMainFragment;
            case Constants.TYPE_WECHAT:
                return weChatFragment;
            case Constants.TYPE_GOLD:
                return goldMainFragment;
            case Constants.TYPE_VTEX:
                return vtexMainFragment;
            case Constants.TYPE_LIKE:
                return collectionFragment;
//            case Constants.TYPE_SETTING:
//                return mSettingFragment;
            case Constants.TYPE_ABOUT:
                return mAboutFragment;
        }
        return zhiHuMainFragment;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }


}
