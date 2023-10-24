package com.mygdx.game.utii;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class CollisionUtils {

    public static Rectangle ac1 = new Rectangle();
    public static Rectangle ac2 = new Rectangle();

    public static Rectangle time = new Rectangle();

    public synchronized static boolean isCollis(Actor a,Actor b,float timp) {
        if(a == null||b == null) {
            return false;
        }

        ac1.setSize(a.getWidth()*a.getScaleX(),a.getHeight()*a.getScaleY());
        ac1.setPosition(a.getX()-(a.getOriginX()*a.getScaleX()-a.getOriginX()), a.getY()-(a.getOriginY()*a.getScaleY()-a.getOriginY()));

        ac2.setSize(b.getWidth()*b.getScaleX(),b.getHeight()*b.getScaleY());
        ac2.setPosition(b.getX()-(b.getOriginX()*b.getScaleX()-b.getOriginX()), b.getY()-(b.getOriginY()*b.getScaleY()-b.getOriginY()));

        float doubletimp = timp *2;

        if(ac1.getWidth()> doubletimp && ac1.getHeight() > doubletimp) {
            time.set(ac1.x+timp, ac1.y+timp, ac1.width-doubletimp, ac1.height - doubletimp);

            return time.overlaps(ac2);
        }else if(ac2.getWidth()>doubletimp && ac2.getHeight()>doubletimp) {
            time.set(ac2.x+timp, ac2.y+timp, ac2.width-doubletimp, ac2.height-doubletimp);
            return time.overlaps(ac1);
        }


        return ac1.overlaps(ac2);
    }
}
