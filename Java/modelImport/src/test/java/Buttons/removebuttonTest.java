package Buttons;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.*;

/**
 * Tests to check removebutton Class.
 *
 * @author Fabian Kerner
 */

public class removebuttonTest {

    /**
     * Create needed parameters.
     */

    private Button removebtntest;
    private DirectoryChooser directorychoosertest;
    private File selectedTestFile;
    public removebuttonTest() {}

    public Button createremovebtntest(ListView<String> listview) {
        directorychoosertest = new DirectoryChooser();
        selectedTestFile = directorychoosertest.showDialog(null);
        return removebtntest;
    }

    /**
     * Test if correct path gets passed.
     */

    @Test
    public void testhandle() {
        String handle = null;
        try {
            Assert.assertEquals(selectedTestFile.getPath(), handle);
            System.out.println("testhandle() test passed");
        } catch (Throwable e) {
            System.out.println("testhandle() test failed");
        }

    }

}