package com.company.socialmedia;

import java.time.*;
import java.util.*;
import java.time.Instant;
public class Timeline {

    private Map<Instant, String> timeLine;
    private Map<Instant, String> aggregatedTimeLine;
    private Map<Instant, String> entry;
    public Timeline(){
        timeLine = new TreeMap<>();
        entry = new TreeMap<>();
    }

    public void addToTimeline(String activity){
        Instant postTime = Instant.now().atZone(ZoneId.of(Clock.systemDefaultZone().getZone().getId())).toInstant();
        timeLine.put(postTime, activity);
    }

    public Map<Instant, String> getTimeLine(){
        return this.timeLine;
    }

    public Map<Instant, String> getAggregatedTimeLine(List<User> friends){
        aggregatedTimeLine = new TreeMap<>();
        friends.stream().forEach(e -> aggregatedTimeLine.putAll(e.getTimeLine()));
        aggregatedTimeLine.putAll(getTimeLine());
        return aggregatedTimeLine;
    }

}
