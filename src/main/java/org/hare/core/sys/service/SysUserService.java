package org.hare.core.sys.service;

import com.hare.jpa.HareSpecification;
import org.hare.common.constant.DeleteEmun;
import org.hare.core.sys.constant.SysConstants;
import org.hare.core.sys.dto.SysUserDTO;
import org.hare.core.sys.dto.SysUserQuery;
import org.hare.core.sys.model.SysUserDO;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * 用户
 * @author wangcheng
 */
public interface SysUserService {

     /**
     * 构建查询条件Specification
     * @param query 查询条件
     * @return Specification
     */
     default Specification<SysUserDO> specification(SysUserQuery query) {
        return new HareSpecification<SysUserDO>()
                .like(StringUtils.hasText(query.getNickname()), "nickname", query.getNickname())
                .eq(StringUtils.hasText(query.getSubject()),"subject", query.getSubject())
                .eq(Objects.nonNull(query.getSubjectId()),"subjectId", query.getSubjectId())
                .eq(StringUtils.hasText(query.getStatus()),"status", query.getStatus())
                .like(StringUtils.hasText(query.getRole()),"role", query.getRole())
                .eq("deleted", DeleteEmun.NOT_DELETED.getCode())
                .ne("id", SysConstants.USER_SYSTEM_ID)
                .desc("id");
    }

    /**
     * 查询用户列表转换为DTO
     * @param query 条件
     * @return 分页数据
     */
    Page<SysUserDTO> findPage(SysUserQuery query);

    /**
     * 查询用户列表转换为DTO
     * @param query 条件
     * @return 列表数据
     */
    List<SysUserDTO> findList(SysUserQuery query);

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
     * 保存
     * @param bodyDTO
     * @return SysUserDO
     */
    SysUserDO create(SysUserDTO bodyDTO);

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

    /**
     * 删除
     * @param id
     */
    void deleteById(Long id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteAllById(List<Long> ids);
}
