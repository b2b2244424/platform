package com.lml.platform.core.audit;

import org.joda.time.DateTime;
import org.springframework.data.domain.Auditable;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;

public interface EntityAudit<Token extends UserToken, ID extends Serializable> extends Auditable<Token, ID>, Persistable<ID> {

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
