package com.emazon.stockmicroservice.domain.util;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ItemConstantsTest {

    @Test
    void when_AttemptIsMadeToCreateAClassInstance_Expect_ThrowsException() throws NoSuchMethodException {
        // Given:private constructor
        Constructor<ItemConstants> constructor = ItemConstants.class.getDeclaredConstructor();

        // When: Constructor is accessible
        constructor.setAccessible(true);

        // Then: Verifies exception is thrown when class is tried to be instantiated
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }
}