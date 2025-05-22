/*
 * Copyright 2019 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package walkingkooka.math;

import walkingkooka.test.Testing;

/**
 * Mixing testing interface for {@link DecimalNumberContext}
 */
public interface MathTesting extends Testing {

    /**
     * Useful constant to verify {@link DecimalNumberContext#zeroDigit()} is honoured.
     */
    char ARABIC_ZERO_DIGIT = '\u0660';

    /**
     * Euro symbol constant
     */
    char EURO_SYMBOL = '\u20AC';

    /**
     * Helper which may be used within tests to build expected strings to verify {@link DecimalNumberContext#zeroDigit()} is honoured.
     */
    default String arabicDigit(final int digit) {
        return String.valueOf(
            (char)(ARABIC_ZERO_DIGIT + digit)
        );
    }

    /**
     * Helper which may be used within tests to build expected strings to verify {@link DecimalNumberContext#zeroDigit()} is honoured.
     */
    default String arabicDigits(final int value) {
        final StringBuilder b = new StringBuilder();

        for(final char c : String.valueOf(value).toCharArray()) {
            switch(c) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    b.append(
                        this.arabicDigit(c - '0')
                    );
            }
        }

        return b.toString();
    }
}
