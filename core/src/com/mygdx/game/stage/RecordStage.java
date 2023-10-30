package com.mygdx.game.stage;

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

public class RecordStage extends IStage {

    private BackgroundActor back;

    private ImageButton quit;
    private ImageButton tryz;

    private Label ta;

    private int money=0;

    public RecordStage(GameMian main, Viewport view) {
        super(main, view);
        init(main);
    }

    private void init(GameMian main){
        back = new BackgroundActor(new TextureRegion(main.getAsset().get(R.Record.background	, Texture .class)));
        back.setCenter(getWidth()/2, getHeight()/2);
        this.addActor(back);

        quit = new ImageButton(new TextureRegionDrawable(main.getAtlas().findRegion(R.Over.OVER_QUIT_UP)),
                new TextureRegionDrawable(main.getAtlas().findRegion(R.Over.OVER_QUIT_UP)));
        quit.setY(getHeight()-quit.getHeight()-main.getHeibian()-50);
        quit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO Auto-generated method stub
                super.clicked(event, x, y);
                RecordStage.this.setView(false);
                getMain().getGameScreen().showMenu();

            }
        });

        tryz = new ImageButton(new TextureRegionDrawable(main.getAtlas().findRegion(R.Record.IMAGE_BUTTON_STAR)),
                new TextureRegionDrawable(main.getAtlas().findRegion(R.Record.IMAGE_BUTTON_STAR)));
        tryz.setX(550);
        tryz.setY(75);

        tryz.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO Auto-generated method stub
                super.clicked(event, x, y);
                RecordStage.this.setView(false);
                getMain().getGameScreen().showMain();

            }
        });

        this.addActor(quit);

        this.addActor(tryz);


        Label.LabelStyle style = new Label.LabelStyle();
        style.font = main.getFont();
        ta  = new Label("",style);
        ta.setPosition(130, getHeight()-220);
        ta.setColor(Color.BLACK);
        ta.setFontScale(1f);
        this.addActor(ta);
    }

    public void showRecord(OverStage over){
        String historyScoresText = "History 3 Highest Scores:\n";

        ArrayList<Integer> scores=over.getHistoryScores();

        if(!scores.isEmpty()){
            for (int i = 0; i < Math.min(3, scores.size()); i++) {
                historyScoresText += scores.get(i) + " meters  ";
            }
        }else{
            historyScoresText+="No record. Please start a new game.";
        }
        ta.setText(historyScoresText+"\n\n");
        money=money+over.getMoneyCount();
        ta.setText(ta.getText()+"Money Count: "+money);
    }

}
