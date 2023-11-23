package cz.cvut.kbss.ear.eshop.service;

import cz.cvut.kbss.ear.eshop.dao.ServerDao;
import cz.cvut.kbss.ear.eshop.model.Book;
import cz.cvut.kbss.ear.eshop.model.Client;
import cz.cvut.kbss.ear.eshop.model.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServerService {
    private static final Logger LOG = LoggerFactory.getLogger(ServerService.class);
    private final ServerDao serverDao;

    @Autowired
    public ServerService(ServerDao serverDao) {
        this.serverDao = serverDao;
    }
    @Transactional(readOnly = true)
    public List<Server> findAll() {
        return serverDao.findAll();
    }
    @Transactional
    public void updateServerStatus(Server server, boolean newStatus) {
        server.setStatus(newStatus);
        serverDao.update(server);
    }
    @Transactional
    public void persist(Server server) {
        serverDao.persist(server);
    }
    @Transactional
    public List<Server> findServersByLocation(String location) {
        return serverDao.findByLocation(location);
    }

    @Transactional
    public void performMaintenance(Server server, String maintenanceDescription) {
        server.setStatus(true);
        server.setLastMaintenanceDate(LocalDateTime.now());
        // Add maintenance description to a log or send a notification
        LOG.info("Maintenance performed on server at location {}. Description: {}", server.getLocation(), maintenanceDescription);
        serverDao.update(server);
    }

    @Transactional
    public void checkCapacity(Server server) {
        if (server.getCapacity() < server.getCurrentLoad()) {
            // Notify the system administrators or trigger an alert for high load
            LOG.warn("Server at location {} is experiencing high load. Current load: {}", server.getLocation(), server.getCurrentLoad());
        } else {
            LOG.info("Server at location {} is operating within capacity limits.", server.getLocation());
        }
    }
}
