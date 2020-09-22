package apk.put.green4go.classes;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Training {
    int id;
    String name;
    int distanceFromTarget;
    String startDateTime;
    String endDateTime;
    ArrayList<Round> rounds;
    int earnedXp;

    public Training() {
        this.rounds = new ArrayList<>();
    }

    public Training(int id, String name, int distanceFromTarget, String startDateTime, String endDateTime, ArrayList<Round> rounds, int earnedXp) {
        this.id = id;
        this.name = name;
        this.distanceFromTarget = distanceFromTarget;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.rounds = rounds;
        this.earnedXp = earnedXp;
        this.rounds = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public int getEarnedXp() {
        return earnedXp;
    }

    public void setEarnedXp(int earnedXp) {
        this.earnedXp = earnedXp;
    }

    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public void addRound(Round round) {
        this.rounds.add(round);
    }

    public void setRounds(ArrayList<Round> rounds) {
        this.rounds = rounds;
    }

    public int getDistanceFromTarget() {
        return distanceFromTarget;
    }

    public void setDistanceFromTarget(int distanceFromTarget) {
        this.distanceFromTarget = distanceFromTarget;
    }

    public int getPoints() {
        int points = 0;
        for(int i = 0; i < rounds.size(); i++)
        {
            points += rounds.get(i).getPoints();
        }
        return points;
    }
}
