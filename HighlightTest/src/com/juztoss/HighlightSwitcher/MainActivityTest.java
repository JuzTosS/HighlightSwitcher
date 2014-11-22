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
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mActivity;
    private ToggleButton mOffBtn;
    private ToggleButton mOnBtn;
    private SeekBar mSeekBar;

    public MainActivityTest() {
        super("com.juztoss.HighlightSwitcher", MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
        mOffBtn = (ToggleButton) mActivity.findViewById(com.juztoss.HighlightSwitcher.R.id.offBtn);
        mOnBtn = (ToggleButton) mActivity.findViewById(com.juztoss.HighlightSwitcher.R.id.onBtn);
        mSeekBar = (SeekBar) mActivity.findViewById(com.juztoss.HighlightSwitcher.R.id.seekBar);
    }

    public void testControlsCreated() {
        assertNotNull(mActivity);
        assertNotNull(mOffBtn);
        assertNotNull(mOnBtn);
        assertNotNull(mSeekBar);
    }

    public void testControlsVisible() {
        ViewAsserts.assertOnScreen(mOffBtn.getRootView(), mOnBtn);
        ViewAsserts.assertOnScreen(mOnBtn.getRootView(), mOffBtn);
        ViewAsserts.assertOnScreen(mOnBtn.getRootView(), mSeekBar);
    }

    public void testOffValueChanged() {
        TouchUtils.tapView(this, mOffBtn);
        int curBrightnessValue;
        try {
            curBrightnessValue = Settings.System.getInt(mActivity.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            curBrightnessValue = -1;
        }

        assertEquals(BrightnessController.MIN_VALUE, curBrightnessValue);
    }

    public void testOnValueChanged() {
        TouchUtils.tapView(this, mOnBtn);
        int curBrightnessValue;
        try {
            curBrightnessValue = Settings.System.getInt(mActivity.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            curBrightnessValue = -1;
        }

        assertEquals(BrightnessController.MAX_VALUE, curBrightnessValue);
    }

    public void testSeekBarValueChanged() {
        TouchUtils.tapView(this, mSeekBar);
        int curBrightnessValue;
        try {
            curBrightnessValue = Settings.System.getInt(mActivity.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            curBrightnessValue = -1;
        }

        assertEquals(BrightnessController.MAX_VALUE / 2, curBrightnessValue);
    }
}
