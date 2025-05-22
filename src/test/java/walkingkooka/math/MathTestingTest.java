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

import org.junit.jupiter.api.Test;

import java.text.DecimalFormatSymbols;

public final class MathTestingTest implements MathTesting {

    @Test
    public void testArabicZeroDigitLocale() {
        this.checkEquals(
            ARABIC_ZERO_DIGIT,
            new DecimalFormatSymbols(ARABIC_ZERO_DIGIT_LOCALE).getZeroDigit()
        );
    }

    @Test
    public void testArabicDigitsZero() {
        this.arabicDigitsAndCheck(
            0,
            "" + ARABIC_ZERO_DIGIT
        );
    }

    @Test
    public void testArabicDigits() {
        this.arabicDigitsAndCheck(
            123,
            this.arabicDigit(1) +
                this.arabicDigit(2) +
                this.arabicDigit(3)
        );
    }

    private void arabicDigitsAndCheck(final int value,
                                      final String expected) {
        this.checkEquals(
            expected,
            this.arabicDigits(value)
        );
    }
}
