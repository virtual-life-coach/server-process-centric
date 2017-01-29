package vlc.pc.rest;

import com.google.api.server.spi.ServiceException;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import vlc.pc.dispatcher.RequestDispatcher;
import vlc.common.to.AppointmentTO;
import vlc.common.to.UserActivityTO;

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

    /**
     * GET method. Retrieve the list of future appointments for a specific user.
     *
     * @param telegramId user telegram id
     * @return appointments list
     * @throws ServiceException on malformed requests or back end error
     */
    @ApiMethod(
            name = "listAppointments",
            path = "appointments/{telegramid}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<AppointmentTO> listAppointments(@Named("telegramid") Integer telegramId) throws ServiceException {
        log.info("listAppointments " + telegramId);
        return RequestDispatcher.listAppointments(telegramId);
    }

    /**
     * GET method. Retrieve the list of activities for a specific user.
     *
     * @param telegramId user telegram id
     * @return activities list
     * @throws ServiceException on malformed requests or back end error
     */
    @ApiMethod(
            name = "listActivities",
            path = "activities/{telegramid}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<UserActivityTO> listActivities(@Named("telegramid") Integer telegramId) throws ServiceException {
        log.info("listActivities " + telegramId);
        return RequestDispatcher.listActivities(telegramId);
    }

    /**
     * GET method. Retrieve a user activity.
     *
     * @param activityId activity id
     * @return activity
     * @throws ServiceException on malformed requests or back end error
     */
    @ApiMethod(
            name = "getActivityProgress",
            path = "activity/{activityid}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public UserActivityTO getActivityProgress(@Named("activityid") Integer activityId)
            throws ServiceException {
        log.info("getActivityProgress " + activityId);
        return RequestDispatcher.getActivityProgress(activityId);
    }

    /**
     * PUT method. Update the current value for a user activity.
     *
     * @param activityId activity id
     * @param value new current activity value
     * @throws ServiceException on malformed requests or back end error
     */
    @ApiMethod(
            name = "updateActivityProgress",
            path = "activity/{activityid}/{value}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public void updateActivityProgress(@Named("activityid") Integer activityId,
                                       @Named("value") Long value) throws ServiceException {
        log.info("updateActivityProgress " + activityId + " " + value);
        RequestDispatcher.updateActivityProgress(activityId, value);
    }
}
