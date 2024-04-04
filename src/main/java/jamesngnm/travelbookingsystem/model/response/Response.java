package jamesngnm.travelbookingsystem.model.response;


import jakarta.servlet.http.HttpServletResponse;
import jamesngnm.travelbookingsystem.exception.ResponseException;

public class Response<T> {
    private int status;
    private int code;
    private String message;
    private String exception;
    private boolean successful;
    private T data;

    public Response() {
        this.status = HttpServletResponse.SC_OK;
        this.code = 0;
        this.message = "Successful";
        this.successful = Boolean.TRUE;
        this.data = null;
    }

    public Response(T data) {
        this.status = HttpServletResponse.SC_OK;
        this.code = 0;
        this.message = "Successful";
        this.successful = Boolean.TRUE;
        if (data instanceof ResponseException responseException) {
            this.status = responseException.getError().getStatus();
            this.code = responseException.getError().getCode();
            this.message = "Error";
            this.exception = responseException.getMessage();
            this.successful = Boolean.FALSE;
        } else {
            this.data = data;
        }
    }

    public Response(int status, T data) {
        this.status = status;
        this.code = 0;
        this.message = "Successful";
        this.successful = Boolean.TRUE;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }
    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public String getException() {
        return exception;
    }
    public boolean isSuccessful() {
        return successful;
    }
    public T getData() {
        return data;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setException(String exception) {
        this.exception = exception;
    }
    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
    public void setData(T data) {
        this.data = data;
    }
    public int hashCode() {
        return ((((((this.getStatus() == 0) ? 43 : this.getStatus()) * 59 + this.getCode()) * 59 + this.getMessage().hashCode()) * 59 + (this.getException() == null ? 43 : this.getException().hashCode())) * 59 + (this.isSuccessful() ? 79 : 97) * 59 + ((this.getData() == null) ? 43 : this.getData().hashCode()));
    }
    public String toString() {
        return "Response(status=" + this.getStatus() + ", code=" + this.getCode() + ", message=" + this.getMessage() + ", exception=" + this.getException() + ", successful=" + this.isSuccessful() + ", data=" + this.getData() + ")";
    }
}
