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

/**
 * A delegator that delegates all {@link DecimalNumberSymbols} property getters.
 */
public interface DecimalNumberSymbolsDelegator {

    /**
     * Returns the negative sign.
     */
    default char negativeSign() {
        return this.decimalNumberSymbols()
            .negativeSign();
    }

    /**
     * Returns the positive sign.
     */
    default char positiveSign() {
        return this.decimalNumberSymbols()
            .positiveSign();
    }

    /**
     * Returns the zero digit.
     */
    default char zeroDigit() {
        return this.decimalNumberSymbols()
            .zeroDigit();
    }

    /**
     * The currency symbol character.
     */
    default String currencySymbol() {
        return this.decimalNumberSymbols()
            .currencySymbol();
    }

    /**
     * Returns the decimal separator character
     */
    default char decimalSeparator() {
        return this.decimalNumberSymbols()
            .decimalSeparator();
    }

    /**
     * The exponent symbol
     */
    default String exponentSymbol() {
        return this.decimalNumberSymbols()
            .exponentSymbol();
    }

    /**
     * The group separator.
     */
    default char groupSeparator() {
        return this.decimalNumberSymbols()
            .groupSeparator();
    }

    /**
     * The percentage symbol.
     */
    default char percentSymbol() {
        return this.decimalNumberSymbols()
            .percentSymbol();
    }

    DecimalNumberSymbols decimalNumberSymbols();
}
