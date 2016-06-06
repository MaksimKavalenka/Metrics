package by.training.parser;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import by.training.bean.element.ConfigElement;

public abstract class JAXBParser {

    private static final String XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
    private static final String XMD_SCHEMA = "src/main/resources/validate/config.xsd";

    public static void createConfig(final ConfigElement config, final File file)
            throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(ConfigElement.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(config, file);
    }

    public static boolean validateConfig(final File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(ConfigElement.class);
            SchemaFactory factory = SchemaFactory.newInstance(XML_SCHEMA);
            Schema schema = factory.newSchema(new File(XMD_SCHEMA));
            Unmarshaller unmarshaller = context.createUnmarshaller();
            unmarshaller.setSchema(schema);
            unmarshaller.unmarshal(file);
        } catch (JAXBException | SAXException e) {
            return false;
        }
        return true;
    }

    public static ConfigElement getConfig(final File file) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(ConfigElement.class);
        return (ConfigElement) context.createUnmarshaller().unmarshal(file);
    }

    public static void updateConfig(final ConfigElement config, final File file)
            throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(ConfigElement.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(config, file);
    }

}
