package xray.services;

import com.hazelcast.internal.monitors.PerformanceMonitor;
import xray.services.loggings.XRayLogger;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

@Startup// asynchronous
@Singleton//asynchronous
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@Interceptors(PerformanceMonitor.class)// still not following Abien!
public class HitsManagement {

    @Inject
    XRayLogger LOG;

    public static final String ENTRY = "/entry/";

//    @Inject
//    URLFilter urlFilter;

    @Inject
    Event<String> uriListener;

//    private HitsCache hitCache =null;
//    private HitsCache trendingCache = null;
//    private HitsCache refererCache;

//    private DailyHitCache dailyHitCache;
    @Inject
    @Grid


}
