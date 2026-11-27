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
import walkingkooka.collect.list.CsvStringList;
import walkingkooka.collect.list.HasCsvStringListTesting;
import walkingkooka.collect.list.HasTsvStringListTesting;
import walkingkooka.collect.list.ImmutableListTesting;
import walkingkooka.collect.list.Lists;
import walkingkooka.collect.list.TsvStringList;
import walkingkooka.text.HasMultiLineTextTesting;

import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class DecimalNumberSymbolsListTest implements ImmutableListTesting<DecimalNumberSymbolsList, DecimalNumberSymbols>,
    HasDecimalNumberSymbolsTesting,
    HasCsvStringListTesting,
    HasMultiLineTextTesting,
    HasTsvStringListTesting {

    // fromCsvStringList................................................................................................

    @Test
    public void testFromCsvStringListWithNullFails() {
        assertThrows(
            NullPointerException.class,
            () -> DecimalNumberSymbolsList.fromCsvStringList(null)
        );
    }

    @Test
    public void testFromCsvStringListWithEmpty() {
        this.fromCsvStringListAndCheck(
            CsvStringList.EMPTY,
            DecimalNumberSymbolsList.EMPTY
        );
    }

    @Test
    public void testFromCsvStringListWithNotEmpty() {
        final DecimalNumberSymbolsList list = DecimalNumberSymbolsList.EMPTY.concat(DECIMAL_NUMBER_SYMBOLS)
            .concat(DIFFERENT_DECIMAL_NUMBER_SYMBOLS);

        this.fromCsvStringListAndCheck(
            list.csvStringList(),
            list
        );
    }

    @Test
    public void testFromCsvStringListWithNotEmptyListIncludesNull() {
        final DecimalNumberSymbolsList list = DecimalNumberSymbolsList.EMPTY.concat(DECIMAL_NUMBER_SYMBOLS)
            .concat(null)
            .concat(DIFFERENT_DECIMAL_NUMBER_SYMBOLS);

        this.fromCsvStringListAndCheck(
            list.csvStringList(),
            list
        );
    }

    private void fromCsvStringListAndCheck(final CsvStringList csvStringList,
                                           final DecimalNumberSymbolsList expected) {
        this.checkEquals(
            expected,
            DecimalNumberSymbolsList.fromCsvStringList(csvStringList)
        );
    }

    // fromTsvStringList................................................................................................

    @Test
    public void testFromTsvStringListWithNullFails() {
        assertThrows(
            NullPointerException.class,
            () -> DecimalNumberSymbolsList.fromTsvStringList(null)
        );
    }

    @Test
    public void testFromTsvStringListWithEmpty() {
        this.fromTsvStringListAndCheck(
            TsvStringList.EMPTY,
            DecimalNumberSymbolsList.EMPTY
        );
    }

    @Test
    public void testFromTsvStringListWithNotEmpty() {
        final DecimalNumberSymbolsList list = DecimalNumberSymbolsList.EMPTY.concat(DECIMAL_NUMBER_SYMBOLS)
            .concat(DIFFERENT_DECIMAL_NUMBER_SYMBOLS);

        this.fromTsvStringListAndCheck(
            list.tsvStringList(),
            list
        );
    }

    private void fromTsvStringListAndCheck(final TsvStringList tsvStringList,
                                           final DecimalNumberSymbolsList expected) {
        this.checkEquals(
            expected,
            DecimalNumberSymbolsList.fromTsvStringList(tsvStringList)
        );
    }

    // setElements......................................................................................................

    @Test
    public void testSetElementsWithDifferent() {
        this.setElementsAndCheck(
            new DecimalNumberSymbolsList(
                Lists.of(DECIMAL_NUMBER_SYMBOLS)
            ),
            Lists.of(DIFFERENT_DECIMAL_NUMBER_SYMBOLS),
            new DecimalNumberSymbolsList(
                Lists.of(DIFFERENT_DECIMAL_NUMBER_SYMBOLS)
            )
        );
    }

    @Test
    public void testSetElementsWithDifferentEmpty() {
        this.setElementsAndCheck(
            new DecimalNumberSymbolsList(
                Lists.of(DECIMAL_NUMBER_SYMBOLS)
            ),
            Lists.empty(),
            DecimalNumberSymbolsList.EMPTY
        );
    }

    @Override
    public DecimalNumberSymbolsList createList() {
        return DecimalNumberSymbolsList.EMPTY;
    }

    // subList..........................................................................................................

    private final static DecimalNumberSymbols DECIMAL_NUMBER_SYMBOLS1 = DECIMAL_NUMBER_SYMBOLS;
    private final static DecimalNumberSymbols DECIMAL_NUMBER_SYMBOLS2 = DIFFERENT_DECIMAL_NUMBER_SYMBOLS;
    private final static DecimalNumberSymbols DECIMAL_NUMBER_SYMBOLS3 = DecimalNumberSymbols.fromDecimalFormatSymbols(
        '+',
        new DecimalFormatSymbols(
            Locale.forLanguageTag("en-CA")
        )
    );
    private final static DecimalNumberSymbols DECIMAL_NUMBER_SYMBOLS4 = DecimalNumberSymbols.fromDecimalFormatSymbols(
        '+',
        new DecimalFormatSymbols(
            Locale.forLanguageTag("en-UK")
        )
    );
    private final static DecimalNumberSymbols DECIMAL_NUMBER_SYMBOLS5 = DecimalNumberSymbols.fromDecimalFormatSymbols(
        '+',
        new DecimalFormatSymbols(
            Locale.forLanguageTag("en-US")
        )
    );

    private final static List<DecimalNumberSymbols> ELEMENTS = Lists.of(
        DECIMAL_NUMBER_SYMBOLS1,
        DECIMAL_NUMBER_SYMBOLS2,
        DECIMAL_NUMBER_SYMBOLS3,
        DECIMAL_NUMBER_SYMBOLS4,
        DECIMAL_NUMBER_SYMBOLS5
    );

    @Test
    public void testSubList() {
        this.subListAndCheck(
            new DecimalNumberSymbolsList(ELEMENTS),
            0,
            2,
            new DecimalNumberSymbolsList(
                Lists.of(
                    DECIMAL_NUMBER_SYMBOLS1,
                    DECIMAL_NUMBER_SYMBOLS2
                )
            )
        );
    }

    @Test
    public void testSubList2() {
        this.subListAndCheck(
            new DecimalNumberSymbolsList(ELEMENTS),
            2,
            4,
            new DecimalNumberSymbolsList(
                Lists.of(
                    DECIMAL_NUMBER_SYMBOLS3,
                    DECIMAL_NUMBER_SYMBOLS4
                )
            )
        );
    }

    @Test
    public void testSubListSubList() {
        this.subListAndCheck(
            new DecimalNumberSymbolsList(ELEMENTS)
                .subList(
                    1,
                    4
                ), // 2, 3, 4
            1,
            3,
            new DecimalNumberSymbolsList(
                Lists.of(
                    DECIMAL_NUMBER_SYMBOLS3,
                    DECIMAL_NUMBER_SYMBOLS4
                )
            )
        );
    }
    
    // CsvStringList....................................................................................................

    @Test
    public void testCsvStringListWithEmpty() {
        this.csvStringListAndCheck(
            DecimalNumberSymbolsList.EMPTY,
            ""
        );
    }

    @Test
    public void testCsvStringListWithNotEmpty() {
        this.csvStringListAndCheck(
            DecimalNumberSymbolsList.EMPTY.concat(DECIMAL_NUMBER_SYMBOLS)
                .concat(DIFFERENT_DECIMAL_NUMBER_SYMBOLS),
            "\"-,+,0,$,.,e,\"\",\"\",∞,.,NaN,%,‰\"\n" +
                "\"-,+,0,$,.,E,\"\",\"\",∞,.,NaN,%,‰\"\n"
        );
    }

    @Test
    public void testCsvStringListWithNotEmptyListIncludesNull() {
        this.csvStringListAndCheck(
            DecimalNumberSymbolsList.EMPTY.concat(DECIMAL_NUMBER_SYMBOLS)
                .concat(null)
                .concat(DIFFERENT_DECIMAL_NUMBER_SYMBOLS),
            "\"-,+,0,$,.,e,\"\",\"\",∞,.,NaN,%,‰\"\n" +
                "\n" +
                "\"-,+,0,$,.,E,\"\",\"\",∞,.,NaN,%,‰\"\n"
        );
    }

    @Test
    public void testCsvStringListAndMultiLineText() {
        this.multiLineTextAndCheck(
            DecimalNumberSymbolsList.EMPTY.concat(DECIMAL_NUMBER_SYMBOLS)
                .concat(DIFFERENT_DECIMAL_NUMBER_SYMBOLS)
                .csvStringList(),
            TEXT_CONTEXT,
            "-,+,0,$,.,e,\",\",∞,.,NaN,%,‰\n" +
                "-,+,0,$,.,E,\",\",∞,.,NaN,%,‰\n"
        );
    }

    // TsvStringList....................................................................................................

    @Test
    public void testTsvStringListWithEmpty() {
        this.tsvStringListAndCheck(
            DecimalNumberSymbolsList.EMPTY,
            ""
        );
    }

    @Test
    public void testTsvStringListWithNotEmpty() {
        this.tsvStringListAndCheck(
            DecimalNumberSymbolsList.EMPTY.concat(DECIMAL_NUMBER_SYMBOLS)
                .concat(DIFFERENT_DECIMAL_NUMBER_SYMBOLS),
            "\"-\t+\t0\t$\t.\te\t,\t∞\t.\tNaN\t%\t‰\"\n" +
                "\"-\t+\t0\t$\t.\tE\t,\t∞\t.\tNaN\t%\t‰\"\n"
        );
    }

    @Test
    public void testTsvStringListAndMultiLineText() {
        this.multiLineTextAndCheck(
            DecimalNumberSymbolsList.EMPTY.concat(DECIMAL_NUMBER_SYMBOLS)
                .concat(DIFFERENT_DECIMAL_NUMBER_SYMBOLS)
                .tsvStringList(),
            TEXT_CONTEXT,
            "-\t+\t0\t$\t.\te\t,\t∞\t.\tNaN\t%\t‰\n" +
                "-\t+\t0\t$\t.\tE\t,\t∞\t.\tNaN\t%\t‰\n"
        );
    }

    // class............................................................................................................

    @Override
    public Class<DecimalNumberSymbolsList> type() {
        return DecimalNumberSymbolsList.class;
    }
}
