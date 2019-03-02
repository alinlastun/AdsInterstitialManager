package module.themejunky.com.adsmanager.util;

/**
 * Created by Junky2 on 8/2/2017.
 */

public interface ListenerAds {
    
    void afterAdIsClosed(String action);
    void loadedAds();
    void loadeFailed();
}
