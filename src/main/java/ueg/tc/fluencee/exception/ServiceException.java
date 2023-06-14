package ueg.tc.fluencee.exception;

public class ServiceException extends RuntimeException {

    private final SistemaMessageCode messageCode;

    public ServiceException(SistemaMessageCode messageCode) {
        super(messageCode.getCode()); // Define a mensagem de erro na superclasse RuntimeException
        this.messageCode = messageCode;
    }

    public String getCode() {
        return messageCode.getCode();
    }

    public int getStatus() {
        return messageCode.getStatus();
    }
}
