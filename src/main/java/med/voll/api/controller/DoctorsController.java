package med.voll.api.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DoctorsDTO;
import med.voll.api.model.doctors.DoctorUpdate;
import med.voll.api.model.doctors.DoctorsData;
import med.voll.api.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
public class DoctorsController {

    @Autowired
    private DoctorService service;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DoctorsData doctor, UriComponentsBuilder uriBuilder) {

        var registerDoctors = service.registerDoctors(doctor);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(registerDoctors.getId()).toUri();

        return ResponseEntity.created(uri).body(new DoctorsDTO(registerDoctors));
    }

    @GetMapping
    public Page<DoctorsDTO> listaDeMedicos(Pageable paginacao) {

        return service.listaDeMedicos(paginacao);
    }

    @PutMapping
    @Transactional
    public ResponseEntity  atualizarMedico(@RequestBody @Valid DoctorUpdate data)
    {
        var doctor = service.atualizarMedico(data);

        return ResponseEntity.ok(new DoctorsDTO(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){

        var doctor = service.detalhar(id);

        return ResponseEntity.ok(doctor);
    }
}
