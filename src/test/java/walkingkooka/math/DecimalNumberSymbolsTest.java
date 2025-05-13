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
import walkingkooka.HashCodeEqualsDefinedTesting2;
import walkingkooka.ToStringTesting;
import walkingkooka.reflect.ClassTesting;
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.test.ParseStringTesting;
import walkingkooka.text.HasTextTesting;
import walkingkooka.text.printer.TreePrintableTesting;

import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class DecimalNumberSymbolsTest implements HashCodeEqualsDefinedTesting2<DecimalNumberSymbols>,
    ToStringTesting<DecimalNumberSymbols>,
    HasTextTesting,
    ParseStringTesting<DecimalNumberSymbols>,
    TreePrintableTesting,
    ClassTesting<DecimalNumberSymbols> {

    private final static char NEGATIVE_SIGN = '-';
    private final static char POSITIVE_SIGN = '+';
    private final static char ZERO_DIGIT = '0';

    private final static String CURRENCY_SYMBOL = "AUD";
    private final static char DECIMAL_SEPARATOR = '.';
    private final static String EXPONENT_SYMBOL = "E";
    private final static char GROUP_SEPARATOR = ',';
    private final static String INFINITY_SYMBOL = "INFINITY";
    private final static char MONETARY_DECIMAL_SEPARATOR = '*'; // pick something different from DECIMAL_SEPARATOR
    private final static String NAN_SYMBOL = "NAN";
    private final static char PERCENTAGE_SYMBOL = '%';
    private final static char PERMILL_SYMBOL = '^';

    private final static char INVALID_CHAR = 'A';
    private final static String INVALID_STRING = "\n";

    private final static char DIFFERENT_CHAR = '!';
    private final static char DIFFERENT_ZERO_DIGIT = '1';
    private final static String DIFFERENT_STRING = "Different";

    // with.............................................................................................................

    @Test
    public void testWithInvalidNegativeSignFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                INVALID_CHAR,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );
    }

    @Test
    public void testWithInvalidPositiveSignFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                INVALID_CHAR,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );
    }

    @Test
    public void testWithNullCurrencySymbolFails() {
        assertThrows(
            NullPointerException.class,
            () -> DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                null,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );
    }

    @Test
    public void testWithEmptyCurrencySymbolFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                "",
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );
    }

    @Test
    public void testWithInvalidCurrencySymbolFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                INVALID_STRING,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );
    }

    @Test
    public void testWithInvalidDecimalSymbolFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                INVALID_CHAR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );
    }

    @Test
    public void testWithNullExponentSymbolFails() {
        assertThrows(
            NullPointerException.class,
            () -> DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                null,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );
    }

    @Test
    public void testWithEmptyExponentSymbolFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                "",
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );
    }

    @Test
    public void testWithInvalidExponentSymbolFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                INVALID_STRING,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );
    }

    @Test
    public void testWithInvalidGroupSeparatorFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                INVALID_CHAR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );
    }

    @Test
    public void testWithNullInfinitySymbolFails() {
        assertThrows(
            NullPointerException.class,
            () -> DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                null,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );
    }

    @Test
    public void testWithEmptyInfinitySymbolFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                "",
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );
    }

    @Test
    public void testWithInvalidInfinitySymbolFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INVALID_STRING,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );
    }

    @Test
    public void testWithInvalidMonetaryDecimalSeparatorFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                INVALID_CHAR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );
    }

    @Test
    public void testWithInvalidPercentSymbolFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                INVALID_CHAR,
                PERMILL_SYMBOL
            )
        );
    }

    @Test
    public void testWithInvalidPermillSymbolFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                INVALID_CHAR
            )
        );
    }

    @Test
    public void testWithNegativeSignEqualPositiveSignFails() {
        final IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                POSITIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );

        this.checkEquals(
            "Invalid character \"negativeSign\" is same as \"positiveSign\" '+'",
            thrown.getMessage()
        );
    }

    @Test
    public void testWithNegativeSignEqualDecimalSeparatorFails() {
        final IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                DECIMAL_SEPARATOR,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );

        this.checkEquals(
            "Invalid character \"negativeSign\" is same as \"decimalSeparator\" '.'",
            thrown.getMessage()
        );
    }

    @Test
    public void testWithNegativeSignEqualGroupSeparatorFails() {
        final IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                GROUP_SEPARATOR,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );

        this.checkEquals(
            "Invalid character \"negativeSign\" is same as \"groupSeparator\" ','",
            thrown.getMessage()
        );
    }

    @Test
    public void testWithNegativeSignEqualMonetaryDecimalSeparatorFails() {
        final IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                MONETARY_DECIMAL_SEPARATOR,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );

        this.checkEquals(
            "Invalid character \"negativeSign\" is same as \"monetaryDecimalSeparator\" '*'",
            thrown.getMessage()
        );
    }

    @Test
    public void testWithNegativeSignEqualPercentSymbolFails() {
        final IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                PERCENTAGE_SYMBOL,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );

        this.checkEquals(
            "Invalid character \"negativeSign\" is same as \"percentSymbol\" '%'",
            thrown.getMessage()
        );
    }

    @Test
    public void testWithNegativeSignEqualPermillSymbolFails() {
        final IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                PERMILL_SYMBOL,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );

        this.checkEquals(
            "Invalid character \"negativeSign\" is same as \"permillSymbol\" '^'",
            thrown.getMessage()
        );
    }

    @Test
    public void testWithPositiveSignEqualDecimalSeparatorFails() {
        final IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                DECIMAL_SEPARATOR,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );

        this.checkEquals(
            "Invalid character \"positiveSign\" is same as \"decimalSeparator\" '.'",
            thrown.getMessage()
        );
    }

    @Test
    public void testWithPositiveSignEqualGroupSeparatorFails() {
        final IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                GROUP_SEPARATOR,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );

        this.checkEquals(
            "Invalid character \"positiveSign\" is same as \"groupSeparator\" ','",
            thrown.getMessage()
        );
    }

    @Test
    public void testWithPositiveSignEqualMonetaryDecimalSeparatorFails() {
        final IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                MONETARY_DECIMAL_SEPARATOR,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );

        this.checkEquals(
            "Invalid character \"positiveSign\" is same as \"monetaryDecimalSeparator\" '*'",
            thrown.getMessage()
        );
    }

    @Test
    public void testWithPositiveSignEqualPercentSymbolFails() {
        final IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                PERCENTAGE_SYMBOL,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );

        this.checkEquals(
            "Invalid character \"positiveSign\" is same as \"percentSymbol\" '%'",
            thrown.getMessage()
        );
    }

    @Test
    public void testWithPositiveSignEqualPermillSymbolFails() {
        final IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                PERMILL_SYMBOL,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );

        this.checkEquals(
            "Invalid character \"positiveSign\" is same as \"permillSymbol\" '^'",
            thrown.getMessage()
        );
    }

    @Test
    public void testWithGroupSeparatorEqualMonetaryDecimalSeparatorFails() {
        final IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );

        this.checkEquals(
            "Invalid character \"groupSeparator\" is same as \"monetaryDecimalSeparator\" '*'",
            thrown.getMessage()
        );
    }

    @Test
    public void testWithGroupSeparatorEqualPercentSymbolFails() {
        final IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );

        this.checkEquals(
            "Invalid character \"groupSeparator\" is same as \"monetaryDecimalSeparator\" '*'",
            thrown.getMessage()
        );
    }

    @Test
    public void testWithGroupSeparatorEqualPermillSymbolFails() {
        final IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                PERMILL_SYMBOL,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );

        this.checkEquals(
            "Invalid character \"groupSeparator\" is same as \"permillSymbol\" '^'",
            thrown.getMessage()
        );
    }

    @Test
    public void testWithPercentSymbolEqualPermillSymbolFails() {
        final IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERMILL_SYMBOL,
                PERMILL_SYMBOL
            )
        );

        this.checkEquals(
            "Invalid character \"percentSymbol\" is same as \"permillSymbol\" '^'",
            thrown.getMessage()
        );
    }

    @Test
    public void testWith() {
        final DecimalNumberSymbols symbols = DecimalNumberSymbols.with(
            NEGATIVE_SIGN,
            POSITIVE_SIGN,
            ZERO_DIGIT,
            CURRENCY_SYMBOL,
            DECIMAL_SEPARATOR,
            EXPONENT_SYMBOL,
            GROUP_SEPARATOR,
            INFINITY_SYMBOL,
            MONETARY_DECIMAL_SEPARATOR,
            NAN_SYMBOL,
            PERCENTAGE_SYMBOL,
            PERMILL_SYMBOL
        );

        this.negativeSignAndCheck(symbols);
        this.positiveSignAndCheck(symbols);
        this.currencySymbolAndCheck(symbols);
        this.decimalSeparatorAndCheck(symbols);
        this.exponentSymbolAndCheck(symbols);
        this.groupSeparatorAndCheck(symbols);
        this.infinitySymbolAndCheck(symbols);
        this.monetaryDecimalSeparatorAndCheck(symbols);
        this.nanSymbolAndCheck(symbols);
        this.percentSymbolAndCheck(symbols);
        this.permillSymbolAndCheck(symbols);
    }

    // negativeSign.....................................................................................................

    @Test
    public void testSetNegativeSignWithInvalidFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setNegativeSign(INVALID_CHAR)
        );
    }

    @Test
    public void testSetNegativeSignWithPositiveSignFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setNegativeSign(POSITIVE_SIGN)
        );
    }

    @Test
    public void testSetNegativeSignWithDecimalSeparatorFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setNegativeSign(DECIMAL_SEPARATOR)
        );
    }

    @Test
    public void testSetNegativeSignWithGroupSeparatorFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setNegativeSign(GROUP_SEPARATOR)
        );
    }

    @Test
    public void testSetNegativeSignWithMonetaryDecimalSeparatorFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setNegativeSign(MONETARY_DECIMAL_SEPARATOR)
        );
    }

    @Test
    public void testSetNegativeSignWithPercentSymbolFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setNegativeSign(PERCENTAGE_SYMBOL)
        );
    }

    @Test
    public void testSetNegativeSignWithPermillSymbolFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setNegativeSign(PERMILL_SYMBOL)
        );
    }

    @Test
    public void testSetNegativeSignSame() {
        final DecimalNumberSymbols symbols = this.createObject();

        assertSame(
            symbols,
            symbols.setNegativeSign(NEGATIVE_SIGN)
        );
    }

    @Test
    public void testSetNegativeSignDifferent() {
        final DecimalNumberSymbols symbols = this.createObject();
        final DecimalNumberSymbols different = symbols.setNegativeSign(DIFFERENT_CHAR);

        assertNotSame(
            symbols,
            different
        );

        this.negativeSignAndCheck(
            different,
            DIFFERENT_CHAR
        );
        this.positiveSignAndCheck(different);
        this.zeroDigitAndCheck(different);
        this.currencySymbolAndCheck(different);
        this.decimalSeparatorAndCheck(different);
        this.exponentSymbolAndCheck(different);
        this.groupSeparatorAndCheck(different);
        this.infinitySymbolAndCheck(different);
        this.nanSymbolAndCheck(different);
        this.monetaryDecimalSeparatorAndCheck(different);
        this.percentSymbolAndCheck(different);
        this.permillSymbolAndCheck(symbols);
    }

    private void negativeSignAndCheck(final DecimalNumberSymbols symbols) {
        this.negativeSignAndCheck(
            symbols,
            NEGATIVE_SIGN
        );
    }

    private void negativeSignAndCheck(final DecimalNumberSymbols symbols,
                                      final char expected) {
        this.checkEquals(
            expected,
            symbols.negativeSign()
        );
    }

    // positiveSign.....................................................................................................

    @Test
    public void testSetPositiveSignWithInvalidFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPositiveSign(INVALID_CHAR)
        );
    }

    @Test
    public void testSetPositiveSignWithNegativeSignFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPositiveSign(NEGATIVE_SIGN)
        );
    }

    @Test
    public void testSetPositiveSignWithDecimalSeparatorFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPositiveSign(DECIMAL_SEPARATOR)
        );
    }

    @Test
    public void testSetPositiveSignWithGroupSeparatorFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPositiveSign(GROUP_SEPARATOR)
        );
    }

    @Test
    public void testSetPositiveSignWithMonetaryDecimalSeparatorFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPositiveSign(MONETARY_DECIMAL_SEPARATOR)
        );
    }

    @Test
    public void testSetPositiveSignWithPercentSymbolFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPositiveSign(PERCENTAGE_SYMBOL)
        );
    }

    @Test
    public void testSetPositiveSignWithPermillSymbolFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPositiveSign(PERMILL_SYMBOL)
        );
    }

    @Test
    public void testSetPositiveSignSame() {
        final DecimalNumberSymbols symbols = this.createObject();

        assertSame(
            symbols,
            symbols.setPositiveSign(POSITIVE_SIGN)
        );
    }

    @Test
    public void testSetPositiveSignDifferent() {
        final DecimalNumberSymbols symbols = this.createObject();
        final DecimalNumberSymbols different = symbols.setPositiveSign(DIFFERENT_CHAR);

        assertNotSame(
            symbols,
            different
        );

        this.negativeSignAndCheck(different);
        this.positiveSignAndCheck(
            different,
            DIFFERENT_CHAR
        );
        this.zeroDigitAndCheck(different);
        this.currencySymbolAndCheck(different);
        this.decimalSeparatorAndCheck(different);
        this.exponentSymbolAndCheck(different);
        this.groupSeparatorAndCheck(different);
        this.infinitySymbolAndCheck(different);
        this.nanSymbolAndCheck(different);
        this.monetaryDecimalSeparatorAndCheck(different);
        this.percentSymbolAndCheck(different);
        this.permillSymbolAndCheck(symbols);
    }

    private void positiveSignAndCheck(final DecimalNumberSymbols symbols) {
        this.positiveSignAndCheck(
            symbols,
            POSITIVE_SIGN
        );
    }

    private void positiveSignAndCheck(final DecimalNumberSymbols symbols,
                                      final char expected) {
        this.checkEquals(
            expected,
            symbols.positiveSign()
        );
    }

    // positiveSign.....................................................................................................

    @Test
    public void testSetZeroDigitWithInvalidFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setZeroDigit(INVALID_CHAR)
        );
    }

    @Test
    public void testSetZeroDigitSame() {
        final DecimalNumberSymbols symbols = this.createObject();

        assertSame(
            symbols,
            symbols.setZeroDigit(ZERO_DIGIT)
        );
    }

    @Test
    public void testSetZeroDigitDifferent() {
        final DecimalNumberSymbols symbols = this.createObject();
        final DecimalNumberSymbols different = symbols.setZeroDigit(DIFFERENT_ZERO_DIGIT);

        assertNotSame(
            symbols,
            different
        );

        this.negativeSignAndCheck(different);
        this.positiveSignAndCheck(different);
        this.zeroDigitAndCheck(
            different,
            DIFFERENT_ZERO_DIGIT
        );
        this.currencySymbolAndCheck(different);
        this.decimalSeparatorAndCheck(different);
        this.exponentSymbolAndCheck(different);
        this.groupSeparatorAndCheck(different);
        this.infinitySymbolAndCheck(different);
        this.nanSymbolAndCheck(different);
        this.monetaryDecimalSeparatorAndCheck(different);
        this.percentSymbolAndCheck(different);
        this.permillSymbolAndCheck(symbols);
    }

    private void zeroDigitAndCheck(final DecimalNumberSymbols symbols) {
        this.zeroDigitAndCheck(
            symbols,
            ZERO_DIGIT
        );
    }

    private void zeroDigitAndCheck(final DecimalNumberSymbols symbols,
                                   final char expected) {
        this.checkEquals(
            expected,
            symbols.zeroDigit()
        );
    }

    // currencySymbol...................................................................................................

    @Test
    public void testSetCurrencySymbolWithInvalidFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setCurrencySymbol(INVALID_STRING)
        );
    }

    @Test
    public void testSetCurrencySymbolSame() {
        final DecimalNumberSymbols symbols = this.createObject();

        assertSame(
            symbols,
            symbols.setCurrencySymbol(CURRENCY_SYMBOL)
        );
    }

    @Test
    public void testSetCurrencySymbolDifferent() {
        final DecimalNumberSymbols symbols = this.createObject();
        final DecimalNumberSymbols different = symbols.setCurrencySymbol(DIFFERENT_STRING);

        assertNotSame(
            symbols,
            different
        );

        this.negativeSignAndCheck(different);
        this.positiveSignAndCheck(different);
        this.zeroDigitAndCheck(different);
        this.currencySymbolAndCheck(
            different,
            DIFFERENT_STRING
        );
        this.decimalSeparatorAndCheck(different);
        this.exponentSymbolAndCheck(different);
        this.groupSeparatorAndCheck(different);
        this.infinitySymbolAndCheck(different);
        this.nanSymbolAndCheck(different);
        this.monetaryDecimalSeparatorAndCheck(different);
        this.percentSymbolAndCheck(different);
        this.permillSymbolAndCheck(symbols);
    }

    private void currencySymbolAndCheck(final DecimalNumberSymbols symbols) {
        this.currencySymbolAndCheck(
            symbols,
            CURRENCY_SYMBOL
        );
    }

    private void currencySymbolAndCheck(final DecimalNumberSymbols symbols,
                                        final String expected) {
        this.checkEquals(
            expected,
            symbols.currencySymbol()
        );
    }

    // decimalSeparator.................................................................................................

    @Test
    public void testSetDecimalSeparatorWithInvalidFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setDecimalSeparator(INVALID_CHAR)
        );
    }

    @Test
    public void testSetDecimalSeparatorWithNegativeSignFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setDecimalSeparator(NEGATIVE_SIGN)
        );
    }

    @Test
    public void testSetDecimalSeparatorWithPositiveSeparatorFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setDecimalSeparator(POSITIVE_SIGN)
        );
    }

    @Test
    public void testSetDecimalSeparatorWithGroupSeparatorFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setDecimalSeparator(GROUP_SEPARATOR)
        );
    }

    @Test
    public void testSetDecimalSeparatorWithPercentSymbolFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setDecimalSeparator(PERCENTAGE_SYMBOL)
        );
    }

    @Test
    public void testSetDecimalSeparatorWithPermillSymbolFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setDecimalSeparator(PERMILL_SYMBOL)
        );
    }

    @Test
    public void testSetDecimalSeparatorSame() {
        final DecimalNumberSymbols symbols = this.createObject();

        assertSame(
            symbols,
            symbols.setDecimalSeparator(DECIMAL_SEPARATOR)
        );
    }

    @Test
    public void testSetDecimalSeparatorWithMonetaryDecimalSeparator() {
        final DecimalNumberSymbols symbols = this.createObject();
        final DecimalNumberSymbols different = symbols.setDecimalSeparator(MONETARY_DECIMAL_SEPARATOR);

        assertNotSame(
            symbols,
            different
        );

        this.negativeSignAndCheck(different);
        this.positiveSignAndCheck(different);
        this.zeroDigitAndCheck(different);
        this.currencySymbolAndCheck(different);
        this.decimalSeparatorAndCheck(
            different,
            MONETARY_DECIMAL_SEPARATOR
        );
        this.exponentSymbolAndCheck(different);
        this.groupSeparatorAndCheck(different);
        this.infinitySymbolAndCheck(different);
        this.nanSymbolAndCheck(different);
        this.monetaryDecimalSeparatorAndCheck(different);
        this.percentSymbolAndCheck(different);
        this.permillSymbolAndCheck(symbols);
    }

    @Test
    public void testSetDecimalSeparatorDifferent() {
        final DecimalNumberSymbols symbols = this.createObject();
        final DecimalNumberSymbols different = symbols.setDecimalSeparator(DIFFERENT_CHAR);

        assertNotSame(
            symbols,
            different
        );

        this.negativeSignAndCheck(different);
        this.positiveSignAndCheck(different);
        this.zeroDigitAndCheck(different);
        this.currencySymbolAndCheck(different);
        this.decimalSeparatorAndCheck(
            different,
            DIFFERENT_CHAR
        );
        this.exponentSymbolAndCheck(different);
        this.groupSeparatorAndCheck(different);
        this.infinitySymbolAndCheck(different);
        this.nanSymbolAndCheck(different);
        this.monetaryDecimalSeparatorAndCheck(different);
        this.percentSymbolAndCheck(different);
        this.permillSymbolAndCheck(symbols);
    }

    private void decimalSeparatorAndCheck(final DecimalNumberSymbols symbols) {
        this.decimalSeparatorAndCheck(
            symbols,
            DECIMAL_SEPARATOR
        );
    }

    private void decimalSeparatorAndCheck(final DecimalNumberSymbols symbols,
                                          final char expected) {
        this.checkEquals(
            expected,
            symbols.decimalSeparator()
        );
    }

    // exponentSymbol...................................................................................................

    @Test
    public void testSetExponentSymbolWithInvalidFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setExponentSymbol(INVALID_STRING)
        );
    }

    @Test
    public void testSetExponentSymbolSame() {
        final DecimalNumberSymbols symbols = this.createObject();

        assertSame(
            symbols,
            symbols.setExponentSymbol(EXPONENT_SYMBOL)
        );
    }

    @Test
    public void testSetExponentSymbolDifferent() {
        final DecimalNumberSymbols symbols = this.createObject();
        final DecimalNumberSymbols different = symbols.setExponentSymbol(DIFFERENT_STRING);

        assertNotSame(
            symbols,
            different
        );

        this.negativeSignAndCheck(different);
        this.positiveSignAndCheck(different);
        this.zeroDigitAndCheck(different);
        this.currencySymbolAndCheck(different);
        this.decimalSeparatorAndCheck(different);
        this.exponentSymbolAndCheck(
            different,
            DIFFERENT_STRING
        );
        this.groupSeparatorAndCheck(different);
        this.infinitySymbolAndCheck(different);
        this.monetaryDecimalSeparatorAndCheck(different);
        this.nanSymbolAndCheck(different);
        this.percentSymbolAndCheck(different);
        this.permillSymbolAndCheck(symbols);
    }

    private void exponentSymbolAndCheck(final DecimalNumberSymbols symbols) {
        this.exponentSymbolAndCheck(
            symbols,
            EXPONENT_SYMBOL
        );
    }

    private void exponentSymbolAndCheck(final DecimalNumberSymbols symbols,
                                        final String expected) {
        this.checkEquals(
            expected,
            symbols.exponentSymbol()
        );
    }

    // groupSeparator...................................................................................................

    @Test
    public void testSetGroupSeparatorWithInvalidFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setGroupSeparator(INVALID_CHAR)
        );
    }

    @Test
    public void testSetGroupSeparatorWithNegativeSignFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setGroupSeparator(NEGATIVE_SIGN)
        );
    }

    @Test
    public void testSetGroupSeparatorWithPositiveSignFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setGroupSeparator(POSITIVE_SIGN)
        );
    }

    @Test
    public void testSetGroupSeparatorWithDecimalSeparatorFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setGroupSeparator(DECIMAL_SEPARATOR)
        );
    }

    @Test
    public void testSetGroupSeparatorWithMonetaryDecimalSeparatorFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setGroupSeparator(MONETARY_DECIMAL_SEPARATOR)
        );
    }

    @Test
    public void testSetGroupSeparatorWithPercentSymbolFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setGroupSeparator(PERCENTAGE_SYMBOL)
        );
    }

    @Test
    public void testSetGroupSeparatorWithPermillSymbolFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setGroupSeparator(PERMILL_SYMBOL)
        );
    }

    @Test
    public void testSetGroupSeparatorSame() {
        final DecimalNumberSymbols symbols = this.createObject();

        assertSame(
            symbols,
            symbols.setGroupSeparator(GROUP_SEPARATOR)
        );
    }

    @Test
    public void testSetGroupSeparatorDifferent() {
        final DecimalNumberSymbols symbols = this.createObject();
        final DecimalNumberSymbols different = symbols.setGroupSeparator(DIFFERENT_CHAR);

        assertNotSame(
            symbols,
            different
        );

        this.negativeSignAndCheck(different);
        this.positiveSignAndCheck(different);
        this.zeroDigitAndCheck(different);
        this.currencySymbolAndCheck(different);
        this.decimalSeparatorAndCheck(different);
        this.exponentSymbolAndCheck(different);
        this.groupSeparatorAndCheck(
            different,
            DIFFERENT_CHAR
        );
        this.infinitySymbolAndCheck(different);
        this.monetaryDecimalSeparatorAndCheck(different);
        this.nanSymbolAndCheck(different);
        this.percentSymbolAndCheck(different);
        this.permillSymbolAndCheck(symbols);
    }

    private void groupSeparatorAndCheck(final DecimalNumberSymbols symbols) {
        this.groupSeparatorAndCheck(
            symbols,
            GROUP_SEPARATOR
        );
    }

    private void groupSeparatorAndCheck(final DecimalNumberSymbols symbols,
                                        final char expected) {
        this.checkEquals(
            expected,
            symbols.groupSeparator()
        );
    }

    // infinitySymbol...................................................................................................

    @Test
    public void testSetInfinitySymbolWithInvalidFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setInfinitySymbol(INVALID_STRING)
        );
    }

    @Test
    public void testSetInfinitySymbolSame() {
        final DecimalNumberSymbols symbols = this.createObject();

        assertSame(
            symbols,
            symbols.setInfinitySymbol(INFINITY_SYMBOL)
        );
    }

    @Test
    public void testSetInfinitySymbolDifferent() {
        final DecimalNumberSymbols symbols = this.createObject();
        final DecimalNumberSymbols different = symbols.setInfinitySymbol(DIFFERENT_STRING);

        assertNotSame(
            symbols,
            different
        );

        this.negativeSignAndCheck(different);
        this.positiveSignAndCheck(different);
        this.zeroDigitAndCheck(different);
        this.currencySymbolAndCheck(different);
        this.decimalSeparatorAndCheck(different);
        this.exponentSymbolAndCheck(different);
        this.groupSeparatorAndCheck(different);
        this.infinitySymbolAndCheck(
            different,
            DIFFERENT_STRING
        );
        this.monetaryDecimalSeparatorAndCheck(different);
        this.nanSymbolAndCheck(different);
        this.percentSymbolAndCheck(different);
        this.permillSymbolAndCheck(symbols);
    }

    private void infinitySymbolAndCheck(final DecimalNumberSymbols symbols) {
        this.infinitySymbolAndCheck(
            symbols,
            INFINITY_SYMBOL
        );
    }

    private void infinitySymbolAndCheck(final DecimalNumberSymbols symbols,
                                        final String expected) {
        this.checkEquals(
            expected,
            symbols.infinitySymbol()
        );
    }

    // monetaryDecimalSeparator.........................................................................................

    @Test
    public void testSetMonetaryDecimalSeparatorWithInvalidFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setMonetaryDecimalSeparator(INVALID_CHAR)
        );
    }

    @Test
    public void testSetMonetaryDecimalSeparatorWithNegativeSignFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setMonetaryDecimalSeparator(NEGATIVE_SIGN)
        );
    }

    @Test
    public void testSetMonetaryDecimalSeparatorWithPositiveSeparatorFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setMonetaryDecimalSeparator(POSITIVE_SIGN)
        );
    }

    @Test
    public void testSetMonetaryDecimalSeparatorWithGroupSeparatorFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setMonetaryDecimalSeparator(GROUP_SEPARATOR)
        );
    }

    @Test
    public void testSetMonetaryDecimalSeparatorWithPercentSymbolFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setMonetaryDecimalSeparator(PERCENTAGE_SYMBOL)
        );
    }

    @Test
    public void testSetMonetaryDecimalSeparatorWithPermillSymbolFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setMonetaryDecimalSeparator(PERMILL_SYMBOL)
        );
    }

    @Test
    public void testSetMonetaryDecimalSeparatorSame() {
        final DecimalNumberSymbols symbols = this.createObject();

        assertSame(
            symbols,
            symbols.setMonetaryDecimalSeparator(MONETARY_DECIMAL_SEPARATOR)
        );
    }

    @Test
    public void testSetMonetaryDecimalSeparatorWithDecimalSeparator() {
        final DecimalNumberSymbols symbols = this.createObject();
        final DecimalNumberSymbols different = symbols.setMonetaryDecimalSeparator(DECIMAL_SEPARATOR);

        assertNotSame(
            symbols,
            different
        );

        this.negativeSignAndCheck(different);
        this.positiveSignAndCheck(different);
        this.zeroDigitAndCheck(different);
        this.currencySymbolAndCheck(different);
        this.decimalSeparatorAndCheck(different);
        this.exponentSymbolAndCheck(different);
        this.groupSeparatorAndCheck(different);
        this.infinitySymbolAndCheck(different);
        this.monetaryDecimalSeparatorAndCheck(
            different,
            DECIMAL_SEPARATOR
        );
        this.nanSymbolAndCheck(different);
        this.percentSymbolAndCheck(different);
        this.permillSymbolAndCheck(symbols);
    }

    @Test
    public void testSetMonetaryDecimalSeparatorDifferent() {
        final DecimalNumberSymbols symbols = this.createObject();
        final DecimalNumberSymbols different = symbols.setMonetaryDecimalSeparator(DIFFERENT_CHAR);

        assertNotSame(
            symbols,
            different
        );

        this.negativeSignAndCheck(different);
        this.positiveSignAndCheck(different);
        this.zeroDigitAndCheck(different);
        this.currencySymbolAndCheck(different);
        this.decimalSeparatorAndCheck(different);
        this.exponentSymbolAndCheck(different);
        this.groupSeparatorAndCheck(different);
        this.infinitySymbolAndCheck(different);
        this.monetaryDecimalSeparatorAndCheck(
            different,
            DIFFERENT_CHAR
        );
        this.nanSymbolAndCheck(different);
        this.percentSymbolAndCheck(different);
        this.permillSymbolAndCheck(symbols);
    }

    private void monetaryDecimalSeparatorAndCheck(final DecimalNumberSymbols symbols) {
        this.monetaryDecimalSeparatorAndCheck(
            symbols,
            MONETARY_DECIMAL_SEPARATOR
        );
    }

    private void monetaryDecimalSeparatorAndCheck(final DecimalNumberSymbols symbols,
                                                  final char expected) {
        this.checkEquals(
            expected,
            symbols.monetaryDecimalSeparator()
        );
    }

    // nanSymbol...................................................................................................

    @Test
    public void testSetNanSymbolWithInvalidFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setNanSymbol(INVALID_STRING)
        );
    }

    @Test
    public void testSetNanSymbolSame() {
        final DecimalNumberSymbols symbols = this.createObject();

        assertSame(
            symbols,
            symbols.setNanSymbol(NAN_SYMBOL)
        );
    }

    @Test
    public void testSetNanSymbolDifferent() {
        final DecimalNumberSymbols symbols = this.createObject();
        final DecimalNumberSymbols different = symbols.setNanSymbol(DIFFERENT_STRING);

        assertNotSame(
            symbols,
            different
        );

        this.negativeSignAndCheck(different);
        this.positiveSignAndCheck(different);
        this.zeroDigitAndCheck(different);
        this.currencySymbolAndCheck(different);
        this.decimalSeparatorAndCheck(different);
        this.groupSeparatorAndCheck(different);
        this.infinitySymbolAndCheck(different);
        this.nanSymbolAndCheck(
            different,
            DIFFERENT_STRING
        );
        this.monetaryDecimalSeparatorAndCheck(different);
        this.percentSymbolAndCheck(different);
        this.permillSymbolAndCheck(symbols);
    }

    private void nanSymbolAndCheck(final DecimalNumberSymbols symbols) {
        this.nanSymbolAndCheck(
            symbols,
            NAN_SYMBOL
        );
    }

    private void nanSymbolAndCheck(final DecimalNumberSymbols symbols,
                                   final String expected) {
        this.checkEquals(
            expected,
            symbols.nanSymbol()
        );
    }

    // percentSymbol.................................................................................................

    @Test
    public void testSetPercentSymbolWithInvalidFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPercentSymbol(INVALID_CHAR)
        );
    }

    @Test
    public void testSetPercentSymbolWithNegativeSignFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPercentSymbol(NEGATIVE_SIGN)
        );
    }

    @Test
    public void testSetPercentSymbolWithPositiveSignFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPercentSymbol(POSITIVE_SIGN)
        );
    }

    @Test
    public void testSetPercentSymbolWithDecimalSeparatorFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPercentSymbol(DECIMAL_SEPARATOR)
        );
    }

    @Test
    public void testSetPercentSymbolWithGroupSeparatorFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPercentSymbol(GROUP_SEPARATOR)
        );
    }

    @Test
    public void testSetPercentSymbolWithMonetaryDecimalSeparatorFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPercentSymbol(MONETARY_DECIMAL_SEPARATOR)
        );
    }

    @Test
    public void testSetPercentSymbolWithPermillSymbolFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPercentSymbol(PERMILL_SYMBOL)
        );
    }

    @Test
    public void testSetPercentSymbolSame() {
        final DecimalNumberSymbols symbols = this.createObject();

        assertSame(
            symbols,
            symbols.setPercentSymbol(PERCENTAGE_SYMBOL)
        );
    }

    @Test
    public void testSetPercentSymbolDifferent() {
        final DecimalNumberSymbols symbols = this.createObject();
        final DecimalNumberSymbols different = symbols.setPercentSymbol(DIFFERENT_CHAR);

        assertNotSame(
            symbols,
            different
        );

        this.negativeSignAndCheck(different);
        this.positiveSignAndCheck(different);
        this.zeroDigitAndCheck(different);
        this.currencySymbolAndCheck(different);
        this.decimalSeparatorAndCheck(different);
        this.exponentSymbolAndCheck(different);
        this.groupSeparatorAndCheck(different);
        this.percentSymbolAndCheck(
            different,
            DIFFERENT_CHAR
        );
        this.permillSymbolAndCheck(symbols);
    }

    private void percentSymbolAndCheck(final DecimalNumberSymbols symbols) {
        this.percentSymbolAndCheck(
            symbols,
            PERCENTAGE_SYMBOL
        );
    }

    private void percentSymbolAndCheck(final DecimalNumberSymbols symbols,
                                          final char expected) {
        this.checkEquals(
            expected,
            symbols.percentSymbol()
        );
    }

    // permillSymbol....................................................................................................

    @Test
    public void testSetPermillSymbolWithInvalidFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPermillSymbol(INVALID_CHAR)
        );
    }

    @Test
    public void testSetPermillSymbolWithNegativeSignFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPermillSymbol(NEGATIVE_SIGN)
        );
    }

    @Test
    public void testSetPermillSymbolWithPositiveSignFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPermillSymbol(POSITIVE_SIGN)
        );
    }

    @Test
    public void testSetPermillSymbolWithDecimalSeparatorFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPermillSymbol(DECIMAL_SEPARATOR)
        );
    }

    @Test
    public void testSetPermillSymbolWithGroupSeparatorFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPermillSymbol(GROUP_SEPARATOR)
        );
    }

    @Test
    public void testSetPermillSymbolWithMonetaryDecimalSeparatorFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPermillSymbol(MONETARY_DECIMAL_SEPARATOR)
        );
    }

    @Test
    public void testSetPermillSymbolWithPercentSymbolFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPermillSymbol(GROUP_SEPARATOR)
        );
    }

    @Test
    public void testSetPermillSymbolSame() {
        final DecimalNumberSymbols symbols = this.createObject();

        assertSame(
            symbols,
            symbols.setPermillSymbol(PERMILL_SYMBOL)
        );
    }

    @Test
    public void testSetPermillSymbolDifferent() {
        final DecimalNumberSymbols symbols = this.createObject();
        final DecimalNumberSymbols different = symbols.setPermillSymbol(DIFFERENT_CHAR);

        assertNotSame(
            symbols,
            different
        );

        this.negativeSignAndCheck(different);
        this.positiveSignAndCheck(different);
        this.zeroDigitAndCheck(different);
        this.currencySymbolAndCheck(different);
        this.decimalSeparatorAndCheck(different);
        this.exponentSymbolAndCheck(different);
        this.groupSeparatorAndCheck(different);
        this.permillSymbolAndCheck(
            different,
            DIFFERENT_CHAR
        );
        this.permillSymbolAndCheck(symbols);
    }

    private void permillSymbolAndCheck(final DecimalNumberSymbols symbols) {
        this.permillSymbolAndCheck(
            symbols,
            PERMILL_SYMBOL
        );
    }

    private void permillSymbolAndCheck(final DecimalNumberSymbols symbols,
                                       final char expected) {
        this.checkEquals(
            expected,
            symbols.permillSymbol()
        );
    }

    // hashCode/equals..................................................................................................

    @Test
    public void testEqualsDifferentNegativeSign() {
        this.checkNotEquals(
            DecimalNumberSymbols.with(
                '!',
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );
    }

    @Test
    public void testEqualsDifferentPositiveSign() {
        this.checkNotEquals(
            DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                '!',
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );
    }

    @Test
    public void testEqualsDifferentZeroDigit() {
        this.checkNotEquals(
            DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                DIFFERENT_ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );
    }

    @Test
    public void testEqualsDifferentCurrencySymbol() {
        this.checkNotEquals(
            DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                DIFFERENT_STRING,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );
    }

    @Test
    public void testEqualsDifferentDecimalSeparator() {
        this.checkNotEquals(
            DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                '!',
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );
    }

    @Test
    public void testEqualsDifferentExponentSymbol() {
        this.checkNotEquals(
            DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                DIFFERENT_STRING,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );
    }

    @Test
    public void testEqualsDifferentGroupSeparator() {
        this.checkNotEquals(
            DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                '!',
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );
    }

    @Test
    public void testEqualsDifferentInfinitySymbol() {
        this.checkNotEquals(
            DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                DIFFERENT_STRING,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );
    }

    @Test
    public void testEqualsDifferentNanSymbol() {
        this.checkNotEquals(
            DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                DIFFERENT_STRING,
                PERCENTAGE_SYMBOL,
                PERMILL_SYMBOL
            )
        );
    }

    @Test
    public void testEqualsDifferentPercentSymbol() {
        this.checkNotEquals(
            DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                '!',
                PERMILL_SYMBOL
            )
        );
    }

    @Test
    public void testEqualsDifferentPermillSymbol() {
        this.checkNotEquals(
            DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
                INFINITY_SYMBOL,
                MONETARY_DECIMAL_SEPARATOR,
                NAN_SYMBOL,
                PERCENTAGE_SYMBOL,
                '!'
            )
        );
    }

    @Override
    public DecimalNumberSymbols createObject() {
        return DecimalNumberSymbols.with(
            NEGATIVE_SIGN,
            POSITIVE_SIGN,
            ZERO_DIGIT,
            CURRENCY_SYMBOL,
            DECIMAL_SEPARATOR,
            EXPONENT_SYMBOL,
            GROUP_SEPARATOR,
            INFINITY_SYMBOL,
            MONETARY_DECIMAL_SEPARATOR,
            NAN_SYMBOL,
            PERCENTAGE_SYMBOL,
            PERMILL_SYMBOL
        );
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createObject(),
            "negativeSign='-' positiveSign='+' zeroDigit='0' currencySymbol=\"AUD\" decimalSeparator='.' exponentSymbol=\"E\" groupSeparator=',' infinitySymbol=\"INFINITY\" monetaryDecimalSeparator='*' nanSymbol=\"NAN\" percentSymbol='%' permillSymbol='^'"
        );
    }

    // TreePrintable....................................................................................................

    @Test
    public void testTreePrintable() {
        this.treePrintAndCheck(
            this.createObject(),
            "DecimalNumberSymbols\n" +
                "  negativeSign\n" +
                "    '-'\n" +
                "  positiveSign\n" +
                "    '+'\n" +
                "  zeroDigit\n" +
                "    '0'\n" +
                "  currencySymbol\n" +
                "    \"AUD\"\n" +
                "  decimalSeparator\n" +
                "    '.'\n" +
                "  exponentSymbol\n" +
                "    \"E\"\n" +
                "  groupSeparator\n" +
                "    ','\n" +
                "  infinitySymbol\n" +
                "    \"INFINITY\"\n" +
                "  monetaryDecimalSeparator\n" +
                "    '*'\n" +
                "  nanSymbol\n" +
                "    \"NAN\"\n" +
                "  percentSymbol\n" +
                "    '%'\n" +
                "  permillSymbol\n" +
                "    '^'\n"
        );
    }

    // fromDecimalFormatSymbols.........................................................................................

    @Test
    public void testFromDecimalFormatSymbolsWithNullDecimalFormatSymbols() {
        assertThrows(
            NullPointerException.class,
            () -> DecimalNumberSymbols.fromDecimalFormatSymbols(
                '+',
                null
            )
        );
    }

    @Test
    public void testFromDecimalFormatSymbols() {
        final DecimalNumberSymbols symbols = DecimalNumberSymbols.fromDecimalFormatSymbols(
            '+',
            DecimalFormatSymbols.getInstance(
                Locale.forLanguageTag("EN-AU")
            )
        );

        this.positiveSignAndCheck(symbols, '+');
        this.negativeSignAndCheck(symbols, '-');
        this.zeroDigitAndCheck(symbols, '0');
        this.currencySymbolAndCheck(symbols, "$");
        this.exponentSymbolAndCheck(symbols, "e");
        this.groupSeparatorAndCheck(symbols, ',');
        this.infinitySymbolAndCheck(symbols, "\u221e"); // infinity symbol
        this.monetaryDecimalSeparatorAndCheck(symbols, '.');
        this.nanSymbolAndCheck(symbols, "NaN");
        this.percentSymbolAndCheck(symbols, '%');
        this.permillSymbolAndCheck(symbols, '\u2030'); // per mill symbol
    }

    @Test
    public void testFromDecimalFormatSymbolsFromAllLocales() {
        final StringBuilder b = new StringBuilder();

        final char leftToRight = '\u200e';

        for (final Locale locale : Locale.getAvailableLocales()) {
            final DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance(locale);

            // some locales have bad data such as LEFT for minusSign
            if (leftToRight == decimalFormatSymbols.getDecimalSeparator() ||
                leftToRight == decimalFormatSymbols.getGroupingSeparator() ||
                leftToRight == decimalFormatSymbols.getMinusSign() ||
                leftToRight == decimalFormatSymbols.getMonetaryDecimalSeparator() ||
                leftToRight == decimalFormatSymbols.getPercent() ||
                leftToRight == decimalFormatSymbols.getPerMill()) {
                continue;
            }

            try {
                DecimalNumberSymbols.fromDecimalFormatSymbols(
                    '+',
                    decimalFormatSymbols
                );
            } catch (final IllegalArgumentException fail) {
                b.append(
                    locale +
                        " " +
                        locale.getDisplayName() +
                        " " +
                        locale.getCountry() +
                        " " +
                        locale.getLanguage() +
                        " decimalSeparator=" + decimalFormatSymbols.getDecimalSeparator() +
                        " groupingSeparator=" + decimalFormatSymbols.getGroupingSeparator() +
                        " minusSign=" + decimalFormatSymbols.getMinusSign() +
                        " monetaryDecimalSeparator=" + decimalFormatSymbols.getMonetaryDecimalSeparator() +
                        " percent=" + decimalFormatSymbols.getPercent() +
                        " perMill=" + decimalFormatSymbols.getPerMill() +
                        " message: " + fail.getMessage() +
                        "\n"
                );
            }
        }

        this.checkEquals(
            "",
            b.toString()
        );
    }

    // HasText..........................................................................................................

    @Test
    public void testText() {
        this.textAndCheck(
            this.createObject(),
            "-,+,0,AUD,.,E,\",\",INFINITY,*,NAN,%,^"
        );
    }

    @Test
    public void testTextWithDecimalSeparatorComma() {
        this.textAndCheck(
            this.createObject()
                .setGroupSeparator(';')
                .setDecimalSeparator(','),
            "-,+,0,AUD,\",\",E,;,INFINITY,*,NAN,%,^"
        );
    }

    @Test
    public void testTextWithMonetaryDecimalSeparatorComma() {
        this.textAndCheck(
            this.createObject()
                .setGroupSeparator(';')
                .setMonetaryDecimalSeparator(','),
            "-,+,0,AUD,.,E,;,INFINITY,\",\",NAN,%,^"
        );
    }

    // parseString......................................................................................................

    @Test
    public void testParseWrongTokenCount() {
        final IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.parse("-,+,0")
        );

        this.checkEquals(
            "Expected 12 tokens but got 3",
            thrown.getMessage()
        );
    }

    @Test
    public void testParseEmptyNegativeSign() {
        final IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.parse(",+,0,$,.,E,\",\",\"INFINITY\",'*',\"NAN\",%,^")
        );

        this.checkEquals(
            "Empty \"negativeSign\"",
            thrown.getMessage()
        );
    }

    @Test
    public void testParse() {
        final DecimalNumberSymbols symbols = this.createObject();

        this.parseStringAndCheck(
            symbols.text(),
            symbols
        );
    }

    @Override
    public DecimalNumberSymbols parseString(final String text) {
        return DecimalNumberSymbols.parse(text);
    }

    @Override
    public Class<? extends RuntimeException> parseStringFailedExpected(final Class<? extends RuntimeException> thrown) {
        return thrown;
    }

    @Override
    public RuntimeException parseStringFailedExpected(final RuntimeException thrown) {
        return thrown;
    }

    // class............................................................................................................

    @Override
    public Class<DecimalNumberSymbols> type() {
        return DecimalNumberSymbols.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }
}
