package apk.put.green4go.classes;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class Global {
    public static User user;
    public static String apiToken;
    public static ArrayList<Training> trainings;
    public static ArrayList<Weapon> weaponsList;
    public static Training newTraining;

    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static void populateTestData() {
        weaponsList = new ArrayList<>();
        Weapon testWeapon = new Weapon(1, "TEC-9", 32, 9.0);
        Weapon testWeapon2 = new Weapon(2, "Glock", 32, 9.0);
        Weapon testWeapon3 = new Weapon(3, "Desert Eagle", 32, 9.0);
        weaponsList.add(testWeapon);
        weaponsList.add(testWeapon2);
        weaponsList.add(testWeapon3);
        ArrayList<Shot> shots = new ArrayList<>(
                Arrays.asList(
                        new Shot(1, 10, 10, 5),
                        new Shot(2, 15, 20, 8),
                        new Shot(3, 8, 11, 2),
                        new Shot(4, 1, 9, 0)
                ));
        ArrayList<Round> rounds = new ArrayList<>(Arrays.asList(
                new Round(1, weaponsList.get(0), shots, 15, 85, 75, 60),
                new Round(2, weaponsList.get(0), shots, 15, 85, 75, 60),
                new Round(3, weaponsList.get(0), shots, 15, 85, 75, 60),
                new Round(4, weaponsList.get(0), shots, 15, 85, 75, 60)
        ));

        trainings = new ArrayList<>(Arrays.asList(
                new Training(1, "Training with boys", 20, "2021-09-07 14:08:43", "2021-09-07 15:10:43", rounds, 250),
                new Training(2, "Training without boys", 10, "2021-09-07 14:08:43", "2021-09-07 15:10:43", rounds, 250),
                new Training(3, "Training with big boys", 20, "2021-09-07 14:08:43", "2021-09-07 15:10:43", rounds, 250),
                new Training(4, "Training with small boys", 20, "2021-09-07 14:08:43", "2021-09-07 15:10:43", rounds, 250),
                new Training(5, "Not training with boys", 16, "2021-09-07 14:08:43", "2021-09-07 15:10:43", rounds, 250),
                new Training(6, "Crushed by boys", 1, "2021-09-07 14:08:43", "2021-09-07 15:10:43", rounds, 250),
                new Training(7, "Training with boys", 11, "2021-09-07 14:08:43", "2021-09-07 15:10:43", rounds, 250)
        ));
    }
}
