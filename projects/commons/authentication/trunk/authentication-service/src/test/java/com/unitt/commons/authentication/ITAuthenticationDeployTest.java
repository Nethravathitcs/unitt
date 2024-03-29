package com.unitt.commons.authentication;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rioproject.test.RioTestRunner;
import org.rioproject.test.SetTestManager;
import org.rioproject.test.TestManager;


/**
 * Testing the Authentication service using the Rio test framework
 * 
 * @author: Generated by the Rio Archetype
 */
@RunWith( RioTestRunner.class )
public class ITAuthenticationDeployTest extends ITAbstractAuthenticationTest
{
    @SetTestManager
    static TestManager testManager;
    Authentication     service;

    @Before
    public void setup() throws Exception
    {
        Assert.assertNotNull( testManager );
        service = (Authentication) testManager.waitForService( Authentication.class );
    }

    @Test
    public void test1()
    {
        testService( service );
    }
}
