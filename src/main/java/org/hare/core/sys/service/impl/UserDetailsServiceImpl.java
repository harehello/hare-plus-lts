//package org.hare.core.sys.service.impl;
//
//import lombok.RequiredArgsConstructor;
//import org.hare.core.sys.dto.LoginUserDTO;
//import org.hare.core.sys.model.SysMenuDO;
//import org.hare.core.sys.model.SysUserDO;
//import org.hare.core.sys.service.SysMenuService;
//import org.hare.core.sys.service.SysUserService;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.Set;
//import java.util.stream.Collectors;
//
///**
// * @author wang cheng
// */
//@RequiredArgsConstructor
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final SysUserService userService;
//    private final SysMenuService menuService;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        SysUserDO sysUserDO = userService.findByUsername(username);
//
//        if (Objects.isNull(sysUserDO)) {
//            throw new UsernameNotFoundException(username + "用户不存在");
//        }
//
//        List<SysMenuDO> menus = menuService.findBtnByUserId(sysUserDO.getId());
//
//        Set<SimpleGrantedAuthority> authorities = menus.stream()
//                .map(m -> new SimpleGrantedAuthority(m.getPermissions()))
//                .collect(Collectors.toSet());
//        LoginUserDTO loginUserDTO = new LoginUserDTO(sysUserDO, authorities);
//        return loginUserDTO;
//    }
//}
