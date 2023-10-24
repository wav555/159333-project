package com.mygdx.game.actor.beas;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationActor extends ImageActor{
    private int index;
    private float startTime;
    private Animation animation;
    private boolean isPlay = true;
    public AnimationActor() {}

    public AnimationActor(Animation animation) {
        // TODO Auto-generated constructor stub
        this.animation = animation;
        setTexture((TextureRegion) animation.getKeyFrame(1));
    }
    public AnimationActor(TextureRegion[] texture,float fo) {
        // TODO Auto-generated constructor stub
        this.animation = new Animation(fo, texture);
        setTexture((TextureRegion) animation.getKeyFrame(1));
    }

    @Override
    public void act(float arg0) {
        // TODO Auto-generated method stub
        super.act(arg0);
        if(animation == null) {
            return;
        }

        if(isPlay) {
            startTime += arg0;
            setTexture((TextureRegion) animation.getKeyFrame(startTime));

        }else {
            FaPlay();
        }
    }

    public void FaPlay() {
        setTexture((TextureRegion) animation.getKeyFrame(index));
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

}

