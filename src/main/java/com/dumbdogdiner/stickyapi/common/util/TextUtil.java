/*
 * Copyright (c) 2020-2021 DumbDogDiner <dumbdogdiner.com>. All rights reserved.
 * Licensed under the MIT license, see LICENSE for more information...
 */
package com.dumbdogdiner.stickyapi.common.util;

import java.util.HashMap;

import org.jetbrains.annotations.NotNull;

public class TextUtil {
    static HashMap<Character, Integer> characterWidths = new HashMap<>() {
        {
            put('!', 1);
            put(',', 1);
            put('\'', 1);
            put('.', 1);
            put(':', 1);
            put(';', 1);
            put('i', 1);
            put('|', 1);
            put('!', 1);

            put('`', 2);
            put('l', 2);

            put(' ', 3);
            put('(', 3);
            put(')', 3);
            put('*', 3);
            put('I', 3);
            put('[', 3);
            put(']', 3);
            put('t', 3);
            put('{', 3);
            put('}', 3);

            put('<', 4);
            put('>', 4);
            put('f', 4);
            put('k', 4);

            put('@', 6);
            put('~', 6);
        }
    };

    // Uses info from:
    /**
     * Get the width of a character (https://minecraft.gamepedia.com/Language#Font)
     * 
     * @param c
     * @return
     */
    public static int getCharacterWidth(@NotNull char c) {
        if (c < 32 || c > 126) {
            // Not presently implemented, would require rendering TTF
            return -1;
        }

        if (characterWidths.containsKey(c)) {
            return characterWidths.get(c);
        }
        return 5;
    }
}
