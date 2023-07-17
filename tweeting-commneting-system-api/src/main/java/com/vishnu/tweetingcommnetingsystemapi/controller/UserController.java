package com.vishnu.tweetingcommnetingsystemapi.controller;

import com.vishnu.tweetingcommnetingsystemapi.dao.TweetDao;
import com.vishnu.tweetingcommnetingsystemapi.dao.UserDao;
import com.vishnu.tweetingcommnetingsystemapi.model.Tweet;
import com.vishnu.tweetingcommnetingsystemapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequestMapping("/users")
@RestController
public class UserController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private TweetDao tweetDao;

    @PostMapping
    public User createUser(@RequestBody User user){
        return userDao.save(user);
    }

    @GetMapping
    public List<User> getAllUsers(){

        List<User> users = new ArrayList<>();
        Iterable<User> all = userDao.findAll();
        all.forEach(user -> users.add(user));

        return users;
    }

    @GetMapping("/{id}/tweets")
    public Set<Tweet> getAllTweetsByUserId(@PathVariable("id") Long id){
        Set<Tweet> tweets = new HashSet<>();
        tweetDao.findAll().forEach(tweet -> {
            if(tweet.getUser().getId() == id){
                tweets.add(tweet);
            }
        });

        return tweets;
    }

}
