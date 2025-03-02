using System.Collections.Generic;

namespace ValidadorCPFRG
{
    public class AFD
    {
        private Dictionary<(Estado, char), Estado> transicoes;

        public AFD()
        {
            transicoes = new Dictionary<(Estado, char), Estado>();

            // Transições para RG
            GeraTransicoesRG();

            // Transições para CPF
            GeraTransicoesCPF();
        }

        private void AdicionarTransicao(Estado estadoAtual, char caractere, Estado proximoEstado)
        {
            transicoes[(estadoAtual, caractere)] = proximoEstado;
        }

        private void GeraTransicoesRG()
        {
            for (char c = '0'; c <= '9'; c++) {
                AdicionarTransicao(Estado.Inicio, c, Estado.RG_Digito1);
                AdicionarTransicao(Estado.RG_Digito1, c, Estado.RG_Digito2);
                AdicionarTransicao(Estado.RG_Digito2, c, Estado.RG_Digito3);
                AdicionarTransicao(Estado.RG_Ponto1, c, Estado.RG_Digito3);
                AdicionarTransicao(Estado.RG_Digito3, c, Estado.RG_Digito4);
                AdicionarTransicao(Estado.RG_Digito4, c, Estado.RG_Digito5);
                AdicionarTransicao(Estado.RG_Digito5, c, Estado.RG_Digito6);
                AdicionarTransicao(Estado.RG_Ponto2, c, Estado.RG_Digito6);
                AdicionarTransicao(Estado.RG_Digito6, c, Estado.RG_Digito7);
                AdicionarTransicao(Estado.RG_Digito7, c, Estado.RG_Digito8);
                AdicionarTransicao(Estado.RG_Digito8, c, Estado.RG_Traco);
                AdicionarTransicao(Estado.RG_Digito8, c, Estado.RG_Final);
            }
            AdicionarTransicao(Estado.RG_Digito2, '.', Estado.RG_Ponto1);
            AdicionarTransicao(Estado.RG_Digito5, '.', Estado.RG_Ponto2);
            AdicionarTransicao(Estado.RG_Digito8, '-', Estado.RG_Traco);
            AdicionarTransicao(Estado.RG_Traco, 'X', Estado.RG_Final);
        }

        private void GeraTransicoesCPF()
        {
            for (char c = '0'; c <= '9'; c++) {
                AdicionarTransicao(Estado.Inicio, c, Estado.CPF_Digito1);
                AdicionarTransicao(Estado.CPF_Digito1, c, Estado.CPF_Digito2);
                AdicionarTransicao(Estado.CPF_Digito2, c, Estado.CPF_Digito3);
                AdicionarTransicao(Estado.CPF_Digito3, c, Estado.CPF_Digito4);
                AdicionarTransicao(Estado.CPF_Ponto1, c, Estado.CPF_Digito4);
                AdicionarTransicao(Estado.CPF_Digito4, c, Estado.CPF_Digito5);
                AdicionarTransicao(Estado.CPF_Digito5, c, Estado.CPF_Digito6);
                AdicionarTransicao(Estado.CPF_Digito6, c, Estado.CPF_Digito7);
                AdicionarTransicao(Estado.CPF_Ponto2, c, Estado.CPF_Digito7);
                AdicionarTransicao(Estado.CPF_Digito7, c, Estado.CPF_Digito8);
                AdicionarTransicao(Estado.CPF_Digito8, c, Estado.CPF_Digito9);
                AdicionarTransicao(Estado.CPF_Digito9, c, Estado.CPF_Digito10);
                AdicionarTransicao(Estado.CPF_Traco, c, Estado.CPF_Digito10);
                AdicionarTransicao(Estado.CPF_Digito10, c, Estado.CPF_Digito11);
                AdicionarTransicao(Estado.CPF_Digito11, c, Estado.CPF_Final);
            }
            AdicionarTransicao(Estado.CPF_Digito3, '.', Estado.CPF_Ponto1);
            AdicionarTransicao(Estado.CPF_Digito6, '.', Estado.CPF_Ponto2);
            AdicionarTransicao(Estado.CPF_Digito9, '-', Estado.CPF_Traco);
        }

        public Estado ObterProximoEstado(Estado estadoAtual, char caractere)
        {
            if (transicoes.TryGetValue((estadoAtual, caractere), out Estado proximoEstado))
            {
                return proximoEstado;
            }
            return Estado.Invalido;
        }
    }
}