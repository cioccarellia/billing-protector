package com.andreacioccarelli.billingprotectorsample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;

import com.andreacioccarelli.billingprotector.BillingProtector;

public class DetectionActivity extends Activity {

    BillingProtector bp;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        bp = new BillingProtector(this);
        updateData();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vib = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vib.vibrate(100);

                updateData();
            }
        });
    }

    void updateData() {
        final TextView mxp = findViewById(R.id.mxp);
        mxp.setText(
                "isRootInstalled: " + String.valueOf(bp.isRootInstalled()) +
                        "\narePirateAppsInstalled: " + bp.arePirateAppsInstalled() +
                        "\n\npirateAppsList: " + bp.getPirateAppsList()
        );
    }
}
