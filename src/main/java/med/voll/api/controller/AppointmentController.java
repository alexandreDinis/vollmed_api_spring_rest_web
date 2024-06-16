package med.voll.api.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DataAppointmentDTO;
import med.voll.api.model.appointment.ScheduleData;
import med.voll.api.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    @PostMapping
    @Transactional
    public ResponseEntity schedule (@RequestBody @Valid ScheduleData data) {

        var dataDTO = service.schedule(data);

        return ResponseEntity.ok(dataDTO);
    }
}
