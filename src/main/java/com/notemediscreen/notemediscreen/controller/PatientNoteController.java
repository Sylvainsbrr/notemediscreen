package com.notemediscreen.notemediscreen.controller;

import com.notemediscreen.notemediscreen.entity.PatientNote;
import com.notemediscreen.notemediscreen.exception.DataNotFoundExecption;
import com.notemediscreen.notemediscreen.repository.PatientNoteRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Api(description = "Gestion des notes des patients")
@RestController
public class PatientNoteController {

    @Autowired
    PatientNoteRepository patientNoteRepository;

    @ApiOperation(value = "Récupère une note de patient avec son id")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Patient with id { id } doesn't have note")})
    @GetMapping(value = "/patient-note/{patientId}")
    public PatientNote findPatientNoteById(@PathVariable("patientId") int patientId){
        PatientNote patientNote = patientNoteRepository.findPatientNoteByPatientId(patientId);
        if (Objects.isNull(patientNote)){
            throw new DataNotFoundExecption("Patient with id " + patientId + " doesn't have note");
        }
        return patientNote;
    }


    @ApiOperation(value = "Mise a jour de la note d'un patient")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "The patient with id {id} to update note doesn't exist in database")})
    @PutMapping(value = "/patient-note/{patientId}")
    public PatientNote updatePatientNote(@PathVariable("patientId") int patientId, @RequestBody PatientNote patientNote){
        PatientNote patientNoteToUpdate = patientNoteRepository.findPatientNoteByPatientId(patientId);
        if(Objects.isNull(patientNoteToUpdate)){
            throw new DataNotFoundExecption("The patient with id " + patientId + " to update note doesn't exist in database");
        } else {
            patientNoteToUpdate.setNotes(patientNote.getNotes());
            patientNoteRepository.save(patientNoteToUpdate);
        }
        return patientNoteToUpdate;
    }



    @ApiOperation(value = "Save la note d'un patient")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "The patient with id {id} have already a note in database")})
    @PostMapping(value = "/patient-note")
    public PatientNote savePatientNote(@RequestBody PatientNote patientNote){
        System.out.println("test");
        PatientNote patientNoteToSave = patientNoteRepository.findPatientNoteByPatientId(patientNote.getPatientId());
        if(Objects.isNull(patientNoteToSave)){
            patientNoteRepository.save(patientNote);
        } else {
            throw new DataNotFoundExecption("The patient with id " + patientNote.getPatientId() + " have already a note in database");
        }
        return patientNote;
    }


    @ApiOperation(value = "Supprime la note d'un patient")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "The patient with id {id} to delete note doesn't exist in database")})
    @DeleteMapping(value = "/patient-note/{patientId}")
    public ResponseEntity<?> deletePatientNote(@PathVariable(value = "patientId") int patientId){
        PatientNote patientNoteToDelete = patientNoteRepository.findPatientNoteByPatientId(patientId);
        String result = "Patient note successfully deleted";
        if(Objects.isNull(patientNoteToDelete)){
            throw new DataNotFoundExecption("The patient with id " + patientId + " to delete note doesn't exist in database");
        } else {
            patientNoteRepository.delete(patientNoteToDelete);
        }
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Récupèration de toutes les notes")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Every notes in database")})
    @GetMapping(value = "/patientsNotes")
    public List<PatientNote> findAllPatientsNotes(){
        List<PatientNote> patientNotesList = patientNoteRepository.findAll();
        if (patientNotesList.isEmpty()){
            throw new DataNotFoundExecption("Every notes in database");
        }
        return patientNotesList;
    }
}
