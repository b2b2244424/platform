
package com.lml.platform.core.support.audit;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractToken<Token extends AbstractToken<Token>> extends AbstractJpaAuthenticationToken<Token, Long> {

	private static final long serialVersionUID = 1L;

	public AbstractToken() {
	}

	public AbstractToken(String principal, String credentials) {
		super(principal, credentials);
	}

}
