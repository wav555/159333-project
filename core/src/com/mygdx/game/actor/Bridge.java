package com.mygdx.game.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.actor.beas.ImageActor;
import com.mygdx.game.main.GameMian;
import com.mygdx.game.utii.R;

// responsible for the pillars used by characters to stand on
public class Bridge extends ImageActor {

    private TextureRegion bridgeBody;
    private TextureRegion bridfeLeft,bridfeRegin;
    private TextureRegion bridfeBodyLeft,bridfeBodyRegin;
    private GameMian mian;
    private float seed;
    private int sum ;
    private boolean isMove;
    public Bridge(GameMian mian,int sum) {
        this.mian = mian;
        this.sum = sum;
        setSum(sum);
    }

    public Bridge() {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // TODO Auto-generated method stub
        Color superColor = batch.getColor();
        Color thisColor = this.getColor();
        batch.setColor(thisColor.a, thisColor.b, thisColor.g, thisColor.r);
        //Draw Left Pillar
        batch.draw(
                bridfeLeft,
                getX()+bridfeBodyLeft.getRegionWidth(), getY(),
                getOriginX(), getOriginY(),
                bridfeLeft.getRegionWidth(), bridfeLeft.getRegionHeight(),
                getScaleX(), getScaleY(),
                getRotation()
        );

        //Draw Right Pillar
        batch.draw(
                bridfeRegin,
                getX()-bridfeBodyRegin.getRegionWidth()*3+getWidth(), getY(),
                getOriginX(), getOriginY(),
                bridfeRegin.getRegionWidth(), bridfeRegin.getRegionHeight(),
                getScaleX(), getScaleY(),
                getRotation()
        );

        batch.draw(
                bridfeBodyLeft,
                getX(), getY()+getHeight()-bridfeBodyLeft.getRegionHeight(),
                getOriginX(), getOriginY(),
                bridfeBodyLeft.getRegionWidth(), bridfeBodyLeft.getRegionHeight(),
                getScaleX(), getScaleY(),
                getRotation()
        );

        for(int i =0;i<sum;i++) {
            batch.draw(
                    bridgeBody,
                    getX()+bridgeBody.getRegionWidth()*i+bridfeBodyLeft.getRegionWidth(),
                    getY()+getHeight()-bridgeBody.getRegionHeight(),
                    getOriginX(), getOriginY(),
                    bridgeBody.getRegionWidth(), bridgeBody.getRegionHeight(),
                    getScaleX(), getScaleY(),
                    getRotation()
            );
        }



        batch.draw(
                bridfeBodyRegin,
                getX()+getWidth()-bridfeBodyRegin.getRegionWidth(), getY()+getHeight()-bridfeBodyRegin.getRegionHeight(),
                getOriginX(), getOriginY(),
                bridfeBodyRegin.getRegionWidth(), bridfeBodyRegin.getRegionHeight(),
                getScaleX(), getScaleY(),
                getRotation()
        );


        batch.setColor(superColor);

    }

    @Override
    public void act(float arg0) {
        // TODO Auto-generated method stub
        super.act(arg0);
        if(isMove) {
            setX(getX()+seed*arg0* R.BEI_SU);
        }
    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub
        sum = 0;
        setY(0);
    }

    public void setSum(int sum) {
        this.sum = sum;
        isMove = true;
        if(bridgeBody ==null)
            bridgeBody = new TextureRegion(mian.getMapatlas().findRegion(R.BackGround.IMAGE_BAM_MIND));

        if(bridfeBodyLeft ==null)
            bridfeBodyLeft = new TextureRegion(mian.getMapatlas().findRegion(R.BackGround.IMAGE_BAM_LEFT));

        if(bridfeBodyRegin ==null)
            bridfeBodyRegin = new TextureRegion(mian.getMapatlas().findRegion(R.BackGround.IMAGE_BAM_REGHT));

        if(bridfeLeft ==null)
            bridfeLeft = new TextureRegion(mian.getMapatlas().findRegion(R.BackGround.IMAGE_BAM_UNDER));

        if(bridfeRegin ==null)
            bridfeRegin = new TextureRegion(mian.getMapatlas().findRegion(R.BackGround.IMAGE_BAM_UNDER));

        setSize(sum*bridgeBody.getRegionWidth()+bridfeBodyLeft.getRegionWidth()+bridfeBodyRegin.getRegionWidth(),
                bridfeLeft.getRegionHeight());


    }

    public GameMian getMian() {
        return mian;
    }

    public void setMian(GameMian mian) {
        this.mian = mian;
    }

    public float getSeed() {
        return seed;
    }

    public void setSeed(float seed) {
        this.seed = seed;
    }

    public boolean isMove() {
        return isMove;
    }

    public void setMove(boolean isMove) {
        this.isMove = isMove;
    }

    public int getSum() {
        return sum;
    }

}

