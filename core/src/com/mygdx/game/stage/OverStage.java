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
import java.util.Collections;

public class OverStage extends IStage {
    private BackgroundActor bark;
    private ImageButton quit;
    private ImageButton tryz;
    private ArrayList<Integer> historyScores; // Historical score collection

    private Label ta;

    public OverStage(GameMian main, Viewport view) {
        super(main, view);
        // TODO Auto-generated constructor stub
        init( main);
        historyScores = new ArrayList<Integer>();
    }

    private void init(GameMian main) {
        bark = new BackgroundActor(new TextureRegion(main.getAsset().get(R.Over.background	, Texture.class)));
        bark.setCenter(getWidth()/2, getHeight()/2);
        this.addActor(bark);

        tryz = new ImageButton(new TextureRegionDrawable(main.getAtlas().findRegion(R.Over.OVER_TRY_UP)),
                new TextureRegionDrawable(main.getAtlas().findRegion(R.Over.OVER_TRY_UP)));
        tryz.setX(550);
        tryz.setY(75);

        tryz.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO Auto-generated method stub
                super.clicked(event, x, y);
                OverStage.this.setView(false);
                getMain().getGameScreen().showMain();

            }
        });

        quit = new ImageButton(new TextureRegionDrawable(main.getAtlas().findRegion(R.Over.OVER_QUIT_UP)),
                new TextureRegionDrawable(main.getAtlas().findRegion(R.Over.OVER_QUIT_UP)));
        quit.setY(getHeight()-quit.getHeight()-main.getHeibian()-50);
        quit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO Auto-generated method stub
                super.clicked(event, x, y);
                OverStage.this.setView(false);
                getMain().getGameScreen().showMenu();

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

    public void setMin(int min) {
        ta.setText("Score: " + min + " meters\n");
        if(!historyScores.isEmpty()){
            // Determine whether the score exceeds the highest
            int highestScore = historyScores.get(0);
            if (min > highestScore) {
                ta.setText(ta.getText() + "New Highest Score!\n");
            }
            // Add this score to the historyScores collection
            historyScores.add(min);
        }else{
            // Add this score to the historyScores collection
            historyScores.add(min);
            ta.setText(ta.getText() + "New Highest Score!\n");
        }
        // Sort historyScores to get the highest score
        Collections.sort(historyScores, Collections.reverseOrder());
        // Displays historyScores
        String historyScoresText = "History 3 Highest Scores:\n";
        for (int i = 0; i < Math.min(3, historyScores.size()); i++) {
            historyScoresText += historyScores.get(i) + " meters  ";
        }
        ta.setText(ta.getText()  + historyScoresText);
    }

    public ArrayList<Integer> getHistoryScores(){
        return historyScores;
    }
}
