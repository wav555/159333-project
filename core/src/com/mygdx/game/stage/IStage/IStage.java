package com.mygdx.game.stage.IStage;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.main.GameMian;

public class IStage extends Stage {

    /**是否显示*/
    private boolean isView;
    /**游戏入口类*/
    private GameMian main;

    public IStage(GameMian main, Viewport view) {
        super(view);
        this.main = main;
    }

    public boolean isView() {
        return isView;
    }

    public void setView(boolean isView) {
        this.isView = isView;
    }

    public GameMian getMain() {
        return main;
    }

    public void setMain(GameMian main) {
        this.main = main;
    }



}
