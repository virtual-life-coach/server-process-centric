package vlc.pc.dispatcher;

import com.google.api.server.spi.ServiceException;
import vlc.common.util.transformer.SoapAppointmentTransformer;
import vlc.common.util.transformer.SoapUserActivityTransformer;
import vlc.ldb.soap.LocalDatabase;
import vlc.ldb.soap.LocalDatabaseService;

import java.util.ArrayList;
import java.util.List;

public class RequestDispatcher {

    public static List<vlc.common.to.AppointmentTO> listAppointments(Integer telegramId) throws ServiceException {
        LocalDatabase service = new LocalDatabaseService().getLocalDatabaseImplPort();
        List<vlc.ldb.soap.AppointmentTO> appointmentsSoap = service.listAppointments();
        List<vlc.common.to.AppointmentTO> appointmentsTO = new ArrayList<>();
        SoapAppointmentTransformer transformer = new SoapAppointmentTransformer();
        for (vlc.ldb.soap.AppointmentTO appointmentSoap : appointmentsSoap) { // TODO filter by telegramId
            appointmentsTO.add(transformer.toTO(appointmentSoap));
        }
        return appointmentsTO;
    }

    public static List<vlc.common.to.UserActivityTO> listActivities(Integer telegramId) throws ServiceException {
        LocalDatabase service = new LocalDatabaseService().getLocalDatabaseImplPort();
        List<vlc.ldb.soap.UserActivityTO> userActivitiesSoap = service.listUserActivities();
        List<vlc.common.to.UserActivityTO> userActivitiesTO = new ArrayList<>();
        SoapUserActivityTransformer transformer = new SoapUserActivityTransformer();
        for (vlc.ldb.soap.UserActivityTO userActivitySoap : userActivitiesSoap) { // TODO filter by telegramId
            userActivitiesTO.add(transformer.toTO(userActivitySoap));
        }
        return userActivitiesTO;
    }

    public static vlc.common.to.UserActivityTO getActivityProgress(Integer activityId) throws ServiceException {
        LocalDatabase service = new LocalDatabaseService().getLocalDatabaseImplPort();
        vlc.ldb.soap.UserActivityTO userActivitySoap = service.readUserActivity(activityId);
        SoapUserActivityTransformer transformer = new SoapUserActivityTransformer();
        return transformer.toTO(userActivitySoap);
    }

    public static void updateActivityProgress(Integer telegramId, Integer activityId, Long value)
            throws ServiceException {
        LocalDatabase service = new LocalDatabaseService().getLocalDatabaseImplPort();
        //service.updateUserActivity();
        // TODO
    }
}
