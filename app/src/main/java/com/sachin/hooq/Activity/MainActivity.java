package com.sachin.hooq.Activity;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.sachin.hooq.Base.Presenter;
import com.sachin.hooq.Base.ResponseInterface;
import com.sachin.hooq.Controller.MainApplication;
import com.sachin.hooq.Fragment.DetailFragment;
import com.sachin.hooq.Fragment.HomeFragment;
import com.sachin.hooq.Fragment.SplashFragment;
import com.sachin.hooq.R;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements ResponseInterface.view {

    private Context mcontext;
    public static boolean AppInForeground;
    private Stack<Fragment> fragmentStack;
    private FrameLayout frameLayoutContainer;
    private SplashFragment splashFragment;
    private HomeFragment homeFragment;
    private DetailFragment detailFragment;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mcontext = this;
        setContentView(R.layout.activity_main);

        //Initialization
        fragmentStack = new Stack<Fragment>();
        frameLayoutContainer = (FrameLayout) findViewById(R.id.container);

        //LoadSplash
        loadSplashPage();
    }

    private void loadSplashPage() {
        splashFragment = new SplashFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(frameLayoutContainer.getId(), splashFragment);
        if (fragmentStack.size() > 0) {
            fragmentStack.lastElement().onPause();
            ft.hide(fragmentStack.lastElement());
        }
        fragmentStack.push(splashFragment);
        ft.commitAllowingStateLoss();
    }


    public void loadHomePage() {
        fragmentStack.clear();
        homeFragment = new HomeFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.hold);
        ft.add(frameLayoutContainer.getId(), homeFragment);
        if (fragmentStack.size() > 0) {
            fragmentStack.lastElement().onPause();
            ft.hide(fragmentStack.lastElement());
        }
        fragmentStack.push(homeFragment);
        ft.commitAllowingStateLoss();
    }

    private void loadDetailPage(String key) {
        detailFragment = DetailFragment.newInstance(key);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.hold);
        ft.add(frameLayoutContainer.getId(), detailFragment);
        if (fragmentStack.size() > 0) {
            fragmentStack.lastElement().onPause();
            ft.hide(fragmentStack.lastElement());
        }
        fragmentStack.push(detailFragment);
        ft.commitAllowingStateLoss();
    }


    @Override
    protected void onResume() {
        super.onResume();
        AppInForeground = true;
        MainApplication.getDBinstance();

    }

    @Override
    protected void onPause() {
        super.onPause();
        AppInForeground = false;
        MainApplication.destroyInstance();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finishAffinity();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(mcontext, "Click again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    public void displayDialogError(Throwable throwable) {

    }

    @Override
    public void sendResult(Object jsonObject) {

    }
}
