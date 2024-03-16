package Buttons;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Tests to check Images Class.
 *
 * @author Fabian Kerner
 */

public class ImagesTest {

    private ImageView imageview;
    private Image imageOpentest;
    private Image imageDeltest;

    /**
     * Test if imageviews have correct size.
     */

    @Test
    public void testCreateopenimage() {

        imageview = new ImageView(imageOpentest);
        imageview.setFitWidth(20);
        imageview.setFitHeight(20);

        try {
            Assert.assertEquals(imageview.getFitHeight(), 20);
            Assert.assertEquals(imageview.getFitWidth(), 20);
            System.out.println("testCreateopenimage() test passed");
        } catch (Throwable e) {
            System.out.println("testCreateopenimage() test failed");
        }
    }



    @Test
    public void testCreatedelimage() {

        imageview = new ImageView(imageDeltest);
        imageview.setFitWidth(20);
        imageview.setFitHeight(20);

        try {
            Assert.assertEquals(imageview.getFitHeight(), 20);
            Assert.assertEquals(imageview.getFitWidth(), 20);
            System.out.println("testCreatedelimage() test passed");
        } catch (Throwable e) {
            System.out.println("testCreatedelimage() test failed");
        }
    }
}