package com.mygdx.game.stage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.mygdx.game.actor.ArticleActor;
import com.mygdx.game.actor.BackgroundActor;
import com.mygdx.game.actor.Bridge;
import com.mygdx.game.actor.CrowActor;
import com.mygdx.game.actor.DaoActor;
import com.mygdx.game.actor.DartsActor;
import com.mygdx.game.actor.NinjaActor;
import com.mygdx.game.actor.WarriorActor;
import com.mygdx.game.actor.beas.AnimationsActor;
import com.mygdx.game.actor.beas.ImageActor;
import com.mygdx.game.main.GameMian;
import com.mygdx.game.stage.IStage.IStage;
import com.mygdx.game.utii.AnimationUtii;
import com.mygdx.game.utii.CollisionUtils;
import com.mygdx.game.utii.R;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.assets.AssetManager;


import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class MainStage extends IStage {
    /**Background image*/
    private ImageActor background1;
    /**Background object*/
    private BackgroundActor background2,background3;

    /**Bridges*/
    private Bridge bridge;
    /**Stake object pool*/
    private Pool<Bridge> pool;
    /**A collection class for a stake*/
    private ArrayList<ImageActor> poolList = new ArrayList<ImageActor>();
    /**Highest Y-axis coordinates*/
    private float bridgeTopYMax;
    /**Minimum Y-axis coordinates*/
    private float bridgeTopYMin;
    /**Warrior object pool*/
    private Pool<WarriorActor> warrPool ;
    /**Warrior set*/
    private ArrayList<AnimationsActor> anList = new ArrayList<AnimationsActor>();
    /**Crow set*/
    private Pool<CrowActor> crowPool ;
    /**Collection of knives and items*/
    private ArrayList<ImageActor> pt = new ArrayList<ImageActor>();
    /**Time to create a raven for the first time*/
    private float createCrowTime = 2f;
    private float createTime;
    /**When the item was first created*/
    private float createArcTime = 2f;
    private float createArTime;

    /**Knife object pool*/
    private Pool<DaoActor> daoPool;
    /**Item object pools*/
    private Pool<ArticleActor> arPool;
    /**Ninja object*/
    private NinjaActor ni;

    /**Bullet object pool*/
    private Pool<DartsActor> darPool;

    private List<DartsActor> darList = new ArrayList<DartsActor>();

    ImageButton an;

    Rectangle reAn;

    Actor nuActor;

    private float min;
    private Label la;
    int c;

    public void disport() {
        this.getRoot().clear();
        darList.clear();
        darPool.clear();
        arPool.clear();
        daoPool.clear();
        crowPool.clear();
        pt.clear();
        anList.clear();
        warrPool.clear();
        poolList.clear();
        pool.clear();


    }
    public void addActors() {
        R.BEI_SU = 1f;
        min =0;
        c = 0;
        this.addActor(background1);
        this.addActor(background2);
        this.addActor(background3);
        this.addActor(an);
        this.addActor(nuActor);
        nuActor.setZIndex(an.getZIndex());
        this.addActor(la);
        creatNin();
    }

    /**The main scene constructor*/
    public MainStage(GameMian main, Viewport view) {
        super(main, view);
        // TODO Auto-generated constructor stub
        init();
        disport();
        addActors();

    }

    /**Initialize the method*/
    public void init() {
        this.getRoot().clear();

        /**Create a pool of bridge objects*/
        pool = Pools.get(Bridge.class,5);
        /**Create a pool of warrior objects*/
        warrPool = Pools.get(WarriorActor.class,5);
        /**Create a pool of crow objects*/
        crowPool = Pools.get(CrowActor.class, 10);
        /**Create a pool of knife objects*/
        daoPool = Pools.get(DaoActor.class, 5);
        /**Create a pool of article objects*/
        arPool = Pools.get(ArticleActor.class, 10);

        darPool = Pools.get(DartsActor.class,20);

        background1 = new ImageActor(new TextureRegion(getMain().getAsset().get(R.BackGround.IMAGE_BRAGKGROUND, Texture.class)));

        background2 = new BackgroundActor(new TextureRegion(getMain().getAsset().get(R.BackGround.IMAGE_BRAGKGROUND1, Texture.class)));
        background2.setMove(true);
        background2.setSeed(-50);
        background2.setScale(2f);

        background3 = new BackgroundActor(new TextureRegion(getMain().getAsset().get(R.BackGround.IMAGE_BRAGKGROUND2, Texture.class)));
        background3.setMove(true);
        background3.setSeed(-100);
        background3.setScale(1.2f);

        //Bridge
        bridge =new Bridge(getMain(), 2);
        bridge.setSeed(R.Physical.SEED_X);	//set speed of movement

        //Calculate the maximum and minimum TopY coordinates
        bridgeTopYMin = Math.max(100, getHeight()- bridge.getHeight()-R.Physical.NIN_UP_SEED/2) ;
        bridgeTopYMax = Math.min(bridgeTopYMin+bridge.getHeight()*0.8f, getHeight()-R.Physical.NIN_UP_SEED-100f);
        bridgeTopYMax= Math.min(bridgeTopYMax, bridge.getHeight());

        TextureRegion[] ans = AnimationUtii.tuerx(this.getMain().getAsset().get(R.Actor.IMAGE_NIN_BUTT, Texture.class), 1, 2);
        an = new ImageButton(new TextureRegionDrawable(ans[0]), new TextureRegionDrawable(ans[1]));
        an.setX(getWidth()-100);
        an.setY(50);
        an.setSize(ans[0].getRegionWidth(), ans[0].getRegionHeight());
        an.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // TODO Auto-generated method stub
                if(ni.getType()==0||ni.getType() ==4) {
                    creatDart();
                    ni.setDart(true);
                }
                return super.touchDown(event, x, y, pointer, button);
            }

        });


        nuActor = new Actor();
        nuActor.setSize(getWidth(), getHeight());

        nuActor.addListener(new ClickListener() {
            int jumpTimes=0;
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                if(jumpTimes==0&&!ni.isUp()){
                    jumpTimes=1;
                    ni.setPlay(false);
                    ni.setUpSeed(300);
                    ni.setType(4);
                    ni.setBridge(null);
                }
                else if(jumpTimes==1) {
                    jumpTimes=0;
                    ni.setPlay(false);
                    ni.setUpSeed(300);
                    ni.setType(4);
                    ni.setBridge(null);
                    ni.setUp(true);
                }
                // TODO Auto-generated method stub
                if(!ni.isUp()) {

                    ni.setPlay(false);
                    ni.setUpSeed(300);
                    ni.setType(4);
                    ni.setBridge(null);
                    ni.setUp(true);

                }

                return super.touchDown(event, x, y, pointer, button);
            }

        });
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = getMain().getFont();
        la  = new Label("",style);
        la.setPosition(10, getHeight()-100);

    }

    /**Create Bridge methods*/
    public Bridge createBridge() {
        /**TopY of bridge*/
        float topY = MathUtils.random(bridgeTopYMin-80, bridgeTopYMax+100);
        Bridge br =  pool.obtain();	// create bridge
        br.setMian(getMain());
        br.setSum(MathUtils.random(1,3));
        if(poolList.size()==0)
            br.setX(0);
        else
            br.setX(poolList.get(poolList.size()-1).getReightX()+160);
        br.setY(0);
        br.setY(topY - br.getHeight());
        br.setSeed(R.Physical.SEED_X);
        poolList.add(br);
        this.addActor(br);
        br.setZIndex(this.nuActor.getZIndex());
        return br;
    }
    /**warrior*/
    public void createWarriorActor() {
        WarriorActor warr= warrPool.obtain();
        warr.setMain(getMain());
        warr.setBridge((Bridge)poolList.get(poolList.size()-1));
        warr.setPlay(true);
        warr.setLife(true);
        warr.setSeed(R.Physical.WARR_SEED_X);
        warr.setType(MathUtils.random(0, 1));
        this.addActor(warr);
        anList.add(warr);
        warr.setZIndex(this.nuActor.getZIndex());
    }
    /**Scene action*/
    @Override
    public void act(float arg0) {
        // TODO Auto-generated method stub
        super.act(arg0);
        if(poolList.size() < 5) {
            int c = MathUtils.random(1, 100);
            if(c<20) {
                createBridge();
            }else if(c <60) {
                createBridge();
                createWarriorActor();
                System.out.println(c);
            }else if(c < 100){
                Bridge bridge =createBridge();
                creatDao(bridge);
            }
        }
        createTime += arg0;
        if(createCrowTime <= createTime) {
            createCrowTime = MathUtils.random(3f, 8f);
            createTime = 0;
            createCrow();
        }

        createArTime += arg0;
        if(createArTime >= createArcTime) {
            createArTime = 0;
            createArcTime = MathUtils.random(3f, 8f);
            creatAr();
        }

        isColl();

        delete();
        min +=Math.abs(R.Physical.SEED_X*arg0/100*R.BEI_SU);
        la.setText("You ran "+(int)min+" meters");

        if((int)min%20==0&&c!=(int)min) {
            c = (int)min;
            R.BEI_SU +=0.1f;
            System.out.println(R.BEI_SU);
        }

        death();
    }
    /**Method of removing*/
    public void delete() {
        //remove bridge
        Iterator<ImageActor> it = poolList.iterator();
        while(it.hasNext()) {
            ImageActor actor = it.next();
            if(actor.getReightX()<0){
                getRoot().removeActor(actor);
                it.remove();
                pool.free((Bridge)actor);
                this.getRoot().removeActor(actor);
            }
        }
        /**remove warrior and crow*/
        Iterator<AnimationsActor> it1 = anList.iterator();
        while(it1.hasNext()) {
            AnimationsActor an= it1.next();
            if(an.getReightX()<0||an.getTopY()<0) {
                it1.remove();
                if(an instanceof WarriorActor) {
                    warrPool.free((WarriorActor)an);
                }else if(an instanceof CrowActor) {
                    crowPool.free((CrowActor)an);
                }
                this.getRoot().removeActor(an);
            }
        }
        /**remove items*/
        Iterator<ImageActor> pt = this.pt.iterator();
        while(pt.hasNext()) {
            ImageActor xt = pt.next();
            if(xt.getReightX() <0||xt.getTopY()<0) {
                pt.remove();
                if(xt instanceof DaoActor) {
                    daoPool.free((DaoActor)xt);
                }else if(xt instanceof ArticleActor) {
                    arPool.free((ArticleActor)xt);
                }
                this.getRoot().removeActor(xt);
            }
        }

        delteArt();
    }
    /**remove dart*/
    public void delteArt() {
        Iterator<DartsActor> pt = darList.iterator();
        while(pt.hasNext()) {
            DartsActor dar = pt.next();
            if(dar.getX()>=getWidth()) {
                pt.remove();
                darPool.free(dar);
                this.getRoot().removeActor(dar);
            }
        }
    }

    /**crow*/
    public void createCrow() {//乌鸦
        float topY = MathUtils.random(300, 500);
        CrowActor crow = crowPool.obtain();
        crow.setMain(getMain());
        crow.setSeed(R.Physical.CROW_SEED_X+R.Physical.SEED_X);
        crow.setX(getWidth());
        crow.setY(topY-crow.getHeight());
        crow.setPlay(true);
        anList.add(crow);
        this.addActor(crow);
        crow.setZIndex(this.nuActor.getZIndex());
    }
    /**knife*/
    public void creatDao(Bridge bridge) {
        DaoActor dao= daoPool.obtain();
        dao.setMain(getMain());
        dao.setBridge(bridge);
        this.addActor(dao);
        this.pt.add(dao);
        dao.setZIndex(this.nuActor.getZIndex());
    }
    /**create items*/
    public void creatAr() {//创建爱心
        float topY = MathUtils.random(200, 400);
        ArticleActor ar = arPool.obtain();
        ar.setMain(getMain());
        ar.setY	(topY-ar.getHeight());
        ar.setX(getWidth());
        ar.setSeed(R.Physical.SEED_X);
        this.addActor(ar);
        this.pt.add(ar);
        ar.setZIndex(this.nuActor.getZIndex());
    }
    /**create ninja**/
    public void creatNin() {//忍者
        ni = new NinjaActor(getMain());
        ni.setContX(200);
        ni.setY(300);
        ni.setType(3);
        this.addActor(ni);
        ni.setZIndex(this.nuActor.getZIndex());
    }
    /**create dart*/
    public void creatDart() {//保护
        DartsActor dar = darPool.obtain();
        dar.setY(ni.getY()+ni.getHeight()/2);
        dar.setX(ni.getReightX()+10);
        dar.setTexture(getMain().getMapatlas().findRegion(R.BackGround.IMAGE_DARTSHOW));
        dar.setOrigin(Align.center);
        dar.setSeed(R.Physical.DAR_SEED_X);
        this.addActor(dar);
        this.darList.add(dar);
    }

//    public void creatMoney() {
//        float topY = MathUtils.random(200, 400);
//        Money m = moneyPool.obtain();
//        m.setMain(getMain());
//        m.setY	(topY-m.getHeight());
//        m.setX(getWidth());
//        m.setSeed(R.Physical.SEED_X);
//        this.addActor(m);
//        this.pt.add(m);
//        m.setZIndex(this.nuActor.getZIndex());
//    }

    /**The method of colliding with bridges*/
    public void isColl() {
        if(ni.getType() !=0) {	//Determine if the ninja status is already on the bridge
            for(int i=0;i<poolList.size();i++) {	//Loop through the bridges
                ImageActor ac = poolList.get(i);
                if(CollisionUtils.isCollis(ac, ni,5)) {//Collision judgment
                    if(ni.getY()>=ac.getTopY()-20) {	//Determine if it is above the bridge
                        if(ni.setBridge(ac)) {			//set up the bridge
                            ni.setPlay(true);
                            ni.setType(0);				//set up the status
                            ni.setY(ac.getTopY()-5);
                            ni.setUp(false);
                        }

                    }

                }
            }

        }
        /**Call the collision item method*/
        isCollPt();

        isEnemy();

        isDar();
    }
    AssetManager yourAssetManager = new AssetManager();

    /**Collision items*/
    public void isCollPt() {
        Iterator<ImageActor> pt = this.pt.iterator();
//        AssetManager yourAssetManager = new AssetManager();
        while(pt.hasNext()) {		//Iterate through the collection
            ImageActor xt = pt.next();	//Take out the data
            if(!xt.isCollision()&&CollisionUtils.isCollis(xt, ni, 5)) {	//judge if they collide
                xt.setCollision(true);		//The settings are configured
                    if (xt instanceof DaoActor) {    //Determine whether to collide with the knife
<<<<<<< HEAD
                        ni.setLife(ni.getLife() - 1);//life--
                        yourAssetManager.load("knife.mp3", Sound.class);
                        yourAssetManager.finishLoading();
                        if (yourAssetManager.isLoaded("knife.mp3", Sound.class)) {
                            Sound sound = yourAssetManager.get("knife.mp3", Sound.class);
                            long soundID=sound.play();
                            sound.setVolume(soundID,-1.5F);
=======
                        ni.setLife(ni.getLife() - 1);    //life--
                    } else if (xt instanceof ArticleActor) {    //Determine the items that collide
                        if (((ArticleActor) xt).getType() == ArticleActor.Type.darts) {//Whether the collision was a dart
                            ni.setDarts(ni.getDarts() + 1);    //dart++
                        } else if (((ArticleActor) xt).getType() == ArticleActor.Type.heart) {//judge if he gets a heart
                            ni.setLife(ni.getLife() + 1);        //life++

>>>>>>> 3e3a751308dcb95ca92eaa0b4557572b5387c72f
                        }
                    }
                    else if (xt instanceof ArticleActor) {    //Determine the items that collide
                        if (((ArticleActor) xt).getType() == ArticleActor.Type.darts) {//Whether the collision was a dart
                            ni.setDarts(ni.getDarts() + 1);//dart++
                            yourAssetManager.load("getLife.wav", Sound.class);
                            yourAssetManager.finishLoading();
                            if (yourAssetManager.isLoaded("getLife.wav", Sound.class)) {
                                Sound sound = yourAssetManager.get("getLife.wav", Sound.class);
                                long soundID=sound.play();
                                sound.setVolume(soundID,1.7F);
                            }
                        } else if (((ArticleActor) xt).getType() == ArticleActor.Type.heart) {//judge if he gets a heart
                            ni.setLife(ni.getLife() + 1);        //life++
                            yourAssetManager.load("ding.mp3", Sound.class);
                            yourAssetManager.finishLoading();
                            if (yourAssetManager.isLoaded("ding.mp3", Sound.class)) {
                                Sound sound = yourAssetManager.get("ding.mp3", Sound.class);
                                long soundID=sound.play();
                                sound.setVolume(soundID,-1.5F);
                            }
                        }else if (((ArticleActor) xt).getType() == ArticleActor.Type.money) {//judge if he gets a heart
                            ni.setMoneyNumber(ni.getMoneyNumber() + 1);        //life++
                            yourAssetManager.load("ding.mp3", Sound.class);
                            yourAssetManager.finishLoading();
                            if (yourAssetManager.isLoaded("ding.mp3", Sound.class)) {
                                Sound sound = yourAssetManager.get("ding.mp3", Sound.class);
                                long soundID=sound.play();
                                sound.setVolume(soundID,-1.5F);
                            }
                        }

                        pt.remove();                        //remove the item
                        arPool.free((ArticleActor) xt);//put into pool

                        this.getRoot().removeActor(xt);        //remove the object
                    }
            }
        }
    }
    /**Collision enemy*/
    public void isEnemy() {
        Iterator<AnimationsActor> pt = anList.iterator();
        while(pt.hasNext()) {
            AnimationsActor ac = pt.next();
            if (!ac.isCollision() && CollisionUtils.isCollis(ac, ni, 10)) {
                ac.setCollision(true);
                if (ni.getType() != 2) { //Determines whether the current state is rotated
                    ni.setLife(ni.getLife() - 1);
                    yourAssetManager.load("knife.mp3", Sound.class);
                    yourAssetManager.finishLoading();
                    if (yourAssetManager.isLoaded("knife.mp3", Sound.class)) {
                        Sound sound = yourAssetManager.get("knife.mp3", Sound.class);
                        long soundID=sound.play();
                        sound.setVolume(soundID,-1.5F);
                    }
                }
            }
        }
    }

    public void isDar() {
        Iterator<DartsActor> pt = darList.iterator();
        while(pt.hasNext()) {
            DartsActor dar = pt.next();
            for(int i=0;i<anList.size();i++) {
                AnimationsActor an = anList.get(i);
                if(an !=null&&!an.isCollision()&&CollisionUtils.isCollis(dar, an, 5)) {
                    an.setCollision(true);
                        an.death();
                }
            }
        }
    }

    private void death() {
        if(ni.getLife() <=0||ni.getTopY()<=0) {
            yourAssetManager.load("fail.wav", Sound.class);
            yourAssetManager.finishLoading();
            if (yourAssetManager.isLoaded("fail.wav", Sound.class)) {
                Sound sound = yourAssetManager.get("fail.wav", Sound.class);
                long soundID=sound.play();
                sound.setVolume(soundID,1.5F);
            }
            this.setView(false);
            getMain().getGameScreen().showOver((int)min);

        }
    }



}
