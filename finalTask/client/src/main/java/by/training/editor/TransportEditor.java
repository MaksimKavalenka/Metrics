package by.training.editor;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.ws.rs.core.UriBuilder;
import javax.xml.ws.WebServiceException;

public class TransportEditor {

    public static URI getBaseURI(final String address) {
        return UriBuilder.fromUri(address).build();
    }

    public static URL getURL(final String address) {
        try {
            return new URL(address);
        } catch (MalformedURLException e) {
            throw new WebServiceException(e);
        }
    }

}
