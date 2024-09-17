package com.emazon.stockmicroservice.domain.util;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BrandConstantsTest {

    @Test
    void when_ClassIsTriedToBeInstantiated_Expect_ReturnsIllegalStateException() {
        // Use reflexion to access private constructor
        Constructor<BrandConstants> constructor = null;
        try {
            constructor = BrandConstants.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            InvocationTargetException thrown = assertThrows(InvocationTargetException.class, constructor::newInstance);
            assertTrue(thrown.getCause() instanceof IllegalStateException);
            assertEquals("Utility class", thrown.getCause().getMessage());
        } catch (NoSuchMethodException e) {
            fail("Constructor not found");
        }
    }

    @Test
    void when_ExpectedValuesAreGiven_Expect_ConstantsValuesMatches() {
        assertEquals("Field 'name' cannot be null", BrandConstants.NAME_NULL_MESSAGE);
        assertEquals("Field 'description' cannot be null", BrandConstants.DESCRIPTION_NULL_MESSAGE);
        assertEquals("Field 'name' cannot be longer than 50 characters", BrandConstants.NAME_OVERSIZED_MESSAGE);
        assertEquals("Field 'description' cannot be longer than 120 characters", BrandConstants.DESCRIPTION_OVERSIZED_MESSAGE);

        assertEquals(50, BrandConstants.MAX_NAME_LENGTH);
        assertEquals(120, BrandConstants.MAX_DESCRIPTION_LENGTH);
    }

    @Test
    void when_EnumConstantsAreProvided_ValuesAreObtainedCorrectly() {
        //verify enum contains expected values
        assertEquals(BrandConstants.Field.NAME, BrandConstants.Field.valueOf("NAME"));
        assertEquals(BrandConstants.Field.DESCRIPTION, BrandConstants.Field.valueOf("DESCRIPTION"));
    }
}