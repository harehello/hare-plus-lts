package org.hare.core.sys.vo;

import lombok.Getter;
import lombok.Setter;
import org.hare.core.sys.model.SysMenuDO;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * 系统菜单视图
 * @author wang cheng
 */
@Getter
@Setter
public class SysMenuVO implements Serializable {

    private static final long serialVersionUID = -1110793496215406300L;
    /**
     * ID
     */
    private Long id;
    /**
     * 父级ID
     */
    private Long pid;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 名称
     */
    private String name;
    /**
     * 路由地址
     */
    private String url;
    /**
     * 打开方式
     */
    private String target;
    /**
     * 权限标识
     */
    private String permissions;
    /**
     * 图标
     */
    private String icon;
    /**
     * 排序
     */
    private Integer seq;

    /**
     * 子项
     */
    private List<SysMenuVO> children;

    public SysMenuVO() {
    }

    public SysMenuVO(SysMenuDO menu) {
        this.id = menu.getId();
        this.pid = menu.getPid();
        this.type = menu.getType();
        this.name = menu.getName();
        this.url = menu.getUrl();
        this.target = menu.getTarget();
        this.permissions = menu.getPermissions();
        this.icon = menu.getIcon();
        this.seq = menu.getSeq();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysMenuVO sysMenuVO = (SysMenuVO) o;
        return Objects.equals(id, sysMenuVO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
