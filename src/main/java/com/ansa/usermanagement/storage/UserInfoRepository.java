package com.ansa.usermanagement.storage;

import org.springframework.data.repository.CrudRepository;

public interface UserInfoRepository extends CrudRepository<UserInfo, Long>{
    UserInfo findByToken(String token);
    UserInfo findByUsername(String username);
}
