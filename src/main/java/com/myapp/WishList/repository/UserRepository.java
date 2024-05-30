package com.myapp.WishList.repository;

import com.myapp.WishList.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findUserByLogin(String userLogin);
}
