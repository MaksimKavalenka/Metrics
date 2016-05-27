package by.training.editor;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Random;

import javax.ws.rs.core.UriBuilder;
import javax.xml.ws.WebServiceException;

public class TransportEditor {

    public static URI getBaseUri(final String address) {
        return UriBuilder.fromUri(address).build();
    }

    public static URL getUrl(final String address) {
        try {
            return new URL(address);
        } catch (MalformedURLException e) {
            throw new WebServiceException(e);
        }
    }

    public static String getRandomString() {
        Random random = new Random(System.currentTimeMillis());
        long value = random.nextLong();
        return Long.toHexString(value);
    }

}
