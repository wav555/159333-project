package com.mygdx.game.actor.beas;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool.Poolable;
public class ImageActor extends Actor implements Poolable{

    /**展示图片*/
    private TextureRegion texture;
    /**是否可碰撞*/
    private boolean isCollision;
    /**构造方法
     * 用于赋值图片
     * 和设置大小
     * */
    public ImageActor(TextureRegion texture) {
        // TODO Auto-generated constructor stub
        this.texture = texture;
        if(texture == null) {
            return;
        }
        setSize(texture.getRegionWidth(),texture.getRegionHeight());
    }
    /**无参构造方法，用于对象池创建*/
    public ImageActor() {

    }
    /**绘制对象*/
    @Override
    public void draw(Batch batch, float parentAlpha) {
        // TODO Auto-generated method stub
        super.draw(batch, parentAlpha);

        if(texture == null) {
            return;
        }
        Color stageColor = batch.getColor();
        Color thisColor = this.getColor();
        batch.setColor(thisColor.a, thisColor.b, thisColor.g, thisColor.r);
        drawActor(batch);
        batch.setColor(stageColor);
    }

    public TextureRegion getTexture() {
        return texture;
    }
    //画出演员
    public void drawActor(Batch batch) {
        batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
    public void setTexture(TextureRegion texture) {
        this.texture = texture;
        setSize(texture.getRegionWidth(), texture.getRegionHeight());
    }
    public float getTopY() {
        return getY() + getHeight();
    }

    public float getReightX() {
        return getX()+getWidth();
    }

    public void setCenterX(float x) {
        setX(x-getWidth()/2);
    }
    public void setCenterY(float y) {
        setY(y-getHeight()/2);
    }

    public void setCenter(float x,float y) {
        setCenterX(x);
        setCenterY(y);
    }
    public void reset() {
        // TODO Auto-generated method stub
    }
    public boolean isCollision() {
        return isCollision;
    }
    public void setCollision(boolean isCollision) {
        this.isCollision = isCollision;
    }
}
