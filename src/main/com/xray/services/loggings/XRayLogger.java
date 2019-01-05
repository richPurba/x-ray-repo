package xray.services.loggings;

import java.util.logging.Level;

public interface XRayLogger {

    public void log(Level level, String string, Object[] object);

    public default void log(Level level, String message){ this.log(level,message,null);}
}
