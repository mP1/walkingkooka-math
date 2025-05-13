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
import walkingkooka.reflect.ClassTesting;
import walkingkooka.reflect.JavaVisibility;

public final class NumberContextLikeTest implements ClassTesting<NumberContextLike> {

    @Test
    public void testDigitWithZeroDigitAndZeroChar() {
        this.digitAndCheck(
            '0',
            '0',
            0
        );
    }

    @Test
    public void testDigitWithZeroDigitAndOneChar() {
        this.digitAndCheck(
            '0',
            '1',
            1
        );
    }

    @Test
    public void testDigitWithZeroDigitAndNineChar() {
        this.digitAndCheck(
            '0',
            '9',
            9
        );
    }

    @Test
    public void testDigitWithZeroDigitAndAlphaChar() {
        this.digitAndCheck(
            '0',
            'A',
            -1
        );
    }

    @Test
    public void testDigitWithZeroDigitAndZeroDigitLessOne() {
        this.digitAndCheck(
            '0',
            (char) ('0' - 1),
            -1
        );
    }

    @Test
    public void testDigitWithOtherNonZeroDigits() {
        for(int i = '9' + 1; i < Character.MAX_VALUE; i++) {
            this.digitAndCheck(
                '0',
                (char)i,
                -1
            );
        }
    }

    @Test
    public void testDigitWithArabicDigits() {
        final char zero = '\u0660';

        for(int i = 0 ; i < 10; i ++) {
            this.digitAndCheck(
                zero,
                (char)(i + zero),
                i
            );
        }
    }

    @Test
    public void testDigitWithExtendedArabicIndicDigits() {
        final char zero = '\u06F0';

        for(int i = 0 ; i < 10; i ++) {
            this.digitAndCheck(
                zero,
                (char)(i + zero),
                i
            );
        }
    }

    @Test
    public void testDigitWithDevanagariDigits() {
        final char zero = '\u0966';

        for(int i = 0 ; i < 10; i ++) {
            this.digitAndCheck(
                zero,
                (char)(i + zero),
                i
            );
        }
    }

    private void digitAndCheck(final char zeroDigit,
                               final char c,
                               final int expected) {
        this.checkEquals(
            expected,
            new FakeDecimalNumberContext() {

                @Override
                public char zeroDigit() {
                    return zeroDigit;
                }
            }.digit(c)
        );
    }

    // class............................................................................................................

    @Override
    public Class<NumberContextLike> type() {
        return NumberContextLike.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }
}
