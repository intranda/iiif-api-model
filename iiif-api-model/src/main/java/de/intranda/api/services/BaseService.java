package de.intranda.api.services;

import java.net.URI;
import java.net.URISyntaxException;

public class BaseService implements Service {

    public URI context;
    
    public BaseService() {
        context = null;
    }
    
    public BaseService(URI context) {
        this.context = context;
    }
    
    @Override
    public URI getContext() {
        return context;
    }
    
}
