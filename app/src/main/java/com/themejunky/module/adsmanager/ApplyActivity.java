package com.themejunky.module.adsmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import module.themejunky.com.adsmanager.Module_AdsManager;
import module.themejunky.com.adsmanager.util.ListenerAds;

public class ApplyActivity extends AppCompatActivity implements ListenerAds {

    private Module_AdsManager myMM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);

        myMM = ((MainApplication) getApplication()).Mam;
        myMM.setListenerAds(this);
   }


    @Override
    public void onBackPressed() {
        ((MainApplication)getApplication()).Mam.setFlowAndShowAds(((MainApplication)getApplication()).flowAdsSfarsit,"NADA_APPLY");
    }

    @Override
    public void afterAdIsClosed(String action) {
        Log.d("asdadadasds",""+action);

        if (action.equals("NADA_APPLY")) {
            super.onBackPressed();
        }

    }

    @Override
    public void loadedAds() {

    }

    @Override
    public void loadeFailed() {

    }
}
