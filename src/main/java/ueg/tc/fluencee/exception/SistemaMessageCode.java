package ueg.tc.fluencee.exception;

import br.ueg.prog.webi.api.exception.MessageCode;

public enum SistemaMessageCode implements MessageCode {
    ERRO_CAMPOS_OBRIGATORIOS("Campos obrigatórios não preenchidos.", 400),
    ERRO_CRIAR_USUARIO("Erro ao criar usuário", 404),
    USUARIO_INATIVO("A ação não pode ser efetuada, pois o usuário está inativo.", 404),
    SENHA_ERRADA("A senha antiga não confere com sua senha.", 400),
    EMAIL_ERRADO("O email que você digitou não confere com o email da sua conta.", 400),
    USUARIO_NAO_ENCONTRADO("O usuário solicitado não foi encontrado.", 404),
    COMPRIMENTO_SENHA("A senha deve ter entre 8 e 35 caracteres!", 400),
    SENHA_LETRA_NUMERO("A senha deve ter no mínimo 1 letra e 1 número!", 400),
    EMAIL_REPETIDO("Este email já está cadastrado! Tente outro email se quiser prosseguir com o cadastro.", 400),
    COMPRIMENTO_EMAIL("O email deve ter no máximo 256 caracteres!", 400),
    EMAIL_INVALIDO("O email deve seguir o padrão email@email.com!", 400),
    COMPRIMENTO_NOME("O nome deve ter entre 3 e 200 caracteres!", 400);
    private final String code;
    private final Integer status;
    SistemaMessageCode(final String code, final Integer status) {
        this.code = code;
        this.status = status;
    }
    public String getCode() { return code; }
    public Integer getStatus() { return status; }
    @Override
    public String toString() { return code; }
}
