package module.themejunky.com.adsmanager.ads;


import android.content.Context;
import com.appnext.ads.interstitial.Interstitial;
import com.appnext.ads.interstitial.InterstitialConfig;
import com.appnext.core.callbacks.OnAdClosed;
import com.appnext.core.callbacks.OnAdError;
import com.appnext.core.callbacks.OnAdLoaded;
import module.themejunky.com.adsmanager.util.ListenerAds;
import module.themejunky.com.adsmanager.util.ListenerLogs;


/**
 * Created by Alin on 5/21/2017.
 */

public class AppnextAds {


    public static AppnextAds instance = null;
    private final ListenerLogs listenerLogs;

    public Interstitial interstitialAppnext;


    public AppnextAds(ListenerLogs listenerLogs) {
        this.listenerLogs = listenerLogs;
    }

    //initAppnext(activity,"8106d659-a20b-4640-943b-d6b0aab18d08");
    public void initAppnext(Context context, String placementID, final ListenerAds listenerAds) {

        InterstitialConfig config = new InterstitialConfig();
        config.setBackButtonCanClose(true);
        config.setCreativeType(Interstitial.TYPE_MANAGED);
        interstitialAppnext = new Interstitial(context, placementID, config);
        listenerLogs.logs("Appnext: initialized");
        interstitialAppnext.setOnAdClosedCallback(new OnAdClosed() {
            @Override
            public void onAdClosed() {
                listenerLogs.logs("Appnext: Closed");
                listenerLogs.isClosedAd();
            }
        });
        interstitialAppnext.setOnAdErrorCallback(new OnAdError() {
            @Override
            public void adError(String s) {
                listenerLogs.logs("Appnext error: " + s.toString());
                listenerAds.loadeFailed();
            }
        });
        interstitialAppnext.setOnAdLoadedCallback(new OnAdLoaded() {
            @Override
            public void adLoaded(String s) {
                listenerAds.loadedAds();
                listenerLogs.logs("Appnext: is Loaded");
            }
        });

        interstitialAppnext.setMute(true);
        interstitialAppnext.setAutoPlay(true);
        interstitialAppnext.loadAd();
        interstitialAppnext.setMute(true);
        interstitialAppnext.setAutoPlay(true);

    }

    public void showAppNext() {
        if (interstitialAppnext != null && interstitialAppnext.isAdLoaded()) {
            interstitialAppnext.showAd();
        }
    }

    public boolean isLoadedAppNext() {
        if (interstitialAppnext != null && interstitialAppnext.isAdLoaded()) {
            return true;
        } else {
            return false;
        }
    }

    public synchronized static AppnextAds getInstance(ListenerLogs listenerLogs) {
        if (instance == null) instance = new AppnextAds(listenerLogs);
        return instance;
    }
}
