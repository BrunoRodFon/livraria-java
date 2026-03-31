package com.livraria.service.impl;

import com.livraria.impl.MultaService;
import com.livraria.model.Emprestimo;
import com.livraria.model.Multa;
import com.livraria.repository.MultaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MultaServiceTest {

    @Mock
    private MultaRepository multaRepository;

    @InjectMocks
    private MultaService multaService;

    @Test
    void deveGerarMultaQuandoHouverAtraso() {

        // 🔹 ARRANGE (preparação do cenário)

        Emprestimo emp = new Emprestimo();

        long diasAtraso = 5;

        // 🔹 ACT (executa o metodo que queremos testar)
        multaService.gerarMultaAtraso(emp, diasAtraso);

        // 🔹 ASSERT (validação)

        // Verifica se o save foi chamado UMA vez
        verify(multaRepository, times(1)).save(any(Multa.class));

        // Captura a multa que foi salva
        verify(multaRepository).save(argThat(multa ->
                multa.getEmprestimo().equals(emp) &&              // ✔ associação correta
                        multa.getTipo() == Multa.Tipo.ATRASO &&           // ✔ tipo correto
                        multa.getStatus() == Multa.Status.PENDENTE &&     // ✔ status correto
                        multa.getValor() == 10.0                          // ✔ 5 dias * 2.0
        ));
    }
}