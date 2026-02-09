# foxbit-trade-bot
Bot para realizar trades na plataforma Foxbit.

## Visão geral
Projeto de bot de trading voltado a criptoativos na Foxbit. O objetivo é automatizar a execução de estratégias de compra e venda, com foco em segurança, controle de risco e observabilidade.

## Objetivos
- Automatizar operações na Foxbit via API.
- Permitir parametrização de estratégias e limites de risco.
- Registrar operações e métricas para análise posterior.

## Escopo (pretendido)
- Conectar à API da Foxbit (autenticação e envio de ordens).
- Coletar cotações e livro de ofertas.
- Executar estratégias simples (ex.: tendência, média móvel, grid).
- Gerenciar risco (limite de perdas, tamanho de posição).
- Logs, alertas e relatórios básicos.

## Fora de escopo (por enquanto)
- Consultoria financeira.
- Estratégias complexas de alta frequência.
- Custódia e gestão de chaves fora do ambiente local.

## Requisitos
- Conta ativa na Foxbit.
- Chaves de API com permissões adequadas.
- Ambiente local com dependências do projeto (a definir).

## Configuração
1) Crie um arquivo de variáveis de ambiente.
2) Informe as credenciais da API e parâmetros de risco.
3) Ajuste a estratégia desejada.

Exemplo de variáveis (ilustrativo):
- FOXBIT_API_KEY
- FOXBIT_API_SECRET
- TRADE_SYMBOL
- MAX_DAILY_LOSS
- ORDER_SIZE

## Execução (pretendida)
- Inicializar o bot em modo simulação.
- Validar resultados e logs.
- Ativar modo real somente após validação.

## Arquitetura (planejada)
- **Data Provider**: captura de cotações/ordens.
- **Strategy Engine**: avaliação de sinais.
- **Risk Manager**: limites e validações.
- **Order Executor**: envio e monitoramento de ordens.
- **Logger/Reporter**: logs e métricas.

## Fluxo operacional
1) Coleta de dados de mercado.
2) Geração de sinais pela estratégia.
3) Verificação de risco e limites.
4) Envio de ordem.
5) Monitoramento e registro.

## Segurança
- Chaves de API nunca devem ser commitadas.
- Usar variáveis de ambiente/secret manager.
- Aplicar permissões mínimas necessárias.

## Roadmap (sugestão)
- [ ] Estrutura base e configuração.
- [ ] Integração com API da Foxbit.
- [ ] Estratégias básicas.
- [ ] Módulo de risco.
- [ ] Relatórios e dashboards.

## Contribuição
- Abra issues para bugs e melhorias.
- Envie PRs com descrição clara.

## Licença
A definir.
