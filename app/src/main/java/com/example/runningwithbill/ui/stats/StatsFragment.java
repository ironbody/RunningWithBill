package com.example.runningwithbill.ui.stats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.runningwithbill.R;
import com.example.runningwithbill.dataObject.DBStatViewModel;
import com.example.runningwithbill.dataObject.Stats;
import com.example.runningwithbill.databinding.FragmentStatsBinding;

import com.example.runningwithbill.dataObject.DBStatViewModel;

public class StatsFragment extends Fragment {

    private FragmentStatsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StatsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(StatsViewModel.class);

        binding = FragmentStatsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        DBStatViewModel DBStats;
        DBStats = new ViewModelProvider(this).get(DBStatViewModel.class);

        // Stats stats = new Stats(0, 0,0,0,0);

        // DBStats.addStats(stats);

        TextView totalText = root.findViewById(R.id.totalDistanceNumber);
        DBStats.readTotal.observe(getViewLifecycleOwner(), totalNR -> {
            totalText.setText(totalNR.toString());
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}