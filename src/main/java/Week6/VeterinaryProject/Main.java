package Week6.VeterinaryProject;


import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

public class Main {
    static PatientController patientController = new PatientController();
    static AppointmentController appointmentController = new AppointmentController();
    public static void main(String[] args) {
        ApplicationConfig applicationConfig = ApplicationConfig.getInstance();
        applicationConfig.initiateServer()
                .startServer(7007)
                .setExceptionHandling()
                .setRoute( () -> {
                    path("patient", () -> {
                        //all patients
                        get("/", ctx -> {
                            patientController.getAllPatients(ctx);
                        });
                        get("/{id}", ctx -> {
                            patientController.getPatientById(ctx);
                        });

                    });
                    path("appointment", () -> {
                        get("/", ctx -> {
                            appointmentController.getAllAppointments(ctx);
                        });
                        get("/{id}", ctx -> {
                            appointmentController.getAppointmentById(ctx);
                        });
                    });
                });


    }
}
