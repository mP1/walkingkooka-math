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
import walkingkooka.ContextTesting;

import java.math.MathContext;

/**
 * Mixing testing interface for {@link DecimalNumberContext}
 */
public interface DecimalNumberContextTesting2<C extends DecimalNumberContext> extends DecimalNumberContextTesting,
    DecimalNumberSymbolsLike,
    ContextTesting<C> {

    @Test
    default void testCurrencySymbol() {
        this.currencySymbolAndCheck(
            this.createContext(),
            this.currencySymbol()
        );
    }

    @Test
    default void testDecimalSeparator() {
        this.decimalSeparatorAndCheck(
            this.createContext(),
            this.decimalSeparator()
        );
    }

    @Test
    default void testExponentSymbol() {
        this.exponentSymbolAndCheck(
            this.createContext(),
            this.exponentSymbol()
        );
    }

    @Test
    default void testGroupSeparator() {
        this.groupSeparatorAndCheck(
            this.createContext(),
            this.groupSeparator()
        );
    }

    @Test
    default void testInfinitySymbol() {
        this.infinitySymbolAndCheck(
            this.createContext(),
            this.infinitySymbol()
        );
    }

    @Test
    default void testMathContext() {
        this.hasMathContextAndCheck(
            this.createContext(),
            this.mathContext()
        );
    }

    @Test
    default void testMonetaryDecimalSeparator() {
        this.monetaryDecimalSeparatorAndCheck(
            this.createContext(),
            this.monetaryDecimalSeparator()
        );
    }

    @Test
    default void testNanSymbol() {
        this.nanSymbolAndCheck(
            this.createContext(),
            this.nanSymbol()
        );
    }
    
    @Test
    default void testNegativeSign() {
        this.negativeSignAndCheck(
            this.createContext(),
            this.negativeSign()
        );
    }

    @Test
    default void testPercentSymbol() {
        this.percentSymbolAndCheck(
            this.createContext(),
            this.percentSymbol()
        );
    }

    @Test
    default void testPermillSymbol() {
        this.permillSymbolAndCheck(
            this.createContext(),
            this.permillSymbol()
        );
    }

    @Test
    default void testPositiveSign() {
        this.positiveSignAndCheck(
            this.createContext(),
            this.positiveSign()
        );
    }

    MathContext mathContext();
    
    // class............................................................................................................

    @Override
    default String typeNameSuffix() {
        return DecimalNumberContext.class.getSimpleName();
    }
}
