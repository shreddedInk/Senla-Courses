// Основной класс Пациент
public class Patient {
    private String id;
    private String name;
    private int age;
    private String diagnosis;

    public Patient(String id, String name, int age, String diagnosis) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.diagnosis = diagnosis;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void displayInfo() {
        System.out.println("Patient ID: " + id + ", Name: " + name + ", Age: " + age + ", Diagnosis: " + diagnosis);
    }
}

// Класс Медицинская Карта (связь 1:1 с Пациентом)
class MedicalRecord {
    private String recordId;
    private String patientId;
    private String notes;

    public MedicalRecord(String recordId, String patientId, String notes) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.notes = notes;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void displayRecord() {
        System.out.println("Record ID: " + recordId + ", Patient ID: " + patientId + ", Notes: " + notes);
    }
}

// Класс Врач (связь многие-к-одному с Пациентами)
class Doctor {
    private String doctorId;
    private String name;
    private String specialization;

    public Doctor(String doctorId, String name, String specialization) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void assignPatient(Patient patient) {
        System.out.println("Doctor " + name + " is assigned to patient " + patient.getName());
    }
}

// Класс Назначение (Appointment - связь многие-ко-многим)
class Appointment {
    private String appointmentId;
    private Patient patient;
    private Doctor doctor;
    private String date;

    public Appointment(String appointmentId, Patient patient, Doctor doctor, String date) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
    }

    public void displayAppointmentDetails() {
        System.out.println("Appointment ID: " + appointmentId + ", Patient: " + patient.getName() +
                ", Doctor: " + doctor.getName() + ", Date: " + date);
    }
}
