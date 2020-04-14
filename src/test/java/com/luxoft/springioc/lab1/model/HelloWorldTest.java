package com.luxoft.springioc.lab1.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HelloWorldTest {

    protected static final String APPLICATION_CONTEXT_XML_FILE_NAME = "classpath:application-context.xml";

    private UsualPerson expectedPerson;

    private AbstractApplicationContext context;

    @Before
    public void setUp() throws Exception {
        context = new FileSystemXmlApplicationContext(new String[]{APPLICATION_CONTEXT_XML_FILE_NAME});
        expectedPerson = getExpectedPerson();
    }

    @Test
    public void testInitPerson() {
        List<UsualPerson> persons = new ArrayList<>();
        persons.add((UsualPerson) context.getBean("person", Person.class));
        persons.add((UsualPerson) context.getBean("personByConstructor", Person.class));
        persons.add((UsualPerson) context.getBean("personByName", Person.class));
        persons.add((UsualPerson) context.getBean("personByType", Person.class));
        persons.add((UsualPerson) context.getBean("personFullConstructor", Person.class));

        for (UsualPerson person : persons) {
            System.out.println("-->" + person.getCountry().getA());
            assertEquals(expectedPerson, person);
        }
    }

    private UsualPerson getExpectedPerson() {
        UsualPerson person = new UsualPerson();
        person.setAge(35);
        person.setName("John Smith");

        Country country = new Country();
        country.setId(1);
        country.setName("Russia");
        country.setCodeName("RU");

        System.out.println("-->" + country.getA());

        person.setCountry(country);

        return person;
    }

    @After
    public void tearDown() throws Exception {
        if (context != null)
            context.close();
    }
}
