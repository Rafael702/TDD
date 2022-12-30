package br.com.alura.tdd.service;

import br.com.alura.tdd.modelo.Funcionario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class BonusServiceTest {

    private BonusService service;
    private Funcionario funcionario;
    @BeforeEach
    public void setup(){
        this.service = new BonusService();
        this.funcionario = new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal("25000"));
    }

    @Test
    void bonusDeveriaSerZeroParaFuncionarioComSalarioMuitoAlto() {
//        assertThrows(IllegalArgumentException.class, () ->
//                service.calcularBonus(
//                        new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal("25000"))
//                ));
// Outro jeito
        try {
            service.calcularBonus(funcionario);
            fail("Nao deu a exception");
        } catch (Exception e) {
            assertEquals("Funcionario com salario maior do que R$100,00 nao pode receber bonus!", e.getMessage());
        }

    }

    @Test
    void bonusDeveriaSerDezPorCentoDoSalario() {
        funcionario.setSalario(new BigDecimal("2500"));
        BigDecimal bonus = service.calcularBonus(funcionario);

        assertEquals(new BigDecimal("250.0"), bonus);
    }

    @Test
    void bonusDeveriaSerDezPorCentoParaSalarioDeExatamenteDezMil() {
        funcionario.setSalario(new BigDecimal("10000"));
        BigDecimal bonus = service.calcularBonus(funcionario);

        assertEquals(new BigDecimal("1000.0"), bonus);
    }
}