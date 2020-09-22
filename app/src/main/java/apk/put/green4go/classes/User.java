package apk.put.green4go.classes;

import java.util.ArrayList;

public class User {
    int id;
    String username;
    String email;
    String avatar;
    int streak;
    int xpPoints;
    ArrayList<Training> trainings;
    ArrayList<Achievment> achievments;

    public User() {
        this.id = 0;
    }

    public User(int id, String username, String email, String avatar, int streak, int xpPoints) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.avatar = avatar;
        this.streak = streak;
        this.xpPoints = xpPoints;
        this.trainings = null;
        this.achievments = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    public int getXpPoints() {
        return xpPoints;
    }

    public void setXpPoints(int xpPoints) {
        this.xpPoints = xpPoints;
    }

    public ArrayList<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(ArrayList<Training> trainings) {
        this.trainings = trainings;
    }

    public void addTraining(Training training) {
        this.trainings.add(training);
    }

    public ArrayList<Achievment> getAchievments() {
        return achievments;
    }

    public void setAchievments(ArrayList<Achievment> achievments) {
        this.achievments = achievments;
    }

    public void addAchievement(Achievment achievment) {
        this.achievments.add(achievment);
    }
}
