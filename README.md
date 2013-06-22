testility
=========

A small Java library with a few classes I've used in other projects in JUnit tests.

**Features**
- You can order test methods (sometimes test methods can execute in random order)

**Documentation**
- [JavaDoc](http://clickermonkey.github.com/testility/)

**Example**

```java
@RunWith(value=OrderedRunner.class)
public class TestOrdered extends BaseTest 
{
    @Test @Ordered(index = 3)
    public void test3() {
        broadcast();
    }
    @Test @Ordered(index = 2)
    public void test2a() {
        broadcast();
    }
    @Test @Ordered(index = 2)
    public void test2b() {
        broadcast();
    }
    @Test @Ordered(index = 1)
    public void test1()  {
        broadcast();
    }    
}
// IN test1
// IN test2a
// IN test2b
// IN test3
```

**Builds**
- [testility-1.0.0.jar](https://github.com/ClickerMonkey/testility/blob/master/build/testility-1.0.0.jar?raw=true)
- [testility-src-1.0.0.jar](https://github.com/ClickerMonkey/testility/blob/master/build/testility-src-1.0.0.jar?raw=true) *- includes source code*

**Projects using testility:**
- [taskaroo](https://github.com/ClickerMonkey/taskaroo)
- [statastic](https://github.com/ClickerMonkey/statastic)
- [daperz](https://github.com/ClickerMonkey/daperz)
- [surfice](https://github.com/ClickerMonkey/surfice)
- [zource](https://github.com/ClickerMonkey/zource)
- [falcon](https://github.com/ClickerMonkey/falcon)
- [buffero](https://github.com/ClickerMonkey/buffero)
- [curity](https://github.com/ClickerMonkey/curity)

**Dependencies**
- JUnit
