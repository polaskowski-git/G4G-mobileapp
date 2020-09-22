package apk.put.green4go.classes;

public class Weapon {
    int id;
    String model;
    int magazineSize;
    double caliberSize;

    public Weapon(int id, String model, int magazineSize, double caliberSize) {
        this.id = id;
        this.model = model;
        this.magazineSize = magazineSize;
        this.caliberSize = caliberSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMagazineSize() {
        return magazineSize;
    }

    public void setMagazineSize(int magazineSize) {
        this.magazineSize = magazineSize;
    }

    public double getCaliberSize() {
        return caliberSize;
    }

    public void setCaliberSize(double caliberSize) {
        this.caliberSize = caliberSize;
    }
}
