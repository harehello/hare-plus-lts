package org.hare.core.sys.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;
import org.hare.core.sys.model.SysRoleDO;
import org.hare.core.sys.model.SysUserDO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.*;

/**
 * @author wang cheng
 */
@ToString
public class LoginUserDTO2 {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    @Column(length = 20, unique = true)
    private String username;

    /**
     * 昵称
     */
    @Column(length = 20)
    private String nickname;

    /**
     * 用户角色关系
     */
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "sys_user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<SysRoleDO> roles;
    /**
     * 角色名称快照 英文逗号分隔
     */
    @Column(length = 150)
    private String role;

    /**
     * 用户主体：企业员工
     * 目前仅有一个类型
     */
    @Column(length = 10)
    private String subject;

    /**
     * 主体ID
     */
    private Long subjectId;
    private Set<GrantedAuthority> authorities;

    private boolean accountNonLocked;



    public boolean equals(Object obj) {
        return obj instanceof LoginUserDTO2 && this.username.equals(((LoginUserDTO2) obj).username);
    }

    public int hashCode() {
        return this.username.hashCode();
    }

    public LoginUserDTO2() {
    }

    public LoginUserDTO2(SysUserDO userDO, Set<? extends GrantedAuthority> authorities) {
        this.user = userDO;
        this.authorities = Collections.unmodifiableSet(authorities);
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
