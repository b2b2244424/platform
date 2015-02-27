package com.lml.platform.core.audit.support;

import com.lml.platform.core.audit.UserToken;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.TextScore;

/**
 * mongo基本的用户token
 * @param <Token> 用户主体对象
 */
public abstract class MongoUserToken<Token extends MongoUserToken> implements UserToken<Token, String> {

    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 认证身份
     */
    @Field
    private Object principal;

    /**
     * 认证凭证
     */
    @Field
    private Object credentials;

    /**
     * 是否记住我
     */
    @TextScore
    private boolean rememberMe;

    /**
     * 地址
     */
    @TextScore
    private String host;

    /**
     * 由那个创建
     */
    @DBRef
    private Token createdBy;

    /**
     * 最后由那个修改
     */
    @DBRef
    private Token lastModifiedBy;

    /**
     * 创建的时间
     */
    @Field
    private DateTime createdDate;

    /**
     * 最后修改的时间
     */
    @Field
    private DateTime lastModifiedDate;

    public MongoUserToken() {
    }

    public MongoUserToken(String principal, String credentials) {
        this.principal = principal;
        this.credentials = credentials;
    }

    public MongoUserToken(String principal, String credentials, boolean rememberMe) {
        this.principal = principal;
        this.credentials = credentials;
        this.rememberMe = rememberMe;
    }

    /**
     * 获取主键
     *
     * @return 主键
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取认证身份
     *
     * @return 认证身份
     */
    @Override
    public Object getPrincipal() {
        return principal;
    }

    /**
     * 设置认证身份
     *
     * @param principal 认证身份
     */
    @Override
    public void setPrincipal(Object principal) {
        this.principal = principal;
    }

    /**
     * 获取认证凭证
     *
     * @return 认证凭证
     */
    @Override
    public Object getCredentials() {
        return credentials;
    }

    /**
     * 设置认证凭证
     *
     * @param credentials 认证凭证
     */
    @Override
    public void setCredentials(Object credentials) {
        this.credentials = credentials;
    }

    /**
     * 获取认证中是否启用记住我
     *
     * @return 认证中是否启用记住我
     */
    @Override
    public boolean isRememberMe() {
        return rememberMe;
    }

    /**
     * 设置认证中是否启用记住我
     *
     * @param rememberMe 认证中是否启用记住我
     */
    @Override
    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    /**
     * 获取主体的地址
     *
     * @return 主体的地址
     */
    @Override
    public String getHost() {
        return host;
    }

    /**
     * 设置主体的地址
     *
     * @param host 主体的地址
     */
    @Override
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * 返回是否持久化的
     *
     * @return 是否持久化的
     */
    @Override
    public boolean isNew() {
        return id == null;
    }

    /**
     * 返回创建的token(由spring audit自动注入)
     *
     * @return 返回创建的token
     */
    @Override
    public Token getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建的token(由spring audit自动注入)
     *
     * @param createdBy 创建的token(由spring audit自动注入)
     */
    @Override
    public void setCreatedBy(Token createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 返回最后修改的token(由spring audit自动注入)
     *
     * @return 最后修改的token(由spring audit自动注入)
     */
    @Override
    public Token getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * 设置最后修改的token(由spring audit自动注入)
     *
     * @param lastModifiedBy 最后修改的token(由spring audit自动注入)
     */
    @Override
    public void setLastModifiedBy(Token lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * 返回创建的日期和时间(由spring audit自动注入)
     *
     * @return 创建的日期和时间(由spring audit自动注入)
     */
    @Override
    public DateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * 设置创建的日期和时间(由spring audit自动注入)
     *
     * @param createdDate 创建的日期和时间(由spring audit自动注入)
     */
    @Override
    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * 返回最后修改的日期和时间(由spring audit自动注入)
     *
     * @return 最后修改的日期和时间(由spring audit自动注入)
     */
    @Override
    public DateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * 设置最后修改的日期和时间(由spring audit自动注入)
     *
     * @param lastModifiedDate 最后修改的日期和时间(由spring audit自动注入)
     */
    @Override
    public void setLastModifiedDate(DateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MongoUserToken)) return false;
        MongoUserToken that = (MongoUserToken) o;
        if (!id.equals(that.id)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
