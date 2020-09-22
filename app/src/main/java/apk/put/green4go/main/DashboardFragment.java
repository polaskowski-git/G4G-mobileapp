package apk.put.green4go.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import apk.put.green4go.R;
import apk.put.green4go.adapters.TrainingListAdapter;
import apk.put.green4go.classes.Global;

public class DashboardFragment extends Fragment {

    FloatingActionButton goToCreateButton;
    RecyclerView trainingList;
    RecyclerView.Adapter adapter;
    public DashboardFragment() {
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
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Global.populateTestData();
        goToCreateButton = requireView().findViewById(R.id.goToCreateButton);
        trainingList = requireView().findViewById(R.id.trainingList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(requireContext());
        trainingList.setLayoutManager(mLayoutManager);

        adapter = new TrainingListAdapter(Global.trainings);
        trainingList.setAdapter(adapter);

        goToCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.dashboard_to_create_training);
            }
        });
    }
}