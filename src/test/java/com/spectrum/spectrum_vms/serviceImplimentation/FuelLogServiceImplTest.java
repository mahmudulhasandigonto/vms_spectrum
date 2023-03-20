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

import com.spectrum.spectrum_vms.entity.FuelLog;
import com.spectrum.spectrum_vms.repository.FuelLogRepository;

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

@ContextConfiguration(classes = {FuelLogServiceImpl.class})
@ExtendWith(SpringExtension.class)
class FuelLogServiceImplTest {
    @MockBean
    private FuelLogRepository fuelLogRepository;

    @Autowired
    private FuelLogServiceImpl fuelLogServiceImpl;

    /**
     * Method under test: {@link FuelLogServiceImpl#save(FuelLog)}
     */
    @Test
    void testSave() {
        FuelLog fuelLog = new FuelLog();
        when(fuelLogRepository.save((FuelLog) any())).thenReturn(fuelLog);
        assertSame(fuelLog, fuelLogServiceImpl.save(new FuelLog()));
        verify(fuelLogRepository).save((FuelLog) any());
    }

    /**
     * Method under test: {@link FuelLogServiceImpl#save(FuelLog)}
     */
    @Test
    void testSave2() {
        when(fuelLogRepository.save((FuelLog) any())).thenThrow(new InvalidDnDOperationException());
        assertThrows(InvalidDnDOperationException.class, () -> fuelLogServiceImpl.save(new FuelLog()));
        verify(fuelLogRepository).save((FuelLog) any());
    }

    /**
     * Method under test: {@link FuelLogServiceImpl#update(FuelLog)}
     */
    @Test
    void testUpdate() throws Exception {
        assertThrows(InvalidDnDOperationException.class, () -> fuelLogServiceImpl.update(new FuelLog()));
    }

    /**
     * Method under test: {@link FuelLogServiceImpl#update(FuelLog)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate2() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.spectrum.spectrum_vms.entity.FuelLog.hasId()" because "fuelLog" is null
        //       at com.spectrum.spectrum_vms.serviceImplimentation.FuelLogServiceImpl.update(FuelLogServiceImpl.java:30)
        //   See https://diff.blue/R013 to resolve this issue.

        fuelLogServiceImpl.update(null);
    }

    /**
     * Method under test: {@link FuelLogServiceImpl#update(FuelLog)}
     */
    @Test
    void testUpdate3() throws Exception {
        FuelLog fuelLog = new FuelLog();
        when(fuelLogRepository.save((FuelLog) any())).thenReturn(fuelLog);
        FuelLog fuelLog1 = mock(FuelLog.class);
        when(fuelLog1.hasId()).thenReturn(true);
        assertSame(fuelLog, fuelLogServiceImpl.update(fuelLog1));
        verify(fuelLogRepository).save((FuelLog) any());
        verify(fuelLog1).hasId();
    }

    /**
     * Method under test: {@link FuelLogServiceImpl#update(FuelLog)}
     */
    @Test
    void testUpdate4() throws Exception {
        when(fuelLogRepository.save((FuelLog) any())).thenThrow(new InvalidDnDOperationException());
        FuelLog fuelLog = mock(FuelLog.class);
        when(fuelLog.hasId()).thenReturn(true);
        assertThrows(InvalidDnDOperationException.class, () -> fuelLogServiceImpl.update(fuelLog));
        verify(fuelLogRepository).save((FuelLog) any());
        verify(fuelLog).hasId();
    }

    /**
     * Method under test: {@link FuelLogServiceImpl#deleteByIds(Long[])}
     */
    @Test
    void testDeleteByIds() {
        doNothing().when(fuelLogRepository).deleteAllById((Iterable<Long>) any());
        fuelLogServiceImpl.deleteByIds(new Long[]{1L});
        verify(fuelLogRepository).deleteAllById((Iterable<Long>) any());
    }

    /**
     * Method under test: {@link FuelLogServiceImpl#deleteByIds(Long[])}
     */
    @Test
    void testDeleteByIds2() {
        doThrow(new InvalidDnDOperationException()).when(fuelLogRepository).deleteAllById((Iterable<Long>) any());
        assertThrows(InvalidDnDOperationException.class, () -> fuelLogServiceImpl.deleteByIds(new Long[]{1L}));
        verify(fuelLogRepository).deleteAllById((Iterable<Long>) any());
    }

    /**
     * Method under test: {@link FuelLogServiceImpl#getDataById(Long)}
     */
    @Test
    void testGetDataById() {
        FuelLog fuelLog = new FuelLog();
        when(fuelLogRepository.findById((Long) any())).thenReturn(Optional.of(fuelLog));
        assertSame(fuelLog, fuelLogServiceImpl.getDataById(1L));
        verify(fuelLogRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link FuelLogServiceImpl#getDataById(Long)}
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
        //       at com.spectrum.spectrum_vms.serviceImplimentation.FuelLogServiceImpl.getDataById(FuelLogServiceImpl.java:44)
        //   See https://diff.blue/R013 to resolve this issue.

        when(fuelLogRepository.findById((Long) any())).thenReturn(Optional.empty());
        fuelLogServiceImpl.getDataById(1L);
    }

    /**
     * Method under test: {@link FuelLogServiceImpl#getDataById(Long)}
     */
    @Test
    void testGetDataById3() {
        when(fuelLogRepository.findById((Long) any())).thenThrow(new InvalidDnDOperationException());
        assertThrows(InvalidDnDOperationException.class, () -> fuelLogServiceImpl.getDataById(1L));
        verify(fuelLogRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link FuelLogServiceImpl#getData()}
     */
    @Test
    void testGetData() {
        ArrayList<FuelLog> fuelLogList = new ArrayList<>();
        when(fuelLogRepository.findAll()).thenReturn(fuelLogList);
        List<FuelLog> actualData = fuelLogServiceImpl.getData();
        assertSame(fuelLogList, actualData);
        assertTrue(actualData.isEmpty());
        verify(fuelLogRepository).findAll();
    }

    /**
     * Method under test: {@link FuelLogServiceImpl#getData()}
     */
    @Test
    void testGetData2() {
        when(fuelLogRepository.findAll()).thenThrow(new InvalidDnDOperationException());
        assertThrows(InvalidDnDOperationException.class, () -> fuelLogServiceImpl.getData());
        verify(fuelLogRepository).findAll();
    }
}

