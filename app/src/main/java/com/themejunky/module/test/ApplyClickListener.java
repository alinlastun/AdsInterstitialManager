package com.themejunky.module.test;

import android.app.Activity;
import android.view.View;

/**
 * Created by Junky2 on 8/2/2017.
 */

public class ApplyClickListener implements View.OnClickListener {

    private String ceva;
    private String motherType;

    public ApplyClickListener(Activity activity, String motherType) {

    }

    @Override
    public void onClick(View v) {
       switch (motherType) {
          // case "keyboard"
       }
    }
}
