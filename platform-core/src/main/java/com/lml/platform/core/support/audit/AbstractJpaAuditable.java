
package com.lml.platform.core.support.audit;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Auditable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class AbstractJpaAuditable<Token extends AbstractJpaPersistable<ID>, ID extends Serializable> extends AbstractJpaPersistable<ID> implements Auditable<Token, ID> {

	private static final long serialVersionUID = 1L;

	/**
	 * 由那个创建
	 */
	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "_CREATED_BY", unique = false, nullable = true)
	@CreatedBy
	@JsonIgnore
	private Token createdBy;

	/**
	 * 最后由那个修改
	 */
	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "_LAST_MODIFIED_BY", unique = false, nullable = true)
	@LastModifiedBy
	@JsonIgnore
	private Token lastModifiedBy;

	/**
	 * 创建的时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "_CREATED_DATE", nullable = true, unique = false)
	@CreatedDate
	private Date createdDate;

	/**
	 * 最后修改的时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "_LAST_MODIFIED_DATE", nullable = true, unique = false)
	@LastModifiedDate
	private Date lastModifiedDate;

	public AbstractJpaAuditable() {
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
	 * @param createdBy
	 *            创建的token(由spring audit自动注入)
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
	 * @param lastModifiedBy
	 *            最后修改的token(由spring audit自动注入)
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
		return null == createdDate ? null : new DateTime(createdDate);
	}

	/**
	 * 设置创建的日期和时间(由spring audit自动注入)
	 * 
	 * @param createdDate
	 *            创建的日期和时间(由spring audit自动注入)
	 */
	@Override
	public void setCreatedDate(DateTime createdDate) {
		this.createdDate = null == createdDate ? null : createdDate.toDate();
	}

	/**
	 * 返回最后修改的日期和时间(由spring audit自动注入)
	 * 
	 * @return 最后修改的日期和时间(由spring audit自动注入)
	 */
	@Override
	public DateTime getLastModifiedDate() {
		return null == lastModifiedDate ? null : new DateTime(lastModifiedDate);
	}

	/**
	 * 设置最后修改的日期和时间(由spring audit自动注入)
	 * 
	 * @param lastModifiedDate
	 *            最后修改的日期和时间(由spring audit自动注入)
	 */
	@Override
	public void setLastModifiedDate(DateTime lastModifiedDate) {
		this.lastModifiedDate = null == lastModifiedDate ? null : lastModifiedDate.toDate();
	}

}
