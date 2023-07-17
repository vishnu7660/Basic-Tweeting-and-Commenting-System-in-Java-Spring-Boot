package com.vishnu.tweetingcommnetingsystemapi.dao;

import com.vishnu.tweetingcommnetingsystemapi.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User,Long> {
}
