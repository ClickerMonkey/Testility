testility
=========

![Stable](http://i4.photobucket.com/albums/y123/Freaklotr4/stage_stable.png)

A small Java library with a few classes I've used in other projects in JUnit tests.

**Features**
- You can order test methods (sometimes test methods can execute in random order)

**Documentation**
- [JavaDoc](http://gh.magnos.org/?r=http://clickermonkey.github.com/Testility/)

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
- [testility-1.0.0.jar](http://gh.magnos.org/?r=https://github.com/ClickerMonkey/Testility/blob/master/build/testility-1.0.0.jar?raw=true)
- [testility-src-1.0.0.jar](http://gh.magnos.org/?r=ttps://github.com/ClickerMonkey/Testility/blob/master/build/testility-src-1.0.0.jar?raw=true) *- includes source code*

**Projects using testility:**
- [taskaroo](http://gh.magnos.org/?r=https://github.com/ClickerMonkey/Taskaroo)
- [statastic](http://gh.magnos.org/?r=https://github.com/ClickerMonkey/Statastic)
- [daperz](http://gh.magnos.org/?r=https://github.com/ClickerMonkey/Daperz)
- [surfice](http://gh.magnos.org/?r=https://github.com/ClickerMonkey/Surfice)
- [zource](http://gh.magnos.org/?r=https://github.com/ClickerMonkey/Zource)
- [falcon](http://gh.magnos.org/?r=https://github.com/ClickerMonkey/Falcon)
- [buffero](http://gh.magnos.org/?r=https://github.com/ClickerMonkey/Buffero)
- [curity](http://gh.magnos.org/?r=https://github.com/ClickerMonkey/Curity)

**Dependencies**
- JUnit
