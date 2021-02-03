package org.mediabump.auth.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mediabump.auth.domain.models.GrantType;

import static org.junit.jupiter.api.Assertions.*;

class GrantTypeTest {

    @ParameterizedTest
    @ValueSource(strings = {"AUTHORIZATION_CODE", "authorization_code", "Authorization_Code"})
    void testAuthorizationCodeTypeEquals_True(String checkType) {
        assertTrue(GrantType.isValid(checkType), checkType);
    }
}
