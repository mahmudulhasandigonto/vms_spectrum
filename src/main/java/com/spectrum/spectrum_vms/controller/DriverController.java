package com.spectrum.spectrum_vms.controller;


import com.spectrum.spectrum_vms.entity.Driver;
import com.spectrum.spectrum_vms.error.DeleteRequestException;
import com.spectrum.spectrum_vms.repository.DriverRepository;
import com.spectrum.spectrum_vms.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/driver/")
@RequiredArgsConstructor
public class DriverController implements BaseController<Driver, Long> {



   final private DriverService driverService;

   final private DriverRepository driverRepository;


    //all information save purpose
    @Override
    public ResponseEntity<Driver> save(@RequestBody Driver driver) {
        driverService.save(driver);
        return ResponseEntity.ok(driver);
    }


    //all information update purpose
    @Override
    public ResponseEntity<String> update(@RequestBody Driver driver) throws Exception {
        try {
            driverService.update(driver);
            return ResponseEntity.ok("Driver information has been updated successfully");
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }


    //single or multiple user information delete purpose
    @Override
    public ResponseEntity<String> deleteByIds(@PathVariable("ids") Long... ids) throws DeleteRequestException {
       try {
           driverService.deleteByIds(ids);
           return ResponseEntity.ok("ID " + Arrays.toString(ids) + " has been deleted successfully");
       }
       catch (Exception ex){
           throw new DeleteRequestException("Error deleting driver with id " + Arrays.toString(ids));
        }

    }


    //single or multiple information get purpose
    @Override
    public ResponseEntity<List<Driver>> getDataByIds(@PathVariable("ids") Long... ids) {
        List<Driver> driverList = driverService.getDataByIds(ids);
        return ResponseEntity.ok(driverList);
    }

    //all information get purpose
    @Override
    public List<Driver> getData() {
        return driverService.getData();
    }


    //for saving image
    @PostMapping("/{id}/image")
    public ResponseEntity<Void> saveImage(@PathVariable("id") Long driverId, @RequestParam("file") MultipartFile file) {
        try {
            Optional<Driver> optionalDriver = driverRepository.findById(driverId);
            if (optionalDriver.isPresent()) {
                Driver driver = optionalDriver.get();

                // Generate a unique filename for the image
                String filename = UUID.randomUUID().toString() + ".jpg";

                // Create the image directory if it doesn't exist
                Path imageDir = Paths.get("path/to/image/directory");
                if (!Files.exists(imageDir)) {
                    Files.createDirectories(imageDir);
                }

                // Save the image file to the directory
                Path imagePath = imageDir.resolve(filename);
                Files.write(imagePath, file.getBytes());

                // Update the driver entity with the filename
                driver.setDriverImage(filename);
                driverRepository.save(driver);

                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/image/{fileName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String fileName) throws IOException {
        File file = new File("path/to/image/directory/" + fileName);
        Resource resource = new UrlResource(file.toURI());
        byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(imageBytes.length);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

}
