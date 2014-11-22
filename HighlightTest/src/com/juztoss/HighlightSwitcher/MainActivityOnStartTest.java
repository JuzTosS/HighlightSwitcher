package com.juztoss.HighlightSwitcher;

import android.provider.Settings;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.widget.SeekBar;
import android.widget.ToggleButton;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.juztoss.HighlightSwitcher.MainActivityTest \
 * com.juztoss.HighlightSwitcher.tests/android.test.InstrumentationTestRunner
 */
public class MainActivityOnStartTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mActivity;
    private SeekBar mSeekBar;

    public MainActivityOnStartTest() {
        super("com.juztoss.HighlightSwitcher", MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
        mSeekBar = (SeekBar) mActivity.findViewById(R.id.seekBar);
    }

    public void testStartPosition() {
        int progress = mSeekBar.getProgress();
        int curBrightnessValue;
        try {
            curBrightnessValue = Settings.System.getInt(mActivity.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            curBrightnessValue = -1;
        }

        assertEquals(curBrightnessValue, progress);
    }
}
