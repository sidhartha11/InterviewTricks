package com.georgecurington.functionalstudymod.concurrent.trieber;

import java.util.concurrent.atomic.AtomicReference;

import com.georgecurington.functionalstudymod.concurrent.threads.ThreadSafe;

/**
 * <pre>
 * This code was taken from JDK source to demonstrate the use of a thread safe
 * stack that uses CAS to prevent multiple threads from causing issues when
 * processing the stack.
 * Note the use of AtomicReference<Node<E>> top; Used to always point to 
 * the top of the stack. AtomicReference items can be updated atomically.
 * </pre>
 * @since Dec 25 2017
 * @version 1.0
 * 
 * @see https://en.wikipedia.org/wiki/Treiber_Stack
 * @param <E>
 */
@ThreadSafe
public class TrieberStack <E> {
    AtomicReference<Node<E>> top = new AtomicReference<Node<E>>();

    public void push(E item) {
        Node<E> newHead = new Node<E>(item);
        Node<E> oldHead;
        do {
            oldHead = top.get();
            newHead.next = oldHead;
        } while (!top.compareAndSet(oldHead, newHead));
    }

    public E pop() {
        Node<E> oldHead;
        Node<E> newHead;
        do {
            oldHead = top.get();
            if (oldHead == null)
                return null;
            newHead = oldHead.next;
        } while (!top.compareAndSet(oldHead, newHead));
        return oldHead.item;
    }

    /**
     * <pre>
     * Generic Node class that is used as an element in the 
     * ThreadSafe Treiber Stack implementation. This is an immutable
     * class that can only be constructed via its Constructor. Note
     * that the E item is final. The Node<E> next field is used to point down
     * into the stack.
     * </pre>
     *
     * @param <E>
     */
    private static class Node <E> {
        public final E item;
        public Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }
}