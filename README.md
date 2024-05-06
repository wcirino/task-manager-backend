# Task Manager API - Java 17

Este é um projeto de API para gerenciamento de tarefas desenvolvido em Java 17.

## Endpoints

### GET /tasks/find-all

Retorna todas as tarefas cadastradas.

### GET /tasks/tasks

Retorna uma lista paginada de tarefas.

#### Parâmetros de consulta

- `page`: (opcional) Número da página. Padrão é 0.
- `size`: (opcional) Tamanho da página. Padrão é 10.

### GET /tasks/search

Busca tarefas com base em diferentes critérios.

#### Parâmetros de consulta

- `id`: (opcional) ID da tarefa.
- `title`: (opcional) Título da tarefa.
- `description`: (opcional) Descrição da tarefa.
- `completed`: (opcional) Se a tarefa foi concluída ou não.
- `dt_created`: (opcional) Data de criação da tarefa no formato 'yyyy-MM-dd'.
- `page`: (opcional) Número da página. Padrão é 0.
- `size`: (opcional) Tamanho da página. Padrão é 10.

### GET /tasks/find/task/{id}

Retorna uma tarefa específica com base no ID fornecido.

### PUT /tasks/{id}/concluir

Marca uma tarefa como concluída com base no ID fornecido.

### POST /tasks/inserir-task

Cria uma nova tarefa.

Exemplo de Payload JSON:
```json
{
    "id": 2,
    "titulo": "Desenvolvimento de Recursos",
    "descricao": "Desenvolver novos recursos para a aplicação móvel.",
    "dt_Criacao": "21/04/2024",
    "dt_Conclusao": "02/05/2024",
    "dt_limite": "01/05/2024",
    "status": "CONCLUIDA",
    "responsavel": "NATALIA",
    "prioridade": "MEDIA"
}
## Front-end

O front-end correspondente a esta API pode ser encontrado no seguinte repositório:

[Task Manager Front-end](https://github.com/wcirino/task-manager-front-end)
