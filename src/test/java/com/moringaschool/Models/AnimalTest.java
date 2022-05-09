package com.moringaschool.Models;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    @Test
    public void test_Animal_Class() throws Exception {
        Animal rhino = new Animal("Rhino");
        Animal ostrich = new Animal("ostrich");
        assertEquals("Rhino", rhino.getName());
        assertEquals("ostrich", ostrich.getName());
    }
}