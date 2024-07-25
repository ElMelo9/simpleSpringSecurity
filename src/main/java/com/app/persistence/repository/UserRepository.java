package com.app.persistence.repository;

import com.app.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {

    Optional<UserEntity> findUserEntityByName(String name);

//    @Query("SELECT U FROM UserEntity u WHERE name = ?")
//    Optional<UserEntity> findUser(String name);
}
