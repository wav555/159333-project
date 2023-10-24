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
   // private ImageButton achi;
    private ImageButton quit;
    private ImageButton tryz;
  //  private ImageButton submit;
  private ArrayList<Integer> historyScores; // 历史分数集合

    private Label ta;

    public OverStage(GameMian main, Viewport view) {
        super(main, view);
        // TODO Auto-generated constructor stub
        init( main);
        historyScores = new ArrayList<Integer>();
    }

    private void init(GameMian main) {
        bark = new BackgroundActor(new TextureRegion(main.getAsset().get(R.Over.background	, Texture.class)));
//		bark.setSize(getWidth(), getHeight());
        bark.setCenter(getWidth()/2, getHeight()/2);
        this.addActor(bark);
//
//        submit = new ImageButton(new TextureRegionDrawable(main.getAtlas().findRegion(R.Over.OVER_TRY_UP)),
//                new TextureRegionDrawable(main.getAtlas().findRegion(R.Over.OVER_TRY_DOWN)));
//        submit.setY(75);
//        submit.setX(10);
        tryz = new ImageButton(new TextureRegionDrawable(main.getAtlas().findRegion(R.Over.OVER_TRY_UP)),
                new TextureRegionDrawable(main.getAtlas().findRegion(R.Over.OVER_TRY_UP)));
        tryz.setX(550);
        tryz.setY(75);

//        achi = new ImageButton(new TextureRegionDrawable(main.getAtlas().findRegion(R.Over.OVER_ACHI_UP)),
//                new TextureRegionDrawable(main.getAtlas().findRegion(R.Over.OVER_ACHI_DOWN)));
//        achi.setX(tryz.getWidth()+10+tryz.getX());
        tryz.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO Auto-generated method stub
                super.clicked(event, x, y);
                OverStage.this.setView(false);
                getMain().getGameScreen().showMain();

            }
        });
        //achi.setY(75);
        //this.addActor(achi);

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


       // this.addActor(submit);

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
            // 判断本次得分是否超过历史最高分数
            int highestScore = historyScores.get(0);
            if (min > highestScore) {
                ta.setText(ta.getText() + "New Highest Score!\n");
            }
            // 添加本次分数到历史分数集合
            historyScores.add(min);
        }else{
            // 添加本次分数到历史分数集合
            historyScores.add(min);
        }


// 对历史分数进行排序，以便获取最高分数
        Collections.sort(historyScores, Collections.reverseOrder());

// 显示历史分数
        String historyScoresText = "History High Scores:\n";
        for (int i = 0; i < Math.min(3, historyScores.size()); i++) {
            historyScoresText += historyScores.get(i) + " meters  ";
        }
        ta.setText(ta.getText()  + historyScoresText);


    }
}
