package org.hare.core.sys.service;

import org.hare.core.sys.dto.SysUserDTO;
import org.hare.core.sys.model.SysUserDO;
import org.hare.framework.jpa.BaseService;

import java.util.List;

/**
 * @author wangcheng
 */
public interface SysUserService extends BaseService<SysUserDO, Long> {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    SysUserDO findByUsername(String username);


    /**
     * 保存
     * @param bodyDTO
     * @return
     */
    SysUserDO create(SysUserDTO bodyDTO);
    /**
     * 保存
     * @param bodyDTOs
     * @return
     */
    void create(List<SysUserDTO> bodyDTOs);

    /**
     * 修改
     * @param bodyDTO
     * @return
     */
    SysUserDO update(SysUserDTO bodyDTO);

    /**
     * 修改密码
     */
    void updatePassword(Long id, String newPassword, String rawPassword);
    /**
     * 重置密码
     * @param id
     */
    void resetPassword(Long id);
}
