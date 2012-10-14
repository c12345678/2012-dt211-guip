package ie.dit.dt211.java;

import static org.junit.Assert.*;

import org.junit.Test;

public class AnimalTest {

	@Test public void createsACat() {
		new Cat();
	}
	
	@Test public void catSaysHello() {
		// Cat implements a feline-style hello
		assertEquals("Miaow", new Cat().sayHello());
	}
	
	@Test public void createsADog() {
		// Need a Dog class
		new Dog();
	}
		
	@Test public void dogSaysHello() {
		// Dog implements a canine-style hello
		assertEquals("Woof", new Dog().sayHello());
	}
	
	@Test public void nameTheCat() {
		// Set a name and check we can get the same one back
		Nameable cat = new Cat();
		cat.setName("Mimi");
		assertEquals("Mimi", cat.getName());
	}
	
	@Test public void catHasAName() {
		// Set a name and see if it has been named
		assertEquals(true, new Cat().hasBeenNamed());
	}
	
	@Test public void dogsNameIsFido() {
		// See if we can get back the specific name given to a dog
		Nameable dog = new Dog();
		dog.setName("Fido");
		assertEquals("Fido", dog.getName());
	}
}
