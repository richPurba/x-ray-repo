package main.com.xray.services;


import com.hazelcast.internal.monitors.PerformanceMonitor;
import xray.services.loggings.XRayLogger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.Path;

@Path("hits")
@Stateless
@Interceptors(PerformanceMonitor.class)// this is still not what Abien is doing!
public class HitsResource {

    @Inject
    private XRayLogger LOG;

    @Inject


}
