package by.training.editor;

import static by.training.exception.ConfigEditorException.*;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import by.training.bean.element.ConfigElement;
import by.training.constants.ConfigDefaultConstants;
import by.training.exception.ConfigEditorException;
import by.training.parser.JAXBParser;

public class ConfigEditor {

    private static final String CONFIG_PATH = System.getProperty("config.path");
    private static final String CONFIG_FILE = System.getProperty("config.file");

    private static final File   FILE        = new File(CONFIG_PATH + CONFIG_FILE);

    public static ConfigElement config;

    public static void init() throws ConfigEditorException {
        checkDirectory(new File(CONFIG_PATH));
        checkFile(FILE);

        if (!JAXBParser.validateConfig(FILE)) {
            setDefault();
        }

        try {
            config = JAXBParser.getConfig(FILE);
        } catch (JAXBException e) {
            throw new ConfigEditorException(CONFIG_ACCESS_ERROR);
        }
    }

    public static void checkDirectory(final File file) {
        if (!file.isDirectory()) {
            file.mkdir();
        }
    }

    public static void checkFile(final File file) throws ConfigEditorException {
        if (!FILE.exists()) {
            try {
                FILE.createNewFile();
            } catch (IOException e) {
                throw new ConfigEditorException(FILE_ACCESS_ERROR);
            }
        }
    }

    public static void setDefault() throws ConfigEditorException {
        try {
            JAXBParser.createConfig(ConfigDefaultConstants.CONFIG, FILE);
        } catch (JAXBException e) {
            throw new ConfigEditorException(JAXB_ERROR);
        }
    }

    public static void updateConfig() throws ConfigEditorException {
        try {
            JAXBParser.updateConfig(config, FILE);
        } catch (JAXBException e) {
            throw new ConfigEditorException(CONFIG_UPDATE_ERROR);
        }
    }

}
