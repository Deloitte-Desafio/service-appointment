-- Inserção de usuários com senhas criptografadas (bcrypt)
-- Senhas descriptadas para teste:
-- test: test123
-- Rafael Mendes: senha123
-- Sofia Almeida: test123
INSERT INTO tb_user (nome, email, senha, tipo_usuario) VALUES
('test', 'test1@example.com', '$2a$10$P8nm6/Cg2M8sZdIfzr0glecGPnYIwXHDQHXRI9RSFNS9upM/mpJrG', 'CLIENTE'),
('Rafael Mendes', 'rafael.mendes@example.com', '$2a$10$mgupGUWBWtrXv2XgZP/GtOatTWqJ2Yw.agpzNQ/dLiQGjSb16uMdW', 'PROFISSIONAL'),
('Sofia Almeida', 'sofia.almeida@example.com', '$2a$10$P8nm6/Cg2M8sZdIfzr0glecGPnYIwXHDQHXRI9RSFNS9upM/mpJrG', 'PROFISSIONAL');

INSERT INTO tb_servico (nome, descricao, duracao_minutos, profissional_id) VALUES
('Tintura de Cabelo', 'Coloração completa', 90, 2),
('Limpeza de Pele', 'Limpeza facial profunda', 60, 3),
('Escova Modelada', 'Escova com modelagem', 45, 3),
('Debugger', 'Debuggar código é uma beleza', 30, 6);

INSERT INTO tb_disponibilidade (profissional_id, dia_da_semana, hora_inicio, hora_fim) VALUES
(6, 'SEGUNDA', '09:00:00', '12:00:00'),
(6, 'SEGUNDA', '13:00:00', '18:00:00'),
(6, 'TERCA', '09:00:00', '12:00:00'),
(6, 'TERCA', '13:00:00', '18:00:00'),
(6, 'QUARTA', '09:00:00', '12:00:00'),
(6, 'QUARTA', '13:00:00', '18:00:00'),
(6, 'QUINTA', '09:00:00', '12:00:00'),
(6, 'QUINTA', '13:00:00', '18:00:00'),
(6, 'SEXTA', '09:00:00', '12:00:00'),
(6, 'SEXTA', '13:00:00', '18:00:00'),
(7, 'SEGUNDA', '09:00:00', '12:00:00'),
(7, 'SEGUNDA', '13:00:00', '18:00:00'),
(7, 'TERCA', '09:00:00', '12:00:00'),
(7, 'TERCA', '13:00:00', '18:00:00'),
(7, 'QUARTA', '09:00:00', '12:00:00'),
(7, 'QUARTA', '13:00:00', '18:00:00'),
(7, 'QUINTA', '09:00:00', '12:00:00'),
(7, 'QUINTA', '13:00:00', '18:00:00'),
(7, 'SEXTA', '09:00:00', '12:00:00'),
(7, 'SEXTA', '13:00:00', '18:00:00');

INSERT INTO tb_agendamento (cliente_id, profissional_id, servico_id, data_hora_inicio, data_hora_fim, status) VALUES
(1, 2, 1, '2025-05-15 10:00:00', '2025-05-15 11:30:00', 'CONCLUIDO'),
(1, 3, 2, '2025-06-01 09:00:00', '2025-06-01 10:00:00', 'CANCELADO_CLIENTE'),
(1, 3, 3, '2025-06-05 11:00:00', '2025-06-05 11:45:00', 'AGENDADO'),
(1, 2, 1, '2025-07-02 14:00:00', '2025-07-02 15:30:00', 'AGENDADO'),
(1, 3, 2, '2025-05-20 13:00:00', '2025-05-20 14:00:00', 'CANCELADO_PROFISSIONAL'),
(5, 6, 1, '2025-07-15 10:00:00', '2025-05-15 11:30:00', 'AGENDADO'),
(5, 6, 1, '2025-06-04 16:00:00', '2025-06-04 17:30:00', 'AGENDADO'),
(5, 6, 1, '2025-07-16 10:00:00', '2025-05-15 11:30:00', 'CANCELADO_PROFISSIONAL'),
(5, 6, 1, '2025-05-15 10:00:00', '2025-05-15 11:30:00', 'CONCLUIDO');