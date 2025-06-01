INSERT INTO tb_user (nome, email, senha, tipo_usuario) VALUES
('João Silva', 'joao.silva@example.com', 'senha123', 'CLIENTE'),
('Maria Oliveira', 'maria.oliveira@example.com', 'senha456', 'CLIENTE'),
('Dr. Carlos Souza', 'carlos.souza@example.com', 'senha789', 'PROFISSIONAL'),
('Dra. Ana Costa', 'ana.costa@example.com', 'senha101', 'PROFISSIONAL');

INSERT INTO tb_servico (nome, descricao, duracao_minutos, profissional_id) VALUES
('Corte de Cabelo', 'Corte masculino ou feminino', 30, 3),
('Manicure', 'Serviço de manicure e pedicure', 60, 4),
('Massagem Relaxante', 'Massagem terapêutica de 1 hora', 60, 3);

INSERT INTO tb_disponibilidade (profissional_id, dia_da_semana, hora_inicio, hora_fim) VALUES
(3, 'SEGUNDA', '09:00:00', '17:00:00'),
(3, 'QUARTA', '10:00:00', '18:00:00'),
(4, 'TERCA', '08:00:00', '16:00:00'),
(4, 'QUINTA', '09:00:00', '17:00:00');

INSERT INTO tb_agendamento (cliente_id, profissional_id, servico_id, data_hora_inicio, data_hora_fim, status) VALUES
(1, 3, 1, '2025-06-02 10:00:00', '2025-06-02 10:30:00', 'AGENDADO'),
(2, 4, 2, '2025-06-03 09:00:00', '2025-06-03 10:00:00', 'AGENDADO'),
(1, 3, 3, '2025-06-04 11:00:00', '2025-06-04 12:00:00', 'AGENDADO');