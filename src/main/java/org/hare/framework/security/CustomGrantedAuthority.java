package org.hare.framework.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

/**
 * Basic concrete implementation of a {@link GrantedAuthority}.
 *
 * <p>
 * Stores a {@code String} representation of an authority granted to the
 * {@link org.springframework.security.core.Authentication Authentication} object.
 *
 * @author wang cheng
 */
public final class CustomGrantedAuthority implements GrantedAuthority {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	private String authority;

	public CustomGrantedAuthority() {
	}

	public CustomGrantedAuthority(String authority) {
		Assert.hasText(authority, "A granted authority textual representation is required");
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return this.authority;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof CustomGrantedAuthority) {
			return this.authority.equals(((CustomGrantedAuthority) obj).authority);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.authority.hashCode();
	}

	@Override
	public String toString() {
		return this.authority;
	}

}
