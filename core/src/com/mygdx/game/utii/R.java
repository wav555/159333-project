package com.mygdx.game.utii;

public class R {
    /**纹理视图*/
    public static final String ATLAS_BUTTON = "image/button/menu.atlas";
    public static float BEI_SU = 1.5f;
    /**菜单所使用的图片*/
    public interface emun{
        /**成就按钮*/
//		public static final String IMAGE_BUTTON_ACHI_DOWN = "achi_down";
//		public static final String IMAGE_BUTTON_ACHI_UP = "achi_up";
        /**游戏结束按钮*/
       // public static final String IMAGE_BUTTON_OVER_QUIT_DOWN = "over_quit_down";
        public static final String IMAGE_BUTTON_OVER_QUIT_UP = "over_quit_up";
        /**游戏开始按钮*/
      //  public static final String IMAGE_BUTTON_PLAY_DOWN = "play_down";
        public static final String IMAGE_BUTTON_PLAY_UP = "play_up";

        /**大图路径*/
        static final String IMAGE_PTAH = "image/emun/";
        /**菜单背景**/
        public static final String IMAGE_BRAGKGROUND = IMAGE_PTAH+"initbg.jpg";
        /**关于我们*/
       // public static final String IMAGE_MORE_UP = "image/emun/more1.png";
        //public static final String IMAGE_MORE_DOWN = IMAGE_PTAH+"more2.png";

    }

    public interface BackGround {
        static final String IMAGE_PTAH = "image/background/";

        /**背景**/
        public static final String IMAGE_BRAGKGROUND = IMAGE_PTAH+"bg1.jpg";
        public static final String IMAGE_BRAGKGROUND1 = IMAGE_PTAH+"bg2.png";
        public static final String IMAGE_BRAGKGROUND2 = IMAGE_PTAH+"bg3.png";
        public static final String IMAGE_DAO = IMAGE_PTAH+"dao.gif";
        public static final String IMAGE_DART_FOOD  = IMAGE_PTAH+"dartfood.png";

        public static final String ATLAS_XIAOTU = IMAGE_PTAH+"xiaotu.atlas";
        public static final String IMAGE_BAM_MIND = "bam_mid";
        public static final String IMAGE_BAM_LEFT= "bam_left";
        public static final String IMAGE_BAM_REGHT = "bam_right";
        public static final String IMAGE_BAM_UNDER = "bam_under";
        public static final String IMAGE_FOODBLOOD =  "foodblood";
        public static final String IMAGE_DARTSHOW  =  "dartshow";
        public static final String IMAGE_BLOOD ="blood";

    }


    public interface Physical{
        public static final float ACTOR_UP_SEED = 200f;
        public static final float INITIAL_SEED = -100f;
        public static float MULTIPLE = 1f;


        public static final float SEED_X = -100f;
        public static final float WARR_SEED_X=-50f;
        public static final float CROW_SEED_X = -100f;
        public static final float NIN_UP_SEED = 300f;
        public static final float NIN_DOWN_SEED = -300f;
        public static final float DAR_SEED_X = 200f;

    }

    public interface Actor{
        static final String IMAGE_PTAH = "image/actor/";

        public static final String IMAGE_ENEMY = IMAGE_PTAH+"enemy.gif";
        public static final String IMAGE_CROW = IMAGE_PTAH+"crow.png";
        public static final String IMAGE_NIN_DAR =IMAGE_PTAH+ "dartman.png";
        public static final String IMAGE_NIN_CHA = IMAGE_PTAH+"changetodart.png";
        public static final String IMAGE_NIN_BULL = IMAGE_PTAH+"bulletman.png";
        public static final String IMAGE_NIN_BUTT = IMAGE_PTAH+ "dartbtn.png";
        public static final String IMAGE_WARR_DEAD=IMAGE_PTAH+"antdead.png";
        public static final String IMAGE_WARR_HUR= IMAGE_PTAH+"hurtblood.png";
    }

    public interface Font{
        public final static String FONT_PAHT = "font/";

        public final static String FONT = FONT_PAHT+"font.fnt";

    }


    public interface Over{
        public static final String background = "image/button/b_page.jpg";

      //  public static final String OVER_ACHI_DOWN = "over_achi_down";
      //  public static final String OVER_ACHI_UP ="over_achi_up";

       // public static final String OVER_QUIT_DOWN = "over_quit_down";
        public static final String OVER_QUIT_UP = "over_quit_up";

       // public static final String OVER_TRY_DOWN = "over_try_down";
        public static final String OVER_TRY_UP = "over_try_up";

//        public static final String OVER_SUB_DOWN = "over_submit_down";
//        public static final String OVER_SUB_UP = "over_submit_up";
    }
}
