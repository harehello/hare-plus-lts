package org.hare.core.sys.service;

import com.hare.jpa.HareSpecification;
import org.hare.core.sys.constant.SysConstants;
import org.hare.core.sys.dto.SysUserDTO;
import org.hare.core.sys.dto.SysUserQueryDTO;
import org.hare.core.sys.model.SysUserDO;
import org.hare.framework.jpa.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 用户
 * @author wangcheng
 */
public interface SysUserService extends BaseService<SysUserDO, Long> {

     /**
     * 构建查询条件Specification
     * @param query 查询条件
     * @return Specification
     */
     default Specification<SysUserDO> specification(SysUserQueryDTO query) {
        return new HareSpecification<SysUserDO>()
                .like(StringUtils.hasText(query.getNickname()), "nickname", query.getNickname())
                .eq(StringUtils.hasText(query.getSubject()),"subject", query.getSubject())
                .eq(StringUtils.hasText(query.getStatus()),"status", query.getStatus())
                .like(StringUtils.hasText(query.getRole()),"role", query.getRole())
                .ne("id", SysConstants.USER_SYSTEM_ID)
                .desc("id");
    }

    /**
     * 查询用户列表转换为DTO
     * @param specification 条件
     * @return 分页数据
     */
    Page<SysUserDTO> findPage(Specification<SysUserDO> specification, Pageable pageable);

    /**
     * 查询用户列表转换为DTO
     * @param specification 条件
     * @return 列表数据
     */
    List<SysUserDTO> findList(Specification<SysUserDO> specification);

    /**
     * 查询用户转换为DTO
     * @param id 用户数据
     * @return SysUserDTO数据对象
     */
    SysUserDTO getById(Long id);

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return SysUserDO
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
     * @return SysUserDO
     */
    SysUserDO create(SysUserDTO bodyDTO);

    /**
     * 保存
     * @param bodyDTOs
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
     */
    void resetPassword(Long id);
}
