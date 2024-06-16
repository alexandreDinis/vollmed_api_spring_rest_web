package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.PatientDTO;
import med.voll.api.model.patient.PatientUpdate;
import med.voll.api.model.patient.PatientData;
import med.voll.api.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
@SecurityRequirement(name = "bearer-key")
public class PatientsController {

    @Autowired
    private PatientService service;

    @PostMapping
    @Transactional
    public ResponseEntity registerPatient(@RequestBody @Valid PatientData patient, UriComponentsBuilder uriBuilder){

        var registerPatient = service.registerPatient(patient);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(registerPatient.getId()).toUri();

        return ResponseEntity.created(uri).body(new PatientDTO(registerPatient));
    }

    @GetMapping
    public Page<PatientDTO> listaDePacientes(Pageable paginacao){

        return service.listaDePacientes(paginacao);
    }


    @PutMapping
    @Transactional
    public ResponseEntity atualizarPaciente(@RequestBody @Valid PatientUpdate data){

        var patient = service.atualizarPaciente(data);

        return ResponseEntity.ok(new PatientDTO(patient));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {

        var patient = service.detalhar(id);

        return ResponseEntity.ok(patient);
    }
}
