package com.themejunky.module.adsmanager;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import module.themejunky.com.adsmanager.Module_AdsManager;

/**
 * Created by Junky2 on 8/2/2017.
 */

public class MainApplication extends Application {
    public List<String> flowAdsInceput = new ArrayList<>();
    public List<String> flowAdsSfarsit = new ArrayList<>();
    public Module_AdsManager Mam;
    @Override
    public void onCreate() {
        super.onCreate();
        flowAdsInceput.add("facebook");
        flowAdsInceput.add("appnext");
        flowAdsInceput.add("admob");

        flowAdsSfarsit.add("facebook");
        flowAdsSfarsit.add("admob");
        flowAdsSfarsit.add("appnext");




    }
}
