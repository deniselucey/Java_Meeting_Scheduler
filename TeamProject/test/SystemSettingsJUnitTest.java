/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import teamproject.system.Property;
import teamproject.system.SystemSetting;

/**
 *
 * @author Nigel
 */
public class SystemSettingsJUnitTest
{
    
    public SystemSettingsJUnitTest()
    {
    }
    
   
    @Ignore
    public void TestCanAccessWithAdmin()
    {
        //TODO
    }
    
    @Ignore
    public void TestCanAccessWithNonAdmin()
    {
        //TODO
    }
    

    @BeforeClass
    public static void setUpClass_TestIsInitialized()
    {
        assertThat(SystemSetting.isInitialized(), is(equalTo(false)));
        SystemSetting.initSystemSetting();
        assertThat(SystemSetting.isInitialized(), is(equalTo(true)));
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
       
            SystemSetting.loadDefault(null);
            SystemSetting.saveSettings(null);
     
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:

    @Test
    public void TestDefaultValues() throws IOException
    {
        SystemSetting.loadDefault(null);
        
        int i = 0;
        for(Property Sp:Property.values())
        {
            i++;
            //Tests if setting load is equal to defaul value.
            assertThat(Sp.getDefaultValue(), is(equalTo(SystemSetting.getProperty(Sp, ""))));
        }
        assertThat(Property.values().length, is(equalTo(i)));
    }
    
    @Test
    public void TestSettingSoftPropertyValueNotStored() throws IOException
    {
        SystemSetting.loadDefault(null);
        Integer i = -1000;
        for(Property Sp:Property.values())
        {
            --i;
            SystemSetting.setProperty(Sp, i.toString() , null);//sets soft properties, not stored
            assertThat(i.toString(), is(not(equalTo(SystemSetting.getProperty(Sp, "")))));
         
        }
    }
    
    @Test
    public void TestSavingOfPropertiesAndReloadingOfHardProperties() throws IOException
    {
        //make sure is init
        SystemSetting.initSystemSetting();
        Integer i = 0;
        for(Property Sp:Property.values())
        {
            //sets Properties to i. 1 < i <= Property.values().length
            i++;
            SystemSetting.setProperty(Sp, i.toString() , null);
        }
        
        //stores then in file
        SystemSetting.saveSettings(null);
        SystemSetting.initSystemSetting();//reloads hardProperties
        for(Property Sp:Property.values())
        {
             //sets Properties to i. Property.values().length < i   <= (Property.values().length)*2
            i++;
            SystemSetting.setProperty(Sp, i.toString() , null);
        }
        //soft propertys are set between Property.values().length and (Property.values().length)*2
        i = 0;
        SystemSetting.initSystemSetting();//reload properties from file
        for(Property Sp:Property.values())
        {
            i++;
            assertThat(i.toString(), is(equalTo(SystemSetting.getProperty(Sp, ""))));
        }
    } 
    
    @Test
    public void testForm()
    {
        SystemSetting s = new SystemSetting();
        System.out.println(s.toHtmlForm());
    }

}
