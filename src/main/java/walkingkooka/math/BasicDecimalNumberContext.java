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

import walkingkooka.ToStringBuilder;

import java.math.MathContext;
import java.util.Locale;
import java.util.Objects;

/**
 * A {@link DecimalNumberContext} that holds constant properties.
 */
final class BasicDecimalNumberContext implements DecimalNumberContext {

    static BasicDecimalNumberContext with(final DecimalNumberSymbols symbols,
                                          final Locale locale,
                                          final MathContext mathContext) {
        return new BasicDecimalNumberContext(
            Objects.requireNonNull(symbols, "symbols"),
            Objects.requireNonNull(locale, "locale"),
            Objects.requireNonNull(mathContext, "mathContext")
        );
    }

    private BasicDecimalNumberContext(final DecimalNumberSymbols symbols,
                                      final Locale locale,
                                      final MathContext mathContext) {
        super();
        this.symbols = symbols;
        this.locale = locale;
        this.mathContext = mathContext;
    }

    @Override
    public String currencySymbol() {
        return this.symbols.currencySymbol();
    }

    @Override
    public char decimalSeparator() {
        return this.symbols.decimalSeparator();
    }

    @Override
    public String exponentSymbol() {
        return this.symbols.exponentSymbol();
    }

    @Override
    public char groupSeparator() {
        return this.symbols.groupSeparator();
    }

    @Override
    public char negativeSign() {
        return this.symbols.negativeSign();
    }

    @Override
    public char percentSymbol() {
        return this.symbols.percentSymbol();
    }

    @Override
    public char positiveSign() {
        return this.symbols.positiveSign();
    }

    private final DecimalNumberSymbols symbols;

    @Override
    public Locale locale() {
        return this.locale;
    }

    private final Locale locale;

    @Override
    public MathContext mathContext() {
        return this.mathContext;
    }

    private final MathContext mathContext;

    @Override
    public String toString() {
        return ToStringBuilder.empty()
            .value(this.symbols)
            .value(this.locale)
            .value(this.mathContext)
            .build();
    }
}
