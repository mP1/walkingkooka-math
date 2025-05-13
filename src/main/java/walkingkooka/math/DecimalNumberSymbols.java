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
            stringToChar("negativeSign", csv.get(0)),
            stringToChar("positiveSign", csv.get(1)),
            stringToChar("zeroDigit", csv.get(2)),
            csv.get(3), // currencySymbol
            stringToChar("decimalSeparator", csv.get(4)),
            csv.get(5), // exponentSymbol
            stringToChar("groupSeparator", csv.get(6)),
            csv.get(7), // infinitySymbol
            stringToChar("decimalSeparator", csv.get(8)),
            csv.get(9), // nanSymbol
            stringToChar("percentageSymbol", csv.get(10)),
            stringToChar("permillSymbol", csv.get(11))
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
                                            final char percentageSymbol,
                                            final char permillSymbol) {
        return new DecimalNumberSymbols(
            checkCharacter("negativeSign", negativeSign),
            checkCharacter("positiveSign", positiveSign),
            checkZeroDigit(zeroDigit),
            checkString("currencySymbol", currencySymbol),
            checkCharacter("decimalSeparator", decimalSeparator),
            checkString("exponentSymbol", exponentSymbol),
            checkCharacter("groupSeparator", groupSeparator),
            checkString("infinitySymbol", infinitySymbol),
            checkCharacter("monetaryDecimalSeparator", monetaryDecimalSeparator),
            checkString("nanSymbol", nanSymbol),
            checkCharacter("percentageSymbol", percentageSymbol),
            checkPermillSymbol("permillSymbol", permillSymbol)
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
                                 final char percentageSymbol,
                                 final char permillSymbol) {
        this.negativeSign = negativeSign;
        this.positiveSign = positiveSign;
        this.zeroDigit = zeroDigit;

        this.decimalSeparator = decimalSeparator;
        ;
        this.groupSeparator = groupSeparator;
        this.monetaryDecimalSeparator = monetaryDecimalSeparator;
        this.percentageSymbol = percentageSymbol;
        this.permillSymbol = permillSymbol;

        failIfEqual(negativeSign, "negativeSign", positiveSign, "positiveSign");
        failIfEqual(negativeSign, "negativeSign", decimalSeparator, "decimalSeparator");
        failIfEqual(negativeSign, "negativeSign", groupSeparator, "groupSeparator");
        failIfEqual(negativeSign, "negativeSign", monetaryDecimalSeparator, "monetaryDecimalSeparator");
        failIfEqual(negativeSign, "negativeSign", percentageSymbol, "percentageSymbol");
        failIfEqual(negativeSign, "negativeSign", permillSymbol, "permillSymbol");

        failIfEqual(positiveSign, "positiveSign", decimalSeparator, "decimalSeparator");
        failIfEqual(positiveSign, "positiveSign", groupSeparator, "groupSeparator");
        failIfEqual(positiveSign, "positiveSign", monetaryDecimalSeparator, "monetaryDecimalSeparator");
        failIfEqual(positiveSign, "positiveSign", percentageSymbol, "percentageSymbol");
        failIfEqual(positiveSign, "positiveSign", permillSymbol, "permillSymbol");

        failIfEqual(decimalSeparator, "decimalSeparator", groupSeparator, "groupSeparator");
        // decimalSeparator can be same as monetaryDecimalSeparator
        failIfEqual(decimalSeparator, "decimalSeparator", percentageSymbol, "percentageSymbol");
        failIfEqual(decimalSeparator, "decimalSeparator", permillSymbol, "permillSymbol");

        failIfEqual(groupSeparator, "groupSeparator", monetaryDecimalSeparator, "monetaryDecimalSeparator");
        failIfEqual(groupSeparator, "groupSeparator", percentageSymbol, "percentageSymbol");
        failIfEqual(groupSeparator, "groupSeparator", permillSymbol, "permillSymbol");

        failIfEqual(monetaryDecimalSeparator, "monetaryDecimalSeparator", percentageSymbol, "percentageSymbol");
        failIfEqual(monetaryDecimalSeparator, "monetaryDecimalSeparator", permillSymbol, "permillSymbol");

        failIfEqual(percentageSymbol, "percentageSymbol", permillSymbol, "permillSymbol");

        this.currencySymbol = Objects.requireNonNull(currencySymbol, "currencySymbol");
        this.exponentSymbol = Objects.requireNonNull(exponentSymbol, "exponentSymbol");
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
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                this.percentageSymbol,
                this.permillSymbol
            );
    }

    private final char negativeSign;

    // positiveSign.....................................................................................................

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
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                this.percentageSymbol,
                this.permillSymbol
            );
    }

    private final char positiveSign;

    // zeroDigit........................................................................................................

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
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                this.percentageSymbol,
                this.permillSymbol
            );
    }

    private final char zeroDigit;

    // currencySymbol...................................................................................................

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
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                this.percentageSymbol,
                this.permillSymbol
            );
    }

    private final String currencySymbol;

    // decimalSeparator.................................................................................................

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
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                this.percentageSymbol,
                this.permillSymbol
            );
    }

    private final char decimalSeparator;

    // exponentSymbol...................................................................................................

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
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                this.percentageSymbol,
                this.permillSymbol
            );
    }

    private final String exponentSymbol;

    // groupSeparator...................................................................................................

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
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                this.percentageSymbol,
                this.permillSymbol
            );
    }

    private final char groupSeparator;

    // infinitySymbol.........................................................................................................

    /**
     * The currency symbol character.
     */
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
                checkString("infinitySymbol", infinitySymbol),
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                this.percentageSymbol,
                this.permillSymbol
            );
    }

    private final String infinitySymbol;

    // monetaryDecimalSeparator.........................................................................................

    /**
     * Returns the monetary decimal separator character
     */
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
                checkCharacter("monetaryDecimalSeparator", monetaryDecimalSeparator),
                this.nanSymbol,
                this.percentageSymbol,
                this.permillSymbol
            );
    }

    private final char monetaryDecimalSeparator;

    // nanSymbol........................................................................................................

    /**
     * The currency symbol character.
     */
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
                checkString("nanSymbol", nanSymbol),
                this.percentageSymbol,
                this.permillSymbol
            );
    }

    private final String nanSymbol;

    // percentageSymbol.................................................................................................

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
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                checkCharacter("percentageSymbol", percentageSymbol),
                this.permillSymbol
            );
    }

    private final char percentageSymbol;

    // permillSymbol....................................................................................................

    /**
     * The permill symbol.
     */
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
                this.percentageSymbol,
                checkCharacter("permillSymbol", permillSymbol)
            );
    }

    private final char permillSymbol;

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

    private final static CharPredicate PERMILL_SYMBOL = PRINTABLE.and(
        CharPredicates.letter()
            .negate()
    ).and(
        CharPredicates.whitespace()
            .negate()
    );

    private static char checkPermillSymbol(final String label,
                                           final char c) {
        if (false == PERMILL_SYMBOL.test(c)) {
            throw new IllegalArgumentException("Invalid " + label + " " + CharSequences.quoteAndEscape(c) + " is not a permill symbol");
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
            )
            .concat(this.infinitySymbol)
            .concat(
                String.valueOf(this.monetaryDecimalSeparator)
            ).concat(this.nanSymbol)
            .concat(
                String.valueOf(this.percentageSymbol)
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
            this.percentageSymbol,
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
            this.percentageSymbol == other.percentageSymbol &&
            this.permillSymbol == other.permillSymbol;
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
            .label("infinitySymbol").value(this.infinitySymbol)
            .label("monetaryDecimalSeparator").value(this.monetaryDecimalSeparator)
            .label("nanSymbol").value(this.nanSymbol)
            .label("percentageSymbol").value(this.percentageSymbol)
            .label("permillSymbol").value(this.permillSymbol)
            .build();
    }

    // TreePrintable....................................................................................................

    @Override
    public void printTree(IndentingPrinter printer) {
        printer.println(this.getClass().getSimpleName());

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
                "infinitySymbol",
                this.infinitySymbol,
                printer
            );
            this.printLabelAndValues(
                "monetaryDecimalSeparator",
                this.monetaryDecimalSeparator,
                printer
            );
            this.printLabelAndValues(
                "nanSymbol",
                this.nanSymbol,
                printer
            );
            this.printLabelAndValues(
                "percentageSymbol",
                this.percentageSymbol,
                printer
            );
            this.printLabelAndValues(
                "permillSymbol",
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
