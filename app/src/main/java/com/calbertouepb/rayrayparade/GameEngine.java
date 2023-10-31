package com.calbertouepb.rayrayparade;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GameEngine {
    private static int EXPECTED_FPS = 30;
    private static final long TIME_BETWEEN_DRAWS = 1000 / EXPECTED_FPS;

    private List<GameObject> mGameObjects = new ArrayList<GameObject>();
    private UpdateThread mUpdateThread;
    private DrawThread mDrawThread;

    public void startGame() {
        // Stop a game if it is running
        stopGame();
        // Setup the game objects
        int numGameObjects = mGameObjects.size();
        for (int i = 0; i < numGameObjects; i++) {
            mGameObjects.get(i).startGame();
        }
        // Start the update thread
        mUpdateThread = new UpdateThread(this);
        mUpdateThread.start();
        // Start the drawing thread
        mDrawThread = new DrawThread(this);
        mDrawThread.start();
    }

    public void stopGame() {
        if (mUpdateThread != null) {
            mUpdateThread.stopGame();
        }
        if (mDrawThread != null) {
            mDrawThread.stopGame();
        }
    }

    public void addGameObject(final GameObject gameObject) {
        if (isRunning()){
            mObjectsToAdd.add(gameObject);
        }else {
            mGameObjects.add(gameObject);
        }
        mActivity.runOnUiThread(gameObject.mOnAddedRunnable);
    }
    public void removeGameObject(final GameObject gameObject) {
        mObjectsToRemove.add(gameObject);
        mActivity.runOnUiThread(gameObject.mOnRemovedRunnable);
    }

    public void onUpdate(long elapsedMillis) {
        int numGameObjects = mGameObjects.size();
        for (int i=0; i<numGameObjects; i++) {
            mGameObjects.get(i).onUpdate(elapsedMillis, this);
        }
        synchronized (mGameObjects) {
            while (!mObjectsToRemove.isEmpty()) {
                mGameObjects.remove(mObjectsToRemove.remove(0));
            }
            while (!mObjectsToAdd.isEmpty()) {
                mGameObjects.add(mObjectsToAdd.remove(0));
            }
        }
    }

    private Runnable mDrawRunnable = new Runnable() {
        @Override
        public void run() {
            synchronized (mGameObjects) {
                int numGameObjects = mGameObjects.size();
                for (int i = 0; i < numGameObjects; i++) {
                    mGameObjects.get(i).onDraw();}
            }
        }
    };
    public void onDraw(Canvas canvas) {
        mActivity.runOnUiThread(mDrawRunnable);
    }

    @Override
    public void run() {
        long previousTimeMillis;
        long currentTimeMillis;
        long elapsedMillis;
        previousTimeMillis = System.currentTimeMillis();
        while (mGameIsRunning) {
            currentTimeMillis = System.currentTimeMillis();
            elapsedMillis = currentTimeMillis - previousTimeMillis;
            mGameEngine.onUpdate(elapsedMillis);
            previousTimeMillis = currentTimeMillis;
        }
    }

    public void start() {
        mGameIsRunning = true;
        mPauseGame = false;
        super.start();
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mGameEngine.onDraw();
            }
        }, 0, TIME_BETWEEN_DRAWS);
    }
    public void stopGame() {mGameIsRunning = false;
        resumeGame();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer.purge();
        }
    }
    public void pauseGame() {
        stopGame();
    }
    public void resumeGame() {
        start();
    }
}
