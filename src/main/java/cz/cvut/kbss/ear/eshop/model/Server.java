package cz.cvut.kbss.ear.eshop.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Server {
    @Id
    @JsonProperty("ipaddress")
    private long IPAddress;

    @JsonProperty("capacity")
    private int Capacity;

    @JsonProperty("currentload")
    @Basic(optional = false)
    @Column(nullable = false)
    private int CurrentLoad;

    @JsonProperty("lastmaintenancedate")
    private LocalDateTime LastMaintenanceDate;

    @JsonProperty("location")
    private String Location;

    @JsonProperty("status")
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean Status;


    public long getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(long IPAddress) {
        this.IPAddress = IPAddress;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public int getCurrentLoad() {
        return CurrentLoad;
    }

    public void setCurrentLoad(int currentLoad) {
        CurrentLoad = currentLoad;
    }

    public LocalDateTime getLastMaintenanceDate() {
        return LastMaintenanceDate;
    }

    public void setLastMaintenanceDate(LocalDateTime lastMaintenanceDate) {
        LastMaintenanceDate = lastMaintenanceDate;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "Server{" +
                "IPAddress=" + IPAddress +
                ", Capacity=" + Capacity +
                ", CurrentLoad=" + CurrentLoad +
                ", LastMaintenanceDate=" + LastMaintenanceDate +
                ", Location='" + Location + '\'' +
                ", Status=" + Status +
                '}';
    }
}
