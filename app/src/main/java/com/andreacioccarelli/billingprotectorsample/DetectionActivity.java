package com.andreacioccarelli.billingprotectorsample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.andreacioccarelli.billingprotector.*;
import java.util.concurrent.TimeUnit;

public class DetectionActivity extends Activity {

    BillingProtector bp;
    private int i = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        bp = new BillingProtector(this, false);
        runRefresh();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vib = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vib.vibrate(100);

                runRefresh();
            }
        });
    }

    void runRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long mills = System.currentTimeMillis();
                updateData();

                long millsAfter = System.currentTimeMillis();
                long diff = millsAfter - mills;
                final long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DetectionActivity.this, "Re-computed. time: " + seconds + "s", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).run();
    }

    @SuppressLint("SetTextI18n")
    void updateData() {
        final TextView mxp = findViewById(R.id.mxp);
        mxp.setText(
                "i=" + i++ +
                "isRootInstalled: " + bp.isRootInstalled() +
                "\n\npirateAppsList: " + bp.getPirateAppsList()
        );
    }
}
