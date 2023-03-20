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

import com.spectrum.spectrum_vms.entity.FuelLog;
import com.spectrum.spectrum_vms.error.DeleteRequestException;
import com.spectrum.spectrum_vms.error.FuelLogNotFoundException;
import com.spectrum.spectrum_vms.repository.FuelLogRepository;
import com.spectrum.spectrum_vms.service.FuelLogService;
import com.spectrum.spectrum_vms.serviceImplimentation.FuelLogServiceImpl;

import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {FuelLogController.class})
@ExtendWith(SpringExtension.class)
class FuelLogControllerTest {
    @Autowired
    private FuelLogController fuelLogController;

    @MockBean
    private FuelLogRepository fuelLogRepository;

    @MockBean
    private FuelLogService fuelLogService;

    /**
     * Method under test: {@link FuelLogController#save(FuelLog)}
     */
    @Test
    void testSave() {
        when(fuelLogService.save((FuelLog) any())).thenReturn(new FuelLog());
        ResponseEntity<FuelLog> actualSaveResult = fuelLogController.save(new FuelLog());
        assertTrue(actualSaveResult.hasBody());
        assertTrue(actualSaveResult.getHeaders().isEmpty());
        assertEquals(200, actualSaveResult.getStatusCodeValue());
        verify(fuelLogService).save((FuelLog) any());
    }

    /**
     * Method under test: {@link FuelLogController#update(FuelLog)}
     */
    @Test
    void testUpdate() throws Exception {
        when(fuelLogService.update((FuelLog) any())).thenReturn(new FuelLog());
        ResponseEntity<String> actualUpdateResult = fuelLogController.update(new FuelLog());
        assertEquals("FuelLog information has been updated successfully", actualUpdateResult.getBody());
        assertEquals(200, actualUpdateResult.getStatusCodeValue());
        assertTrue(actualUpdateResult.getHeaders().isEmpty());
        verify(fuelLogService).update((FuelLog) any());
    }

    /**
     * Method under test: {@link FuelLogController#update(FuelLog)}
     */
    @Test
    void testUpdate2() throws Exception {
        when(fuelLogService.update((FuelLog) any())).thenThrow(new Exception());
        ResponseEntity<String> actualUpdateResult = fuelLogController.update(new FuelLog());
        assertNull(actualUpdateResult.getBody());
        assertEquals(422, actualUpdateResult.getStatusCodeValue());
        assertTrue(actualUpdateResult.getHeaders().isEmpty());
        verify(fuelLogService).update((FuelLog) any());
    }

    /**
     * Method under test: {@link FuelLogController#deleteByIds(Long[])}
     */
    @Test
    void testDeleteByIds() throws DeleteRequestException {
        doNothing().when(fuelLogService).deleteByIds((Long[]) any());
        ResponseEntity<?> actualDeleteByIdsResult = fuelLogController.deleteByIds(1L);
        assertEquals("ID [1] has been deleted successfully", actualDeleteByIdsResult.getBody());
        assertEquals(200, actualDeleteByIdsResult.getStatusCodeValue());
        assertTrue(actualDeleteByIdsResult.getHeaders().isEmpty());
        verify(fuelLogService).deleteByIds((Long[]) any());
    }

    /**
     * Method under test: {@link FuelLogController#getDataById(Long)}
     */
    @Test
    void testGetDataById() throws FuelLogNotFoundException {
        when(fuelLogService.getDataById((Long) any())).thenReturn(new FuelLog());
        ResponseEntity<FuelLog> actualDataById = fuelLogController.getDataById(1L);
        assertTrue(actualDataById.hasBody());
        assertTrue(actualDataById.getHeaders().isEmpty());
        assertEquals(200, actualDataById.getStatusCodeValue());
        verify(fuelLogService).getDataById((Long) any());
    }

    /**
     * Method under test: {@link FuelLogController#getData()}
     */
    @Test
    void testGetData() {
        ArrayList<FuelLog> fuelLogList = new ArrayList<>();
        when(fuelLogService.getData()).thenReturn(fuelLogList);
        List<FuelLog> actualData = fuelLogController.getData();
        assertSame(fuelLogList, actualData);
        assertTrue(actualData.isEmpty());
        verify(fuelLogService).getData();
    }

    /**
     * Method under test: {@link FuelLogController#saveImage(Long, MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSaveImage() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.web.multipart.MultipartException: Current request is not a multipart request
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:731)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:814)
        //   org.springframework.web.multipart.MultipartException: Current request is not a multipart request
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:731)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:814)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access files outside the temporary directory (file 'path\to\image\directory\79eb43ff-709f-4d9a-970a-b4f52af18cc1.jpg', permission 'write').
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        FuelLogRepository fuelLogRepository = mock(FuelLogRepository.class);
        when(fuelLogRepository.findById((Long) any())).thenReturn(Optional.of(new FuelLog()));
        FuelLogController fuelLogController = new FuelLogController(new FuelLogServiceImpl(mock(FuelLogRepository.class)),
                fuelLogRepository);
        fuelLogController.saveImage(1L,
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
    }

    /**
     * Method under test: {@link FuelLogController#saveImage(Long, MultipartFile)}
     */
    @Test
    void testSaveImage2() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.web.multipart.MultipartException: Current request is not a multipart request
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:731)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:814)
        //   org.springframework.web.multipart.MultipartException: Current request is not a multipart request
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:731)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:814)
        //   See https://diff.blue/R013 to resolve this issue.

        FuelLogRepository fuelLogRepository = mock(FuelLogRepository.class);
        when(fuelLogRepository.findById((Long) any())).thenReturn(Optional.empty());
        FuelLogController fuelLogController = new FuelLogController(new FuelLogServiceImpl(mock(FuelLogRepository.class)),
                fuelLogRepository);
        ResponseEntity<Void> actualSaveImageResult = fuelLogController.saveImage(1L,
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
        assertNull(actualSaveImageResult.getBody());
        assertEquals(404, actualSaveImageResult.getStatusCodeValue());
        assertTrue(actualSaveImageResult.getHeaders().isEmpty());
        verify(fuelLogRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link FuelLogController#saveImage(Long, MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSaveImage3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.web.multipart.MultipartException: Current request is not a multipart request
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:731)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:814)
        //   org.springframework.web.multipart.MultipartException: Current request is not a multipart request
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:731)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:814)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.web.multipart.MultipartFile.getBytes()" because "file" is null
        //       at com.spectrum.spectrum_vms.controller.FuelLogController.saveImage(FuelLogController.java:100)
        //   See https://diff.blue/R013 to resolve this issue.

        FuelLogRepository fuelLogRepository = mock(FuelLogRepository.class);
        when(fuelLogRepository.findById((Long) any())).thenReturn(Optional.of(new FuelLog()));
        (new FuelLogController(new FuelLogServiceImpl(mock(FuelLogRepository.class)), fuelLogRepository)).saveImage(1L,
                null);
    }

    /**
     * Method under test: {@link FuelLogController#saveImage(Long, MultipartFile)}
     */
    @Test
    void testSaveImage4() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.web.multipart.MultipartException: Current request is not a multipart request
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:731)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:814)
        //   org.springframework.web.multipart.MultipartException: Current request is not a multipart request
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:731)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:814)
        //   See https://diff.blue/R013 to resolve this issue.

        FuelLogRepository fuelLogRepository = mock(FuelLogRepository.class);
        when(fuelLogRepository.findById((Long) any())).thenReturn(Optional.of(new FuelLog()));
        FuelLogController fuelLogController = new FuelLogController(new FuelLogServiceImpl(mock(FuelLogRepository.class)),
                fuelLogRepository);
        MultipartFile multipartFile = mock(MultipartFile.class);
        when(multipartFile.getBytes()).thenThrow(new IOException());
        ResponseEntity<Void> actualSaveImageResult = fuelLogController.saveImage(1L, multipartFile);
        assertNull(actualSaveImageResult.getBody());
        assertEquals(500, actualSaveImageResult.getStatusCodeValue());
        assertTrue(actualSaveImageResult.getHeaders().isEmpty());
        verify(fuelLogRepository).findById((Long) any());
        verify(multipartFile).getBytes();
    }

    /**
     * Method under test: {@link FuelLogController#getImage(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetImage() throws IOException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.nio.file.NoSuchFileException: C:\dev\vehicle-management-system\BackEnd\path\to\image\directory\42
        //       at sun.nio.fs.WindowsException.translateToIOException(WindowsException.java:85)
        //       at sun.nio.fs.WindowsException.rethrowAsIOException(WindowsException.java:103)
        //       at sun.nio.fs.WindowsException.rethrowAsIOException(WindowsException.java:108)
        //       at sun.nio.fs.WindowsFileSystemProvider.newByteChannel(WindowsFileSystemProvider.java:236)
        //       at java.nio.file.Files.newByteChannel(Files.java:380)
        //       at java.nio.file.Files.newByteChannel(Files.java:432)
        //       at java.nio.file.Files.readAllBytes(Files.java:3288)
        //       at com.spectrum.spectrum_vms.controller.FuelLogController.getImage(FuelLogController.java:120)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:705)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:814)
        //   See https://diff.blue/R013 to resolve this issue.

        (new FuelLogController(new FuelLogServiceImpl(mock(FuelLogRepository.class)), mock(FuelLogRepository.class)))
                .getImage("foo.txt");
    }
}

