package vlc.pc.dispatcher;

import com.google.api.server.spi.ServiceException;
import vlc.common.to.AppointmentTO;
import vlc.common.to.UserActivityTO;

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

    public static List<UserActivityTO> listActivities(Integer telegramId) throws ServiceException {
        // TODO
        List<UserActivityTO> activities = new ArrayList<>();
        activities.add(new UserActivityTO(1, 1, 3, "details1", 100L, 10L, "tomorrow", false));
        activities.add(new UserActivityTO(2, 2, 3, "details2", 100L, 5L, "tomorrow", true));
        return activities;
    }

    public static UserActivityTO getActivityProgress(Integer activityId) throws ServiceException {
        // TODO
        return new UserActivityTO(4, 2, 3, "details", 50L, 10L, "tomorrow", false);
    }

    public static void updateActivityProgress(Integer telegramId, Integer activityId, Long value)
            throws ServiceException {
        // TODO
    }
}
