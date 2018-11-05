package com.andreacioccarelli.billingprotectorsample;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;

import com.andreacioccarelli.billingprotector.BillingProtector;
import com.andreacioccarelli.billingprotector.data.PirateApp;

import java.util.ArrayList;
import java.util.List;

public class SecondaryActivity extends Activity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        BillingProtector bp = new BillingProtector(this);

        TextView mxp = findViewById(R.id.mxp);
        mxp.setText(
                "isRootInstalled: " + String.valueOf(bp.isRootInstalled()) +
                        "\narePirateAppsInstalled: " + bp.arePirateAppsInstalled() +
                        "\n\npirateAppsList: " + bp.getPirateAppsList()
        );

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {}
}
