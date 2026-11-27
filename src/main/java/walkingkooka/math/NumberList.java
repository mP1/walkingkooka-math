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

import walkingkooka.collect.list.ImmutableListDefaults;
import walkingkooka.collect.list.Lists;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;

/**
 * An immutable list of {@link Number} that allows null elements.
 */
public final class NumberList extends AbstractList<Number>
    implements ImmutableListDefaults<NumberList, Number> {

    /**
     * An empty {@link NumberList}.
     */
    public final static NumberList EMPTY = new NumberList(
        Lists.empty()
    );

    // @VisibleForTesting
    NumberList(final List<Number> numbers) {
        this.numbers = numbers;
    }

    @Override
    public Number get(int index) {
        return this.numbers.get(index);
    }

    @Override
    public int size() {
        return this.numbers.size();
    }

    private final List<Number> numbers;

    @Override
    public void elementCheck(final Number number) {
        // nulls are allowed.
    }

    @Override
    public NumberList setElements(final Collection<Number> elements) {
        return (NumberList)
            this.setElementsMaybeCreate(
                elements,
                (final Collection<Number> collection) -> collection instanceof NumberList,
                this::elementCheck,
                this::replaceNumbers
            );
    }

    @Override
    public NumberList subList(final int from,
                              final int to) {
        return 0 == from && this.size() == to ?
            this :
            this.replaceNumbers(
                this.numbers.subList(
                    from,
                    to
                )
            );
    }

    private NumberList replaceNumbers(final List<Number> numbers) {
        return numbers.isEmpty() ?
            EMPTY :
            new NumberList(numbers);
    }
}
