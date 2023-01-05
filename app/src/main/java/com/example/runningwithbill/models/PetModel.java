package com.example.runningwithbill.models;

import android.content.Context;
import android.util.Log;
import com.example.runningwithbill.ui.pet.PetViews;
import com.example.runningwithbill.dataObject.DBPetViewModel;

import dagger.hilt.android.ViewModelLifecycle;

public class PetModel {

    //Constant variables
    final int HEALTH_GET_FROM_FOOD = 20;

    //Public variables
    public PetModel() {};

    //Private variables
    private int current_beak_length = 0;
    private int current_hat_height = 0;
    private int current_body_shape = 0;

    private int current_stats_point = 10;
    private int current_level = 1;
    private int current_food = 1;

    private int current_health = 70;
    private int current_experience = 20;

    private int feedPetState = -1;

    //Public functions
    public boolean addStats(PetViews stat, int value){

        //Check if you have stats points
        if(current_stats_point > 0)
        {
            switch (stat) {
                case BEAK:
                    current_beak_length += value;
                    break;
                case HAT:
                    current_hat_height += value;
                    break;
                case BODY:
                    current_body_shape += value;
                    break;
                default:
                    Log.d("Stats", "Invalid stat");
                    break;
            }
            current_stats_point -= 1;
            Log.d("Stats info: ", stat.toString());
            return true;
        }

        return false;
    }
    public void changeFoodState()
    {
        feedPetState *= -1;
    }
    public int getFeedState()
    {
        return feedPetState;
    }
    public boolean feedPet() {
        if(feedPetState == 1)
        {
            if(current_food > 0) {
                useFood();
                feedPetState = -1;
                return true;
            }
            Log.d("Feed pet:", "No food");
            feedPetState = -1;
        }

        return false;
    }

    public void init() {


    }

    //Private functions
    private void useFood() {
        current_health += HEALTH_GET_FROM_FOOD;
        current_food -= 1;
        Log.d("Feed pet:", "10");
    }

    //Get functions
    public int getBeakLength() {return current_beak_length; }
    public int getHatHeight() { return current_hat_height; }
    public int getBodyShape() { return current_body_shape;}
    public int getStatPoints() { return current_stats_point; }
    public int getFood() { return current_food;}
    public int getLevel() { return current_level;}
    public int getHealth() { return current_health;}
    public int getExperience() { return current_experience; }

    //Set functions
    public void setBeakLength(int value) { current_beak_length = value;}
    public void setHatHeight(int value) { current_hat_height = value; }
    public void setBodySize(int value) { current_body_shape = value; }
    public void setStatPoints(int value) { current_stats_point = value; }
    public void setLevel(int value) { current_level = value;}
    public void setFood(int value) { current_food = value; }
    public void setHealth(int value) { current_health = value; }
    public void setExperience(int value) { current_experience = value; }

}
