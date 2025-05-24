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

import walkingkooka.EmptyTextException;
import walkingkooka.ToStringBuilder;
import walkingkooka.collect.list.CsvStringList;
import walkingkooka.predicate.Predicates;
import walkingkooka.predicate.character.CharPredicate;
import walkingkooka.predicate.character.CharPredicates;
import walkingkooka.text.CharSequences;
import walkingkooka.text.HasText;
import walkingkooka.text.printer.IndentingPrinter;
import walkingkooka.text.printer.TreePrintable;

import java.text.DecimalFormatSymbols;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Holds locale sensitive symbols related to the text representation or formatting of a number into text.
 */
public final class DecimalNumberSymbols implements DecimalNumberSymbolsLike,
    TreePrintable,
    HasText {

    private static final String NEGATIVE_SIGN_LABEL = "negativeSign";
    private static final String POSITIVE_SIGN_LABEL = "positiveSign";
    private static final String ZERO_DIGIT_LABEL = "zeroDigit";

    private static final String CURRENCY_SYMBOL_LABEL = "currencySymbol";
    private static final String DECIMAL_SEPARATOR_LABEL = "decimalSeparator";
    private static final String EXPONENT_SYMBOL_LABEL = "exponentSymbol";
    private static final String GROUP_SEPARATOR_LABEL = "groupSeparator";
    private static final String INFINITY_SYMBOL_LABEL = "infinitySymbol";
    private static final String MONETARY_DECIMAL_SEPARATOR_LABEL = "monetaryDecimalSeparator";
    private static final String NAN_SYMBOL_LABEL = "nanSymbol";
    private static final String PERCENT_SYMBOL_LABEL = "percentSymbol";
    private static final String PERMILL_SYMBOL_LABEL = "permillSymbol";

    /**
     * Parses the {@link String csv text} with each token interpreted as a character or string for each
     * of the {@link DecimalNumberSymbols} properties. This is the inverse of {@link #text()}.
     */
    public static DecimalNumberSymbols parse(final String text) {
        final CsvStringList csv = CsvStringList.parse(text);

        final int tokenCount = csv.size();
        if (12 != tokenCount) {
            throw new IllegalArgumentException("Expected 12 tokens but got " + tokenCount);
        }

        return with(
            stringToChar(NEGATIVE_SIGN_LABEL, csv.get(0)),
            stringToChar(POSITIVE_SIGN_LABEL, csv.get(1)),
            stringToChar(ZERO_DIGIT_LABEL, csv.get(2)),
            csv.get(3), // currencySymbol
            stringToChar(DECIMAL_SEPARATOR_LABEL, csv.get(4)),
            csv.get(5), // exponentSymbol
            stringToChar(GROUP_SEPARATOR_LABEL, csv.get(6)),
            csv.get(7), // infinitySymbol
            stringToChar(MONETARY_DECIMAL_SEPARATOR_LABEL, csv.get(8)),
            csv.get(9), // nanSymbol
            stringToChar(PERCENT_SYMBOL_LABEL, csv.get(10)),
            stringToChar(PERMILL_SYMBOL_LABEL, csv.get(11))
        );
    }

    private static char stringToChar(final String label,
                                     final String string) {
        final int length = string.length();
        switch (length) {
            case 0:
                throw new EmptyTextException(label);
            case 1:
                return string.charAt(0);
            default:
                throw new IllegalArgumentException("Invalid " + label + " expected 1 character but got " + length);
        }
    }

    public static DecimalNumberSymbols fromDecimalFormatSymbols(final char positiveSign,
                                                                final DecimalFormatSymbols symbols) {
        Objects.requireNonNull(symbols, "symbols");

        return with(
            symbols.getMinusSign(),
            positiveSign,
            symbols.getZeroDigit(),
            symbols.getCurrencySymbol(),
            symbols.getDecimalSeparator(),
            symbols.getExponentSeparator(),
            symbols.getGroupingSeparator(),
            symbols.getInfinity(),
            symbols.getMonetaryDecimalSeparator(),
            symbols.getNaN(),
            symbols.getPercent(),
            symbols.getPerMill()
        );
    }


    public static DecimalNumberSymbols with(final char negativeSign,
                                            final char positiveSign,
                                            final char zeroDigit,
                                            final String currencySymbol,
                                            final char decimalSeparator,
                                            final String exponentSymbol,
                                            final char groupSeparator,
                                            final String infinitySymbol,
                                            final char monetaryDecimalSeparator,
                                            final String nanSymbol,
                                            final char percentSymbol,
                                            final char permillSymbol) {
        return new DecimalNumberSymbols(
            checkCharacter(NEGATIVE_SIGN_LABEL, negativeSign),
            checkCharacter(POSITIVE_SIGN_LABEL, positiveSign),
            checkZeroDigit(zeroDigit),
            checkString(CURRENCY_SYMBOL_LABEL, currencySymbol),
            checkCharacter(DECIMAL_SEPARATOR_LABEL, decimalSeparator),
            checkString(EXPONENT_SYMBOL_LABEL, exponentSymbol),
            checkCharacter(GROUP_SEPARATOR_LABEL, groupSeparator),
            checkString(INFINITY_SYMBOL_LABEL, infinitySymbol),
            checkCharacter(MONETARY_DECIMAL_SEPARATOR_LABEL, monetaryDecimalSeparator),
            checkString(NAN_SYMBOL_LABEL, nanSymbol),
            checkCharacter(PERCENT_SYMBOL_LABEL, percentSymbol),
            checkPermillSymbol(permillSymbol)
        );
    }

    private DecimalNumberSymbols(final char negativeSign,
                                 final char positiveSign,
                                 final char zeroDigit,
                                 final String currencySymbol,
                                 final char decimalSeparator,
                                 final String exponentSymbol,
                                 final char groupSeparator,
                                 final String infinitySymbol,
                                 final char monetaryDecimalSeparator,
                                 final String nanSymbol,
                                 final char percentSymbol,
                                 final char permillSymbol) {
        this.negativeSign = negativeSign;
        this.positiveSign = positiveSign;
        this.zeroDigit = zeroDigit;

        this.decimalSeparator = decimalSeparator;
        ;
        this.groupSeparator = groupSeparator;
        this.monetaryDecimalSeparator = monetaryDecimalSeparator;
        this.percentSymbol = percentSymbol;
        this.permillSymbol = permillSymbol;

        failIfEqual(negativeSign, NEGATIVE_SIGN_LABEL, positiveSign, POSITIVE_SIGN_LABEL);
        failIfEqual(negativeSign, NEGATIVE_SIGN_LABEL, decimalSeparator, DECIMAL_SEPARATOR_LABEL);
        failIfEqual(negativeSign, NEGATIVE_SIGN_LABEL, groupSeparator, GROUP_SEPARATOR_LABEL);
        failIfEqual(negativeSign, NEGATIVE_SIGN_LABEL, monetaryDecimalSeparator, MONETARY_DECIMAL_SEPARATOR_LABEL);
        failIfEqual(negativeSign, NEGATIVE_SIGN_LABEL, percentSymbol, PERCENT_SYMBOL_LABEL);
        failIfEqual(negativeSign, NEGATIVE_SIGN_LABEL, permillSymbol, PERMILL_SYMBOL_LABEL);

        failIfEqual(positiveSign, POSITIVE_SIGN_LABEL, decimalSeparator, DECIMAL_SEPARATOR_LABEL);
        failIfEqual(positiveSign, POSITIVE_SIGN_LABEL, groupSeparator, GROUP_SEPARATOR_LABEL);
        failIfEqual(positiveSign, POSITIVE_SIGN_LABEL, monetaryDecimalSeparator, MONETARY_DECIMAL_SEPARATOR_LABEL);
        failIfEqual(positiveSign, POSITIVE_SIGN_LABEL, percentSymbol, PERCENT_SYMBOL_LABEL);
        failIfEqual(positiveSign, POSITIVE_SIGN_LABEL, permillSymbol, PERMILL_SYMBOL_LABEL);

        failIfEqual(decimalSeparator, DECIMAL_SEPARATOR_LABEL, groupSeparator, GROUP_SEPARATOR_LABEL);
        // decimalSeparator can be same as monetaryDecimalSeparator
        failIfEqual(decimalSeparator, DECIMAL_SEPARATOR_LABEL, percentSymbol, PERCENT_SYMBOL_LABEL);
        failIfEqual(decimalSeparator, DECIMAL_SEPARATOR_LABEL, permillSymbol, PERMILL_SYMBOL_LABEL);

        failIfEqual(groupSeparator, GROUP_SEPARATOR_LABEL, monetaryDecimalSeparator, MONETARY_DECIMAL_SEPARATOR_LABEL);
        failIfEqual(groupSeparator, GROUP_SEPARATOR_LABEL, percentSymbol, PERCENT_SYMBOL_LABEL);
        failIfEqual(groupSeparator, GROUP_SEPARATOR_LABEL, permillSymbol, PERMILL_SYMBOL_LABEL);

        failIfEqual(monetaryDecimalSeparator, MONETARY_DECIMAL_SEPARATOR_LABEL, percentSymbol, PERCENT_SYMBOL_LABEL);
        failIfEqual(monetaryDecimalSeparator, MONETARY_DECIMAL_SEPARATOR_LABEL, permillSymbol, PERMILL_SYMBOL_LABEL);

        failIfEqual(percentSymbol, PERCENT_SYMBOL_LABEL, permillSymbol, PERMILL_SYMBOL_LABEL);

        this.currencySymbol = Objects.requireNonNull(currencySymbol, CURRENCY_SYMBOL_LABEL);
        this.exponentSymbol = Objects.requireNonNull(exponentSymbol, EXPONENT_SYMBOL_LABEL);
        this.infinitySymbol = infinitySymbol;
        this.nanSymbol = nanSymbol;
    }

    private static void failIfEqual(final char left, final String leftLabel,
                                    final char right, final String rightLabel) {
        if (left == right) {
            // Invalid character "negativeSign" is same as "positiveSign" '+'
            throw new IllegalArgumentException(
                "Invalid character " +
                    CharSequences.quoteAndEscape(leftLabel) +
                    " is same as " +
                    CharSequences.quoteAndEscape(rightLabel) +
                    " " +
                    CharSequences.quoteAndEscape(right)
            );
        }
    }

    // negativeSign.....................................................................................................

    /**
     * Returns the negative sign.
     */
    @Override
    public char negativeSign() {
        return this.negativeSign;
    }

    public DecimalNumberSymbols setNegativeSign(final char negativeSign) {
        return this.negativeSign == negativeSign ?
            this :
            new DecimalNumberSymbols(
                checkCharacter(NEGATIVE_SIGN_LABEL, negativeSign),
                this.positiveSign,
                this.zeroDigit,
                this.currencySymbol,
                this.decimalSeparator,
                this.exponentSymbol,
                this.groupSeparator,
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                this.percentSymbol,
                this.permillSymbol
            );
    }

    private final char negativeSign;

    // positiveSign.....................................................................................................

    /**
     * Returns the positive sign.
     */
    @Override
    public char positiveSign() {
        return this.positiveSign;
    }

    public DecimalNumberSymbols setPositiveSign(final char positiveSign) {
        return this.positiveSign == positiveSign ?
            this :
            new DecimalNumberSymbols(
                this.negativeSign,
                checkCharacter(POSITIVE_SIGN_LABEL, positiveSign),
                this.zeroDigit,
                this.currencySymbol,
                this.decimalSeparator,
                this.exponentSymbol,
                this.groupSeparator,
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                this.percentSymbol,
                this.permillSymbol
            );
    }

    private final char positiveSign;

    // zeroDigit........................................................................................................

    /**
     * Returns the zero digit.
     */
    @Override
    public char zeroDigit() {
        return this.zeroDigit;
    }

    public DecimalNumberSymbols setZeroDigit(final char zeroDigit) {
        return this.zeroDigit == zeroDigit ?
            this :
            new DecimalNumberSymbols(
                this.negativeSign,
                this.positiveSign,
                checkZeroDigit(zeroDigit),
                this.currencySymbol,
                this.decimalSeparator,
                this.exponentSymbol,
                this.groupSeparator,
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                this.percentSymbol,
                this.permillSymbol
            );
    }

    private final char zeroDigit;

    // currencySymbol...................................................................................................

    /**
     * The currency symbol character.
     */
    @Override
    public String currencySymbol() {
        return this.currencySymbol;
    }

    public DecimalNumberSymbols setCurrencySymbol(final String currencySymbol) {
        return this.currencySymbol.equals(currencySymbol) ?
            this :
            new DecimalNumberSymbols(
                this.negativeSign,
                this.positiveSign,
                this.zeroDigit,
                checkString(CURRENCY_SYMBOL_LABEL, currencySymbol),
                this.decimalSeparator,
                this.exponentSymbol,
                this.groupSeparator,
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                this.percentSymbol,
                this.permillSymbol
            );
    }

    private final String currencySymbol;

    // decimalSeparator.................................................................................................

    /**
     * Returns the decimal separator character
     */
    @Override
    public char decimalSeparator() {
        return this.decimalSeparator;
    }

    public DecimalNumberSymbols setDecimalSeparator(final char decimalSeparator) {
        return this.decimalSeparator == decimalSeparator ?
            this :
            new DecimalNumberSymbols(
                this.negativeSign,
                this.positiveSign,
                this.zeroDigit,
                this.currencySymbol,
                checkCharacter(DECIMAL_SEPARATOR_LABEL, decimalSeparator),
                this.exponentSymbol,
                this.groupSeparator,
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                this.percentSymbol,
                this.permillSymbol
            );
    }

    private final char decimalSeparator;

    // exponentSymbol...................................................................................................

    /**
     * The exponent symbol
     */
    @Override
    public String exponentSymbol() {
        return this.exponentSymbol;
    }

    public DecimalNumberSymbols setExponentSymbol(final String exponentSymbol) {
        return this.exponentSymbol.equals(exponentSymbol) ?
            this :
            new DecimalNumberSymbols(
                this.negativeSign,
                this.positiveSign,
                this.zeroDigit,
                this.currencySymbol,
                this.decimalSeparator,
                checkString(EXPONENT_SYMBOL_LABEL, exponentSymbol),
                this.groupSeparator,
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                this.percentSymbol,
                this.permillSymbol
            );
    }

    private final String exponentSymbol;

    // groupSeparator...................................................................................................

    /**
     * The group separator.
     */
    @Override
    public char groupSeparator() {
        return this.groupSeparator;
    }

    public DecimalNumberSymbols setGroupSeparator(final char groupSeparator) {
        return this.groupSeparator == groupSeparator ?
            this :
            new DecimalNumberSymbols(
                this.negativeSign,
                this.positiveSign,
                this.zeroDigit,
                this.currencySymbol,
                this.decimalSeparator,
                this.exponentSymbol,
                checkCharacter(GROUP_SEPARATOR_LABEL, groupSeparator),
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                this.percentSymbol,
                this.permillSymbol
            );
    }

    private final char groupSeparator;

    // infinitySymbol.........................................................................................................

    /**
     * The INFINITY symbol.
     */
    @Override
    public String infinitySymbol() {
        return this.infinitySymbol;
    }

    public DecimalNumberSymbols setInfinitySymbol(final String infinitySymbol) {
        return this.infinitySymbol.equals(infinitySymbol) ?
            this :
            new DecimalNumberSymbols(
                this.negativeSign,
                this.positiveSign,
                this.zeroDigit,
                this.currencySymbol,
                this.decimalSeparator,
                this.exponentSymbol,
                this.groupSeparator,
                checkString(INFINITY_SYMBOL_LABEL, infinitySymbol),
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                this.percentSymbol,
                this.permillSymbol
            );
    }

    private final String infinitySymbol;

    // monetaryDecimalSeparator.........................................................................................

    /**
     * Returns the monetary decimal separator character
     */
    @Override
    public char monetaryDecimalSeparator() {
        return this.monetaryDecimalSeparator;
    }

    public DecimalNumberSymbols setMonetaryDecimalSeparator(final char monetaryDecimalSeparator) {
        return this.monetaryDecimalSeparator == monetaryDecimalSeparator ?
            this :
            new DecimalNumberSymbols(
                this.negativeSign,
                this.positiveSign,
                this.zeroDigit,
                this.currencySymbol,
                decimalSeparator,
                this.exponentSymbol,
                this.groupSeparator,
                this.infinitySymbol,
                checkCharacter(MONETARY_DECIMAL_SEPARATOR_LABEL, monetaryDecimalSeparator),
                this.nanSymbol,
                this.percentSymbol,
                this.permillSymbol
            );
    }

    private final char monetaryDecimalSeparator;

    // nanSymbol........................................................................................................

    /**
     * The NAN symbol.
     */
    @Override
    public String nanSymbol() {
        return this.nanSymbol;
    }

    public DecimalNumberSymbols setNanSymbol(final String nanSymbol) {
        return this.nanSymbol.equals(nanSymbol) ?
            this :
            new DecimalNumberSymbols(
                this.negativeSign,
                this.positiveSign,
                this.zeroDigit,
                this.currencySymbol,
                this.decimalSeparator,
                this.exponentSymbol,
                this.groupSeparator,
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                checkString(NAN_SYMBOL_LABEL, nanSymbol),
                this.percentSymbol,
                this.permillSymbol
            );
    }

    private final String nanSymbol;

    // percentSymbol.................................................................................................

    /**
     * The percent symbol.
     */
    @Override
    public char percentSymbol() {
        return this.percentSymbol;
    }

    public DecimalNumberSymbols setPercentSymbol(final char percentSymbol) {
        return this.percentSymbol == percentSymbol ?
            this :
            new DecimalNumberSymbols(
                this.negativeSign,
                this.positiveSign,
                this.zeroDigit,
                this.currencySymbol,
                this.decimalSeparator,
                this.exponentSymbol,
                this.groupSeparator,
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                checkCharacter(PERCENT_SYMBOL_LABEL, percentSymbol),
                this.permillSymbol
            );
    }

    private final char percentSymbol;

    // permillSymbol....................................................................................................

    /**
     * The permill symbol.
     */
    @Override
    public char permillSymbol() {
        return this.permillSymbol;
    }

    public DecimalNumberSymbols setPermillSymbol(final char permillSymbol) {
        return this.permillSymbol == permillSymbol ?
            this :
            new DecimalNumberSymbols(
                this.negativeSign,
                this.positiveSign,
                this.zeroDigit,
                this.currencySymbol,
                this.decimalSeparator,
                this.exponentSymbol,
                this.groupSeparator,
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                this.percentSymbol,
                checkCharacter(PERMILL_SYMBOL_LABEL, permillSymbol)
            );
    }

    private final char permillSymbol;

    // helper...........................................................................................................

    private final static CharPredicate PRINTABLE = CharPredicates.asciiControl()
        .negate();

    /**
     * This {@link CharPredicate} is used to verify all characters except for {@link #permillSymbol()} and {@link #zeroDigit}
     */
    public final static CharPredicate SYMBOL = PRINTABLE.and(
        CharPredicates.letterOrDigit()
            .negate()
    ).and(
        CharPredicates.whitespace()
            .negate()
    ).setToString("symbol");

    private static char checkCharacter(final String label,
                                       final char c) {
        if (false == SYMBOL.test(c)) {
            throw new DecimalNumberSymbolsInvalidCharacterException(label, c);
        }
        return c;
    }

    /**
     * A {@link CharPredicate} that may be used to test if a character is a valid PERMILL symbol.
     */
    public final static CharPredicate PERMILL_SYMBOL = PRINTABLE.and(
        CharPredicates.letter()
            .negate()
    ).and(
        CharPredicates.whitespace()
            .negate()
    ).setToString(PERMILL_SYMBOL_LABEL);

    private static char checkPermillSymbol(final char c) {
        if (false == PERMILL_SYMBOL.test(c)) {
            throw new DecimalNumberSymbolsInvalidCharacterException(PERMILL_SYMBOL_LABEL, c);
        }
        return c;
    }

    private static String checkString(final String label,
                                      final String value) {
        return CharPredicates.failIfNullOrEmptyOrInitialAndPartFalse(
            value,
            label,
            PRINTABLE,
            PRINTABLE
        );
    }

    /**
     * This {@link Predicate} may be used to verify any of the {@link String} properties of a {@link DecimalNumberSymbols}
     * such as {@link DecimalNumberSymbols#currencySymbol()}, {@link DecimalNumberSymbols#infinitySymbol()}, {@link DecimalNumberSymbols#nanSymbol()}.
     */
    public final static Predicate<CharSequence> STRING = Predicates.initialAndPart(
        PRINTABLE,
        PRINTABLE
    );

    /**
     * May be used to test if the given character is a valid zero digit.
     */
    public final static CharPredicate ZERO_DIGIT = CharPredicates.digit()
        .setToString(ZERO_DIGIT_LABEL);

    private static char checkZeroDigit(final char zeroDigit) {
        if (false == ZERO_DIGIT.test(zeroDigit)) {
            throw new DecimalNumberSymbolsInvalidCharacterException(ZERO_DIGIT_LABEL, zeroDigit);
        }

        return zeroDigit;
    }

    // HasText..........................................................................................................

    /**
     * Returns a CSV string with the individual symbols.
     */
    @Override
    public String text() {
        return CsvStringList.EMPTY.concat(
                String.valueOf(this.negativeSign)
            ).concat(
                String.valueOf(this.positiveSign)
            ).concat(
                String.valueOf(this.zeroDigit)
            ).concat(this.currencySymbol)
            .concat(
                String.valueOf(this.decimalSeparator)
            ).concat(this.exponentSymbol)
            .concat(
                String.valueOf(this.groupSeparator)
            )
            .concat(this.infinitySymbol)
            .concat(
                String.valueOf(this.monetaryDecimalSeparator)
            ).concat(this.nanSymbol)
            .concat(
                String.valueOf(this.percentSymbol)
            ).concat(
                String.valueOf(this.permillSymbol)
            ).text();
    }

    // Object...........................................................................................................

    @Override
    public int hashCode() {
        return Objects.hash(
            this.negativeSign,
            this.positiveSign,
            this.zeroDigit,
            this.currencySymbol,
            this.decimalSeparator,
            this.exponentSymbol,
            this.groupSeparator,
            this.infinitySymbol,
            this.monetaryDecimalSeparator,
            this.nanSymbol,
            this.percentSymbol,
            this.permillSymbol
        );
    }

    @Override
    public boolean equals(final Object other) {
        return this == other || other instanceof DecimalNumberSymbols && this.equals0((DecimalNumberSymbols) other);
    }

    private boolean equals0(final DecimalNumberSymbols other) {
        return this.negativeSign == other.negativeSign &&
            this.positiveSign == other.positiveSign &&
            this.zeroDigit == other.zeroDigit &&
            this.currencySymbol.equals(other.currencySymbol) &&
            this.decimalSeparator == other.decimalSeparator &&
            this.exponentSymbol.equals(other.exponentSymbol) &&
            this.groupSeparator == other.groupSeparator &&
            this.infinitySymbol.equals(other.infinitySymbol) &&
            this.monetaryDecimalSeparator == other.monetaryDecimalSeparator &&
            this.nanSymbol.equals(other.nanSymbol) &&
            this.percentSymbol == other.percentSymbol &&
            this.permillSymbol == other.permillSymbol;
    }

    @Override
    public String toString() {
        return ToStringBuilder.empty()
            .label(NEGATIVE_SIGN_LABEL).value(this.negativeSign)
            .label(POSITIVE_SIGN_LABEL).value(this.positiveSign)
            .label(ZERO_DIGIT_LABEL).value(this.zeroDigit)
            .label(CURRENCY_SYMBOL_LABEL).value(this.currencySymbol)
            .label(DECIMAL_SEPARATOR_LABEL).value(this.decimalSeparator)
            .label(EXPONENT_SYMBOL_LABEL).value(this.exponentSymbol)
            .label(GROUP_SEPARATOR_LABEL).value(this.groupSeparator)
            .label(INFINITY_SYMBOL_LABEL).value(this.infinitySymbol)
            .label(MONETARY_DECIMAL_SEPARATOR_LABEL).value(this.monetaryDecimalSeparator)
            .label(NAN_SYMBOL_LABEL).value(this.nanSymbol)
            .label(PERCENT_SYMBOL_LABEL).value(this.percentSymbol)
            .label(PERMILL_SYMBOL_LABEL).value(this.permillSymbol)
            .build();
    }

    // TreePrintable....................................................................................................

    @Override
    public void printTree(IndentingPrinter printer) {
        printer.println(this.getClass().getSimpleName());

        printer.indent();
        {
            this.printLabelAndValues(
                NEGATIVE_SIGN_LABEL,
                this.negativeSign,
                printer
            );
            this.printLabelAndValues(
                POSITIVE_SIGN_LABEL,
                this.positiveSign,
                printer
            );
            this.printLabelAndValues(
                ZERO_DIGIT_LABEL,
                this.zeroDigit,
                printer
            );
            this.printLabelAndValues(
                CURRENCY_SYMBOL_LABEL,
                this.currencySymbol,
                printer
            );
            this.printLabelAndValues(
                DECIMAL_SEPARATOR_LABEL,
                this.decimalSeparator,
                printer
            );
            this.printLabelAndValues(
                EXPONENT_SYMBOL_LABEL,
                this.exponentSymbol,
                printer
            );
            this.printLabelAndValues(
                GROUP_SEPARATOR_LABEL,
                this.groupSeparator,
                printer
            );
            this.printLabelAndValues(
                INFINITY_SYMBOL_LABEL,
                this.infinitySymbol,
                printer
            );
            this.printLabelAndValues(
                MONETARY_DECIMAL_SEPARATOR_LABEL,
                this.monetaryDecimalSeparator,
                printer
            );
            this.printLabelAndValues(
                NAN_SYMBOL_LABEL,
                this.nanSymbol,
                printer
            );
            this.printLabelAndValues(
                PERCENT_SYMBOL_LABEL,
                this.percentSymbol,
                printer
            );
            this.printLabelAndValues(
                PERMILL_SYMBOL_LABEL,
                this.permillSymbol,
                printer
            );
        }
        printer.outdent();
    }

    private void printLabelAndValues(final String label,
                                     final Object value,
                                     final IndentingPrinter printer) {
        printer.println(label);

        printer.indent();
        {
            printer.println(
                CharSequences.quoteIfChars(value)
            );
        }
        printer.outdent();
    }
}
