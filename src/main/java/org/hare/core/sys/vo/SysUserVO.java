package org.hare.core.sys.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hare.core.sys.model.SysUserDO;

/**
 * @author wang cheng
 */
@Getter
@Setter
@ToString
public class SysUserVO {

    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;

    /**
     * 状态：正常、限制、删除
     */
    private String status;

    /**
     * 角色名称快照 英文逗号分隔
     */
    private String role;

    /**
     * 用户主体：企业员工
     * 目前仅有一个类型
     */
    private String subject;

    public static SysUserVO convert(SysUserDO entity) {
        SysUserVO sysUserVO = new SysUserVO();
        sysUserVO.setId(entity.getId());
        sysUserVO.setUsername(entity.getUsername());
        sysUserVO.setNickname(entity.getNickname());
        sysUserVO.setStatus(entity.getStatus());
        sysUserVO.setRole(entity.getRole());
        sysUserVO.setSubject(entity.getSubject());
        return sysUserVO;

    }
}
