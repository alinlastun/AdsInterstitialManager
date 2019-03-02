package com.themejunky.module.adsmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import module.themejunky.com.adsmanager.Module_AdsManager;
import module.themejunky.com.adsmanager.util.Action;
import module.themejunky.com.adsmanager.util.ListenerAds;

public class MainActivity extends AppCompatActivity implements ListenerAds,View.OnClickListener {

    public Module_AdsManager mam;
    private Button apply,rate,getMore;
    private View viewButtons;
    private ImageView splash;
    private LinearLayout layoutButtons;
    private byte nrFailedLoad=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apply = (Button) findViewById(R.id.applyid);
        rate = (Button) findViewById(R.id.rateId);
        getMore = (Button) findViewById(R.id.getmoreId);
        splash = (ImageView)findViewById(R.id.loadingId);
        layoutButtons = (LinearLayout) findViewById(R.id.layoutButonsId);

        Log.d("TestLogs2","onCreate");

        apply.setOnClickListener(this);
        rate.setOnClickListener(this);
        getMore.setOnClickListener(this);

        mam = Module_AdsManager.getInstance(this,this,false);
        ((MainApplication) getApplication()).Mam = mam;
        mam.setLog("TestLogs");
        mam.initInterstitialAdmobAds("ca-app-pub-5322508131338449/2877444211");
        //mam.initInterstitialFacebookAds("698838770248387_848026318662964");
        mam.initInterstitialAppNextAds("8106d659-a20b-4640-943b-d6b0aab18d08");

    }
/*
    public void OpenApplication(View view) {
        mam.showAds();
    }*/

    @Override
    public void afterAdIsClosed(String action) {

        switch (action){
            case Action.APPLY:
               startActivity(new Intent(this,ApplyActivity.class));
                break;
            case "rate":
                Toast.makeText(this, "Rate", Toast.LENGTH_SHORT).show();
                break;
            case "more":
                Toast.makeText(this, "GetMore", Toast.LENGTH_SHORT).show();
                break;
            case "back":

                break;

        }


    }


    @Override
    public void loadedAds() {
        Log.d("TestLogs","loadedAds");

        Log.d("TestLogs","loadedAds: "+splash.getVisibility());

        if (splash.getVisibility()==View.VISIBLE ) {
            Log.d("TestLogs","intra in metoda loaded");
            Log.d("loadereee","dispari");
            splash.setVisibility(View.GONE);
            layoutButtons.setVisibility(View.VISIBLE);

           // showAds(this,"in");

            mam.setFlowAndShowAds(((MainApplication)getApplication()).flowAdsInceput,"NADA");

        } else {
            Log.d("loadereee","ai disparut");
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d("TestLogs2","onResume : "+splash.getVisibility());
    //    splash.setVisibility(View.GONE);
     //   Log.d("TestLogs2","onResume : "+splash.getVisibility());
    }

    @Override
    public void loadeFailed() {
        Log.d("TestLogs","loadeFailed");
        Log.d("TestLogs", "" +(View.GONE));
        Log.d("TestLogs", String.valueOf(splash.getVisibility()));
        nrFailedLoad++;
        if(nrFailedLoad==3){
            Log.d("TestLogs","inra in metoda faild 1");
            splash.setVisibility(View.GONE);
            Log.d("TestLogs","inra in metoda faild 2");
            layoutButtons.setVisibility(View.VISIBLE);
            layoutButtons.bringToFront();
            Log.d("TestLogs","inra in metoda faild 3");



            nrFailedLoad=0;
        }
        Log.d("TestLogs", "nr: "+String.valueOf(nrFailedLoad));

    }



//
//        nrLoaded++;
//            splash.setVisibility(View.GONE);
//            layoutButtons.setVisibility(View.VISIBLE);
//        if(nrLoaded==1){
//            mam.setFlow(true,((MainApplication)getApplication()).flowAdsInceput);
//            mam.setAction("continue");
//        }


/*
    Boolean superPower;
    public void showAds(Activity activity,String inOrOut) {
        if (!superPower) {
            return;
        } else {


            if (activity!=null) {

                String witchActivity;

                if (activity instanceof MainActivity) {

                    witchActivity = "main";

                } else if (activity instanceof ApplyActivity) {
                    witchActivity = "apply";
                }


                if ( DataBase.findFlowByActivityAndFlat(witchActivity,inOrOut,true)==0) {
                    return;
                }
                else {
                    mam.setFlow(((MainApplication)getApplication()).flowAdsInceput);

                }
            }
        }

    }*/

    @Override
    public void onClick(View v) {
        viewButtons = v;
        switch (viewButtons.getId()) {
            case R.id.applyid:
                if(mam.isAdLoaded()){
                    Log.d("TestButton", "1");
                  mam.setFlowAndShowAds(((MainApplication)getApplication()).flowAdsInceput,"apply");
                    Log.d("TestButton", "2");
                }else {
                    Log.d("TestButton", "3");
                    Toast.makeText(this, "Apply", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rateId:
                if(mam.isAdLoaded()){
                    mam.setFlowAndShowAds(((MainApplication)getApplication()).flowAdsInceput,"rate");
                   // mam.setAction("rate");
                }else {
                    Toast.makeText(this, "Rate", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.getmoreId:
                if(mam.isAdLoaded()){
                    mam.setFlowAndShowAds(((MainApplication)getApplication()).flowAdsInceput,"more");
                   // mam.setAction("more");
                }else {
                    Toast.makeText(this, "GetMore", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}
