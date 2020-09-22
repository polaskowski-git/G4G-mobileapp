package apk.put.green4go.classes;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Round {
    int id;
    Weapon weapon;
    ArrayList<Shot> shots;
    Bitmap photo;
    String basePhoto;
    int points;
    int accuracy;
    int precision;
    int overallScore;

    public Round(int id, Weapon weapon, ArrayList<Shot> shots, int points, int accuracy, int precision, int overallScore) {
        this.id = id;
        this.weapon = weapon;
        this.shots = shots;
        this.points = points;
        this.accuracy = accuracy;
        this.precision = precision;
        this.overallScore = overallScore;
        this.photo = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public ArrayList<Shot> getShots() {
        return shots;
    }

    public void addShot(Shot shot){
        this.shots.add(shot);
    }

    public void setShots(ArrayList<Shot> shots) {
        this.shots = shots;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public int getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(int overallScore) {
        this.overallScore = overallScore;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
