/*
 * Copyright 2025 Miroslav Pokorny (github.com/mP1)
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
import walkingkooka.collect.list.ImmutableListTesting;
import walkingkooka.collect.list.ListTesting2;
import walkingkooka.collect.list.Lists;
import walkingkooka.reflect.ClassTesting;
import walkingkooka.reflect.JavaVisibility;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberListTest implements ListTesting2<NumberList, Number>,
    ClassTesting<NumberList>,
    ImmutableListTesting<NumberList, Number> {

    private final static Number NUMBER1 = 111;

    private final static Number NUMBER2 = 222;

    @Test
    public void testWithNullFails() {
        assertThrows(
            NullPointerException.class,
            () -> NumberList.with(null)
        );
    }

    @Test
    public void testWithDoesntDoubleWrap() {
        final NumberList list = this.createList();
        assertSame(
            list,
            NumberList.with(list)
        );
    }

    @Test
    public void testWithEmpty() {
        assertSame(
            NumberList.EMPTY,
            NumberList.with(
                Lists.empty()
            )
        );
    }

    // list.............................................................................................................

    @Test
    public void testGet() {
        this.getAndCheck(
            this.createList(),
            0, // index
            NUMBER1 // expected
        );
    }

    @Test
    public void testGet2() {
        this.getAndCheck(
            this.createList(),
            1, // index
            NUMBER2 // expected
        );
    }

    @Test
    public void testSetFails() {
        this.setFails(
            this.createList(),
            0, // index
            NUMBER1 // expected
        );
    }

    @Test
    public void testRemoveIndexFails() {
        final NumberList list = this.createList();

        this.removeIndexFails(
            list,
            0
        );
    }

    @Test
    public void testRemoveElementFails() {
        final NumberList list = this.createList();

        this.removeFails(
            list,
            list.get(0)
        );
    }

    @Test
    public void testSetElementsIncludesNullFails() {
        final NullPointerException thrown = assertThrows(
            NullPointerException.class,
            () -> this.createList()
                .setElements(
                    Lists.of(
                        NUMBER1,
                        null
                    )
                )
        );
        this.checkEquals(
            "includes null Number",
            thrown.getMessage()
        );
    }

    @Override
    public NumberList createList() {
        return NumberList.with(
            Lists.of(
                NUMBER1,
                NUMBER2
            )
        );
    }

    // class............................................................................................................

    @Override
    public Class<NumberList> type() {
        return NumberList.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }
}
