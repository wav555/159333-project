package com.mygdx.game.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.actor.beas.ImageActor;
import com.mygdx.game.utii.R;

public class BackgroundActor extends ImageActor {
    /**是否移动*/
    private boolean isMove;
    /**移动速度*/
    private float seed;
    /**偏移量*/
    private float offx;

    public BackgroundActor(TextureRegion texture) {
        // TODO Auto-generated constructor stub
        super(texture);
    }
    /**演员动作，用于移动图片*/
    @Override
    public void act(float arg0) {
        // TODO Auto-generated method stub
        super.act(arg0);
        if(isMove) {
            offx+= seed*arg0* R.BEI_SU;
            offx %= getWidth();
            if(offx >0) {
                offx -=getWidth();
            }
        }
    }
    /**画出2福图片，已实现连续动画*/
    @Override
    public void draw(Batch batch, float parentAlpha) {
        // TODO Auto-generated method stub
        batch.draw(getTexture(), getX()+offx, getY());
        batch.draw(getTexture(), getX()+offx+getWidth(), getY());

    }

    public boolean isMove() {
        return isMove;
    }

    public void setMove(boolean isMove) {
        this.isMove = isMove;
    }

    public float getSeed() {
        return seed;
    }

    public void setSeed(float seed) {
        this.seed = seed;
    }


    public float getOffx() {
        return offx;
    }

    public void setOffx(float offx) {
        this.offx = offx;
    }


}
