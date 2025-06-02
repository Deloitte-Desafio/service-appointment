-- Inserção de usuários com senhas criptografadas (bcrypt)
-- Senhas descriptadas para teste:
-- test: senha123
-- Rafael Mendes: senha456
-- Sofia Almeida: senha789
INSERT INTO tb_user (nome, email, senha, tipo_usuario) VALUES
('test', 'test@example.com', '$2a$10$XURP2Z1e8z1J9f2k3l4m5u6v7w8x9y0z1a2b3c4d5e6f7g8h9i0j', 'CLIENTE'),
('Rafael Mendes', 'rafael.mendes@example.com', '$2a$10$Y2k3l4m5n6o7p8q9r0s1t2u3v4w5x6y7z8a9b0c1d2e3f4g5h6i7j', 'PROFISSIONAL'),
('Sofia Almeida', 'sofia.almeida@example.com', '$2a$10$Z3m4n5o6p7q8r9s0t1u2v3w4x5y6z7a8b9c0d1e2f3g4h5i6j7k8', 'PROFISSIONAL');

INSERT INTO tb_servico (nome, descricao, duracao_minutos, profissional_id) VALUES
('Tintura de Cabelo', 'Coloração completa', 90, 2),
('Limpeza de Pele', 'Limpeza facial profunda', 60, 3),
('Escova Modelada', 'Escova com modelagem', 45, 3);

INSERT INTO tb_disponibilidade (profissional_id, dia_da_semana, hora_inicio, hora_fim) VALUES
(2, 'SEGUNDA', '09:00:00', '12:00:00'),
(2, 'SEGUNDA', '13:00:00', '18:00:00'),
(2, 'TERCA', '09:00:00', '12:00:00'),
(2, 'TERCA', '13:00:00', '18:00:00'),
(2, 'QUARTA', '09:00:00', '12:00:00'),
(2, 'QUARTA', '13:00:00', '18:00:00'),
(2, 'QUINTA', '09:00:00', '12:00:00'),
(2, 'QUINTA', '13:00:00', '18:00:00'),
(2, 'SEXTA', '09:00:00', '12:00:00'),
(2, 'SEXTA', '13:00:00', '18:00:00'),
(3, 'SEGUNDA', '09:00:00', '12:00:00'),
(3, 'SEGUNDA', '13:00:00', '18:00:00'),
(3, 'TERCA', '09:00:00', '12:00:00'),
(3, 'TERCA', '13:00:00', '18:00:00'),
(3, 'QUARTA', '09:00:00', '12:00:00'),
(3, 'QUARTA', '13:00:00', '18:00:00'),
(3, 'QUINTA', '09:00:00', '12:00:00'),
(3, 'QUINTA', '13:00:00', '18:00:00'),
(3, 'SEXTA', '09:00:00', '12:00:00'),
(3, 'SEXTA', '13:00:00', '18:00:00');

INSERT INTO tb_agendamento (cliente_id, profissional_id, servico_id, data_hora_inicio, data_hora_fim, status) VALUES
(1, 2, 1, '2025-05-15 10:00:00', '2025-05-15 11:30:00', 'CONCLUIDO'),
(1, 3, 2, '2025-06-01 09:00:00', '2025-06-01 10:00:00', 'CANCELADO_CLIENTE'),
(1, 3, 3, '2025-06-05 11:00:00', '2025-06-05 11:45:00', 'AGENDADO'),
(1, 2, 1, '2025-07-02 14:00:00', '2025-07-02 15:30:00', 'AGENDADO'),
(1, 3, 2, '2025-05-20 13:00:00', '2025-05-20 14:00:00', 'CANCELADO_PROFISSIONAL');