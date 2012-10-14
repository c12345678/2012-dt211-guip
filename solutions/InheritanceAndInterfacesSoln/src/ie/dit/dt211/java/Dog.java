package ie.dit.dt211.java;

public class Dog extends Animal implements Nameable {
	
	String name;
	
	Dog() {
		name = new String();
	}

	@Override
	public String sayHello() {
		return "Woof";
	}

	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean hasBeenNamed() {
		return name != null;
	}

}
