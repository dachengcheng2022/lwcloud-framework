package com.bt.component.model.jiguang;

public class JGNotificationModel {
    private String alert;
    private JGNotificationAndroidModel android;
    private JGNotificationIOSModel ios;

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public JGNotificationAndroidModel getAndroid() {
        return android;
    }

    public void setAndroid(JGNotificationAndroidModel android) {
        this.android = android;
    }

    public JGNotificationIOSModel getIos() {
        return ios;
    }

    public void setIos(JGNotificationIOSModel ios) {
        this.ios = ios;
    }

    @Override
    public String toString() {
        return "JGNotificationModel{" +
                "alert='" + alert + '\'' +
                ", android=" + android +
                ", ios=" + ios +
                '}';
    }
}
