package com.calbertouepb.rayrayparade;

public abstract class GameObject {
    public abstract void startGame();
    public abstract void onUpdate(long elapsedMillis, GameEngine gameEngine);
    public abstract void onDraw();
    public final Runnable mOnAddedRunnable = new Runnable() {
        @Override
        public void run() {
            onAddedToGameUiThread();
        }
    };
    public final Runnable mOnRemovedRunnable = new Runnable() {
        @Override
        public void run() {
            onRemovedFromGameUiThread();
        }
    };
    public void onRemovedFromGameUiThread(){
    }
    public void onAddedToGameUiThread(){
    }
}
