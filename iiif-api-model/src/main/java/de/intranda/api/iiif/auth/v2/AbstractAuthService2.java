package de.intranda.api.iiif.auth.v2;

import java.net.URI;

import de.intranda.api.iiif.image.Service;

public abstract class AbstractAuthService2 extends Service implements IAuthMessage {

    public abstract URI getId();

    public URI getContext() {
        return URI.create(CONTEXT);
    }

    public abstract String getType();
}
