package org.hare.core.sys.dto;

import lombok.Getter;
import lombok.ToString;
import org.hare.core.sys.model.SysDeptDO;
import org.hare.core.sys.model.SysMenuDO;
import org.hare.core.sys.model.SysUserDO;
import org.hare.framework.security.CustomGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wang cheng
 */
@ToString
public class LoginUserDTO {
    /**
     * 用户ID
     */
    @Getter
    private Long userId;

    /**
     * 用户名
     */
    @Getter
    private String username;

    /**
     * 昵称
     */
    @Getter
    private String nickname;

    /**
     * 用户主体：企业员工
     * 目前仅有一个类型
     */
    @Getter
    private String subject;

    /**
     * 主体ID
     */
    @Getter
    private Long subjectId;

    /**
     * 权限
     */
    @Getter
    private Set<GrantedAuthority> authorities;
    /**
     * 数据权限
     */
    @Getter
    private Set<DataScope> dataScopes;

    @Getter
    private boolean accountNonLocked;

    public boolean equals(Object obj) {
        return obj instanceof LoginUserDTO && this.username.equals(((LoginUserDTO) obj).username);
    }

    public int hashCode() {
        return this.username.hashCode();
    }

    public LoginUserDTO() {
    }

    public LoginUserDTO(Long userId, String username, String nickname, String subject, Long subjectId, Collection<? extends GrantedAuthority> authorities, Collection<DataScope> dataScopes, boolean accountNonLocked) {
        this.userId = userId;
        this.username = username;
        this.nickname = nickname;
        this.subject = subject;
        this.subjectId = subjectId;
        this.accountNonLocked = accountNonLocked;
        if (authorities == null) {
            authorities = AuthorityUtils.NO_AUTHORITIES;
        }
        this.authorities = Collections.unmodifiableSet(new LinkedHashSet<>(authorities));
        this.dataScopes = Collections.unmodifiableSet(new LinkedHashSet<>(dataScopes));
    }

    public LoginUserDTO(Set<? extends GrantedAuthority> authorities) {
        this.authorities =  Collections.unmodifiableSet(authorities);
    }

    public static LoginUserBuilder withUsername(String username) {
        return builder().username(username);
    }

    public static LoginUserBuilder builder() {
        return new LoginUserBuilder();
    }

    public static LoginUserBuilder withSysUser(SysUserDO user) {
        return withUsername(user.getUsername())
                .userId(user.getId())
                .nickname(user.getNickname())
                .subject(user.getSubject())
                .subjectId(user.getSubjectId())
                .authorities(user)
                .dataScopes(user)
                .accountLocked(false);
    }

    public static List<GrantedAuthority> createAuthorityList(List<String> authorities) {
        return authorities.stream()
                .map(CustomGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public static final class LoginUserBuilder {

        /**
         * 用户ID
         */
        private Long userId;

        /**
         * 用户名
         */
        private String username;

        /**
         * 昵称
         */
        private String nickname;

        /**
         * 用户主体：企业员工
         * 目前仅有一个类型
         */
        private String subject;

        /**
         * 主体ID
         */
        private Long subjectId;

        private List<GrantedAuthority> authorities;

        private List<DataScope> dataScopes;;

        private boolean accountLocked;

        /**
         * Creates a new instance
         */
        private LoginUserBuilder() {
        }

        public LoginUserBuilder userId(Long userId) {
            Assert.notNull(userId, "userId cannot be null");
            this.userId = userId;
            return this;
        }

        public LoginUserBuilder username(String username) {
            Assert.notNull(username, "username cannot be null");
            this.username = username;
            return this;
        }

        public LoginUserBuilder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public LoginUserBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public LoginUserBuilder subjectId(Long subjectId) {
            this.subjectId = subjectId;
            return this;
        }

        public LoginUserBuilder roles(String... roles) {
            List<GrantedAuthority> authorities = new ArrayList<>(roles.length);
            for (String role : roles) {
                Assert.isTrue(!role.startsWith("ROLE_"),
                        () -> role + " cannot start with ROLE_ (it is automatically added)");
                authorities.add(new CustomGrantedAuthority("ROLE_" + role));
            }
            return authorities(authorities);
        }

        public LoginUserBuilder authorities(GrantedAuthority... authorities) {
            return authorities(Arrays.asList(authorities));
        }

        /**
         * Populates the authorities. This attribute is required.
         * @param authorities the authorities for this user. Cannot be null, or contain
         * null values
         * @return the {@link User.UserBuilder} for method chaining (i.e. to populate
         * additional attributes for this user)
         * @see #roles(String...)
         */
        public LoginUserBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = new ArrayList<>(authorities);
            return this;
        }

        public LoginUserBuilder authorities(SysUserDO user) {
            final List<String> permissions = user.getRoles()
                    .stream()
                    .flatMap(role -> role.getMenus().stream())
                    .map(SysMenuDO::getPermissions)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

//            user.getRoles().forEach(r -> permissions.add("ROLE_" + r.getName()));

            return authorities(createAuthorityList(permissions));
        }

        public LoginUserBuilder dataScopes(SysUserDO user) {
            this.dataScopes = user.getRoles()
                    .stream()
                    .map(r -> new DataScope(r.getDataScope(),
                            r.getDepts().stream().map(SysDeptDO::getId).collect(Collectors.toList())))
                    .collect(Collectors.toList());
            return this;
        }

        public LoginUserBuilder accountLocked(boolean accountLocked) {
            this.accountLocked = accountLocked;
            return this;
        }

        public LoginUserDTO build() {
            return new LoginUserDTO(userId, username, nickname, subject, subjectId, authorities, dataScopes, !accountLocked);
        }
    }

    public static class DataScope {
        @Getter
        private String name;
        @Getter
        private List<Long> addition;

        public DataScope() {
        }

        public DataScope(String name, List<Long> addition) {
            this.name = name;
            this.addition = addition;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            DataScope dataScope = (DataScope) o;
            return Objects.equals(name, dataScope.name);
        }

    }

}
