package com.spectrum.spectrum_vms.controller;

import com.spectrum.spectrum_vms.entity.Driver;
import com.spectrum.spectrum_vms.entity.Vehicle;
import com.spectrum.spectrum_vms.error.DeleteRequestException;
import com.spectrum.spectrum_vms.repository.VehicleRepository;
import com.spectrum.spectrum_vms.service.VehicleService;
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
@RequiredArgsConstructor
@RequestMapping("/vehicle/")
public class VehicleController implements BaseController<Vehicle, Long> {
   final private VehicleService vehicleService;
    private final VehicleRepository vehicleRepository;

    // all information save purpose
    @Override
    public ResponseEntity<Vehicle> save(@RequestBody Vehicle vehicle) {
        vehicleService.save(vehicle);
        return ResponseEntity.ok(vehicle);
    }

    // all information update purpose
    @Override
    public ResponseEntity<String> update(@RequestBody Vehicle vehicle) throws Exception {
        try {
            vehicleService.update(vehicle);
            return ResponseEntity.ok("Vehicle information has been updated successfully");
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    // single or multiple information delete purpose
    @Override
    public ResponseEntity<String> deleteByIds(@PathVariable("ids") Long... ids) throws DeleteRequestException {
        try {
            vehicleService.deleteByIds(ids);
            return ResponseEntity.ok("ID " + Arrays.toString(ids) + " has been deleted successfully");
        }catch (Exception ex){
            throw new DeleteRequestException("Error deleting vehicle with id " + ids);
        }
    }

    //single or multiple information get purpose
    @Override
    public ResponseEntity<List<Vehicle>> getDataByIds(@PathVariable("ids") Long... ids) {
        List<Vehicle> vehicleList = vehicleService.getDataByIds(ids);
        return ResponseEntity.ok(vehicleList);
    }

    // all information get purpose
    @Override
    public List<Vehicle> getData() {
        return vehicleService.getData();
    }


    //for saving image
    @PostMapping("/{id}/image")
    public ResponseEntity<Void> saveImage(@PathVariable("id") Long driverId, @RequestParam("file") MultipartFile file) {
        try {
            Optional<Vehicle> optionalVehicle = vehicleRepository.findById(driverId);
            if (optionalVehicle.isPresent()) {
                Vehicle vehicle = optionalVehicle.get();

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
                vehicle.setVehicleImage(filename);
                vehicleRepository.save(vehicle);

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
