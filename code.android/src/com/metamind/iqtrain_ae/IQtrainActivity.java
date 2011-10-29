/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.metamind.iqtrain_ae;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.metamind.iqtrain_ae.IQtrainView.LunarThread;
import com.metamind.iqtrain_ae.TrainingManager.State;
import com.rainlib.Debug;

/**
 * This is a simple LunarLander activity that houses a single LunarView. It
 * demonstrates...
 * <ul>
 * <li>animating by calling invalidate() from draw()
 * <li>loading and drawing resources
 * <li>handling onPause() in an animation
 * </ul>
 */
public class IQtrainActivity extends Activity {
	
	
	public static GlobalData global = new GlobalData();
	
	public Button startButton;
	
	public IQtrainActivity activity = this;
	
    private static final int MENU_EASY = 1;

    private static final int MENU_HARD = 2;

    private static final int MENU_MEDIUM = 3;

    private static final int MENU_PAUSE = 4;

    private static final int MENU_RESUME = 5;

    private static final int MENU_START = 6;

    private static final int MENU_STOP = 7;

    /** A handle to the thread that's actually running the animation. */
    private LunarThread mLunarThread;

    /** A handle to the View in which the game is running. */
    public IQtrainView mMainView;

    /**
     * Invoked during init to give the Activity a chance to set up its Menu.
     * 
     * @param menu the Menu to which entries may be added
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, MENU_START, 0, R.string.menu_start);
        menu.add(0, MENU_STOP, 0, R.string.menu_stop);
        menu.add(0, MENU_PAUSE, 0, R.string.menu_pause);
        menu.add(0, MENU_RESUME, 0, R.string.menu_resume);
        menu.add(0, MENU_EASY, 0, R.string.menu_easy);
        menu.add(0, MENU_MEDIUM, 0, R.string.menu_medium);
        menu.add(0, MENU_HARD, 0, R.string.menu_hard);

        return true;
    }
    
  

    /**
     * Invoked when the user selects an item from the Menu.
     * 
     * @param item the Menu entry which was selected
     * @return true if the Menu item was legit (and we consumed it), false
     *         otherwise
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_START:
                mLunarThread.doStart();
                return true;
            case MENU_STOP:
                mLunarThread.setState(LunarThread.STATE_LOSE,
                        getText(R.string.message_stopped));
                return true;
            case MENU_PAUSE:
                mLunarThread.pause();
                return true;
            case MENU_RESUME:
                mLunarThread.unpause();
                return true;
            case MENU_EASY:
                mLunarThread.setDifficulty(LunarThread.DIFFICULTY_EASY);
                return true;
            case MENU_MEDIUM:
                mLunarThread.setDifficulty(LunarThread.DIFFICULTY_MEDIUM);
                return true;
            case MENU_HARD:
                mLunarThread.setDifficulty(LunarThread.DIFFICULTY_HARD);
                return true;
        }

        return false;
    }

    public void showTrainingSelectGUI() {
  

    }
    
      
    
    /**
     * Invoked when the Activity is created.
     * 
     * @param savedInstanceState a Bundle containing state saved from a previous
     *        execution, or null if this is a new execution
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	setContentView(R.layout.start_layout);

        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(startButtonListener);
        
        global.trainingManager.setActivity(this);
        
        //showTrainingSelectGUI();


        // tell system to use the layout defined in our XML file
        //setContentView(R.layout.lunar_layout);
//        setContentView(R.layout.start_layout);

        // get handles to the LunarView from XML, and its LunarThread
        mMainView = (IQtrainView) findViewById(R.id.starttraining);
        mLunarThread = mMainView.getThread();

        // give the LunarView a handle to the TextView used for messages
        mMainView.setTextView((TextView) findViewById(R.id.text));
           
  
          mMainView.setEnabled(true);
        mMainView.setVisibility(View.VISIBLE);
        

        if (savedInstanceState == null) {
            // we were just launched: set up a new game
            mLunarThread.setState(LunarThread.STATE_READY);
            Log.w(this.getClass().getName(), "SIS is null");
        } else {
            // we are being restored: resume a previous game
            mLunarThread.restoreState(savedInstanceState);
            Log.w(this.getClass().getName(), "SIS is nonnull");
        }
    }

    /**
     * Invoked when the Activity loses user focus.
     */
    @Override
    protected void onPause() {
        super.onPause();
        mMainView.getThread().pause(); // pause game when Activity pauses
    }

    /**
     * Notification that something is about to happen, to give the Activity a
     * chance to save state.
     * 
     * @param outState a Bundle into which this Activity should save its state
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // just have the View's thread save its state into our Bundle
        super.onSaveInstanceState(outState);
        mLunarThread.saveState(outState);
        Log.w(this.getClass().getName(), "SIS called");
    }




    // Create an anonymous class to act as a button click listener.
    private OnClickListener startButtonListener = new OnClickListener()
    {
        public void onClick(View v)
        {
//    		Intent myIntent = new Intent(activity, com.metamind.iqtrain_ae.TrainingManagerActivity.class);//new Intent();
    		//myIntent.setClassName("com.metamind.iqtrain_ae", "com.metamind.iqtrain_ae.TrainingManagerActivity");
    		//myIntent.putExtra("com.android.samples.SpecialValue", "Hello, Joe!"); // key/value pair, where key needs current package prefix.
//    		startActivity(myIntent);  
    		
        	Debug.print("Start Training clicked");
        	setContentView(R.layout.training_layout);
        	

    		global.trainingManager.state = State.TRAINING_SESSION;
        }
    };
	
}
