package ie.ioffer.web.rest_api;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class Application extends ResourceConfig {

    public Application() {
        super(MultiPartFeature.class);
    }
}
