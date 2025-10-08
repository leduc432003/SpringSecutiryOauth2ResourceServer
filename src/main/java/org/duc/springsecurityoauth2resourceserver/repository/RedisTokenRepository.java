package org.duc.springsecurityoauth2resourceserver.repository;

import org.duc.springsecurityoauth2resourceserver.model.RedisToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisTokenRepository extends CrudRepository<RedisToken, String> {
}
