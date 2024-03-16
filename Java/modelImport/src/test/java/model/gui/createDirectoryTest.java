package model.gui;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests to check createDirectory Class.
 *
 * @author Fabian Kerner
 */

public class createDirectoryTest {

    File files = new File("TestOrt" + "/" + "TestName");
    File meshes = new File(files + "/meshes");
    File materials = new File(files + "/materials/textures");
    File config = new File(files + "/model.config");
    File sdf = new File(files + "/model.sdf");

    /**
     * Check if files get created in correct path.
     */

    @Test
    public void testFiles() {
        String file = "TestOrt" + files.separator + "TestName";
        try {
            Assert.assertEquals(files.getPath(), file);
            System.out.println("testFiles() test passed");
        } catch (Throwable e) {
            System.out.println("testFiles() test failed");
        }
    }

    @Test
    public void testMeshes() {
        String mesh = "TestOrt" + files.separator + "TestName" + files.separator +"meshes";
        try {
            Assert.assertEquals(meshes.getPath(), mesh);
            System.out.println("testMeshes() test passed");
        } catch (Throwable e) {
            System.out.println("testMeshes() test failed");
        }
    }

    @Test
    public void testMaterials() {
        String mat = "TestOrt" + files.separator + "TestName" + files.separator+ "materials" + files.separator+ "textures";
        try {
            Assert.assertEquals(materials.getPath(), mat);
            System.out.println("testMaterials() test passed");
        } catch (Throwable e) {
            System.out.println("testMaterials() test failed");
        }
    }

    @Test
    public void testConfig() {
        String conf = "TestOrt" + files.separator + "TestName" + files.separator +"model.config";
        try {
            Assert.assertEquals(config.getPath(), conf);
            System.out.println("testConfig() test passed");
        } catch (Throwable e) {
            System.out.println("testConfig() test failed");
        }
    }

    @Test
    public void testSDF() {
        String testsdf = "TestOrt" + files.separator+ "TestName" + files.separator + "model.sdf";
        try {
            Assert.assertEquals(sdf.getPath(), testsdf);
            System.out.println("testSDF() test passed");
        } catch (Throwable e) {
            System.out.println("testSDF() test failed");
        }
    }

}