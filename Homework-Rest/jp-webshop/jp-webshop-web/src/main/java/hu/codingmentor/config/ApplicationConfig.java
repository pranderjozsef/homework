package hu.codingmentor.config;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("/webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(hu.codingmentor.exception.BadRequestExceptionMapper.class);
        resources.add(hu.codingmentor.exception.ValidationExceptionMapper.class);
        resources.add(hu.codingmentor.rest.CartRESTService.class);
        resources.add(hu.codingmentor.rest.InventoryRESTService.class);
        resources.add(hu.codingmentor.rest.UserRESTService.class);
    }   
}
