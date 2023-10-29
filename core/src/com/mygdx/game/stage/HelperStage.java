package com.mygdx.game.stage;

import static com.jetbrains.internal.JBRApi.init;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.actor.BackgroundActor;
import com.mygdx.game.main.GameMian;
import com.mygdx.game.stage.IStage.IStage;
import com.mygdx.game.utii.R;

import java.util.ArrayList;

public class HelperStage extends IStage {
    private BackgroundActor bark;
    private ImageButton quit;
    private Label ta;
    public HelperStage(GameMian main, Viewport view) {
        super(main, view);
        // TODO Auto-generated constructor stub
        init( main);
    }

    private void init(GameMian main) {
        bark = new BackgroundActor(new TextureRegion(main.getAsset().get(R.Record.background	, Texture.class)));
        bark.setCenter(getWidth()/2, getHeight()/2);
        this.addActor(bark);

        quit = new ImageButton(new TextureRegionDrawable(main.getAtlas().findRegion(R.Over.OVER_QUIT_UP)),
                new TextureRegionDrawable(main.getAtlas().findRegion(R.Over.OVER_QUIT_UP)));
        quit.setY(getHeight()-quit.getHeight()-main.getHeibian()-50);
        quit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO Auto-generated method stub
                super.clicked(event, x, y);
                HelperStage.this.setView(false);
                getMain().getGameScreen().showMenu();

            }
        });
        this.addActor(quit);

        Label.LabelStyle style = new Label.LabelStyle();
        style.font = main.getFont();
        ta  = new Label("",style);
        ta.setText("\nAuthor: Xinyi Zhang\n             Lingyi Li\n             Jingyi Liu\n             Qingyu Zhang\nContact: 1467821297@qq.com");
        ta.setPosition(130, getHeight()-220);
        ta.setColor(Color.BLACK);
        ta.setFontScale(1f);
        this.addActor(ta);
    }

}
