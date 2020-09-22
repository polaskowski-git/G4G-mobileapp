package apk.put.green4go.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import apk.put.green4go.R;
import apk.put.green4go.classes.Training;

public class TrainingListAdapter extends RecyclerView.Adapter {
    private ArrayList<Training> trainings;

    public TrainingListAdapter(ArrayList<Training> trainings) {
        this.trainings = trainings;
    }

    @NonNull
    @Override
    public TrainingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list_row, parent, false);

        return new TrainingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Training training = trainings.get(position);
        ((TrainingViewHolder)holder).trainingTitle.setText(training.getName());
        ((TrainingViewHolder)holder).traningPoints.setText(String.valueOf(training.getPoints()));
        ((TrainingViewHolder)holder).trainingTime.setText("1h 20min");
        ((TrainingViewHolder)holder).trainingDate.setText(training.getStartDateTime());
    }

    @Override
    public int getItemCount() {
        if (trainings != null) {
            return trainings.size();
        } else {
            return 0;
        }
    }


    public static class TrainingViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView trainingTitle;
        public final TextView traningPoints;
        public final TextView trainingTime;
        public final TextView trainingDate;
        public final ImageButton editTraining;

        public TrainingViewHolder(View view) {
            super(view);
            this.view = view;
            trainingTitle = view.findViewById(R.id.trainingTitle);
            traningPoints = view.findViewById(R.id.traningPoints);
            trainingTime = view.findViewById(R.id.trainingTime);
            trainingDate = view.findViewById(R.id.trainingDate);
            editTraining = view.findViewById(R.id.editTraining);
        }
    }
}
