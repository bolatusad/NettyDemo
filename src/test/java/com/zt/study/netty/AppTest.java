package com.zt.study.netty;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
//        assertTrue( true );

        System.out.println("hello");
        testAA(20);
    }

    public void testAA(Integer a){
        Integer c = a;

        a = 10;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("");
            }
        });
        System.out.println(c);
    }


}
