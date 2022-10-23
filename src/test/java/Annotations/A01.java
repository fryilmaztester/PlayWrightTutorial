package Annotations;



import org.junit.jupiter.api.*;

public class A01 {


    @BeforeEach
    public void setUp01(){
        System.out.println("Running BEFORE METHOD Each");
    }
    @AfterEach
    public void tearDown01(){
        System.out.println("Running After METHOD Each");
    }
    @BeforeAll
    public static void setUp(){
        System.out.println("Running BEFORE METHOD Alll");
    }
    @AfterAll
    public static void tearDown() {
        System.out.println("Running AFTER METHOD All");
    }
    @Test
    public void test1(){
        System.out.println("Running Test 1");
    }

    @Test
    public void test2(){
        System.out.println("Running Test 2");
    }


    @Test
    public void test3(){
        System.out.println("Running Test 3");
    }

    @Test
    public void test4(){
        System.out.println("Running Test 4");
    }
}
