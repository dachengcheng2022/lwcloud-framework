package com.bt.component.model.jiguang;

import java.util.Map;

public class JGBaseModel {
    private String cid;//可选	用于防止 api 调用端重试造成服务端的重复推送而定义的一个标识符。
    private String platform; //当前支持 Android, iOS, Windows Phone 三个平台的推送。其关键字分别为："android", "ios", "winphone" 所有平台 all
    private Object audience;//如果要发广播（全部设备），则直接填写 “all”。 推送设备对象，表示一条推送可以被推送到哪些设备列表。确认推送设备对象，JPush 提供了多种方式，比如：别名、标签、注册ID、分群、广播等。
    private JGNotificationModel notification;//“通知”对象，是一条推送的实体内容对象之一（另一个是“消息”），是会作为“通知”推送到客户端的。 其下属属性包含 4 种，3 个平台属性，以及一个 "alert" 属性。
    private Map<String,Object> options;//可选参数

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Object getAudience() {
        return audience;
    }

    public void setAudience(Object audience) {
        this.audience = audience;
    }

    public JGNotificationModel getNotification() {
        return notification;
    }

    public void setNotification(JGNotificationModel notification) {
        this.notification = notification;
    }

    public Map<String, Object> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Object> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "JGBaseModel{" +
                "cid='" + cid + '\'' +
                ", platform='" + platform + '\'' +
                ", audience=" + audience +
                ", notification=" + notification +
                ", options=" + options +
                '}';
    }
}
