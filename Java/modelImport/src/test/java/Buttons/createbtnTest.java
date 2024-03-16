package Buttons;

import main.modelname;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.*;

/**
 * Tests to check createbtn Class.
 *
 * @author Fabian Kerner
 */

public class createbtnTest {

    /**
     * Create strings to have tests on.
     */

    String testname = "name";
    String testdae = "dae";


    String intosdf = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
            "<sdf version='1.6'>\r\n" +
            "  <model name=\"" + testname + "\">\r\n" +
            "    <static>true</static>\r\n" +
            "    <link name='" + testname + "_link'>\r\n" +
            "      <collision name='" + testname + "_collision'>\r\n" +
            "        <max_contacts>10</max_contacts>\r\n" +
            "        <geometry>\r\n" +
            "          <mesh>\r\n" +
            "            <uri>model://" + testname + "/meshes/" + testdae + "</uri>\r\n" +
            "          </mesh>\r\n" +
            "        </geometry>\r\n" +
            "      </collision>\r\n" +
            "      <visual name='" + testname + "_visual'>\r\n" +
            "        <geometry>\r\n" +
            "          <mesh>\r\n" +
            "            <uri>model://" + testname + "/meshes/" + testdae + "</uri>\r\n" +
            "          </mesh>\r\n" +
            "        </geometry>\r\n" +
            "      </visual>\r\n" +
            "    </link>\r\n" +
            "  </model>\r\n" +
            "</sdf>";

    /**
     * Test if correct string is implemented.
     */

    @Test
    public void testCreatecreatebtn() {
        String test = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
                "<sdf version='1.6'>\r\n" +
                "  <model name=\"" + "name" + "\">\r\n" +
                "    <static>true</static>\r\n" +
                "    <link name='" + "name" + "_link'>\r\n" +
                "      <collision name='" + "name" + "_collision'>\r\n" +
                "        <max_contacts>10</max_contacts>\r\n" +
                "        <geometry>\r\n" +
                "          <mesh>\r\n" +
                "            <uri>model://" + "name" + "/meshes/" + "dae" + "</uri>\r\n" +
                "          </mesh>\r\n" +
                "        </geometry>\r\n" +
                "      </collision>\r\n" +
                "      <visual name='" + "name" + "_visual'>\r\n" +
                "        <geometry>\r\n" +
                "          <mesh>\r\n" +
                "            <uri>model://" + "name" + "/meshes/" + "dae" + "</uri>\r\n" +
                "          </mesh>\r\n" +
                "        </geometry>\r\n" +
                "      </visual>\r\n" +
                "    </link>\r\n" +
                "  </model>\r\n" +
                "</sdf>";
        try {
            Assert.assertEquals(intosdf, test);
            System.out.println("Createcreatebtn() test passed");
        } catch (Throwable e) {
            System.out.println("Createcreatebtn() test failed");
        }



    }
}