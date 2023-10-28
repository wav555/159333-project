package com.mygdx.game.stage.IStage;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.main.GameMian;

public class IStage extends Stage {

    private boolean isView;

    private GameMian main;//Game Entry

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
