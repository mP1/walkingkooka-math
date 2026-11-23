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

import walkingkooka.collect.list.CsvStringList;
import walkingkooka.collect.list.HasCsvStringList;
import walkingkooka.collect.list.ImmutableListDefaults;
import walkingkooka.collect.list.Lists;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;

/**
 * A {@link List} that holds nullable {@link DecimalNumberSymbols symbols}.
 */
public final class DecimalNumberSymbolsList extends AbstractList<DecimalNumberSymbols>
    implements ImmutableListDefaults<DecimalNumberSymbolsList, DecimalNumberSymbols>,
    HasCsvStringList {

    public final static DecimalNumberSymbolsList EMPTY = new DecimalNumberSymbolsList(Lists.empty());

    // @VisibleForTesting
    DecimalNumberSymbolsList(final List<DecimalNumberSymbols> list) {
        super();
        this.list = list;
    }

    @Override
    public DecimalNumberSymbols get(final int index) {
        return this.list.get(index);
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public DecimalNumberSymbolsList setElements(final Collection<DecimalNumberSymbols> collection) {
        DecimalNumberSymbolsList decimalNumberSymbolsList;

        if (collection instanceof DecimalNumberSymbolsList) {
            decimalNumberSymbolsList = (DecimalNumberSymbolsList) collection;
        } else {
            final List<DecimalNumberSymbols> copy = Lists.array();
            copy.addAll(collection);

            if (copy.equals(this)) {
                decimalNumberSymbolsList = this;
            } else {
                decimalNumberSymbolsList = copy.isEmpty() ?
                    EMPTY :
                    new DecimalNumberSymbolsList(copy);
            }
        }

        return decimalNumberSymbolsList;
    }

    @Override
    public void elementCheck(final DecimalNumberSymbols decimalNumberSymbols) {
        // null is allowed
    }

    private final List<DecimalNumberSymbols> list;

    // HasCsvStringList.................................................................................................

    @Override
    public CsvStringList csvStringList() {
        return CsvStringList.makeCsvStringList(
            this,
            (final DecimalNumberSymbols decimalNumberSymbols) -> decimalNumberSymbols.csvStringList()
                .text()
        ).setAllLineBreaks();
    }
}
