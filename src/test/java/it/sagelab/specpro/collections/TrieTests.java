package it.sagelab.specpro.collections;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TrieTests {

    @Test
    public void testInsertStringKeys() {

        List<String> p1 = Arrays.asList("a", "bc", "def");
        List<String> p2 = Arrays.asList("a", "bc", "x");
        List<String> p3 = Arrays.asList("a", "bc");

        Trie<String> trie = new Trie<>();
        trie.insert(p1);
        trie.insert(p2);
        trie.insert(p3);

        assertFalse(trie.root.isLeaf);
        assertEquals(1, trie.root.children.size());
        assertTrue(trie.root.contains("a"));

        TrieNode<String> t1 = trie.root.get("a");
        assertFalse(t1.isLeaf);
        assertEquals(1, t1.children.size());
        assertTrue(t1.contains("bc"));

        TrieNode<String> t2 = t1.get("bc");
        assertTrue(t2.isLeaf);
        assertEquals(2, t2.children.size());
        assertTrue(t2.contains("def"));
        assertTrue(t2.contains("x"));

        assertTrue(t2.get("def").isLeaf);
        assertEquals(0, t2.get("def").children.size());

        assertTrue(t2.get("x").isLeaf);
        assertEquals(0, t2.get("x").children.size());
    }


    @Test
    public void testInsertIntegerKeys() {

        List<Integer> p1 = Arrays.asList(1, 2, 3);
        List<Integer> p2 = Arrays.asList(3, 4, 5);
        List<Integer> p3 = Arrays.asList(1, 2, 10);
        List<Integer> p4 = Arrays.asList(3);

        Trie<Integer> trie = new Trie<>();
        trie.insert(p1);
        trie.insert(p2);
        trie.insert(p3);
        trie.insert(p4);

        assertFalse(trie.root.isLeaf);
        assertEquals(2, trie.root.children.size());
        assertTrue(trie.root.contains(1));
        assertTrue(trie.root.contains(3));

        TrieNode<Integer> t1 = trie.root.get(1);
        assertFalse(t1.isLeaf);
        assertEquals(1, t1.children.size());
        assertTrue(t1.contains(2));

        TrieNode<Integer> t2 = trie.root.get(3);
        assertTrue(t2.isLeaf);
        assertEquals(1, t2.children.size());
        assertTrue(t2.contains(4));

        TrieNode<Integer> t3 = t1.get(2);
        assertFalse(t3.isLeaf);
        assertEquals(2, t3.children.size());
        assertTrue(t3.contains(3));
        assertTrue(t3.contains(10));

        assertTrue(t3.get(3).isLeaf);
        assertTrue(t3.get(3).children.isEmpty());
        assertTrue(t3.get(10).isLeaf);
        assertTrue(t3.get(10).children.isEmpty());

        TrieNode<Integer> t4 = t2.get(4);
        assertFalse(t4.isLeaf);
        assertEquals(1, t4.children.size());
        assertTrue(t4.contains(5));
        assertTrue(t4.get(5).isLeaf);
        assertTrue(t4.get(5).children.isEmpty());
    }


    @Test
    public void testContains() {
        List<String> p1 = Arrays.asList("a", "b", "def");
        List<String> p2 = Arrays.asList("a", "b", "x");
        List<String> p3 = Arrays.asList("a", "c", "e");

        Trie<String> trie = new Trie<>();
        trie.insert(p1);
        trie.insert(p2);
        trie.insert(p3);

        assertTrue(trie.contains(p1));
        assertTrue(trie.contains(p2));
        assertTrue(trie.contains(p3));
        assertTrue(trie.contains(Arrays.asList("a")));
        assertTrue(trie.contains(Arrays.asList("a", "b")));

        assertFalse(trie.contains(Arrays.asList("c")));
        assertFalse(trie.contains(Arrays.asList("b")));
        assertFalse(trie.contains(Arrays.asList("a", "x")));
        assertFalse(trie.contains(Arrays.asList("a", "c", "b")));
    }


    @Test
    public void testContainsSequence() {
        List<String> p1 = Arrays.asList("a", "b", "def");
        List<String> p2 = Arrays.asList("a", "b", "x");
        List<String> p3 = Arrays.asList("a", "c", "e");

        Trie<String> trie = new Trie<>();
        trie.insert(p1);
        trie.insert(p2);
        trie.insert(p3);

        assertTrue(trie.containsSequence(p1));
        assertTrue(trie.containsSequence(p2));
        assertTrue(trie.containsSequence(p3));

        assertFalse(trie.containsSequence(Arrays.asList("a")));
        assertFalse(trie.containsSequence(Arrays.asList("a", "b")));
        assertFalse(trie.containsSequence(Arrays.asList("c")));
        assertFalse(trie.containsSequence(Arrays.asList("b")));
        assertFalse(trie.containsSequence(Arrays.asList("a", "x")));
        assertFalse(trie.containsSequence(Arrays.asList("a", "c", "b")));
    }


    @Test
    public void testIterator() {
        List<String> p1 = Arrays.asList("a", "bc", "def");
        List<String> p2 = Arrays.asList("a", "bc", "x");
        List<String> p3 = Arrays.asList("a", "bc");

        Trie<String> trie = new Trie<>();
        trie.insert(p1);
        trie.insert(p2);
        trie.insert(p3);

        Iterator<List<String>> iterator = trie.iterator();
        assertTrue(iterator.hasNext());
        List<String> r1 = iterator.next();
        assertEquals(p3, r1);
        assertTrue(iterator.hasNext());
        List<String> r2 = iterator.next();
        assertTrue(iterator.hasNext());
        List<String> r3 = iterator.next();
        assertFalse(iterator.hasNext());

        assertNotEquals(r2, r3);
        assertTrue(r2.equals(p1) || r2.equals(p2));
        assertTrue(r3.equals(p1) || r3.equals(p2));
    }

    @Test
    public void testAndJoinWithSameKey() {

        Trie<String> trie1= new Trie<>();
        trie1.insert(Arrays.asList("a", "b", "c"));
        trie1.insert(Arrays.asList("a", "b", "e"));
        trie1.insert(Arrays.asList("a", "d"));
        trie1.insert(Arrays.asList("b", "c"));
        Trie<String> trie2 = new Trie<>();
        trie2.insert(Arrays.asList("a", "b", "c"));
        trie2.insert(Arrays.asList("a", "d", "e"));
        trie2.insert(Arrays.asList("b", "c"));

        // Two nodes are joint only if they have the same key
        Trie<String> jointTrie = trie1.andJoin(trie2, (s1, s2) -> s1.equals(s2) ? s1 : null);

        assertTrue(jointTrie.containsSequence(Arrays.asList("a", "b", "c")));
        assertTrue(jointTrie.containsSequence(Arrays.asList("b", "c")));

        assertFalse(jointTrie.containsSequence(Arrays.asList("a", "b", "e")));
        assertFalse(jointTrie.containsSequence(Arrays.asList("a", "d")));
        assertFalse(jointTrie.containsSequence(Arrays.asList("a", "d", "e")));
    }

    @Test
    public void testAndJoinConcatenatingKey() {

        Trie<String> trie1= new Trie<>();
        trie1.insert(Arrays.asList("a", "b", "c"));
        trie1.insert(Arrays.asList("a", "b", "e"));
        trie1.insert(Arrays.asList("a", "d"));
        trie1.insert(Arrays.asList("b", "c"));
        Trie<String> trie2 = new Trie<>();
        trie2.insert(Arrays.asList("a", "b", "c"));
        trie2.insert(Arrays.asList("a", "d", "e"));
        trie2.insert(Arrays.asList("b", "c"));

        // The result of two nodes is their key concatenation
        Trie<String> jointTrie = trie1.andJoin(trie2, (s1, s2) -> s1 + s2);

        assertTrue(jointTrie.containsSequence(Arrays.asList("aa", "bb", "cc")));
        assertTrue(jointTrie.containsSequence(Arrays.asList("aa", "bb", "ec")));
        assertTrue(jointTrie.containsSequence(Arrays.asList("aa", "bd", "ee")));
        assertTrue(jointTrie.containsSequence(Arrays.asList("aa", "bd", "ce")));
        assertTrue(jointTrie.containsSequence(Arrays.asList("ab", "dc")));
        assertTrue(jointTrie.containsSequence(Arrays.asList("bb", "cc")));
    }

    @Test
    public void testInsertWithComparator() {
        List<String> str1 = Arrays.asList("A", "B", "C");
        List<String> str2 = Arrays.asList("a", "b", "d");
        List<String> str3 = Arrays.asList("a", "B", "D", "f");

        Trie<String> trie = new Trie<>();
        trie.insert(str1, String::compareToIgnoreCase);
        trie.insert(str2, String::compareToIgnoreCase);
        trie.insert(str3, String::compareToIgnoreCase);

        assertTrue(trie.containsSequence(Arrays.asList("A", "B", "C")));
        assertTrue(trie.containsSequence(Arrays.asList("A", "B", "d")));
        assertFalse(trie.containsSequence(Arrays.asList("a", "b", "d")));
        assertTrue(trie.containsSequence(Arrays.asList("A", "B", "d", "f")));
        assertFalse(trie.containsSequence(Arrays.asList("a", "B", "D", "f")));

    }

    @Test
    public void testCancelSequences() {
        List<String> p1 = Arrays.asList("a", "bc", "def");
        List<String> p2 = Arrays.asList("a", "bc", "x");
        List<String> p3 = Arrays.asList("a", "bc");

        Trie<String> trie = new Trie<>();
        trie.insert(p1);
        trie.insert(p2);
        trie.insert(p3);

        assertEquals(3, trie.size());
        trie.remove(p3);
        assertEquals(2, trie.size());
        trie.remove(p2);
        assertEquals(1, trie.size());
        assertTrue(trie.containsSequence(p1));
        assertFalse(trie.containsSequence(p2));
        assertFalse(trie.containsSequence(p3));

        for(List<String> sequence: trie) {
            assertEquals(p1, sequence);
        }
    }

    @Test
    public void testInsertWithDuplicatedSequences() {
        List<String> p1 = Arrays.asList("a", "bc", "def");
        List<String> p2 = Arrays.asList("a", "bc", "x");
        List<String> p3 = Arrays.asList("a", "bc");

        Trie<String> trie = new Trie<>();
        trie.insert(p1);
        trie.insert(p2);
        trie.insert(p3);

        assertEquals(3, trie.size());
        trie.insert(p1);
        assertEquals(3, trie.size());
        trie.insert(p2);
        assertEquals(3, trie.size());
        trie.insert(p3);
        assertEquals(3, trie.size());

        Iterator<List<String>> itr = trie.iterator();
        assertTrue(itr.hasNext());
        assertEquals(p3, itr.next());
        assertTrue(itr.hasNext());
        assertEquals(p1, itr.next());
        assertTrue(itr.hasNext());
        assertEquals(p2, itr.next());
        assertFalse(itr.hasNext());

    }

}
