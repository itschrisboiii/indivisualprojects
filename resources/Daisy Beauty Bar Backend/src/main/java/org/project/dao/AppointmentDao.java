package org.project.dao;

import org.project.model.Appointment;

import java.util.List;

public interface AppointmentDao {
    // gets an appointment by inputting an id
    Appointment getAppointmentById(int appointmentId);

    // get appointment by date
    Appointment getAppointmentByDate (String date);

    // shows all appointments
    List<Appointment> getAppointments();

    // creates appointment
    Appointment createAppointment(Appointment appointment);

    // updates appointment
    Appointment updateAppointment(Appointment appointment);
    // cancel appointment
    int cancelAppointmentById(int appointmentId);
}
