package utilities;
import static org.junit.Assert.*;
import org.junit.*;


public class BSTreeNodeTests<E> 
{
	private BSTreeNode bst;

	@Before
	public void setUp() throws Exception 
	{
		bst = new BSTreeNode("Calgary");
	}

	@After
	public void tearDown() throws Exception
	{
		bst = null;
	}

	@Test
	public void testGetNode()
	{
		assertEquals("Calgary", bst.getNode());
	}

	@Test
	public void testSetNode() 
	{
		bst.setNode("Toronto");
		assertEquals("Toronto", bst.getNode());
	}

	@Test
	public void testGetLeft() 
	{
		assertNull(bst.getLeft());
		bst.setLeft(new BSTreeNode("Burnaby"));
		assertEquals("Burnaby", bst.getLeft().getNode());
	}

	@Test
	public void testGetRight() 
	{
		assertNull(bst.getLeft());
		bst.setRight(new BSTreeNode("Quebec"));
		assertEquals("Quebec", bst.getRight().getNode());
	}

	@Test
	public void testSetLeft() {
		assertNull(bst.getLeft());
		bst.setLeft(new BSTreeNode("Burnaby"));
		assertEquals("Burnaby", bst.getLeft().getNode());
	}

	@Test
	public void testSetRight() {
		assertNull(bst.getLeft());
		bst.setRight(new BSTreeNode("Quebec"));
		assertEquals("Quebec", bst.getRight().getNode());
	}

}
