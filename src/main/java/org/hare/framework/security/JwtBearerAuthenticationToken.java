package org.hare.framework.security;

import org.hare.core.sys.dto.LoginUserDTO;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.Transient;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;

import java.util.Map;

/**
 * an implementation of an AbstractOAuth2TokenAuthenticationToken representing a Jwt Authentication.
 * @author wang cheng
 */
@Transient
public class JwtBearerAuthenticationToken extends AbstractOAuth2TokenAuthenticationToken<Jwt> {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final String name;
    private final Long userId;

    public JwtBearerAuthenticationToken(Jwt jwt, LoginUserDTO principal) {
        super(jwt, principal, jwt, principal.getAuthorities());
        this.setAuthenticated(true);
        this.name = jwt.getSubject();
        this.userId = principal.getUserId();
    }

    @Override
    public Map<String, Object> getTokenAttributes() {
        return this.getToken().getClaims();
    }

    /**
     * The principal name which is, by default, the {@link Jwt}'s subject
     */
    @Override
    public String getName() {
        return this.name;
    }

    public Long getUserId() {
        return userId;
    }
}
