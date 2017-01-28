package vlc.pc.dispatcher;

import com.google.api.server.spi.ServiceException;
import com.google.api.server.spi.response.InternalServerErrorException;
import vlc.common.connectors.Telegram;
import vlc.common.util.Constants;
import vlc.common.util.transformer.SoapAppointmentTransformer;
import vlc.common.util.transformer.SoapUserActivityTransformer;
import vlc.ldb.soap.LocalDatabase;
import vlc.ldb.soap.LocalDatabaseService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class RequestDispatcher {

    private static final Logger log = Logger.getLogger(RequestDispatcher.class.getName());

    public static List<vlc.common.to.AppointmentTO> listAppointments(Integer telegramId) throws ServiceException {
        LocalDatabase service = new LocalDatabaseService().getLocalDatabaseImplPort();
        List<vlc.ldb.soap.AppointmentTO> appointmentsSoap = service.listAppointments();
        List<vlc.common.to.AppointmentTO> appointmentsTO = new ArrayList<>();
        SoapAppointmentTransformer transformer = new SoapAppointmentTransformer();
        vlc.ldb.soap.UserTO currentUser = service.getUserByTelegramId(telegramId);
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
        for (vlc.ldb.soap.AppointmentTO appointmentSoap : appointmentsSoap) {
            try {
                if (appointmentSoap.getUserId().equals(currentUser.getId()) &&
                        now.before(formatter.parse(appointmentSoap.getDate()))) { // hide old appointments
                    appointmentsTO.add(transformer.toTO(appointmentSoap));
                }
            } catch (ParseException e) {
                throw new InternalServerErrorException(e.getMessage());
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
            if (userActivitySoap.getUserId().equals(currentUser.getId()) && !userActivitySoap.isCompleted()) {
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
        vlc.ldb.soap.UserActivityTO userActivitySoap = service.readUserActivity(activityId);
        if (userActivitySoap.isCompleted()) {
            vlc.ldb.soap.UserTO userSoap = service.readUser(userActivitySoap.getUserId());
            Telegram.sendMessage(userSoap.getTelegramId(), "Awesome! You have just completed your daily activity. " +
                    "Maybe tomorrow we could try with something harder. No pain, no gain!");
        }
    }
}
