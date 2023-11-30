package cz.cvut.kbss.ear.eshop.service;

import cz.cvut.kbss.ear.eshop.dao.ClientDao;
import cz.cvut.kbss.ear.eshop.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {

    private final ClientDao clientDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClientService(ClientDao clientDao, PasswordEncoder passwordEncoder) {
        this.clientDao = clientDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public List<Client> findAll() {
        return clientDao.findAll();
    }

    @Transactional
    public Client find(Integer id) {
        return clientDao.find(id);
    }

    @Transactional
    public List<Client> findByFirstNameAndLastName(String firstName, String lastName) {
        return clientDao.findByFirstNameAndLastName(firstName, lastName);
    }
    @Transactional
    public void persist(Client client) {
        String encodedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(encodedPassword);
        clientDao.persist(client);
    }

    @Transactional
    public void update(Client client) {
        clientDao.update(client);
    }

    @Transactional
    public void remove(Client client) {
        clientDao.remove(client);
    }
}
