package com.bt.component.model.jiguang;


import java.util.Map;

public class JGNotificationAndroidModel {
    private String alert;//必填	通知内容	这里指定了，则会覆盖上级统一指定的 alert 信息；内容可以为空字符串，则表示不展示到通知栏。
    private String title;//	可选	通知标题	如果指定了，则通知里原来展示 App名称的地方，将展示成这个字段。
    private Integer builder_id;//可选	通知栏样式ID	Android SDK 可设置通知栏样式，这里根据样式 ID 来指定该使用哪套样式。
    private Integer priority;//可选	通知栏展示优先级	默认为0，范围为 -2～2 ，其他值将会被忽略而采用默认。
    private String category;//可选	通知栏条目过滤或排序	完全依赖 rom 厂商对 category 的处理策略
    private Integer style;//可选	通知栏样式类型	默认为0，还有1，2，3可选，用来指定选择哪种通知栏样式，其他值无效。有三种可选分别为bigText=1，Inbox=2，bigPicture=3。
    private Integer alert_type;//可选	通知提醒方式	可选范围为 -1 ～ 7 ，对应 Notification.DEFAULT_ALL = -1 或者 Notification.DEFAULT_SOUND = 1, Notification.DEFAULT_VIBRATE = 2, Notification.DEFAULT_LIGHTS = 4 的任意 “or” 组合。默认按照 -1 处理。
    private String big_text;//	可选	大文本通知栏样式	当 style = 1 时可用，内容会被通知栏以大文本的形式展示出来。支持 api 16以上的rom。
    private Map<String,Object> inbox;//可选	文本条目通知栏样式	当 style = 2 时可用， json 的每个 key 对应的 value 会被当作文本条目逐条展示。支持 api 16以上的rom。
    private String big_pic_path;//可选	大图片通知栏样式	当 style = 3 时可用，可以是网络图片 url，或本地图片的 path，目前支持.jpg和.png后缀的图片。图片内容会被通知栏以大图片的形式展示出来。如果是 http／https 的url，会自动下载；如果要指定开发者准备的本地图片就填sdcard 的相对路径。支持 api 16以上的rom。
    private Map<String,Object> extras;//可选	扩展字段	这里自定义 JSON 格式的 Key/Value 信息，以供业务使用。

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBuilder_id() {
        return builder_id;
    }

    public void setBuilder_id(Integer builder_id) {
        this.builder_id = builder_id;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public Integer getAlert_type() {
        return alert_type;
    }

    public void setAlert_type(Integer alert_type) {
        this.alert_type = alert_type;
    }

    public String getBig_text() {
        return big_text;
    }

    public void setBig_text(String big_text) {
        this.big_text = big_text;
    }

    public Map<String, Object> getInbox() {
        return inbox;
    }

    public void setInbox(Map<String, Object> inbox) {
        this.inbox = inbox;
    }

    public String getBig_pic_path() {
        return big_pic_path;
    }

    public void setBig_pic_path(String big_pic_path) {
        this.big_pic_path = big_pic_path;
    }

    public Map<String, Object> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, Object> extras) {
        this.extras = extras;
    }

    @Override
    public String toString() {
        return "JGNotificationAndroidModel{" +
                "alert='" + alert + '\'' +
                ", title='" + title + '\'' +
                ", builder_id=" + builder_id +
                ", priority=" + priority +
                ", category='" + category + '\'' +
                ", style=" + style +
                ", alert_type=" + alert_type +
                ", big_text='" + big_text + '\'' +
                ", inbox=" + inbox +
                ", big_pic_path='" + big_pic_path + '\'' +
                ", extras=" + extras +
                '}';
    }
}
