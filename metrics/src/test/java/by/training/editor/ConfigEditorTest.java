package by.training.editor;

import static org.junit.Assert.*;

import org.junit.Test;

import by.training.exception.ConfigEditorException;

public class ConfigEditorTest {

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateConfig() throws ConfigEditorException {
        ConfigEditor.updateConfig();
        fail("testUpdateConfig has failed");
    }

}
