package med.voll.api.domain.medico;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.paciente.Convenio;
import med.voll.api.domain.paciente.DadosCadastroPaciente;
import med.voll.api.domain.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@DisplayName("Test using local test database (Postgres)")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;


    @Test
    @DisplayName("Should return 0 when there's no medic available on specific date")
    void shouldNotReturnMedicAvailable() {

        var nextMonday10Am = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medico = cadastrarMedico(new DadosCadastroMedico("jao2", "11111111111","jap@vollmed.com","121212", Especialidade.CARDIOLOGIA, dadosEndereco()));

        var paciente2 = cadastrarPaciente(new DadosCadastroPaciente("Pedro", "2323ewwe@teste.com","11111111111",dadosEndereco(), Convenio.PARTICULAR, null));

        cadastrarConsulta(medico, paciente2, nextMonday10Am);


        var value = medicoRepository.getRandomMedic(Especialidade.CARDIOLOGIA, nextMonday10Am);

        assertEquals(0, value.size());

    }

    @Test
    @DisplayName("Should return next medic when the requested one isnt available")
    void shouldNotReturnMedicAvailable2() {

        var nextMonday10Am = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medico = cadastrarMedico(new DadosCadastroMedico("jao", "11111111111","jap@vollmed.com","121212", Especialidade.CARDIOLOGIA, dadosEndereco()));
        var medico2 = cadastrarMedico(new DadosCadastroMedico("jao2", "11111111111","jap@vollmed.com","121212", Especialidade.CARDIOLOGIA, dadosEndereco()));

        var paciente2 = cadastrarPaciente(new DadosCadastroPaciente("Pedro", "2323ewwe@teste.com","11111111111",dadosEndereco(), Convenio.PARTICULAR, null));

        cadastrarConsulta(medico, paciente2, nextMonday10Am);


        var value = medicoRepository.getRandomMedic(Especialidade.CARDIOLOGIA, nextMonday10Am);

        assertEquals(medico2, value.get(0));

    }

    private void cadastrarConsulta(Medico m, Paciente p, LocalDateTime d){
        var c = new Consulta(null, m,p,d);
        m.addConsulta(c);
        p.addConsulta(c);
        em.persist(c);
    }

    private Medico cadastrarMedico(DadosCadastroMedico dto){
        var m = new Medico(dto);
        em.persist(m);
        return m;
    }

    private Paciente cadastrarPaciente(DadosCadastroPaciente dto){
        var p = new Paciente(dto);
        em.persist(p);
        return p;
    }

    private DadosEndereco dadosEndereco(){
        return new DadosEndereco("1111","1111","12222222","1111","SP","1111",null);
    }
}