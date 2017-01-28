package dispatcher;

import com.google.api.server.spi.ServiceException;
import vlc.common.to.ActivityTO;
import vlc.common.to.AppointmentTO;

import java.util.ArrayList;
import java.util.List;

public class RequestDispatcher {

    public static List<AppointmentTO> listAppointments(Integer telegramId) throws ServiceException {
        // TODO
        List<AppointmentTO> appointments = new ArrayList<>();
        appointments.add(new AppointmentTO(1, "user" + telegramId, "doctor1", "date1", "location1"));
        appointments.add(new AppointmentTO(2, "user" + telegramId, "doctor2", "date2", "location2"));
        return appointments;
    }

    public static List<ActivityTO> listActivities(Integer telegramId) throws ServiceException {
        // TODO
        List<ActivityTO> activities = new ArrayList<>();
        activities.add(new ActivityTO(1, "Decription 1 for " + telegramId));
        activities.add(new ActivityTO(2, "Decription 2 for " + telegramId));
        return activities;
    }

    public static ActivityTO getActivityProgress(Integer telegramId, Integer activityId) throws ServiceException {
        // TODO
        return new ActivityTO(activityId, "Current progress for " + telegramId + " activityId " + activityId);
    }

    public static void updateActivityProgress(Integer telegramId, Integer activityId, Long value)
            throws ServiceException {
        // TODO
    }
}
