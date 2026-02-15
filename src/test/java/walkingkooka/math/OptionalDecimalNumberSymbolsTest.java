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
import walkingkooka.HashCodeEqualsDefinedTesting2;
import walkingkooka.ToStringTesting;
import walkingkooka.reflect.ClassTesting;
import walkingkooka.reflect.JavaVisibility;

import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class OptionalDecimalNumberSymbolsTest implements ClassTesting<OptionalDecimalNumberSymbols>,
    HashCodeEqualsDefinedTesting2<OptionalDecimalNumberSymbols>,
    ToStringTesting<OptionalDecimalNumberSymbols> {

    private final static Optional<DecimalNumberSymbols> DECIMAL_NUMBER_SYMBOLS = Optional.of(
        DecimalNumberSymbols.fromDecimalFormatSymbols(
            '+',
            new DecimalFormatSymbols(
                Locale.forLanguageTag("en-AU")
            )
        )
    );

    // with.............................................................................................................

    @Test
    public void testWithNullFails() {
        assertThrows(
            NullPointerException.class,
            () -> OptionalDecimalNumberSymbols.with(null)
        );
    }

    @Test
    public void testWithEmpty() {
        assertSame(
            OptionalDecimalNumberSymbols.EMPTY,
            OptionalDecimalNumberSymbols.with(
                Optional.empty()
            )
        );
    }

    @Test
    public void testWithNotEmpty() {
        final OptionalDecimalNumberSymbols optional = OptionalDecimalNumberSymbols.with(DECIMAL_NUMBER_SYMBOLS);

        assertSame(
            DECIMAL_NUMBER_SYMBOLS,
            optional.value()
        );
    }

    // hashCode/equals..................................................................................................

    @Test
    public void testEqualsDifferent() {
        this.checkNotEquals(
            OptionalDecimalNumberSymbols.with(
                Optional.of(
                    DecimalNumberSymbols.fromDecimalFormatSymbols(
                        '+',
                        new DecimalFormatSymbols(
                            Locale.forLanguageTag("en-NZ")
                        )
                    )
                )
            )
        );
    }

    @Override
    public OptionalDecimalNumberSymbols createObject() {
        return OptionalDecimalNumberSymbols.with(DECIMAL_NUMBER_SYMBOLS);
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createObject(),
            DECIMAL_NUMBER_SYMBOLS.get()
                .toString()
        );
    }

    @Test
    public void testToStringWithEmpty() {
        this.toStringAndCheck(
            OptionalDecimalNumberSymbols.EMPTY,
            ""
        );
    }

    // class............................................................................................................

    @Override
    public Class<OptionalDecimalNumberSymbols> type() {
        return OptionalDecimalNumberSymbols.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }
}
