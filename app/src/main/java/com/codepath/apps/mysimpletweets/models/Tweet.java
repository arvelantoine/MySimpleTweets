package com.codepath.apps.mysimpletweets.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**

 */

// Parse the JSON * Store the data,  encapsulate state logic or display logic
public class Tweet {
    // list out the attributes
    private String body;
    private long uid;
    //private User user;
    private User user; // store embedded user  object
    private String createdAt;

    public User getUser() {
        return user;
    }

    public String getBody() {
        return body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public long getUid() {
        return uid;
    }

    //deserialize the JSON and build tweet objects
    // Tweet.from JSON("{....}")
    public static Tweet fromJSON(JSONObject jsonObject){
        Tweet tweet = new Tweet();
        // extract the values from the json
        try {
            tweet.body = jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        }catch (JSONException e){
            e.printStackTrace();
        }


        // return the tweet object
        return tweet;
    }
    // tweet from jasonarray
    public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArray){
        ArrayList<Tweet> tweets = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++){
            try {
                JSONObject TweetJson = jsonArray.getJSONObject(i);
                Tweet tweet = Tweet.fromJSON(TweetJson);
                if (tweet != null){
                    tweets.add(tweet);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }
        return tweets;
    }
}
