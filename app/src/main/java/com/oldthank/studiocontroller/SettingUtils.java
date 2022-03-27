package com.oldthank.studiocontroller;

import com.nle.mylibrary.forUse.mdbus4150.Modbus4150;

public class SettingUtils {

    public static Modbus4150 modbus4150;

    private static String IP;
    private static Integer Port;
    private static Integer PutterOpen;
    private static Integer PutterClose;

    public SettingUtils() {
    }

    public static Modbus4150 getModbus4150() {
        return modbus4150;
    }

    public static void setModbus4150(Modbus4150 modbus4150) {
        SettingUtils.modbus4150 = modbus4150;
    }

    public static String getIP() {
        return IP;
    }

    public  void setIP(String IP) {
        SettingUtils.IP = IP;
    }

    public static Integer getPort() {
        return Port;
    }

    public  void setPort(Integer port) {
        Port = port;
    }

    public static Integer getPutterOpen() {
        return PutterOpen;
    }

    public  void setPutterOpen(Integer putterOpen) {
        PutterOpen = putterOpen;
    }

    public static Integer getPutterClose() {
        return PutterClose;
    }

    public  void setPutterClose(Integer putterClose) {
        PutterClose = putterClose;
    }
}
