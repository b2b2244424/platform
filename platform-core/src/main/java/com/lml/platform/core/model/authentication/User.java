
package com.lml.platform.core.model.authentication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lml.platform.core.support.audit.AbstractToken;

@Entity
@Table(name = "AUTHENTICATION_USER")
public class User extends AbstractToken<User> {

	private static final long serialVersionUID = 1L;

	@Column(name = "_NICKNAME", length = 16, nullable = true, unique = false)
	private String nickname;

	public User() {
	}

	public User(String principal, String credentials) {
		super(principal, credentials);
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
