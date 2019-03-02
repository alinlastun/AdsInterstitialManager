/*
package module.themejunky.com.adsmanager.ads;

import android.app.Activity;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;


import module.themejunky.com.adsmanager.util.ListenerAds;
import module.themejunky.com.adsmanager.util.ListenerLogs;


*/
/**
 * Created by Junky2 on 4/28/2017.
 *//*


public class FacebookAds  {

    private ListenerLogs listenerLogs;
    public static FacebookAds mInstance = null;
    private InterstitialAd interstitialAd;
    private ListenerAds listenerAds;

    public FacebookAds(ListenerLogs listenerLogs){
        this.listenerLogs = listenerLogs;
    }



    public void initFacebookInterstitial(Activity activity, String keyFacebook, final ListenerAds listenerAds) {
        this.listenerAds = listenerAds;
        AdSettings.addTestDevice("f755429e799a9291a0e305d065db6326");

        interstitialAd = new InterstitialAd(activity, keyFacebook);
        listenerLogs.logs("Facebook:  initialized");
        interstitialAd.setAdListener(new InterstitialAdListener() {

            @Override
            public void onInterstitialDisplayed(Ad ad) {
                listenerLogs.logs("Facebook: displayed!");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                interstitialAd.loadAd();
                listenerLogs.logs("Facebook : dismissed!");
                listenerLogs.isClosedAd();
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                listenerLogs.logs("Faceboook error: "+ adError.getErrorMessage());
                listenerAds.loadeFailed();

            }

            @Override
            public void onAdLoaded(Ad ad) {
                listenerAds.loadedAds();
                listenerLogs.logs("Faceboook: is Loaded");
            }

            @Override
            public void onAdClicked(Ad ad) {
                listenerLogs.logs("Faceboook: clicked");
            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        });
        interstitialAd.loadAd();

    }

    public  void showInterstitialFacebook() {

        if (interstitialAd !=null && interstitialAd.isAdLoaded()) {
            interstitialAd.show();
            listenerLogs.logs("Faceboook: is shown");
        } else {
            listenerLogs.logs("Faceboook: show failed");


        }

    }
    public boolean isFacebookLoaded(){
        if(interstitialAd!=null && interstitialAd.isAdLoaded()){
            return true;
        }else {
            return false;
        }
    }

    public synchronized static FacebookAds getmInstance(ListenerLogs listenerLogs) {
        if (mInstance == null) mInstance = new FacebookAds(listenerLogs);
        return mInstance;
    }



}
*/
