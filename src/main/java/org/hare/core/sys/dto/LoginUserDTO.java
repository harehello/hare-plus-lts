package org.hare.core.sys.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;
import org.hare.core.sys.model.SysUserDO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * @author wang cheng
 */
@ToString
public class LoginUserDTO implements UserDetails {
    private static final long serialVersionUID = -5082095911171826416L;

    private SysUserDO user;
    private String terminal;
    private Set<GrantedAuthority> authorities;

    public LoginUserDTO() {
    }

    public LoginUserDTO(SysUserDO userDO, Set<? extends GrantedAuthority> authorities) {
        this.user = userDO;
        this.authorities = Collections.unmodifiableSet(authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    @JsonIgnore
    @Override
    public String getUsername() {
        return user.getUsername();
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public SysUserDO getUser() {
        return user;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }
}
