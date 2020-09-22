package apk.put.green4go.classes;

public class Shot {
    int id;
    int coordinatesX;
    int coordinatesY;
    int points;

    public Shot(int id, int coordinatesX, int coordinatesY, int points) {
        this.id = id;
        this.coordinatesX = coordinatesX;
        this.coordinatesY = coordinatesY;
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoordinatesX() {
        return coordinatesX;
    }

    public void setCoordinatesX(int coordinatesX) {
        this.coordinatesX = coordinatesX;
    }

    public int getCoordinatesY() {
        return coordinatesY;
    }

    public void setCoordinatesY(int coordinatesY) {
        this.coordinatesY = coordinatesY;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
