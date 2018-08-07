package com.company.socialmedia;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

//Process all the user action for Social Media Application
public class ActionHandler {
    List<User> Users;
    User currentUser;

    public ActionHandler(List<User> setUserList) {
        this.Users = setUserList;
    }

    public User getUser(String userName, String userType, String actionType) {
        User user = Users.stream().filter(
                e -> e.getUserName().toLowerCase().equals(userName.toLowerCase())).findFirst().orElse(null);
        if (user == null && userType.equals("currentuser") && actionType == "posting") {
            user = new User(userName);
            Users.add(user);
        }
        return user;
    }

    public void processAction(String userInput) {
        String userName = userInput.split(" ")[0];
        if (userInput.contains("->")) {
            currentUser = getUser(userName, "currentuser", "posting");
            userPost(currentUser, userInput.split("->")[1].trim());
        } else {
            currentUser = getUser(userName, "currentuser", "other");
            if (currentUser != null) {
                if (userInput.contains("wall")) {
                    printTimeline(currentUser, "wall");
                } else if (userInput.contains("follows")) {
                    if (currentUser != null) {
                        User newFriend = getUser(userInput.split("follows")[1].trim(), "friend", "following");
                        followFriend(currentUser, newFriend);
                    }
                } else if (userInput.split(" ").length == 1) {
                    if (currentUser != null) {
                        printTimeline(currentUser, "personal");
                    }
                }
            } else {
                System.out.println("No such user");
            }

        }
    }

    public void userPost(User currentUser, String userPost) {
        currentUser.addToTimeline(currentUser.getUserName() + " : " + userPost);
    }

    public void printTimeline(User currentUser, String timeLineType) {
        Map<Instant, String> timeLine;
        if (timeLineType == "wall") {
            timeLine = currentUser.getAggregatedTimeLine(currentUser.getFriends());
        } else {
            timeLine = currentUser.getTimeLine();
        }
        timeLine.forEach((k, v) -> {
            Instant currently = Instant.now().atZone(ZoneId.of(Clock.systemDefaultZone().getZone().getId())).toInstant();
            if (Duration.between(k, Instant.now()).toMinutes() == 0) {
                System.out.println(v + "  (" + Duration.between(k, Instant.now().atZone(ZoneId.of(Clock.systemDefaultZone().getZone().getId()))).getSeconds() + " seconds ago)");
            } else {
                System.out.println(v + "  (" + Duration.between(k, Instant.now().atZone(ZoneId.of(Clock.systemDefaultZone().getZone().getId()))).toMinutes() + " minutes ago)");
            }
        });
    }

    public void followFriend(User currentUser, User friend) {
        if (friend == null) {
            System.out.println("No such user");
        } else {
            currentUser.followFriend(currentUser, friend);
        }
    }

}



