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

import com.spectrum.spectrum_vms.entity.Driver;
import com.spectrum.spectrum_vms.error.DeleteRequestException;
import com.spectrum.spectrum_vms.error.DriverNotFoundException;
import com.spectrum.spectrum_vms.repository.DriverRepository;
import com.spectrum.spectrum_vms.service.DriverService;
import com.spectrum.spectrum_vms.serviceImplimentation.DriverServiceImpl;

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

@ContextConfiguration(classes = {DriverController.class})
@ExtendWith(SpringExtension.class)
class DriverControllerTest {
    @Autowired
    private DriverController driverController;

    @MockBean
    private DriverRepository driverRepository;

    @MockBean
    private DriverService driverService;

    /**
     * Method under test: {@link DriverController#save(Driver)}
     */
    @Test
    void testSave() {
        when(driverService.save((Driver) any())).thenReturn(new Driver());
        ResponseEntity<Driver> actualSaveResult = driverController.save(new Driver());
        assertTrue(actualSaveResult.hasBody());
        assertTrue(actualSaveResult.getHeaders().isEmpty());
        assertEquals(200, actualSaveResult.getStatusCodeValue());
        verify(driverService).save((Driver) any());
    }

    /**
     * Method under test: {@link DriverController#update(Driver)}
     */
    @Test
    void testUpdate() throws Exception {
        when(driverService.update((Driver) any())).thenReturn(new Driver());
        ResponseEntity<String> actualUpdateResult = driverController.update(new Driver());
        assertEquals("Driver information has been updated successfully", actualUpdateResult.getBody());
        assertEquals(200, actualUpdateResult.getStatusCodeValue());
        assertTrue(actualUpdateResult.getHeaders().isEmpty());
        verify(driverService).update((Driver) any());
    }

    /**
     * Method under test: {@link DriverController#update(Driver)}
     */
    @Test
    void testUpdate2() throws Exception {
        when(driverService.update((Driver) any())).thenThrow(new Exception());
        ResponseEntity<String> actualUpdateResult = driverController.update(new Driver());
        assertNull(actualUpdateResult.getBody());
        assertEquals(422, actualUpdateResult.getStatusCodeValue());
        assertTrue(actualUpdateResult.getHeaders().isEmpty());
        verify(driverService).update((Driver) any());
    }

    /**
     * Method under test: {@link DriverController#deleteByIds(Long[])}
     */
    @Test
    void testDeleteByIds() throws DeleteRequestException {
        doNothing().when(driverService).deleteByIds((Long[]) any());
        ResponseEntity<String> actualDeleteByIdsResult = driverController.deleteByIds(1L);
        assertEquals("ID [1] has been deleted successfully", actualDeleteByIdsResult.getBody());
        assertEquals(200, actualDeleteByIdsResult.getStatusCodeValue());
        assertTrue(actualDeleteByIdsResult.getHeaders().isEmpty());
        verify(driverService).deleteByIds((Long[]) any());
    }

    /**
     * Method under test: {@link DriverController#getDataById(Long)}
     */
    @Test
    void testGetDataById() throws DriverNotFoundException {
        when(driverService.getDataById((Long) any())).thenReturn(new Driver());
        ResponseEntity<Driver> actualDataById = driverController.getDataById(1L);
        assertTrue(actualDataById.hasBody());
        assertTrue(actualDataById.getHeaders().isEmpty());
        assertEquals(200, actualDataById.getStatusCodeValue());
        verify(driverService).getDataById((Long) any());
    }

    /**
     * Method under test: {@link DriverController#getData()}
     */
    @Test
    void testGetData() {
        ArrayList<Driver> driverList = new ArrayList<>();
        when(driverService.getData()).thenReturn(driverList);
        List<Driver> actualData = driverController.getData();
        assertSame(driverList, actualData);
        assertTrue(actualData.isEmpty());
        verify(driverService).getData();
    }

    /**
     * Method under test: {@link DriverController#getDataByAvailability(Boolean)}
     */
    @Test
    void testGetDataByAvailability() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        DriverRepository driverRepository = mock(DriverRepository.class);
        ArrayList<Driver> driverList = new ArrayList<>();
        when(driverRepository.findByIsAvailable((Boolean) any())).thenReturn(driverList);
        List<Driver> actualDataByAvailability = (new DriverController(new DriverServiceImpl(driverRepository),
                mock(DriverRepository.class))).getDataByAvailability(true);
        assertSame(driverList, actualDataByAvailability);
        assertTrue(actualDataByAvailability.isEmpty());
        verify(driverRepository).findByIsAvailable((Boolean) any());
    }

    /**
     * Method under test: {@link DriverController#getDataByAvailability(Boolean)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetDataByAvailability2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.spectrum.spectrum_vms.service.DriverService.findByIsAvailable(java.lang.Boolean)" because "this.driverService" is null
        //       at com.spectrum.spectrum_vms.controller.DriverController.getDataByAvailability(DriverController.java:101)
        //   See https://diff.blue/R013 to resolve this issue.

        (new DriverController(null, mock(DriverRepository.class))).getDataByAvailability(true);
    }

    /**
     * Method under test: {@link DriverController#getDataBasedOnProblem()}
     */
    @Test
    void testGetDataBasedOnProblem() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        DriverRepository driverRepository = mock(DriverRepository.class);
        ArrayList<Driver> driverList = new ArrayList<>();
        when(driverRepository.findByProblemIsNotNull()).thenReturn(driverList);
        List<Driver> actualDataBasedOnProblem = (new DriverController(new DriverServiceImpl(driverRepository),
                mock(DriverRepository.class))).getDataBasedOnProblem();
        assertSame(driverList, actualDataBasedOnProblem);
        assertTrue(actualDataBasedOnProblem.isEmpty());
        verify(driverRepository).findByProblemIsNotNull();
    }

    /**
     * Method under test: {@link DriverController#getDataBasedOnProblem()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetDataBasedOnProblem2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.spectrum.spectrum_vms.service.DriverService.findByProblemIsNotNull()" because "this.driverService" is null
        //       at com.spectrum.spectrum_vms.controller.DriverController.getDataBasedOnProblem(DriverController.java:107)
        //   See https://diff.blue/R013 to resolve this issue.

        (new DriverController(null, mock(DriverRepository.class))).getDataBasedOnProblem();
    }

    /**
     * Method under test: {@link DriverController#getDataByContactNumber(String)}
     */
    @Test
    void testGetDataByContactNumber() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        DriverRepository driverRepository = mock(DriverRepository.class);
        Driver driver = new Driver();
        when(driverRepository.findByContactNumber((String) any())).thenReturn(driver);
        assertSame(driver, (new DriverController(new DriverServiceImpl(driverRepository), mock(DriverRepository.class)))
                .getDataByContactNumber("42"));
        verify(driverRepository).findByContactNumber((String) any());
    }

    /**
     * Method under test: {@link DriverController#getDataByContactNumber(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetDataByContactNumber2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.spectrum.spectrum_vms.service.DriverService.findByContactNumber(String)" because "this.driverService" is null
        //       at com.spectrum.spectrum_vms.controller.DriverController.getDataByContactNumber(DriverController.java:113)
        //   See https://diff.blue/R013 to resolve this issue.

        (new DriverController(null, mock(DriverRepository.class))).getDataByContactNumber("42");
    }

    /**
     * Method under test: {@link DriverController#saveImage(Long, MultipartFile)}
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
        //     to access files outside the temporary directory (file 'path\to\image\directory\a16c8af1-ed71-4dd3-bcac-acb7f938d6a9.jpg', permission 'write').
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        DriverRepository driverRepository = mock(DriverRepository.class);
        when(driverRepository.findById((Long) any())).thenReturn(Optional.of(new Driver()));
        DriverController driverController = new DriverController(new DriverServiceImpl(mock(DriverRepository.class)),
                driverRepository);
        driverController.saveImage(1L,
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
    }

    /**
     * Method under test: {@link DriverController#saveImage(Long, MultipartFile)}
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

        DriverRepository driverRepository = mock(DriverRepository.class);
        when(driverRepository.findById((Long) any())).thenReturn(Optional.empty());
        DriverController driverController = new DriverController(new DriverServiceImpl(mock(DriverRepository.class)),
                driverRepository);
        ResponseEntity<Void> actualSaveImageResult = driverController.saveImage(1L,
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
        assertNull(actualSaveImageResult.getBody());
        assertEquals(404, actualSaveImageResult.getStatusCodeValue());
        assertTrue(actualSaveImageResult.getHeaders().isEmpty());
        verify(driverRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link DriverController#saveImage(Long, MultipartFile)}
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
        //       at com.spectrum.spectrum_vms.controller.DriverController.saveImage(DriverController.java:137)
        //   See https://diff.blue/R013 to resolve this issue.

        DriverRepository driverRepository = mock(DriverRepository.class);
        when(driverRepository.findById((Long) any())).thenReturn(Optional.of(new Driver()));
        (new DriverController(new DriverServiceImpl(mock(DriverRepository.class)), driverRepository)).saveImage(1L, null);
    }

    /**
     * Method under test: {@link DriverController#saveImage(Long, MultipartFile)}
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

        DriverRepository driverRepository = mock(DriverRepository.class);
        when(driverRepository.findById((Long) any())).thenReturn(Optional.of(new Driver()));
        DriverController driverController = new DriverController(new DriverServiceImpl(mock(DriverRepository.class)),
                driverRepository);
        MultipartFile multipartFile = mock(MultipartFile.class);
        when(multipartFile.getBytes()).thenThrow(new IOException());
        ResponseEntity<Void> actualSaveImageResult = driverController.saveImage(1L, multipartFile);
        assertNull(actualSaveImageResult.getBody());
        assertEquals(500, actualSaveImageResult.getStatusCodeValue());
        assertTrue(actualSaveImageResult.getHeaders().isEmpty());
        verify(driverRepository).findById((Long) any());
        verify(multipartFile).getBytes();
    }

    /**
     * Method under test: {@link DriverController#getImage(String)}
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
        //       at com.spectrum.spectrum_vms.controller.DriverController.getImage(DriverController.java:157)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:705)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:814)
        //   See https://diff.blue/R013 to resolve this issue.

        (new DriverController(new DriverServiceImpl(mock(DriverRepository.class)), mock(DriverRepository.class)))
                .getImage("foo.txt");
    }
}

