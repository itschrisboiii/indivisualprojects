package org.project.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.project.model.Service;

public class JdbcServicesDaoTests extends BaseDaoTest{

    private static final Service SERVICE_1 = mapValuesToService(1, "Hybrid", 70);

    private static final Service SERVICE_2 = mapValuesToService(2, "Cateye", 75);

    private static final Service SERVICE_3 = mapValuesToService(3, "Classic", 60);

    private JdbcServicesDao jdbcServicesDao;

    @Before
    public void setup() {
        jdbcServicesDao = new JdbcServicesDao(dataSource);
    }

    @Test
    public void getServiceById_returns_correct_service_for_id(){
        Service service = jdbcServicesDao.getServiceById(1);
        Assert.assertNotNull("getServiceById(1) returned null", service);
        assertServicesMatch("getServicesById(1) returned wrong or partial data", SERVICE_1, service);
    }

    private static Service mapValuesToService(int id, String serviceName, int price) {
        Service service = new Service();
        service.setId(id);
        service.setServiceName(serviceName);
        service.setPrice(price);
        return service;
    }

    private void assertServicesMatch(String message, Service expected, Service actual) {
        Assert.assertEquals(message, expected.getId(), actual.getId());
        Assert.assertEquals(message, expected.getServiceName(), actual.getServiceName());;
        Assert.assertEquals(message, expected.getPrice(), actual.getPrice());
    }
}
