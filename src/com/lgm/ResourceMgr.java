package com.lgm;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author:李罡毛
 * @date:2021/2/6 13:28
 */
public class ResourceMgr {
    static BufferedImage tankL,tankR,tankU,tankD;
    static BufferedImage bulletL,bulletR,bulletU,bulletD;
    static {
        try {
            tankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResource("images/tankL.gif"));
            tankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResource("images/tankR.gif"));
            tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResource("images/tankU.gif"));
            tankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResource("images/tankD.gif"));
            bulletL = ImageIO.read(ResourceMgr.class.getClassLoader().getResource("images/bulletL.gif"));
            bulletR = ImageIO.read(ResourceMgr.class.getClassLoader().getResource("images/bulletR.gif"));
            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResource("images/bulletU.gif"));
            bulletD = ImageIO.read(ResourceMgr.class.getClassLoader().getResource("images/bulletD.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
