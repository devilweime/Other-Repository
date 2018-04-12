package com.example.wgw.android_demo_ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText ui_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.ui_btn).setOnClickListener(OnClickListener);
        ui_input = (EditText) findViewById(R.id.ui_input);

    }


    private View.OnClickListener OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.ui_btn:
                    Toast.makeText(MainActivity.this,ui_input.getText(),Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    public void click(View v){
        switch (v.getId()){
            case R.id.ui_btn:
                Toast.makeText(MainActivity.this,"Button点击事件2",Toast.LENGTH_LONG).show();
                break;
        }
    }


}
