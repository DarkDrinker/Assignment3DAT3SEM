package Week6.VeterinaryProject;

import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppointmentController {
    public static Map<String, AppointmentDTO> appointments = new HashMap<>();
    static {
        appointments.put("1", new AppointmentDTO(1,"2024-03-25 10:00", "1"));
        appointments.put("2", new AppointmentDTO(2, "2024-03-26 14:30", "2"));
    }
    public void getAllAppointments(Context ctx){
        ctx.json(new ArrayList<>(appointments.values()));
    }
    public void getAppointmentById(Context ctx){
        String appointmentId = ctx.pathParam("id");
        AppointmentDTO appointment = appointments.get(appointmentId);
        if (appointment != null) {
            ctx.json(appointment);
        } else {
            ctx.status(404).json("Appointment not found");
        }
    }
}
