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
    /**资源缓存类*/
    private AssetManager asset;
    /**解析组合图类*/
    private TextureAtlas atlas,mapatlas;
    /**游戏窗口大小*/
    private float wordWidth;
    private float wordHeight;
    /**场景*/
    private GameScreen gameScreen;
    /**黑边*/
    private float heibian ;
    private FPSDebug fpsDebug;

    private BitmapFont font;
    private Music music;

    private Sound jump;



    public void create() {//初始化游戏
        // TODO Auto-generated method stub
        asset = new AssetManager();//创建资源缓存类
        /**加载要缓存数据*/
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

        asset.finishLoading();//进行缓存
        font= asset.get(R.Font.FONT, BitmapFont.class);
        /**返回组合图形类*/
        atlas = asset.get(R.ATLAS_BUTTON, TextureAtlas.class);
        mapatlas = asset.get(R.BackGround.ATLAS_XIAOTU,TextureAtlas.class);
        /**返回背景图片*/
        Texture t = asset.get(R.emun.IMAGE_BRAGKGROUND,Texture.class);
        /**获取背景图片宽度，并计算高度*/
        wordWidth = t.getWidth();
//		wordWidth = 480;
        wordHeight =
                wordWidth* Gdx.graphics.getHeight()/Gdx.graphics.getWidth();
        wordHeight = t.getHeight();
        /**计算黑白宽度*/
        heibian = (wordHeight - t.getHeight()) /2.0f;
        /**创建场景*/
        gameScreen = new GameScreen(this);
        /**设置场景*/
        setScreen(gameScreen);
        //设置背景音乐
        // 加载背景音乐, 创建 Music 实例
        music = Gdx.audio.newMusic(Gdx.files.internal("mymusic.wav"));

        // 背景音乐设置循环播放
        music.setLooping(true);

        // 设置音量, 值范围 0.0 ~ 1.0
        music.setVolume(1.2F);
        // int jumpSound1Id = sound.load(context, R.raw.jump_sound_1, 1);

        music.play();

        // 加载音效, 创建 Sound 实例

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
        //设置bgm
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

        // 黑色清屏
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.justTouched()) {
            // 点击屏幕一次, 播放音效一次
            jump.play();
        }
        // 父类渲染场景
        super.render();
//		gameScreen.render(0);
//		 判断是否需要渲染帧率
        fpsDebug.render();

    }

    private class FPSDebug implements Disposable {

        private SpriteBatch batch;

        private BitmapFont  fpsBitmapFont;
        /** 文本高度占屏幕高度的比例 */
        private static final float OCCUPY_HEIGHT_RATIO = 14.0f/480.0f;
        /** 显示的文本偏移右下角的X轴和Y轴比例(相对于字体高度的比例) */
        private static final float DISPLAY_ORIGIN_OFFSET_RATIO = 0.5f;

        private float x,y;

        public void init(BitmapFont fpsBitmapFont,int fontRawPixSize) {
            this.batch = new SpriteBatch();
            this.fpsBitmapFont = fpsBitmapFont;
            // 计算帧率文本显示位置（为了兼容所有不同尺寸的屏幕）
            float height = Gdx.graphics.getHeight();
            float scale = (height * OCCUPY_HEIGHT_RATIO)/(float)fontRawPixSize;
            this.fpsBitmapFont.getData().setScale(scale);
            float scaledFontSize = fontRawPixSize * scale;
            float offset = scaledFontSize * DISPLAY_ORIGIN_OFFSET_RATIO;
            x = scaledFontSize - offset;
            y = scaledFontSize * 1.85f - offset;
        }

        public void render(){
            // 绘制帧率
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
            //设置bgm
            if (music != null) {
                music.dispose();
            }

            if (jump != null) {
                jump.dispose();
            }
            // fpsBitmapFont 由资源管理器负责管理, 这里不需要销毁
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
