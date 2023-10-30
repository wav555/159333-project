package com.mygdx.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.actor.beas.ImageActor;
import com.mygdx.game.main.GameMian;
import com.mygdx.game.utii.AnimationUtii;
import com.mygdx.game.utii.R;

public class ArticleActor extends ImageActor {

    private float seed;

    private boolean isCollision;

    private Type type;

    private GameMian main;

    private Animation an;
    private float timp;

    public Type getType() {
        return type;
    }


    public void setType(Type type) {
        this.type = type;
    }


    public GameMian getMain() {
        return main;
    }


    public void setMain(GameMian main) {
        if(this.main == null)
            this.main = main;
        int ran = MathUtils.random(1, 3);

        if(ran <= 1.0) {
            type = ArticleActor.Type.heart;
            setTexture(main.getMapatlas().findRegion(R.BackGround.IMAGE_FOODBLOOD));
        }else if(ran>1.0&&ran<=2.0){
            type= ArticleActor.Type.award;
            Texture texture = new Texture(Gdx.files.internal("money.png"));
            TextureRegion region = new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
            setTexture(region);

        }else {
            type =  ArticleActor.Type.darts;
            if(an == null) {
                an = AnimationUtii.createAnimation((Texture) (main.getAsset().get(R.BackGround.IMAGE_DART_FOOD)), 1, 3);
                setTexture((TextureRegion) an.getKeyFrame(0));
                setTexture(main.getMapatlas().findRegion(R.BackGround.IMAGE_DARTSHOW));
            }
        }
    }


    @Override
    public void act(float arg0) {
        // TODO Auto-generated method stub
        super.act(arg0);
        setX(getX()+seed*arg0*R.BEI_SU);
        timp +=arg0*2;
        if(type == ArticleActor.Type.darts) {
            setTexture((TextureRegion) an.getKeyFrame(timp));
        }

    }


    public float getSeed() {
        return seed;
    }


    public void setSeed(float seed) {
        this.seed = seed;
    }


    public boolean isCollision() {
        return isCollision;
    }


    public void setCollision(boolean isCollision) {
        this.isCollision = isCollision;
    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub
        isCollision = false;
        type = null;

    }


    public static enum Type{
        heart,
        darts,
        award
    }
}

