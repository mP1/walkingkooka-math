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
 * Defines the getters for a {@link DecimalNumberSymbols}.
 */
public interface DecimalNumberSymbolsLike extends NumberContextLike {

    /**
     * The currency symbol character.
     */
    String currencySymbol();

    /**
     * Returns the decimal separator character
     */
    char decimalSeparator();

    /**
     * The exponent symbol
     */
    String exponentSymbol();

    /**
     * The group separator.
     */
    char groupSeparator();

    /**
     * The Infinity symbol character.
     */
    String infinitySymbol();

    /**
     * Returns the monetary decimal separator character
     */
    char monetaryDecimalSeparator();

    /**
     * The NAN character.
     */
    String nanSymbol();

    /**
     * The percentage symbol.
     */
    char percentSymbol();

    /**
     * The permill symbol.
     */
    char permillSymbol();
}
