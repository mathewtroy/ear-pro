package cz.cvut.kbss.ear.eshop.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
public class Server {
    @Id
    private long IPAddress;

    private int Capacity;
    @Basic(optional = false)
    @Column(nullable = false)
    private int CurrentLoad;
    private LocalDateTime LastMaintenanceDate;
    private String Location;
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
