package Week6.VeterinaryProject;

import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientController {
    public static Map<String, PatientDTO> patients = new HashMap<>();
    static {
        List<String> allergies1 = new ArrayList<>();
        allergies1.add("Pollen");
        List<String> medications1 = new ArrayList<>();
        medications1.add("Antihistamine");
        patients.put("1", new PatientDTO(1, "Fluffy", "Cat", "Female", 5, allergies1, medications1));

        List<String> allergies2 = new ArrayList<>();
        allergies2.add("Chocolate");
        allergies2.add("dust");
        List<String> medications2 = new ArrayList<>();
        medications2.add("Painkiller");
        patients.put("2", new PatientDTO(2, "Max", "Dog", "Male", 3, allergies2, medications2));
    }
    public void getAllPatients(Context ctx) {
        ctx.json(new ArrayList<>(patients.values()));
    }
    public void getPatientById(Context ctx) {
        String patientId = ctx.pathParam("id");
        PatientDTO patient = patients.get(patientId);
        if (patient != null) {
            ctx.json(patient);
        } else {
            ctx.status(404).json("Appointment not found");
        }
    }
}
