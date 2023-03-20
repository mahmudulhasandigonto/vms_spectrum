package com.spectrum.spectrum_vms.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.spectrum.spectrum_vms.entity.VehicleRequisition;
import com.spectrum.spectrum_vms.enums.RequestStatus;
import com.spectrum.spectrum_vms.error.DeleteRequestException;
import com.spectrum.spectrum_vms.error.VehicleRequestNotFoundException;
import com.spectrum.spectrum_vms.repository.VehicleRequisitionRepository;
import com.spectrum.spectrum_vms.service.VehicleRequisitionService;
import com.spectrum.spectrum_vms.serviceImplimentation.VehicleRequisitionServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {VehicleRequisitionController.class})
@ExtendWith(SpringExtension.class)
class VehicleRequisitionControllerTest {
    @Autowired
    private VehicleRequisitionController vehicleRequisitionController;

    @MockBean
    private VehicleRequisitionService vehicleRequisitionService;

    /**
     * Method under test: {@link VehicleRequisitionController#save(VehicleRequisition)}
     */
    @Test
    void testSave() {
        when(vehicleRequisitionService.save((VehicleRequisition) any())).thenReturn(new VehicleRequisition());
        ResponseEntity<VehicleRequisition> actualSaveResult = vehicleRequisitionController.save(new VehicleRequisition());
        assertTrue(actualSaveResult.hasBody());
        assertTrue(actualSaveResult.getHeaders().isEmpty());
        assertEquals(200, actualSaveResult.getStatusCodeValue());
        verify(vehicleRequisitionService).save((VehicleRequisition) any());
    }

    /**
     * Method under test: {@link VehicleRequisitionController#update(VehicleRequisition)}
     */
    @Test
    void testUpdate() throws Exception {
        when(vehicleRequisitionService.update((VehicleRequisition) any())).thenReturn(new VehicleRequisition());
        ResponseEntity<String> actualUpdateResult = vehicleRequisitionController.update(new VehicleRequisition());
        assertEquals("Vehicle Request information has been updated successfully", actualUpdateResult.getBody());
        assertEquals(200, actualUpdateResult.getStatusCodeValue());
        assertTrue(actualUpdateResult.getHeaders().isEmpty());
        verify(vehicleRequisitionService).update((VehicleRequisition) any());
    }

    /**
     * Method under test: {@link VehicleRequisitionController#update(VehicleRequisition)}
     */
    @Test
    void testUpdate2() throws Exception {
        when(vehicleRequisitionService.update((VehicleRequisition) any())).thenThrow(new Exception());
        ResponseEntity<String> actualUpdateResult = vehicleRequisitionController.update(new VehicleRequisition());
        assertNull(actualUpdateResult.getBody());
        assertEquals(422, actualUpdateResult.getStatusCodeValue());
        assertTrue(actualUpdateResult.getHeaders().isEmpty());
        verify(vehicleRequisitionService).update((VehicleRequisition) any());
    }

    /**
     * Method under test: {@link VehicleRequisitionController#deleteByIds(Long[])}
     */
    @Test
    void testDeleteByIds() throws DeleteRequestException {
        doNothing().when(vehicleRequisitionService).deleteByIds((Long[]) any());
        ResponseEntity<String> actualDeleteByIdsResult = vehicleRequisitionController.deleteByIds(1L);
        assertEquals("ID [1] has been deleted successfully", actualDeleteByIdsResult.getBody());
        assertEquals(200, actualDeleteByIdsResult.getStatusCodeValue());
        assertTrue(actualDeleteByIdsResult.getHeaders().isEmpty());
        verify(vehicleRequisitionService).deleteByIds((Long[]) any());
    }

    /**
     * Method under test: {@link VehicleRequisitionController#getDataById(Long)}
     */
    @Test
    void testGetDataById() throws VehicleRequestNotFoundException {
        when(vehicleRequisitionService.getDataById((Long) any())).thenReturn(new VehicleRequisition());
        ResponseEntity<VehicleRequisition> actualDataById = vehicleRequisitionController.getDataById(1L);
        assertTrue(actualDataById.hasBody());
        assertTrue(actualDataById.getHeaders().isEmpty());
        assertEquals(200, actualDataById.getStatusCodeValue());
        verify(vehicleRequisitionService).getDataById((Long) any());
    }

    /**
     * Method under test: {@link VehicleRequisitionController#getDataByUserId(Long)}
     */
    @Test
    void testGetDataByUserId() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        VehicleRequisitionRepository vehicleRequisitionRepository = mock(VehicleRequisitionRepository.class);
        when(vehicleRequisitionRepository.findByUserId((Long) any())).thenReturn(new ArrayList<>());
        ResponseEntity<List<VehicleRequisition>> actualDataByUserId = (new VehicleRequisitionController(
                new VehicleRequisitionServiceImpl(vehicleRequisitionRepository))).getDataByUserId(1L);
        assertTrue(actualDataByUserId.hasBody());
        assertEquals(200, actualDataByUserId.getStatusCodeValue());
        assertTrue(actualDataByUserId.getHeaders().isEmpty());
        verify(vehicleRequisitionRepository).findByUserId((Long) any());
    }

    /**
     * Method under test: {@link VehicleRequisitionController#getDataByUserId(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetDataByUserId2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.spectrum.spectrum_vms.service.VehicleRequisitionService.findByUserId(java.lang.Long)" because "this.vehicleRequestService" is null
        //       at com.spectrum.spectrum_vms.controller.VehicleRequisitionController.getDataByUserId(VehicleRequisitionController.java:72)
        //   See https://diff.blue/R013 to resolve this issue.

        (new VehicleRequisitionController(null)).getDataByUserId(1L);
    }

    /**
     * Method under test: {@link VehicleRequisitionController#getData()}
     */
    @Test
    void testGetData() {
        ArrayList<VehicleRequisition> vehicleRequisitionList = new ArrayList<>();
        when(vehicleRequisitionService.getData()).thenReturn(vehicleRequisitionList);
        List<VehicleRequisition> actualData = vehicleRequisitionController.getData();
        assertSame(vehicleRequisitionList, actualData);
        assertTrue(actualData.isEmpty());
        verify(vehicleRequisitionService).getData();
    }

    /**
     * Method under test: {@link VehicleRequisitionController#getDataByRequestStatus(RequestStatus)}
     */
    @Test
    void testGetDataByRequestStatus() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        VehicleRequisitionRepository vehicleRequisitionRepository = mock(VehicleRequisitionRepository.class);
        when(vehicleRequisitionRepository.findByRequestStatus((RequestStatus) any())).thenReturn(new ArrayList<>());
        ResponseEntity<List<VehicleRequisition>> actualDataByRequestStatus = (new VehicleRequisitionController(
                new VehicleRequisitionServiceImpl(vehicleRequisitionRepository))).getDataByRequestStatus(RequestStatus.PENDING);
        assertTrue(actualDataByRequestStatus.hasBody());
        assertEquals(200, actualDataByRequestStatus.getStatusCodeValue());
        assertTrue(actualDataByRequestStatus.getHeaders().isEmpty());
        verify(vehicleRequisitionRepository).findByRequestStatus((RequestStatus) any());
    }

    /**
     * Method under test: {@link VehicleRequisitionController#getDataByRequestStatus(RequestStatus)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetDataByRequestStatus2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.spectrum.spectrum_vms.service.VehicleRequisitionService.getDataByRequestStatus(com.spectrum.spectrum_vms.enums.RequestStatus)" because "this.vehicleRequestService" is null
        //       at com.spectrum.spectrum_vms.controller.VehicleRequisitionController.getDataByRequestStatus(VehicleRequisitionController.java:85)
        //   See https://diff.blue/R013 to resolve this issue.

        (new VehicleRequisitionController(null)).getDataByRequestStatus(RequestStatus.PENDING);
    }

    /**
     * Method under test: {@link VehicleRequisitionController#getDataByRequestStatus(RequestStatus)}
     */
    @Test
    void testGetDataByRequestStatus3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        VehicleRequisitionRepository vehicleRequisitionRepository = mock(VehicleRequisitionRepository.class);
        when(vehicleRequisitionRepository.findByRequestStatus((RequestStatus) any())).thenReturn(new ArrayList<>());
        ResponseEntity<List<VehicleRequisition>> actualDataByRequestStatus = (new VehicleRequisitionController(
                new VehicleRequisitionServiceImpl(vehicleRequisitionRepository)))
                .getDataByRequestStatus(RequestStatus.ACCEPTED);
        assertTrue(actualDataByRequestStatus.hasBody());
        assertEquals(200, actualDataByRequestStatus.getStatusCodeValue());
        assertTrue(actualDataByRequestStatus.getHeaders().isEmpty());
        verify(vehicleRequisitionRepository).findByRequestStatus((RequestStatus) any());
    }

    /**
     * Method under test: {@link VehicleRequisitionController#getDataByRequestStatus(RequestStatus)}
     */
    @Test
    void testGetDataByRequestStatus4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        VehicleRequisitionRepository vehicleRequisitionRepository = mock(VehicleRequisitionRepository.class);
        when(vehicleRequisitionRepository.findByRequestStatus((RequestStatus) any())).thenReturn(new ArrayList<>());
        ResponseEntity<List<VehicleRequisition>> actualDataByRequestStatus = (new VehicleRequisitionController(
                new VehicleRequisitionServiceImpl(vehicleRequisitionRepository)))
                .getDataByRequestStatus(RequestStatus.REJECTED);
        assertTrue(actualDataByRequestStatus.hasBody());
        assertEquals(200, actualDataByRequestStatus.getStatusCodeValue());
        assertTrue(actualDataByRequestStatus.getHeaders().isEmpty());
        verify(vehicleRequisitionRepository).findByRequestStatus((RequestStatus) any());
    }

    /**
     * Method under test: {@link VehicleRequisitionController#getDataByRequestStatus(RequestStatus)}
     */
    @Test
    void testGetDataByRequestStatus5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        VehicleRequisitionRepository vehicleRequisitionRepository = mock(VehicleRequisitionRepository.class);
        when(vehicleRequisitionRepository.findByRequestStatus((RequestStatus) any())).thenReturn(new ArrayList<>());
        ResponseEntity<List<VehicleRequisition>> actualDataByRequestStatus = (new VehicleRequisitionController(
                new VehicleRequisitionServiceImpl(vehicleRequisitionRepository)))
                .getDataByRequestStatus(RequestStatus.CONFIRMED);
        assertTrue(actualDataByRequestStatus.hasBody());
        assertEquals(200, actualDataByRequestStatus.getStatusCodeValue());
        assertTrue(actualDataByRequestStatus.getHeaders().isEmpty());
        verify(vehicleRequisitionRepository).findByRequestStatus((RequestStatus) any());
    }
}

