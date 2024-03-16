package Buttons;

import javafx.scene.control.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests to check alerts Class.
 *
 * @author Fabian Kerner
 */

public class alertsTest {

    /**
     * Create alerts to be tested.
     */
    private Alert alert1;
    public Alert blanksalerttest() {
        alert1 = new alerts().blanksalert();
        return alert1;
    }

    private Alert alert2;
    public Alert existalerttest() {
        alert2 = new alerts().existalert();
        return alert2;
    }

    private Alert alert3;
    public Alert furtheractionalerttest() {
        alert3 = new alerts().existalert();
        return alert3;
    }

    /**
     * Check if alerts give out correct Text.
     */

    @Test
    public void testBlanksalert() {
        try {
            Assert.assertEquals(blanksalerttest().getContentText(), "Please fill in the blanks!");
            System.out.println("testBlanksalert() test passed");
        } catch (Throwable e) {
            System.out.println("testBlanksalert() test failed");
        }

    }


    @Test
    public void testExistalert() {
        try {
            Assert.assertEquals(existalerttest().getContentText(), "Model already exists!");
            System.out.println("Existalert() test passed");
        } catch (Throwable e) {
            System.out.println("testExistalert() test failed");
        }
    }

    @Test
    public void testFurtheractionalert() {
        try {
            Assert.assertEquals(furtheractionalerttest().getContentText(), "Successfully created directory and moved files!\n" + "Create another model?");
            System.out.println("testFurtheractionalert() test passed");
        } catch (Throwable e) {
            System.out.println("testFurtheractionalert() test failed");
        }
    }


}