Builder Patter
Example taken from Effective Java, 3rd edition.

Class to Build contains a static method
for Building the actual class. This approach maintains immutability of the resulting instance. 

CLASS-TO-BUILD {
    PRIVATE CONSTRUCTOR(Builder) {
         use Builder to populate fields
    }
    
   
    
    static builder class {
    
        initialize all optional parameters to default values.
        builder(required parameters)
        
        public Builder operation_1(parameter)
        {
        return Builder
        }
        
         // expose a putblic build method taking Builder as a parameter
         
         public CLASS-TO-BUILD build() {
             return CLASS-TO-BUILD(this)
         }
  }
    
 