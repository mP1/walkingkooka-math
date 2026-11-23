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
public interface DecimalNumberSymbolsLikeDelegator extends DecimalNumberSymbolsLike {

    /**
     * Returns the negative sign.
     */
    @Override
    default char negativeSign() {
        return this.decimalNumberSymbolsLike()
            .negativeSign();
    }

    /**
     * Returns the positive sign.
     */
    @Override
    default char positiveSign() {
        return this.decimalNumberSymbolsLike()
            .positiveSign();
    }

    /**
     * Returns the zero digit.
     */
    @Override
    default char zeroDigit() {
        return this.decimalNumberSymbolsLike()
            .zeroDigit();
    }

    /**
     * The currency symbol character.
     */
    @Override
    default String currencySymbol() {
        return this.decimalNumberSymbolsLike()
            .currencySymbol();
    }

    /**
     * Returns the decimal separator character
     */
    @Override
    default char decimalSeparator() {
        return this.decimalNumberSymbolsLike()
            .decimalSeparator();
    }

    /**
     * The exponent symbol
     */
    @Override
    default String exponentSymbol() {
        return this.decimalNumberSymbolsLike()
            .exponentSymbol();
    }

    /**
     * The group separator.
     */
    @Override
    default char groupSeparator() {
        return this.decimalNumberSymbolsLike()
            .groupSeparator();
    }

    /**
     * The infinity symbol
     */
    @Override
    default String infinitySymbol() {
        return this.decimalNumberSymbolsLike()
            .infinitySymbol();
    }

    /**
     * Returns the monetary decimal separator character
     */
    @Override
    default char monetaryDecimalSeparator() {
        return this.decimalNumberSymbolsLike()
            .monetaryDecimalSeparator();
    }

    /**
     * The nan symbol
     */
    @Override
    default String nanSymbol() {
        return this.decimalNumberSymbolsLike()
            .nanSymbol();
    }

    /**
     * The percent symbol.
     */
    @Override
    default char percentSymbol() {
        return this.decimalNumberSymbolsLike()
            .percentSymbol();
    }

    /**
     * The permill symbol.
     */
    @Override
    default char permillSymbol() {
        return this.decimalNumberSymbolsLike()
            .permillSymbol();
    }

    DecimalNumberSymbolsLike decimalNumberSymbolsLike();
}
