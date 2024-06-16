package med.voll.api.repository;

import med.voll.api.model.doctors.Doctor;
import med.voll.api.model.doctors.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByStatusTrue(Pageable paginacao);


   @Query("select d from Doctor d where d.status = true and d.especialidade = :especialidade and d.id not in(select a.doctor.id from Appointment a where a.date = :date) order by rand() limit 1")
    Doctor escolherMedicoDisponivel(Especialidade especialidade, LocalDateTime date);

   @Query("select d.status from Doctor d where d.id = :id")
    Boolean findStatusById(@Param("id")Long id);
}
