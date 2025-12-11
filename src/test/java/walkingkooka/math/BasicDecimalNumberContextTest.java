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
import walkingkooka.reflect.ClassTesting2;
import walkingkooka.reflect.JavaVisibility;

import java.math.MathContext;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class BasicDecimalNumberContextTest implements ClassTesting2<BasicDecimalNumberContext>,
    DecimalNumberContextTesting2<BasicDecimalNumberContext> {

    private final static int DECIMAL_NUMBER_DIGIT_COUNT = 10;
    
    private final static Locale LOCALE = Locale.FRANCE;
    private final static DecimalNumberSymbols SYMBOLS = DecimalNumberSymbols.with(
        '-',
        '+',
        '0',
        "$",
        '.',
        "E",
        ',',
        "INFINITY!",
        '*',
        "NAN!",
        '%',
        '^'
    );
    private final static MathContext MATH_CONTEXT = MathContext.DECIMAL32;

    @Test
    public void testWithNullInvalidDecimalNumberDigitCount() {
        assertThrows(
            IllegalArgumentException.class,
            () -> BasicDecimalNumberContext.with(
                -1,
                null,
                LOCALE,
                MATH_CONTEXT
            )
        );
    }

    @Test
    public void testWithNullDecimalNumberSymbols() {
        assertThrows(
            NullPointerException.class,
            () -> BasicDecimalNumberContext.with(
                DECIMAL_NUMBER_DIGIT_COUNT,
                null,
                LOCALE,
                MATH_CONTEXT
            )
        );
    }

    @Test
    public void testWithNullLocale() {
        assertThrows(
            NullPointerException.class,
            () -> BasicDecimalNumberContext.with(
                DECIMAL_NUMBER_DIGIT_COUNT,
                SYMBOLS,
                null,
                MATH_CONTEXT
            )
        );
    }

    @Test
    public void testWithNullMathContext() {
        assertThrows(
            NullPointerException.class,
            () -> BasicDecimalNumberContext.with(
                DECIMAL_NUMBER_DIGIT_COUNT,
                SYMBOLS,
                LOCALE,
                null
            )
        );
    }

    @Test
    public void testWith() {
        final BasicDecimalNumberContext context = this.createContext();
        this.currencySymbolAndCheck(context, "$");
        this.decimalSeparatorAndCheck(context, '.');
        this.exponentSymbolAndCheck(context, "E");
        this.groupSeparatorAndCheck(context, ',');
        this.negativeSignAndCheck(context, '-');
        this.percentSymbolAndCheck(context, '%');
        this.positiveSignAndCheck(context, '+');
        this.decimalNumberDigitCountAndCheck(
            context,
            DECIMAL_NUMBER_DIGIT_COUNT
        );

        this.localeAndCheck(
            context,
            LOCALE
        );
        this.hasMathContextAndCheck(context, MATH_CONTEXT);
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createContext(),
            "negativeSign='-' positiveSign='+' zeroDigit='0' currencySymbol=\"$\" decimalSeparator='.' exponentSymbol=\"E\" groupSeparator=',' infinitySymbol=\"INFINITY!\" monetaryDecimalSeparator='*' nanSymbol=\"NAN!\" percentSymbol='%' permillSymbol='^' fr_FR precision=7 roundingMode=HALF_EVEN"
        );
    }

    @Override
    public BasicDecimalNumberContext createContext() {
        return BasicDecimalNumberContext.with(
            DECIMAL_NUMBER_DIGIT_COUNT,
            SYMBOLS,
            LOCALE,
            MATH_CONTEXT
        );
    }

    @Override
    public String currencySymbol() {
        return "$";
    }

    @Override
    public int decimalNumberDigitCount() {
        return DECIMAL_NUMBER_DIGIT_COUNT;
    }
    
    @Override
    public char decimalSeparator() {
        return '.';
    }

    @Override
    public String exponentSymbol() {
        return "E";
    }

    @Override
    public char groupSeparator() {
        return ',';
    }

    @Override
    public String infinitySymbol() {
        return "INFINITY!";
    }

    @Override
    public char monetaryDecimalSeparator() {
        return '*';
    }

    @Override
    public String nanSymbol() {
        return "NAN!";
    }

    @Override
    public MathContext mathContext() {
        return MathContext.DECIMAL32;
    }

    @Override
    public char negativeSign() {
        return '-';
    }

    @Override
    public char percentSymbol() {
        return '%';
    }

    @Override
    public char permillSymbol() {
        return '^';
    }

    @Override
    public char positiveSign() {
        return '+';
    }

    @Override
    public char zeroDigit() {
        return '0';
    }

    @Override
    public Class<BasicDecimalNumberContext> type() {
        return BasicDecimalNumberContext.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PACKAGE_PRIVATE;
    }
}
