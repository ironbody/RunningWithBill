package com.example.runningwithbill.ui.map;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.runningwithbill.databinding.FragmentMapBinding;
import com.example.runningwithbill.location.LocationService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;
import java.util.Map;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private FragmentMapBinding binding;
    private GoogleMap googleMap;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MapViewModel homeViewModel =
                new ViewModelProvider(this).get(MapViewModel.class);

        binding = FragmentMapBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        MapView mapView = binding.mapView;

        mapView.onCreate(savedInstanceState);
        mapView.onResume();

        mapView.getMapAsync(this);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        googleMap = map;

        LocationService.Companion.getLocations().observe(this,
                new Observer<List<Location>>() {
                    Polyline polyline = null;
                    @Override
                    public void onChanged(List<Location> locations) {
                        int size = locations.size();
                        Location current = locations.get(size - 1);
                        LatLng latLng = new LatLng(current.getLatitude(), current.getLongitude());
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15f));

                        PolylineOptions options = getPolylineFromLocations(locations);
                        options.color(Color.CYAN);
                        Polyline newLine = googleMap.addPolyline(options);
                        if (polyline != null) {
                            polyline.remove();
                        }
                        polyline = newLine;
                    }
                });
    }

    private PolylineOptions getPolylineFromLocations(List<Location> locations) {
        PolylineOptions options = new PolylineOptions();
        for (Location loc:
             locations) {
            LatLng latLng = new LatLng(loc.getLatitude(), loc.getLongitude());
            options.add(latLng);
        }

        return options;
    }
}