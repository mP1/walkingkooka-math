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

package walkingkooka.math.sample;

import org.junit.jupiter.api.Test;
import walkingkooka.math.DecimalNumberContexts;
import walkingkooka.math.DecimalNumberSymbols;
import walkingkooka.math.Maths;

import java.math.MathContext;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Sample {

    public static void main(final String[] args) {
        new Sample();
    }

    private Sample() {
        this.testMathsIsNumber();
    }

    @Test
    public void testMathsIsNumber() {
        this.checkEquals(
            true,
            Maths.isNumber(12)
        );
    }

    @Test
    public void testDecimalNumberContextBasicCurrencySymbols() {
        this.checkEquals(
            "$",
            DecimalNumberContexts.basic(
                DecimalNumberSymbols.with(
                    '-',
                    '+',
                    '0',
                    "$",
                    '.',
                    "E",
                    ',',
                    '%'
                ),
                Locale.getDefault(),
                MathContext.DECIMAL32
            ).currencySymbol()
        );
    }

    public void checkEquals(final Object expected,
                            final Object actual) {
        assertEquals(
            expected,
            actual
        );
    }
}
