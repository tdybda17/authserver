package org.mediabump.endpoints.endpoints;

import org.springframework.http.HttpStatus;

public interface RestPresenter {
    String getResponse();
    HttpStatus getHttpStatus();
}
