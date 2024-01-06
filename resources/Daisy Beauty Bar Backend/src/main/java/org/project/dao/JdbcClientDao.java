package org.project.dao;

import org.project.exception.DaoException;
import org.project.model.Client;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcClientDao implements ClientDao{


    private final JdbcTemplate jdbcTemplate;

    public JdbcClientDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Client getClientById(int clientId) {
        Client client = null;
        String sql = "SELECT * FROM client WHERE id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, clientId);
            if (results.next()) {
                client = mapRowToClient(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return client;
    }

    @Override
    public List<Client> getClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Client client = mapRowToClient(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return clients;
    }

    @Override
    public Client newClient(Client client) {
        Client newClient = null;
        String sql = "INSERT INTO client(first_name, last_name, email) VALUES (?, ?, ?) " +
                "RETURNING id;";
        try {
            int newClientId = jdbcTemplate.queryForObject(sql, int.class, client.getFirstName(), client.getLastName(), client.getEmail());
            newClient = getClientById(newClientId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newClient;
    }

    private Client mapRowToClient (SqlRowSet rowSet) {
        Client client = new Client();
        client.setId(rowSet.getInt("id"));
        client.setFirstName(rowSet.getString("first_name"));
        client.setLastName(rowSet.getString("last_name"));
        client.setEmail(rowSet.getString("email"));
        return client;
    }
}
