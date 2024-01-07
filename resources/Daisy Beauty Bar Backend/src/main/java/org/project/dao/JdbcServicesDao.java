package org.project.dao;

import org.project.exception.DaoException;
import org.project.model.Service;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcServicesDao implements ServicesDao{

    private final JdbcTemplate jdbcTemplate;
    public JdbcServicesDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Service getServiceById(int serviceId) {
        Service service = null;
        String sql = "SELECT * FROM services WHERE id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, serviceId);
            if (results.next()) {
                service = mapRowToService(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return service;
    }

    @Override
    public List<Service> getServices() {
        List<Service> services = new ArrayList<>();
        String sql = "SELECT * FROM services WHERE id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Service service = mapRowToService(results);
                services.add(service);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return services;
    }

    @Override
    public Service newService(Service service) {
        Service newService = null;
        String sql = "INSERT INTO services(service_name, price) VALUES (?, ?);";
        try {
            int newServiceId = jdbcTemplate.queryForObject(sql,int.class, service.getServiceName(), service.getPrice());
            newService = getServiceById(newServiceId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newService;
    }

    @Override
    public Service updateService(Service updatedService) {
        Service service = null;
        String sql = "UPDATE services SET service_name=?, price=? WHERE id = ?;";
        try {
            jdbcTemplate.update(sql, updatedService.getServiceName(), updatedService.getPrice());
            service = getServiceById(updatedService.getId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return service;
    }

    @Override
    public int deleteServiceById(int serviceId) {
        int numberOfRows = 0;
        String sql = "DELETE FROM services WHERE id = ?;";

        try {
            numberOfRows = jdbcTemplate.update(sql, serviceId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }

    private Service mapRowToService(SqlRowSet rowSet) {
        Service service = new Service();
        service.setId(rowSet.getInt("id"));
        service.setServiceName(rowSet.getString("service_name"));
        service.setPrice(rowSet.getInt("price"));
        return service;
    }
}
