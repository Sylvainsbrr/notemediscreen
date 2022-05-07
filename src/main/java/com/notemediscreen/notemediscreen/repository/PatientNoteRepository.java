package com.notemediscreen.notemediscreen.repository;

import com.notemediscreen.notemediscreen.entity.PatientNote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientNoteRepository extends MongoRepository<PatientNote, String> {
    public PatientNote findPatientNoteByPatientId(int patientId);
}
