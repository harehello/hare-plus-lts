package org.hare.core.sys.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hare.framework.jpa.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

/**
 * 用户
 * @author wang cheng
 */

@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "sys_user", indexes =  {
        @Index(name = "idx_username", columnList = "username"),
        @Index(name = "idx_nickname", columnList = "nickname"),
        @Index(name = "idx_subject_subject_id", columnList = "subject,subjectId"),
})
public class SysUserDO extends BaseEntity {

    private static final long serialVersionUID = 1246825374847350472L;
    /**
     * 用户名
     */
    @Column(length = 20, unique = true)
    private String username;
    /**
     * 密码
     */
    @JsonIgnore
    @Column(length = 100)
    private String password;
    /**
     * 昵称
     */
    @Column(length = 20)
    private String nickname;

    /**
     * 状态：正常、限制
     */
    @Column(length = 10)
    private String status;

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

    /**
     * 删除标识：0-未删除 1-已删除
     */
    private Integer deleted = 0;
}
