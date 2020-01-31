package com.crud.tasks.domain;

import com.crud.tasks.mapper.TaskMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DomainTestSuite {
    @Autowired
    private TaskMapper taskMapper;




    @Test
    public void trelloDtoTest(){
        //Given
        TrelloDto trelloDto;
        TrelloDto trelloDto1 = new TrelloDto();
        //When
        trelloDto = new TrelloDto(10, 10);
        //Then
        Assert.assertEquals(10, trelloDto.getBoard());
        Assert.assertEquals(10, trelloDto.getCard());
        Assert.assertEquals(0, trelloDto1.getCard());
    }

    @Test
    public void taskMapperTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title1", "content1");
        TaskDto taskDto1 = new TaskDto();
        List<Task> tasks = new ArrayList<>();
        //When
        Task task = taskMapper.mapToTask(taskDto);
        taskDto = taskMapper.mapToTaskDto(task);
        tasks.add(taskMapper.mapToTask(taskDto));
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(tasks);
        //Then
        Assert.assertEquals((Long)1L, taskDto.getId());
        Assert.assertEquals("title1", taskDto.getTitle());
        Assert.assertEquals("content1", taskDto.getContent());
        Assert.assertEquals((Long)1L, task.getId());
        Assert.assertEquals("title1", task.getTitle());
        Assert.assertEquals("content1", task.getContent());
        Assert.assertEquals((Long)1L, taskDtos.get(0).getId());
        Assert.assertEquals("title1", taskDtos.get(0).getTitle());
        Assert.assertEquals("content1", taskDtos.get(0).getContent());
        Assert.assertEquals(1, taskDtos.size());
        Assert.assertTrue(taskDto1.getId()== null);
    }

    @Test
    public void badgesTest(){
        //Given
        TrelloDto trelloDto = new TrelloDto(1 , 1);
        AttachmentsByTypeDto attachmentsByTypeDto = new AttachmentsByTypeDto(trelloDto);
        AttachmentsByTypeDto attachmentsByTypeDto1 = new AttachmentsByTypeDto();
        BadgesDto badgesDto1 = new BadgesDto();
        //When
        BadgesDto badgesDto = new BadgesDto(1, attachmentsByTypeDto);
        //Then
        Assert.assertEquals(1, badgesDto.getVotes());
        Assert.assertEquals(1, badgesDto.getAttachmentsByTypeDto().getTrello().getCard());
        Assert.assertEquals(1, badgesDto.getAttachmentsByTypeDto().getTrello().getBoard());
        Assert.assertEquals(null, attachmentsByTypeDto1.getTrello());
        Assert.assertEquals(0, badgesDto1.getVotes());
    }

}
