package com.vishnu.tweetingcommnetingsystemapi.dao;

import com.vishnu.tweetingcommnetingsystemapi.model.Tweet;
import org.springframework.data.repository.CrudRepository;

public interface TweetDao extends CrudRepository<Tweet, Long> {

}
