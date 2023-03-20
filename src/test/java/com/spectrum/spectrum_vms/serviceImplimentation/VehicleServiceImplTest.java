package com.spectrum.spectrum_vms.serviceImplimentation;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.spectrum.spectrum_vms.entity.Vehicle;
import com.spectrum.spectrum_vms.repository.VehicleRepository;

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

@ContextConfiguration(classes = {VehicleServiceImpl.class})
@ExtendWith(SpringExtension.class)
class VehicleServiceImplTest {
    @MockBean
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleServiceImpl vehicleServiceImpl;

    /**
     * Method under test: {@link VehicleServiceImpl#save(Vehicle)}
     */
    @Test
    void testSave() {
        Vehicle vehicle = new Vehicle();
        when(vehicleRepository.save((Vehicle) any())).thenReturn(vehicle);
        assertSame(vehicle, vehicleServiceImpl.save(new Vehicle()));
        verify(vehicleRepository).save((Vehicle) any());
    }

    /**
     * Method under test: {@link VehicleServiceImpl#save(Vehicle)}
     */
    @Test
    void testSave2() {
        when(vehicleRepository.save((Vehicle) any())).thenThrow(new InvalidDnDOperationException());
        assertThrows(InvalidDnDOperationException.class, () -> vehicleServiceImpl.save(new Vehicle()));
        verify(vehicleRepository).save((Vehicle) any());
    }

    /**
     * Method under test: {@link VehicleServiceImpl#update(Vehicle)}
     */
    @Test
    void testUpdate() throws Exception {
        assertThrows(InvalidDnDOperationException.class, () -> vehicleServiceImpl.update(new Vehicle()));
    }

    /**
     * Method under test: {@link VehicleServiceImpl#update(Vehicle)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate2() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.spectrum.spectrum_vms.entity.Vehicle.hasId()" because "vehicle" is null
        //       at com.spectrum.spectrum_vms.serviceImplimentation.VehicleServiceImpl.update(VehicleServiceImpl.java:26)
        //   See https://diff.blue/R013 to resolve this issue.

        vehicleServiceImpl.update(null);
    }

    /**
     * Method under test: {@link VehicleServiceImpl#update(Vehicle)}
     */
    @Test
    void testUpdate3() throws Exception {
        Vehicle vehicle = new Vehicle();
        when(vehicleRepository.save((Vehicle) any())).thenReturn(vehicle);
        Vehicle vehicle1 = mock(Vehicle.class);
        when(vehicle1.hasId()).thenReturn(true);
        assertSame(vehicle, vehicleServiceImpl.update(vehicle1));
        verify(vehicleRepository).save((Vehicle) any());
        verify(vehicle1).hasId();
    }

    /**
     * Method under test: {@link VehicleServiceImpl#update(Vehicle)}
     */
    @Test
    void testUpdate4() throws Exception {
        when(vehicleRepository.save((Vehicle) any())).thenThrow(new InvalidDnDOperationException());
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.hasId()).thenReturn(true);
        assertThrows(InvalidDnDOperationException.class, () -> vehicleServiceImpl.update(vehicle));
        verify(vehicleRepository).save((Vehicle) any());
        verify(vehicle).hasId();
    }

    /**
     * Method under test: {@link VehicleServiceImpl#deleteByIds(Long[])}
     */
    @Test
    void testDeleteByIds() {
        doNothing().when(vehicleRepository).deleteAllById((Iterable<Long>) any());
        vehicleServiceImpl.deleteByIds(new Long[]{1L});
        verify(vehicleRepository).deleteAllById((Iterable<Long>) any());
    }

    /**
     * Method under test: {@link VehicleServiceImpl#deleteByIds(Long[])}
     */
    @Test
    void testDeleteByIds2() {
        doThrow(new InvalidDnDOperationException()).when(vehicleRepository).deleteAllById((Iterable<Long>) any());
        assertThrows(InvalidDnDOperationException.class, () -> vehicleServiceImpl.deleteByIds(new Long[]{1L}));
        verify(vehicleRepository).deleteAllById((Iterable<Long>) any());
    }

    /**
     * Method under test: {@link VehicleServiceImpl#getDataById(Long)}
     */
    @Test
    void testGetDataById() {
        Vehicle vehicle = new Vehicle();
        when(vehicleRepository.findById((Long) any())).thenReturn(Optional.of(vehicle));
        assertSame(vehicle, vehicleServiceImpl.getDataById(1L));
        verify(vehicleRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link VehicleServiceImpl#getDataById(Long)}
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
        //       at com.spectrum.spectrum_vms.serviceImplimentation.VehicleServiceImpl.getDataById(VehicleServiceImpl.java:40)
        //   See https://diff.blue/R013 to resolve this issue.

        when(vehicleRepository.findById((Long) any())).thenReturn(Optional.empty());
        vehicleServiceImpl.getDataById(1L);
    }

    /**
     * Method under test: {@link VehicleServiceImpl#getDataById(Long)}
     */
    @Test
    void testGetDataById3() {
        when(vehicleRepository.findById((Long) any())).thenThrow(new InvalidDnDOperationException());
        assertThrows(InvalidDnDOperationException.class, () -> vehicleServiceImpl.getDataById(1L));
        verify(vehicleRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link VehicleServiceImpl#getData()}
     */
    @Test
    void testGetData() {
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        when(vehicleRepository.findAll()).thenReturn(vehicleList);
        List<Vehicle> actualData = vehicleServiceImpl.getData();
        assertSame(vehicleList, actualData);
        assertTrue(actualData.isEmpty());
        verify(vehicleRepository).findAll();
    }

    /**
     * Method under test: {@link VehicleServiceImpl#getData()}
     */
    @Test
    void testGetData2() {
        when(vehicleRepository.findAll()).thenThrow(new InvalidDnDOperationException());
        assertThrows(InvalidDnDOperationException.class, () -> vehicleServiceImpl.getData());
        verify(vehicleRepository).findAll();
    }

    /**
     * Method under test: {@link VehicleServiceImpl#findByIsAvailable(boolean)}
     */
    @Test
    void testFindByIsAvailable() {
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        when(vehicleRepository.findByIsAvailable(anyBoolean())).thenReturn(vehicleList);
        List<Vehicle> actualFindByIsAvailableResult = vehicleServiceImpl.findByIsAvailable(true);
        assertSame(vehicleList, actualFindByIsAvailableResult);
        assertTrue(actualFindByIsAvailableResult.isEmpty());
        verify(vehicleRepository).findByIsAvailable(anyBoolean());
    }

    /**
     * Method under test: {@link VehicleServiceImpl#findByIsAvailable(boolean)}
     */
    @Test
    void testFindByIsAvailable2() {
        when(vehicleRepository.findByIsAvailable(anyBoolean())).thenThrow(new InvalidDnDOperationException());
        assertThrows(InvalidDnDOperationException.class, () -> vehicleServiceImpl.findByIsAvailable(true));
        verify(vehicleRepository).findByIsAvailable(anyBoolean());
    }

    /**
     * Method under test: {@link VehicleServiceImpl#findByProblemIsNotNull()}
     */
    @Test
    void testFindByProblemIsNotNull() {
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        when(vehicleRepository.findByProblemIsNotNull()).thenReturn(vehicleList);
        List<Vehicle> actualFindByProblemIsNotNullResult = vehicleServiceImpl.findByProblemIsNotNull();
        assertSame(vehicleList, actualFindByProblemIsNotNullResult);
        assertTrue(actualFindByProblemIsNotNullResult.isEmpty());
        verify(vehicleRepository).findByProblemIsNotNull();
    }

    /**
     * Method under test: {@link VehicleServiceImpl#findByProblemIsNotNull()}
     */
    @Test
    void testFindByProblemIsNotNull2() {
        when(vehicleRepository.findByProblemIsNotNull()).thenThrow(new InvalidDnDOperationException());
        assertThrows(InvalidDnDOperationException.class, () -> vehicleServiceImpl.findByProblemIsNotNull());
        verify(vehicleRepository).findByProblemIsNotNull();
    }
}

