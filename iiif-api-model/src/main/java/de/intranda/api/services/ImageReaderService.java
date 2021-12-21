package de.intranda.api.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ImageReaderService implements Service {

        private static final String CONTEXTPATH = ImageReaderDefinition.URI_PATH;

        private Collection<String> readers;
        private String baseURI;
        
        /**
         * 
         */
        public ImageReaderService() {
           this.readers = new ArrayList<>();
           baseURI =  "";
        }
        
        public ImageReaderService(String baseURI) {
            this.readers = new ArrayList<>();
            this.baseURI =  baseURI;
         }
        
        public ImageReaderService(Collection<String> readers) {
            this("", readers);
        }

        public ImageReaderService(String baseURI, Collection<String> readers) {
            this.readers = readers == null ? Collections.emptyList() : readers;
            this.baseURI =  baseURI;
         }
        
        public void setBaseURI(String baseURI) {
            this.baseURI = baseURI;
        }
        
        public Collection<String> getReaders() {
            return readers;
        }
        
        /* (non-Javadoc)
         * @see de.intranda.digiverso.presentation.servlets.rest.services.Service#getContext()
         */
        @Override
        public URI getContext() {
            return URI.create(baseURI + CONTEXTPATH);
        }

}
