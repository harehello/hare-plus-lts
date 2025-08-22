package org.hare.core.sys.controller;

import lombok.RequiredArgsConstructor;
import org.hare.common.component.BaseController;
import org.hare.core.sys.dto.LoginUserResponse;
import org.hare.core.sys.dto.SysUserDTO;
import org.hare.core.sys.dto.SysUserPasswordBodyDTO;
import org.hare.core.sys.dto.SysUserQuery;
import org.hare.core.sys.model.SysMenuDO;
import org.hare.core.sys.model.SysUserDO;
import org.hare.core.sys.service.SysMenuService;
import org.hare.core.sys.service.SysUserService;
import org.hare.core.sys.service.SysUserSubjectService;
import org.hare.framework.security.JwtBearerAuthenticationToken;
import org.hare.framework.web.domain.R;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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
    private final SysUserSubjectService userSubjectService;

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
     * 分页列表
     * @param query
     * @return
     */
    @PreAuthorize("hasAuthority('sys:user:list')")
    @GetMapping("/page")
    public R page(SysUserQuery query) {

        return R.success(service.findPage(query));
    }

    /**
     * 列表
     * @param query
     * @return
     */
    @PreAuthorize("hasAuthority('sys:user:list')")
    @GetMapping("/list")
    public R list(SysUserQuery query) {
        return R.success(service.findList(query));
    }

    /**
     * 重置密码
     * @return
     */
    @PreAuthorize("hasAuthority('sys:user:resetPassword')")
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
     * 根据用户ID获取用户信息
     * @return
     */
    @PreAuthorize("hasAuthority('sys:user:list')")
    @GetMapping(value = "/info/{id}")
    public R getById(@PathVariable Long id) {
        return R.success(service.getById(id));
    }

    /**
     * 用户菜单 树形结构
     * @return
     */
    @GetMapping("/menu")
    public R menu(JwtBearerAuthenticationToken authentication) {
        List<SysMenuDO> list = menuService.findMenuByUserId(authentication.getUserId());
        return R.success(menuService.bulidTree(list));
    }

    /**
     * 用户主体类型
     */
    @GetMapping("/subjectType")
    public R subjectType(Authentication authentication) {
        return R.success(userSubjectService.getType());
    }
    /**
     * 用户主体列表
     */
    @GetMapping("/subjectList/{type}")
    public R subjectList(@PathVariable String type, Authentication authentication) {
        return R.success(userSubjectService.getOption(type));
    }

}
