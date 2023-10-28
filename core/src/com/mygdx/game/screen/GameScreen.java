package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.main.GameMian;
import com.mygdx.game.stage.MainStage;
import com.mygdx.game.stage.MenuStage;
import com.mygdx.game.stage.OverStage;

//Game scenes, responsible for switching between different stages
public class GameScreen  extends ScreenAdapter {
    //Game Menu Stage
    private MenuStage menu;

    private GameMian mian;

    private MainStage main;

    private OverStage over;


    public GameScreen(GameMian mian) {
        this.mian = mian;
        init();
    }
   //Initialize scene
    public void init() {

       //menu
        menu = new MenuStage(mian, new StretchViewport(mian.getWordWidth(),mian.getWordHeight()));
        menu.setView(true);
        //main stage
        main = new MainStage(mian, new StretchViewport(mian.getWordWidth(),mian.getWordHeight()));
        main.setView(false);//At the beginning, display the menu without displaying the main scene

        over = new OverStage(mian, new StretchViewport(mian.getWordWidth(),mian.getWordHeight()));
        over.setView(false);//End interface not displayed
        //Set up touch screen monitoring
        Gdx.input.setInputProcessor(menu);
    }

    @Override
    public void render(float delta) {//Update per frame
        // TODO Auto-generated method stub
        super.render(delta);

        if(menu.isView()) {
            menu.act(delta);
            menu.draw();
        }

        if(main.isView()) {
            main.act(delta);
            main.draw();
        }

        if(over.isView()) {
            over.act(delta);
            over.draw();
        }
    }

    public void showMain() {
        menu.setView(false);
        main.disport();
        main.addActors();
        main.setView(true);
        Gdx.input.setInputProcessor(main);
    }

    @Override
    public void dispose() {//Release resources
        // TODO Auto-generated method stub
        super.dispose();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(menu != null) {
            menu.dispose();
        }

        if(main != null) {
            main.dispose();
        }

        if(over != null) {
            over.dispose();
        }
    }


    public void showMenu() {
        menu.setView(true);
        Gdx.input.setInputProcessor(menu);
    }

    public void showOver(int min) {
        over.setMin(min);
        over.setView(true);
        Gdx.input.setInputProcessor(over);
    }

}
