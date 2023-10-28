package com.mygdx.game.actor.beas;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**Parent class that works with animated characters**/
public class AnimationsActor extends ImageActor {

    private boolean isLife = true;
    private int type =0;
    private int timpType = 0;
    private Animation[] animations;
    private int[] index;
    private boolean isPlay = true;
    private float startTime;
    private float seed;
    @Override
    public void act(float arg0) {
        // TODO Auto-generated method stub
        super.act(arg0);
        if(animations != null) {
            if(isPlay) {
                if(type != timpType ) {
                    startTime = 0;
                    timpType = type;
                }
                startTime += arg0;
                if(type <= animations.length) {
                    setTexture((TextureRegion) animations[type].getKeyFrame(startTime));
                    index[type] = animations[type].getKeyFrameIndex(startTime);
                }

            }else {
                drawdingzhi();
            }
        }
    }

    public void drawdingzhi() {
        TextureRegion[] region = (TextureRegion[]) animations[type].getKeyFrames();
        if(index[type] >=0 && index[type] <region.length) {
            setTexture(region[index[type]]);
        }
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public Animation[] getAnimations() {
        return animations;
    }
    public void setAnimations(Animation[] animations) {
        this.animations = animations;
    }
    public int[] getIndex() {
        return index;
    }
    public void setIndex(int[] index) {
        this.index = index;
    }
    public boolean isPlay() {
        return isPlay;
    }
    public void setPlay(boolean isPlay) {
        this.isPlay = isPlay;
    }
    public boolean isLife() {
        return isLife;
    }
    public void setLife(boolean isLife) {
        this.isLife = isLife;
    }
    public float getSeed() {
        return seed;
    }
    public void setSeed(float seed) {
        this.seed = seed;
    }

    public void death() {}




}
