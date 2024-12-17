package ehu.java.interpoldemo.model;

public class Request extends AbstractModel {
    private int requestId;
    private int userId;
    private Criminal criminal;
    private String comment;
    private Status status;

    private Request(RequestBuilder builder) {
        this.requestId = builder.requestId;
        this.userId = builder.userId;
        this.criminal = builder.criminal;
        this.comment = builder.comment;
        this.status = builder.status;
    }

    public int getRequestId() {
        return requestId;
    }

    public int getUserId() {
        return userId;
    }

    public Criminal getCriminal() {
        return criminal;
    }

    public String getComment() {
        return comment;
    }

    public Status getStatus() {
        return status;
    }

    public static class RequestBuilder {
        private int requestId;
        private int userId;
        private Criminal criminal;
        private String comment;
        private Status status = Status.getDefaultStatus();

        public RequestBuilder setRequestId(int requestId) {
            this.requestId = requestId;
            return this;
        }

        public RequestBuilder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public RequestBuilder setCriminal(Criminal criminal) {
            this.criminal = criminal;
            return this;
        }

        public RequestBuilder setComment(String comments) {
            this.comment = comment;
            return this;
        }

        public RequestBuilder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public Request build() {
            return new Request(this);
        }
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestId=" + requestId +
                ", userId=" + userId +
                ", criminal=" + criminal +
                ", comments='" + comment + '\'' +
                ", status=" + status +
                '}';
    }
}
