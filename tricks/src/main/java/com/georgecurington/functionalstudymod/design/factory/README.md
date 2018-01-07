Welcome to the Factory Design Pattern in Java tutorial. 
Factory Pattern is one of the Creational Design pattern and it’s 
widely used in JDK as well as frameworks like Spring and Struts.

[see link]:https://www.journaldev.com/1392/factory-design-pattern-in-java

Factory design pattern is used when we have a super class with multiple sub-classes 
and based on input, we need to return one of the sub-class. 
This pattern take out the responsibility of instantiation of a class from client 
program to the factory class.

Let’s first learn how to implement factory design pattern in java and 
then we will look into factory pattern advantages. 
We will see some of factory design pattern usage in JDK. Note that this pattern is also 
known as Factory Method Design Pattern.

### Factory Design Pattern Super Class ###
Super class in factory design pattern can be an interface, abstract class or a normal java class. 
For our factory design pattern example, we have abstract super class with 
overridden toString() method for testing purpose.

```public abstract class Computer {
	
	public abstract String getRAM();
	public abstract String getHDD();
	public abstract String getCPU();
	
	@Override
	public String toString(){
		return "RAM= "+this.getRAM()+", HDD="+this.getHDD()+", CPU="+this.getCPU();
	}
}```

### Factory Design Pattern Sub Classes ###
Let’s say we have two sub-classes PC and Server with below implementation.

```
    package com.journaldev.design.model;

    public class PC extends Computer {

	private String ram;
	private String hdd;
	private String cpu;
	
	public PC(String ram, String hdd, String cpu){
		this.ram=ram;
		this.hdd=hdd;
		this.cpu=cpu;
	}
	@Override
	public String getRAM() {
		return this.ram;
	}

	@Override
	public String getHDD() {
		return this.hdd;
	}

	@Override
	public String getCPU() {
		return this.cpu;
	}

}
```

Notice that both the classes are extending Computer super class.

```
    package com.journaldev.design.model;

    public class Server extends Computer {

	private String ram;
	private String hdd;
	private String cpu;
	
	public Server(String ram, String hdd, String cpu){
		this.ram=ram;
		this.hdd=hdd;
		this.cpu=cpu;
	}
	@Override
	public String getRAM() {
		return this.ram;
	}

	@Override
	public String getHDD() {
		return this.hdd;
	}

	@Override
	public String getCPU() {
		return this.cpu;
	}

}
```
Factory Class
Now that we have super classes and sub-classes ready, 
we can write our factory class. 
Here is the basic implementation.

```
package com.journaldev.design.factory;

import com.journaldev.design.model.Computer;
import com.journaldev.design.model.PC;
import com.journaldev.design.model.Server;

    public class ComputerFactory {

	public static Computer getComputer(String type, String ram, String hdd, String cpu){
		if("PC".equalsIgnoreCase(type)) return new PC(ram, hdd, cpu);
		else if("Server".equalsIgnoreCase(type)) return new Server(ram, hdd, cpu);
		
		return null;
	}
}
```

Some important points about Factory Design Pattern method are;

We can keep Factory class Singleton or 
we can keep the method that returns the subclass as static.
Notice that based on the input parameter, different subclass is created and returned. 
getComputer is the factory method.

class diagram

![alt text](/InterviewTricks/tricks/readmeimages/factory-pattern-java.png)

### Factory Design Pattern Advantages ###
1. Factory design pattern provides approach to code for interface rather than implementation.
2. Factory pattern removes the instantiation of actual implementation classes from client code. 
Factory pattern makes our code more robust, less coupled and easy to extend. 
For example, we can easily change PC class implementation because client program is unaware of this.
3. Factory pattern provides abstraction between implementation and client classes through inheritance.

### Factory Design Pattern Examples in JDK ###
1. java.util.Calendar, ResourceBundle and NumberFormat getInstance() methods 
uses Factory pattern.
2. valueOf() method in wrapper classes like Boolean, Integer etc.

