package com.mygdx.game.utii;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class CollisionUtils {
   // define collision detection area
    public static Rectangle rectangle1 = new Rectangle();
    public static Rectangle rectangle2 = new Rectangle();

    public static Rectangle time = new Rectangle();

    public synchronized static boolean isCollis(Actor a,Actor b,float buffer) {
        //determine if there are objects that are going to collide with each other
        if(a == null||b == null) {
            return false;
        }
       // calculate collision detection area
        rectangle1.setSize(a.getWidth()*a.getScaleX(),a.getHeight()*a.getScaleY());
        rectangle1.setPosition(a.getX()-(a.getOriginX()*a.getScaleX()-a.getOriginX()), a.getY()-(a.getOriginY()*a.getScaleY()-a.getOriginY()));

        rectangle2.setSize(b.getWidth()*b.getScaleX(),b.getHeight()*b.getScaleY());
        rectangle2.setPosition(b.getX()-(b.getOriginX()*b.getScaleX()-b.getOriginX()), b.getY()-(b.getOriginY()*b.getScaleY()-b.getOriginY()));

        float doubleBuffer = buffer *2;
        //check collision
        if(rectangle1.getWidth()> doubleBuffer && rectangle1.getHeight() > doubleBuffer) {
            time.set(rectangle1.x+buffer, rectangle1.y+buffer, rectangle1.width-doubleBuffer, rectangle1.height - doubleBuffer);

            return time.overlaps(rectangle2);
        }else if(rectangle2.getWidth()>doubleBuffer && rectangle2.getHeight()>doubleBuffer) {
            time.set(rectangle2.x+buffer, rectangle2.y+buffer, rectangle2.width-doubleBuffer, rectangle2.height-doubleBuffer);
            return time.overlaps(rectangle1);
        }
        return rectangle1.overlaps(rectangle2);
    }
}

