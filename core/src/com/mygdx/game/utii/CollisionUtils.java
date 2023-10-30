package com.mygdx.game.utii;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class CollisionUtils {
   // define collision detection area
    public static Rectangle rectangle1 = new Rectangle();
    public static Rectangle rectangle2 = new Rectangle();

    public static Rectangle time = new Rectangle();

    public synchronized static boolean isCollis(Actor a,Actor b,float timp) {
        //determine if there are objects that are going to collide with each other
        if(a == null||b == null) {
            return false;
        }
       // calculate collision detection area
        rectangle1.setSize(a.getWidth()*a.getScaleX(),a.getHeight()*a.getScaleY());
        rectangle1.setPosition(a.getX()-(a.getOriginX()*a.getScaleX()-a.getOriginX()), a.getY()-(a.getOriginY()*a.getScaleY()-a.getOriginY()));

        rectangle2.setSize(b.getWidth()*b.getScaleX(),b.getHeight()*b.getScaleY());
        rectangle2.setPosition(b.getX()-(b.getOriginX()*b.getScaleX()-b.getOriginX()), b.getY()-(b.getOriginY()*b.getScaleY()-b.getOriginY()));

        float doubletimp = timp *2;
        //check collision
        if(rectangle1.getWidth()> doubletimp && rectangle1.getHeight() > doubletimp) {
            time.set(rectangle1.x+timp, rectangle1.y+timp, rectangle1.width-doubletimp, rectangle1.height - doubletimp);

            return time.overlaps(rectangle2);
        }else if(rectangle2.getWidth()>doubletimp && rectangle2.getHeight()>doubletimp) {
            time.set(rectangle2.x+timp, rectangle2.y+timp, rectangle2.width-doubletimp, rectangle2.height-doubletimp);
            return time.overlaps(rectangle1);
        }
        return rectangle1.overlaps(rectangle2);
    }
}

