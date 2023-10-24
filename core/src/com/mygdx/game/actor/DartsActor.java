package com.mygdx.game.actor;

import com.mygdx.game.actor.beas.ImageActor;
import com.mygdx.game.utii.R;

public class DartsActor extends ImageActor {

    private float ration;

    private float seed;

    @Override
    public void act(float arg0) {
        // TODO Auto-generated method stub
        super.act(arg0);
        ration-=arg0*getX();
        setRotation(ration);
        setX(getX()+seed*arg0* R.BEI_SU);
    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub
        ration = 0;

    }


    public float getRation() {
        return ration;
    }

    public void setRation(float ration) {
        this.ration = ration;
    }

    public float getSeed() {
        return seed;
    }

    public void setSeed(float seed) {
        this.seed = seed;
    }


}

