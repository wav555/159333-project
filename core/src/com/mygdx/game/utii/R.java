package com.mygdx.game.utii;

public class R {
    /**texture view*/
    public static final String ATLAS_BUTTON = "image/button/menu.atlas";
    public static float BEI_SU = 1.5f;
    /**Images used for menus*/
    public interface emun{
        /**game over button*/
        //public static final String IMAGE_BUTTON_OVER_QUIT_DOWN = "over_quit_down";
        public static final String IMAGE_BUTTON_OVER_QUIT_UP = "over_quit_up";

        public static final String IMAGE_BUTTON_PLAY_EXIT = "play_exit";

        public static final String IMAGE_BUTTON_PLAY_HELPER = "play_helper";
        public static final String IMAGE_BUTTON_PLAY_RECORD = "play_record";

        public static final String IMAGE_BUTTON_EXIT = "exit";

        /**start game button*/
       //public static final String IMAGE_BUTTON_PLAY_DOWN = "play_down";
        public static final String IMAGE_BUTTON_PLAY_UP = "play_up";
        public static final String IMAGE_BUTTON_MONEY = "image/button/money.png";


        /**Image*/
        static final String IMAGE_PTAH = "image/emun/";
        /**menu background**/
        public static final String IMAGE_BRAGKGROUND = IMAGE_PTAH+"initbg.jpg";

    }

    public interface BackGround {
        static final String IMAGE_PTAH = "image/background/";

        /**background**/
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
    public interface Record{
        public static final String background = "image/button/b_board.jpg";
        public static final String IMAGE_BUTTON_STAR = "start";
    }
}
