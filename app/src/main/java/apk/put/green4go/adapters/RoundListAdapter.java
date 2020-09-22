package apk.put.green4go.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import apk.put.green4go.R;
import apk.put.green4go.classes.Round;
import apk.put.green4go.classes.Training;

public class RoundListAdapter extends RecyclerView.Adapter {
    private ArrayList<Round> rounds;

    public RoundListAdapter(ArrayList<Round> rounds) {
        this.rounds = rounds;
    }

    @NonNull
    @Override
    public RoundViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.rounds_list_row, parent, false);

        return new RoundViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Round round = rounds.get(position);
        ((RoundViewHolder)holder).trainingTitle.setText("Round "+String.valueOf(position+1));
        ((RoundViewHolder)holder).traningPoints.setText(String.valueOf(round.getPoints()));
        ((RoundViewHolder)holder).accuracy.setText(String.valueOf(round.getAccuracy())+"%");
        ((RoundViewHolder)holder).precision.setText(String.valueOf(round.getPrecision())+"%");
        ((RoundViewHolder)holder).overallScore.setText(String.valueOf(round.getOverallScore()));
    }

    @Override
    public int getItemCount() {
        if (rounds != null) {
            return rounds.size();
        } else {
            return 0;
        }
    }


    public static class RoundViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView trainingTitle;
        public final TextView traningPoints;
        public final TextView accuracy;
        public final TextView precision;
        public final TextView overallScore;
        public final ImageButton removeRound;

        public RoundViewHolder(View view) {
            super(view);
            this.view = view;
            trainingTitle = view.findViewById(R.id.trainingTitle);
            traningPoints = view.findViewById(R.id.traningPoints);
            accuracy = view.findViewById(R.id.accuracy);
            precision = view.findViewById(R.id.precision);
            overallScore = view.findViewById(R.id.overallScore);
            removeRound = view.findViewById(R.id.removeRound);
        }
    }
}
