package com.oldthank.studiocontroller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.nle.mylibrary.forUse.mdbus4150.MdBus4150SensorListener;
import com.nle.mylibrary.forUse.mdbus4150.Modbus4150;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private static Modbus4150 modbus4150 = null;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        findViewById(R.id.imagebtnOpen).setOnTouchListener(this);
        findViewById(R.id.imagebtnClose).setOnTouchListener(this);
    }

    //Activity 启动时调用
    @Override
    protected void onStart() {
        super.onStart();

        /**
         * 在activity启动时提示用户进行TCP连接到串口服务器
         */
        if (SettingUtils.getModbus4150() == null){
            Toast.makeText(this, "请先进行TCP连接串口服务器...", Toast.LENGTH_SHORT).show();
        }
        if (modbus4150 == null && SettingUtils.getModbus4150() != null){
            modbus4150 = SettingUtils.getModbus4150();
        }
    }


    /**
     * 创建menu并且为其中的btn进行事件绑定
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mian,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.setting:
                Intent intent = new Intent(this, Setting.class);
                startActivity(intent);
                break;
        }
        return false;
    }


    /**
     * 任务需求：可以查询README.md
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int action = event.getAction();

        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP){
            runOnUiThread(()->imageView.setImageResource(R.drawable.ic_media_play));
            try {
                modbus4150.closeRelay(SettingUtils.getPutterOpen(),(i)->{});
                modbus4150.closeRelay(SettingUtils.getPutterClose(),(i)->{});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(action == MotionEvent.ACTION_DOWN) // 按下状态
        {
            switch (v.getId()) {
                case R.id.imagebtnOpen:
                    try {
                        modbus4150.openRelay(SettingUtils.getPutterOpen(),(open)->{
                            System.out.println("open = " + open);
                            runOnUiThread(()->imageView.setImageResource(R.drawable.ic_media_rew));
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case R.id.imagebtnClose:
                    try {
                        modbus4150.openRelay(SettingUtils.getPutterClose(),(close)->{
                            System.out.println("close = " + close);
                            runOnUiThread(()->imageView.setImageResource(R.drawable.ic_media_ff));
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
        return true;
    }
}