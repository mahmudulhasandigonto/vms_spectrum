package com.spectrum.spectrum_vms.serviceImplimentation;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.spectrum.spectrum_vms.entity.Driver;
import com.spectrum.spectrum_vms.repository.DriverRepository;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DriverServiceImpl.class})
@ExtendWith(SpringExtension.class)
class DriverServiceImplTest {
    @MockBean
    private DriverRepository driverRepository;

    @Autowired
    private DriverServiceImpl driverServiceImpl;

    /**
     * Method under test: {@link DriverServiceImpl#save(Driver)}
     */
    @Test
    void testSave() {
        Driver driver = new Driver();
        when(driverRepository.save((Driver) any())).thenReturn(driver);
        assertSame(driver, driverServiceImpl.save(new Driver()));
        verify(driverRepository).save((Driver) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#save(Driver)}
     */
    @Test
    void testSave2() {
        when(driverRepository.save((Driver) any())).thenThrow(new InvalidDnDOperationException());
        assertThrows(InvalidDnDOperationException.class, () -> driverServiceImpl.save(new Driver()));
        verify(driverRepository).save((Driver) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#update(Driver)}
     */
    @Test
    void testUpdate() throws Exception {
        assertThrows(InvalidDnDOperationException.class, () -> driverServiceImpl.update(new Driver()));
    }

    /**
     * Method under test: {@link DriverServiceImpl#update(Driver)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate2() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.spectrum.spectrum_vms.entity.Driver.hasId()" because "driver" is null
        //       at com.spectrum.spectrum_vms.serviceImplimentation.DriverServiceImpl.update(DriverServiceImpl.java:28)
        //   See https://diff.blue/R013 to resolve this issue.

        driverServiceImpl.update(null);
    }

    /**
     * Method under test: {@link DriverServiceImpl#update(Driver)}
     */
    @Test
    void testUpdate3() throws Exception {
        Driver driver = new Driver();
        when(driverRepository.save((Driver) any())).thenReturn(driver);
        Driver driver1 = mock(Driver.class);
        when(driver1.hasId()).thenReturn(true);
        assertSame(driver, driverServiceImpl.update(driver1));
        verify(driverRepository).save((Driver) any());
        verify(driver1).hasId();
    }

    /**
     * Method under test: {@link DriverServiceImpl#update(Driver)}
     */
    @Test
    void testUpdate4() throws Exception {
        when(driverRepository.save((Driver) any())).thenThrow(new InvalidDnDOperationException());
        Driver driver = mock(Driver.class);
        when(driver.hasId()).thenReturn(true);
        assertThrows(InvalidDnDOperationException.class, () -> driverServiceImpl.update(driver));
        verify(driverRepository).save((Driver) any());
        verify(driver).hasId();
    }

    /**
     * Method under test: {@link DriverServiceImpl#deleteByIds(Long[])}
     */
    @Test
    void testDeleteByIds() {
        doNothing().when(driverRepository).deleteAllById((Iterable<Long>) any());
        driverServiceImpl.deleteByIds(new Long[]{1L});
        verify(driverRepository).deleteAllById((Iterable<Long>) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#deleteByIds(Long[])}
     */
    @Test
    void testDeleteByIds2() {
        doThrow(new InvalidDnDOperationException()).when(driverRepository).deleteAllById((Iterable<Long>) any());
        assertThrows(InvalidDnDOperationException.class, () -> driverServiceImpl.deleteByIds(new Long[]{1L}));
        verify(driverRepository).deleteAllById((Iterable<Long>) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#getDataById(Long)}
     */
    @Test
    void testGetDataById() {
        Driver driver = new Driver();
        when(driverRepository.findById((Long) any())).thenReturn(Optional.of(driver));
        assertSame(driver, driverServiceImpl.getDataById(1L));
        verify(driverRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#getDataById(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetDataById2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.spectrum.spectrum_vms.serviceImplimentation.DriverServiceImpl.getDataById(DriverServiceImpl.java:42)
        //   See https://diff.blue/R013 to resolve this issue.

        when(driverRepository.findById((Long) any())).thenReturn(Optional.empty());
        driverServiceImpl.getDataById(1L);
    }

    /**
     * Method under test: {@link DriverServiceImpl#getDataById(Long)}
     */
    @Test
    void testGetDataById3() {
        when(driverRepository.findById((Long) any())).thenThrow(new InvalidDnDOperationException());
        assertThrows(InvalidDnDOperationException.class, () -> driverServiceImpl.getDataById(1L));
        verify(driverRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#getData()}
     */
    @Test
    void testGetData() {
        ArrayList<Driver> driverList = new ArrayList<>();
        when(driverRepository.findAll()).thenReturn(driverList);
        List<Driver> actualData = driverServiceImpl.getData();
        assertSame(driverList, actualData);
        assertTrue(actualData.isEmpty());
        verify(driverRepository).findAll();
    }

    /**
     * Method under test: {@link DriverServiceImpl#getData()}
     */
    @Test
    void testGetData2() {
        when(driverRepository.findAll()).thenThrow(new InvalidDnDOperationException());
        assertThrows(InvalidDnDOperationException.class, () -> driverServiceImpl.getData());
        verify(driverRepository).findAll();
    }

    /**
     * Method under test: {@link DriverServiceImpl#findByIsAvailable(Boolean)}
     */
    @Test
    void testFindByIsAvailable() {
        ArrayList<Driver> driverList = new ArrayList<>();
        when(driverRepository.findByIsAvailable((Boolean) any())).thenReturn(driverList);
        List<Driver> actualFindByIsAvailableResult = driverServiceImpl.findByIsAvailable(true);
        assertSame(driverList, actualFindByIsAvailableResult);
        assertTrue(actualFindByIsAvailableResult.isEmpty());
        verify(driverRepository).findByIsAvailable((Boolean) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#findByIsAvailable(Boolean)}
     */
    @Test
    void testFindByIsAvailable2() {
        when(driverRepository.findByIsAvailable((Boolean) any())).thenThrow(new InvalidDnDOperationException());
        assertThrows(InvalidDnDOperationException.class, () -> driverServiceImpl.findByIsAvailable(true));
        verify(driverRepository).findByIsAvailable((Boolean) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#findByProblemIsNotNull()}
     */
    @Test
    void testFindByProblemIsNotNull() {
        ArrayList<Driver> driverList = new ArrayList<>();
        when(driverRepository.findByProblemIsNotNull()).thenReturn(driverList);
        List<Driver> actualFindByProblemIsNotNullResult = driverServiceImpl.findByProblemIsNotNull();
        assertSame(driverList, actualFindByProblemIsNotNullResult);
        assertTrue(actualFindByProblemIsNotNullResult.isEmpty());
        verify(driverRepository).findByProblemIsNotNull();
    }

    /**
     * Method under test: {@link DriverServiceImpl#findByProblemIsNotNull()}
     */
    @Test
    void testFindByProblemIsNotNull2() {
        when(driverRepository.findByProblemIsNotNull()).thenThrow(new InvalidDnDOperationException());
        assertThrows(InvalidDnDOperationException.class, () -> driverServiceImpl.findByProblemIsNotNull());
        verify(driverRepository).findByProblemIsNotNull();
    }

    /**
     * Method under test: {@link DriverServiceImpl#findByContactNumber(String)}
     */
    @Test
    void testFindByContactNumber() {
        Driver driver = new Driver();
        when(driverRepository.findByContactNumber((String) any())).thenReturn(driver);
        assertSame(driver, driverServiceImpl.findByContactNumber("42"));
        verify(driverRepository).findByContactNumber((String) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#findByContactNumber(String)}
     */
    @Test
    void testFindByContactNumber2() {
        when(driverRepository.findByContactNumber((String) any())).thenThrow(new InvalidDnDOperationException());
        assertThrows(InvalidDnDOperationException.class, () -> driverServiceImpl.findByContactNumber("42"));
        verify(driverRepository).findByContactNumber((String) any());
    }
}

