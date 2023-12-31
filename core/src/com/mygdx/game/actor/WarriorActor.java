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
    //Game main category
    private GameMian main;
    //Standing wooden stakes
    private Bridge bridge;
   //speed
    private float seed;
   //Parametric construction method responsible for resetting some basic variables
    private TextureRegion[] death;

    private float deathX,deathY;

    public GameMian getMain() {
        return main;
    }


   //Set the game main class and animate it

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

    //Set the wooden stakes and set the x and Y axis coordinates of the samurai
    public void setBridge(Bridge bridge) {
        setY(bridge.getTopY());
        setX(MathUtils.random(bridge.getX(), bridge.getReightX()));
        this.bridge = bridge;
    }

    public void setSeed(float seed) {
        this.seed = seed;
    }
    ///The actions of a samurai
    @Override
    public void act(float arg0) {
        // TODO Auto-generated method stub
        super.act(arg0);

        if(bridge == null) {
            return;
        }
        switch(getType()) {
            case 0://Move in the 0 direction and move to the right at the vertex
                setX(getX()+(bridge.getSeed()+this.seed)*arg0*R.BEI_SU);
                if(this.getX() <= bridge.getX()) {
                    setType(1);
                }
                break;
            case 1://1 and 0 are opposite
                setX(getX()+(bridge.getSeed()-this.seed)*arg0*R.BEI_SU);
                if(this.getReightX()>=bridge.getReightX()) {
                    setType(0);
                }
                break;
            case 2://2 deaths, Y-axis downward movement
                setY(getY()-Math.abs((bridge.getSeed()+this.seed*2)*arg0)*R.BEI_SU);
                break;
        }
    }

    //The reset method for placing objects in the pool, including resetting speed, wooden stakes, and collision or not
    @Override
    public void reset() {
        // TODO Auto-generated method stub
        this.bridge = null;
        this.seed = 0f;
        deathX = 0;
        deathY= 0;
        setCollision(false);

    }
    //Method called on death
    public void death() {
        deathX = getX();
        deathY = getY();
        setType(2);
    }

}
