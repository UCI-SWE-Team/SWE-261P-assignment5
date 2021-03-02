package org.apache.commons.lang3.text;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class StrBuilderMockingTest {

    @Test
    public void testAppendBehaviour(){
        StrBuilder strBuilder = mock(StrBuilder.class);

        strBuilder.append('a');
        strBuilder.append('b');
        strBuilder.append(' ');
        strBuilder.append('?');

        verify(strBuilder,times(1)).append('?');
        verify(strBuilder,times(1)).append(' ');
        verify(strBuilder,times(1)).append('b');
        verify(strBuilder,times(1)).append('a');

        verifyNoMoreInteractions(strBuilder);
    }

    @Test
    public void testAppendTimes(){
        StrBuilder strBuilder = mock(StrBuilder.class);

        strBuilder.append('a');
        strBuilder.append('b');
        strBuilder.append(' ');
        strBuilder.append('?');

        verify(strBuilder,times(4)).append(anyChar());
    }

    @Test
    public void testAppendOrder(){

        StrBuilder strBuilder = mock(StrBuilder.class);
        strBuilder.append('!');
        strBuilder.append('@');
        strBuilder.append('#');
        strBuilder.append('$');

        InOrder inOrder = inOrder(strBuilder);
        inOrder.verify(strBuilder).append('!');
        inOrder.verify(strBuilder).append('@');
        inOrder.verify(strBuilder).append('#');
        inOrder.verify(strBuilder).append('$');

    }

    @Test
    public void testSizeArgumentCaptor(){
        StrBuilder strBuilder = mock(StrBuilder.class);

        when(strBuilder.size()).thenReturn(100);
        strBuilder.append("abc");

        assertEquals(strBuilder.size(),100);

        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);

        verify(strBuilder,times(1)).append(stringCaptor.capture());
        assertEquals(stringCaptor.getValue(),"abc");

    }

    @Test
    public void testSpy(){
        StrBuilder strBuilder = new StrBuilder();
        StrBuilder spy = spy(strBuilder);

        spy.append(1);
        spy.size();
        verify(spy).size();
    }

    @Test
    public void testSize(){
        StrBuilder strBuilder = mock(StrBuilder.class);
        when(strBuilder.size()).thenReturn(3);
        assertEquals(strBuilder.size(),3);

    }
}
