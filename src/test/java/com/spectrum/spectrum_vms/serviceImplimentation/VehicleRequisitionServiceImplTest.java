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

import com.spectrum.spectrum_vms.entity.VehicleRequisition;
import com.spectrum.spectrum_vms.enums.RequestStatus;
import com.spectrum.spectrum_vms.repository.VehicleRequisitionRepository;

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

@ContextConfiguration(classes = {VehicleRequisitionServiceImpl.class})
@ExtendWith(SpringExtension.class)
class VehicleRequisitionServiceImplTest {
    @MockBean
    private VehicleRequisitionRepository vehicleRequisitionRepository;

    @Autowired
    private VehicleRequisitionServiceImpl vehicleRequisitionServiceImpl;

    /**
     * Method under test: {@link VehicleRequisitionServiceImpl#save(VehicleRequisition)}
     */
    @Test
    void testSave() {
        VehicleRequisition vehicleRequisition = new VehicleRequisition();
        when(vehicleRequisitionRepository.save((VehicleRequisition) any())).thenReturn(vehicleRequisition);
        assertSame(vehicleRequisition, vehicleRequisitionServiceImpl.save(new VehicleRequisition()));
        verify(vehicleRequisitionRepository).save((VehicleRequisition) any());
    }

    /**
     * Method under test: {@link VehicleRequisitionServiceImpl#save(VehicleRequisition)}
     */
    @Test
    void testSave2() {
        when(vehicleRequisitionRepository.save((VehicleRequisition) any())).thenThrow(new InvalidDnDOperationException());
        assertThrows(InvalidDnDOperationException.class,
                () -> vehicleRequisitionServiceImpl.save(new VehicleRequisition()));
        verify(vehicleRequisitionRepository).save((VehicleRequisition) any());
    }

    /**
     * Method under test: {@link VehicleRequisitionServiceImpl#update(VehicleRequisition)}
     */
    @Test
    void testUpdate() throws Exception {
        assertThrows(InvalidDnDOperationException.class,
                () -> vehicleRequisitionServiceImpl.update(new VehicleRequisition()));
    }

    /**
     * Method under test: {@link VehicleRequisitionServiceImpl#update(VehicleRequisition)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate2() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.spectrum.spectrum_vms.entity.VehicleRequisition.hasId()" because "vehicleRequest" is null
        //       at com.spectrum.spectrum_vms.serviceImplimentation.VehicleRequisitionServiceImpl.update(VehicleRequisitionServiceImpl.java:26)
        //   See https://diff.blue/R013 to resolve this issue.

        vehicleRequisitionServiceImpl.update(null);
    }

    /**
     * Method under test: {@link VehicleRequisitionServiceImpl#update(VehicleRequisition)}
     */
    @Test
    void testUpdate3() throws Exception {
        VehicleRequisition vehicleRequisition = new VehicleRequisition();
        when(vehicleRequisitionRepository.save((VehicleRequisition) any())).thenReturn(vehicleRequisition);
        VehicleRequisition vehicleRequisition1 = mock(VehicleRequisition.class);
        when(vehicleRequisition1.hasId()).thenReturn(true);
        assertSame(vehicleRequisition, vehicleRequisitionServiceImpl.update(vehicleRequisition1));
        verify(vehicleRequisitionRepository).save((VehicleRequisition) any());
        verify(vehicleRequisition1).hasId();
    }

    /**
     * Method under test: {@link VehicleRequisitionServiceImpl#update(VehicleRequisition)}
     */
    @Test
    void testUpdate4() throws Exception {
        when(vehicleRequisitionRepository.save((VehicleRequisition) any())).thenThrow(new InvalidDnDOperationException());
        VehicleRequisition vehicleRequisition = mock(VehicleRequisition.class);
        when(vehicleRequisition.hasId()).thenReturn(true);
        assertThrows(InvalidDnDOperationException.class, () -> vehicleRequisitionServiceImpl.update(vehicleRequisition));
        verify(vehicleRequisitionRepository).save((VehicleRequisition) any());
        verify(vehicleRequisition).hasId();
    }

    /**
     * Method under test: {@link VehicleRequisitionServiceImpl#deleteByIds(Long[])}
     */
    @Test
    void testDeleteByIds() {
        doNothing().when(vehicleRequisitionRepository).deleteAllById((Iterable<Long>) any());
        vehicleRequisitionServiceImpl.deleteByIds(new Long[]{1L});
        verify(vehicleRequisitionRepository).deleteAllById((Iterable<Long>) any());
    }

    /**
     * Method under test: {@link VehicleRequisitionServiceImpl#deleteByIds(Long[])}
     */
    @Test
    void testDeleteByIds2() {
        doThrow(new InvalidDnDOperationException()).when(vehicleRequisitionRepository)
                .deleteAllById((Iterable<Long>) any());
        assertThrows(InvalidDnDOperationException.class, () -> vehicleRequisitionServiceImpl.deleteByIds(new Long[]{1L}));
        verify(vehicleRequisitionRepository).deleteAllById((Iterable<Long>) any());
    }

    /**
     * Method under test: {@link VehicleRequisitionServiceImpl#getDataById(Long)}
     */
    @Test
    void testGetDataById() {
        VehicleRequisition vehicleRequisition = new VehicleRequisition();
        when(vehicleRequisitionRepository.findById((Long) any())).thenReturn(Optional.of(vehicleRequisition));
        assertSame(vehicleRequisition, vehicleRequisitionServiceImpl.getDataById(1L));
        verify(vehicleRequisitionRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link VehicleRequisitionServiceImpl#getDataById(Long)}
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
        //       at com.spectrum.spectrum_vms.serviceImplimentation.VehicleRequisitionServiceImpl.getDataById(VehicleRequisitionServiceImpl.java:40)
        //   See https://diff.blue/R013 to resolve this issue.

        when(vehicleRequisitionRepository.findById((Long) any())).thenReturn(Optional.empty());
        vehicleRequisitionServiceImpl.getDataById(1L);
    }

    /**
     * Method under test: {@link VehicleRequisitionServiceImpl#getDataById(Long)}
     */
    @Test
    void testGetDataById3() {
        when(vehicleRequisitionRepository.findById((Long) any())).thenThrow(new InvalidDnDOperationException());
        assertThrows(InvalidDnDOperationException.class, () -> vehicleRequisitionServiceImpl.getDataById(1L));
        verify(vehicleRequisitionRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link VehicleRequisitionServiceImpl#getData()}
     */
    @Test
    void testGetData() {
        ArrayList<VehicleRequisition> vehicleRequisitionList = new ArrayList<>();
        when(vehicleRequisitionRepository.findAll()).thenReturn(vehicleRequisitionList);
        List<VehicleRequisition> actualData = vehicleRequisitionServiceImpl.getData();
        assertSame(vehicleRequisitionList, actualData);
        assertTrue(actualData.isEmpty());
        verify(vehicleRequisitionRepository).findAll();
    }

    /**
     * Method under test: {@link VehicleRequisitionServiceImpl#getData()}
     */
    @Test
    void testGetData2() {
        when(vehicleRequisitionRepository.findAll()).thenThrow(new InvalidDnDOperationException());
        assertThrows(InvalidDnDOperationException.class, () -> vehicleRequisitionServiceImpl.getData());
        verify(vehicleRequisitionRepository).findAll();
    }

    /**
     * Method under test: {@link VehicleRequisitionServiceImpl#findByUserId(Long)}
     */
    @Test
    void testFindByUserId() {
        ArrayList<VehicleRequisition> vehicleRequisitionList = new ArrayList<>();
        when(vehicleRequisitionRepository.findByUserId((Long) any())).thenReturn(vehicleRequisitionList);
        List<VehicleRequisition> actualFindByUserIdResult = vehicleRequisitionServiceImpl.findByUserId(1L);
        assertSame(vehicleRequisitionList, actualFindByUserIdResult);
        assertTrue(actualFindByUserIdResult.isEmpty());
        verify(vehicleRequisitionRepository).findByUserId((Long) any());
    }

    /**
     * Method under test: {@link VehicleRequisitionServiceImpl#findByUserId(Long)}
     */
    @Test
    void testFindByUserId2() {
        when(vehicleRequisitionRepository.findByUserId((Long) any())).thenThrow(new InvalidDnDOperationException());
        assertThrows(InvalidDnDOperationException.class, () -> vehicleRequisitionServiceImpl.findByUserId(1L));
        verify(vehicleRequisitionRepository).findByUserId((Long) any());
    }

    /**
     * Method under test: {@link VehicleRequisitionServiceImpl#getDataByRequestStatus(RequestStatus)}
     */
    @Test
    void testGetDataByRequestStatus() {
        ArrayList<VehicleRequisition> vehicleRequisitionList = new ArrayList<>();
        when(vehicleRequisitionRepository.findByRequestStatus((RequestStatus) any())).thenReturn(vehicleRequisitionList);
        List<VehicleRequisition> actualDataByRequestStatus = vehicleRequisitionServiceImpl
                .getDataByRequestStatus(RequestStatus.PENDING);
        assertSame(vehicleRequisitionList, actualDataByRequestStatus);
        assertTrue(actualDataByRequestStatus.isEmpty());
        verify(vehicleRequisitionRepository).findByRequestStatus((RequestStatus) any());
    }

    /**
     * Method under test: {@link VehicleRequisitionServiceImpl#getDataByRequestStatus(RequestStatus)}
     */
    @Test
    void testGetDataByRequestStatus2() {
        ArrayList<VehicleRequisition> vehicleRequisitionList = new ArrayList<>();
        when(vehicleRequisitionRepository.findByRequestStatus((RequestStatus) any())).thenReturn(vehicleRequisitionList);
        List<VehicleRequisition> actualDataByRequestStatus = vehicleRequisitionServiceImpl
                .getDataByRequestStatus(RequestStatus.ACCEPTED);
        assertSame(vehicleRequisitionList, actualDataByRequestStatus);
        assertTrue(actualDataByRequestStatus.isEmpty());
        verify(vehicleRequisitionRepository).findByRequestStatus((RequestStatus) any());
    }

    /**
     * Method under test: {@link VehicleRequisitionServiceImpl#getDataByRequestStatus(RequestStatus)}
     */
    @Test
    void testGetDataByRequestStatus3() {
        ArrayList<VehicleRequisition> vehicleRequisitionList = new ArrayList<>();
        when(vehicleRequisitionRepository.findByRequestStatus((RequestStatus) any())).thenReturn(vehicleRequisitionList);
        List<VehicleRequisition> actualDataByRequestStatus = vehicleRequisitionServiceImpl
                .getDataByRequestStatus(RequestStatus.REJECTED);
        assertSame(vehicleRequisitionList, actualDataByRequestStatus);
        assertTrue(actualDataByRequestStatus.isEmpty());
        verify(vehicleRequisitionRepository).findByRequestStatus((RequestStatus) any());
    }

    /**
     * Method under test: {@link VehicleRequisitionServiceImpl#getDataByRequestStatus(RequestStatus)}
     */
    @Test
    void testGetDataByRequestStatus4() {
        ArrayList<VehicleRequisition> vehicleRequisitionList = new ArrayList<>();
        when(vehicleRequisitionRepository.findByRequestStatus((RequestStatus) any())).thenReturn(vehicleRequisitionList);
        List<VehicleRequisition> actualDataByRequestStatus = vehicleRequisitionServiceImpl
                .getDataByRequestStatus(RequestStatus.CONFIRMED);
        assertSame(vehicleRequisitionList, actualDataByRequestStatus);
        assertTrue(actualDataByRequestStatus.isEmpty());
        verify(vehicleRequisitionRepository).findByRequestStatus((RequestStatus) any());
    }

    /**
     * Method under test: {@link VehicleRequisitionServiceImpl#getDataByRequestStatus(RequestStatus)}
     */
    @Test
    void testGetDataByRequestStatus5() {
        when(vehicleRequisitionRepository.findByRequestStatus((RequestStatus) any()))
                .thenThrow(new InvalidDnDOperationException());
        assertThrows(InvalidDnDOperationException.class,
                () -> vehicleRequisitionServiceImpl.getDataByRequestStatus(RequestStatus.PENDING));
        verify(vehicleRequisitionRepository).findByRequestStatus((RequestStatus) any());
    }
}

