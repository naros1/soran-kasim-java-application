package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DBServiceTestSuite {
    @Autowired
    private DbService dbService;

    @Test
    public void dbServiceMethodsTest(){
        //Given
        Task task = new Task(1L, "Title1", "Content1");
        //When
        dbService.saveTask(task);
        Optional<Task> readTask1 = dbService.getTaskById(1L);
        Task readTask2 = dbService.getAllTasks().get(0);
        //Then
        Assert.assertEquals("Title1", readTask1.get().getTitle());
        Assert.assertEquals("Title1", readTask2.getTitle());
        //CleanUp
        try {
            dbService.deleteTaskById(1L);
        }catch (Exception e){
            System.out.println(e);
        }




    }
}
