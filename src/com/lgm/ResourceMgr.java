package com.lgm;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author:李罡毛
 * @date:2021/2/6 13:28
 */
public class ResourceMgr {
    /**
     * 私有化无参构造方法，单粒模式
     */
    private ResourceMgr(){}

    static BufferedImage tankL,tankR,tankU,tankD;//友军
    static BufferedImage badTankL,badTankR,badTankU,badTankD;//敌军
    static BufferedImage bulletL,bulletR,bulletU,bulletD;
    static BufferedImage[] explodeImages = new BufferedImage[16];
    static {
        try {
            tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResource("images/GoodTank1.png"));
            tankR = ImageUtil.rotateImage(tankU,90);
            tankL = ImageUtil.rotateImage(tankU,-90);
            tankD = ImageUtil.rotateImage(tankU,-180);
            badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResource("images/BadTank1.png"));
            badTankR = ImageUtil.rotateImage(badTankU,90);
            badTankL = ImageUtil.rotateImage(badTankU,-90);
            badTankD = ImageUtil.rotateImage(badTankU,-180);
            bulletL = ImageIO.read(ResourceMgr.class.getClassLoader().getResource("images/bulletL.gif"));
            bulletR = ImageUtil.rotateImage(bulletL,-180);
            bulletU = ImageUtil.rotateImage(bulletL,90);
            bulletD = ImageUtil.rotateImage(bulletL,-90);
            for (int i = 0; i < explodeImages.length; i++) {
                explodeImages[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResource("" +
                        "images/e"+(i+1)+".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
