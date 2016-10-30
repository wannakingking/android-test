package anony.test.webviewdemo;

public class JavaArrayJSWrapper
{
    private Object[] innerArray;

    public JavaArrayJSWrapper(Object[] object)
    {
        this.innerArray = object;
    }

    public int length()
    {
        return this.innerArray.length;
    }

    public Object get(int index)
    {
        return this.innerArray[index];
    }
}
