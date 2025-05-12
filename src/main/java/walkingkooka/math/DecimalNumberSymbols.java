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

import walkingkooka.ToStringBuilder;
import walkingkooka.collect.list.CsvStringList;
import walkingkooka.predicate.character.CharPredicate;
import walkingkooka.predicate.character.CharPredicates;
import walkingkooka.text.CharSequences;
import walkingkooka.text.HasText;
import walkingkooka.text.printer.IndentingPrinter;
import walkingkooka.text.printer.TreePrintable;

import java.text.DecimalFormatSymbols;
import java.util.Objects;

/**
 * Holds locale sensitive symbols related to the text representation or formatting of a number into text.
 */
public final class DecimalNumberSymbols implements TreePrintable,
    HasText {

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
            symbols.getPercent()
        );
    }


    public static DecimalNumberSymbols with(final char negativeSign,
                                            final char positiveSign,
                                            final char zeroDigit,
                                            final String currencySymbol,
                                            final char decimalSeparator,
                                            final String exponentSymbol,
                                            final char groupSeparator,
                                            final char percentageSymbol) {
        return new DecimalNumberSymbols(
            checkCharacter("negativeSign", negativeSign),
            checkCharacter("positiveSign", positiveSign),
            checkZeroDigit(zeroDigit),
            checkString("currencySymbol", currencySymbol),
            checkCharacter("decimalSeparator", decimalSeparator),
            checkString("exponentSymbol", exponentSymbol),
            checkCharacter("groupSeparator", groupSeparator),
            checkCharacter("percentageSymbol", percentageSymbol)
        );
    }

    private DecimalNumberSymbols(final char negativeSign,
                                 final char positiveSign,
                                 final char zeroDigit,
                                 final String currencySymbol,
                                 final char decimalSeparator,
                                 final String exponentSymbol,
                                 final char groupSeparator,
                                 final char percentageSymbol) {
        this.negativeSign = negativeSign;
        this.positiveSign = positiveSign;
        this.zeroDigit = zeroDigit;

        this.decimalSeparator = decimalSeparator;;
        this.groupSeparator = groupSeparator;
        this.percentageSymbol = percentageSymbol;

        failIfEqual(negativeSign, "negativeSign", positiveSign, "positiveSign");
        failIfEqual(negativeSign, "negativeSign", decimalSeparator, "decimalSeparator");
        failIfEqual(negativeSign, "negativeSign", groupSeparator, "groupSeparator");
        failIfEqual(negativeSign, "negativeSign", percentageSymbol, "percentageSymbol");

        failIfEqual(positiveSign, "positiveSign", decimalSeparator, "decimalSeparator");
        failIfEqual(positiveSign, "positiveSign", groupSeparator, "groupSeparator");
        failIfEqual(positiveSign, "positiveSign", percentageSymbol, "percentageSymbol");

        failIfEqual(decimalSeparator, "decimalSeparator", groupSeparator, "groupSeparator");
        failIfEqual(decimalSeparator, "decimalSeparator", percentageSymbol, "percentageSymbol");

        failIfEqual(groupSeparator, "groupSeparator", percentageSymbol, "percentageSymbol");

        this.currencySymbol = Objects.requireNonNull(currencySymbol, "currencySymbol");
        this.exponentSymbol = Objects.requireNonNull(exponentSymbol, "exponentSymbol");
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
    
    /**
     * Returns the negative sign.
     */
    public char negativeSign() {
        return this.negativeSign;
    }

    public DecimalNumberSymbols setNegativeSign(final char negativeSign) {
        return this.negativeSign == negativeSign ?
            this :
            new DecimalNumberSymbols(
                checkCharacter("negativeSign", negativeSign),
                this.positiveSign,
                this.zeroDigit,
                this.currencySymbol,
                this.decimalSeparator,
                this.exponentSymbol,
                this.groupSeparator,
                this.percentageSymbol
            );
    }

    private final char negativeSign;

    /**
     * Returns the positive sign.
     */
    public char positiveSign() {
        return this.positiveSign;
    }

    public DecimalNumberSymbols setPositiveSign(final char positiveSign) {
        return this.positiveSign == positiveSign ?
            this :
            new DecimalNumberSymbols(
                this.negativeSign,
                checkCharacter("positiveSign", positiveSign),
                this.zeroDigit,
                this.currencySymbol,
                this.decimalSeparator,
                this.exponentSymbol,
                this.groupSeparator,
                this.percentageSymbol
            );
    }

    private final char positiveSign;

    /**
     * Returns the zero digit.
     */
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
                this.percentageSymbol
            );
    }

    private final char zeroDigit;

    /**
     * The currency symbol character.
     */
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
                checkString("currencySymbol", currencySymbol),
                this.decimalSeparator,
                this.exponentSymbol,
                this.groupSeparator,
                this.percentageSymbol
            );
    }

    private final String currencySymbol;

    /**
     * Returns the decimal separator character
     */
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
                checkCharacter("decimalSeparator", decimalSeparator),
                this.exponentSymbol,
                this.groupSeparator,
                this.percentageSymbol
            );
    }

    private final char decimalSeparator;

    /**
     * The exponent symbol
     */
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
                checkString("exponentSymbol", exponentSymbol),
                this.groupSeparator,
                this.percentageSymbol
            );
    }

    private final String exponentSymbol;

    /**
     * The group separator.
     */
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
                checkCharacter("groupSeparator", groupSeparator),
                this.percentageSymbol
            );
    }

    private final char groupSeparator;

    /**
     * The percentage symbol.
     */
    public char percentageSymbol() {
        return this.percentageSymbol;
    }

    public DecimalNumberSymbols setPercentageSymbol(final char percentageSymbol) {
        return this.percentageSymbol == percentageSymbol ?
            this :
            new DecimalNumberSymbols(
                this.negativeSign,
                this.positiveSign,
                this.zeroDigit,
                this.currencySymbol,
                this.decimalSeparator,
                this.exponentSymbol,
                this.groupSeparator,
                checkCharacter("percentageSymbol", percentageSymbol)
            );
    }

    private final char percentageSymbol;

    // helper...........................................................................................................

    private final static CharPredicate PRINTABLE = CharPredicates.asciiControl()
        .negate();

    private final static CharPredicate SYMBOL = PRINTABLE.and(
        CharPredicates.letterOrDigit()
            .negate()
    ).and(
        CharPredicates.whitespace()
            .negate()
    );

    private static char checkCharacter(final String label,
                                       final char c) {
        if (false == SYMBOL.test(c)) {
            throw new IllegalArgumentException("Invalid " + label + " " + CharSequences.quoteAndEscape(c) + " is not a symbol");
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

    private static char checkZeroDigit(final char zeroDigit) {
        if (false == Character.isDigit(zeroDigit)) {
            throw new IllegalArgumentException("Invalid zero digit " + CharSequences.quoteIfChars(zeroDigit));
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
            ).concat(
                String.valueOf(this.percentageSymbol)
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
            this.percentageSymbol
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
            this.percentageSymbol == other.percentageSymbol;
    }

    @Override
    public String toString() {
        return ToStringBuilder.empty()
            .label("negativeSign").value(this.negativeSign)
            .label("positiveSign").value(this.positiveSign)
            .label("zeroDigit").value(this.zeroDigit)
            .label("currencySymbol").value(this.currencySymbol)
            .label("decimalSeparator").value(this.decimalSeparator)
            .label("exponentSymbol").value(this.exponentSymbol)
            .label("groupSeparator").value(this.groupSeparator)
            .label("percentageSymbol").value(this.percentageSymbol)
            .build();
    }

    // TreePrintable....................................................................................................

    @Override
    public void printTree(IndentingPrinter printer) {
        printer.print(this.getClass().getSimpleName());

        printer.indent();
        {
            this.printLabelAndValues(
                "negativeSign",
                this.negativeSign,
                printer
            );
            this.printLabelAndValues(
                "positiveSign",
                this.positiveSign,
                printer
            );
            this.printLabelAndValues(
                "zeroDigit",
                this.zeroDigit,
                printer
            );
            this.printLabelAndValues(
                "currencySymbol",
                this.currencySymbol,
                printer
            );
            this.printLabelAndValues(
                "decimalSeparator",
                this.decimalSeparator,
                printer
            );
            this.printLabelAndValues(
                "exponentSymbol",
                this.exponentSymbol,
                printer
            );
            this.printLabelAndValues(
                "groupSeparator",
                this.groupSeparator,
                printer
            );
            this.printLabelAndValues(
                "percentageSymbol",
                this.percentageSymbol,
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
