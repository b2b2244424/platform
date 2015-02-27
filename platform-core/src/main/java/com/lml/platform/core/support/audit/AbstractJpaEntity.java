
package com.lml.platform.core.support.audit;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractJpaEntity<Token extends AbstractJpaAuthenticationToken<Token, ID>, ID extends Serializable> extends AbstractJpaAuditable<Token, ID> {

	private static final long serialVersionUID = 1L;

}
