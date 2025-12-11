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

import walkingkooka.util.HasLocaleTesting;

/**
 * Mixing testing interface for {@link DecimalNumberContext}
 */
public interface DecimalNumberContextTesting extends HasLocaleTesting,
    HasMathContextTesting,
    HasDecimalNumberSymbolsTesting,
    MathTesting {

    default void currencySymbolAndCheck(final DecimalNumberContext context,
                                        final String currencySymbol) {
        this.checkEquals(
            currencySymbol,
            context.currencySymbol(),
            "currencySymbol"
        );
    }

    default void decimalNumberDigitCountAndCheck(final DecimalNumberContext context,
                                                 final int expected) {
        this.checkEquals(
            expected,
            context.decimalNumberDigitCount(),
            "decimalNumberDigitCount"
        );
    }

    default void decimalSeparatorAndCheck(final DecimalNumberContext context,
                                          final char decimalSeparator) {
        this.checkEquals(
            decimalSeparator,
            context.decimalSeparator(),
            "decimalSeparator"
        );
    }

    default void exponentSymbolAndCheck(final DecimalNumberContext context,
                                        final String exponentSymbol) {
        this.checkEquals(
            exponentSymbol,
            context.exponentSymbol(),
            "exponentSymbol"
        );
    }

    default void groupSeparatorAndCheck(final DecimalNumberContext context,
                                        final char groupSeparator) {
        this.checkEquals(
            groupSeparator,
            context.groupSeparator(),
            "groupSeparator"
        );
    }

    default void infinitySymbolAndCheck(final DecimalNumberContext context,
                                        final String infinitySymbol) {
        this.checkEquals(
            infinitySymbol,
            context.infinitySymbol(),
            "infinitySymbol"
        );
    }
    
    default void monetaryDecimalSeparatorAndCheck(final DecimalNumberContext context, 
                                                  final char monetaryDecimalSeparator) {
        this.checkEquals(
            monetaryDecimalSeparator,
            context.monetaryDecimalSeparator(),
            "monetaryDecimalSeparator"
        );
    }

    default void nanSymbolAndCheck(final DecimalNumberContext context,
                                        final String nanSymbol) {
        this.checkEquals(
            nanSymbol,
            context.nanSymbol(),
            "nanSymbol"
        );
    }
    
    default void negativeSignAndCheck(final DecimalNumberContext context,
                                      final char negativeSign) {
        this.checkEquals(
            negativeSign,
            context.negativeSign(),
            "negativeSign"
        );
    }

    default void percentSymbolAndCheck(final DecimalNumberContext context,
                                       final char percentSymbol) {
        this.checkEquals(
            percentSymbol,
            context.percentSymbol(),
            "percentSymbol"
        );
    }

    default void permillSymbolAndCheck(final DecimalNumberContext context,
                                       final char permillSymbol) {
        this.checkEquals(
            permillSymbol,
            context.permillSymbol(),
            "permillSymbol"
        );
    }

    default void positiveSignAndCheck(final DecimalNumberContext context,
                                      final char positiveSign) {
        this.checkEquals(
            positiveSign,
            context.positiveSign(),
            "positiveSign"
        );
    }

    default void zeroDigitAndCheck(final DecimalNumberContext context,
                                   final char zeroDigit) {
        this.checkEquals(
            zeroDigit,
            context.zeroDigit(),
            "zeroDigit"
        );
    }
}
