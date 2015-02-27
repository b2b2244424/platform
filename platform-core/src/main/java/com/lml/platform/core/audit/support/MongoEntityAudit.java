package com.lml.platform.core.audit.support;


import com.lml.platform.core.audit.EntityAudit;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * mongo基本的实体
 *
 * @param <Token> 用户主体对象
 */
public abstract class MongoEntityAudit<Token extends MongoUserToken> implements EntityAudit<Token, String> {

    /**
     * 主键
     */
    @Id
    private String id;

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

    public MongoEntityAudit() {
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


}
