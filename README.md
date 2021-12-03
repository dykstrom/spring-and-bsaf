# spring-and-bsaf

This small application demonstrates how to make the 
[Spring Framework](https://spring.io/projects/spring-framework) and the
[Better Swing Application Framework (BSAF)](https://sourceforge.net/projects/bsaf)
work together. We leave the question of _why_ you would like to do that
to another time. ;-)

The problem with combining the two frameworks is that they both want to create
the singleton instance of the main application class. BSAF requires you to
create and run your Application subclass by calling the static _launch_ method. 
Spring of course wants to create all managed beans as part of the application
context.

The solution is to introduce a @Configuration class with a method that creates 
a Spring bean from the BSAF application instance. In the Application subclass,
you let BSAF create the application:

```java
public static void main(String[] args) {
    // Create the singleton application instance using BSAF here
    launch(BsafApplication.class, args);
}
```
    
In the @Configuration class, you create a bean that just returns the singleton
instance:

```java
@Bean
public BsafApplication application() {
    // Return the singleton application instance as a Spring bean here
    return (BsafApplication) Application.getInstance();
}
```

This enables the application instance to be injected into other classes, even 
though it was created before the application context was loaded. You just have 
to be careful to not access the application bean before the application instance
has been created by calling _launch_. Otherwise, BSAF will create a dummy instance
that is not compatible with your real Application subclass.

For a Kotlin version of this project, please see
[spring-and-bsaf-kotlin](https://github.com/dykstrom/spring-and-bsaf-kotlin).
