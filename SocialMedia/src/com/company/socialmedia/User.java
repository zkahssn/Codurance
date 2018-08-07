package com.company.socialmedia;

import java.util.ArrayList;
import java.util.List;

public class User extends Timeline {

    private String userName;
    private String userMessage;
    private List<User> friends;
    private List<String> wall;

    public User(String userName) {
        this.userName = userName;
        this.friends = new ArrayList<>();
        this.wall = new ArrayList<>();
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void followFriend(User current, User follow) {
        current.friends.add(follow);
        addToTimeline(current.getUserName() + "  is now following " + follow.getUserName());
    }

    public List<User> getFriends() {
        return this.friends;
    }

}
