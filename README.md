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

### PUT /tasks/atualizar-task/{id}

Atualiza uma tarefa existente com base no ID fornecido.

### DELETE /tasks/{id}

Exclui uma tarefa com base no ID fornecido.
