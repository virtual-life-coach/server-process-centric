package rest;

import com.google.api.server.spi.ServiceException;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import dispatcher.RequestDispatcher;
import vlc.common.to.ActivityTO;
import vlc.common.to.AppointmentTO;

import java.util.List;
import java.util.logging.Logger;

/**
 * The Function API which Endpoints will be exposing as REST service.
 */
@Api(
        name = "function",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "vlc-process-centric",
                ownerName = "vlc-process-centric",
                packagePath = ""
        )
)
public class Function {

    private static final Logger log = Logger.getLogger(Function.class.getName());

    @ApiMethod(
            name = "listAppointments",
            path = "appointment/{telegramid}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<AppointmentTO> listAppointments(@Named("telegramid") Integer telegramId) throws ServiceException {
        log.info("listAppointments " + telegramId);
        return RequestDispatcher.listAppointments(telegramId);
    }

    @ApiMethod(
            name = "listActivities",
            path = "activity/{telegramid}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<ActivityTO> listActivities(@Named("telegramid") Integer telegramId) throws ServiceException {
        log.info("listActivities " + telegramId);
        return RequestDispatcher.listActivities(telegramId);
    }

    @ApiMethod(
            name = "getActivityProgress",
            path = "activity/{telegramid}/{activityid}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public ActivityTO getActivityProgress(@Named("telegramid") Integer telegramId, @Named("activityid") Integer activityId)
            throws ServiceException {
        log.info("getActivityProgress " + telegramId + " " + activityId);
        return RequestDispatcher.getActivityProgress(telegramId, activityId);
    }

    @ApiMethod(
            name = "updateActivityProgress",
            path = "activity/{telegramid}/{activityid}/{value}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public void updateActivityProgress(@Named("telegramid") Integer telegramId, @Named("activityid") Integer activityId,
                                       @Named("value") Long value) throws ServiceException {
        log.info("updateActivityProgress " + telegramId + " " + activityId + " " + value);
        RequestDispatcher.updateActivityProgress(telegramId, activityId, value);
    }
}