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
    TreePrintableTesting,
    ClassTesting<DecimalNumberSymbols> {

    private final static char NEGATIVE_SIGN = '-';
    private final static char POSITIVE_SIGN = '+';
    private final static char ZERO_DIGIT = '0';
    
    private final static String CURRENCY_SYMBOL = "AUD";
    private final static char DECIMAL_SEPARATOR = '.';
    private final static String EXPONENT_SYMBOL = "E";
    private final static char GROUP_SEPARATOR = ',';
    private final static char PERCENTAGE_SYMBOL = '%';

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
                PERCENTAGE_SYMBOL
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
                PERCENTAGE_SYMBOL
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
                PERCENTAGE_SYMBOL
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
                PERCENTAGE_SYMBOL
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
                PERCENTAGE_SYMBOL
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
                PERCENTAGE_SYMBOL
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
                PERCENTAGE_SYMBOL
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
                PERCENTAGE_SYMBOL
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
                PERCENTAGE_SYMBOL
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
                PERCENTAGE_SYMBOL
            )
        );
    }

    @Test
    public void testWithInvalidPercentageSymbolFails() {
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
                PERCENTAGE_SYMBOL
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
                PERCENTAGE_SYMBOL
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
                PERCENTAGE_SYMBOL
            )
        );

        this.checkEquals(
            "Invalid character \"negativeSign\" is same as \"groupSeparator\" ','",
            thrown.getMessage()
        );
    }

    @Test
    public void testWithNegativeSignEqualPercentageSymbolFails() {
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
                PERCENTAGE_SYMBOL
            )
        );

        this.checkEquals(
            "Invalid character \"negativeSign\" is same as \"percentageSymbol\" '%'",
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
                PERCENTAGE_SYMBOL
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
                PERCENTAGE_SYMBOL
            )
        );

        this.checkEquals(
            "Invalid character \"positiveSign\" is same as \"groupSeparator\" ','",
            thrown.getMessage()
        );
    }

    @Test
    public void testWithPositiveSignEqualPercentageSymbolFails() {
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
                PERCENTAGE_SYMBOL
            )
        );

        this.checkEquals(
            "Invalid character \"positiveSign\" is same as \"percentageSymbol\" '%'",
            thrown.getMessage()
        );
    }

    @Test
    public void testWithGroupSeparatorEqualPercentageSymbolFails() {
        final IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                PERCENTAGE_SYMBOL,
                PERCENTAGE_SYMBOL
            )
        );

        this.checkEquals(
            "Invalid character \"groupSeparator\" is same as \"percentageSymbol\" '%'",
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
            PERCENTAGE_SYMBOL
        );

        this.negativeSignAndCheck(symbols);
        this.positiveSignAndCheck(symbols);
        this.currencySymbolAndCheck(symbols);
        this.decimalSeparatorAndCheck(symbols);
        this.exponentSymbolAndCheck(symbols);
        this.groupSeparatorAndCheck(symbols);
        this.percentageSymbolAndCheck(symbols);
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
    public void testSetNegativeSignWithPercentageSymbolFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setNegativeSign(PERCENTAGE_SYMBOL)
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
        this.percentageSymbolAndCheck(different);
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
    public void testSetPositiveSignWithPercentageSymbolFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPositiveSign(PERCENTAGE_SYMBOL)
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
        this.percentageSymbolAndCheck(different);
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
        this.percentageSymbolAndCheck(different);
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
        this.percentageSymbolAndCheck(different);
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
    public void testSetDecimalSeparatorWithPercentageSymbolFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setDecimalSeparator(PERCENTAGE_SYMBOL)
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
        this.percentageSymbolAndCheck(different);
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
        this.percentageSymbolAndCheck(different);
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
    public void testSetGroupSeparatorWithPercentageSymbolFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setGroupSeparator(PERCENTAGE_SYMBOL)
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
        this.percentageSymbolAndCheck(different);
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

    // percentageSymbol.................................................................................................

    @Test
    public void testSetPercentageSymbolWithInvalidFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPercentageSymbol(INVALID_CHAR)
        );
    }

    @Test
    public void testSetPercentageSymbolWithNegativeSignFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPercentageSymbol(NEGATIVE_SIGN)
        );
    }

    @Test
    public void testSetPercentageSymbolWithPositiveSignFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPercentageSymbol(POSITIVE_SIGN)
        );
    }

    @Test
    public void testSetPercentageSymbolWithDecimalSeparatorFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPercentageSymbol(DECIMAL_SEPARATOR)
        );
    }

    @Test
    public void testSetPercentageSymbolWithGroupSeparatorFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createObject()
                .setPercentageSymbol(GROUP_SEPARATOR)
        );
    }

    @Test
    public void testSetPercentageSymbolSame() {
        final DecimalNumberSymbols symbols = this.createObject();

        assertSame(
            symbols,
            symbols.setPercentageSymbol(PERCENTAGE_SYMBOL)
        );
    }

    @Test
    public void testSetPercentageSymbolDifferent() {
        final DecimalNumberSymbols symbols = this.createObject();
        final DecimalNumberSymbols different = symbols.setPercentageSymbol(DIFFERENT_CHAR);

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
        this.percentageSymbolAndCheck(
            different,
            DIFFERENT_CHAR
        );
    }

    private void percentageSymbolAndCheck(final DecimalNumberSymbols symbols) {
        this.percentageSymbolAndCheck(
            symbols,
            PERCENTAGE_SYMBOL
        );
    }

    private void percentageSymbolAndCheck(final DecimalNumberSymbols symbols,
                                          final char expected) {
        this.checkEquals(
            expected,
            symbols.percentageSymbol()
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
                PERCENTAGE_SYMBOL
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
                PERCENTAGE_SYMBOL
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
                PERCENTAGE_SYMBOL
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
                PERCENTAGE_SYMBOL
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
                PERCENTAGE_SYMBOL
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
                PERCENTAGE_SYMBOL
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
                PERCENTAGE_SYMBOL
            )
        );
    }

    @Test
    public void testEqualsDifferentPercentageSymbol() {
        this.checkNotEquals(
            DecimalNumberSymbols.with(
                NEGATIVE_SIGN,
                POSITIVE_SIGN,
                ZERO_DIGIT,
                CURRENCY_SYMBOL,
                DECIMAL_SEPARATOR,
                EXPONENT_SYMBOL,
                GROUP_SEPARATOR,
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
            PERCENTAGE_SYMBOL
        );
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createObject(),
            "negativeSign='-' positiveSign='+' zeroDigit='0' currencySymbol=\"AUD\" decimalSeparator='.' exponentSymbol=\"E\" groupSeparator=',' percentageSymbol='%'"
        );
    }

    // TreePrintable....................................................................................................

    @Test
    public void testTreePrintable() {
        this.treePrintAndCheck(
            this.createObject(),
            "DecimalNumberSymbolsnegativeSign\n" +
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
                "  percentageSymbol\n" +
                "    '%'\n"
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
        this.percentageSymbolAndCheck(symbols, '%');
    }

    @Test
    public void testFromDecimalFormatSymbolsFromAllLocales() {
        for (final Locale locale : Locale.getAvailableLocales()) {
            DecimalFormatSymbols.getInstance(locale);
        }
    }

    // HasText..........................................................................................................

    @Test
    public void testText() {
        this.textAndCheck(
            this.createObject(),
            "-,+,0,AUD,.,E,\",\",%"
        );
    }

    @Test
    public void testTextWithDecimalSeparatorComma() {
        this.textAndCheck(
            this.createObject()
                .setGroupSeparator(';')
                .setDecimalSeparator(','),
            "-,+,0,AUD,\",\",E,;,%"
        );
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
