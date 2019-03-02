package module.themejunky.com.adsmanager;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import module.themejunky.com.adsmanager.ads.AdmobAds;
import module.themejunky.com.adsmanager.ads.AppnextAds;

import module.themejunky.com.adsmanager.util.AddsConstants;
import module.themejunky.com.adsmanager.util.ListenerAds;
import module.themejunky.com.adsmanager.util.ListenerLogs;


/**
 * Created by Junky2 on 7/21/2017.
 */

public class Module_AdsManager implements ListenerLogs {

    public static Module_AdsManager mInstance = null;
    private final Activity activity;
    private String nameLog;
    private int next = 0;
    private static List<String> addsFlow = new ArrayList<>();


  //  private FacebookAds facebookAds;
    private AdmobAds admobAds;
    private AppnextAds appnextAds;
    private ListenerAds listenerAds;
    private String action;

    public Module_AdsManager(Activity activity,ListenerAds listenerAds,boolean isFacebook){
        this.activity = activity;
        this.listenerAds = listenerAds;
        if(isFacebook){
           // facebookAds = FacebookAds.getmInstance(this);
            admobAds = AdmobAds.getmInstance(this);
            appnextAds = AppnextAds.getInstance(this);
        }else {
            admobAds = AdmobAds.getmInstance(this);
            appnextAds = AppnextAds.getInstance(this);
        }

    }

    public synchronized static Module_AdsManager getInstance(Activity activity,ListenerAds listenerAds,boolean isFacebook) {
        if (mInstance == null) mInstance = new Module_AdsManager(activity,listenerAds,isFacebook);
        return mInstance;
    }

    public void setListenerAds(ListenerAds listenerAds) {
        this.listenerAds = listenerAds;
    }

   /* public void initInterstitialFacebookAds(String keyFacebook){
        if(keyFacebook!=null && !keyFacebook.equals("")){
            facebookAds.initFacebookInterstitial(activity,keyFacebook,listenerAds);
        }
    }*/
    public void initInterstitialAdmobAds(String keyAdmob){
        if(keyAdmob!=null && !keyAdmob.equals("")){
            admobAds.initAdmobInterstitial(activity,keyAdmob,listenerAds);
        }
    }
    public void initInterstitialAppNextAds(String keyAppNext){
        if(keyAppNext!=null && !keyAppNext.equals("")){
            appnextAds.initAppnext(activity,keyAppNext,listenerAds);
        }
    }
 /*   public void showInterstitialFacebook(){
        facebookAds.showInterstitialFacebook();
    }*/
    public void showInterstitialAdmob(){
        admobAds.showAdmobAds();
    }
    public void showInterstitialAppNext(){
        appnextAds.showAppNext();
    }
  /*  public boolean isLoadedFacebook(){
        return facebookAds.isFacebookLoaded();
    }*/
    public boolean isLoadedAdmob(){
       return admobAds.isLoadedAdmob();
    }
    public boolean isLoadedAppNext(){
        return appnextAds.isLoadedAppNext();
    }
    public boolean isAdLoaded(){
        return admobAds.isLoadedAdmob()||appnextAds.isLoadedAppNext();
    }


    public void setFlowAndShowAds(List<String> flowAds,String action){
        this.action = action;
        addsFlow=flowAds;
        runAdds_Part1();

    }

    public void setLog (String nameLog){
        this.nameLog= nameLog;
    }
    @Override
    public void logs(String logs) {
        Log.d(nameLog,logs);
    }

    @Override
    public void isClosedAd() {
            listenerAds.afterAdIsClosed(action);
    }

    private void runAdds_Part1() {
        this.next = -1;
        runAdds_Part2();

    }

    private void runAdds_Part2() {

        this.next++;
        if (next < addsFlow.size() && activity != null) {
            switch (addsFlow.get(next)) {

                case AddsConstants.ADMOB:
                    Log.d("TestLogs", "ADMOB 1");
                   if(admobAds.isLoadedAdmob()){
                       Log.d("TestLogs", "ADMOB 2");
                       admobAds.showAdmobAds();
                       Log.d("TestLogs", "ADMOB 3");
                   }else{
                       Log.d("TestLogs", "ADMOB 4");
                       runAdds_Part2();
                   }
                    break;
               /* case AddsConstants.FACEBOOK:
                    if(facebookAds!=null){
                        if(facebookAds.isFacebookLoaded()){
                            facebookAds.showInterstitialFacebook();
                        }else{
                            runAdds_Part2();
                        }
                    }else{
                        runAdds_Part2();
                    }

                    break;*/
                case AddsConstants.APPNEXT:
                    Log.d("TestLogs", "APPNEXT 1");
                    if(appnextAds.isLoadedAppNext()){
                        Log.d("TestLogs", "APPNEXT 2");
                        appnextAds.showAppNext();
                        Log.d("TestLogs", "APPNEXT 3");
                    }else{
                        Log.d("TestLogs", "APPNEXT 4");
                        runAdds_Part2();

                    }
                    break;
                default:
                    Log.d("TestLogs", "default ");
                    runAdds_Part2();
                    break;
            }
        }
    }


}
