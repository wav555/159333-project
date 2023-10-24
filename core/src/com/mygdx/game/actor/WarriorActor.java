package com.mygdx.game.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.actor.beas.AnimationsActor;
import com.mygdx.game.main.GameMian;
import com.mygdx.game.utii.AnimationUtii;
import com.mygdx.game.utii.R;

public class WarriorActor extends AnimationsActor {
    /**游戏主类*/
    private GameMian main;
    /**站立用的木桩*/
    private Bridge bridge;
    /**移动速度*/
    private float seed;
    /**有参构造方法，负责重置一些基础变量*/
    private TextureRegion[] death;

    private float deathX,deathY;

    public WarriorActor(GameMian main) {
        setMain(main);
        setLife(true);
        setPlay(true);
    }
    public WarriorActor() {
        setLife(true);
        setPlay(true);
    }

    public GameMian getMain() {
        return main;
    }





    public  Bridge bidge;
    /**设置游戏主类，并设置动画*/
    public void setMain(GameMian main) {
        if(this.main != null) {
            return;
        }
        this.main = main;
        Animation[] animations = new Animation[3];
        setIndex(new int[3]);
        Texture tx = main.getAsset().get(R.Actor.IMAGE_ENEMY, Texture.class);
        int row = 1;
        int cols = 9;
        int perCellWidth = tx.getWidth()/cols;
        int perCellheight = tx.getHeight()/row;
        TextureRegion[][] cellRegopns = TextureRegion.split(tx, perCellWidth, perCellheight);
        TextureRegion[][] cell = TextureRegion.split(tx, perCellWidth, perCellheight);
        TextureRegion[] z = new TextureRegion[row*cols];
        TextureRegion[] f = new TextureRegion[row*cols];
        int index =0;
        for(int i=0;i<row;i++) {
            for(int j=0;j<cols;j++,index++) {
                z[index] = cellRegopns[i][j];
                cell[i][j].flip(true, false);
                f[index] = cell[i][j];
            }
        }
        setSize(perCellWidth, perCellheight);
        animations[0] = new Animation(0.2f, z);
        animations[0].setPlayMode(Animation.PlayMode.LOOP);
        animations[1] = new Animation(0.2f, f);
        animations[1].setPlayMode(Animation.PlayMode.LOOP);
        animations[2] = AnimationUtii.createAnimation(main.getAsset().get(R.Actor.IMAGE_WARR_HUR, Texture.class), 1, 5, Animation.PlayMode.REVERSED, 0.2f);
        death = AnimationUtii.tuerx((Texture) main.getAsset().get(R.Actor.IMAGE_WARR_DEAD), 1, 5);
        setAnimations(animations);
    }

    /**设置木桩，并设置武士的x与Y轴坐标*/
    public void setBridge(Bridge bridge) {
        setY(bridge.getTopY());
        setX(MathUtils.random(bridge.getX(), bridge.getReightX()));
        this.bridge = bridge;
    }

    public void setSeed(float seed) {
        this.seed = seed;
    }
    /**武士的动作*/
    @Override
    public void act(float arg0) {
        // TODO Auto-generated method stub
        super.act(arg0);

        if(bridge == null) {
            return;
        }
        switch(getType()) {
            case 0://0向做移动，到移动到顶点 向右移动
                setX(getX()+(bridge.getSeed()+this.seed)*arg0*R.BEI_SU);
                if(this.getX() <= bridge.getX()) {
                    setType(1);
                }
                break;
            case 1://1 和0相反
                setX(getX()+(bridge.getSeed()-this.seed)*arg0*R.BEI_SU);
                if(this.getReightX()>=bridge.getReightX()) {
                    setType(0);
                }
                break;
            case 2://2死亡，Y轴向下移动
                setY(getY()-Math.abs((bridge.getSeed()+this.seed*2)*arg0)*R.BEI_SU);
                break;
        }
    }

    //画出演员
//    public void drawActor(Batch batch) {
//        if(getType()!=2) {
//            super.drawActor(batch);
//        }else {//画出死亡效果
//            batch.draw(getTexture(), deathX, deathY, getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
//            batch.draw(death[2], getX()+death[2].getRegionWidth(), getTopY(), getOriginX(), getOriginY(), death[2].getRegionWidth(), death[2].getRegionHeight(), getScaleX(), getScaleY(), getRotation());
//            batch.draw(death[0], getX(), getTopY()-death[0].getRegionHeight(), getOriginX(), getOriginY(), death[0].getRegionWidth(), death[0].getRegionHeight(), getScaleX(), getScaleY(), getRotation());
//            batch.draw(death[3], getX(), getTopY()-death[3].getRegionHeight()*2, getOriginX(), getOriginY(), death[3].getRegionWidth(), death[3].getRegionHeight(), getScaleX(), getScaleY(), getRotation());
//            batch.draw(death[1], getX()+death[1].getRegionHeight()*3, getTopY()-death[1].getRegionHeight(), getOriginX(), getOriginY(), death[1].getRegionWidth(), death[1].getRegionHeight(), getScaleX(), getScaleY(), getRotation());
//            batch.draw(death[4], getX()+death[1].getRegionHeight()*3, getTopY()-death[3].getRegionHeight()*2, getOriginX(), getOriginY(), death[3].getRegionWidth(), death[3].getRegionHeight(), getScaleX(), getScaleY(), getRotation());
//        }
//
//    }

    /**放入对象池的重置方法，重置速度、木桩、是否碰撞*/
    @Override
    public void reset() {
        // TODO Auto-generated method stub
        this.bridge = null;
        this.seed = 0f;
        deathX = 0;
        deathY= 0;
        setCollision(false);

    }
    //死亡时调用的方法
    public void death() {
        deathX = getX();
        deathY = getY();
        setType(2);
    }

}
