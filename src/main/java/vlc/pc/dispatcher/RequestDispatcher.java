package vlc.pc.dispatcher;

import com.google.api.server.spi.ServiceException;
import vlc.common.util.transformer.SoapAppointmentTransformer;
import vlc.common.util.transformer.SoapUserActivityTransformer;
import vlc.ldb.soap.LocalDatabase;
import vlc.ldb.soap.LocalDatabaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class RequestDispatcher {

    Logger log = Logger.getLogger(RequestDispatcher.class.getName());

    public static List<vlc.common.to.AppointmentTO> listAppointments(Integer telegramId) throws ServiceException {
        LocalDatabase service = new LocalDatabaseService().getLocalDatabaseImplPort();
        List<vlc.ldb.soap.AppointmentTO> appointmentsSoap = service.listAppointments();
        List<vlc.common.to.AppointmentTO> appointmentsTO = new ArrayList<>();
        SoapAppointmentTransformer transformer = new SoapAppointmentTransformer();
        vlc.ldb.soap.UserTO currentUser = service.getUserByTelegramId(telegramId);
        for (vlc.ldb.soap.AppointmentTO appointmentSoap : appointmentsSoap) {
            if (appointmentSoap.getUserId().equals(currentUser.getId())) {
                appointmentsTO.add(transformer.toTO(appointmentSoap));
            }
        }
        return appointmentsTO;
    }

    public static List<vlc.common.to.UserActivityTO> listActivities(Integer telegramId) throws ServiceException {
        LocalDatabase service = new LocalDatabaseService().getLocalDatabaseImplPort();
        List<vlc.ldb.soap.UserActivityTO> userActivitiesSoap = service.listUserActivities();
        List<vlc.common.to.UserActivityTO> userActivitiesTO = new ArrayList<>();
        SoapUserActivityTransformer transformer = new SoapUserActivityTransformer();
        vlc.ldb.soap.UserTO currentUser = service.getUserByTelegramId(telegramId);
        for (vlc.ldb.soap.UserActivityTO userActivitySoap : userActivitiesSoap) {
            if (userActivitySoap.getUserId().equals(currentUser.getId())) {
                userActivitiesTO.add(transformer.toTO(userActivitySoap));
            }
        }
        return userActivitiesTO;
    }

    public static vlc.common.to.UserActivityTO getActivityProgress(Integer activityId) throws ServiceException {
        LocalDatabase service = new LocalDatabaseService().getLocalDatabaseImplPort();
        vlc.ldb.soap.UserActivityTO userActivitySoap = service.readUserActivity(activityId);
        SoapUserActivityTransformer transformer = new SoapUserActivityTransformer();
        return transformer.toTO(userActivitySoap);
    }

    public static void updateActivityProgress(Integer activityId, Long value) throws ServiceException {
        LocalDatabase service = new LocalDatabaseService().getLocalDatabaseImplPort();
        service.updateUserActivityValue(activityId, value);
    }
}
