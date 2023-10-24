package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.main.GameMian;
import com.mygdx.game.stage.MainStage;
import com.mygdx.game.stage.MenuStage;
import com.mygdx.game.stage.OverStage;

/**游戏场景类，负责不同舞台的切换*/
public class GameScreen  extends ScreenAdapter {
    /**游戏菜单舞台*/
    private MenuStage menu;

    private GameMian mian;
    /**游戏主舞台*/
    private MainStage main;

    private OverStage over;

    /**初始化场景*/
    public GameScreen(GameMian mian) {
        this.mian = mian;
        init();
    }
    /**初始化方法*/
    public void init() {

        /**创建菜单*/
        menu = new MenuStage(mian, new StretchViewport(mian.getWordWidth(),mian.getWordHeight()));
        menu.setView(true);//设置是否显示
        /**创建主场景*/
        main = new MainStage(mian, new StretchViewport(mian.getWordWidth(),mian.getWordHeight()));
        main.setView(false);//设置是否显示 开始的时候显示菜单不显示主场景

        over = new OverStage(mian, new StretchViewport(mian.getWordWidth(),mian.getWordHeight()));
        over.setView(false);//结束界面不显示
        /**设置触屏监控*/
        Gdx.input.setInputProcessor(menu);
    }

    @Override
    public void render(float delta) {//每一帧的更新
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
        main.addActors();//添加小人
        main.setView(true);
        Gdx.input.setInputProcessor(main);
    }

    @Override
    public void dispose() {//释放资源
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
