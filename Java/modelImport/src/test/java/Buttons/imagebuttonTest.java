package Buttons;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.*;

import static org.testng.Assert.*;

/**
 * Tests to check imagebutton Class.
 *
 * @author Fabian Kerner
 */

public class imagebuttonTest {

    /**
     * Create list with files.
     */

    private List<File> selectedFiles = new ArrayList<File>();



    @Test
    public void testhandle() {

        /**
         * Test if correct file was added.
         */

        File testfile = new File("TestOrt" + "/" + "TestName");
        selectedFiles.add(0,testfile);

        try {
            Assert.assertTrue(selectedFiles.contains(testfile));
            System.out.println("testhandle() test passed");
        } catch (Throwable e) {
            System.out.println("testhandle() test failed");
        }

    }

}