
package com.lml.platform.core.support.audit;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.apache.shiro.authc.AuthenticationToken;

@MappedSuperclass
public abstract class AbstractJpaAuthenticationToken<Token extends AbstractJpaAuthenticationToken<Token, ID>, ID extends Serializable> extends AbstractJpaAuditable<Token, ID> implements AuthenticationToken {

	private static final long serialVersionUID = 1L;

	/**
	 * 认证身份
	 */
	@Column(name = "_PRINCIPAL", length = 64, nullable = false, unique = true)
	private String principal;

	/**
	 * 认证凭证
	 */
	@Column(name = "_CREDENTIALS", length = 127, nullable = false, unique = false)
	private String credentials;

	public AbstractJpaAuthenticationToken() {
	}

	public AbstractJpaAuthenticationToken(String principal, String credentials) {
		this.principal = principal;
		this.credentials = credentials;
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
	 * @param principal
	 *            认证身份
	 */
	public void setPrincipal(String principal) {
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
	 * @param credentials
	 *            认证凭证
	 */
	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

}
