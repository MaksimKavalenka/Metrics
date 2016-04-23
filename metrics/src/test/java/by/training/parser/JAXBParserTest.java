package by.training.parser;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import by.training.constants.ConfigDefaultConstants;

public class JAXBParserTest {

    private static File           file;

    @ClassRule
    public static TemporaryFolder temporaryFolder = new TemporaryFolder();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        file = temporaryFolder.newFile("config.xml");
        JAXBParser.createConfig(ConfigDefaultConstants.CONFIG, file);
    }

    @Test
    public void testValidateConfig() {
        assertTrue("testValidateConfig has failed", JAXBParser.validateConfig(file));
    }

    @Test
    public void testGetConfig() throws JAXBException, IOException {
        assertNotNull("testGetConfig has failed", JAXBParser.getConfig(file));
    }

}
