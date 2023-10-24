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
    /*起跳的图片**/
    private TextureRegion up;
    /*下降的图片**/
    private TextureRegion down;
    /*发送飞镖的图片**/
    private TextureRegion dart;
    /*生命值**/
    private TextureRegion lifeImage;
    /*特殊飞镖图片**/
    private TextureRegion dartsImage;
    /*是否起跳**/
    private boolean isUp;
    /*是否发送飞镖**/
    private boolean isDart;

    private float centex;
    /*生命数量**/
    private int life=5;
    /*特殊飞镖数**/
    private int darts = 0;
    /*上升速度**/
    private float UpSeed = 3;
    /*下降速度**/
    private float downSeed = -300;
    /*计数器**/
    private float date;
    /*木桩**/
    public ImageActor bridge;
    /*游戏主类**/
    private GameMian main;

    /**构造方法 赋值初始化成员变量*/
    public NinjaActor(GameMian main){
        this.main = main;
        /*行走动画**/
        Animation walk = AnimationUtii.createAnimation(main.getAsset().get(R.Actor.IMAGE_NIN_DAR, Texture.class), 1, 10,Animation.PlayMode.LOOP , 0.15f);
        /*旋转动画**/
        Animation takeOff = AnimationUtii.createAnimation(main.getAsset().get(R.Actor.IMAGE_NIN_CHA, Texture.class), 1, 11,Animation.PlayMode.NORMAL , 0.25f,false,false,0,5);
        Animation Spin = AnimationUtii.createAnimation(main.getAsset().get(R.Actor.IMAGE_NIN_CHA, Texture.class), 1, 11,Animation.PlayMode.LOOP , 0.15f,false,false,5,10);
        Animation decline = AnimationUtii.createAnimation(main.getAsset().get(R.Actor.IMAGE_NIN_CHA, Texture.class), 1, 11,Animation.PlayMode.LOOP , 0.05f,false,false,10,11);
        Animation[] ans = new Animation[4];
        ans[0] = walk;
        ans[1] = takeOff;
        ans[2] = Spin;
        ans[3] = decline;
        /*初始化图片**/
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
                UpSeed += downSeed*arg0;	//上升速度 = 上升速度+下降速度*时间
                setY(getY()+UpSeed * arg0);		//设置Y轴坐标，y= y+上升速度*时间
                break;
            case 4:
                if(UpSeed >= 0) {
                    setTexture(up);
                }else {
                    setTexture(down);
                }
                UpSeed += downSeed*arg0;	//上升速度 = 上升速度+下降速度*时间
                setY(getY()+UpSeed * arg0);		//设置Y轴坐标，y= y+上升速度*时间
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
        // 设置演员初始化位置

        // 获取一个 MoveTo 动作, 3 秒内移动到 (150, 300) 的位置
        MoveToAction action = Actions.moveTo(getX(), main.getWordHeight()/2+75, 1.25F);

        // 将动作附加在演员身上, 执行动作

        /*
         * 动作执行原理（查看 Actor 和相应 Action 的源码）:
         *
         * 实际上动作添加到演员身上的后, 动作被存放到一个数组中, 然后在更新演员逻辑的 actor.act()方法中遍历存放动作的数组,
         * 对每一个动作根据时间步 delta 改变演员相应的状态属性值。然后在绘制演员的 actor.draw() 方法中绘制演员时使用新的
         * 状态属性值绘制, 和上一帧相比, 就显的演员被“动”起来了。
         */

        RunnableAction runnable = Actions.run(new Runnable() {
            public void run() {
                // 打印一句 log 表示动作已执行
                setType(2);
            }
        });

        SequenceAction sequence = Actions.sequence(action, runnable);

        // 执行顺序动作
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

