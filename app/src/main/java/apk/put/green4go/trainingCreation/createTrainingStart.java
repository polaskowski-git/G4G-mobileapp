package apk.put.green4go.trainingCreation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import apk.put.green4go.R;
import apk.put.green4go.classes.Global;
import apk.put.green4go.classes.Training;

public class createTrainingStart extends Fragment {

    Button continueButton;
    ImageButton backButton;
    EditText trainingNameInput;
    EditText distanceInput;

    public createTrainingStart() {
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
        return inflater.inflate(R.layout.fragment_create_training_start, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        backButton = requireView().findViewById(R.id.backButton);
        continueButton = requireView().findViewById(R.id.continueButton);
        trainingNameInput = requireView().findViewById(R.id.trainingNameInput);
        distanceInput = requireView().findViewById(R.id.distanceInput);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!trainingNameInput.getText().toString().isEmpty() && !distanceInput.getText().toString().isEmpty()) {
                    Global.newTraining = new Training();
                    Global.newTraining.setName(trainingNameInput.getText().toString());
                    Global.newTraining.setDistanceFromTarget(Integer.parseInt(distanceInput.getText().toString()));
                    Navigation.findNavController(view).navigate(R.id.create_to_training);
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigateUp();
            }
        });
    }
}