package com.lml.platform.core.audit;

import org.springframework.data.domain.AuditorAware;

public interface UserTokenAuditorAware<Token extends UserToken> extends AuditorAware<Token> {

    /**
     * 返回当前操作用户token
     *
     * @return 当前操作用户token
     */
    @Override
    Token getCurrentAuditor();

}
