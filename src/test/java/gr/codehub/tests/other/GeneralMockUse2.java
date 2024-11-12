package gr.codehub.tests.other;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

// https://javadoc.io/static/org.mockito/mockito-core/4.4.0/org/mockito/Mockito.html
public class GeneralMockUse2 {

    @Test
    public  void main() {


        //mock creation
        List mockedList = mock(List.class);

        //using mock object
        mockedList.add("one");
        mockedList.clear();

        //verification
        verify(mockedList).add("one");
        verify(mockedList).clear();

//    Once created, a mock will remember all interactions. Then you can selectively verify whatever interactions you are interested in.

//        2. How about some stubbing?

        //You can mock concrete classes, not just interfaces
        mockedList = mock(LinkedList.class);

        //stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        //following prints "first"
        System.out.println(mockedList.get(0));

        //following throws runtime exception
        System.out.println(mockedList.get(1));

        //following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));

        //Although it is possible to verify a stubbed invocation, usually it's just redundant
        //If your code cares what get(0) returns, then something else breaks (often even before verify() gets executed).
        //If your code doesn't care what get(0) returns, then it should not be stubbed.
        verify(mockedList).get(0);

//        By
//        default,for all methods that return a value, a mock will return either null, a primitive / primitive
//        wrapper value, or an empty collection, as appropriate.For example 0 for an int/Integer and false for a boolean/
//        Boolean.
//                Stubbing can be overridden:
//        for example common stubbing can go to fixture setup but the test methods can override it.Please note
//        that overridding stubbing is a potential code smell that points out too much stubbing
//        Once stubbed, the method will always return a stubbed value, regardless of how many times it is called.
//                Last stubbing is more important -when you stubbed the same method with the same arguments many
//        times.Other words:the order of stubbing matters but it is only meaningful rarely, e.g.when stubbing exactly
//        the same method calls or sometimes when argument matchers are used, etc.
//        3. Argument matchers
//        Mockito verifies argument values in natural java style:by using an equals () method.Sometimes, when
//        extra flexibility is required then you might use argument matchers:

        //stubbing using built-in anyInt() argument matcher
        when(mockedList.get(anyInt())).thenReturn("element");



        //following prints "element"
        System.out.println(mockedList.get(999));

        //you can also verify using an argument matcher
        ////////// dont! verify(mockedList).get(anyInt());

        mockedList.add(22); // this is to make run the ones below

        //argument matchers can also be written as Java 8 Lambdas
        verify(mockedList).add(argThat(someString -> someString != null));


//        Argument matchers allow flexible verification or stubbing.Click here or here to see more built -in matchers and
//        examples of custom argument matchers / hamcrest matchers.
//
//                For information solely on custom argument matchers check out javadoc for ArgumentMatcher class.
//
//        Be reasonable with using complicated argument matching.The natural matching style using equals ()
//        with occasional anyX() matchers tend to give clean and simple tests.Sometimes it
//        's just better to refactor the code to allow equals() matching or even implement equals() method to help out with testing.
//
//        Also, read section 15 or javadoc for ArgumentCaptor class.ArgumentCaptor is a special implementation of
//        an argument matcher that captures argument values for further assertions.
//
//        Warning on argument matchers:
//
//        If you are using argument matchers, all arguments have to be provided by matchers.
//
//                The following example shows verification but the same applies to stubbing:

//        Matcher methods like any (), eq() do not return matchers.Internally,they record a matcher on a stack and
//        return a dummy value (usually null).This implementation is due to static type safety imposed by the java
//        compiler.The consequence is that you cannot use any (), eq() methods outside of verified/stubbed method.
//
//        4. Verifying exact number of invocations / at least x /never

        //using mock
        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        //following two verifications work exactly the same - times(1) is used by default
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        //exact number of invocations verification
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        //verification using never(). never() is an alias to times(0)
        verify(mockedList, never()).add("never happened");

        //verification using atLeast()/atMost()
        verify(mockedList, atMostOnce()).add("once");
        verify(mockedList, atLeastOnce()).add("three times");
        verify(mockedList, atLeast(2)).add("three times");
        verify(mockedList, atMost(5)).add("three times");


//        times(1) is the
//        default.Therefore using times(1) explicitly can be omitted.
//
//        5. Stubbing void methods with exceptions
//
//        doThrow(new RuntimeException()).when(mockedList).clear();

        //following throws RuntimeException:
        mockedList.clear();

//        Read more about doThrow () | doAnswer() family of methods in section 12.
//        6. Verification in order

        // A. Single mock whose methods must be invoked in a particular order
        List singleMock = mock(List.class);

        //using a single mock
        singleMock.add("was added first");
        singleMock.add("was added second");

        //create an inOrder verifier for a single mock
        InOrder inOrder = inOrder(singleMock);

        //following will make sure that add is first called with "was added first", then with "was added second"
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

        // B. Multiple mocks that must be used in a particular order
//        List firstMock = mock(List.class);
//        List secondMock = mock(List.class);
//
//        //using mocks
//        firstMock.add("was called first");
//        secondMock.add("was called second");
//
//        //create inOrder object passing any mocks that need to be verified in order
//        //InOrder inOrder = inOrder(firstMock, secondMock);
//
//        //following will make sure that firstMock was called before secondMock
//        inOrder.verify(firstMock).add("was called first");
//        inOrder.verify(secondMock).add("was called second");

        // Oh, and A + B can be mixed together at will

//        Verification in order is flexible - you don
//        't have to verify all interactions one-by-one but only those that you are interested in testing in order.
//        Also, you can create an InOrder object passing only the mocks that are relevant for in - order verification.
//
//        7. Making sure interaction(s) never happened on mock

        //using mocks - only mockOne is interacted
        //mockOne.add("one");

        //ordinary verification
        //verify(mockOne).add("one");

        //verify that method was never called on a mock
        // verify(mockOne, never()).add("two");


        //  8. Finding redundant invocations

        //using mocks
        mockedList.add("one");
        mockedList.add("two");

        verify(mockedList).add("one");

        //following verification will fail
        verifyNoMoreInteractions(mockedList);

//        A word of warning:Some users who did a lot of classic, expect -run - verify mocking tend to use
//        verifyNoMoreInteractions() very often, even in every test method.verifyNoMoreInteractions() is not
//        recommended to use in every test method.verifyNoMoreInteractions() is a handy assertion from the
//        interaction testing toolkit.Use it only when it
//        's relevant. Abusing it leads to overspecified, less maintainable tests.
//        See also never() - it is more explicit and communicates the intent well.
//
//        9. Shorthand for mocks creation -@Mock
//                annotation
//        Minimizes repetitive mock creation code.
//                Makes the test class more readable.
//        Makes the verification error easier to read because the field name is used to identify the mock.

//                 13. Spying on real objects
//                You can create spies of real objects.When you use the spy then the real methods are
//                called(unless a method was stubbed).
//                        Real spies should be used carefully and occasionally,for example when dealing with legacy code.
//
//                Spying on real objects can be associated with "partial mocking" concept.Before the release 1.8, Mockito
//                spies were not real partial mocks.The reason was we thought partial mock is a code smell.At some
//                point we found legitimate use cases for partial mocks (3 rd party interfaces, interim refactoring of
//                legacy code).


        List list = new LinkedList();
        List spy = spy(list);

        //optionally, you can stub out some methods:
        when(spy.size()).thenReturn(100);

        //using the spy calls *real* methods
        spy.add("one");
        spy.add("two");

        //prints "one" - the first element of a list
        System.out.println(spy.get(0));

        //size() method was stubbed - 100 is printed
        System.out.println(spy.size());

        //optionally, you can verify
        verify(spy).add("one");
        verify(spy).add("two");

    }
}