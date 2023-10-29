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
    /**start playing*/
    private ImageButton play;
    /**exit the game*/
    private ImageButton exit;
    //help
    private ImageButton helper;

    private ImageButton record;
    private ImageActor breakGround;

    /**Constructor*/
    public MenuStage(GameMian main, Viewport view) {
        super(main, view);
        // TODO Auto-generated constructor stub
        init();
    }
    //Initialization method
    public void init() {

        /**background**/
        TextureRegion r = new TextureRegion(getMain().getAsset().get(R.emun.IMAGE_BRAGKGROUND, Texture.class));
        breakGround = new ImageActor(r);
        breakGround.setCenter(getWidth()/2, getHeight()/2);
        this.addActor(breakGround);
        /**start playing button*/
        play = new ImageButton(new TextureRegionDrawable(getMain().getAtlas().findRegion(R.emun.IMAGE_BUTTON_PLAY_UP)),
                new TextureRegionDrawable(getMain().getAtlas().findRegion(R.emun.IMAGE_BUTTON_PLAY_UP)));
        play.setX(getWidth()*63/130);
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

        /**exit button*/
		exit = new ImageButton(new TextureRegionDrawable(getMain().getAtlas().findRegion(R.emun.IMAGE_BUTTON_PLAY_EXIT)),
                new TextureRegionDrawable(getMain().getAtlas().findRegion(R.emun.IMAGE_BUTTON_PLAY_EXIT)));
        exit.setX(getWidth()*12/13);
        exit.setY((getHeight()-getMain().getHeibian()/2)/100);
        exit.addListener(new ClickListener(){
            public void clicked(InputEvent event,float x,float y){
                super.clicked(event,x,y);
                System.exit(0);
            }
        });
        this.addActor(exit);

        helper = new ImageButton(new TextureRegionDrawable(getMain().getAtlas().findRegion(R.emun.IMAGE_BUTTON_PLAY_HELPER)),
                new TextureRegionDrawable(getMain().getAtlas().findRegion(R.emun.IMAGE_BUTTON_PLAY_HELPER)));
        helper.setX(getWidth()*119/130);
        helper.setY((getHeight()-getMain().getHeibian()/2)*5/6);
        helper.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO Auto-generated method stub
                super.clicked(event, x, y);
                getMain().getGameScreen().showHelper();

            }
        });
        this.addActor(helper);

        record = new ImageButton(new TextureRegionDrawable(getMain().getAtlas().findRegion(R.emun.IMAGE_BUTTON_PLAY_RECORD)),
                new TextureRegionDrawable(getMain().getAtlas().findRegion(R.emun.IMAGE_BUTTON_PLAY_RECORD)));
        record.setX(getWidth()/25);
        record.setY((getHeight()-getMain().getHeibian()/2)*11/15);
        record.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO Auto-generated method stub
                super.clicked(event, x, y);
                getMain().getGameScreen().showRecord1();

            }
        });
        this.addActor(record);
    }

}

