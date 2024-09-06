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
     * 断言用户唯一
     * @param username 要检查的用户名
     */
    void assertUsernameUnique(String username);

    /**
     *
     * @param username 要检查的用户名
     * @param userId 当前用户的ID（如果修改用户时，忽略当前用户的用户名）
     */
    void assertUsernameUnique(String username, Long userId);

    /**
     *
     * @param username 要检查的用户名
     * @return 如果用户名唯一返回true，否则返回false
     */
    boolean isUsernameUnique(String username);
    /**
     *
     * @param username 要检查的用户名
     * @param userId 当前用户的ID（如果修改用户时，忽略当前用户的用户名）
     * @return 如果用户名唯一返回true，否则返回false
     */
    boolean isUsernameUnique(String username, Long userId);

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
