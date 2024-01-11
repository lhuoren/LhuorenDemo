package cn.gacnio.it.database.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author heyufei
 * @version 1.0
 * @className CommonEntity
 * @description TODO
 * @since 2022/8/16 17:56
 */

@Entity
public class CommonEntity {
    @Id
    private long id;
    private String key;
    private String type;
    private String jsonStr;

    public CommonEntity() {}

    public CommonEntity(String key, String type, String jsonStr) {
        this.key = key;
        this.type = type;
        this.jsonStr = jsonStr;
    }

    @Generated(hash = 1557129042)
    public CommonEntity(long id, String key, String type, String jsonStr) {
        this.id = id;
        this.key = key;
        this.type = type;
        this.jsonStr = jsonStr;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJsonStr() {
        return this.jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

}
