## Chapter 2. Creating and destroying objects

### Item 1. Consider static factory methods over constructors:

The static factory method is not the same as the Factory Design Pattern

Adv:
1. **It is easier to name.** In cases, where a class seems to require multiple constructors with the same signature, replace the constructors with static factory methods and carefully chosen names to highlight their differences.
2. **No need to create a new instance, when the method is invoked, unlike constructors.** The Boolean.valueOf(boolean) method illustrates this technique: it creates an obj. This technique is similar to the Flyweight pattern
3. **Can return an object of any subtype of their return.**
4. **Returned object can vary from call to call as a function of the input params.** Can return other implementations. based on different input params. The _EnumSet_ class has no public constructors, only static factories. In the OpenJDK impl, they return an instance of noe of 2 subclasses, depending on the size of the underlying enum type: if it has sixty-four or fewer elements, as most enum types do, the static factories return a RegularEnumSet, which is backed by a single long; if the enum type has sixty-five or more elements, the factories return a JumboEnumSet instance, backed by a long array.
5. **The class of the returned object need not exist when the class containing the method is written**. The return type can be an interface, the implementation of it can be generated later. The actual impl. can be loaded dynamically. Like JDBC API service provider, decouples implementation

Dis:
1. **Classes without public or protected constructors can't be subclassed**
2. **static factory methods is that they are hard for devs to find**

## Item 2. Consider using a builder when faced with many constructor params

Case: class has a lot of props some of them are optional, like for ex. NutritionFacts

1. **Telescoping constructor patters.** suggests to have a lot of overloaded constructors. Dis. it doesn't look clean
2. **Java Beans patterns** proposes to use setters in that case, but it's not thread-safe and have inconsistent behaviour partway through the constructor, it rejects immutability, although looks clean.
3. **Builder patter**, allows us to have a nice and clean code, with its safety, good for classes whic hhave multiple varargs2, also can aggregate the params into multiple calls. But in perf-critical situations, it can take some to create the builder, every class should have its own builder.

Covariant return typing - subclass returns type of subclass

### Item 3. Enforce the singleton prop with a private constructor or an enum type

The class which is instantiated only once called singleton
Represents a stateless object such as a function, or system component that is unique

This can be achieved in 2 ways:
1. Public field, but privileged client can invoke private constructor reflectively with _AccessibleObject.setAccessible_ method. To defend against this attack, modify constructor to make it throw an exception
2. Static factory method: pros factory can be generic, method reference can be used as a supplier
3. Enum singleton preferred approach, provides serialization, the best way. But if your singleton should extend a superclass other than Enum, u can't

To make the class Serializable using 1st 2 approaches, besides implementing a Serializabl interface, we need to make all fields transient, and provide readResolve(). 
Otherwise, when serialized every time, a new instance will be created

Ways how it can be broken can be found [here.](https://www.geeksforgeeks.org/prevent-singleton-pattern-reflection-serialization-cloning/)
**`Breaking the singleton pattern in the app can cause Dos of System, UnAuth access, inconsistent logging, etc.**
`
### Item 4 Enforce noninstantiability with a private constructor

By making a class abstract, we cannot guarantee that a constructor will be created, so a class can be made noninstantiable by including a private constructor:

// Noninstantiable utility class 
```
public class UtilityClass {
    private UtilityClass() { throw new AssesrtionError();}
}
```
utility classes were not designed to be instantiates, as they have static fields
like Arrays, Collections classes which have private constructors
As a side effect, this idiom also prevents the class from being subclassed

### Item 5. Prefer dependency injection to hardwiring resources

**Static utility classes and singletons are inappropriate for
classes whose behavior is parameterized by an underlying resource**

Spell checker depends on dictionary
A simple pattern that satisfies this requirement is to
pass the resource into the constructor when creating a new instance
```
// Dependency injection provides flexibility and testability
public class SpellChecker {
    private final Lexicon dictionary;
    public SpellChecker(Lexicon dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary);
    }
    public boolean isValid(String word) { ... }
    public List<String> suggestions(String typo) { ... }
}
```
In summary, do not use a singleton or static utility class to implement a class that depends on one or more underlying resources whose behavior affects that of the class, and do not have the class create these resources directly. Instead, pass the resources, or factories to create them, into the constructor (or static factory or builder).
### Item 6.  Avoid creating unnecessary objects
It is often appropriate to reuse a single object instead of creating a new function- ally equivalent object each time it is needed. Reuse can be both faster and more stylish. An object can always be reused if it is immutable (Section 17).

Some object creations are much more expensive than others. If you’re going to need such an “expensive object” repeatedly, it may be advisable to cache it for reuse.

Another way to create unnecessary objects is autoboxing, which allows the programmer to mix primitive and boxed primitive types, boxing and unboxing automatically as needed. Autoboxing blurs but does not erase the distinction between primitive and boxed primitive types.

— Prefer primitives to boxed primitives, and watch out for unintentional autoboxing.


### Item 7. Eliminate obsolete obj references
Generally speaking, whenever a class manages its own memory, the programmer should be alert for memory leaks. Whenever an element is freed, any object references contained in the element should be nulled out. Another common source of memory leaks is caches. A third common source of memory leaks is listeners and other callbacks.

### Item 8. Avoid finalizers and cleaners

— Finalizers are unpredictable, often dangerous, and generally unnecessary.
— The Java 9 replacement for finalizers is cleaners. Cleaners are less dangerous than finalizers, but still unpredictable, slow, and generally unnecessary.
One shortcoming of finalizers and cleaners is that there is no guarantee they’ll be executed promptly [JLS, 12.6]. It can take arbitrarily long between the time that an object becomes unreachable and the time its finalizer or cleaner runs. This means that you should never do anything time-critical in a finalizer or cleaner.

So what should you do instead of writing a finalizer or cleaner for a class whose objects encapsulate resources that require termination, such as files or threads? Just have your class implement AutoCloseable, and require its clients to invoke the close method on each instance when it is no longer needed.

### Section 9: Prefer try-with-resources to try — finally
Always use try -with-resources in preference to try-finally when working with resources that must be closed. The resulting code is shorter and clearer, and the exceptions that it generates are more useful.


### Section 10: Obey the general contract when overriding equals
The easiest way to avoid problems is not to override the equals method, in which case each instance of the class is equal only to itself. This is the right thing to do if any of the following conditions apply:

Each instance of the class is inherently unique.
There is no need for the class to provide a “logical equality” test.
A superclass has already overridden equals , and the superclass behavior is appropriate for this class.
The class is private or package-private, and you are certain that its equals method will never be invoked.
So when is it appropriate to override equals? It is when a class has a notion of logical equality that differs from mere object identity and a superclass has not already overridden equals. This is generally the case for value classes. A value class is simply a class that represents a value, such as Integer or String.

When you are finished writing your equals method, ask yourself three questions: Is it symmetric? Is it transitive? Is it consistent?

Here are a few final caveats:
* Always override hashCode when you override equals
* Don’t try to be too clever. If you simply test fields for equality, it’s not hard to adhere to the equals contract
IDEs generate equals (and hashCode) methods is generally preferable to implementing them manually because IDEs do not make careless mistakes, and humans do.

### Section 11. Always override hashCode when you override equals
Don’t try to be too clever. If you simply test fields for equality, it’s not hard to adhere to the equals contract
IDEs generate equals (and hashCode) methods is generally preferable to implementing them manually because IDEs do not make careless mistakes, and humans do.

Section 11: Always override hashCode when you override equals
You must override hashCode in every class that overrides equals.

— The key provision that is violated when you fail to override hashCode is the second one: equal objects must have equal hash codes.
This one, for example, is always legal but should never be used:

// The worst possible legal hashCode implementation - never use!
```@Override public int hashCode() { return 42; }```
It’s legal because it ensures that equal objects have the same hash code. It’s atrocious because it ensures that every object has the same hash code. Therefore, every object hashes to the same bucket, and hash tables degenerate to linked lists.

The Objects class has a static method that takes an arbitrary number of objects and returns a hash code for them. This method, named hash , lets you write one-line hashCode methods whose quality is comparable to those written according to the recipe in this item.

// One-line hashCode method - mediocre performance
```
@Override public int hashCode() {
return Objects.hash(lineNum, prefix, areaCode);
}
```

### Section 12: Always override toString
— Providing a good toString implementation makes your class much more pleasant to use and makes systems using the class easier to debug.
The toString method is automatically invoked when an object is passed to println, printf, the string concatenation operator, or assert, or is printed by a debugger.

Nor should you write a toString method in most enum types because Java provides a perfectly good one for you. You should, however, write a toString method in any abstract class whose subclasses share a common string representation. For example, the toString methods on most collection implementations are inherited from the abstract collection classes.

To recap, override Object’s toString implementation in every instantiable class you write, unless a superclass has already done so. It makes classes much more pleasant to use and aids in debugging.

