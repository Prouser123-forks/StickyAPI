/*
 * Copyright (c) 2020-2021 DumbDogDiner <dumbdogdiner.com>. All rights reserved.
 * Licensed under the MIT license, see LICENSE for more information...
 */
package com.dumbdogdiner.stickyapi.common.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberUtilTest {
    // TODO: add getPercentage tests.

    @Test
    public void testIsNumeric() {
        assertTrue(NumberUtil.isNumeric(""));
        assertTrue(NumberUtil.isNumeric("123"));
        assertFalse(NumberUtil.isNumeric("  "));
        assertFalse(NumberUtil.isNumeric("12 3"));
        assertFalse(NumberUtil.isNumeric("ab2c"));
        assertFalse(NumberUtil.isNumeric("12-3"));
        assertFalse(NumberUtil.isNumeric("12.3"));
    }

    @RepeatedTest(100)
    @SuppressWarnings("deprecation") // getRandomNumber is deprecated
    public void testGetRandomNumber() {
        int number = NumberUtil.getRandomNumber(0, 100);
        assertTrue(number >= 0); // number is 0 or more
        assertTrue(number <= 100); // number is 100 or less
    }

    @Test
    @SuppressWarnings("deprecation") // getRandomNumber is deprecated
    public void testGetRandomNumberIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            NumberUtil.getRandomNumber(1, 0);
        });
    }

    // TODO: intHelper case 0 and default case tests?

    @Test
    public void testIntHelper() {
        assertEquals(NumberUtil.longToInt(1L), 1);
    }

    @Test
    public void testIntHelperMax() {
        assertEquals(NumberUtil.longToInt(((long)Integer.MAX_VALUE) + 1L), Integer.MAX_VALUE);
    }

    @Test
    public void testIntHelperMin() {
        assertEquals(NumberUtil.longToInt(((long) Integer.MIN_VALUE) - 1L), Integer.MIN_VALUE);
    }
}
