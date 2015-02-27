
package com.lml.platform.core.support.audit;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractEntity<Token extends AbstractToken<Token>> extends AbstractJpaEntity<Token, Long> {

	private static final long serialVersionUID = 1L;

}
