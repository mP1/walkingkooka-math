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

import walkingkooka.locale.HasLocaleTesting;

/**
 * Mixing testing interface for {@link DecimalNumberContext}
 */
public interface DecimalNumberContextTesting extends HasLocaleTesting,
    HasMathContextTesting {

    default void checkCurrencySymbol(final DecimalNumberContext context, final String currencySymbol) {
        this.checkEquals(currencySymbol, context.currencySymbol(), "currencySymbol");
    }

    default void checkDecimalSeparator(final DecimalNumberContext context, final char decimalSeparator) {
        this.checkEquals(decimalSeparator, context.decimalSeparator(), "decimalSeparator");
    }

    default void checkExponentSymbol(final DecimalNumberContext context, final String exponentSymbol) {
        this.checkEquals(exponentSymbol, context.exponentSymbol(), "exponentSymbol");
    }

    default void checkGroupSeparator(final DecimalNumberContext context, final char groupSeparator) {
        this.checkEquals(groupSeparator, context.groupSeparator(), "groupSeparator");
    }

    default void checkNegativeSign(final DecimalNumberContext context, final char negativeSign) {
        this.checkEquals(negativeSign, context.negativeSign(), "negativeSign");
    }

    default void checkPercentageSymbol(final DecimalNumberContext context, final char percentageSymbol) {
        this.checkEquals(percentageSymbol, context.percentageSymbol(), "percentageSymbol");
    }

    default void checkPositiveSign(final DecimalNumberContext context, final char positiveSign) {
        this.checkEquals(positiveSign, context.positiveSign(), "positiveSign");
    }
}
