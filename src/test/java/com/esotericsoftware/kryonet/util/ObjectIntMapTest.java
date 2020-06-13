package com.esotericsoftware.kryonet.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ObjectIntMapTest {

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor() {
		new ObjectIntMap(-1, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor2() {
		new ObjectIntMap((1 << 30) + 1, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor3() {
		new ObjectIntMap(15, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor4() {
		new ObjectIntMap(15, -1);
	}

	@Test
	public void testConstructor5() {
		ObjectIntMap tmp = new ObjectIntMap(15);
		tmp.put("S", 3);
		tmp.put("A", 11);
		tmp.put("S", 2);

		ObjectIntMap map2 = new ObjectIntMap<>(tmp);

		assertArrayEquals(tmp.keyTable, map2.keyTable);
		assertEquals(tmp.size, map2.size);
		assertEquals(tmp.threshold, map2.threshold);
		assertArrayEquals(tmp.valueTable, map2.valueTable);
	}

	@Test
	public void testStuff() {
		ObjectIntMap tmp = new ObjectIntMap(4);

		// toString() - 1
		assertEquals("{}", tmp.toString());

		// put(x, y)
		tmp.put("S", 3);
		tmp.put("A", 11);

		assertEquals(3, tmp.get("S", -1));
		assertEquals(2, tmp.size);

		tmp.put("S", 2);
		tmp.put("K", 5);

		assertEquals(2, tmp.get("S", -1));

		// remove(x, y)
		assertEquals(5, tmp.remove("K", -1));

		// toString() - 2
		assertEquals("{S=2, A=11}", tmp.toString());

		// findKex(y)
		assertEquals("A", tmp.findKey(11));

		// containsKey/Value(x)
		assertTrue(tmp.containsKey("S"));
		assertFalse(tmp.containsKey("G"));
		assertTrue(tmp.containsValue(11));
		assertFalse(tmp.containsValue(26));
	}

}
