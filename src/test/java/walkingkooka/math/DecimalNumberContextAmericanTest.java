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

import java.math.MathContext;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class DecimalNumberContextAmericanTest implements ClassTesting2<DecimalNumberContextAmerican>,
    DecimalNumberContextTesting2<DecimalNumberContextAmerican> {

    @Test
    public void testWithNullMathContextFails() {
        assertThrows(NullPointerException.class, () -> DecimalNumberContextAmerican.with(null));
    }

    @Test
    public void testWithMathContext32() {
        withConstantAndCheck(MathContext.DECIMAL32);
    }

    @Test
    public void testWithMathContext64() {
        withConstantAndCheck(MathContext.DECIMAL64);
    }

    @Test
    public void testWithMathContext128() {
        withConstantAndCheck(MathContext.DECIMAL128);
    }

    @Test
    public void testWithMathContextUnlimited() {
        withConstantAndCheck(MathContext.UNLIMITED);
    }

    private void withConstantAndCheck(final MathContext mathContext) {
        assertSame(DecimalNumberContextAmerican.with(mathContext), DecimalNumberContextAmerican.with(mathContext));
        withAndCheck(mathContext);
    }

    @Test
    public void testWith() {
        this.withAndCheck(MATH_CONTEXT);
    }

    @Test
    public void testWithCustomMathContext() {
        this.withAndCheck(new MathContext(33));
    }

    private void withAndCheck(final MathContext mathContext) {
        final DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(Locale.US);

        final DecimalNumberContextAmerican context = DecimalNumberContextAmerican.with(mathContext);

        this.currencySymbolAndCheck(
            context,
            symbols.getCurrencySymbol()
        );
        this.decimalNumberDigitCountAndCheck(
            context,
            DecimalNumberContext.DEFAULT_NUMBER_DIGIT_COUNT
        );
        this.decimalSeparatorAndCheck(
            context,
            String.valueOf(
                symbols.getDecimalSeparator()
            )
        );
        this.exponentSymbolAndCheck(
            context,
            String.valueOf(
                symbols.getExponentSeparator()
            )
        );
        this.groupSeparatorAndCheck(
            context,
            String.valueOf(
                symbols.getGroupingSeparator()
            )
        );
        this.infinitySymbolAndCheck(context, symbols.getInfinity());
        this.monetaryDecimalSeparatorAndCheck(
            context,
            String.valueOf(
                symbols.getMonetaryDecimalSeparator()
            )
        );
        this.nanSymbolAndCheck(
            context,
            symbols.getNaN()
        );
        this.negativeSignAndCheck(
            context,
            String.valueOf(
                symbols.getMinusSign()
            )
        );
        this.percentSymbolAndCheck(
            context,
            String.valueOf(
                symbols.getPercent()
            )
        );
        this.permillSymbolAndCheck(
            context,
            String.valueOf(
                symbols.getPerMill()
            )
        );
        this.positiveSignAndCheck(
            context,
            String.valueOf('+')
        );
        this.zeroDigitAndCheck(
            context,
            symbols.getZeroDigit()
        );

        this.hasMathContextAndCheck(context, mathContext);
    }

    @Test
    public void testLocale() {
        this.localeAndCheck(
            this.createContext(),
            Locale.US
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createContext(),
            "locale=en_US \"mathContext\" precision=7 roundingMode=HALF_EVEN \"decimalNumberSymbols\" negativeSign=\"-\" positiveSign=\"+\" zeroDigit='0' currencySymbol=\"$\" decimalSeparator=\".\" exponentSymbol=\"E\" groupSeparator=\",\" infinitySymbol=\"∞\" monetaryDecimalSeparator=\".\" nanSymbol=\"NaN\" percentSymbol=\"%\" permillSymbol=\"‰\""
        );
    }

    @Override
    public DecimalNumberContextAmerican createContext() {
        return DecimalNumberContextAmerican.with(MATH_CONTEXT);
    }

    @Override
    public String currencySymbol() {
        return "$";
    }

    @Override
    public int decimalNumberDigitCount() {
        return DecimalNumberContext.DEFAULT_NUMBER_DIGIT_COUNT;
    }

    @Override
    public String decimalSeparator() {
        return ".";
    }

    @Override
    public String exponentSymbol() {
        return "E";
    }

    @Override
    public String groupSeparator() {
        return ",";
    }

    @Override
    public String infinitySymbol() {
        return DECIMAL_FORMAT_SYMBOLS.getInfinity();
    }

    @Override
    public MathContext mathContext() {
        return MATH_CONTEXT;
    }

    @Override
    public String monetaryDecimalSeparator() {
        return ".";
    }

    @Override
    public String nanSymbol() {
        return DECIMAL_FORMAT_SYMBOLS.getNaN();
    }

    @Override
    public String negativeSign() {
        return "-";
    }

    @Override
    public String percentSymbol() {
        return "%";
    }

    @Override
    public String permillSymbol() {
        return String.valueOf(
            DECIMAL_FORMAT_SYMBOLS.getPerMill()
        );
    }

    @Override
    public String positiveSign() {
        return "+";
    }

    @Override
    public char zeroDigit() {
        return '0';
    }

    private final static DecimalFormatSymbols DECIMAL_FORMAT_SYMBOLS = DecimalFormatSymbols.getInstance(Locale.US);

    // class............................................................................................................

    @Override
    public Class<DecimalNumberContextAmerican> type() {
        return DecimalNumberContextAmerican.class;
    }

    @Override
    public void testTypeNaming() {
        throw new UnsupportedOperationException();
    }
}
