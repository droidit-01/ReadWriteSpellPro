package com.RogersCenter.readwritespell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FlowSelectionActivity extends AppCompatActivity {

    ImageView iv_recommended_flow,iv_customised_flow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_selection);
        iv_recommended_flow =findViewById(R.id.iv_recommended_flow);
        iv_customised_flow =findViewById(R.id.iv_customised_flow);

        iv_recommended_flow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FlowSelectionActivity.this, HomeActivity.class);
                StaticValues.SavePreferences("FLOW","RECOMMENDED",FlowSelectionActivity.this);
                startActivity(i);
                finish();
            }
        });

        iv_customised_flow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FlowSelectionActivity.this, HomeActivity.class);
                StaticValues.SavePreferences("FLOW","CUSTOMISED",FlowSelectionActivity.this);
                startActivity(i);
                finish();
            }
        });
    }
}