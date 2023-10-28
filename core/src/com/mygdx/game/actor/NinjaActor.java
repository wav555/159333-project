package com.mygdx.game.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.mygdx.game.actor.beas.AnimationsActor;
import com.mygdx.game.actor.beas.ImageActor;
import com.mygdx.game.main.GameMian;
import com.mygdx.game.utii.AnimationUtii;
import com.mygdx.game.utii.R;

public class NinjaActor extends AnimationsActor {
    private TextureRegion up;//up image
    private TextureRegion down;//down image
    private TextureRegion dart; //darts image
    private TextureRegion lifeImage; //Health value
    private TextureRegion dartsImage;//Special dart pictures
    private boolean isUp;//Jump or not
    private boolean isDart;//if send darts
    private float centex;
    private int life=5;//num of life
    private int moneyNumber=0;
    private int darts = 0;//num of darts
    private float UpSeed = 3;//up speed
    private float downSeed = -300;//down speed
    private float date;//Counter
    public ImageActor bridge;
    private GameMian main;

   //Constructor assignment initializes member variables
    public NinjaActor(GameMian main){
        this.main = main;
        //Walking animation
        Animation walk = AnimationUtii.createAnimation(main.getAsset().get(R.Actor.IMAGE_NIN_DAR, Texture.class), 1, 10,Animation.PlayMode.LOOP , 0.15f);
        //Rotate Animation
        Animation takeOff = AnimationUtii.createAnimation(main.getAsset().get(R.Actor.IMAGE_NIN_CHA, Texture.class), 1, 11,Animation.PlayMode.NORMAL , 0.25f,false,false,0,5);
        Animation Spin = AnimationUtii.createAnimation(main.getAsset().get(R.Actor.IMAGE_NIN_CHA, Texture.class), 1, 11,Animation.PlayMode.LOOP , 0.15f,false,false,5,10);
        Animation decline = AnimationUtii.createAnimation(main.getAsset().get(R.Actor.IMAGE_NIN_CHA, Texture.class), 1, 11,Animation.PlayMode.LOOP , 0.05f,false,false,10,11);
        Animation[] ans = new Animation[4];
        ans[0] = walk;
        ans[1] = takeOff;
        ans[2] = Spin;
        ans[3] = decline;
       //Initialize image
        TextureRegion[] tu = (TextureRegion[]) walk.getKeyFrames();
        up = tu[2];
        down = tu[1];
        setAnimations(ans);
        setIndex(new int[4]);
        setType(1);
        lifeImage = main.getMapatlas().findRegion(R.BackGround.IMAGE_BLOOD);
        dartsImage = main.getMapatlas().findRegion(R.BackGround.IMAGE_DARTSHOW);
        dart = new TextureRegion( main.getAsset().get(R.Actor.IMAGE_NIN_BULL, Texture.class));
    }

    public void drawdingzhi() {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // TODO Auto-generated method stub
        super.draw(batch, parentAlpha);

        for(int i=0;i<life;i++) {
            batch.draw(lifeImage, i*lifeImage.getRegionWidth(), main.getWordHeight()-lifeImage.getRegionHeight());
        }

        for(int i=0;i<moneyNumber;i++) {
            batch.draw(dartsImage, i*dartsImage.getRegionWidth(), main.getWordHeight()/2+100-lifeImage.getRegionHeight());
        }


    }

    @Override
    public void act(float arg0) {
        // TODO Auto-generated method stub
        super.act(arg0);


        switch(getType()) {
            case 0:
                if(bridge == null) {
                    setType(4) ;
                    setPlay(false);
                }
                if(getX() > bridge.getReightX()-20) {
                    setPlay(false);
                    setType(4) ;

                    UpSeed = 0;
                }
                break;
            case 1:
                break;
            case 2:
                date += arg0;
                if(date >=5) {
                    date = 0;
                    setType(3);
                    UpSeed = 0;
                }
                break;
            case 3:
                date += arg0;
                if(date >=0.35f) {
                    date =0;
                    setPlay(false);
                    setType(4);
                    UpSeed = 0;
                }
                UpSeed += downSeed*arg0;
                setY(getY()+UpSeed * arg0);
                break;
            case 4:
                if(UpSeed >= 0) {
                    setTexture(up);
                }else {
                    setTexture(down);
                }
                UpSeed += downSeed*arg0;
                setY(getY()+UpSeed * arg0);
                break;
        }

        dart(arg0);

    }

    public void dart(float arg0) {
        if(isDart) {
            if(getType() ==4) {
                setPlay(false);
            }
            date+=arg0;
            setTexture(dart);
        }

        if(date>0.25f&&isDart) {
            date = 0;
            isDart = false;
            if(getType() !=4) {
                setPlay(true);
            }
        }
    }

    public void setContX(float centex) {
        this.centex = centex;
        setContX();
    }
    public void setContX() {
        TextureRegion text;
        if(isPlay()) {
            text = (TextureRegion) getAnimations()[getType()].getKeyFrame(0);
            setSize(text.getRegionWidth(), text.getRegionHeight());
            setX(centex-text.getRegionWidth()/2);
        }else {
            setX(centex-up.getRegionWidth()/2);
        }
    }


    public void setUpSeed(float seed) {
        this.UpSeed = seed;

    }


    public boolean setBridge(ImageActor bridge) {
        if(this.bridge != bridge) {
            this.bridge = bridge;
            return true;
        }
        return false;
    }

    private void testMoveToAction() {
        // Set actor initialization position

        // Obtain a MoveTo action and move to a position of (150, 300) within 3 seconds
        MoveToAction action = Actions.moveTo(getX(), main.getWordHeight()/2+75, 1.25F);

        // Attach the action to the actor and execute the action

        /*
         *Action execution principle (check the source code of Actor and corresponding Action):
         *
         *After the action is actually added to the actor, it is stored in an array and then iterated through the array storing the action in the actor. act() method that updates the actor's logic,
         *Change the actor's corresponding state attribute value based on the time step delta for each action. Then, when drawing actors in the actor. draw() method, use the new
         *The state attribute values are drawn, and compared to the previous frame, it shows that the actor has been "moved".
         */

        RunnableAction runnable = Actions.run(new Runnable() {
            public void run() {
                //Print a log to indicate that the action has been executed
                setType(2);
            }
        });

        SequenceAction sequence = Actions.sequence(action, runnable);

        // Execute sequential actions
        this.addAction(sequence);
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        if(life <=8)
            this.life = life;
    }

    public int getDarts() {
        return darts;
    }

    public void setDarts(int darts) {
        if(darts >0) {
            setPlay(true);
            setType(1);
            this.darts = 0;
            testMoveToAction();
            return;
        }
        this.darts = darts;
    }

    public int getMoneyNumber() {
        return moneyNumber;
    }

    public void setMoneyNumber(int moneyNumber) {
        this.moneyNumber = moneyNumber;
    }
    @Override
    public synchronized void setType(int type) {
        // TODO Auto-generated method stub
        super.setType(type);
        setContX();
    }

    public boolean isUp() {
        return isUp;
    }

    public void setUp(boolean isUp) {
        this.isUp = isUp;
    }

    public boolean isDart() {
        return isDart;
    }

    public void setDart(boolean isDart) {
        this.isDart = isDart;
    }

}

