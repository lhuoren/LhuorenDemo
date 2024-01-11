package cn.gacnio.it.database.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Unique;

/**
 * <p>
 * 当前登陆用户
 * id，userName,token,头像url，其他的一些信息；
 *
 * 有登陆就直接插入，无登陆，直接删除；
 */
@Entity
public class CurrentUserDB {
    @Id(autoincrement = true)
    private Long id;
    private String token;
    private String userId; // 用户id
    @Unique
    private String userName;  //账户
    private String facePic;    //头像
    private String name;   //真实姓名
    private String deviceid;   //补充当前设备id
    private String phone;  // 用户电话号码
    @Generated(hash = 1944022523)
    public CurrentUserDB(Long id, String token, String userId, String userName,
            String facePic, String name, String deviceid, String phone) {
        this.id = id;
        this.token = token;
        this.userId = userId;
        this.userName = userName;
        this.facePic = facePic;
        this.name = name;
        this.deviceid = deviceid;
        this.phone = phone;
    }
    @Generated(hash = 837972805)
    public CurrentUserDB() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getFacePic() {
        return this.facePic;
    }
    public void setFacePic(String facePic) {
        this.facePic = facePic;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDeviceid() {
        return this.deviceid;
    }
    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
