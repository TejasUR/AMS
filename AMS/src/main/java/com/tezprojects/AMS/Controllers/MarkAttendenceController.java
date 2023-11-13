package com.tezprojects.AMS.Controllers;

import com.tezprojects.AMS.Models.ClassAttendenceGeneratorModel;
import com.tezprojects.AMS.Models.MarkAttendenceModel;
import com.tezprojects.AMS.Models.MarkAttendenceRequestModel;
import com.tezprojects.AMS.Repositories.ClassAttendenceGeneratorRepository;
import com.tezprojects.AMS.Repositories.MarkAttendenceRepository;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@Log
@RestController
@RequestMapping("/MarkAttendence")
public class MarkAttendenceController {

    @Autowired
    private MarkAttendenceRepository markAttendenceRepository;
    @Autowired
    private ClassAttendenceGeneratorRepository classAttendenceGeneratorRepository;
    private String uniqueClassId;

    @PostMapping("/")
    public ResponseEntity<?> markNewAttendence(@RequestBody @NotNull MarkAttendenceRequestModel markAttendenceRequest)
    {
        log.info("Received POST Request : " + markAttendenceRequest.toString());
        if(markAttendenceRequest.getId() == null || markAttendenceRequest.getStudentId() == null)
        {
            log.info("Null Params Passed for POST Request");
            return ResponseEntity.badRequest().build();
        }
        this.uniqueClassId = markAttendenceRequest.getId();
        Optional<ClassAttendenceGeneratorModel> classAttendenceGeneratorModel = this.classAttendenceGeneratorRepository.findById(this.uniqueClassId);
        if(classAttendenceGeneratorModel.isEmpty())
        {
            log.info("No Such Class currently available for Attendence");
            return ResponseEntity.badRequest().build();
        }
        Date currentTime = new Date();
        long epochCurrentTime = currentTime.getTime()/1000;
        long epochEndTime = classAttendenceGeneratorModel.get().getEndTime().getTime()/1000;
        if(epochEndTime<epochCurrentTime)
        {
            return ResponseEntity.ok("The Attendence Time is Completed and the request cannot be processed");
        }
        Optional<MarkAttendenceModel> markAttendenceModel = this.markAttendenceRepository.findById(markAttendenceRequest.getId());
        String alterStudentList = markAttendenceModel.get().getStudentList();
        log.info("Obtained Student List : " + alterStudentList);
        if(alterStudentList == null)
        {
            alterStudentList = markAttendenceRequest.getStudentId().toString();
        } else {
            alterStudentList += ", " + markAttendenceRequest.getStudentId().toString();
        }
        markAttendenceModel.get().setStudentList(alterStudentList);
        MarkAttendenceModel savedResponse = this.markAttendenceRepository.save(markAttendenceModel.get());
        return ResponseEntity.ok(savedResponse);
    }

}
