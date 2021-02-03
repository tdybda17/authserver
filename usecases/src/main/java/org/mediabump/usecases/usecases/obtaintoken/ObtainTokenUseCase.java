package org.mediabump.usecases.usecases.obtaintoken;

import org.mediabump.auth.domain.models.GrantType;
import org.mediabump.usecases.repository.ClientRepository;
import org.mediabump.usecases.request.ValidationException;

public class ObtainTokenUseCase {
    private final ClientRepository clientRepository;
    private final ObtainTokenRequest request;
    private final ObtainTokenListener listener;

    public ObtainTokenUseCase(
            ClientRepository clientRepository,
            ObtainTokenRequest request,
            ObtainTokenListener listener) {
        this.clientRepository = clientRepository;
        this.request = request;
        this.listener = listener;
    }

    public void obtain() {
        try {
            request.validate();
        } catch (ValidationException e) {
            listener.onValidationError(e.getMessage());
            return;
        }

        if (!clientRepository.isAuthorized(request.getClientId(), request.getClientSecret())) {
            listener.onUnauthorizedClient();
            return;
        }

        if (!GrantType.isValid(request.getGrantType())) {
            listener.onInvalidGrantType();
            return;
        }

        GrantType grantType = GrantType.from(request.getGrantType());
    }
}
