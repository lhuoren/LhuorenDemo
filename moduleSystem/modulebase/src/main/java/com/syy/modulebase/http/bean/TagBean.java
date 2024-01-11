package com.syy.modulebase.http.bean;

import android.graphics.Color;

public class TagBean implements InsertData {
  public static final String TAG_FORMART = "&nbsp;<tag type='%s' id='%s' name='%s'>%s</tag>&nbsp;";
  public static final String TAG = "tag";
  public static final String TYPE = "type";
  public static final String USER = "user";
  public static final String TOPIC = "topic";
  public static final String ID = "id";
  public static final String NAME = "name";
  private String name;
  private String id;
  private String type;

  public TagBean(String name, String id, String type) {
    this.name = name;
    this.id = id;
    this.type = type;

  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  @Override
  public String toString() {
    return "TagBean{" +
        "name='" + name + '\'' +
        ", id='" + id + '\'' +
        ", tag='" + type + '\'' +
        '}';
  }

  @Override
  public CharSequence charSequence() {
    if (TagBean.TOPIC.equals(type)) {
      return "#" + getName() +"#";
    }else {
      return "@" + getName();
    }
  }


  @Override public FormatRange.FormatData formatData() {
    return new UserConvert(this);
  }

  @Override public int color() {
    return Color.parseColor("#00B9EF");
  }


  private class UserConvert implements FormatRange.FormatData {


    private final TagBean tag;

    public UserConvert(TagBean tag) {
      this.tag = tag;
    }

    @Override public CharSequence formatCharSequence() {
      return String.format(TagBean.TAG_FORMART, tag.getType(),tag.getId(),tag.getName(),charSequence());
    }
  }
}
