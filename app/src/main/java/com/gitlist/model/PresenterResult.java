package com.gitlist.model;



public class PresenterResult<Response> {

    private Response response;

    private String error;

    public PresenterResult(Response response) {
        this.response = response;
    }

    public PresenterResult(String error) {
        this.error = error;
    }

    public Response getResponse() {
        return response;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "PresenterResult{" +
                "response=" + response +
                ", error='" + error + '\'' +
                '}';
    }
}
