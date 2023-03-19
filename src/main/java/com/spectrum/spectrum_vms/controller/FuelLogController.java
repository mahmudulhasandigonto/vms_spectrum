package com.spectrum.spectrum_vms.controller;


import com.spectrum.spectrum_vms.entity.Driver;
import com.spectrum.spectrum_vms.entity.FuelLog;
import com.spectrum.spectrum_vms.error.DeleteRequestException;
import com.spectrum.spectrum_vms.error.FuelLogNotFoundException;
import com.spectrum.spectrum_vms.repository.FuelLogRepository;
import com.spectrum.spectrum_vms.service.FuelLogService;
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
@RequestMapping("/fuellog/")
@RequiredArgsConstructor
public class FuelLogController implements BaseController<FuelLog, Long> {

    final private FuelLogService fuelLogService;
    private final FuelLogRepository fuelLogRepository;

    @Override
    public ResponseEntity<FuelLog> save(@RequestBody FuelLog fuelLog) {
        fuelLogService.save(fuelLog);
        return  ResponseEntity.ok(fuelLog);
    }

    @Override
    public ResponseEntity<String> update(@RequestBody FuelLog fuelLog) throws Exception {
        try {
            fuelLogService.update(fuelLog);
            return ResponseEntity.ok("FuelLog information has been updated successfully");
        }catch (Exception ex){
            return ResponseEntity.unprocessableEntity().body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteByIds(@PathVariable("ids") Long... ids) throws DeleteRequestException {
        try {
            fuelLogService.deleteByIds(ids);
            return ResponseEntity.ok("ID " + Arrays.toString(ids) + " has been deleted successfully");
        }catch (Exception ex){
            throw new DeleteRequestException("Error deleting fuelLog with id " + ids);
        }
    }

    //single information get purpose
    @Override
    public ResponseEntity<FuelLog> getDataById(@PathVariable("id") Long id) throws FuelLogNotFoundException {
       try {
           FuelLog fuelLongList = fuelLogService.getDataById(id);
           return ResponseEntity.ok(fuelLongList);
       }catch (Exception e){
           throw new FuelLogNotFoundException("Fuel Log Not Found");
       }
    }

    @Override
    public List<FuelLog> getData() {
        return fuelLogService.getData();
    }


    //for saving image
    @PostMapping("/{id}/image")
    public ResponseEntity<Void> saveImage(@PathVariable("id") Long fuelLogId, @RequestParam("file") MultipartFile file) {
        try {
            Optional<FuelLog> optionalFuelLog = fuelLogRepository.findById(fuelLogId);
            if (optionalFuelLog.isPresent()) {
                FuelLog fuelLog = optionalFuelLog.get();

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
                fuelLog.setReceiptImage(filename);
                fuelLogRepository.save(fuelLog);

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
