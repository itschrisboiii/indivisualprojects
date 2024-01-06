package org.project.dao;

import org.project.model.Service;

import java.util.List;

public class JdbcServicesDao implements ServicesDao{
    @Override
    public Service getServiceById(int serviceId) {
        return null;
    }

    @Override
    public List<Service> getServices() {
        return null;
    }

    @Override
    public Service newService(Service service) {
        return null;
    }

    @Override
    public Service updateService(Service updatedService) {
        return null;
    }

    @Override
    public int deleteServiceById(int serviceId) {
        return 0;
    }
}
