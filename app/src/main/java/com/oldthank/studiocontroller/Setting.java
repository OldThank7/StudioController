package com.oldthank.studiocontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.nle.mylibrary.forUse.mdbus4150.Modbus4150;
import com.nle.mylibrary.transfer.DataBusFactory;

public class Setting extends AppCompatActivity {

    private EditText editip,editport;
    private EditText putteropen,putterclose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);

        editip = findViewById(R.id.editIp);
        editport = findViewById(R.id.editPort);

        putteropen = findViewById(R.id.editPutterOpen);
        putterclose = findViewById(R.id.editPutterClose);

    }

    public void Conection(View view) {
        SettingUtils settingUtils = new SettingUtils();

        settingUtils.setIP(editip.getText().toString());
        settingUtils.setPort(editToInteger(editport));
        settingUtils.setPutterOpen(editToInteger(putteropen));
        settingUtils.setPutterClose(editToInteger(putterclose));

        if (SettingUtils.getModbus4150() == null) {

            String ip = SettingUtils.getIP();
            Integer port = SettingUtils.getPort();


            Modbus4150 modbus4150 = new Modbus4150(DataBusFactory.newSocketDataBus(ip,port));

//            Modbus4150 modbus4150 = new Modbus4150(DataBusFactory.newSerialDataBus(0,9600));

            SettingUtils.setModbus4150(modbus4150);
        }else{
            Toast.makeText(this, "TCP已连接...", Toast.LENGTH_SHORT).show();
        }

    }

    public Integer editToInteger(EditText text){
        return Integer.valueOf(text.getText().toString());
    }
}