package com.mygdx.game.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.actor.beas.AnimationsActor;
import com.mygdx.game.main.GameMian;
import com.mygdx.game.utii.R;

public class CrowActor extends AnimationsActor {

    private GameMian main;

    public CrowActor() {

    }

    public void setMain(GameMian main) {
        if(this.main != null) {
            return;
        }
        this.main = main;
        Animation[] ans=new Animation[2];
        setIndex(new int[1]);
        Texture tx = main.getAsset().get(R.Actor.IMAGE_CROW,Texture.class);
        int row =1;
        int cols = 7;
        int perCellWidth = tx.getWidth()/cols;
        int perCellHeight = tx.getHeight()/row;

        TextureRegion[][] cellRegopns= TextureRegion.split(tx, perCellWidth, perCellHeight);
        TextureRegion[] z = new TextureRegion[row*cols-1];
        int index =0;
        for(int i=0;i<row;i++) {
            for(int j=0;j<cols-1;j++,index++) {
                z[index] = cellRegopns[i][j];
            }
        }
        setSize(perCellWidth, perCellHeight);
        ans[0] = new Animation(0.2f, z);
        ans[0].setPlayMode(Animation.PlayMode.LOOP);
        ans[1] = new Animation(0.2f,cellRegopns[0][6]);
        setIndex(new int[ans.length]);
        setAnimations(ans);
    }

    @Override
    public void act(float arg0) {
        // TODO Auto-generated method stub
        super.act(arg0);
        if(getType()==0) {
            setX(getX()+getSeed()*arg0*R.BEI_SU);
        }else {
            setX(getX()+getSeed()*arg0*0.5f*R.BEI_SU);
            setY(getY()+getSeed()*arg0*R.BEI_SU);
        }

    }
    @Override
    public void reset() {
        // TODO Auto-generated method stub
        setType(0);
        setCollision(false);
    }

    @Override
    public void death() {
        // TODO Auto-generated method stub
        setType(1);
    }
}
