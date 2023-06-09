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

import com.spectrum.spectrum_vms.entity.Vehicle;
import com.spectrum.spectrum_vms.error.DeleteRequestException;
import com.spectrum.spectrum_vms.error.VehicleNotFoundException;
import com.spectrum.spectrum_vms.repository.VehicleRepository;
import com.spectrum.spectrum_vms.service.VehicleService;
import com.spectrum.spectrum_vms.serviceImplimentation.VehicleServiceImpl;

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

@ContextConfiguration(classes = {VehicleController.class})
@ExtendWith(SpringExtension.class)
class VehicleControllerTest {
    @Autowired
    private VehicleController vehicleController;

    @MockBean
    private VehicleRepository vehicleRepository;

    @MockBean
    private VehicleService vehicleService;

    /**
     * Method under test: {@link VehicleController#save(Vehicle)}
     */
    @Test
    void testSave() {
        when(vehicleService.save((Vehicle) any())).thenReturn(new Vehicle());
        ResponseEntity<Vehicle> actualSaveResult = vehicleController.save(new Vehicle());
        assertTrue(actualSaveResult.hasBody());
        assertTrue(actualSaveResult.getHeaders().isEmpty());
        assertEquals(200, actualSaveResult.getStatusCodeValue());
        verify(vehicleService).save((Vehicle) any());
    }

    /**
     * Method under test: {@link VehicleController#update(Vehicle)}
     */
    @Test
    void testUpdate() throws Exception {
        when(vehicleService.update((Vehicle) any())).thenReturn(new Vehicle());
        ResponseEntity<String> actualUpdateResult = vehicleController.update(new Vehicle());
        assertEquals("Vehicle information has been updated successfully", actualUpdateResult.getBody());
        assertEquals(200, actualUpdateResult.getStatusCodeValue());
        assertTrue(actualUpdateResult.getHeaders().isEmpty());
        verify(vehicleService).update((Vehicle) any());
    }

    /**
     * Method under test: {@link VehicleController#update(Vehicle)}
     */
    @Test
    void testUpdate2() throws Exception {
        when(vehicleService.update((Vehicle) any())).thenThrow(new Exception());
        ResponseEntity<String> actualUpdateResult = vehicleController.update(new Vehicle());
        assertNull(actualUpdateResult.getBody());
        assertEquals(422, actualUpdateResult.getStatusCodeValue());
        assertTrue(actualUpdateResult.getHeaders().isEmpty());
        verify(vehicleService).update((Vehicle) any());
    }

    /**
     * Method under test: {@link VehicleController#deleteByIds(Long[])}
     */
    @Test
    void testDeleteByIds() throws DeleteRequestException {
        doNothing().when(vehicleService).deleteByIds((Long[]) any());
        ResponseEntity<String> actualDeleteByIdsResult = vehicleController.deleteByIds(1L);
        assertEquals("ID [1] has been deleted successfully", actualDeleteByIdsResult.getBody());
        assertEquals(200, actualDeleteByIdsResult.getStatusCodeValue());
        assertTrue(actualDeleteByIdsResult.getHeaders().isEmpty());
        verify(vehicleService).deleteByIds((Long[]) any());
    }

    /**
     * Method under test: {@link VehicleController#getDataById(Long)}
     */
    @Test
    void testGetDataById() throws VehicleNotFoundException {
        when(vehicleService.getDataById((Long) any())).thenReturn(new Vehicle());
        ResponseEntity<Vehicle> actualDataById = vehicleController.getDataById(1L);
        assertTrue(actualDataById.hasBody());
        assertTrue(actualDataById.getHeaders().isEmpty());
        assertEquals(200, actualDataById.getStatusCodeValue());
        verify(vehicleService).getDataById((Long) any());
    }

    /**
     * Method under test: {@link VehicleController#getData()}
     */
    @Test
    void testGetData() {
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        when(vehicleService.getData()).thenReturn(vehicleList);
        List<Vehicle> actualData = vehicleController.getData();
        assertSame(vehicleList, actualData);
        assertTrue(actualData.isEmpty());
        verify(vehicleService).getData();
    }

    /**
     * Method under test: {@link VehicleController#saveImage(Long, MultipartFile)}
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
        //     to access files outside the temporary directory (file 'path\to\image\directory\38efdd39-73b8-447e-be95-a3ec05c0238d.jpg', permission 'write').
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        VehicleRepository vehicleRepository = mock(VehicleRepository.class);
        when(vehicleRepository.findById((Long) any())).thenReturn(Optional.of(new Vehicle()));
        VehicleController vehicleController = new VehicleController(new VehicleServiceImpl(mock(VehicleRepository.class)),
                vehicleRepository);
        vehicleController.saveImage(1L,
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
    }

    /**
     * Method under test: {@link VehicleController#saveImage(Long, MultipartFile)}
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

        VehicleRepository vehicleRepository = mock(VehicleRepository.class);
        when(vehicleRepository.findById((Long) any())).thenReturn(Optional.empty());
        VehicleController vehicleController = new VehicleController(new VehicleServiceImpl(mock(VehicleRepository.class)),
                vehicleRepository);
        ResponseEntity<Void> actualSaveImageResult = vehicleController.saveImage(1L,
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
        assertNull(actualSaveImageResult.getBody());
        assertEquals(404, actualSaveImageResult.getStatusCodeValue());
        assertTrue(actualSaveImageResult.getHeaders().isEmpty());
        verify(vehicleRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link VehicleController#saveImage(Long, MultipartFile)}
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
        //       at com.spectrum.spectrum_vms.controller.VehicleController.saveImage(VehicleController.java:102)
        //   See https://diff.blue/R013 to resolve this issue.

        VehicleRepository vehicleRepository = mock(VehicleRepository.class);
        when(vehicleRepository.findById((Long) any())).thenReturn(Optional.of(new Vehicle()));
        (new VehicleController(new VehicleServiceImpl(mock(VehicleRepository.class)), vehicleRepository)).saveImage(1L,
                null);
    }

    /**
     * Method under test: {@link VehicleController#saveImage(Long, MultipartFile)}
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

        VehicleRepository vehicleRepository = mock(VehicleRepository.class);
        when(vehicleRepository.findById((Long) any())).thenReturn(Optional.of(new Vehicle()));
        VehicleController vehicleController = new VehicleController(new VehicleServiceImpl(mock(VehicleRepository.class)),
                vehicleRepository);
        MultipartFile multipartFile = mock(MultipartFile.class);
        when(multipartFile.getBytes()).thenThrow(new IOException());
        ResponseEntity<Void> actualSaveImageResult = vehicleController.saveImage(1L, multipartFile);
        assertNull(actualSaveImageResult.getBody());
        assertEquals(500, actualSaveImageResult.getStatusCodeValue());
        assertTrue(actualSaveImageResult.getHeaders().isEmpty());
        verify(vehicleRepository).findById((Long) any());
        verify(multipartFile).getBytes();
    }

    /**
     * Method under test: {@link VehicleController#getImage(String)}
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
        //       at com.spectrum.spectrum_vms.controller.VehicleController.getImage(VehicleController.java:122)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:705)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:814)
        //   See https://diff.blue/R013 to resolve this issue.

        (new VehicleController(new VehicleServiceImpl(mock(VehicleRepository.class)), mock(VehicleRepository.class)))
                .getImage("foo.txt");
    }
}

