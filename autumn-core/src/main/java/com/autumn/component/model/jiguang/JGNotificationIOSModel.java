package com.autumn.component.model.jiguang;

import java.util.Map;

public class JGNotificationIOSModel {
    private String alert;//必填	通知内容	这里指定内容将会覆盖上级统一指定的 alert 信息；内容为空则不展示到通知栏。支持字符串形式也支持官方定义的alert payload 结构
    private String sound;//可选	通知提示声音	如果无此字段，则此消息无声音提示；有此字段，如果找到了指定的声音就播放该声音，否则播放默认声音,如果此字段为空字符串，iOS 7 为默认声音，iOS 8及以上系统为无声音。(消息) 说明：JPush 官方 API Library (SDK) 会默认填充声音字段。提供另外的方法关闭声音。
    private Integer badge;//可选	应用角标	如果不填，表示不改变角标数字；否则把角标数字改为指定的数字；为 0 表示清除。JPush 官方 API Library(SDK) 会默认填充badge值为"+1",详情参考：badge +1
    private String category;//可选 IOS8才支持。设置APNs payload中的"category"字段值
    private Boolean content_available;//推送的时候携带"content-available":true 说明是 Background Remote Notification，如果不携带此字段则是普通的Remote Notification
    private Boolean mutable_content;//推送的时候携带"content-available":true 说明是 Background Remote Notification，如果不携带此字段则是普通的Remote Notification。

    private Map<String,Object> extras;//可选	扩展字段	这里自定义 JSON 格式的 Key/Value 信息，以供业务使用。

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public Integer getBadge() {
        return badge;
    }

    public void setBadge(Integer badge) {
        this.badge = badge;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Map<String, Object> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, Object> extras) {
        this.extras = extras;
    }

    public Boolean getContent_available() {
        return content_available;
    }

    public void setContent_available(Boolean content_available) {
        this.content_available = content_available;
    }

    public Boolean getMutable_content() {
        return mutable_content;
    }

    public void setMutable_content(Boolean mutable_content) {
        this.mutable_content = mutable_content;
    }

    @Override
    public String toString() {
        return "JGNotificationIOSModel{" +
                "alert='" + alert + '\'' +
                ", sound='" + sound + '\'' +
                ", badge=" + badge +
                ", category='" + category + '\'' +
                ", extras=" + extras +
                '}';
    }
}
