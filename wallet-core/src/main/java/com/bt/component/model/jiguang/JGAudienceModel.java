package com.bt.component.model.jiguang;

import java.util.List;

public class JGAudienceModel {
    private List<String> tag;//数组。多个标签之间是 OR 的关系，即取并集。
    private List<String> tag_and;//数组。多个标签之间是 AND 关系，即取交集。
    private List<String> tag_not;//数组。多个标签之间，先取多标签的并集，再对该结果取补集。
    private List<String> alias;//数组。多个别名之间是 OR 关系，即取并集。
    private List<String> registration_id;//数组。多个注册ID之间是 OR 关系，即取并集。
    private List<String> segment;//在页面创建的用户分群的 ID。定义为数组，但目前限制一次只能推送一个。
    private List<String> abtest;//在页面创建的 A/B 测试的 ID。定义为数组，但目前限制是一次只能推送一个。

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public List<String> getTag_and() {
        return tag_and;
    }

    public void setTag_and(List<String> tag_and) {
        this.tag_and = tag_and;
    }

    public List<String> getTag_not() {
        return tag_not;
    }

    public void setTag_not(List<String> tag_not) {
        this.tag_not = tag_not;
    }

    public List<String> getAlias() {
        return alias;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

    public List<String> getRegistration_id() {
        return registration_id;
    }

    public void setRegistration_id(List<String> registration_id) {
        this.registration_id = registration_id;
    }

    public List<String> getSegment() {
        return segment;
    }

    public void setSegment(List<String> segment) {
        this.segment = segment;
    }

    public List<String> getAbtest() {
        return abtest;
    }

    public void setAbtest(List<String> abtest) {
        this.abtest = abtest;
    }

    @Override
    public String toString() {
        return "JGAudienceModel{" +
                "tag=" + tag +
                ", tag_and=" + tag_and +
                ", tag_not=" + tag_not +
                ", alias=" + alias +
                ", registration_id=" + registration_id +
                ", segment=" + segment +
                ", abtest=" + abtest +
                '}';
    }
}
