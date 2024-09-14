package org.hare.core.sys.dto;

import lombok.Getter;
import lombok.Setter;
import org.hare.core.sys.model.SysUserDO;

import java.io.Serializable;
import java.util.List;

/**
 * 用户表单
 * @author wang cheng
 */
@Getter
@Setter
public class SysUserDTO implements Serializable {
    private static final long serialVersionUID = 5404525099795630402L;

    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
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
     * 角色集合
     */
    private List<Long> roleIds;

    /**
     * 用户主体：企业员工
     * 目前仅有一个类型
     */
    private String subject;
    /**
     * 主体ID
     */
    private Long subjectId;

    public static SysUserDO convert(SysUserDTO dto) {
        SysUserDO sysUserDO = new SysUserDO();
        sysUserDO.setUsername(dto.getUsername());
        sysUserDO.setNickname(dto.getNickname());
        sysUserDO.setSubject(dto.getSubject());
        sysUserDO.setSubjectId(dto.getSubjectId());
        return sysUserDO;
    }

    public static SysUserDTO convert(SysUserDO entity) {
        SysUserDTO sysUserDTO = new SysUserDTO();
        sysUserDTO.setId(entity.getId());
        sysUserDTO.setUsername(entity.getUsername());
        sysUserDTO.setNickname(entity.getNickname());
        sysUserDTO.setStatus(entity.getStatus());
        sysUserDTO.setSubject(entity.getSubject());
        sysUserDTO.setRole(entity.getRole());
        sysUserDTO.setSubjectId(entity.getSubjectId());
        return sysUserDTO;
    }
}
