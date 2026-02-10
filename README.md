# Foxbit Trade Bot

Bot de trade automatizado para a exchange Foxbit utilizando Kotlin, Spring Boot WebFlux e arquitetura orientada a domínio.

O projeto foi construído priorizando:

- código limpo
- separação de responsabilidades
- configuração externa
- facilidade de evolução
- execução segura (paper trade)

---

## Status atual

O bot:

- conecta na API oficial da Foxbit
- consome dados de mercado (ticker 24h)
- consulta saldo da conta
- executa estratégia baseada em thresholds
- valida risco antes de enviar ordem
- **simula envio** (não movimenta dinheiro real)
- suporta múltiplos ativos simultaneamente
- recebe configuração por variáveis externas
- possui logs simplificados e amigáveis para leitores de tela

---

## Arquitetura

A aplicação é dividida em camadas.

application -> orquestra o fluxo
domain -> regras de negócio
infra -> integração com a Foxbit
config -> propriedades externas
arduino
Copiar

Fluxo do ciclo:

scheduler
↓
trading cycle service
↓
gateway
↓
strategy
↓
risk manager
↓
paper trade (log)
yaml
Copiar

---

## Conceitos importantes

### Strategy
Decide se deve comprar, vender ou não fazer nada.

### Risk Manager
Garante que existe saldo antes da operação.

### Gateway
Traduz chamadas da Foxbit para modelos do domínio.

### Paper Trade
Mesmo quando aprovado, apenas loga a execução.

---

## Configuração

Tudo é configurável sem recompilar.

Valores vêm do `application.yaml` mas podem ser sobrescritos via:

- variáveis de ambiente
- system properties (`-D`)

---

## application.yaml

```yaml
bot:
  strategy:
    buy-below: ${BOT_STRATEGY_BUY_BELOW:300000}
    sell-above: ${BOT_STRATEGY_SELL_ABOVE:350000}
    min-qty: ${BOT_MIN_QTY:0.0001}
    max-qty: ${BOT_MAX_QTY:0.001}

  symbols: ${BOT_SYMBOLS:BTC_BRL}
  tick-interval-ms: ${BOT_TICK_INTERVAL_MS:60000}
 
Executando local
Exemplo: BTC em dolar, ciclo de 5 minutos
Windows:
nginx
Copiar
gradlew bootRun ^
  -DBOT_SYMBOLS=BTC_USDT ^
  -DBOT_TICK_INTERVAL_MS=300000
Linux:
bash
Copiar
./gradlew bootRun \
  -DBOT_SYMBOLS=BTC_USDT \
  -DBOT_TICK_INTERVAL_MS=300000
 
Variáveis disponíveis
Variável
Descrição
BOT_SYMBOLS
Lista de mercados separados por vírgula
BOT_STRATEGY_BUY_BELOW
Preço abaixo do qual compra
BOT_STRATEGY_SELL_ABOVE
Preço acima do qual vende
BOT_TICK_INTERVAL_MS
Intervalo do ciclo
FOXBIT_API_KEY
Chave da API
FOXBIT_API_SECRET
Segredo da API
Copiar tabela
 
Logs
Os logs seguem padrão:
• 
sem caracteres especiais
• 
frases curtas
• 
foco operacional
• 
amigáveis para leitura por voz
Exemplo:
csharp
Copiar
[btcbrl] Observando mercado
[btcbrl] Preco ultimo=370000 compra=369900 venda=370100
[btcbrl] Estrategia quer BUY
[btcbrl] Ordem bloqueada Insufficient balance
[btcbrl] Ciclo finalizado
 
Segurança
Nenhuma ordem real é enviada.
A aplicação está em modo simulação.
 
Próximos passos naturais
Evoluções previstas para maturidade do robô:
• 
controle de posição (saber se já está comprado)
• 
evitar ordens repetidas
• 
percentual de carteira ao invés de quantidade fixa
• 
métricas e telemetria
• 
persistência de estado
• 
execução real
 
Tecnologias
• 
Kotlin
• 
Java 21
• 
Spring Boot 4
• 
WebFlux
• 
Reactor
 
Objetivo do projeto
Servir como base sólida para estudo e evolução de um sistema real de trading automatizado.
 
Aviso
Uso educacional. Não utilize em produção financeira sem auditoria adequada.
