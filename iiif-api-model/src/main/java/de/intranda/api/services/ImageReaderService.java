package de.intranda.api.services;

import java.net.URI;
import java.util.Collection;
import java.util.Collections;

public class ImageReaderService implements Service {

    private static final String CONTEXTPATH = ImageReaderDefinition.URI_PATH;
    private static final String SERVICEID = "imageReaders";
    private static final String TYPE = "GoobiImageReadersService";
    private static final String PROFILE = "goobi/ics/imageReaders/1.0/service";

    private final Collection<String> readers;
    private final URI context;
    private final URI id;

    /**
     * 
     */
    public ImageReaderService() {
        this("");
    }

    public ImageReaderService(String baseURI) {
        this(baseURI, Collections.emptyList());
    }

    public ImageReaderService(Collection<String> readers) {
        this("", readers);
    }

    public ImageReaderService(String baseURI, Collection<String> readers) {
        this.readers = readers == null ? Collections.emptyList() : readers;
        this.context = URI.create(baseURI + CONTEXTPATH);
        this.id = URI.create(baseURI + SERVICEID);
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.servlets.rest.services.Service#getContext()
     */
    @Override
    public URI getContext() {
        return this.context;
    }

    public URI getId() {
        return this.id;
    }

    public String getType() {
        return TYPE;
    }

    public String getProfile() {
        return PROFILE;
    }

    public Collection<String> getReaders() {
        return readers;
    }

}
