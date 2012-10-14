package ie.dit.dt211.java;

public class Cat extends Animal implements Nameable {
	
	String name;
	
	Cat() {
		name = new String();
	}

	@Override
	public String sayHello() {
		return "Miaow";
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
