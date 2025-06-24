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
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class AmericanDecimalNumberContextTest implements ClassTesting2<AmericanDecimalNumberContext>,
    DecimalNumberContextTesting2<AmericanDecimalNumberContext> {

    private final static MathContext MATH_CONTEXT = MathContext.DECIMAL32;

    @Test
    public void testWithNullMathContextFails() {
        assertThrows(NullPointerException.class, () -> AmericanDecimalNumberContext.with(null));
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
        assertSame(AmericanDecimalNumberContext.with(mathContext), AmericanDecimalNumberContext.with(mathContext));
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

        final AmericanDecimalNumberContext context = AmericanDecimalNumberContext.with(mathContext);

        this.currencySymbolAndCheck(context, symbols.getCurrencySymbol());
        this.decimalSeparatorAndCheck(context, symbols.getDecimalSeparator());
        this.exponentSymbolAndCheck(context, symbols.getExponentSeparator());
        this.groupSeparatorAndCheck(context, symbols.getGroupingSeparator());
        this.infinitySymbolAndCheck(context, symbols.getInfinity());
        this.monetaryDecimalSeparatorAndCheck(context, symbols.getMonetaryDecimalSeparator());
        this.nanSymbolAndCheck(context, symbols.getNaN());
        this.negativeSignAndCheck(context, symbols.getMinusSign());
        this.percentSymbolAndCheck(context, symbols.getPercent());
        this.permillSymbolAndCheck(context, symbols.getPerMill());
        this.positiveSignAndCheck(context, '+');
        this.zeroDigitAndCheck(context, symbols.getZeroDigit());

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
            "locale=en_US \"mathContext\" precision=7 roundingMode=HALF_EVEN \"decimalNumberSymbols\" negativeSign='-' positiveSign='+' zeroDigit='0' currencySymbol=\"$\" decimalSeparator='.' exponentSymbol=\"E\" groupSeparator=',' infinitySymbol=\"∞\" monetaryDecimalSeparator='.' nanSymbol=\"NaN\" percentSymbol='%' permillSymbol='‰'"
        );
    }

    @Override
    public AmericanDecimalNumberContext createContext() {
        return AmericanDecimalNumberContext.with(MATH_CONTEXT);
    }

    @Override
    public String currencySymbol() {
        return "$";
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
        return DECIMAL_FORMAT_SYMBOLS.getInfinity();
    }

    @Override
    public MathContext mathContext() {
        return MATH_CONTEXT;
    }

    @Override
    public char monetaryDecimalSeparator() {
        return '.';
    }

    @Override
    public String nanSymbol() {
        return DECIMAL_FORMAT_SYMBOLS.getNaN();
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
        return DECIMAL_FORMAT_SYMBOLS.getPerMill();
    }

    @Override
    public char positiveSign() {
        return '+';
    }

    @Override
    public char zeroDigit() {
        return '0';
    }

    private final static DecimalFormatSymbols DECIMAL_FORMAT_SYMBOLS = DecimalFormatSymbols.getInstance(Locale.US);

    // class............................................................................................................

    @Override
    public Class<AmericanDecimalNumberContext> type() {
        return AmericanDecimalNumberContext.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PACKAGE_PRIVATE;
    }
}
