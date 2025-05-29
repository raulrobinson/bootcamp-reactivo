package com.bootcamp.ws.infrastructure.inbound.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Error DTO", title = "Error DTO")
public class ErrorDto {

    @Schema(description = "Error code", example = "Code of the error")
    private String code;

    @Schema(description = "Error message", example = "Error message")
    private String message;

    @Schema(description = "Parameter that caused the error", example = "Parameter that caused the error")
    private String parameter;

    @Schema(description = "Date of the error occurrence", example = "2023-10-01T12:00:00Z")
    private String date;

    public ErrorDto() {
    }

    public ErrorDto(String code, String message, String parameter, String date) {
        this.code = code;
        this.message = message;
        this.parameter = parameter;
        this.date = date;
    }

    public static ErrorDto of(String code, String message, String parameter, String date) {
        return new ErrorDto(code, message, parameter, date);
    }

    // Getters y setters
    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getParameter() {
        return parameter;
    }

    public String getDate() {
        return date;
    }

    public static class Builder {
        private String code;
        private String message;
        private String parameter;
        private String date;

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder parameter(String parameter) {
            this.parameter = parameter;
            return this;
        }

        public Builder date(String date) {
            this.date = date;
            return this;
        }

        public ErrorDto build() {
            return new ErrorDto(code, message, parameter, date);
        }
    }
}
