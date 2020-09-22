package apk.put.green4go.trainingCreation;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import androidx.navigation.Navigation;
import apk.put.green4go.R;
import apk.put.green4go.classes.Global;
import apk.put.green4go.classes.Round;
import apk.put.green4go.classes.Shot;
import apk.put.green4go.classes.Weapon;

import static android.app.Activity.RESULT_OK;

public class createTrainingAddRoundFragment extends Fragment {

    ImageButton backButton;
    ImageButton takePhotoButton;
    Spinner weaponUsedInput;
    Button addRoundButton;

    Bitmap photo;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST = 112;

    public createTrainingAddRoundFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_training_add_round, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        ActivityCompat.requestPermissions((Activity) requireContext(), PERMISSIONS, REQUEST );

        backButton = requireView().findViewById(R.id.backButton);
        takePhotoButton = requireView().findViewById(R.id.takePhotoButton);
        weaponUsedInput = requireView().findViewById(R.id.weaponUsedInput);
        addRoundButton = requireView().findViewById(R.id.addRoundButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigateUp();
            }
        });

        takePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestTakePhoto();
            }
        });

        addRoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Weapon weaponChosen = new Weapon(1, "TEC-9", 32, 9.0);
                ArrayList<Shot> shots = new ArrayList<>(
                        Arrays.asList(
                                new Shot(1, 10, 10, 5),
                                new Shot(2, 15, 20, 8),
                                new Shot(3, 8, 11, 2),
                                new Shot(4, 1, 9, 0)
                        ));
                for(Weapon weapon : Global.weaponsList) {
                    if(weapon.getModel().equals(weaponUsedInput.getSelectedItem().toString())) {
                        weaponChosen = weapon;
                    }
                }

                Round round = new Round((Global.newTraining.getRounds().size()+1), weaponChosen, shots, 39, 75, 90, 130);

                Global.newTraining.addRound(round);

                Navigation.findNavController(view).navigateUp();
            }
        });
    }

    public void requestTakePhoto() {
        //SettingsManager.sharedInstance().TAKE_PHOTO = true;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (resultCode == RESULT_OK) {
                photo = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                takePhotoButton.setImageBitmap(photo);
                takePhotoButton.setScaleType(ImageView.ScaleType.FIT_XY);

                photo.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream .toByteArray();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}