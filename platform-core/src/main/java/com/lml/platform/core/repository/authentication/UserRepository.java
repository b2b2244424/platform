package com.lml.platform.core.repository.authentication;

import com.lml.platform.core.model.authentication.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
