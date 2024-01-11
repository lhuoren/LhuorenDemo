package cn.gacnio.it.database.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;


@Entity
public class ModuleEntity {

    @Id
    private long id;

    private String name;

    private String className;

    private int resId;
    private int selectedIconId;
    private String animationIcon;
    private int unSelectedIconId;

    private boolean enabled;

    private int order;//排序

    @Generated(hash = 1371113163)
    public ModuleEntity(long id, String name, String className, int resId,
            int selectedIconId, String animationIcon, int unSelectedIconId,
            boolean enabled, int order) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.resId = resId;
        this.selectedIconId = selectedIconId;
        this.animationIcon = animationIcon;
        this.unSelectedIconId = unSelectedIconId;
        this.enabled = enabled;
        this.order = order;
    }

    @Generated(hash = 1314096604)
    public ModuleEntity() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getResId() {
        return this.resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getSelectedIconId() {
        return this.selectedIconId;
    }

    public void setSelectedIconId(int selectedIconId) {
        this.selectedIconId = selectedIconId;
    }

    public String getAnimationIcon() {
        return this.animationIcon;
    }

    public void setAnimationIcon(String animationIcon) {
        this.animationIcon = animationIcon;
    }

    public int getUnSelectedIconId() {
        return this.unSelectedIconId;
    }

    public void setUnSelectedIconId(int unSelectedIconId) {
        this.unSelectedIconId = unSelectedIconId;
    }

    public boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

}
