package com.lgm;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
*@author:李罡毛
*@date:2021/2/5 21:12
*/
public class ImageTest {
    @Test
    public void bufferedImageTest(){
        try {
            assertNotNull(ImageIO.read(
                        new File("D:\\IdeaProjects\\WarOfTank\\src\\images\\1.gif")));
            assertNotNull(ImageIO.read(this.getClass().getClassLoader().getResource("images\\1.gif")));
            BufferedImage bufferedImage = ImageIO.read(ImageTest.class.getClassLoader().getResource("images\\0.gif"));
            assertNotNull(bufferedImage);
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
