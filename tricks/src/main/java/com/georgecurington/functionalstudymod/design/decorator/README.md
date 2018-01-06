### Decorator design pattern ###
[a link](https://www.journaldev.com/1540/decorator-design-pattern-in-java-example)
Is used to modify the functionality of an object at runtime. At the same time other instances of the same class will not be affected by this, so individual object gets the modified behavior. Decorator design pattern is one of the structural design pattern (such as Adapter Pattern, Bridge Pattern, Composite Pattern) and uses abstract classes or interface with composition to implement.

We use inheritance or composition to extend the behavior of an object but this is done at compile time and its applicable to all the instances of the class. We can’t add any new functionality of remove any existing behavior at runtime – this is when Decorator pattern comes into picture.

Suppose we want to implement different kinds of cars – we can create interface Car to define the assemble method and then we can have a Basic car, further more we can extend it to Sports car and Luxury Car. The implementation hierarchy will look like below image.

                           CAR
                            |
                            
                         BASIC CAR
                         
                      |               |
                    
                   SportsCar      LuxuryCar 
                   
But if we want to get a car at runtime that has both the features of sports car and luxury car, then the implementation gets complex and if further more we want to specify which features should be added first, it gets even more complex. Now imagine if we have ten different kind of cars, the implementation logic using inheritance and composition will be impossible to manage. To solve this kind of programming situation, we apply decorator pattern in java.

We need to have following types to implement decorator design pattern.

1. Component Interface – The interface or abstract class defining the methods that will be implemented. In our case Car will be the component interface.

public interface Car { 
    public void assemble();
}

2. Component Implementation – The basic implementation of the component interface. We can have BasicCar class as our component implementation.      

public class BasicCar implements Car {   
    @Override
    public void assemble() {
        System.out.print("Basic Car.");
    }
}

3. Decorator – Decorator class implements the component interface and it has a HAS-A relationship with the component interface. The component variable should be accessible to the child decorator classes, so we will make this variable protected.

public class CarDecorator implements Car {
    protected Car car;
    public CarDecorator(Car c) {
        this.car = c;
    }
 
    @Override
    public void assemble() {
        this.car.assemble();
    }
    
}

4. Concrete Decorators – Extending the base decorator functionality and modifying the component behavior accordingly. We can have concrete decorator classes as LuxuryCar and SportsCar.
                  
                  
public class SportsCar extends CarDecorator {
    public SportsCar(Car c) {
        super(c);
    }
    @Override 
    public void assemble() {
        super.assemble();
        System.out.print(" Adding features of sports car.");
    }
}
        
public class LuxuryCar extends CarDecorator {

    public LuxuryCar(Car c) { 
        super(c);
    }
    
    @Override   
    public void assemble() {
        super.assemble();
        System.out.println(" Adding features of luxury car.");
    }
    
Class Diagram below:
![alt text]https://github.com/sidhartha11/InterviewTricks/tree/master/tricks/readmeimages/decorator-pattern-700x486.png)
   
                         
                           
