package com.lml.platform.core.audit;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;
import org.joda.time.DateTime;
import org.springframework.data.domain.Auditable;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;

/**
 * 用户Token
 *
 * @author lanmingle
 */
public interface UserToken<Token extends UserToken, ID extends Serializable> extends AuthenticationToken, RememberMeAuthenticationToken, HostAuthenticationToken, Persistable<ID>, Auditable<Token, ID> {

    /**
     * 获取主键
     *
     * @return 主键
     */
    @Override
    ID getId();

    /**
     * 设置主键
     *
     * @param id 主键
     */
    void setId(ID id);

    /**
     * 获取认证身份
     *
     * @return 认证身份
     */
    @Override
    Object getPrincipal();

    /**
     * 设置认证身份
     *
     * @param principal 认证身份
     */
    void setPrincipal(Object principal);

    /**
     * 获取认证凭证
     *
     * @return 认证凭证
     */
    @Override
    Object getCredentials();

    /**
     * 设置认证凭证
     *
     * @param credentials 认证凭证
     */
    void setCredentials(Object credentials);

    /**
     * 获取认证中是否启用记住我
     *
     * @return 认证中是否启用记住我
     */
    @Override
    boolean isRememberMe();

    /**
     * 设置认证中是否启用记住我
     *
     * @param rememberMe 认证中是否启用记住我
     */
    void setRememberMe(boolean rememberMe);

    /**
     * 获取主体的地址
     *
     * @return 主体的地址
     */
    @Override
    String getHost();

    /**
     * 设置主体的地址
     *
     * @param host 主体的地址
     */
    void setHost(String host);

    /**
     * 返回是否持久化的
     *
     * @return 是否持久化的
     */
    @Override
    boolean isNew();

    /**
     * 返回创建的token(由spring audit自动注入)
     *
     * @return 返回创建的token
     */
    @Override
    Token getCreatedBy();

    /**
     * 设置创建的token(由spring audit自动注入)
     *
     * @param createdBy 创建的token(由spring audit自动注入)
     */
    @Override
    void setCreatedBy(Token createdBy);

    /**
     * 返回最后修改的token(由spring audit自动注入)
     *
     * @return 最后修改的token(由spring audit自动注入)
     */
    @Override
    Token getLastModifiedBy();

    /**
     * 设置最后修改的token(由spring audit自动注入)
     *
     * @param lastModifiedBy 最后修改的token(由spring audit自动注入)
     */
    @Override
    void setLastModifiedBy(Token lastModifiedBy);

    /**
     * 返回创建的日期和时间(由spring audit自动注入)
     *
     * @return 创建的日期和时间(由spring audit自动注入)
     */
    @Override
    DateTime getCreatedDate();

    /**
     * 设置创建的日期和时间(由spring audit自动注入)
     *
     * @param createdDate 创建的日期和时间(由spring audit自动注入)
     */
    @Override
    void setCreatedDate(DateTime createdDate);

    /**
     * 返回最后修改的日期和时间(由spring audit自动注入)
     *
     * @return 最后修改的日期和时间(由spring audit自动注入)
     */
    @Override
    DateTime getLastModifiedDate();

    /**
     * 设置最后修改的日期和时间(由spring audit自动注入)
     *
     * @param lastModifiedDate 最后修改的日期和时间(由spring audit自动注入)
     */
    @Override
    void setLastModifiedDate(DateTime lastModifiedDate);

}
