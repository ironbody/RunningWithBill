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

import java.util.Observer;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.runningwithbill.R;
import com.example.runningwithbill.dataObject.Pet;
import com.example.runningwithbill.databinding.FragmentPetBinding;
import com.example.runningwithbill.dataObject.DBPetViewModel;

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

    private int foodState = -1;

    private ConstraintLayout layout;

    private FragmentPetBinding binding;

    private PetModel petmodel;
    private DBPetViewModel petDb;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PetViewModel dashboardViewModel =
                new ViewModelProvider(this).get(PetViewModel.class);

        binding = FragmentPetBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Create objects
        petDb = new ViewModelProvider(this).get(DBPetViewModel.class);
        petmodel = new PetModel();

        //Get all buttons
        buttons[0] = root.findViewById(R.id.beakButton);
        buttons[1] = root.findViewById(R.id.hatButton);
        buttons[2] = root.findViewById(R.id.bodyButton);
        buttons[3] = root.findViewById(R.id.foodButton);

        //Get all textViews
        texts[0] = root.findViewById(R.id.beakTextView);
        texts[1] = root.findViewById(R.id.hatTextView);
        texts[2] = root.findViewById(R.id.bodyTextView);
        texts[3] = root.findViewById(R.id.statPointTextView);
        texts[4] = root.findViewById(R.id.foodTextView);
        texts[5] = root.findViewById(R.id.levelTextView);
        texts[6] = root.findViewById(R.id.feedPetView);

        //Get body parts
        bodyParts[0] = root.findViewById(R.id.beakView);
        bodyParts[1] = root.findViewById(R.id.hatView);
        bodyParts[2] = root.findViewById(R.id.bodyView);
        bodyParts[3] = root.findViewById(R.id.headView);

        //Get progression bar
        pBar[0] = root.findViewById(R.id.xpBarView);
        pBar[1] = root.findViewById(R.id.healthBarView);

        //Set width/height
        beakWidth = bodyParts[0].getLayoutParams().width;
        hatHeight = bodyParts[1].getLayoutParams().height;
        bodyWidth = bodyParts[2].getLayoutParams().width;
        bodyHeight = bodyParts[2].getLayoutParams().height;

        //Read
        readFromDataBase();

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
            addStats(PetViews.BEAK, 1);
            updateTexts(PetViews.BEAK);
            updateTexts((PetViews.STAT_POINT));
            updateBodyParts(PetViews.BEAK);
            updateButtons();
        });

        //Hat button
        buttons[1].setOnClickListener(view -> {
            addStats(PetViews.HAT, 1);
            updateTexts(PetViews.HAT);
            updateTexts((PetViews.STAT_POINT));
            updateBodyParts(PetViews.HAT);
            updateButtons();
        });

        //Body button
        buttons[2].setOnClickListener(view -> {
            addStats(PetViews.BODY, 1);
            updateTexts(PetViews.BODY);
            updateTexts((PetViews.STAT_POINT));
            updateBodyParts(PetViews.BODY);
            updateButtons();

        });

        //Food button
        buttons[3].setOnClickListener(view -> {
            foodState *= -1;
            updateButtons();
        });

    }
    @SuppressLint("SetTextI18n")
    private void updateTexts(PetViews stat) {
        switch(stat){
            case BEAK:
                petDb.readBeakLenght.observe(getViewLifecycleOwner(), nr -> {
                    texts[0].setText(getResources().getText(R.string.beakLength_text).toString()
                            + " " + nr);
                });
                break;
            case HAT:
                petDb.readHatHeight.observe(getViewLifecycleOwner(), nr -> {
                    texts[1].setText(getResources().getText(R.string.hatHeight_text).toString()
                            + " " + nr);
                });
                break;
            case BODY:
                petDb.readBodySize.observe(getViewLifecycleOwner(), nr -> {
                    texts[2].setText(getResources().getText(R.string.bodyShape_text).toString()
                            + " " + nr);
                });
                break;
            case STAT_POINT:
                petDb.readStatPoints.observe(getViewLifecycleOwner(), nr -> {
                    texts[3].setText(getResources().getText(R.string.statPoints_text).toString()
                    + " " + nr);
                });
                break;
            case FOOD:
                petDb.readFood.observe(getViewLifecycleOwner(), nr -> {
                    texts[4].setText(getResources().getText(R.string.food_text).toString()
                            + " " + nr);
                });
                break;
            case LEVEL:
                petDb.readLevel.observe(getViewLifecycleOwner(), nr -> {
                    texts[5].setText(getResources().getText(R.string.level_text).toString()
                            + " " + nr);
                });
                break;
            case ALL:
                updateTexts(PetViews.BEAK);
                updateTexts(PetViews.HAT);
                updateTexts(PetViews.BODY);
                updateTexts(PetViews.STAT_POINT);
                updateTexts(PetViews.FOOD);
                updateTexts(PetViews.LEVEL);
            default:
                break;
        }
    }
    private void updateButtons(){
        petDb.readStatPoints.observe(getViewLifecycleOwner(), nr -> {
            if(nr <= 0){
                for (int i = 0; i < 3; i++){
                    buttons[i].setVisibility(View.INVISIBLE);
                }
            } else {
                for (int i = 0; i < 3; i++){
                    buttons[i].setVisibility(View.VISIBLE);
                }
            }
        });

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
                petDb.readBeakLenght.observe(getViewLifecycleOwner(), nr -> {
                    float beakScale = 1.0f + ((float)nr * scale_offSet);
                    bodyParts[0].setScaleX(beakScale);
                    bodyParts[0].getLayoutParams().width = (int)(beakWidth * beakScale);
                    bodyParts[0].requestLayout();
                });
                break;
            case HAT:
                petDb.readHatHeight.observe(getViewLifecycleOwner(), nr -> {
                    float hatScale = 1.0f + ((float)nr * scale_offSet);
                    bodyParts[1].setScaleY(hatScale);
                    bodyParts[1].getLayoutParams().height = (int)(hatHeight * hatScale);
                    bodyParts[1].requestLayout();
                });
                break;
            case BODY:
                petDb.readBodySize.observe(getViewLifecycleOwner(), nr -> {
                    float bodyScale = 1.0f + ((float)nr * scale_offSet * 0.5f);
                    bodyParts[2].getLayoutParams().width = (int)(bodyWidth * bodyScale);
                    bodyParts[2].getLayoutParams().height = (int)(bodyHeight * bodyScale);
                    bodyParts[2].requestLayout();
                });
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
        petDb.readExperience.observe(getViewLifecycleOwner(), nr -> {
            pBar[0].setProgress(nr);
        });
        petDb.readHealth.observe(getViewLifecycleOwner(), nr -> {
            pBar[1].setProgress(nr);
        });
    }
    private void feedPet(){
        if(foodState == 1)
        {
            petDb.readFood.observe(getViewLifecycleOwner(), nr -> {
                if(nr > 0) {
                    petDb.addHealth(20);
                    petDb.addFood(-1);
                    foodState = -1;
                    updateTexts(PetViews.FOOD);
                    updateButtons();
                    updateProgressBar();
                }
            });
            //foodState = -1;
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

    private void readFromDataBase() {

        //Text views
        petDb.readBeakLenght.observe(getViewLifecycleOwner(), nr -> {
            texts[0].setText(String.valueOf(nr));
        });
        petDb.readHatHeight.observe(getViewLifecycleOwner(), nr -> {
            texts[1].setText(String.valueOf(nr));
        });
        petDb.readBodySize.observe(getViewLifecycleOwner(), nr -> {
            texts[2].setText(String.valueOf(nr));
        });
        petDb.readStatPoints.observe(getViewLifecycleOwner(), nr -> {
            texts[3].setText(String.valueOf(nr));
        });
        petDb.readFood.observe(getViewLifecycleOwner(), nr -> {
            texts[4].setText(String.valueOf(nr));
        });
        petDb.readLevel.observe(getViewLifecycleOwner(), nr -> {
            texts[5].setText(String.valueOf(nr));
        });

        //Progressbar
        petDb.readExperience.observe(getViewLifecycleOwner(), nr -> {
            pBar[0].setProgress(nr);
        });
        petDb.readHealth.observe(getViewLifecycleOwner(), nr -> {
            pBar[1].setProgress(nr);
        });

    }
    private void addStats(PetViews stat, int value) {

        petDb.readStatPoints.observe(getViewLifecycleOwner(), nr -> {
            if(nr > 0) {
                switch(stat) {
                    case BEAK:
                        petDb.addBeakLength(value);
                        break;
                    case HAT:
                        petDb.addHatHeight(value);
                        break;
                    case BODY:
                        petDb.addBodySize(value);
                        break;
                    default:
                        break;
                }
                petDb.addStatPoints(-1);
            } else {
                Log.d("Stats: ", "No stat points");
            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}