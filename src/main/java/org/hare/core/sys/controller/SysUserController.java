package org.hare.core.sys.controller;

import com.hare.jpa.HareSpecification;
import lombok.RequiredArgsConstructor;
import org.hare.common.component.BaseController;
import org.hare.core.sys.constant.SysConstants;
import org.hare.core.sys.dto.*;
import org.hare.core.sys.model.*;
import org.hare.core.sys.service.*;
import org.hare.core.sys.vo.SysUserVO;
import org.hare.framework.web.domain.R;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户
 * @author wang cheng
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sys/user")
public class SysUserController extends BaseController {
    private final SysUserService service;
    private final SysMenuService menuService;
    private final SysJobService postService;
    private final SysRoleService roleService;
    private final SysDeptService deptService;

    /**
     * 添加
     * @param form
     * @return
     */
    @PostMapping
    public R create(@Validated @RequestBody SysUserDTO form) {
        service.create(form);
        return R.success();
    }

    /**
     * 修改
     * @param form
     * @return
     */
    @PutMapping
    public R update(@Validated @RequestBody SysUserDTO form) {
        service.update(form);
        return R.success();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        service.deleteById(id);
        return R.success();
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @DeleteMapping
    public R delete(@RequestBody Long[] ids) {
        service.deleteAllById(Arrays.asList(ids));
        return R.success();
    }

    /**
     * 构建查询条件
     * @param query
     * @return
     */
    private Specification buildSpecification(SysUserQueryDTO query) {
        return new HareSpecification<SysUserDO>()
                .ne("id", SysConstants.USER_SYSTEM_ID)
                .ne("roleIds", SysConstants.ROLE_ADMIN)
                .eq(Objects.nonNull(query.getPostId()), "employee.post", query.getPostId())
                .like(StringUtils.hasText(query.getFullName()), "employee.fullName", query.getFullName())
                .likeRight(StringUtils.hasText(query.getDept()), "employee.dept.parentIds", query.getDept());
    }

    /**
     * 分页列表
     * @param query
     * @return
     */
    @GetMapping("/page")
    public R page(SysUserQueryDTO query) {
        Page<SysUserDO> page = service.findAll(buildSpecification(query), query.getPageable());
        Page<SysUserVO> voPage = page.map(SysUserVO::new);
        return R.success(voPage);
    }

    /**
     * 列表
     * @param query
     * @return
     */
    @GetMapping("/list")
    public R list(SysUserQueryDTO query) {
        List<SysUserDO> list = service.findAll(buildSpecification(query));
        List<SysUserVO> vos = list.stream().map(SysUserVO::new).collect(Collectors.toList());
        return R.success(vos);
    }

    /**
     * 重置密码
     * @return
     */
    @GetMapping("/resetPassword/{id}")
    public R resetPassword(@PathVariable Long id) {
        service.resetPassword(id);
        return R.success();
    }

    /**
     * 修改自己的密码
     * @return
     */
    @PutMapping("/password")
    public R password(@RequestBody SysUserPasswordBodyDTO body) {
//        service.updatePassword(SecurityContextUtils.getUserId(), body.getNewPassword(), body.getRawPassword());
        return R.success();
    }

    /**
     * 用户信息
     * @return
     */
    @GetMapping(value = "/info")
    public R info(Authentication authentication) {
        String username = authentication.getName();
        SysUserDO user = service.findByUsername(username);
        return R.success(new LoginUserResponse(user, authentication.getAuthorities()));
    }

    /**
     * 用户菜单 树形结构
     * @return
     */
    @GetMapping("/menu")
    public R menu(Authentication authentication) {
        List<SysMenuDO> list = menuService.findMenuByUserId(0L);
        return R.success(menuService.bulidTree(list));
    }

    /**
     * 导入
     * @param file
     * @return
     */
    @PostMapping("/import")
    @ResponseBody
    public R importExcel(MultipartFile file) {
        try {
//            ExcelDataListener excelListener = new ExcelDataListener<SysUserImportDTO>();
//            EasyExcel.read(file.getInputStream(), SysUserImportDTO.class, excelListener).sheet().doRead();
//            List<SysUserImportDTO> dtos = excelListener.getList();
//            // 部门
//            final List<SysDeptDO> depts = deptService.findAll();
//            // 岗位
//            final List<SysPostDO> posts = postService.findAll();
//            // 角色
//            final List<SysRoleDO> roles = roleService.findAll();
//
//            List<SysUserBodyDTO> userBodys = new ArrayList<SysUserBodyDTO>();
//            SysUserBodyDTO bodyDTO = null;
//            for (SysUserImportDTO dto : dtos) {
//                // 必要信息没有直接跳过
//                if (!StringUtils.hasText(dto.getFullName())) {
//                    continue;
//                }
//                if (!StringUtils.hasText(dto.getPhone())) {
//                    continue;
//                }
//                String deptName = dto.getDeptName();
//                if (!StringUtils.hasText(deptName)) {
//                    continue;
//                }
//                Optional<SysDeptDO> deptFirst = depts.stream().filter(t -> t.getName().equals(deptName)).findFirst();
//                if (!deptFirst.isPresent()) {
//                    continue;
//                }
//
//                Long deptId = deptFirst.get().getId();
//
//                bodyDTO = SysUserImportDTO.convertToBodyDTO(dto);
//                bodyDTO.setDeptId(deptId);
//
//                String post = dto.getPost();
//                if (StringUtils.hasText(post)) {
//                    Optional<SysPostDO> postFirst = posts.stream().filter(t -> t.getName().equals(post)).findFirst();
//                    if (postFirst.isPresent()) {
//                        Long postId = postFirst.get().getId();
//                        bodyDTO.setPostId(postId);
//                    }
//                }
//
//                String role = dto.getRole();
//                if (StringUtils.hasText(role)) {
//                    List<Long> roleIds = new ArrayList<>();
//                    String[] roleArr = role.split(Constants.PUNCTUATION_COMMA);
//                    for (String s : roleArr) {
//                        Optional<SysRoleDO> roleFirst = roles.stream().filter(t -> t.getName().equals(s)).findFirst();
//                        if (roleFirst.isPresent()) {
//                            Long roleId = roleFirst.get().getId();
//                            roleIds.add(roleId);
//                        }
//                    }
//                    bodyDTO.setRoleIds(roleIds);
//                }
//                userBodys.add(bodyDTO);
//            }
//
//            service.create(userBodys);
        } catch (Exception e) {
            return R.failed("导入失败");
        }
        return R.success();
    }
}
