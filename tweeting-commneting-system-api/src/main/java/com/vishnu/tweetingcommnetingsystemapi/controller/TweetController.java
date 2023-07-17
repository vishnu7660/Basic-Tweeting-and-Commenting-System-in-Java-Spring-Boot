package com.vishnu.tweetingcommnetingsystemapi.controller;

import com.vishnu.tweetingcommnetingsystemapi.dao.TweetDao;
import com.vishnu.tweetingcommnetingsystemapi.dao.UserDao;
import com.vishnu.tweetingcommnetingsystemapi.model.Tweet;
import com.vishnu.tweetingcommnetingsystemapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tweets")
public class TweetController {

    @Autowired
    private TweetDao tweetDao;

    @Autowired
    private UserDao userDao;

    @PostMapping(path = "/{id}")
    private Tweet createTweet(@PathVariable("id") Long id, @RequestBody Tweet tweet){

        User user = userDao.findById(id).orElseThrow(() -> new RuntimeException("User not found"));


        Tweet tweet1 = new Tweet();
        tweet1.setId(tweet.getId());
        tweet1.setContent(tweet.getContent());
        tweet1.setTimestamp(LocalDateTime.now());
        tweet1.setUser(user);
        return tweetDao.save(tweet1);
    }


    @GetMapping
    private List<Tweet> getTweets(){

        List<Tweet> tweets = new ArrayList<>();
        Iterable<Tweet> all = tweetDao.findAll();
        all.forEach(tweet -> tweets.add(tweet));
        return tweets;
    }

    @GetMapping(path = "/{userId}/{tweetId}")
    public String deleteTweet(@PathVariable("userId") Long userId, @PathVariable("tweetId") Long tweetId){
        userDao.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Tweet tweet = tweetDao.findById(tweetId).orElseThrow(() -> new RuntimeException("Tweet id not found"));
        tweetDao.delete(tweet);

        return "Deleted Tweet Successfuly";
    }

}
