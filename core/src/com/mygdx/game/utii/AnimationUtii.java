package com.mygdx.game.utii;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
public class AnimationUtii {

    public static Animation createAnimation(Texture tx,int row ,int cols,Animation.PlayMode a,
                                            float gap,boolean b,boolean c,int start,int end) {
        int perCellWidth = tx.getWidth()/cols; //calculate width
        int perCellheight = tx.getHeight()/row;//calculate width
        TextureRegion[][] cell = TextureRegion.split(tx, perCellWidth, perCellheight);
        TextureRegion[] z = new TextureRegion[row*cols];// store texture using one-dimensional array
        int index =0;
        for(int i=0;i<row;i++) {
            for(int j=0;j<cols;j++,index++) {
                cell[i][j].flip(b, c);// set if texture needs to be reversed
                z[index] = cell[i][j];
            }
        }
        TextureRegion[] time;
        if(start != end&&end > start) {
            time = new TextureRegion[end - start];
            int index1= 0;
            for(int i=start;i<end;i++) {
                time[index1++] =z[i];
            }
            z = time;
        }
        Animation animations = new Animation(gap, z);// create animations
        animations.setPlayMode(a);// set play mode

        return animations;

    }

    public static Animation createAnimation(Texture tx,int row ,int cols,Animation.PlayMode a,float date,boolean r,boolean c) {
        return createAnimation(tx, row, cols, a, date, r, c,0,0);
    }

    public static Animation createAnimation(Texture tx,int row ,int cols,Animation.PlayMode a,float date) {
        return createAnimation(tx, row, cols, a, date, false, false);
    }

    public static Animation createAnimation(Texture tx,int row ,int cols) {
        return createAnimation(tx, row, cols, Animation.PlayMode.LOOP, 0.2f);
    }


    public static TextureRegion tuerx(Texture tx,int row ,int cols,int ind) {
        int perCellWidth = tx.getWidth()/cols;
        int perCellheight = tx.getHeight()/row;
        TextureRegion[][] cell = TextureRegion.split(tx, perCellWidth, perCellheight);
        TextureRegion[] z = new TextureRegion[row*cols];
        int index =0;
        for(int i=0;i<row;i++) {
            for(int j=0;j<cols;j++,index++) {
                z[index] = cell[i][j];
            }
        }
        return z[ind];
    }

    public static TextureRegion[] tuerx(Texture tx,int row ,int cols) {
        int perCellWidth = tx.getWidth()/cols;
        int perCellheight = tx.getHeight()/row;
        TextureRegion[][] cell = TextureRegion.split(tx, perCellWidth, perCellheight);
        TextureRegion[] z = new TextureRegion[row*cols];
        int index =0;
        for(int i=0;i<row;i++) {
            for(int j=0;j<cols;j++,index++) {
                z[index] = cell[i][j];
            }
        }
        return z;
    }
}
