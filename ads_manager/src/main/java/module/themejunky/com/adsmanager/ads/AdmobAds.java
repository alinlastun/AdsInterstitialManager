package module.themejunky.com.adsmanager.ads;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.List;

import module.themejunky.com.adsmanager.util.ListenerAds;
import module.themejunky.com.adsmanager.util.ListenerLogs;


/**
 * Created by Alin on 4/24/2017.
 */

public class AdmobAds  {


    private final ListenerLogs listenerLogs;
    public InterstitialAd interstitialAdmob;
    private static AdmobAds mInstance = null;
    public static String adUnitId;
    private List<String> logs = new ArrayList<>();
    private ListenerAds listenerAds;

    public AdmobAds(ListenerLogs listenerLogs){
        this.listenerLogs = listenerLogs;
    }


    public void initAdmobInterstitial(Context context, String adUnitId, final ListenerAds listenerAds) {
        this.listenerAds = listenerAds;
        interstitialAdmob = new com.google.android.gms.ads.InterstitialAd(context);
        if (adUnitId != null) {
            interstitialAdmob.setAdUnitId(adUnitId);
            listenerLogs.logs("Admob: initialized");
            AdListener adListener = new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    interstitialAdmob.loadAd(new AdRequest.Builder().addTestDevice("74df1a5b43f90b50dd8ea33699814380").build());
                    listenerLogs.logs("Admob: Closed");
                    listenerLogs.isClosedAd();
                }

                @Override
                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    listenerLogs.logs("Admob: Failed To Load");
                    listenerAds.loadeFailed();
                }

                @Override
                public void onAdLeftApplication() {
                    super.onAdLeftApplication();
                    listenerLogs.logs("Admob: Lef Application");
                }

                @Override
                public void onAdOpened() {
                    super.onAdOpened();
                    listenerLogs.logs("Admob: Opened");

                }

                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    listenerLogs.logs("Admob: is Loaded");
                    listenerAds.loadedAds();

                }
            };
            interstitialAdmob.setAdListener(adListener);
            requestNewInterstitial(null);
        }

    }

    public boolean isLoadedAdmob() {
        if (interstitialAdmob!=null && interstitialAdmob.isLoaded()) {
            return true;
        } else {
            return false;
        }
    }

    public void showAdmobAds() {
        if (interstitialAdmob!=null && interstitialAdmob.isLoaded()) {
            interstitialAdmob.show();
        }
    }


    public void requestNewInterstitial(String testDeviceId) {
        AdRequest adRequest;
        if (testDeviceId != null)
            adRequest = new AdRequest.Builder()
                    .addTestDevice(testDeviceId)
                    .build();
        else
            adRequest = new AdRequest.Builder()
                    .build();
        interstitialAdmob.loadAd(adRequest);
    }

    public synchronized static AdmobAds getmInstance(ListenerLogs listenerLogs) {
        if (mInstance == null) mInstance = new AdmobAds(listenerLogs);
        return mInstance;

    }


}
