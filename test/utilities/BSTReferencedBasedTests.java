package utilities;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

import java.util.Iterator;

import org.junit.*;

import exceptions.TreeException;

public class BSTReferencedBasedTests<E> {
	private BSTReferencedBased bst;

	@Before
	public void setUp() throws Exception {
		this.bst = new BSTReferencedBased();
		bst.add("Calgary");
		bst.add("Vancouver");
		bst.add("Toronto");
		bst.add("Quebec");
		bst.add("Burnaby");
	}

	@After
	public void tearDown() throws Exception {
		this.bst = null;
	}

	@Test
	public void testGetRoot() {
		try {
			assertEquals("Calgary", bst.getRoot().getNode());
		} catch (TreeException e) {
			assertFalse(false);
			e.printStackTrace();
		}

		bst.clear();

		try {
			bst.getRoot().getNode();
			assertFalse(false);
		} catch (TreeException e) {
			assumeTrue(true);
		}

	}

	@Test
	public void testGetHeight() {
		assertEquals(4, bst.getHeight());
		bst.clear();
		assertEquals(0, bst.getHeight());
		bst.add("New York");
		assertEquals(1, bst.getHeight());
		bst.add("Washington");
		assertEquals(2, bst.getHeight());
	}

	@Test
	public void testSize() {
		assertEquals(5, bst.size());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(bst.isEmpty());
		bst.clear();
		assertTrue(bst.isEmpty());
	}

	@Test
	public void testClear() {
		bst.clear();
		assertEquals(0, bst.size());
		assertEquals(0, bst.getHeight());

		try {
			assertNull(bst.getRoot());
		} catch (TreeException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testContains() {
		try {
			assertTrue(bst.contains("Calgary"));
			assertTrue(bst.contains("Quebec"));
			assertTrue(bst.contains("Burnaby"));
			assertTrue(bst.contains("Vancouver"));
			assertTrue(bst.contains("Toronto"));
		} catch (TreeException e) {
			assertFalse(false);
			e.printStackTrace();
		}

		try {
			assertFalse(bst.contains("New York"));
		} catch (TreeException e) {
			assertFalse(false);
		}
	}

	@Test
	public void testSearch() {
		try {
			assertEquals(bst.getRoot().getRight(), bst.search("Vancouver"));
			assertEquals(bst.getRoot().getRight().getLeft(), bst.search("Toronto"));
			assertEquals(bst.getRoot().getLeft(), bst.search("Burnaby"));
		} catch (TreeException e) {
			assertFalse(false);
			e.printStackTrace();
		}
	}

	@Test
	public void testAdd() {
		// abcdefghijklmnopqrstuvwxyz
		bst.add("Winnipeg");
		bst.add("Saskatoon");
		bst.add("Abbotsford");

		try {
			assertEquals(bst.getRoot().getLeft().getLeft().getNode(), bst.search("Abbotsford").getNode());
			assertEquals(bst.getRoot().getRight().getLeft().getLeft().getNode(), bst.search("Quebec").getNode());
			assertEquals(bst.getRoot().getRight().getRight().getNode(), bst.search("Winnipeg").getNode());
		} catch (TreeException e) {
			assertFalse(false);
			e.printStackTrace();
		}

		try {
			bst.add(null);
			bst.add("Winnipeg");
			assertFalse(false);
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testInorderIterator() {
		Object[] o = new Object[bst.size()];
		Iterator<E> bstinorderIterator = bst.inorderIterator();

		int i = 0;
		while (bstinorderIterator.hasNext()) {
			o[i] = bstinorderIterator.next();
			i++;
		}

		assertEquals("Burnaby", o[0]);
		assertEquals("Calgary", o[1]);
		assertEquals("Quebec", o[2]);
		assertEquals("Toronto", o[3]);
		assertEquals("Vancouver", o[4]);
	}

	@Test
	public void testPreorderIterator() {
		Object[] o = new Object[bst.size()];
		Iterator<E> bstpreorderIterator = bst.preorderIterator();

		int i = 0;
		while (bstpreorderIterator.hasNext()) {
			o[i] = bstpreorderIterator.next();
			i++;
		}

		assertEquals("Calgary", o[0]);
		assertEquals("Burnaby", o[1]);
		assertEquals("Vancouver", o[2]);
		assertEquals("Toronto", o[3]);
		assertEquals("Quebec", o[4]);
	}

	@Test
	public void testPostorderIterator() {
		Object[] o = new Object[bst.size()];
		Iterator<E> bstpostrderIterator = bst.postorderIterator();

		int i = 0;
		while (bstpostrderIterator.hasNext()) {
			o[i] = bstpostrderIterator.next();
			i++;
		}

		assertEquals("Burnaby", o[0]);
		assertEquals("Quebec", o[1]);
		assertEquals("Toronto", o[2]);
		assertEquals("Vancouver", o[3]);
		assertEquals("Calgary", o[4]);
	}
}
