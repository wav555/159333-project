package com.mygdx.game.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.screen.GameScreen;
import com.mygdx.game.utii.R;

public class GameMian extends Game {
    //Resource Cache Class
    private AssetManager asset;
    //Analytic Composite Graph Class
    private TextureAtlas atlas,mapatlas;
    //Game Window Size
    private float wordWidth;
    private float wordHeight;
    //Scenarios
    private GameScreen gameScreen;
    //Black edge
    private float heibian ;
    private FPSDebug fpsDebug;

    private BitmapFont font;
    private Music music;

    private Sound jump;



    public void create() {//Initialize Game
        // TODO Auto-generated method stub
        asset = new AssetManager();//Create Resource Cache Class
       //Load data to cache
        asset.load("image/button/menu.png", Texture.class);
        asset.load("image/button/menu.atlas",TextureAtlas.class);
        asset.load(R.emun.IMAGE_BRAGKGROUND,Texture.class);
      //  asset.load(R.emun.IMAGE_MORE_DOWN,Texture.class);
       // asset.load(R.emun.IMAGE_MORE_UP,Texture.class);
        asset.load(R.BackGround.IMAGE_BRAGKGROUND, Texture.class);
        asset.load(R.BackGround.IMAGE_BRAGKGROUND1, Texture.class);
        asset.load(R.BackGround.IMAGE_BRAGKGROUND2, Texture.class);
        asset.load(R.BackGround.IMAGE_DAO, Texture.class);
        asset.load(R.BackGround.IMAGE_DART_FOOD, Texture.class);
        asset.load(R.BackGround.ATLAS_XIAOTU,TextureAtlas.class);

        asset.load(R.Actor.IMAGE_NIN_CHA,Texture.class);
        asset.load(R.Actor.IMAGE_NIN_DAR,Texture.class);
        asset.load(R.Actor.IMAGE_NIN_BULL,Texture.class);
        asset.load(R.Actor.IMAGE_NIN_BUTT,Texture.class);
        asset.load(R.Actor.IMAGE_WARR_DEAD, Texture.class);
        asset.load(R.Actor.IMAGE_WARR_HUR, Texture.class);
        asset.load(R.Actor.IMAGE_ENEMY,Texture.class);
        asset.load(R.Actor.IMAGE_CROW, Texture.class);

        asset.load(R.Over.background,Texture.class);


        asset.load(R.Font.FONT, BitmapFont.class);

        asset.finishLoading();//Caching
        font= asset.get(R.Font.FONT, BitmapFont.class);
        //Return to Composite Graph Class
        atlas = asset.get(R.ATLAS_BUTTON, TextureAtlas.class);
        mapatlas = asset.get(R.BackGround.ATLAS_XIAOTU,TextureAtlas.class);
        //Return to background image
        Texture t = asset.get(R.emun.IMAGE_BRAGKGROUND,Texture.class);
        //Obtain the width of the background image and calculate the height
        wordWidth = t.getWidth();
//		wordWidth = 480;
        wordHeight =
                wordWidth* Gdx.graphics.getHeight()/Gdx.graphics.getWidth();
        wordHeight = t.getHeight();
        heibian = (wordHeight - t.getHeight()) /2.0f;//Calculate black and white width
        gameScreen = new GameScreen(this); //Creating Scenes
        setScreen(gameScreen);//Set up the scene

        //Set background music
        // Load background music and create a Music instance
        music = Gdx.audio.newMusic(Gdx.files.internal("mymusic.wav"));

        // Background music setting loop playback
        music.setLooping(true);

        // Set volume, value range 0.0~1.0
        music.setVolume(1.2F);
        // int jumpSound1Id = sound.load(context, R.raw.jump_sound_1, 1);
        music.play();

        // Load sound effects and create a Sound instance
        jump = Gdx.audio.newSound(Gdx.files.internal("jump.mp3"));

        fpsDebug = new FPSDebug();
        fpsDebug.init(font, 25);
    }


    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        super.dispose();
        if(atlas != null) {
            atlas.dispose();
        }
        if(mapatlas !=null) {
            mapatlas.dispose();
        }
        if(asset !=null) {
            asset.dispose();
        }
        //bgm
        if (music != null) {
            music.dispose();
        }

        if (jump != null) {
            jump.dispose();
        }
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub

        // Black Clear Screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.justTouched()) {
            // Click on the screen once to play the sound effect once
            jump.play();
        }
        // Parent class rendering scene
        super.render();
//		gameScreen.render(0);
//		Determine if rendering frame rate is required
        fpsDebug.render();

    }

    private class FPSDebug implements Disposable {

        private SpriteBatch batch;

        private BitmapFont  fpsBitmapFont;
        //The proportion of text height to screen height
        private static final float OCCUPY_HEIGHT_RATIO = 14.0f/480.0f;
        //The displayed text is offset by the X-axis and Y-axis proportions in the lower right corner (relative to the font height)
        private static final float DISPLAY_ORIGIN_OFFSET_RATIO = 0.5f;

        private float x,y;

        public void init(BitmapFont fpsBitmapFont,int fontRawPixSize) {
            this.batch = new SpriteBatch();
            this.fpsBitmapFont = fpsBitmapFont;
            // Calculate frame rate text display position (for compatibility with all screens of different sizes)
            float height = Gdx.graphics.getHeight();
            float scale = (height * OCCUPY_HEIGHT_RATIO)/(float)fontRawPixSize;
            this.fpsBitmapFont.getData().setScale(scale);
            float scaledFontSize = fontRawPixSize * scale;
            float offset = scaledFontSize * DISPLAY_ORIGIN_OFFSET_RATIO;
            x = scaledFontSize - offset;
            y = scaledFontSize * 1.85f - offset;
        }

        public void render(){
            // Draw frame rate
            batch.begin();
            fpsBitmapFont.draw(batch, "FPS:"+Gdx.graphics.getFramesPerSecond(), x, y);
            batch.end();
        }


        public void dispose() {
            // TODO Auto-generated method stub
            if(batch != null) {
                batch.dispose();
                batch = null;
            }
            //bgm
            if (music != null) {
                music.dispose();
            }

            if (jump != null) {
                jump.dispose();
            }
            // FpsBitmapFont is managed by the resource manager and does not need to be destroyed here
        }

    }



    public AssetManager getAsset() {
        return asset;
    }



    public void setAsset(AssetManager asset) {
        this.asset = asset;
    }



    public TextureAtlas getAtlas() {
        return atlas;
    }



    public void setAtlas(TextureAtlas atlas) {
        this.atlas = atlas;
    }



    public float getWordWidth() {
        return wordWidth;
    }



    public void setWordWidth(float wordWidth) {
        this.wordWidth = wordWidth;
    }



    public float getWordHeight() {
        return wordHeight;
    }



    public void setWordHeight(float wordHeight) {
        this.wordHeight = wordHeight;
    }


    public GameScreen getGameScreen() {
        return gameScreen;
    }


    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }


    public float getHeibian() {
        return heibian;
    }


    public void setHeibian(float heibian) {
        this.heibian = heibian;
    }


    public TextureAtlas getMapatlas() {
        return mapatlas;
    }


    public void setMapatlas(TextureAtlas mapatlas) {
        this.mapatlas = mapatlas;
    }


    public BitmapFont getFont() {
        return font;
    }


    public void setFont(BitmapFont font) {
        this.font = font;
    }



}
