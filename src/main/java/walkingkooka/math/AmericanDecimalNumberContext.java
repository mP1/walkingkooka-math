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
 * A {@link DecimalNumberContext} with all values set to what can broadly be described as american.
 * This is useful for as many internet standards also use the same symbols. Note the {@link #locale()} throws
 * {@link UnsupportedOperationException}.
 */
final class AmericanDecimalNumberContext implements DecimalNumberContext,
    DecimalNumberSymbolsLikeDelegator{

    /**
     * Factory that returns a constant if a {@link MathContext} constant is given.
     */
    static AmericanDecimalNumberContext with(final MathContext mathContext) {
        Objects.requireNonNull(mathContext, "mathContext");

        return UNLIMITED.mathContext.equals(mathContext) ?
            UNLIMITED :
            DECIMAL32.mathContext.equals(mathContext) ?
                DECIMAL32 :
                DECIMAL64.mathContext.equals(mathContext) ?
                    DECIMAL64 :
                    DECIMAL128.mathContext.equals(mathContext) ?
                        DECIMAL128 :
                        new AmericanDecimalNumberContext(mathContext);
    }

    private final static AmericanDecimalNumberContext UNLIMITED = new AmericanDecimalNumberContext(MathContext.UNLIMITED);
    private final static AmericanDecimalNumberContext DECIMAL32 = new AmericanDecimalNumberContext(MathContext.DECIMAL32);
    private final static AmericanDecimalNumberContext DECIMAL64 = new AmericanDecimalNumberContext(MathContext.DECIMAL64);
    private final static AmericanDecimalNumberContext DECIMAL128 = new AmericanDecimalNumberContext(MathContext.DECIMAL128);

    /**
     * Private ctor use singleton.
     */
    private AmericanDecimalNumberContext(final MathContext mathContext) {
        super();
        this.mathContext = mathContext;
    }

    @Override
    public Locale locale() {
        return US;
    }

    private final static Locale US = Locale.forLanguageTag("EN-US");

    @Override
    public MathContext mathContext() {
        return this.mathContext;
    }

    private final MathContext mathContext;

    // DecimalNumberSymbolsLikeDelegator................................................................................

    @Override
    public DecimalNumberSymbolsLike decimalNumberSymbolsLike() {
        return DECIMAL_NUMBER_SYMBOLS;
    }

    private final static DecimalNumberSymbols DECIMAL_NUMBER_SYMBOLS = DecimalNumberSymbols.with(
        '-',
        '+',
        '0',
        "$",
        '.', // decimalSeparator
        "E",
        ',',
        "\u221e", // infinitySymbol
        '.', // monetaryDecimalSeparator
        "NaN", // nanSymbols
        '%',
        '\u2030' // permillSymbol
    );

    // Object...........................................................................................................

    @Override
    public String toString() {
        return ToStringBuilder.empty()
            .label("locale")
            .value(this.locale())
            .value("mathContext")
            .value(this.mathContext())
            .value("decimalNumberSymbols")
            .value(DECIMAL_NUMBER_SYMBOLS)
            .build();
    }
}
