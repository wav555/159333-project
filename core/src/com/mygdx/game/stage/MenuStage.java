package com.mygdx.game.stage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.actor.beas.ImageActor;
import com.mygdx.game.main.GameMian;
import com.mygdx.game.stage.IStage.IStage;
import com.mygdx.game.utii.R;

public class MenuStage extends IStage {
    /**开始按钮*/
    private ImageButton play;
    /**成就按钮*/
    //private ImageButton hid;
    /**推出游戏*/
    private ImageButton over;
    //其他按钮
    //private ImageButton move;
    //帮助按钮
    private ImageActor breakGround;

    /**菜单场景的构造方法*/
    public MenuStage(GameMian main, Viewport view) {
        super(main, view);
        // TODO Auto-generated constructor stub
        init();
    }
    //初始化方法
    public void init() {

        /**创建背景**/
        TextureRegion r = new TextureRegion(getMain().getAsset().get(R.emun.IMAGE_BRAGKGROUND, Texture.class));
        breakGround = new ImageActor(r);
        breakGround.setCenter(getWidth()/2, getHeight()/2);
        this.addActor(breakGround);
        /**创建开始按钮*/
        play = new ImageButton(new TextureRegionDrawable(getMain().getAtlas().findRegion(R.emun.IMAGE_BUTTON_PLAY_UP)),
                new TextureRegionDrawable(getMain().getAtlas().findRegion(R.emun.IMAGE_BUTTON_PLAY_UP)));
        play.setX(getWidth()*60/130);
        play.setY((getHeight()-getMain().getHeibian()/2)*20/100);
        play.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO Auto-generated method stub
                super.clicked(event, x, y);
                getMain().getGameScreen().showMain();

            }
        });
        this.addActor(play);

        /**创建成就按钮*/
//		hid = new ImageButton(new TextureRegionDrawable(getMain().getAtlas().findRegion(R.emun.IMAGE_BUTTON_ACHI_UP)),
//				new TextureRegionDrawable(getMain().getAtlas().findRegion(R.emun.IMAGE_BUTTON_ACHI_DOWN)));
//		hid.setX(getWidth()*60/100);
//		hid.setY((getHeight()-getMain().getHeibian()/2)*23/100);
//		this.addActor(hid);

//        /**创建游戏结束按钮*/
//        over = new ImageButton(new TextureRegionDrawable(getMain().getAtlas().findRegion(R.emun.IMAGE_BUTTON_OVER_QUIT_UP)),
//                new TextureRegionDrawable(getMain().getAtlas().findRegion(R.emun.IMAGE_BUTTON_OVER_QUIT_UP)));
//        over.setX(getWidth()*60/100);
//        over.setY((getHeight()-getMain().getHeibian()/2)*5/100);
//        this.addActor(over);

        /**创建关于我按钮*/
//        TextureRegion up = new TextureRegion(getMain().getAsset().get(R.emun.IMAGE_MORE_UP,Texture.class));
//        TextureRegion down = new TextureRegion(getMain().getAsset().get(R.emun.IMAGE_MORE_DOWN,Texture.class));
//        move = new ImageButton(new TextureRegionDrawable(up),
//                new TextureRegionDrawable(new TextureRegion(down)));
////		move.setScale(1021f);
//        float widht = getWidth()*0.3f;
//        System.out.println(widht);
//        move.setSize(150, 150);
//        move.setX(0);
//        move.setY((getHeight())-(move.getHeight()+getMain().getHeibian()/3));
//        this.addActor(move);
    }

}

