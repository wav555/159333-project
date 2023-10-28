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

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class MainStage extends IStage {
    /**背景图片*/
    private ImageActor background1;
    /**背景对象*/
    private BackgroundActor background2,background3;

    /**木桩*/
    private Bridge bridge;
    /**木桩的对象池*/
    private Pool<Bridge> pool;
    /**木桩的集合类*/
    private ArrayList<ImageActor> poolList = new ArrayList<ImageActor>();
    /**最高Y轴坐标*/
    private float bridgeTopYMax;
    /**最低Y轴坐标*/
    private float bridgeTopYMin;
    /**武士对象池*/
    private Pool<WarriorActor> warrPool ;
    /**武士集合*/
    private ArrayList<AnimationsActor> anList = new ArrayList<AnimationsActor>();
    /**乌鸦集合*/
    private Pool<CrowActor> crowPool ;
    /**刀和物品集合*/
    private ArrayList<ImageActor> pt = new ArrayList<ImageActor>();
    /**初次创建乌鸦时间*/
    private float createCrowTime = 2f;
    private float createTime;
    /**初次创建物品时间*/
    private float createArcTime = 2f;
    private float createArTime;

    /**刀对象池*/
    private Pool<DaoActor> daoPool;
    /**物品对象池*/
    private Pool<ArticleActor> arPool;
    /**忍者对象*/
    private NinjaActor ni;

    /**子弹对象池*/
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

    /**主场景构造方法*/
    public MainStage(GameMian main, Viewport view) {
        super(main, view);
        // TODO Auto-generated constructor stub
        init();
        disport();
        addActors();

    }

    /**初始化方法*/
    public void init() {
        this.getRoot().clear();

        /**创建木桩对象池*/
        pool = Pools.get(Bridge.class,5);
        /**创建武士对象池*/
        warrPool = Pools.get(WarriorActor.class,5);
        /**创建乌鸦对象池*/
        crowPool = Pools.get(CrowActor.class, 10);
        /**创建刀对象池*/
        daoPool = Pools.get(DaoActor.class, 5);
        /**创建物品对象池*/
        arPool = Pools.get(ArticleActor.class, 10);

        darPool = Pools.get(DartsActor.class,20);



        //创建背景1
        background1 = new ImageActor(new TextureRegion(getMain().getAsset().get(R.BackGround.IMAGE_BRAGKGROUND, Texture.class)));

        //创建背景2
        background2 = new BackgroundActor(new TextureRegion(getMain().getAsset().get(R.BackGround.IMAGE_BRAGKGROUND1, Texture.class)));
        background2.setMove(true);
        background2.setSeed(-50);
        background2.setScale(2f);

        //创建背景3
        background3 = new BackgroundActor(new TextureRegion(getMain().getAsset().get(R.BackGround.IMAGE_BRAGKGROUND2, Texture.class)));
        background3.setMove(true);
        background3.setSeed(-100);
        background3.setScale(1.2f);

        //创建木桩
        bridge =new Bridge(getMain(), 2);
        bridge.setSeed(R.Physical.SEED_X);	//设置移动速度

        //计算最大和最小TopY轴坐标
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

    /**创建木桩方法*/
    public Bridge createBridge() {
        /**随机出木桩的TopY*/
        float topY = MathUtils.random(bridgeTopYMin-80, bridgeTopYMax+100);
        Bridge br =  pool.obtain();	//创建木桩
        br.setMian(getMain());
        br.setSum(MathUtils.random(1,3));
        if(poolList.size()==0)
            br.setX(0);
        else
            br.setX(poolList.get(poolList.size()-1).getReightX()+50);
        br.setY(0);
        br.setY(topY - br.getHeight());
        br.setSeed(R.Physical.SEED_X);
        poolList.add(br);
        this.addActor(br);
        br.setZIndex(this.nuActor.getZIndex());
        return br;
    }
    /**创建武士方法*/
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
    /**场景动作*/
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
    /**移除方法*/
    public void delete() {
        //移除木桩
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
        /**移除武士和乌鸦的方法*/
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
        /**移除物品方法*/
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
    /**移除子弹*/
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

    /**创建乌鸦*/
    public void createCrow() {
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
    /**创建陷阱*/
    public void creatDao(Bridge bridge) {
        DaoActor dao= daoPool.obtain();
        dao.setMain(getMain());
        dao.setBridge(bridge);
        this.addActor(dao);
        this.pt.add(dao);
        dao.setZIndex(this.nuActor.getZIndex());

    }
    /**创建物品*/
    public void creatAr() {
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
    /**创建忍者**/
    public void creatNin() {
        ni = new NinjaActor(getMain());
        ni.setContX(200);
        ni.setY(300);
        ni.setType(3);
        this.addActor(ni);
        ni.setZIndex(this.nuActor.getZIndex());
    }
    /**创建子弹（飞镖）*/
    public void creatDart() {
        DartsActor dar = darPool.obtain();
        dar.setY(ni.getY()+ni.getHeight()/2);
        dar.setX(ni.getReightX()+10);
        dar.setTexture(getMain().getMapatlas().findRegion(R.BackGround.IMAGE_DARTSHOW));
        dar.setOrigin(Align.center);
        dar.setSeed(R.Physical.DAR_SEED_X);
        this.addActor(dar);
        this.darList.add(dar);
    }

    /**碰撞木桩的方法*/
    public void isColl() {
        if(ni.getType() !=0) {	//判断忍者状态是否为已在木桩上
            for(int i=0;i<poolList.size();i++) {	//循环遍历木桩
                ImageActor ac = poolList.get(i);
                if(CollisionUtils.isCollis(ac, ni,5)) {//碰撞判断
                    if(ni.getY()>=ac.getTopY()-20) {	//判断是否在木桩上方
                        if(ni.setBridge(ac)) {			//设置木桩
                            ni.setPlay(true);
                            ni.setType(0);				//设置状态
                            ni.setY(ac.getTopY()-5);
                            ni.setUp(false);
                        }

                    }

                }
            }

        }
        /**调用碰撞物品方法*/
        isCollPt();

        isEnemy();

        isDar();
    }

    /**碰撞物品*/
    public void isCollPt() {
        Iterator<ImageActor> pt = this.pt.iterator();//遍历器
        while(pt.hasNext()) {		//遍历集合
            ImageActor xt = pt.next();	//取出数据
            if(!xt.isCollision()&&CollisionUtils.isCollis(xt, ni, 5)) {	//判断是否碰撞，且是否是已配置过
                xt.setCollision(true);		//设置已配置
                    if (xt instanceof DaoActor) {    //判断是否碰撞刀
                        ni.setLife(ni.getLife() - 1);    //将生命减一
                    } else if (xt instanceof ArticleActor) {    //判断碰撞的道具
                        if (((ArticleActor) xt).getType() == ArticleActor.Type.darts) {//碰撞的是否为飞镖
                            ni.setDarts(ni.getDarts() + 1);    //增加飞镖
                        } else if (((ArticleActor) xt).getType() == ArticleActor.Type.heart) {//碰撞是否生命
                            ni.setLife(ni.getLife() + 1);        //生命加一

                        }
                        pt.remove();                        //移除该物品
                        arPool.free((ArticleActor) xt);        //放入对象池
                        this.getRoot().removeActor(xt);        //移除对象
                    }
            }
        }
    }
    /**碰撞敌人*/
    public void isEnemy() {
        Iterator<AnimationsActor> pt = anList.iterator();//遍历器
        while(pt.hasNext()) {
            AnimationsActor ac = pt.next();
            if (!ac.isCollision() && CollisionUtils.isCollis(ac, ni, 10)) {
                ac.setCollision(true);
                if (ni.getType() != 2) { // 判断当前状态是否为旋转状态
                    ni.setLife(ni.getLife() - 1);
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
            this.setView(false);
            getMain().getGameScreen().showOver((int)min);
        }
    }

}
