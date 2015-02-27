
package com.lml.platform.core.support.audit;

import org.springframework.data.domain.AuditorAware;

public interface TokenAuditorAware<Token extends AbstractToken<Token>> extends AuditorAware<Token> {

	/**
	 * 返回当前操作用户token
	 * 
	 * @return 当前操作用户token
	 */
	@Override
	Token getCurrentAuditor();

}
