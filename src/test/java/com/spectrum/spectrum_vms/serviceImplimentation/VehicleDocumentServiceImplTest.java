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

import com.spectrum.spectrum_vms.entity.VehicleDocument;
import com.spectrum.spectrum_vms.repository.VehicleDocumentRepository;

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

@ContextConfiguration(classes = {VehicleDocumentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class VehicleDocumentServiceImplTest {
    @MockBean
    private VehicleDocumentRepository vehicleDocumentRepository;

    @Autowired
    private VehicleDocumentServiceImpl vehicleDocumentServiceImpl;

    /**
     * Method under test: {@link VehicleDocumentServiceImpl#save(VehicleDocument)}
     */
    @Test
    void testSave() {
        VehicleDocument vehicleDocument = new VehicleDocument();
        when(vehicleDocumentRepository.save((VehicleDocument) any())).thenReturn(vehicleDocument);
        assertSame(vehicleDocument, vehicleDocumentServiceImpl.save(new VehicleDocument()));
        verify(vehicleDocumentRepository).save((VehicleDocument) any());
    }

    /**
     * Method under test: {@link VehicleDocumentServiceImpl#save(VehicleDocument)}
     */
    @Test
    void testSave2() {
        when(vehicleDocumentRepository.save((VehicleDocument) any())).thenThrow(new InvalidDnDOperationException());
        assertThrows(InvalidDnDOperationException.class, () -> vehicleDocumentServiceImpl.save(new VehicleDocument()));
        verify(vehicleDocumentRepository).save((VehicleDocument) any());
    }

    /**
     * Method under test: {@link VehicleDocumentServiceImpl#update(VehicleDocument)}
     */
    @Test
    void testUpdate() throws Exception {
        assertThrows(InvalidDnDOperationException.class, () -> vehicleDocumentServiceImpl.update(new VehicleDocument()));
    }

    /**
     * Method under test: {@link VehicleDocumentServiceImpl#update(VehicleDocument)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate2() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.spectrum.spectrum_vms.entity.VehicleDocument.hasId()" because "vehicleDocument" is null
        //       at com.spectrum.spectrum_vms.serviceImplimentation.VehicleDocumentServiceImpl.update(VehicleDocumentServiceImpl.java:28)
        //   See https://diff.blue/R013 to resolve this issue.

        vehicleDocumentServiceImpl.update(null);
    }

    /**
     * Method under test: {@link VehicleDocumentServiceImpl#update(VehicleDocument)}
     */
    @Test
    void testUpdate3() throws Exception {
        VehicleDocument vehicleDocument = new VehicleDocument();
        when(vehicleDocumentRepository.save((VehicleDocument) any())).thenReturn(vehicleDocument);
        VehicleDocument vehicleDocument1 = mock(VehicleDocument.class);
        when(vehicleDocument1.hasId()).thenReturn(true);
        assertSame(vehicleDocument, vehicleDocumentServiceImpl.update(vehicleDocument1));
        verify(vehicleDocumentRepository).save((VehicleDocument) any());
        verify(vehicleDocument1).hasId();
    }

    /**
     * Method under test: {@link VehicleDocumentServiceImpl#update(VehicleDocument)}
     */
    @Test
    void testUpdate4() throws Exception {
        when(vehicleDocumentRepository.save((VehicleDocument) any())).thenThrow(new InvalidDnDOperationException());
        VehicleDocument vehicleDocument = mock(VehicleDocument.class);
        when(vehicleDocument.hasId()).thenReturn(true);
        assertThrows(InvalidDnDOperationException.class, () -> vehicleDocumentServiceImpl.update(vehicleDocument));
        verify(vehicleDocumentRepository).save((VehicleDocument) any());
        verify(vehicleDocument).hasId();
    }

    /**
     * Method under test: {@link VehicleDocumentServiceImpl#deleteByIds(Long[])}
     */
    @Test
    void testDeleteByIds() {
        doNothing().when(vehicleDocumentRepository).deleteAllById((Iterable<Long>) any());
        vehicleDocumentServiceImpl.deleteByIds(new Long[]{1L});
        verify(vehicleDocumentRepository).deleteAllById((Iterable<Long>) any());
    }

    /**
     * Method under test: {@link VehicleDocumentServiceImpl#deleteByIds(Long[])}
     */
    @Test
    void testDeleteByIds2() {
        doThrow(new InvalidDnDOperationException()).when(vehicleDocumentRepository).deleteAllById((Iterable<Long>) any());
        assertThrows(InvalidDnDOperationException.class, () -> vehicleDocumentServiceImpl.deleteByIds(new Long[]{1L}));
        verify(vehicleDocumentRepository).deleteAllById((Iterable<Long>) any());
    }

    /**
     * Method under test: {@link VehicleDocumentServiceImpl#getDataById(Long)}
     */
    @Test
    void testGetDataById() {
        VehicleDocument vehicleDocument = new VehicleDocument();
        when(vehicleDocumentRepository.findById((Long) any())).thenReturn(Optional.of(vehicleDocument));
        assertSame(vehicleDocument, vehicleDocumentServiceImpl.getDataById(1L));
        verify(vehicleDocumentRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link VehicleDocumentServiceImpl#getDataById(Long)}
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
        //       at com.spectrum.spectrum_vms.serviceImplimentation.VehicleDocumentServiceImpl.getDataById(VehicleDocumentServiceImpl.java:42)
        //   See https://diff.blue/R013 to resolve this issue.

        when(vehicleDocumentRepository.findById((Long) any())).thenReturn(Optional.empty());
        vehicleDocumentServiceImpl.getDataById(1L);
    }

    /**
     * Method under test: {@link VehicleDocumentServiceImpl#getDataById(Long)}
     */
    @Test
    void testGetDataById3() {
        when(vehicleDocumentRepository.findById((Long) any())).thenThrow(new InvalidDnDOperationException());
        assertThrows(InvalidDnDOperationException.class, () -> vehicleDocumentServiceImpl.getDataById(1L));
        verify(vehicleDocumentRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link VehicleDocumentServiceImpl#getData()}
     */
    @Test
    void testGetData() {
        ArrayList<VehicleDocument> vehicleDocumentList = new ArrayList<>();
        when(vehicleDocumentRepository.findAll()).thenReturn(vehicleDocumentList);
        List<VehicleDocument> actualData = vehicleDocumentServiceImpl.getData();
        assertSame(vehicleDocumentList, actualData);
        assertTrue(actualData.isEmpty());
        verify(vehicleDocumentRepository).findAll();
    }

    /**
     * Method under test: {@link VehicleDocumentServiceImpl#getData()}
     */
    @Test
    void testGetData2() {
        when(vehicleDocumentRepository.findAll()).thenThrow(new InvalidDnDOperationException());
        assertThrows(InvalidDnDOperationException.class, () -> vehicleDocumentServiceImpl.getData());
        verify(vehicleDocumentRepository).findAll();
    }
}

