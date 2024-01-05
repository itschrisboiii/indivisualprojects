package org.project.model;

import java.sql.Time;
import java.time.LocalDate;

public class Appointment {

    private int id;
    private LocalDate date;
    private Time time;
    private int client_id;
    private int service_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }
    @Override
    public String toString() {
        return "Appointment {" +
                "id=" + id +
                "date=" + date +
                "time=" + time +
                "clientId=" + client_id +
                "serviceId=" + service_id+
                "}";
    }
}

