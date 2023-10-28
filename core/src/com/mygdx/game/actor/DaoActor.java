package com.mygdx.game.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.actor.beas.ImageActor;
import com.mygdx.game.main.GameMian;
import com.mygdx.game.utii.R;

public class DaoActor extends ImageActor {

   // The length of the knife array
    private int sum;

    //Wooden stakes, used to place on top of wooden stakes
    private Bridge bridge;

   //The movement speed should not have its own movement speed,
   //the one with wooden stakes should prevail
    private float seed;

    //Class for images
    private GameMian main;

    //Display images
    private Texture tu;

    //Set up wooden stakes
    //initialize the size and position of the knife array
    public Bridge getBridge() {
        return bridge;
    }

    public void setBridge(Bridge bridge) {
        //Random knife array width
        sum = MathUtils.random(1, 8);
        //Set the position of the knife array
        setX(MathUtils.random(bridge.getX(), bridge.getReightX()-sum*tu.getWidth()));

        setY(bridge.getTopY()-10);
        //Set Movement Speed
        this.seed = bridge.getSeed();
        //set size
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
    //Draw a knife array based on the sum value
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
