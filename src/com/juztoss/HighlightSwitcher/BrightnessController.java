package com.juztoss.HighlightSwitcher;

import android.app.Activity;
import android.provider.Settings;
import android.view.WindowManager;

public class BrightnessController {

    public static final int MAX_VALUE = 255;
    public static final int MIN_VALUE = 1;

    private Activity mActivity;
    private IBrightnessChangedListener mListener;

    public BrightnessController(Activity activity) {
        mActivity = activity;
    }

    public int getBrightness() {
        int value;
        try {
            value = Settings.System.getInt(mActivity.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            value = -1;
        }

        return value;
    }

    public void setBrightness(int value) {
        Settings.System.putInt(mActivity.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
        Settings.System.putInt(mActivity.getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS, value);

        WindowManager.LayoutParams layoutParams = mActivity.getWindow().getAttributes();
        layoutParams.screenBrightness = (float) value / (float) MAX_VALUE;
        mActivity.getWindow().setAttributes(layoutParams);
        if (mListener != null)
            mListener.onBrightnessChanged();
    }

    public void setListener(IBrightnessChangedListener listener) {
        mListener = listener;
    }
}
