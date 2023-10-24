package com.mygdx.game.utii;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
public class AnimationUtii {

    /**切割图 tx
     * row 行数
     * cols 列数
     * a 播放模式
     * date 播放间隔
     * r c 是否反转
     * start end 对生成的图片数值进行截取
     *
     * */
    public static Animation createAnimation(Texture tx,int row ,int cols,Animation.PlayMode a,
                                            float date,boolean r,boolean c,int start,int end) {
        int perCellWidth = tx.getWidth()/cols;	//计算行宽
        int perCellheight = tx.getHeight()/row;//计算行高
        TextureRegion[][] cell = TextureRegion.split(tx, perCellWidth, perCellheight);//对图像进行切割
        TextureRegion[] z = new TextureRegion[row*cols];	//根据行宽生成相关数组
        int index =0;										//计数
        for(int i=0;i<row;i++) {							//循环 将二维数组 转换为一维数组
            for(int j=0;j<cols;j++,index++) {
                cell[i][j].flip(r, c);				//		是否反转
                z[index] = cell[i][j];				//添加到一维数组
            }
        }
        TextureRegion[] time;						//临时数组  用于切割
        if(start != end&&end > start) {
            time = new TextureRegion[end - start];	//创建要且的数量，
            int index1= 0;
            for(int i=start;i<end;i++) {			//对其截取
                time[index1++] =z[i];
            }
            z = time;								//将截取后的数组赋值
        }
        Animation animations = new Animation(date, z);	//创建动画
        animations.setPlayMode(a);					//设置动画效果

        return animations;							//返回创建好的动画


    }

    public static Animation createAnimation(Texture tx,int row ,int cols,Animation.PlayMode a,float date,boolean r,boolean c) {
        return createAnimation(tx, row, cols, a, date, r, c,0,0);
    }

    public static Animation createAnimation(Texture tx,int row ,int cols,Animation.PlayMode a,float date) {
        return createAnimation(tx, row, cols, a, date, false, false);
    }

    public static Animation createAnimation(Texture tx,int row ,int cols) {
        return createAnimation(tx, row, cols, Animation.PlayMode.LOOP, 0.2f);
    }


    public static TextureRegion tuerx(Texture tx,int row ,int cols,int ind) {
        int perCellWidth = tx.getWidth()/cols;	//计算行宽
        int perCellheight = tx.getHeight()/row;//计算行高
        TextureRegion[][] cell = TextureRegion.split(tx, perCellWidth, perCellheight);//对图像进行切割
        TextureRegion[] z = new TextureRegion[row*cols];	//根据行宽生成相关数组
        int index =0;										//计数
        for(int i=0;i<row;i++) {							//循环 将二维数组 转换为一维数组
            for(int j=0;j<cols;j++,index++) {
                z[index] = cell[i][j];				//添加到一维数组
            }
        }
        return z[ind];
    }

    public static TextureRegion[] tuerx(Texture tx,int row ,int cols) {
        int perCellWidth = tx.getWidth()/cols;	//计算行宽
        int perCellheight = tx.getHeight()/row;//计算行高
        TextureRegion[][] cell = TextureRegion.split(tx, perCellWidth, perCellheight);//对图像进行切割
        TextureRegion[] z = new TextureRegion[row*cols];	//根据行宽生成相关数组
        int index =0;										//计数
        for(int i=0;i<row;i++) {							//循环 将二维数组 转换为一维数组
            for(int j=0;j<cols;j++,index++) {
                z[index] = cell[i][j];				//添加到一维数组
            }
        }
        return z;
    }
}
