package apk.put.green4go.trainingCreation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import apk.put.green4go.R;
import apk.put.green4go.adapters.RoundListAdapter;
import apk.put.green4go.adapters.TrainingListAdapter;
import apk.put.green4go.classes.Global;
import apk.put.green4go.classes.Training;

public class createTrainingMainFragment extends Fragment {

    Button addRoundButton;
    ImageButton backButton;
    RecyclerView roundList;
    RecyclerView.Adapter adapter;

    public createTrainingMainFragment() {
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
        return inflater.inflate(R.layout.fragment_create_training_main, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        addRoundButton = requireView().findViewById(R.id.addRoundButton);
        backButton = requireView().findViewById(R.id.backButton);
        roundList = requireView().findViewById(R.id.roundList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(requireContext());
        roundList.setLayoutManager(mLayoutManager);

        adapter = new RoundListAdapter(Global.newTraining.getRounds());
        roundList.setAdapter(adapter);


        addRoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.create_training_main_to_add_round);
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