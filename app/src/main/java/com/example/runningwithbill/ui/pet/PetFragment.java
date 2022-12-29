package com.example.runningwithbill.ui.pet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.runningwithbill.R;
import com.example.runningwithbill.databinding.FragmentPetBinding;

import com.example.runningwithbill.models.PetModel;

public class PetFragment extends Fragment {

    //Private variables
    private final ImageButton[] buttons = new ImageButton[4];
    private final TextView[] texts = new TextView[7];
    private final ImageView[] bodyParts = new ImageView[4];
    private final ProgressBar[] pBar = new ProgressBar[2];

    private int beakWidth = 0;
    private int hatHeight = 0;
    private int bodyWidth = 0;
    private int bodyHeight = 0;

    private ConstraintLayout layout;

    private FragmentPetBinding binding;

    private PetModel petmodel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PetViewModel dashboardViewModel =
                new ViewModelProvider(this).get(PetViewModel.class);

        binding = FragmentPetBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Create objects
        petmodel = new PetModel();

        //Get all buttons
        buttons[0] = root.findViewById((R.id.beakButton));
        buttons[1] = root.findViewById((R.id.hatButton));
        buttons[2] = root.findViewById((R.id.bodyButton));
        buttons[3] = root.findViewById((R.id.foodButton));

        //Get all textViews
        texts[0] = root.findViewById((R.id.beakTextView));
        texts[1] = root.findViewById((R.id.hatTextView));
        texts[2] = root.findViewById((R.id.bodyTextView));
        texts[3] = root.findViewById((R.id.statPointTextView));
        texts[4] = root.findViewById((R.id.foodTextView));
        texts[5] = root.findViewById((R.id.levelTextView));
        texts[6] = root.findViewById((R.id.feedPetView));

        //Get body parts
        bodyParts[0] = root.findViewById(((R.id.beakView)));
        bodyParts[1] = root.findViewById(((R.id.hatView)));
        bodyParts[2] = root.findViewById(((R.id.bodyView)));
        bodyParts[3] = root.findViewById(((R.id.headView)));

        //Get progression bar
        pBar[0] = root.findViewById((R.id.xpBarView));
        pBar[1] = root.findViewById((R.id.healthBarView));

        //Set width/height
        beakWidth = bodyParts[0].getLayoutParams().width;
        hatHeight = bodyParts[1].getLayoutParams().height;
        bodyWidth = bodyParts[2].getLayoutParams().width;
        bodyHeight = bodyParts[2].getLayoutParams().height;

        //Constrain
        layout = root.findViewById(R.id.petConstraint);

        //Update views
        onClickEvent();
        updateButtons();
        updateTexts(PetViews.ALL);
        updateProgressBar();
        updateBodyParts(PetViews.ALL);

        //Shake
        shakeDetection();

        //Tap
        tapDetection();

        return root;
    }

    //Private functions
    private void onClickEvent() {

        //Beak button
        buttons[0].setOnClickListener(view -> {
            if(petmodel.addStats(PetViews.BEAK, 1)) {
                updateTexts(PetViews.BEAK);
                updateBodyParts(PetViews.BEAK);
                updateTexts((PetViews.STAT_POINT));
                updateButtons();
            } else {
                Log.d("Stats Error: ", "Failed to add BEAK stat!");
            }
        });

        //Hat button
        buttons[1].setOnClickListener(view -> {
            if(petmodel.addStats(PetViews.HAT, 1)) {
                updateTexts(PetViews.HAT);
                updateBodyParts(PetViews.HAT);
                updateTexts((PetViews.STAT_POINT));
                updateButtons();
            } else {
                Log.d("Stats Error: ", "Failed to add HAT stat!");
            }
        });

        //Body button
        buttons[2].setOnClickListener(view -> {
            if(petmodel.addStats(PetViews.BODY, 1)) {
                updateTexts(PetViews.BODY);
                updateBodyParts(PetViews.BODY);
                updateTexts((PetViews.STAT_POINT));
                updateButtons();
            } else {
                Log.d("Stats Error: ", "Failed to add BODY stat!");
            }
        });

        //Food button
        buttons[3].setOnClickListener(view -> {
            petmodel.changeFoodState();
            updateButtons();
        });

    }
    @SuppressLint("SetTextI18n")
    private void updateTexts(PetViews stat) {
        switch(stat){
            case BEAK:
                texts[0].setText(getResources().getText(R.string.beakLength_text).toString() +
                        " " + petmodel.getBeakLength());
                break;
            case HAT:
                texts[1].setText(getResources().getText(R.string.hatHeight_text).toString() +
                        " " + petmodel.getHatHeight());
                break;
            case BODY:
                texts[2].setText(getResources().getText(R.string.bodyShape_text).toString() +
                        " " + petmodel.getBodyShape());
                break;
            case STAT_POINT:
                texts[3].setText(getResources().getText(R.string.statPoints_text).toString() +
                        " " + petmodel.getStatPoints());
                break;
            case FOOD:
                texts[4].setText(getResources().getText(R.string.food_text).toString() +
                        " " + petmodel.getFood());
                break;
            case LEVEL:
                texts[5].setText(getResources().getText(R.string.level_text).toString() +
                        " " + petmodel.getLevel());
                break;
            case ALL:
                texts[0].setText(getResources().getText(R.string.beakLength_text).toString() +
                        " " + petmodel.getBeakLength());
                texts[1].setText(getResources().getText(R.string.hatHeight_text).toString() +
                        " " + petmodel.getHatHeight());
                texts[2].setText(getResources().getText(R.string.bodyShape_text).toString() +
                        " " + petmodel.getBodyShape());
                texts[3].setText(getResources().getText(R.string.statPoints_text).toString() +
                        " " + petmodel.getStatPoints());
                texts[4].setText(getResources().getText(R.string.food_text).toString() +
                        " " + petmodel.getFood());
                texts[5].setText(getResources().getText(R.string.level_text).toString() +
                        " " + petmodel.getLevel());

            default:
                break;
        }
    }
    private void updateButtons(){
        if(petmodel.getStatPoints() <= 0) {
            for (int i = 0; i < 3; i++){
                buttons[i].setVisibility(View.INVISIBLE);
            }
        }
        else {
            for (int i = 0; i < 3; i++){
                buttons[i].setVisibility(View.VISIBLE);
            }
        }

        if(petmodel.getFeedState() == 1) {
            texts[6].setVisibility(View.VISIBLE);
        }
        else {
            texts[6].setVisibility(View.INVISIBLE);
        }
    }
    private void updateBodyParts(PetViews part) {
        float scale_offSet = 0.1f;
        switch(part){
            case BEAK:
                float beakScale = 1.0f + ((float)petmodel.getBeakLength() * scale_offSet);
                bodyParts[0].setScaleX(beakScale);
                bodyParts[0].getLayoutParams().width = (int)(beakWidth * beakScale);
                bodyParts[0].requestLayout();
                break;
            case HAT:
                float hatScale = 1.0f + ((float)petmodel.getHatHeight() * scale_offSet);
                bodyParts[1].setScaleY(hatScale);
                bodyParts[1].getLayoutParams().height = (int)(hatHeight * hatScale);
                bodyParts[1].requestLayout();
                break;
            case BODY:
                float bodyScale = 1.0f + ((float)petmodel.getBodyShape() * scale_offSet * 0.5f);
                bodyParts[2].getLayoutParams().width = (int)(bodyWidth * bodyScale);
                bodyParts[2].getLayoutParams().height = (int)(bodyHeight * bodyScale);
                bodyParts[2].requestLayout();
                //Update constrains
                int margin = bodyParts[2].getLayoutParams().height - 64;
                ConstraintSet set = new ConstraintSet();
                set.clone(layout);
                set.connect(bodyParts[3].getId(), ConstraintSet.RIGHT, bodyParts[2].getId(), ConstraintSet.RIGHT, 0);
                set.connect(bodyParts[3].getId(), ConstraintSet.BOTTOM, bodyParts[2].getId(), ConstraintSet.BOTTOM, margin);
                set.applyTo(layout);
                break;
            case ALL:
                updateBodyParts(PetViews.BEAK);
                updateBodyParts(PetViews.HAT);
                updateBodyParts(PetViews.BODY);
                break;
            default:
                break;
        }
    }
    private void updateProgressBar() {
        pBar[0].setProgress(petmodel.getExperience());
        pBar[1].setProgress(petmodel.getHealth());
    }
    private void feedPet(){
        if(petmodel.feedPet()){
            updateTexts(PetViews.FOOD);
            updateButtons();
            updateProgressBar();
        }
    }
    private void shakeDetection() {
        SensorManager sm = (SensorManager) requireContext().getSystemService(Context.SENSOR_SERVICE);
        Sensor sensorShake = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        SensorEventListener sEvent = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent != null) {
                    float x = sensorEvent.values[0];
                    float y = sensorEvent.values[1];
                    float z = sensorEvent.values[2];

                    if (x > 2 || x < -2 ||
                            y > 11.81 || y < 7.81 ||
                            z > 2 || z < -2)
                    {
                        feedPet();
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        sm.registerListener(sEvent, sensorShake, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @SuppressLint("ClickableViewAccessibility")
    private void tapDetection() {

        bodyParts[2].setOnTouchListener((view, motionEvent) -> {
            feedPet();
            return false;
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}