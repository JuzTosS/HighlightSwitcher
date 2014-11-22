package com.juztoss.HighlightSwitcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

    private BrightnessController mBrightnessController;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initBrightnessController();
        initSeekBar();
        setMoveToBackListener();

        update();
    }

    private void initBrightnessController() {
        mBrightnessController = new BrightnessController(this);
        mBrightnessController.setListener(new IBrightnessChangedListener() {
            @Override
            public void onBrightnessChanged() {
                update();
            }
        });
    }

    private void initSeekBar() {
        seekBar().setMax(BrightnessController.MAX_VALUE - BrightnessController.MIN_VALUE);
        seekBar().setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mBrightnessController.setBrightness(BrightnessController.MIN_VALUE + progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setMoveToBackListener() {
        View layout = findViewById(R.id.rootLayout);
        layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                moveTaskToBack(true);

            }
        });
    }

    private void update() {
        int level = mBrightnessController.getBrightness();
        seekBar().setProgress(level);

        if (level <= BrightnessController.MIN_VALUE)
            offBtn().setChecked(true);
        else
            offBtn().setChecked(false);

        if (level >= BrightnessController.MAX_VALUE)
            onBtn().setChecked(true);
        else
            onBtn().setChecked(false);
    }

    public void offClick(View view) {
        mBrightnessController.setBrightness(BrightnessController.MIN_VALUE);
    }

    public void onClick(View view) {
        mBrightnessController.setBrightness(BrightnessController.MAX_VALUE);
    }

    public void onSeekClick(View view) {
    }

    private SeekBar seekBar() {
        return (SeekBar) findViewById(R.id.seekBar);
    }

    private ToggleButton offBtn() {
        return (ToggleButton) findViewById(R.id.offBtn);
    }

    private ToggleButton onBtn() {
        return (ToggleButton) findViewById(R.id.onBtn);
    }
}
