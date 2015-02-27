
package com.lml.platform.core.util.authentication;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lml.platform.core.model.authentication.User;
import com.lml.platform.core.support.audit.TokenAuditorAware;

public class UserTokenAware implements TokenAuditorAware<User> {

    /**
     * slf4j logger
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static User defaultTestUser = null;

    public static final void setDefaultTestUser(User user) {
        defaultTestUser = user;
    }

    public UserTokenAware() {
    }


    @Override
    public User getCurrentAuditor() {
        User user = null;
        Subject subject = tryGetSubject();
        logger.debug("调用了 subject is {}", subject);
        if (subject != null) {
            Object principal = subject.getPrincipal();
            user = tryConvertPrincipalToUser(principal);
            if (user == null) {
                PrincipalCollection principalCollection = subject.getPrincipals();
                for (Object principalItem : principalCollection) {
                    user = tryConvertPrincipalToUser(principalItem);
                    if (user != null) {
                        break;
                    }
                }
            }
        }
        if (user == null) {
            user = UserTokenAware.defaultTestUser;
        }
        logger.debug("user is {}", user);
        return user;
    }

    /**
     * 尝试转换身份对象为用户对象
     *
     * @param principal 身份对象
     * @return 成功返回用户对象, 失败返回空
     */
    public User tryConvertPrincipalToUser(Object principal) {
        return principal != null ? ((principal instanceof User) ? ((User) principal) : null) : null;
    }

    /**
     * 尝试获取当前操作主体对象
     *
     * @return 当前操作的主体对象
     */
    private Subject tryGetSubject() {
        Subject subject = null;
        try {
            subject = SecurityUtils.getSubject();
        } catch (Exception e) {
            // e.printStackTrace();
            logger.warn(e.getMessage());
        }
        return subject;
    }

}
