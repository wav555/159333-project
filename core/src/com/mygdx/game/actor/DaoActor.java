package com.mygdx.game.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.actor.beas.ImageActor;
import com.mygdx.game.main.GameMian;
import com.mygdx.game.utii.R;

public class DaoActor extends ImageActor {

    /**刀阵的长度*/
    private int sum;
    /**木桩，用来放在木桩上面*/
    private Bridge bridge;
    /**移动速度，应该没有自身的移动速度，已木桩的为准*/
    private float seed;
    /**给图片的类*/
    private GameMian main;
    /**展示图片*/
    private Texture tu;


    public Bridge getBridge() {
        return bridge;
    }
    /**设置木桩，并初始化刀阵的大小和位置*/
    public void setBridge(Bridge bridge) {
        //随机刀阵宽度
        sum = MathUtils.random(1, 8);
        //设置刀阵位置
        setX(MathUtils.random(bridge.getX(), bridge.getReightX()-sum*tu.getWidth()));

        setY(bridge.getTopY()-10);
        //设置移动速度
        this.seed = bridge.getSeed();
        //设置大小
        setSize(sum*tu.getWidth(), tu.getHeight());

    }

    public GameMian getMain() {
        return main;
    }

    public void setMain(GameMian main) {
        if(this.main != null) {
            return;
        }
        this.main = main;
        this.tu = main.getAsset().get(R.BackGround.IMAGE_DAO	, Texture.class);
        setTexture(new TextureRegion(tu));
    }

    @Override
    public void act(float arg0) {
        // TODO Auto-generated method stub
        super.act(arg0);
        setX(getX()+seed *arg0*R.BEI_SU);
    }
    /**绘制刀阵，根据sum的值绘制出*/
    @Override
    public void drawActor(Batch batch) {
        // TODO Auto-generated method stub

        for(int i=0;i<sum;i++) {
            batch.draw(getTexture(), getX()+(i*tu.getWidth()), getY(), getOriginX(), getOriginY(), tu.getWidth(), tu.getHeight(), getScaleX(), getScaleY(), getRotation());
        }

    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub
        setCollision(false);
    }


    public float getSeed() {
        return seed;
    }

    public void setSeed(float seed) {
        this.seed = seed;
    }


}
