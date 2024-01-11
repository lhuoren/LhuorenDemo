package com.gac.nioapp.test.bean;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import com.syy.modulebase.http.bean.FormatRange;
import com.syy.modulebase.http.bean.InsertData;
import com.syy.modulebase.http.bean.TagBean;
import com.syy.modulebase.http.constants.Constants;

/**
 * @Description：
 * textView.setText(spanny);
 * textView.setMovementMethod(LinkMovementMethod.getInstance());需要这一句不然clickSpan无响应
 * @Author：Sai
 * @Date：2019/3/13 13:33
 */
public class TopicBean implements Parcelable, InsertData {
    private String id;
    private String name;
    /**
     * id : 2
     * coverPath : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1552983780491&di=4ff00028c21715290c6abb7dbd97b986&imgtype=0&src=http%3A%2F%2Fs3.xchuxing.com%2Fxchuxing%2Fforum%2F201710%2F23%2F214147bttuinvkniiqci77.jpg
     * topicDescribe : 蔚来 ES8 已于今年 4 月上海国际车展亮相。ES8 是一款高性能纯电动七座 SUV ， 座椅采用 2+3+2 布局，车长超过 5 米，轴距超过 3 米。ES8 由全铝合金车身和底盘打造，全系标配主动式空气悬挂，搭载前后双电机，采用四轮驱动。外观采用“ X-Bar ”前脸和“心跳曲线”尾灯等蔚来家族设计语言。ES8 支持换电模式，将为用户带来超越燃油车加油的加电体验。ES8 将在全球完成 200 万公里路试，年底正式投入量产，明年上半年开始交付。
     * joinCount : 1
     * joinPersonCount : 1
     * sort : 2
     * status : 1
     * delFlag : 0
     * createTime : 2019-03-11T17:55:23.000+0000
     * updateTime : 2019-03-19T05:39:28.000+0000
     * createId : 1
     * updateId : 1
     */

    private String coverPath;
    private String topicDescribe;
    private String joinPersonCount;
    private String miniProgramShareUrl;
    private String htmlShareUrl;
    private String mTag = Constants.TOPIC_TAG;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public String getTopicDescribe() {
        return topicDescribe;
    }

    public void setTopicDescribe(String topicDescribe) {
        this.topicDescribe = topicDescribe;
    }

    public String getJoinPersonCount() {
        return joinPersonCount;
    }

    public void setJoinPersonCount(String joinPersonCount) {
        this.joinPersonCount = joinPersonCount;
    }

    public String getMiniProgramShareUrl() {
        return miniProgramShareUrl;
    }

    public void setMiniProgramShareUrl(String miniProgramShareUrl) {
        this.miniProgramShareUrl = miniProgramShareUrl;
    }

    public String getHtmlShareUrl() {
        return htmlShareUrl;
    }

    public void setHtmlShareUrl(String htmlShareUrl) {
        this.htmlShareUrl = htmlShareUrl;
    }
    public void setTag(String mTag) {
        this.mTag = mTag;
    }

    public String getTag() {
        return mTag;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.coverPath);
        dest.writeString(this.topicDescribe);
        dest.writeString(this.joinPersonCount);
        dest.writeString(this.mTag);
    }

    public TopicBean() {
    }

    protected TopicBean(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.coverPath = in.readString();
        this.topicDescribe = in.readString();
        this.joinPersonCount = in.readString();
        this.mTag = in.readString();
    }

    public static final Creator<TopicBean> CREATOR = new Creator<TopicBean>() {
        @Override
        public TopicBean createFromParcel(Parcel source) {
            return new TopicBean(source);
        }

        @Override
        public TopicBean[] newArray(int size) {
            return new TopicBean[size];
        }
    };




    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TopicBean tag = (TopicBean) o;

        if (name != null ? !name.equals(tag.name) : tag.name != null) return false;
        return id != null ? id.equals(tag.id) : tag.id == null;
    }

    @Override public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override public CharSequence charSequence() {
        return "#" + name + "#";
    }

    @Override public FormatRange.FormatData formatData() {
        return new TopConvert(this);
    }

    @Override public int color() {
        return Color.parseColor("#00B9EF");
    }

    private class TopConvert implements FormatRange.FormatData {
        private final TopicBean tag;

        public TopConvert(TopicBean tag) {
            this.tag = tag;
        }

        @Override public CharSequence formatCharSequence() {
            return String.format(TagBean.TAG_FORMART,TagBean.TOPIC, tag.getId(), tag.getName(),
                    "#" + tag.getName() + "#");
        }
    }
}
