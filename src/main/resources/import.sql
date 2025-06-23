INSERT INTO tb_tipo (name) VALUES ('Luz');        

INSERT INTO tb_morador (nome, cpf, data_nascimento, celular, email, contato_familia, login, senha, foto) VALUES ('Samuel', '11993347666', '2004-05-12', '3196903134', 'samuferraz12052004@gmail.com', 'Mãe: 31988994231', 'samuel', '$2a$10$BEA2.iTet0ngrkNwcHXeY.S0hRMuaz1Lb2fV3ZcJe.mF97EiilYmi', '.');
INSERT INTO tb_morador (nome, cpf, data_nascimento, celular, email, contato_familia, login, senha, foto) VALUES ('Ana', '98765432100', '2000-08-14', '31988887777', 'ana@gmail.com', 'Pai: 31977778888', 'ana', '$2a$10$7SFDNZaJkpjvhC1BVazx3e9vkSL7K1Fsn33J8oZn5bwx6m1FjnFpC', '*');
INSERT INTO tb_morador (nome, cpf, data_nascimento, celular, email, contato_familia, login, senha, foto) VALUES ('João', '11122233344', '1999-09-16', '31987654321', 'joao@gmail.com', 'Tia: 31966665555', 'joao', '$2a$10$UPRNljPXKD0BKFa/gJLHXeoI13BuJYi4pn00ClrTjzZAUag1fjoZC', '^\');


INSERT INTO tb_conta (valor, data_vencimento, situacao, id_morador, id_tipo_conta, observacao) VALUES (150.00, '2025-07-10', 'PENDENTE', 1, 1, 'Conta de luz de julho');

INSERT INTO tb_rateio (valor, situacao, id_morador, id_conta) VALUES (75.00, 'EM_ABERTO', 2, 1);
INSERT INTO tb_rateio (valor, situacao, id_morador, id_conta) VALUES (75.00, 'PAGO', 3, 1);

INSERT INTO tb_historico (id_conta, id_morador, data, situacao) VALUES (1, 1, '2025-06-25T14:30:00', 'PENDENTE'); 
