package refactown.cleancode.megasena;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Versão com nomes, formatação e comentários melhorados.
 * O método ainda é monolítico e muito grande (34 linhas).
 */
public class MegaSenaV1 {

    /**
     * Método que calcula o valor que uma certa aposta deve receber em função do número de acertos.
     *
     * @param numerosApostados Os números jogados em uma aposta.
     * @param premioTotal      O valor to prêmio em relação ao número de acertos.
     * @return O valor do prêmio a ser recebido por esta aposta.
     */

    // Esse método deve apenas calcular o premio. Está com mais responsabilidades
    public double calculaPremio(List<Integer> numerosApostados, double premioTotal) {
        List<Integer> numerosValidos = new ArrayList<>();

        // Trecho de validação com divida técnica
        // Pode virar método para ser reusado
        for (Integer apostado : numerosApostados) {
            if (apostado < 1 || apostado > 60) {
                return 0.0; // inválido
            }

            // Poderia retornar uma exception
            if (numerosValidos.contains(apostado)) {
                return 0.0; // repetido
            }
            numerosValidos.add(apostado);
        }

        // Métodos podem ser desmembrados
        if (numerosValidos.size() >= 6 && numerosValidos.size() <= 15) {
            List<Integer> numerosSorteados = new ArrayList<>();
            int numeroSorteado;
            while (numerosSorteados.size() < 6) {
                numeroSorteado = new Random().nextInt(59) + 1;
                if (!numerosSorteados.contains(numeroSorteado)) {
                    numerosSorteados.add(numeroSorteado);
                }
            }

            int acertos = 0;
            for (Integer apostado : numerosApostados) {
                if (numerosSorteados.contains(apostado)) {
                    acertos++;
                }
            }

            if (acertos == 6) {
                return premioTotal; // Sena = 100%
            } else if (acertos == 5) {
                return premioTotal * 0.2; // Quina = 20%
            } else if (acertos == 4) {
                return premioTotal * 0.05; // Quadra = 5%
            }
        }
        // Caso falhe, deve retornar exception
        return 0.0;
    }
}
