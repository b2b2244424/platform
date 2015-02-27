package com.lml.platform.core.model.authentication;

import com.lml.platform.core.support.audit.AbstractEntity;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class AbstractUserEntity extends AbstractEntity<User> {
}
