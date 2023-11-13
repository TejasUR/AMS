package com.tezprojects.AMS.Controllers;

import com.tezprojects.AMS.Models.ClassAttendenceGeneratorModel;
import com.tezprojects.AMS.Models.MarkAttendenceModel;
import com.tezprojects.AMS.Repositories.ClassAttendenceGeneratorRepository;
import com.tezprojects.AMS.Repositories.MarkAttendenceRepository;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/GenerateClass")
@Log
public class ClassAttendenceGeneratorController {
    @Autowired
    private ClassAttendenceGeneratorRepository classAttendenceGeneratorRepository;
    @Autowired
    private MarkAttendenceRepository markAttendenceRepository;

    @PostMapping("/")
    public ResponseEntity<?> generateNewClass(@RequestBody @NotNull ClassAttendenceGeneratorModel classAttendenceGeneratorModel)
    {
        log.info("Received POST Request : " + classAttendenceGeneratorModel.toString());
        if(classAttendenceGeneratorModel.getTeacherId() == null)
        {
            log.info("Null Teacher ID Passed for POST Request");
            return ResponseEntity.badRequest().build();
        }
        Date currentTime = new Date();
        long epochCurrentTime = currentTime.getTime();
        long epochAddedTime = epochCurrentTime + 300000 ;
        Date addedTime = new Date(epochAddedTime);
        classAttendenceGeneratorModel.setStartTime(currentTime);
        classAttendenceGeneratorModel.setEndTime(addedTime);
        epochCurrentTime = epochCurrentTime/1000;
        String generatedId = classAttendenceGeneratorModel.getTeacherId().toString() + "_" + epochCurrentTime;
        log.info("Generated Id : " + generatedId);
        classAttendenceGeneratorModel.setId(generatedId);
        ClassAttendenceGeneratorModel savedResponse = this.classAttendenceGeneratorRepository.save(classAttendenceGeneratorModel);
        MarkAttendenceModel markAttendenceModel =  MarkAttendenceModel
                                                    .builder()
                                                    .id(generatedId)
                                                    .build();
        this.markAttendenceRepository.save(markAttendenceModel);
        log.info("[POST Class Gen] Added Entry to Database");
        return ResponseEntity.ok(savedResponse);
    }

    @GetMapping("/")
    public ResponseEntity<?> getClassById(@RequestBody @NotNull ClassAttendenceGeneratorModel classAttendenceGeneratorModel)
    {
        log.info("Received GET Request : " + classAttendenceGeneratorModel.toString());
        log.info("Retrieved Classes from Database");
        if(classAttendenceGeneratorModel.getId().isEmpty())
        {
            log.info("Null ID Passed for GET Request");
            return ResponseEntity.badRequest().build();
        }
        log.info("[GET Class Gen] Retrieved Entry to Database");
        return ResponseEntity.ok(this.classAttendenceGeneratorRepository.findById(classAttendenceGeneratorModel.getId()));
    }
}
